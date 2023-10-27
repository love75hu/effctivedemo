package cn.mediinfo.grus.shujuzx.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.StopWatch;
import cn.hutool.core.text.CharSequenceUtil;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.util.JacksonUtil;
import cn.mediinfo.cyan.msf.core.util.MapUtil;
import cn.mediinfo.cyan.msf.core.util.MapUtils;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.cyan.msf.tenant.security.TenantIdentityService;
import cn.mediinfo.grus.shujuzx.bo.RelatedFangAnBO;
import cn.mediinfo.grus.shujuzx.common.fangan.condition.*;
import cn.mediinfo.grus.shujuzx.dto.fangannr.FangAnSqlDTO;
import cn.mediinfo.grus.shujuzx.dto.fangansc.FangAnSCDTO;
import cn.mediinfo.grus.shujuzx.dto.fangansc.QueryField;
import cn.mediinfo.grus.shujuzx.dto.shitumx.*;
import cn.mediinfo.grus.shujuzx.enums.FangAnOperator;
import cn.mediinfo.grus.shujuzx.enums.NodeTypeEnum;
import cn.mediinfo.grus.shujuzx.enums.ShuJuZLXDMEnum;
import cn.mediinfo.grus.shujuzx.manager.FangAnManager;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnSC;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnXXSaveRequest;
import cn.mediinfo.grus.shujuzx.service.FangAnNRService;
import cn.mediinfo.grus.shujuzx.service.FangAnSCService;
import cn.mediinfo.grus.shujuzx.service.FangAnService;
import cn.mediinfo.grus.shujuzx.service.ShiTuMXService;
import cn.mediinfo.grus.shujuzx.sql.ast.SQLQueryExpr;
import cn.mediinfo.grus.shujuzx.sql.ast.SQLQueryNode;
import cn.mediinfo.grus.shujuzx.sql.ast.SQLQueryObject;
import cn.mediinfo.grus.shujuzx.sql.enums.SQLBinaryOperator;
import cn.mediinfo.grus.shujuzx.util.FangAnTreeUtils;
import cn.mediinfo.grus.shujuzx.util.SqlUtils;
import com.querydsl.core.util.CollectionUtils;
import jakarta.annotation.Resource;
import kotlin.Triple;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.analysis.function.Subtract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FangAnServiceImpl implements FangAnService {

    @Autowired
    private ShiTuMXService shiTuMXService;

    @Autowired
    private FangAnSCService fangAnSCService;

    @Autowired
    private FangAnManager fangAnManager;

    @Autowired
    private TenantIdentityService tenantIdentityService;

    @Autowired
    private FangAnNRService fangAnNRService;

    @Resource
    @Qualifier("datasourcesjzx_jdbcTemplateFactory")
    JdbcTemplate jdbcTemplate;

    /**
     * 保存方案
     * @param request
     */
    @Override
    public String saveFangAn(FangAnXXSaveRequest request) throws YuanChengException {
        StopWatch watch = new StopWatch();
        watch.start("AST树转换成sql");
        String sql = getSql(request.getRoot(), request.getFangAnSCList(), request.getFangAnLXDM());
        log.info("sql expr sql:{}", sql);
        watch.stop();
        log.info(watch.prettyPrint(TimeUnit.MILLISECONDS));
        return fangAnManager.saveFangAn(request, sql);
    }

    /**
     * 获取SQL
     * @param root 方案树
     * @param fangAnSCList 方案输出项
     * @param fangAnLXDM 方案类型代码
     * @return String
     * @throws YuanChengException
     */
    @Override
    public String getSql(FangAnTreeNode root, List<FangAnSC> fangAnSCList, String fangAnLXDM) throws YuanChengException {
        List<FangAnCondition> conditionList = ListUtil.toList();
        FangAnTreeUtils.getConditionList(root, conditionList);
        //todo 校验条件

        //子方案
        subFangAn(conditionList);

        Set<String> shiTuMXIds = conditionList.stream().map(FangAnCondition::getShiTuMXID).collect(Collectors.toSet());
        //from
        List<TableDTO> tableList = shiTuMXService.listTable(shiTuMXIds);
        tableList = (CollUtil.isEmpty(tableList)) ? ListUtil.toList() : tableList;

        //输出字段
        FangAnSCDTO fangAnSC = fangAnSCService.getAllFangAnSC(fangAnSCList);
        fangAnSC = fangAnSC == null ? new FangAnSCDTO() : fangAnSC;

        //生成别名 key schema.table value alias
        Map<String,Triple<String,Boolean,Boolean>> aliasMap = getAliasMap(tableList,fangAnSC,fangAnLXDM);
        //拼接表关系
        String table = getTable(aliasMap);
        //拼接表关联关系
        String joinRelation = getTableJoinRelation(tableList, aliasMap, fangAnLXDM);
        //过滤条件
        String filterCondition = getFilterCondition(tableList, aliasMap);

        String fields = getQueryFields(fangAnSC, aliasMap);

        //where
        conditionList.forEach(e ->{
            if(CollUtil.isEmpty(e.getRelatedFieldConditions())){
                return;
            }
            e.getRelatedFieldConditions().forEach(s -> shiTuMXIds.add(s.getShiTuMXGXID()));
        });
        List<FieldDTO> fieldList = shiTuMXService.listFields(shiTuMXIds);
        fieldList.forEach(e -> e.setAlias(aliasMap.get(e.getMoShi() + "." + e.getBiaoMing()).getFirst()));
        //转化sql树
        Map<String, FieldDTO> fieldMap = fieldList.stream().collect(Collectors.toMap(FieldDTO::getId, e -> e));
        SQLQueryNode condition = transform(root, fieldMap);

        //构建sql树
        SQLQueryNode sqlRoot = new SQLQueryNode(new SQLQueryObject("SELECT"));
        sqlRoot.setLeft(new SQLQueryNode(new SQLQueryObject(fields)));
        sqlRoot.setRight(new SQLQueryNode(new SQLQueryObject("FROM")));
        sqlRoot.getRight().setLeft(new SQLQueryNode(new SQLQueryObject(table)));
        sqlRoot.getRight().setRight(new SQLQueryNode(new SQLQueryObject("WHERE")));

        SQLQueryNode where = sqlRoot.getRight().getRight();

        if (StringUtils.isEmpty(filterCondition) && StringUtils.isEmpty(joinRelation)) {
            where.setLeft(condition);
        } else {
            if (StringUtils.isEmpty(filterCondition)) {
                where.setLeft(new SQLQueryNode(new SQLQueryObject(joinRelation)));
                where.setRight(new SQLQueryNode(new SQLQueryObject(SQLBinaryOperator.AND)));
                where.getRight().setRight(condition);
            } else if (StringUtils.isEmpty(joinRelation)) {
                where.setLeft(new SQLQueryNode(new SQLQueryObject(filterCondition)));
                where.setRight(new SQLQueryNode(new SQLQueryObject(SQLBinaryOperator.AND)));
                where.getRight().setRight(condition);
            } else {
                where.setLeft(new SQLQueryNode(new SQLQueryObject(joinRelation)));
                where.setRight(new SQLQueryNode(new SQLQueryObject(SQLBinaryOperator.AND)));
                where.getRight().setRight(new SQLQueryNode(new SQLQueryObject(filterCondition)));
                where.getRight().setLeft(new SQLQueryNode(new SQLQueryObject(SQLBinaryOperator.AND)));
                where.getRight().getLeft().setRight(condition);
            }
        }

        SQLQueryExpr expr = new SQLQueryExpr(sqlRoot);
        log.debug("sql expr json:{}", JacksonUtil.getBeanToJson(expr));

        return expr.buildSQL(sqlRoot);
    }

    private void subFangAn(List<FangAnCondition> conditionList) {
        List<RelatedFangAnQueryCondition> list = ListUtil.toList();
        //方案
        List<RelatedFangAnQueryCondition> relatedFangAnQueryConditions = conditionList.stream().map(FangAnCondition::getRelatedFangAnQueryCondition).filter(Objects::nonNull).toList();
        if (CollUtil.isNotEmpty(relatedFangAnQueryConditions)) {
            list.addAll(relatedFangAnQueryConditions);
        }
        //关联条件-子方案查询
        conditionList.forEach(e -> {
            if (CollUtil.isEmpty(e.getRelatedFieldConditions())) {
                return;
            }
            e.getRelatedFieldConditions().forEach(f -> {
                if (f.getRelatedFangAnQueryCondition() != null) {
                    list.add(f.getRelatedFangAnQueryCondition());
                }
            });
        });

        if (CollUtil.isEmpty(list)) {
            return;
        }

        List<RelatedFangAnBO> relatedFangAnList = list.stream().map(e -> {
            RelatedFangAnBO relatedFangAn = new RelatedFangAnBO();
            relatedFangAn.setFangAnId(e.getFangAnId());
            relatedFangAn.setFangAnSCId(e.getFangAnSCId());
            return relatedFangAn;
        }).toList();

        List<FangAnSqlDTO> fangAnSqlList = fangAnNRService.listFangAnSql(relatedFangAnList);
        Map<String, FangAnSqlDTO> map = fangAnSqlList.stream().collect(Collectors.toMap(FangAnSqlDTO::getFangAnId, e -> e));


        conditionList.forEach(e -> {
            if (e.getRelatedFangAnQueryCondition() == null) {
                return;
            }
            RelatedFangAnQueryCondition relatedFangAnQueryCondition = e.getRelatedFangAnQueryCondition();
            FangAnSqlDTO fangAnSql = map.get(relatedFangAnQueryCondition.getFangAnId());
            relatedFangAnQueryCondition.setSql(fangAnSql.getSql());
            relatedFangAnQueryCondition.setZiDuanBM(fangAnSql.getZiDuanBM());
        });
    }

    /**
     * 将FangAnTree转化SQLQueryNode
     *
     * @param node     FangAnTree
     * @param fieldMap 字段Map
     * @return SQLQueryNode
     */
    private SQLQueryNode transform(FangAnTreeNode node, Map<String, FieldDTO> fieldMap) {
        if (node == null) {
            return null;
        }

        FangAnCondition condition = node.getCondition();
        String operator = condition == null ? node.getRelation() : condition.getOperator();
        SQLBinaryOperator sqlOperator = SQLBinaryOperator.getSQLBinaryOperator(operator);

        SQLQueryNode sqlNode;
        if (condition == null || NodeTypeEnum.RELATION_NODE.getType().equals(node.getNodeType())) {
            sqlNode = new SQLQueryNode(new SQLQueryObject(sqlOperator));
        } else {
            FieldDTO field = fieldMap.get(condition.getShiTuMXID());
            List<String> valList = condition.getValues().stream().map(FanganConditionValue::getVal).toList();
            SQLQueryObject obj = toSQLQueryObject(condition.getOperator(), field, valList, condition.getRelatedFangAnQueryCondition());
            //子条件
            if(CollUtil.isNotEmpty(condition.getRelatedFieldConditions())){
                String subSql = getRelatedFieldCondition(condition.getRelatedFieldConditions(),fieldMap);
                obj.setSubSql(subSql);
            }
            sqlNode = new SQLQueryNode(obj);
        }

        if(CollUtil.isEmpty(node.getChildrenConditions()))
        {
            return  sqlNode;
        }

        List<FangAnTreeNode> list=new ArrayList<>(node.getChildrenConditions());
        Collections.reverse(list);
        int index=0;
        SQLQueryNode currentSqlNode=sqlNode;
        for (FangAnTreeNode item : list) {
            boolean isGuanXiJD = Objects.isNull(item.getCondition());
            if (0 == index) {
                currentSqlNode.setRight(getGuanXiNode(transform(item, fieldMap),isGuanXiJD));
            } else if (index == list.size() - 1) {
                currentSqlNode.setLeft(getGuanXiNode(transform(item, fieldMap),isGuanXiJD));
            } else {
                SQLQueryNode childrenSqlNode = new SQLQueryNode(new SQLQueryObject(sqlOperator));
                childrenSqlNode.setRight(transform(item, fieldMap));
                currentSqlNode.setLeft(getGuanXiNode(childrenSqlNode,isGuanXiJD));
                currentSqlNode = currentSqlNode.getLeft();
            }
            index++;
        }
        return sqlNode;
    }

    /**
     * 组合关系节点
     *
     * @param childrenNode 子节点
     * @param isGuanXiJD 是否关系节点
     * @return SQLQueryNode
     */
    private SQLQueryNode getGuanXiNode(SQLQueryNode childrenNode,boolean isGuanXiJD) {
        if (!isGuanXiJD) {
            return childrenNode;
        }
        SQLQueryNode guanXiNode = new SQLQueryNode(new SQLQueryObject("()"));
        guanXiNode.setLeft(childrenNode);
        return  guanXiNode;
    }

    private SQLQueryObject toSQLQueryObject(String operator, FieldDTO field, List<String> valList, RelatedFangAnQueryCondition relatedFangAnQueryCondition) {
        String val = "";
        SQLBinaryOperator sqlOperator = SQLBinaryOperator.getSQLBinaryOperator(operator);
        if (CollUtil.isEmpty(valList)) {
            //子方案查询
            if (FangAnOperator.IN_FANGAN.getType().equals(operator)) {
                sqlOperator = SQLBinaryOperator.IN;
                val = SqlUtils.replaceOutputField(relatedFangAnQueryCondition.getSql(), relatedFangAnQueryCondition.getZiDuanBM());
            }
        } else if (valList.size() > 1) {
            sqlOperator =SQLBinaryOperator.getSQLBinaryOperator(operator);
            if (ShuJuZLXDMEnum.NUMBER.getType().equals(field.getShuJuZLXDM())) {
                val = CharSequenceUtil.join(",", valList);
            } else {
                val = CharSequenceUtil.join(",", spliceSingleQuotes(valList));
            }
        } else {
            boolean containFlag = FangAnOperator.isContain(operator);
            //判断字段类型
            if (containFlag || ShuJuZLXDMEnum.NUMBER.getType().equals(field.getShuJuZLXDM())) {
                val = valList.get(0);
            } else {
                val = CollUtil.isEmpty(valList) ? "" : "'" + valList.get(0) + "'";
            }
        }

        SQLQueryObject obj = new SQLQueryObject(sqlOperator);
        obj.setFieldName(field.getAlias() + "." + field.getZiDuanBM());
        obj.setVal(val);

        return obj;
    }


    private String getRelatedFieldCondition(List<RelatedFieldCondition> relatedFieldConditions, Map<String, FieldDTO> fieldMap) {
        if (CollUtil.isEmpty(relatedFieldConditions)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        relatedFieldConditions.forEach(e -> {
            FieldDTO field = fieldMap.get(e.getShiTuMXGXID());
            List<String> valList = e.getValues().stream().map(FanganConditionValue::getVal).toList();
            SQLQueryObject obj = toSQLQueryObject(e.getOperator(),field,valList,e.getRelatedFangAnQueryCondition());
            builder.append(obj.getText());
            builder.append(" AND ");
        });

        return  builder.toString();
    }

    /**
     * 获取表别名
     *
     * @param tableList 表
     * @param fangAnSC 方案输出
     * @param fangAnLXDM 方案类型代码
     * @return Map
     */
    private Map<String,Triple<String,Boolean,Boolean>> getAliasMap(List<TableDTO> tableList, FangAnSCDTO fangAnSC, String fangAnLXDM) {
        Map<String,Triple<String,Boolean,Boolean>> aliasMap= MapUtil.newHashMap(); //First：别名，Second:是否外连表，Third:是否基础表
        List<SchemaTable> schemaTableList = ListUtil.toList();
        tableList.forEach(e -> schemaTableList.addAll(e.getSchemaTableList()));

        //基础表
        SchemaTable jiChuBiao=new SchemaTable();
        switch (fangAnLXDM)
        {
            case "1":
                jiChuBiao.setBiaoMing("jz_mz_jiuzhenxx");
                jiChuBiao.setMoShi("vela_jz");
                break;
            case "3":
                jiChuBiao.setBiaoMing("jz_zy_jiuzhenxx");
                jiChuBiao.setMoShi("vela_jz");
                break;
        }

        for (int i = 0; i < schemaTableList.size(); i++) {
            SchemaTable table = schemaTableList.get(i);
            String tableName=table.getMoShi() + "." + table.getBiaoMing();
            aliasMap.put(tableName,new Triple<>("t" + i,false,tableName.equals(StringUtil.concat(jiChuBiao.getMoShi(),".",jiChuBiao.getBiaoMing()))) );
        }

        if (ObjectUtils.isNotEmpty(jiChuBiao) && !StringUtils.isBlank(jiChuBiao.getBiaoMing())&& !aliasMap.containsKey(jiChuBiao.getMoShi() + "." + jiChuBiao.getBiaoMing())) {
            aliasMap.put(jiChuBiao.getMoShi() + "." + jiChuBiao.getBiaoMing(),new Triple<>("t" + aliasMap.size(),false,true));
        }
        //输出所需表
        //数据元表
        List<SchemaTable> shuChuBiaoList= MapUtils.copyListProperties(fangAnSC.getQueryFields(), SchemaTable::new);
        //TODO 药品，检查，检验

        //获取外连表
        int index=aliasMap.size();
        List<SchemaTable> waiLianBiaoList= shuChuBiaoList.stream().filter(p->!aliasMap.containsKey(p.getMoShi() + "." + p.getBiaoMing())).toList();
        for(SchemaTable item :waiLianBiaoList)
        {
            aliasMap.put(item.getMoShi() + "." + item.getBiaoMing(),new Triple<>("t" + index,true,false));
            index++;
        }

        return aliasMap;
    }

    /**
     * 拼接表名
     *
     * @param aliasMap 表别名
     * @return 表
     */
    private String getTable(Map<String,Triple<String,Boolean,Boolean>> aliasMap) {
        List<String> tableList = ListUtil.toList();
        aliasMap.forEach((k, v) -> tableList.add(k + " " + v.getFirst()));

        return " " + CharSequenceUtil.join(",", tableList);
    }

    /**
     * 拼接表关联关系
     *
     * @param tableList 表
     * @param aliasMap  表别名
     * @param fangAnLXDM  方案类型代码
     * @return 表关系sql
     */
    private String getTableJoinRelation(List<TableDTO> tableList, Map<String,Triple<String,Boolean,Boolean>> aliasMap, String fangAnLXDM) {
        List<TableRelationCondition> tableRelationConditionList = ListUtil.toList();
        tableList.forEach(e -> {
            if (CollUtil.isNotEmpty(e.getTableRelationConditionList())) {
                tableRelationConditionList.addAll(e.getTableRelationConditionList());
            }
        });
        //获取基础表
        Triple<String,Boolean,Boolean> jiChuTable=aliasMap.values().stream().filter(Triple::getThird).findFirst().orElse(null);

        Map<String,Boolean> neiLianTableMap=new HashMap<>();
        StringBuilder builder = new StringBuilder();
        if (CollUtil.isEmpty(tableRelationConditionList)) {
            tableRelationConditionList.forEach(e -> {
                String key = e.getMoShi() + "." + e.getBiaoMing();
                String alias = aliasMap.get(key).getFirst();
                builder.append(alias).append(".").append(e.getZiduan());
                builder.append("=");
                neiLianTableMap.put(key,true);
                key = e.getGlMoShi() + "." + e.getGlBiaoMing();
                alias = aliasMap.get(key).getFirst();
                builder.append(alias).append(".").append(e.getGlZiDuan());
                builder.append(" AND ");
                neiLianTableMap.put(key,false);
            });
        }
        if(Objects.isNull(jiChuTable)){
            return CharSequenceUtil.replaceLast(builder.toString(), " AND ", " ");
        }
        Map<String,String> jiChuBGX=new HashMap<>(); //基础表关系 first:基础表字段 second:关联表字段，默认同种方案类型于基础表的关联关系一致
        switch (fangAnLXDM) {
            case "0":
                jiChuBGX.put("jiuzhenywid","jiuzhenywid");
                jiChuBGX.put("jiuzhenywlx","jiuzhenywlx");
                jiChuBGX.put("zuzhijgid","zuzhijgid");
                break;
            case "1":
                jiChuBGX.put("id","jiuzhenid");
                jiChuBGX.put("zuzhijgid","zuzhijgid");
                break;
            case "3":
                jiChuBGX.put("id","zhuyuanjzid");
                jiChuBGX.put("zuzhijgid","zuzhijgid");
                break;
            default:
                break;
        }

        aliasMap.forEach((k,v)->{
            if (!Objects.isNull(neiLianTableMap.get(k)) && !neiLianTableMap.get(k)) {
                return;
            }
            //外连不考虑
            if (v.getSecond()) {
                return;
            }
            if(v.getFirst().equals(jiChuTable.getFirst()))
            {
                return;
            }
            jiChuBGX.forEach((k1,v1)->{
                builder.append(jiChuTable.getFirst()).append(".").append(k1);
                builder.append("=");
                builder.append(v.getFirst()).append(".").append(v1);
                builder.append(" AND ");
            });
        });

        return CharSequenceUtil.replaceLast(builder.toString(), " AND ", " ");
    }

    /**
     * 拼接过滤条件
     *
     * @param tableList 表
     * @param aliasMap  表别名
     * @return 过滤条件sql
     */
    private String getFilterCondition(List<TableDTO> tableList, Map<String,Triple<String,Boolean,Boolean>> aliasMap) {
        List<SqlFilterCondition> filterConditionList = ListUtil.toList();
        tableList.forEach(e -> {
            if (CollUtil.isNotEmpty(e.getFilterConditionList())) {
                filterConditionList.addAll(e.getFilterConditionList());
            }
        });

        String defaultCondition = getDefaultCondition(aliasMap);

        if (CollUtil.isEmpty(filterConditionList)) {
            return defaultCondition;
        }

        StringBuilder builder = new StringBuilder();
        filterConditionList.forEach(e -> {
            String key = e.getMoShi() + "." + e.getBiaoMing();
            String alias = aliasMap.get(key).getFirst();
            builder.append(alias).append(".").append(e.getZiDuanBM());
            builder.append(e.getOperator());
            builder.append(e.getVal());
            builder.append(" AND ");
        });

        return builder.append(defaultCondition).toString();
    }

    /**
     * 拼接默认条件
     *
     * @param aliasMap 表别名
     * @return 默认条件
     */
    private String getDefaultCondition(Map<String,Triple<String,Boolean,Boolean>> aliasMap) {
        String tenantId = tenantIdentityService.getCurrentTenant().TenantId();

        StringBuilder builder = new StringBuilder();
        aliasMap.values().forEach(e -> {
            builder.append(e.getFirst()).append(".zuofeibz=0");
            builder.append(" AND ");
            builder.append(e.getFirst()).append(".zuhuid='").append(tenantId).append("'");
            builder.append(" AND ");
        });
        return CharSequenceUtil.replaceLast(builder.toString(), " AND ", " ");
    }


    /**
     * 数据元输出字段
     *
     * @param fangAnSC 方案输出项
     * @param aliasMap 表别名
     * @return 输出字段
     */
    private String getQueryFields(FangAnSCDTO fangAnSC, Map<String,Triple<String,Boolean,Boolean>> aliasMap) {
        List<QueryField> queryFields = fangAnSC.getQueryFields();
        if (CollUtil.isEmpty(queryFields)) {
            return "";
        }

        List<String> fields = ListUtil.toList();
        //增加基础表默认输出字段
        Triple<String,Boolean,Boolean> jiChuTable=aliasMap.values().stream().filter(Triple::getThird).findFirst().orElse(null);
        if(!Objects.isNull(jiChuTable)){
            fields.add(jiChuTable.getFirst() + ".id");
            fields.add(jiChuTable.getFirst() + ".bingrenid");
        }

        for (QueryField e : queryFields) {
            String key = e.getMoShi() + "." + e.getBiaoMing();
            String alias = aliasMap.containsKey(key) ? aliasMap.get(key).getFirst() : "";
            fields.add(alias + "." + e.getZiDuanBM());
        }

        return " " + CharSequenceUtil.join(",", fields);
    }

    /**
     * 拼接单引号
     *
     * @param list 值
     * @return "'"+val+"'"
     */
    private List<String> spliceSingleQuotes(List<String> list) {
        List<String> vals = ListUtil.toList();
        list.forEach(e -> vals.add("'" + e + "'"));
        return vals;
    }
}

package cn.mediinfo.grus.shujuzx.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.StopWatch;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.util.JacksonUtil;
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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
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


    @Override
    public String saveFangAn(FangAnXXSaveRequest request) throws YuanChengException {
        StopWatch watch = new StopWatch();
        watch.start("AST树转换成sql");
        String sql = getSql(request.getRoot(), request.getFangAnSCList());
        log.info("sql expr sql:{}", sql);
        watch.stop();
        log.info(watch.prettyPrint(TimeUnit.MILLISECONDS));
        return fangAnManager.saveFangAn(request, sql);
    }

    @Override
    public String getSql(FangAnTreeNode root, List<FangAnSC> fangAnSCList) throws YuanChengException {

        List<FangAnCondition> conditionList = ListUtil.toList();
        FangAnTreeUtils.getConditionList(root, conditionList);
        //todo 校验条件

        //子方案
        subFangAn(conditionList);

        Set<String> shiTuMXIds = conditionList.stream().map(FangAnCondition::getShiTuMXID).collect(Collectors.toSet());
        //from
        List<TableDTO> tableList = shiTuMXService.listTable(shiTuMXIds);

        //生成别名 key schema.table value alias
        Map<String, String> aliasMap = getAliasMap(tableList);
        //拼接表关系
        String table = getTable(aliasMap);
        //拼接表关联关系
        String joinRelation = getTableJoinRelation(tableList, aliasMap);
        // 过滤条件
        String filterCondition = getFilterCondition(tableList, aliasMap);

        //输出字段
        FangAnSCDTO fangAnSC = fangAnSCService.getAllFangAnSC(fangAnSCList);
        String fields = getQueryFields(fangAnSC, aliasMap);

        //where
        conditionList.forEach(e ->{
            if(CollUtil.isEmpty(e.getRelatedFieldConditions())){
                return;
            }
            e.getRelatedFieldConditions().forEach(s -> shiTuMXIds.add(s.getShiTuMXGXID()));
        });
        List<FieldDTO> fieldList = shiTuMXService.listFields(shiTuMXIds);
        fieldList.forEach(e -> e.setAlias(aliasMap.get(e.getMoShi() + "." + e.getBiaoMing())));
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

        sqlNode.setLeft(transform(node.getLeft(), fieldMap));
        sqlNode.setRight(transform(node.getRight(), fieldMap));

        return sqlNode;
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
            sqlOperator = FangAnOperator.CONTAIN.getType().equalsIgnoreCase(operator) ? SQLBinaryOperator.IN : SQLBinaryOperator.NOTIN;
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
     * @return Map
     */
    private Map<String, String> getAliasMap(List<TableDTO> tableList) {
        Map<String, String> aliasMap = MapUtil.newHashMap(tableList.size());
        List<SchemaTable> schemaTableList = ListUtil.toList();
        tableList.forEach(e -> schemaTableList.addAll(e.getSchemaTableList()));
        for (int i = 0; i < schemaTableList.size(); i++) {
            SchemaTable table = schemaTableList.get(i);
            aliasMap.put(table.getMoShi() + "." + table.getBiaoMing(), "t" + i);
        }
        return aliasMap;
    }

    /**
     * 拼接表名
     *
     * @param aliasMap 表别名
     * @return 表
     */
    private String getTable(Map<String, String> aliasMap) {
        List<String> tableList = ListUtil.toList();
        aliasMap.forEach((k, v) -> tableList.add(k + " " + v));

        return " " + CharSequenceUtil.join(",", tableList);
    }

    /**
     * 拼接表关联关系
     *
     * @param tableList 表
     * @param aliasMap  表别名
     * @return 表关系sql
     */
    private String getTableJoinRelation(List<TableDTO> tableList, Map<String, String> aliasMap) {
        List<TableRelationCondition> tableRelationConditionList = ListUtil.toList();
        tableList.forEach(e -> {
            if (CollUtil.isNotEmpty(e.getTableRelationConditionList())) {
                tableRelationConditionList.addAll(e.getTableRelationConditionList());
            }
        });
        if (CollUtil.isEmpty(tableRelationConditionList)) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        tableRelationConditionList.forEach(e -> {
            String key = e.getMoShi() + "." + e.getBiaoMing();
            String alias = aliasMap.get(key);
            builder.append(alias).append(".").append(e.getZiduan());
            builder.append("=");
            key = e.getGlMoShi() + "." + e.getGlBiaoMing();
            alias = aliasMap.get(key);
            builder.append(alias).append(".").append(e.getGlZiDuan());
            builder.append(" AND ");
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
    private String getFilterCondition(List<TableDTO> tableList, Map<String, String> aliasMap) {
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
            String alias = aliasMap.get(key);
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
    private String getDefaultCondition(Map<String, String> aliasMap) {
        String tenantId = tenantIdentityService.getCurrentTenant().TenantId();

        StringBuilder builder = new StringBuilder();
        aliasMap.values().forEach(e -> {
            builder.append(e).append(".zuofeibz=0");
            builder.append(" AND ");
            builder.append(e).append(".zuhuid='").append(tenantId).append("'");
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
    private String getQueryFields(FangAnSCDTO fangAnSC, Map<String, String> aliasMap) {
        List<QueryField> queryFields = fangAnSC.getQueryFields();
        if (CollUtil.isEmpty(queryFields)) {
            return "";
        }

        List<String> fields = ListUtil.toList();
        queryFields.forEach(e -> {
            String key = e.getMoShi() + "." + e.getBiaoMing();
            String alias = aliasMap.get(key);
            fields.add(alias + "." + e.getZiDuanBM());
        });

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

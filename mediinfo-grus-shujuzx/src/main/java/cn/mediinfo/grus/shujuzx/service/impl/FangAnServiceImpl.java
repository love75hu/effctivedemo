package cn.mediinfo.grus.shujuzx.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.StopWatch;
import cn.hutool.core.text.CharSequenceUtil;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.util.JacksonUtil;
import cn.mediinfo.cyan.msf.core.util.Tuple;
import cn.mediinfo.cyan.msf.tenant.security.TenantIdentityService;
import cn.mediinfo.grus.shujuzx.bo.RelatedFangAnBO;
import cn.mediinfo.grus.shujuzx.common.fangan.condition.*;
import cn.mediinfo.grus.shujuzx.dto.fangannr.FangAnSqlDTO;
import cn.mediinfo.grus.shujuzx.dto.shitumx.FieldDTO;
import cn.mediinfo.grus.shujuzx.dto.shitumx.SchemaTable;
import cn.mediinfo.grus.shujuzx.dto.shitumx.ShuJuJMXZDDto;
import cn.mediinfo.grus.shujuzx.dto.shitumx.TableDTO;
import cn.mediinfo.grus.shujuzx.enums.FangAnOperator;
import cn.mediinfo.grus.shujuzx.enums.NodeTypeEnum;
import cn.mediinfo.grus.shujuzx.enums.ShuJuZLXDMEnum;
import cn.mediinfo.grus.shujuzx.manager.FangAnManager;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.ShuJuXXMSRso;
import cn.mediinfo.grus.shujuzx.remoteservice.GongYongRemoteService;
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
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FangAnServiceImpl implements FangAnService {

    @Resource
    @Qualifier("datasourcesjzx_jdbcTemplateFactory")
    JdbcTemplate jdbcTemplate;
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
    @Autowired
    private GongYongRemoteService gongYongRemoteService;

    /**
     * s
     * 保存方案
     *
     * @param request
     */
    @Override
    public String saveFangAn(FangAnXXSaveRequest request) throws YuanChengException {
        StopWatch watch = new StopWatch();
        watch.start("AST树转换成sql");
        String sql = getSql(request.getRoot(), request.getFangAnSCList(), request.getFangAnLXDM(), 0);
        log.info("sql expr sql:{}", sql);
        watch.stop();
        log.info(watch.prettyPrint(TimeUnit.MILLISECONDS));
        return fangAnManager.saveFangAn(request, sql);
    }

    /**
     * 获取SQL
     *
     * @param root         方案树
     * @param fangAnSCList 方案输出项
     * @param fangAnLXDM   方案类型代码
     * @param type         查询类别 0获取sql 1查询结果
     * @return String
     * @throws YuanChengException
     */
    @Override
    public String getSql(FangAnTreeNode root, List<FangAnSC> fangAnSCList, String fangAnLXDM, Integer type) throws YuanChengException {
        List<FangAnCondition> conditionList = ListUtil.toList();
        FangAnTreeUtils.getConditionList(root, conditionList);
        //todo 校验条件

        //子方案
        subFangAn(conditionList);

        Set<String> shiTuMXIds = conditionList.stream().map(FangAnCondition::getShiTuMXID).collect(Collectors.toSet());
        //from
        List<TableDTO> tableList = shiTuMXService.listTable(shiTuMXIds);
        tableList = (CollUtil.isEmpty(tableList)) ? ListUtil.toList() : tableList;

        //生成别名 key schema.table value alias
        Map<String, Tuple.Tuple4<String, String, Boolean, Boolean>> aliasMap = getAliasMap(tableList, fangAnLXDM);
        //拼接表关系
        String table = getTable(aliasMap);
        //拼接表关联关系
        String joinRelation = getTableJoinRelation(tableList, aliasMap, fangAnLXDM);
        //拼接视图过滤条件
        String filterCondition = getFilterCondition(tableList, aliasMap);
        //拼接输出
        String fields = getQueryFields(fangAnSCList, aliasMap, fangAnLXDM);

        //where
        conditionList.forEach(e -> {
            if (CollUtil.isEmpty(e.getRelatedFieldConditions())) {
                return;
            }
            e.getRelatedFieldConditions().forEach(s -> shiTuMXIds.add(s.getShiTuMXGXID()));
        });
        List<FieldDTO> fieldList = shiTuMXService.listFields(shiTuMXIds);

        fieldList.forEach(e -> e.setAlias(aliasMap.entrySet().stream().filter(p -> p.getKey().contains(formatBiaoMing(e.getMoShi(), e.getBiaoMing()))).map(Map.Entry::getValue).findFirst().orElse(Tuple.of("", "", false, false)).item1()));
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

    public List<Object> getFangAnJG() {
        return null;
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
            if(Objects.isNull(fangAnSql)){
                return;
            }
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

        SQLQueryNode sqlNode = new SQLQueryNode();
        if (condition == null || NodeTypeEnum.RELATION_NODE.getType().equals(node.getNodeType())) {
            sqlNode = new SQLQueryNode(new SQLQueryObject(sqlOperator));
        } else {
            FieldDTO field = fieldMap.get(condition.getShiTuMXID());
            List<String> valList = Optional.ofNullable(condition.getValues()).orElse(new ArrayList<>()).stream().map(FanganConditionValue::getVal).toList();
            SQLQueryObject obj = toSQLQueryObject(condition.getOperator(), field, valList, condition.getRelatedFangAnQueryCondition());
            //子条件
            if (CollUtil.isNotEmpty(condition.getRelatedFieldConditions())) {
                String subSql = getRelatedFieldCondition(condition.getRelatedFieldConditions(), fieldMap);
                obj.setSubSql(subSql);
            }
            sqlNode = new SQLQueryNode(obj);
        }

        if (CollUtil.isEmpty(node.getChildrenConditions())) {
            return sqlNode;
        }

        //子条件列表反转后构建AST树
        var list = node.getChildrenConditions().listIterator(node.getChildrenConditions().size());
        int index = 0;
        SQLQueryNode currentSqlNode = sqlNode;
        while (list.hasPrevious()) {
            var item = list.previous();
            boolean isGuanXiJD = Objects.isNull(item.getCondition());
            if (0 == index) {
                currentSqlNode.setRight(getGuanXiNode(transform(item, fieldMap), isGuanXiJD));
            } else if (index == node.getChildrenConditions().size() - 1) {
                currentSqlNode.setLeft(getGuanXiNode(transform(item, fieldMap), isGuanXiJD));
            } else {
                SQLQueryNode childrenSqlNode = new SQLQueryNode(new SQLQueryObject(sqlOperator));
                childrenSqlNode.setRight(transform(item, fieldMap));
                currentSqlNode.setLeft(getGuanXiNode(childrenSqlNode, isGuanXiJD));
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
     * @param isGuanXiJD   是否关系节点
     * @return SQLQueryNode
     */
    private SQLQueryNode getGuanXiNode(SQLQueryNode childrenNode, boolean isGuanXiJD) {
        if (!isGuanXiJD) {
            return childrenNode;
        }
        SQLQueryNode guanXiNode = new SQLQueryNode(new SQLQueryObject("()"));
        guanXiNode.setLeft(childrenNode);
        return guanXiNode;
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
            sqlOperator = SQLBinaryOperator.getSQLBinaryOperator(operator);
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
            } else if (ShuJuZLXDMEnum.DATE.getType().equals(field.getShuJuZLXDM())) {
                val = "date'" + valList.get(0) + "'";
            } else {
                val = "'" + valList.get(0) + "'";
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
            SQLQueryObject obj = toSQLQueryObject(e.getOperator(), field, valList, e.getRelatedFangAnQueryCondition());
            builder.append(obj.getText());
            builder.append(" AND ");
        });

        return builder.toString();
    }

    /**
     * 获取表别名
     *
     * @param tableList  表
     * @param fangAnLXDM 方案类型代码
     * @return Map
     */
    private Map<String, Tuple.Tuple4<String, String, Boolean, Boolean>> getAliasMap(List<TableDTO> tableList, String fangAnLXDM) throws YuanChengException {
        //Tuple4中T1：别名，T2: 表关联，T3:是否外连表，T4:是否基础表,LinkedHashMap有序,CaseInsensitiveMap不区分大小写
        Map<String, Tuple.Tuple4<String, String, Boolean, Boolean>> aliasMap = new LinkedHashMap<>();
        //基础表
        SchemaTable jiChuBiao = new SchemaTable();
        List<ShuJuXXMSRso> jiChuBiaoXXList = gongYongRemoteService.getShuJXXMS(ListUtil.toList("JZ_MZ_JIUZHENXX", "JZ_ZY_JIUZHENXX")).getData("获取模式信息失败");

        switch (fangAnLXDM) {
            case "1":
                ShuJuXXMSRso menZhen = jiChuBiaoXXList.stream().filter(p -> p.getBiaoMing().equals("JZ_MZ_JIUZHENXX")).findFirst().orElse(new ShuJuXXMSRso());
                jiChuBiao.setBiaoMing(Optional.ofNullable(menZhen.getBiaoMing()).orElse("").toLowerCase());
                jiChuBiao.setMoShi(Optional.ofNullable(menZhen.getShuJuYMC()).orElse("").toLowerCase());
                break;
            case "3":
                ShuJuXXMSRso zhuYuan = jiChuBiaoXXList.stream().filter(p -> p.getBiaoMing().equals("JZ_ZY_JIUZHENXX")).findFirst().orElse(new ShuJuXXMSRso());
                jiChuBiao.setBiaoMing(Optional.ofNullable(zhuYuan.getBiaoMing()).orElse("").toLowerCase());
                jiChuBiao.setMoShi(Optional.ofNullable(zhuYuan.getShuJuYMC()).orElse("").toLowerCase());
                break;
        }

        //根据条件中涉及的表或视图设置表别名
        for (int i = 0; i < tableList.size(); i++) {
            TableDTO table = tableList.get(i);
            if (CollUtil.isEmpty(table.getSchemaTableList())) {
                continue;
            }
            String tableName = StringUtils.join(table.getSchemaTableList().stream().map(p -> formatBiaoMing(p.getMoShi(), p.getBiaoMing())).collect(Collectors.toSet()), ",");
            String shiTuBGX = getShiTuBGX(table, tableName);
            aliasMap.put(tableName, Tuple.of("t_" + i, shiTuBGX, false, tableName.contains(formatBiaoMing(jiChuBiao.getMoShi(), jiChuBiao.getBiaoMing()))));
        }

        if (ObjectUtils.isNotEmpty(jiChuBiao) && !StringUtils.isBlank(jiChuBiao.getBiaoMing()) && !aliasMap.containsKey(formatBiaoMing(jiChuBiao.getMoShi(), jiChuBiao.getBiaoMing()))) {
            aliasMap.put(formatBiaoMing(jiChuBiao.getMoShi(), jiChuBiao.getBiaoMing()), Tuple.of("t_" + aliasMap.size(), formatBiaoMing(jiChuBiao.getMoShi(), jiChuBiao.getBiaoMing()), false, true));
        }

        return aliasMap;
    }

    /**
     * 拼接表名
     *
     * @param aliasMap 表别名
     * @return 表
     */
    private String getTable(Map<String, Tuple.Tuple4<String, String, Boolean, Boolean>> aliasMap) {
        List<String> tableNameList = ListUtil.toList();
        aliasMap.forEach((k, v) -> tableNameList.add(v.item2() + " " + v.item1()));

        return " " + CharSequenceUtil.join(",", tableNameList);
    }

    /**
     * 拼接表关联关系
     *
     * @param tableList  表
     * @param aliasMap   表别名
     * @param fangAnLXDM 方案类型代码
     * @return 表关系sql
     */
    private String getTableJoinRelation(List<TableDTO> tableList, Map<String, Tuple.Tuple4<String, String, Boolean, Boolean>> aliasMap, String fangAnLXDM) {
        //获取基础表
        Tuple.Tuple4<String, String, Boolean, Boolean> jiChuTable = aliasMap.values().stream().filter(Tuple.Tuple4::item4).findFirst().orElse(null);
        //获取视图中表关系
        StringBuilder builder = new StringBuilder();

        if (Objects.isNull(jiChuTable)) {
            return CharSequenceUtil.replaceLast(builder.toString(), " AND ", " ");
        }
        Map<String, String> jiChuBGX = new HashMap<>(); //基础表关系 first:基础表字段 second:关联表字段，默认同种方案类型于基础表的关联关系一致
        switch (fangAnLXDM) {
            case "0":
                jiChuBGX.put("jiuzhenywid", "jiuzhenywid");
                jiChuBGX.put("jiuzhenywlx", "jiuzhenywlx");
                jiChuBGX.put("zuzhijgid", "zuzhijgid");
                break;
            case "1":
                jiChuBGX.put("id", "jiuzhenid");
                jiChuBGX.put("zuzhijgid", "zuzhijgid");
                break;
            case "3":
                jiChuBGX.put("id", "zhuyuanjzid");
                jiChuBGX.put("zuzhijgid", "zuzhijgid");
                break;
            default:
                break;
        }

        aliasMap.forEach((k, v) -> {
            //外连暂不考虑
            if (v.item3()) {
                return;
            }
            if (v.item1().equals(jiChuTable.item1())) {
                return;
            }
            jiChuBGX.forEach((k1, v1) -> {
                builder.append(jiChuTable.item1()).append(".").append(k1);
                builder.append("=");
                builder.append(v.item1()).append(".").append(v1);
                builder.append(" AND ");
            });
        });

        return CharSequenceUtil.replaceLast(builder.toString(), " AND ", " ");
    }

    /**
     * 拼接视图过滤条件
     *
     * @param tableList 表
     * @param aliasMap  表别名
     * @return 过滤条件sql
     */
    private String getFilterCondition(List<TableDTO> tableList, Map<String, Tuple.Tuple4<String, String, Boolean, Boolean>> aliasMap) {
        //拼接过滤条件
        StringBuilder builder = new StringBuilder();
        tableList.forEach(e -> {
            if (CollUtil.isEmpty(e.getSchemaTableList()) || e.getSchemaTableList().size() > 1) {
                return;
            }
            String filterCondition = e.getFilterConditionList();
            for (SchemaTable f : e.getSchemaTableList()) {
                String key = formatBiaoMing(f.getMoShi(), f.getBiaoMing());
                if (Objects.isNull(filterCondition)) {
                    return;
                }
                String alias = aliasMap.get(key).item1();
                filterCondition = filterCondition.replaceAll("(?i)" + key, alias);
            }
            builder.append("(");
            builder.append(filterCondition);
            builder.append(")");
            builder.append(" AND ");
        });
        //拼接默认过滤条件
        String defaultCondition = getDefaultCondition(aliasMap);
        if (builder.isEmpty()) {
            return defaultCondition;
        }
        return builder.append(defaultCondition).toString();
    }

    /**
     * 拼接默认条件
     *
     * @param aliasMap 表别名
     * @return 默认条件
     */
    private String getDefaultCondition(Map<String, Tuple.Tuple4<String, String, Boolean, Boolean>> aliasMap) {
        String tenantId = tenantIdentityService.getCurrentTenant().TenantId();

        StringBuilder builder = new StringBuilder();
        aliasMap.values().forEach(e -> {
            if (e.item3()) { //外连表不拼接默认条件
                return;
            }
            builder.append(e.item1()).append(".zuofeibz=0");
            builder.append(" AND ");
            builder.append(e.item1()).append(".zuhuid='").append(tenantId).append("'");
            builder.append(" AND ");
        });
        return CharSequenceUtil.replaceLast(builder.toString(), " AND ", " ");
    }


    /**
     * 数据元输出字段
     *
     * @param fangAnSCList 方案输出详情
     * @param aliasMap     表别名
     * @param fangAnLXDM   方案类型代码
     * @return 输出字段
     */
    private String getQueryFields(List<FangAnSC> fangAnSCList, Map<String, Tuple.Tuple4<String, String, Boolean, Boolean>> aliasMap, String fangAnLXDM) {
        List<String> fields = ListUtil.toList();
        //增加基础表默认输出字段
        Tuple.Tuple4<String, String, Boolean, Boolean> jiChuTable = aliasMap.values().stream().filter(Tuple.Tuple4::item4).findFirst().orElse(null);
        if (!Objects.isNull(jiChuTable)) {
            fields.add(jiChuTable.item1() + ".id");
            fields.add(jiChuTable.item1() + ".bingrenid");
        }
        //数据元
        for (FangAnSC e : Optional.ofNullable(fangAnSCList).orElse(new ArrayList<>())) {
            String key = aliasMap.keySet().stream().filter(p -> p.contains(formatBiaoMing(e.getMoShi(), e.getBiaoMing()))).findFirst().orElse("");
            String alias = aliasMap.containsKey(key) ? aliasMap.get(key).item1() : "";
            switch (e.getZhiBiaoLXDM()) {
                case "2": //检验
                    key = aliasMap.keySet().stream().filter(p -> p.contains("jy_bg_baogaomx")).findFirst().orElse("");
                    alias = aliasMap.containsKey(key) ? aliasMap.get(key).item1() : "";
                    fields.add(MessageFormat.format("(case when {0}.shiyanxmdm=''{1}'' then concat({0}.shiyanjg,{0}.danwei) else '''' end) as {2}", alias, e.getZhiBiaoID(), "ZB_" + fangAnSCList.indexOf(e)));
                    break;
                case "3": //检查
                    key = "vela_jc.jc_bg_baogaoxx";
                    key = aliasMap.keySet().stream().filter(p -> p.contains("jc_bg_baogaoxx")).findFirst().orElse("");
                    String shenQingBWKey = aliasMap.keySet().stream().filter(p -> p.contains("jc_sq_shenqingdbw")).findFirst().orElse("");
                    alias = aliasMap.containsKey(key) ? aliasMap.get(key).item1() : "";
                    String shenQingBWAlias = aliasMap.containsKey(shenQingBWKey) ? aliasMap.get(shenQingBWKey).item1() : "";
                    fields.add(MessageFormat.format("(case when {0}.jianchabwid=''{1}'' then {2}.zhenduanjg else '''' end) as {3}", shenQingBWAlias, e.getZhiBiaoID(), alias, "ZB_" + fangAnSCList.indexOf(e)));
                    break;
                case "4": //药品
                    switch (fangAnLXDM) {
                        case "1": //门诊
                            key = aliasMap.keySet().stream().filter(p -> p.contains("yz_mz_yizhuxx")).findFirst().orElse("");
                            alias = aliasMap.containsKey(key) ? aliasMap.get(key).item1() : "";
                            fields.add(MessageFormat.format("(case when {0}.guigeid=''{1}'' then concat({0}.yicijl,{0}.yicijldw) else '''' end) as {2}", alias, e.getZhiBiaoID(), "ZB_" + fangAnSCList.indexOf(e)));
                            break;
                        case "3": //住院
                            key = aliasMap.keySet().stream().filter(p -> p.contains("yz_zy_yizhuxx")).findFirst().orElse("");
                            alias = aliasMap.containsKey(key) ? aliasMap.get(key).item1() : "";
                            fields.add(MessageFormat.format("(case when {0}.guigeid=''{1}'' then concat({0}.yicijl,{0}.yicijldw) else '''' end) as {2}", alias, e.getZhiBiaoID(), "ZB_" + fangAnSCList.indexOf(e)));
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    fields.add(alias + "." + e.getZhiBiaoID());
                    break;
            }
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

    /**
     * 格式化表名
     * @param moShi
     * @param biaoMing
     * @return
     */
    private String formatBiaoMing(String moShi, String biaoMing) {
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isNotBlank(moShi)) {
            builder.append(moShi + ".");
        }
        builder.append(biaoMing);
        return builder.toString().toLowerCase();
    }

    /**
     * 获取视图表关系
     *
     * @param table     表信息
     * @param tableName 表组合名
     * @return
     */
    private String getShiTuBGX(TableDTO table, String tableName) {
        StringBuilder builder = new StringBuilder();
        if (table.getSchemaTableList().size() <= 1) {
            builder.append(tableName);
            return builder.toString();
        }
        builder.append("(select ");
        //字段名列表
        List<String> ziDuanMingList = new ArrayList<>();
        //表名列表
        Set<String> biaoMingList = new HashSet<>();
        //获取视图中表关系
        String relationCondition = Optional.ofNullable(table.getTableRelationConditionList()).orElse("");
        //获取视图中过滤条件
        String filterCondition = Optional.ofNullable(table.getFilterConditionList()).orElse("");
        int index = 0;
        for (SchemaTable f : table.getSchemaTableList()) {
            String key = formatBiaoMing(f.getMoShi(), f.getBiaoMing());
            for (ShuJuJMXZDDto p : Optional.ofNullable(f.getShuJuJMXZDDtos()).orElse(new ArrayList<>())) {
                ziDuanMingList.add("l" + index + "." + p.getZiDuanBM());
            }
            biaoMingList.add(key + " l" + index);
            relationCondition = relationCondition.replaceAll("(?i)" + key, "l" + index);
            filterCondition = filterCondition.replaceAll("(?i)" + key, "l" + index);
            index++;
        }
        builder.append(CollUtil.join(ziDuanMingList, ","));
        builder.append(" from ");
        builder.append(CollUtil.join(biaoMingList, ","));
        builder.append(" where 1=1 ");
        if (StringUtils.isNotBlank(relationCondition)) {
            builder.append(" AND ");
            builder.append(relationCondition);
        }
        if (StringUtils.isNotBlank(filterCondition)) {
            builder.append(" AND ");
            builder.append(filterCondition);
        }
        builder.append(")");
        return builder.toString();
    }
}

package cn.mediinfo.grus.shujuzx.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.StopWatch;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.util.*;
import cn.mediinfo.cyan.msf.security.IdentityService;
import cn.mediinfo.grus.shujuzx.bo.RelatedFangAnBO;
import cn.mediinfo.grus.shujuzx.common.fangan.condition.FangAnCondition;
import cn.mediinfo.grus.shujuzx.common.fangan.condition.FangAnTreeNode;
import cn.mediinfo.grus.shujuzx.common.fangan.condition.RelatedFangAnQueryCondition;
import cn.mediinfo.grus.shujuzx.common.fangan.condition.RelatedFieldCondition;
import cn.mediinfo.grus.shujuzx.config.QuanWenJSConfig;
import cn.mediinfo.grus.shujuzx.dto.bihuanlcs.BingLiXQDto;
import cn.mediinfo.grus.shujuzx.dto.bihuanlcs.BingLiXQXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuanlcs.JiuluTextDto;
import cn.mediinfo.grus.shujuzx.dto.fangan.BingLiSCDTO;
import cn.mediinfo.grus.shujuzx.dto.fangan.FangAnByFACXLSDTO;
import cn.mediinfo.grus.shujuzx.dto.fangan.FangAnQueryDTO;
import cn.mediinfo.grus.shujuzx.dto.fangan.FangAnSCDTO;
import cn.mediinfo.grus.shujuzx.dto.fangancxls.FangAnCXLSDTO;
import cn.mediinfo.grus.shujuzx.dto.fangannr.FangAnSqlDTO;
import cn.mediinfo.grus.shujuzx.dto.result.*;
import cn.mediinfo.grus.shujuzx.dto.shitumx.*;
import cn.mediinfo.grus.shujuzx.dto.shujuzxsts.SC_SC_ShouCangJMXListDto;
import cn.mediinfo.grus.shujuzx.enums.FangAnOperator;
import cn.mediinfo.grus.shujuzx.enums.NodeTypeEnum;
import cn.mediinfo.grus.shujuzx.enums.ShuJuZLXDMEnum;
import cn.mediinfo.grus.shujuzx.manager.FangAnManager;
import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnSCModel;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.ShuJuXXMSRso;
import cn.mediinfo.grus.shujuzx.remotedto.JiuZhenXXs.JiuZhenIDYWLXIDRso;
import cn.mediinfo.grus.shujuzx.remotedto.JiuZhenXXs.ZhuYuanMZJZXXRso;
import cn.mediinfo.grus.shujuzx.remotedto.linchuang.ChaXunDto;
import cn.mediinfo.grus.shujuzx.remotedto.linchuang.JiuluTextRso;
import cn.mediinfo.grus.shujuzx.remoteservice.GongYongRemoteService;
import cn.mediinfo.grus.shujuzx.remoteservice.JiuZhenRemoteService;
import cn.mediinfo.grus.shujuzx.remoteservice.LinChuangRemoteService;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_FangAnSCRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_SC_ShouCangJMXRepository;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnSC;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnXXSaveRequest;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnXXUpdateRequest;
import cn.mediinfo.grus.shujuzx.service.*;
import cn.mediinfo.grus.shujuzx.sql.ast.SQLQueryExpr;
import cn.mediinfo.grus.shujuzx.sql.ast.SQLQueryNode;
import cn.mediinfo.grus.shujuzx.sql.ast.SQLQueryObject;
import cn.mediinfo.grus.shujuzx.sql.enums.SQLBinaryOperator;
import cn.mediinfo.grus.shujuzx.util.FangAnTreeUtils;
import cn.mediinfo.grus.shujuzx.util.SqlUtils;
import cn.mediinfo.grus.shujuzx.utils.ExportUtils;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class FangAnServiceImpl implements FangAnService {

    @Resource
    @Autowired
    private ShiTuMXService shiTuMXService;
    @Autowired
    private FangAnManager fangAnManager;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private FangAnNRService fangAnNRService;
    @Autowired
    private GongYongRemoteService gongYongRemoteService;
    @Autowired
    private ChaXunFAXXService chaXunFAXXService;

    @Autowired
    private JiuZhenRemoteService jiuZhenRemoteService;

    @Autowired
    private LinChuangRemoteService linChuangRemoteService;

    @Autowired
    public SC_SC_ShouCangJMXRepository scScShouCangJMXRepository;

    @Autowired
    private LyraIdentityService lyraIdentityService;

    @Autowired
    private QuanWenJSConfig quanWenJSConfig;

    @Autowired
    private SC_CX_FangAnSCRepository fangAnSCRepository;

    /**
     * 保存方案
     *
     * @param request
     */
    @Override
    public String saveFangAn(FangAnXXSaveRequest request) throws YuanChengException, TongYongYWException {
        StopWatch watch = new StopWatch();
        watch.start("AST树转换成sql");
        String sql = getSql(request.getRoot(), request.getFangAnSCList(), request.getFangAnLXDM(), request.getGuanJianZi());
        log.info("sql expr sql:{}", sql);
        watch.stop();
        log.info(watch.prettyPrint(TimeUnit.MILLISECONDS));
        return fangAnManager.saveFangAn(request, sql);
    }

    /**
     * 根据方案ID获取方案信息
     *
     * @param fangAnID 方案ID
     * @return FangAnQueryDTO
     * @throws TongYongYWException 异常
     */
    @Override
    public FangAnQueryDTO getFangAnXX(String fangAnID) throws TongYongYWException, WeiZhaoDSJException {
        return fangAnManager.getFangAnXXByFAID(fangAnID);
    }

    /**
     * 更新方案
     *
     * @param request 入参
     * @return Boolean
     * @throws YuanChengException 异常
     * @throws TongYongYWException 异常
     */
    @Override
    public Boolean updateFangAnXX(FangAnXXUpdateRequest request) throws YuanChengException, TongYongYWException {
        StopWatch watch = new StopWatch();
        watch.start("AST树转换成sql");
        String sql = getSql(request.getRoot(), request.getFangAnSCList(), request.getFangAnLXDM(), request.getGuanJianZi());
        log.info("sql expr sql:{}", sql);
        watch.stop();
        log.info(watch.prettyPrint(TimeUnit.MILLISECONDS));
        return fangAnManager.updateFangAnXX(request, sql);
    }

    /**
     * 根据方案查询历史获取方案信息
     *
     * @param fangAnCXLSId 方案查询历史id
     * @return FangAnByFACXLSDTO
     * @throws TongYongYWException
     */
    @Override
    public FangAnByFACXLSDTO getFangAnCXLS(String fangAnCXLSId) throws TongYongYWException, WeiZhaoDSJException {
        FangAnByFACXLSDTO result = new FangAnByFACXLSDTO();
        FangAnCXLSDTO fangAnCXLS = chaXunFAXXService.getFangAnCXLSByID(fangAnCXLSId);
        if (ObjectUtils.isEmpty(fangAnCXLS)) {
            throw new TongYongYWException("查询方案历史不存在");
        }
        if (StringUtils.isBlank(fangAnCXLS.getFangAnID())) {
            BeanUtil.copyProperties(fangAnCXLS, result);
            result.setId("");
            result.setRoot(JsonUtil.getJsonToBean(fangAnCXLS.getChaXunTJ(),FangAnTreeNode.class));
            result.setFangAnSCList(JsonUtil.getJsonToList(fangAnCXLS.getChaXunSC(),FangAnSCDTO.class));
            return result;
        }
        FangAnQueryDTO fangAnXX = fangAnManager.getFangAnXXByFAID(fangAnCXLS.getFangAnID());
        if (ObjectUtils.isNotEmpty(fangAnXX)) {
            BeanUtil.copyProperties(fangAnXX, result);
        }
        return result;
    }

    /**
     * 获取SQL
     *
     * @param root         方案树
     * @param fangAnSCList 方案输出项
     * @param fangAnLXDM   方案类型代码
     * @param guanJianZi   关键字
     * @return String
     * @throws YuanChengException 异常
     */
    @Override
    public String getSql(FangAnTreeNode root, List<FangAnSC> fangAnSCList, String fangAnLXDM, String guanJianZi) throws YuanChengException, TongYongYWException {
        if (StringUtils.isBlank(fangAnLXDM)) {
            return "";
        }
        if (Objects.isNull(root) && CollUtil.isEmpty(fangAnSCList)) {
            return "";
        }
        List<FangAnCondition> conditionList = ListUtil.toList();
        FangAnTreeUtils.getConditionList(root, conditionList);
        if(ObjectUtils.isEmpty(conditionList)||conditionList.stream().noneMatch(Objects::nonNull)){
            return "";
        }
        //子方案
        subFangAn(conditionList);

        List<String> shiTuIds = conditionList.stream().map(FangAnCondition::getShiTuID).distinct().collect(Collectors.toList());
        if(shiTuIds.stream().anyMatch(StringUtil::isBlank)){
            throw new TongYongYWException("视图ID不能为空");
        }
        shiTuIds.addAll(fangAnSCList.stream().filter(p -> "1".equals(p.getZhiBiaoLXDM())).map(FangAnSC::getZhiBiaoFLID).toList());
        //from
        ShuJuXSTXQDto shuJuXSTXQ = Optional.ofNullable(shiTuMXService.getShuJuSTXQDto(shiTuIds)).orElse(new ShuJuXSTXQDto());
        List<TableDTO> tableList = Optional.ofNullable(shuJuXSTXQ.getTableDTO()).orElse(ListUtil.toList());

        //基础表信息
        List<ShuJuXXMSRso> jiChuBiaoXXList = gongYongRemoteService.getShuJXXMS(ListUtil.toList("JZ_MZ_JIUZHENXX", "JZ_ZY_JIUZHENXX", "BL_MZ_BINGLIJLTEXT", "BL_WS_JILUTEXT", "BR_DA_JIBENXX")).getData("获取基础信息表模式信息失败");

        //生成别名 T1：别名，T2: 表关联，T3:是否外连表，T4:是否基础表
        Map<String, Tuple.Tuple4<String, String, Boolean, Boolean>> aliasMap = getAliasMap(tableList, fangAnLXDM, jiChuBiaoXXList);
        //拼接表关系
        String table = getTable(aliasMap);
        //拼接表关联关系
        String joinRelation = getTableJoinRelation(tableList, aliasMap, fangAnLXDM, guanJianZi, jiChuBiaoXXList);
        //拼接视图过滤条件
        String filterCondition = getFilterCondition(tableList, aliasMap);
        //拼接输出
        String fields = getQueryFields(fangAnSCList, aliasMap, fangAnLXDM);

        //where
        List<ShuJuJMXZDDto> fieldList = tableList.stream().flatMap(p ->Optional.ofNullable(p.getSchemaTableList()).orElse(new ArrayList<>()).stream().flatMap(q -> Optional.ofNullable(q.getShuJuJMXZDDtos()).orElse(new ArrayList<>()).stream())).toList();

        fieldList.forEach(e -> e.setAlias(aliasMap.entrySet().stream().filter(p -> p.getKey().contains(formatBiaoMing(e.getShiTuID(), e.getMoShi(), e.getBiaoMing()))).map(Map.Entry::getValue).findFirst().orElse(Tuple.of("", "", false, false)).item1()));
        //转化sql树
        Map<String, ShuJuJMXZDDto> fieldMap = fieldList.stream().collect(Collectors.toMap(f ->Optional.ofNullable(f.getShiTuID()).orElse("").toLowerCase() + "|" + Optional.ofNullable(f.getZiDuanBM()).orElse("").toLowerCase(), e -> e));
        SQLQueryNode condition = transform(root, fieldMap);
        if (ObjectUtils.isNotEmpty(condition.getLeft()) && root!=null) {
            condition = getGuanXiNode(condition, Objects.isNull(root.getCondition()));
        }
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
                if(Objects.isNull(condition.getLeft())){
                    where.getRight().setLeft(condition);
                }
                else {
                    where.getRight().setLeft(new SQLQueryNode(new SQLQueryObject(SQLBinaryOperator.AND)));
                    where.getRight().getLeft().setRight(condition);
                }
            }
        }

        SQLQueryExpr expr = new SQLQueryExpr(sqlRoot);
        log.debug("sql expr json:{}", JacksonUtil.getBeanToJson(expr));

        return expr.buildSQL(sqlRoot);
    }

    /**
     * 获取结果列表总数
     *
     * @param fangAnCXLSId 方案查询历史id
     * @param mergeType 合并类型，1-患者，2-就诊
     * @return Long
     * @throws TongYongYWException 异常
     */
    @Override
    public Long getFangAnJGCount(String fangAnCXLSId, Integer mergeType) throws TongYongYWException, YuanChengException {
        FangAnCXLSDTO fangAnCXLS = chaXunFAXXService.getFangAnCXLSByID(fangAnCXLSId);
        if (ObjectUtils.isEmpty(fangAnCXLS)) {
            throw new TongYongYWException("查询方案历史不存在");
        }
        String guanJianZD = "zuzhijgid,jiuzhenid";
        if (Objects.equals(mergeType, 1)) {
            guanJianZD = "bingrenid";
        }
        String sql = MessageFormat.format("select count(1) from (select {0} from ({1}) tt group by {0}) tt1",
                guanJianZD, fangAnCXLS.getChaXunSQL());
        if(Objects.isNull(mergeType)||Objects.equals(0,mergeType)){
            sql = MessageFormat.format("select count(1) from (select distinct * from ({0}) tt) tt1",
                    fangAnCXLS.getChaXunSQL());
        }
        return linChuangRemoteService.getShuLiang(new ChaXunDto(sql)).getData("数据查询失败");
    }

    /**
     * 获取方案查询结果
     *
     * @param fangAnCXLSId 方案查询历史id
     * @param mergeType 合并类型，1-患者，2-就诊
     * @param pageIndex 页码
     * @param pageSize 每页数量
     * @param isShowBQ 是否显示标签(Excel导出不显示标签)
     * @return List<List<QueryResultDTO>>
     * @throws TongYongYWException 异常
     */
    public List<List<QueryResultDTO>> getFangAnJGList(String fangAnCXLSId, Integer mergeType, Integer pageIndex, Integer pageSize, boolean isShowBQ) throws TongYongYWException, YuanChengException {
        List<List<QueryResultDTO>> result = new ArrayList<>();
        List<FangAnSCDTO> fangAnSCList = new ArrayList<>();
        //收藏夹明细
        List<SC_SC_ShouCangJMXListDto> shouChangJMXList = new ArrayList<>();
        List<String> bingRenIDList=new ArrayList<>();
        //结果平铺
        if(Objects.isNull(mergeType)||Objects.equals(0,mergeType)) {
            result = getFangAnCXQueryResultList(fangAnCXLSId, pageIndex, pageSize, fangAnSCList, bingRenIDList);
            //获取病人收藏夹列表
            if (isShowBQ) {
                shouChangJMXList = BeanUtil.copyListProperties(scScShouCangJMXRepository.findByShouCangRIDAndBingRenIDIn(lyraIdentityService.getYongHuId(), bingRenIDList), SC_SC_ShouCangJMXListDto::new);
            }
            //添加收藏夹标签
            return getShouCangJiaList(isShowBQ, result, shouChangJMXList);
        }

        //根据查询结果按病人进行分组
        List<FangAnCXBRFZDto> bingRenFZList = getFangAnCXBRFZList(fangAnCXLSId, mergeType, pageIndex, pageSize, fangAnSCList);
        if (CollUtil.isEmpty(bingRenFZList)) {
            return result;
        }
        String bingRenIDXSMC = "MPI";
        boolean isHuanZheHB=Objects.equals(mergeType, 1);

        //获取病人收藏夹列表
        if(isShowBQ){
            //获取病人ID列表
            bingRenIDList=bingRenFZList.stream().map(FangAnCXBRFZDto::getBingRenID).distinct().toList();
            shouChangJMXList= BeanUtil.copyListProperties(scScShouCangJMXRepository.findByShouCangRIDAndBingRenIDIn(lyraIdentityService.getYongHuId(),bingRenIDList), SC_SC_ShouCangJMXListDto::new);
        }
        //列表时最大支持列数
        int maxRecordCols=100;
        //药品或者列表展示时
        if (fangAnSCList.stream().anyMatch(p -> "4".equals(p.getZhiBiaoLXDM()))||isShowBQ) {
            //获取输出字段部分按就诊/患者合并的最大长度
            int maxColCount = bingRenFZList.stream().map(br -> br.getFangAnCXJZFZList().stream().map(jz -> jz.getFangAnCXSTFZList().stream().map(st -> st.getFangAnCXSTZJFZList().stream().map(stzj -> stzj.getFangAnCXZDFZList().stream().map(FangAnCXZDFZDto::getZiDuanZhiCount).reduce(0, Integer::sum)).reduce(0, Integer::sum)).reduce(0, (isHuanZheHB ? Integer::sum : Integer::max))).reduce(0, Integer::max)).reduce(0, Integer::max);

            for (FangAnCXBRFZDto bingRenFZ : bingRenFZList) {
                if (isHuanZheHB) {
                    List<QueryResultDTO> row0 = new ArrayList<>();
                    List<QueryResultDTO> row1 = new ArrayList<>();
                    //加入基本信息空值行
                    row0.add(new QueryResultDTO("bingrenid", "bingrenid", bingRenIDXSMC, ""));
                    row1.add(new QueryResultDTO("bingrenid", "bingrenid", bingRenIDXSMC, bingRenFZ.getBingRenID()));
                    if (CollUtil.isNotEmpty(bingRenFZ.getBingRenJBXX())) {
                        row0.addAll(BeanUtil.copyListProperties(bingRenFZ.getBingRenJBXX(), QueryResultDTO::new).stream().peek(q -> q.setZiDuanZhi("")).toList());
                        row1.addAll(bingRenFZ.getBingRenJBXX());
                    }
                    //字段数量
                    int ziDuanCount = 0;
                    for (FangAnCXJZFZDto jiuZhenFZ : bingRenFZ.getFangAnCXJZFZList()) {
                        for (FangAnCXSTFZDto shiTuFZ : jiuZhenFZ.getFangAnCXSTFZList()) {
                            for (FangAnCXSTZJFZDto shiTuZJFZ : shiTuFZ.getFangAnCXSTZJFZList()) {
                                for (FangAnCXZDFZDto ziDuanFZ : shiTuZJFZ.getFangAnCXZDFZList()) {
                                    for (Object zhi : ziDuanFZ.getZiDuanZhiList()) {
                                        row0.add(new QueryResultDTO("", "", "", ziDuanFZ.getZiDuanMC()));
                                        //如果为列表，则将字段名和结果防止为2列
                                        if (isShowBQ) {
                                            row1.add(new QueryResultDTO("", "", "", ziDuanFZ.getZiDuanMC() + " " + zhi));
                                            continue;
                                        }
                                        row1.add(new QueryResultDTO("", "", "", zhi));
                                    }
                                    ziDuanCount = ziDuanCount + ziDuanFZ.getZiDuanZhiCount();
                                }
                            }
                        }
                    }
                    //补全长度
                    if (ziDuanCount < maxColCount) {
                        List<QueryResultDTO> kongBaiZDList = new ArrayList<>(Collections.nCopies(maxColCount - ziDuanCount, new QueryResultDTO()));
                        row0.addAll(kongBaiZDList);
                        row1.addAll(kongBaiZDList);
                    }
                    //列表时限制列数
                    if (isShowBQ) {
                        row1 = row1.subList(0, Math.min(maxRecordCols, row1.size()));
                    }
                    //非列表时字段名单行显示
                    if (!isShowBQ) {
                        result.add(row0);
                    }
                    result.add(row1);
                    continue;
                }
                //按就诊分组
                if(CollectionUtil.isEmpty(bingRenFZ.getFangAnCXJZFZList())){
                    List<QueryResultDTO> row = new ArrayList<>();
                    //加入基本信息
                    row.add(new QueryResultDTO("bingrenid", "bingrenid", bingRenIDXSMC, bingRenFZ.getBingRenID()));
                    if(CollUtil.isNotEmpty(bingRenFZ.getBingRenJBXX())) {
                        row.addAll(bingRenFZ.getBingRenJBXX());
                    }
                    //列表时限制列数
                    if(isShowBQ){
                        row=row.subList(0,Math.min(maxRecordCols, row.size()));
                    }
                    result.add(row);
                }
                for (FangAnCXJZFZDto jiuZhenFZ : bingRenFZ.getFangAnCXJZFZList()) {
                    List<QueryResultDTO> row0 = new ArrayList<>();
                    List<QueryResultDTO> row1 = new ArrayList<>();
                    //加入基本信息空值行
                    row0.add(new QueryResultDTO("bingrenid", "bingrenid", bingRenIDXSMC, ""));
                    row1.add(new QueryResultDTO("bingrenid", "bingrenid", bingRenIDXSMC, bingRenFZ.getBingRenID()));
                    if (CollUtil.isNotEmpty(bingRenFZ.getBingRenJBXX())) {
                        row0.addAll(BeanUtil.copyListProperties(bingRenFZ.getBingRenJBXX(), QueryResultDTO::new).stream().peek(q -> q.setZiDuanZhi("")).toList());
                        row1.addAll(bingRenFZ.getBingRenJBXX());
                    }
                    //字段数量
                    int ziDuanCount = 0;
                    for (FangAnCXSTFZDto shiTuFZ : jiuZhenFZ.getFangAnCXSTFZList()) {
                        for (FangAnCXSTZJFZDto shiTuZJFZ : shiTuFZ.getFangAnCXSTZJFZList()) {
                            for (FangAnCXZDFZDto ziDuanFZ : shiTuZJFZ.getFangAnCXZDFZList()) {
                                for (Object zhi : ziDuanFZ.getZiDuanZhiList()) {
                                    row0.add(new QueryResultDTO("", "", "", ziDuanFZ.getZiDuanMC()));
                                    //如果为列表，则将字段名和结果防止为2列
                                    if (isShowBQ) {
                                        row1.add(new QueryResultDTO("", "", "", ziDuanFZ.getZiDuanMC() + " " + zhi));
                                        continue;
                                    }
                                    row1.add(new QueryResultDTO("", "", "", zhi));
                                }
                                ziDuanCount = ziDuanCount + ziDuanFZ.getZiDuanZhiCount();
                            }
                        }
                    }
                    //补全长度
                    if (ziDuanCount < maxColCount) {
                        List<QueryResultDTO> kongBaiZDList = new ArrayList<>(Collections.nCopies(maxColCount - ziDuanCount, new QueryResultDTO()));
                        row0.addAll(kongBaiZDList);
                        row1.addAll(kongBaiZDList);
                    }
                    //列表时限制列数
                    if (isShowBQ) {
                        row1 = row1.subList(0, Math.min(maxRecordCols, row1.size()));
                    }
                    //非列表时字段名单行显示
                    if (!isShowBQ) {
                        result.add(row0);
                    }
                    result.add(row1);
                }
            }
            //添加收藏夹标签
            return getShouCangJiaList(isShowBQ, result, shouChangJMXList);
        }

        //普通行转列
        Stream<FangAnCXJGFZDto> binRenSXFZStream=bingRenFZList.stream().flatMap(br->br.getFangAnCXJZFZList().stream().flatMap(jz->jz.getFangAnCXSTFZList().stream().flatMap(st->st.getFangAnCXSTZJFZList().stream().flatMap(stzj->stzj.getFangAnCXZDFZList().stream().map(zd-> {
            FangAnCXJGFZDto binRenSXFZ=new FangAnCXJGFZDto();
            binRenSXFZ.setJiuZhenSXH(jz.getShunXuHao());
            binRenSXFZ.setShiTuBM(st.getShiTuDM());
            binRenSXFZ.setShiTuSXH(st.getShiTuSXH());
            binRenSXFZ.setShiTuZJSXH(stzj.getShiTuZJSXH());
            binRenSXFZ.setZiDuanDM(zd.getZiDuanDM());
            binRenSXFZ.setZiDuanYDM(zd.getZiDuanYDM());
            binRenSXFZ.setZiDuanMC(zd.getZiDuanMC());
            binRenSXFZ.setZiDuanSXH(zd.getZiDuanSXH());
            binRenSXFZ.setZiDuanCD(zd.getZiDuanZhiCount());
            return binRenSXFZ;
         })))));
        List<FangAnCXJGFZDto> bingRenSXFZList;
        //按患者合并
        if (isHuanZheHB) {
            //按就诊次数视图次数获取每个字段次数分组
            bingRenSXFZList=binRenSXFZStream.collect(Collectors.groupingBy(br->ListUtil.toList(br.getJiuZhenSXH(),br.getShiTuBM(),br.getShiTuSXH(),br.getShiTuZJSXH(),br.getZiDuanDM(),br.getZiDuanYDM(),br.getZiDuanMC(),br.getZiDuanSXH()),LinkedHashMap::new, Collectors.toList())).entrySet().stream().map(br-> {
                Integer ziDuanMaxCount = br.getValue().stream().map(FangAnCXJGFZDto::getZiDuanCD).reduce(0, Integer::max);
                FangAnCXJGFZDto binRenSXFZ=new FangAnCXJGFZDto();
                binRenSXFZ.setJiuZhenSXH((Integer) br.getKey().get(0));
                binRenSXFZ.setShiTuBM(String.valueOf(br.getKey().get(1)));
                binRenSXFZ.setShiTuSXH((Integer) br.getKey().get(2));
                binRenSXFZ.setShiTuZJSXH((Integer) br.getKey().get(3));
                binRenSXFZ.setZiDuanDM(String.valueOf(br.getKey().get(4)));
                binRenSXFZ.setZiDuanYDM(String.valueOf(br.getKey().get(5)));
                binRenSXFZ.setZiDuanMC(String.valueOf(br.getKey().get(6)));
                binRenSXFZ.setZiDuanSXH((Integer) br.getKey().get(7));
                binRenSXFZ.setZiDuanCD(ziDuanMaxCount);
                return binRenSXFZ;
            }).sorted(Comparator.comparing(FangAnCXJGFZDto::getJiuZhenSXH).thenComparing(FangAnCXJGFZDto::getShiTuSXH).thenComparing(FangAnCXJGFZDto::getShiTuZJSXH).thenComparing(FangAnCXJGFZDto::getZiDuanSXH)).toList();
        }
        else{
            //按视图次数获取每个字段次数分组
            bingRenSXFZList=binRenSXFZStream.collect(Collectors.groupingBy(br->ListUtil.toList(br.getShiTuBM(),br.getShiTuSXH(),br.getShiTuZJSXH(),br.getZiDuanDM(),br.getZiDuanYDM(),br.getZiDuanMC(),br.getZiDuanSXH()),LinkedHashMap::new, Collectors.toList())).entrySet().stream().map(br-> {
                Integer ziDuanMaxCount = br.getValue().stream().map(FangAnCXJGFZDto::getZiDuanCD).reduce(0, Integer::max);
                FangAnCXJGFZDto binRenSXFZ=new FangAnCXJGFZDto();
                binRenSXFZ.setJiuZhenSXH(1);
                binRenSXFZ.setShiTuBM(String.valueOf(br.getKey().get(0)));
                binRenSXFZ.setShiTuSXH((Integer) br.getKey().get(1));
                binRenSXFZ.setShiTuZJSXH((Integer) br.getKey().get(2));
                binRenSXFZ.setZiDuanDM(String.valueOf(br.getKey().get(3)));
                binRenSXFZ.setZiDuanYDM(String.valueOf(br.getKey().get(4)));
                binRenSXFZ.setZiDuanMC(String.valueOf(br.getKey().get(5)));
                binRenSXFZ.setZiDuanSXH((Integer) br.getKey().get(6));
                binRenSXFZ.setZiDuanCD(ziDuanMaxCount);
                return binRenSXFZ;
            }).sorted(Comparator.comparing(FangAnCXJGFZDto::getShiTuSXH).thenComparing(FangAnCXJGFZDto::getShiTuZJSXH).thenComparing(FangAnCXJGFZDto::getZiDuanSXH)).toList();
        }
        for (FangAnCXBRFZDto bingRenFZ : bingRenFZList) {
            //按患者分组
            if(isHuanZheHB){
                List<QueryResultDTO> row = new ArrayList<>();
                //加入基本信息空值行
                row.add(new QueryResultDTO("bingrenid", "bingrenid", bingRenIDXSMC, bingRenFZ.getBingRenID()));
                if(CollUtil.isNotEmpty(bingRenFZ.getBingRenJBXX())) {
                    row.addAll(bingRenFZ.getBingRenJBXX());
                }
                for(var bingRenSXFZ:bingRenSXFZList) {
                    for (int zdTuple = 0; zdTuple < bingRenSXFZ.getZiDuanCD(); zdTuple++) {
                        Object zhi = "";
                        var jiuZhenFZ = bingRenFZ.getFangAnCXJZFZList().stream().filter(jz -> Objects.equals(jz.getShunXuHao(), bingRenSXFZ.getJiuZhenSXH())).flatMap(jz -> jz.getFangAnCXSTFZList().stream().filter(st -> Objects.equals(st.getShiTuDM(), bingRenSXFZ.getShiTuBM())).flatMap(st -> st.getFangAnCXSTZJFZList().stream().filter(stzj -> Objects.equals(stzj.getShiTuZJSXH(), bingRenSXFZ.getShiTuZJSXH())).flatMap(stzj -> stzj.getFangAnCXZDFZList().stream().filter(zd -> Objects.equals(zd.getZiDuanDM(), bingRenSXFZ.getZiDuanDM()))))).findFirst().orElse(null);
                        if (jiuZhenFZ != null && jiuZhenFZ.getZiDuanZhiCount() >= bingRenSXFZ.getZiDuanCD()) {
                            zhi = jiuZhenFZ.getZiDuanZhiList().get(zdTuple);
                        }
                        row.add(new QueryResultDTO(bingRenSXFZ.getZiDuanDM(),bingRenSXFZ.getZiDuanYDM(), bingRenSXFZ.getZiDuanMC(), zhi));
                    }
                }
                result.add(row);
                continue;
            }
            //按就诊分组
            if(CollectionUtil.isEmpty(bingRenFZ.getFangAnCXJZFZList())){
                List<QueryResultDTO> row = new ArrayList<>();
                //加入基本信息
                row.add(new QueryResultDTO("bingrenid", "bingrenid", bingRenIDXSMC, bingRenFZ.getBingRenID()));
                if(CollUtil.isNotEmpty(bingRenFZ.getBingRenJBXX())) {
                    row.addAll(bingRenFZ.getBingRenJBXX());
                }
                result.add(row);
            }
            for (FangAnCXJZFZDto jiuZhenFZ : bingRenFZ.getFangAnCXJZFZList()) {
                List<QueryResultDTO> row = new ArrayList<>();
                //加入基本信息
                row.add(new QueryResultDTO("bingrenid", "bingrenid", bingRenIDXSMC, bingRenFZ.getBingRenID()));
                if(CollUtil.isNotEmpty(bingRenFZ.getBingRenJBXX())) {
                    row.addAll(bingRenFZ.getBingRenJBXX());
                }
                for(var bingRenSXFZ:bingRenSXFZList) {
                    for (int zdTuple = 0; zdTuple < bingRenSXFZ.getZiDuanCD(); zdTuple++) {
                        Object zhi = "";
                        var shiTuZDFZ = jiuZhenFZ.getFangAnCXSTFZList().stream().filter(st -> Objects.equals(st.getShiTuDM(), bingRenSXFZ.getShiTuBM())).flatMap(st -> st.getFangAnCXSTZJFZList().stream().filter(stzj -> Objects.equals(stzj.getShiTuZJSXH(), bingRenSXFZ.getShiTuZJSXH())).flatMap(stzj -> stzj.getFangAnCXZDFZList().stream().filter(zd -> Objects.equals(zd.getZiDuanDM(), bingRenSXFZ.getZiDuanDM())))).findFirst().orElse(null);
                        if (shiTuZDFZ != null && shiTuZDFZ.getZiDuanZhiCount() >= bingRenSXFZ.getZiDuanCD()) {
                            zhi = shiTuZDFZ.getZiDuanZhiList().get(zdTuple);
                        }
                        row.add(new QueryResultDTO(bingRenSXFZ.getZiDuanDM(), bingRenSXFZ.getZiDuanYDM(), bingRenSXFZ.getZiDuanMC(), zhi));
                    }
                }
                result.add(row);
            }
        }
        //添加收藏夹标签
        return getShouCangJiaList(isShowBQ, result, shouChangJMXList);
    }

    /**
     * 获取方案查询病人分组列表
     *
     * @param fangAnCXLSId 方案查询历史id
     * @param mergeType 合并类型，1-患者，2-就诊
     * @param pageIndex 页码
     * @param pageSize  每页数量
     * @param fangAnSCList  方案输出项
     * @return List<FangAnCXBRFZDto>
     * @throws TongYongYWException 异常
     */
    private List<FangAnCXBRFZDto> getFangAnCXBRFZList(String fangAnCXLSId, Integer mergeType, Integer pageIndex, Integer pageSize, List<FangAnSCDTO> fangAnSCList) throws TongYongYWException, YuanChengException {
        FangAnCXLSDTO fangAnCXLS = chaXunFAXXService.getFangAnCXLSByID(fangAnCXLSId);
        if (ObjectUtils.isEmpty(fangAnCXLS)) {
            throw new TongYongYWException("查询方案历史不存在");
        }
        if (StringUtil.isBlank(fangAnCXLS.getChaXunSQL())) {
            throw new TongYongYWException("查询方案不存在");
        }
        //根据合并方式分页获取关键字段
        String guanJianZD = "zuzhijgid,jiuzhenid";
        if (Objects.equals(mergeType, 1)) {
            guanJianZD = "bingrenid";
        }
        String guanJianZDSql = MessageFormat.format("select {0} from (select {0},row_number() over(order by {0}) as rn from ({1}) tt group by {0}) tt where rn>{3,number,#} and rn<={2,number,#}", guanJianZD, fangAnCXLS.getChaXunSQL(), pageSize * pageIndex, pageSize * (pageIndex - 1));
        log.info("关键字段查询语句：{}", guanJianZDSql);
        List<Map<String, Object>> guanJianZDList = linChuangRemoteService.getZiDianList(new ChaXunDto(guanJianZDSql)).getData("数据查询失败");
        if (CollUtil.isEmpty(guanJianZDList)) {
            return null;
        }
        //获取详细的查询结果
        //避免因为分页超出1000造成查询报错，所以再进行一次分页
        int guanJianZDPageCount = (int) Math.ceil(guanJianZDList.size() * 1.0 / 1000);
        List<Map<String, Object>> jieGuoList = new ArrayList<>();
        for (int i = 0; i < guanJianZDPageCount; i++) {
            int fromIndex = i * 1000;
            int toIndex = Math.min(fromIndex + 1000, guanJianZDList.size());
            //关键字拼接为字符串，以便作为查询条件加入sql中
            List<String> pageGuanJianZDList = new ArrayList<>();
            for (Map<String, Object> entry : guanJianZDList.subList(fromIndex, toIndex)) {
                List<String> guanJianZDZhi = new ArrayList<>();
                for (String k : guanJianZD.split(",")) {
                    guanJianZDZhi.add("'" + entry.get(k) + "'");
                }
                pageGuanJianZDList.add("(" + StringUtils.join(guanJianZDZhi, ",") + ")");
            }
            String pageResultSql = MessageFormat.format("select * from ({0}) tt where ({1}) in ({2}) order by {1}", fangAnCXLS.getChaXunSQL(), guanJianZD, StringUtils.join(pageGuanJianZDList, ","));
            log.info("分页查询语句：{}", pageResultSql);
            jieGuoList.addAll(linChuangRemoteService.getZiDianList(new ChaXunDto(pageResultSql)).getData("数据查询失败"));
        }
        //解析sql,获取字段与表的关系
        Pattern pattern = Pattern.compile("t_\\d+|zd_\\d+");
        Matcher matcher = pattern.matcher(fangAnCXLS.getChaXunSQL());
        List<String> matCherList=new ArrayList<>();
        while (matcher.find()) {
            matCherList.add(matcher.group());
        }

        //区分方案查询类型是否为住院（药品时会用到）
        boolean isZhuYuan = "3".equals(jieGuoList.get(0).get("jiuzhenywlx"));
        fangAnSCList.addAll(JsonUtil.getJsonToList(fangAnCXLS.getChaXunSC(), FangAnSCDTO.class));
        for (FangAnSCDTO fangAnSC : fangAnSCList) {
            fangAnSC.setId("zd_" + fangAnSCList.indexOf(fangAnSC));
            if(StringUtil.hasText(fangAnSC.getZhiBiaoLXDM())) {
                switch (fangAnSC.getZhiBiaoLXDM()) {
                    case "2":
                        fangAnSC.setBiaoMing("jy_bg_baogaomx");
                        fangAnSC.setZhiBiaoID("");
                        break;
                    case "3":
                        fangAnSC.setBiaoMing("jc_bg_baogaoxx");
                        fangAnSC.setZhiBiaoID("");
                        break;
                    case "4":
                        fangAnSC.setBiaoMing(isZhuYuan ? "yz_zy_yizhuxx" : "yz_mz_yizhuxx");
                        fangAnSC.setZhiBiaoID("");
                        break;
                    default:
                        break;
                }
            }
            if (StringUtils.isBlank(fangAnSC.getBiaoMing())) {
                continue;
            }
            fangAnSC.setBiaoMing(Optional.ofNullable(fangAnSC.getBiaoMing()).orElse("").toLowerCase());
            int index= matCherList.indexOf(fangAnSC.getId())-1;
            if(index>=0) {
                fangAnSC.setBieMing(matCherList.get(index));
            }
        }

        List<FangAnSCDTO> jiBenXXSCList = fangAnSCList.stream().filter(p -> "br_da_jibenxx".equalsIgnoreCase(p.getBiaoMing())).toList();
        var qiTaSCList = fangAnSCList.stream().filter(p -> !"br_da_jibenxx".equalsIgnoreCase(p.getBiaoMing())&&StringUtil.hasText(p.getBieMing())).collect(Collectors.groupingBy(FangAnSCDTO::getBieMing, LinkedHashMap::new, Collectors.toList()));

        //结果分组除重 患者基本信息表：br_da_jibenxx，该表相关字段分组后只显示1次，其它按就诊显示
        return jieGuoList.stream().collect(Collectors.groupingBy(p -> p.get("bingrenid"),LinkedHashMap::new, Collectors.toList())).entrySet().stream().map(p -> {
            FangAnCXBRFZDto bingRenFZ = new FangAnCXBRFZDto();
            bingRenFZ.setBingRenID(String.valueOf(p.getKey()));
            //绑定患者基本信息
            if (CollUtil.isNotEmpty(jiBenXXSCList)) {
                List<QueryResultDTO> jiBenXXSCJGList = new ArrayList<>();
                for (FangAnSCDTO jiBenXXSC : jiBenXXSCList) {
                    QueryResultDTO jiBenXXSCJG = new QueryResultDTO(jiBenXXSC.getId(),jiBenXXSC.getZhiBiaoID(), jiBenXXSC.getZhiBiaoMC(), p.getValue().get(0).get(jiBenXXSC.getId()));
                    jiBenXXSCJGList.add(jiBenXXSCJG);
                }
                bingRenFZ.setBingRenJBXX(jiBenXXSCJGList);
            }
            if (CollUtil.isEmpty(qiTaSCList)) {
                bingRenFZ.setFangAnCXJZFZList(new ArrayList<>());
                return bingRenFZ;
            }
            AtomicInteger shunXuHao= new AtomicInteger();
            List<FangAnCXJZFZDto> jiuZhenFZList = p.getValue().stream().sorted(Comparator.comparing(jz->DateUtil.parse(Optional.ofNullable(jz.get("jiuzhenrq")).orElse("").toString()),Comparator.nullsLast(Comparator.reverseOrder()))).collect(Collectors.groupingBy(jz -> CollUtil.toList(jz.get("zuzhijgid"), jz.get("jiuzhenid")),LinkedHashMap::new, Collectors.toList())).entrySet().stream().map(jz -> {
                FangAnCXJZFZDto jiuZhenFZ = new FangAnCXJZFZDto();
                jiuZhenFZ.setZuZhiJGID(String.valueOf(jz.getKey().get(0)));
                jiuZhenFZ.setJiuZhenID(String.valueOf(jz.getKey().get(1)));
                jiuZhenFZ.setJiuZhenRQ(DateUtil.parse(Optional.ofNullable(jz.getValue().get(0).get("jiuzhenrq")).orElse("").toString()));
                jiuZhenFZ.setShunXuHao(shunXuHao.getAndIncrement());
                List<FangAnCXSTFZDto> shiTuFZList=new ArrayList<>();
                //非患者基本信息以外的信息绑定
                int shiTuSXH=0;
                for(Map.Entry<String,List<FangAnSCDTO>> qiTaSC:qiTaSCList.entrySet()){
                    FangAnCXSTFZDto shiTuFZ=new FangAnCXSTFZDto();
                    shiTuFZ.setShiTuDM(qiTaSC.getKey());
                    shiTuFZ.setShiTuSXH(shiTuSXH++);
                    AtomicInteger shiTuZJSXH= new AtomicInteger();
                    var ziDuanIDList=qiTaSC.getValue().stream().map(FangAnSCDTO::getId).toList();
                    //数据按视图字段分组
                    var shiTuZJFZList=jz.getValue().stream().map(st->st.entrySet().stream().filter(zd->ziDuanIDList.contains(zd.getKey())).toList()).map(st->{
                        List<FangAnCXZDFZDto> ziDuanFZList = new ArrayList<>();
                        int ziDuanSXH=0;
                        FangAnCXSTZJFZDto shiTuZJFZ=new FangAnCXSTZJFZDto();
                        //结果按主键分组时会出现值未合并为一列的情况，比如输出2个检验指标时结果中会有4列
                        //shiTuZJFZ.setShiTuZJZ(String.valueOf(st.getKey()));
                        shiTuZJFZ.setShiTuZJSXH(shiTuZJSXH.getAndIncrement());
                        for (FangAnSCDTO fangAnSC : qiTaSC.getValue()) {
                            FangAnCXZDFZDto ziDuanFZ = new FangAnCXZDFZDto();
                            ziDuanFZ.setZiDuanDM(fangAnSC.getId());
                            ziDuanFZ.setZiDuanYDM(fangAnSC.getZhiBiaoID());
                            ziDuanFZ.setZiDuanMC(fangAnSC.getZhiBiaoMC());
                            List<Object> zhiList = st.stream().filter(zd ->Objects.equals(fangAnSC.getId(),zd.getKey())&&ObjectUtils.isNotEmpty(zd.getValue())).map(Map.Entry::getValue).distinct().toList();
                            ziDuanFZ.setZiDuanZhiList(zhiList);
                            ziDuanFZ.setZiDuanZhiCount(zhiList.size());
                            ziDuanFZ.setZiDuanSXH(ziDuanSXH++);
                            ziDuanFZList.add(ziDuanFZ);
                        }
                        shiTuZJFZ.setFangAnCXZDFZList(ziDuanFZList);
                        shiTuZJFZ.setZiDuanCount(ziDuanFZList.size());
                        return shiTuZJFZ;
                    }).toList();
                    shiTuFZ.setFangAnCXSTZJFZList(shiTuZJFZList);
                    shiTuFZ.setShiTuZJCount(shiTuZJFZList.size());
                    shiTuFZList.add(shiTuFZ);
                }
                jiuZhenFZ.setFangAnCXSTFZList(shiTuFZList);
                return jiuZhenFZ;
            }).toList();
            bingRenFZ.setFangAnCXJZFZList(jiuZhenFZList);
            bingRenFZ.setJiuZhenCount(jiuZhenFZList.size());
            return bingRenFZ;
        }).toList();
    }

    /**
     * 分页获取方案查询结果集
     *
     * @param fangAnCXLSId 方案查询历史id
     * @param pageIndex 页码
     * @param pageSize  每页数量
     * @param fangAnSCList  方案输出项
     * @param bingRenIDList 病人ID列表
     * @return List<FangAnCXBRFZDto>
     * @throws TongYongYWException 异常
     */
    private List<List<QueryResultDTO>> getFangAnCXQueryResultList(String fangAnCXLSId, Integer pageIndex, Integer pageSize, List<FangAnSCDTO> fangAnSCList,List<String> bingRenIDList) throws TongYongYWException, YuanChengException {
        FangAnCXLSDTO fangAnCXLS = chaXunFAXXService.getFangAnCXLSByID(fangAnCXLSId);
        if (ObjectUtils.isEmpty(fangAnCXLS)) {
            throw new TongYongYWException("查询方案历史不存在");
        }
        if (StringUtil.isBlank(fangAnCXLS.getChaXunSQL())) {
            throw new TongYongYWException("查询方案不存在");
        }
        String jieGuoSql = MessageFormat.format("select * from (select *,row_number() over() as rn from (select distinct * from ({0}) tt) tt) tt where rn>{2,number,#} and rn<={1,number,#}", fangAnCXLS.getChaXunSQL(), pageSize * pageIndex, pageSize * (pageIndex - 1));
        log.info("结果查询语句：{}", jieGuoSql);
        List<Map<String, Object>> jieGuoList = linChuangRemoteService.getZiDianList(new ChaXunDto(jieGuoSql)).getData("数据查询失败");
        if (CollUtil.isEmpty(jieGuoList)) {
            return new ArrayList<>();
        }
        bingRenIDList.addAll(jieGuoList.stream().map(p->Optional.ofNullable(p.get("bingrenid")).orElse("").toString()).toList());
        //解析sql,获取字段与表的关系
        Pattern pattern = Pattern.compile("t_\\d+|zd_\\d+");
        Matcher matcher = pattern.matcher(fangAnCXLS.getChaXunSQL());
        List<String> matCherList=new ArrayList<>();
        while (matcher.find()) {
            matCherList.add(matcher.group());
        }

        //区分方案查询类型是否为住院（药品时会用到）
        boolean isZhuYuan = "3".equals(jieGuoList.get(0).get("jiuzhenywlx"));
        fangAnSCList.addAll(JsonUtil.getJsonToList(fangAnCXLS.getChaXunSC(), FangAnSCDTO.class));
        for (FangAnSCDTO fangAnSC : fangAnSCList) {
            fangAnSC.setId("zd_" + fangAnSCList.indexOf(fangAnSC));
            if(StringUtil.hasText(fangAnSC.getZhiBiaoLXDM())) {
                switch (fangAnSC.getZhiBiaoLXDM()) {
                    case "2":
                        fangAnSC.setBiaoMing("jy_bg_baogaomx");
                        fangAnSC.setZhiBiaoID("");
                        break;
                    case "3":
                        fangAnSC.setBiaoMing("jc_bg_baogaoxx");
                        fangAnSC.setZhiBiaoID("");
                        break;
                    case "4":
                        fangAnSC.setBiaoMing(isZhuYuan ? "yz_zy_yizhuxx" : "yz_mz_yizhuxx");
                        fangAnSC.setZhiBiaoID("");
                        break;
                    default:
                        break;
                }
            }
            if (StringUtils.isBlank(fangAnSC.getBiaoMing())) {
                continue;
            }
            fangAnSC.setBiaoMing(Optional.ofNullable(fangAnSC.getBiaoMing()).orElse("").toLowerCase());
            int index= matCherList.indexOf(fangAnSC.getId())-1;
            if(index>=0) {
                fangAnSC.setBieMing(matCherList.get(index));
            }
        }

        return jieGuoList.stream().map(p -> {
            List<QueryResultDTO> queryResultList=new ArrayList<>();
            //加入基本信息空值行
            queryResultList.add(new QueryResultDTO("bingrenid", "bingrenid", "MPI", p.get("bingrenid")));
            for (FangAnSCDTO fangAnSC : fangAnSCList) {
                QueryResultDTO ziDuanXX = new QueryResultDTO();
                ziDuanXX.setZiDuanDM(fangAnSC.getId());
                ziDuanXX.setZiDuanYDM(fangAnSC.getZhiBiaoID());
                ziDuanXX.setZiDuanMC(fangAnSC.getZhiBiaoMC());
                ziDuanXX.setZiDuanZhi(p.get(fangAnSC.getId()));
                queryResultList.add(ziDuanXX);
            }
            return queryResultList;
        }).toList();
    }

    /**
     * 获取收藏夹列表
     *
     * @param isShowBQ 是否显示标签
     * @param result 方案结果
     * @param shouChangJMXList 收藏夹明细列表
     * @return List<List<QueryResultDTO>>
     */
    private List<List<QueryResultDTO>> getShouCangJiaList(Boolean isShowBQ, List<List<QueryResultDTO>> result, List<SC_SC_ShouCangJMXListDto> shouChangJMXList) {
        if(isShowBQ){
            for (var jieGuoLB :result){
                jieGuoLB.add(new QueryResultDTO("","","标签",shouChangJMXList.stream().filter(x-> Objects.equals(x.getBingRenID(), jieGuoLB.stream().filter(p->"bingrenid".equals(p.getZiDuanDM())).map(QueryResultDTO::getZiDuanZhi).findFirst().orElse(null))).toList(),1));
            }
        }
        return result;
    }

    /**
     * 获取方案结果Excel
     *
     * @param fangAnCXLSId 方案查询历史id
     * @param mergeType 合并类型，1-患者，2-就诊
     * @param pageSize 页码
     * @return List<XSSFWorkbook>
     * @throws TongYongYWException 异常
     * @throws RuntimeException 异常
     */
    public List<ByteArrayOutputStream> getFangAnJGExcelList(String fangAnCXLSId, Integer mergeType, Integer pageSize) throws TongYongYWException, RuntimeException, YuanChengException {
        List<ByteArrayOutputStream> excelList=new ArrayList<>();
        Long jieGuoCount=getFangAnJGCount(fangAnCXLSId,mergeType);

        int pageCount= ExportUtils.getPageCount(Math.toIntExact(jieGuoCount),pageSize);
        int i=1;
        //用do...while可以解决数据为空时返回的压缩包不能解压的问题
        do {
            List<List<QueryResultDTO>> fangAnJGList = getFangAnJGList(fangAnCXLSId, mergeType, i, pageSize, false);
            i++;
            try (XSSFWorkbook wb = new XSSFWorkbook()) {
                XSSFSheet sheet = wb.createSheet("结果列表");
                int index = 0;
                for (List<QueryResultDTO> fangAnJGRow : fangAnJGList) {
                    buildSheet(sheet, fangAnJGRow, index);
                    index++;
                }
                // 将Workbook写入内存流
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                wb.write(baos);
                excelList.add(baos);
                baos.close();
            } catch (IOException e) {
                throw new TongYongYWException(e.getMessage());
            }
        } while (i <= pageCount);

        return excelList;
    }

    /**
     * 填充表格
     *
     * @param sheet 表单
     * @param fangAnJGRow 方案结果行
     * @param index 行标
     */
    private void buildSheet(XSSFSheet sheet, List<QueryResultDTO> fangAnJGRow, int index) {
        int colIndex = 0;
        XSSFRow row0 = null;
        XSSFRow row = sheet.createRow(index + 1);
        if (index == 0) {
            row0 = sheet.createRow(index);
        }
        for (QueryResultDTO fangAnJGCOl : fangAnJGRow) {

            if (index > 0) {
                row.createCell(colIndex).setCellValue(null != fangAnJGCOl.getZiDuanZhi() ? fangAnJGCOl.getZiDuanZhi().toString() : "");
                colIndex++;
                continue;
            }
            if (row0 != null) {
                XSSFCell cell = row0.createCell(colIndex);
                cell.setCellStyle(getHeadStyle(sheet.getWorkbook()));
                cell.setCellValue(fangAnJGCOl.getZiDuanMC());
            }
            row.createCell(colIndex).setCellValue(null != fangAnJGCOl.getZiDuanZhi() ? fangAnJGCOl.getZiDuanZhi().toString() : "");
            colIndex++;
        }
    }

    /**
     * 设置样式
     *
     * @param wb 表格
     * @return CellStyle
     */
    private CellStyle getHeadStyle(Workbook wb){
        CellStyle cellStyle=wb.createCellStyle();
        //居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //加粗
        Font font=wb.createFont();
        font.setBold(true);
        cellStyle.setFont(font);
        return cellStyle;
    }

    /**
     * 获取方案患者信息
     *
     * @param fangAnCXLSId 方案查询历史id
     * @param pageIndex 页码
     * @param pageSize 每页数量
     * @return List<BingRenJBXXDTO>
     * @throws TongYongYWException 异常
     */
    @Override
    public List<BingRenJBXXDTO> getBinRenJBXXList(String fangAnCXLSId, Integer pageIndex, Integer pageSize) throws TongYongYWException, YuanChengException {
        FangAnCXLSDTO fangAnCXLS = chaXunFAXXService.getFangAnCXLSByID(fangAnCXLSId);
        if (ObjectUtils.isEmpty(fangAnCXLS)) {
            throw new TongYongYWException("查询方案历史不存在");
        }
        if (StringUtils.isBlank(fangAnCXLS.getGuanJianZi())) {
            return null;
        }
        if (pageSize > 1000) {
            throw new TongYongYWException("每页条数不能超过1000");
        }

        String bingLiCXJCSql = getBingRenJBXXSQL(fangAnCXLS.getChaXunSQL(), fangAnCXLS.getGuanJianZi());

        //根据合并方式分页获取关键字段
        String guanJianZD = "bingrenid";
        String guanJianZDSql = MessageFormat.format("select {0} from (select {0},row_number() over(order by {0}) as rn from ({1}) tt group by {0}) tt where rn<= {2,number,#} and rn>{3,number,#}", guanJianZD, bingLiCXJCSql, pageSize * pageIndex, pageSize * (pageIndex - 1));

        List<String> bingRenIDList = linChuangRemoteService.getStringList(new ChaXunDto(guanJianZDSql)).getData("数据查询失败");

        //获取对应病人的收藏夹明细
        List<SC_SC_ShouCangJMXListDto> shouChangJMXList= BeanUtil.copyListProperties(scScShouCangJMXRepository.findByShouCangRIDAndBingRenIDIn(lyraIdentityService.getYongHuId(),bingRenIDList), SC_SC_ShouCangJMXListDto::new);

        //获取病历查询结果
        String bingLiCXSql = MessageFormat.format(" {0} and a.bingrenid in (''{1}'') order by a.bingrenid", bingLiCXJCSql, CollUtil.join(bingRenIDList, "','"));

        List<BingLiSCDTO> bingLiSCList = BeanUtil.copyListProperties(linChuangRemoteService.getZiDianList(new ChaXunDto(bingLiCXSql)).getData("数据查询失败"),BingLiSCDTO::new);

        return bingLiSCList.stream().collect(Collectors.groupingBy(p -> ListUtil.toList(p.getBingRenID(), p.getXingMing(), p.getXingBieDM(), p.getXingBieMC(), p.getChuShengRQ(), p.getMenZhenCS(), p.getZhuYuanCS()),LinkedHashMap::new, Collectors.toList())).entrySet().stream().map(p -> {
            BingRenJBXXDTO item = new BingRenJBXXDTO();
            item.setBingRenID(String.valueOf(p.getKey().get(0)));
            item.setXingMing(String.valueOf(p.getKey().get(1)));
            item.setXingBieDM(String.valueOf(p.getKey().get(2)));
            item.setXingBieMC(String.valueOf(p.getKey().get(3)));
            item.setChuShengRQ(DateUtil.parse(Optional.ofNullable(p.getKey().get(4)).orElse("").toString()));
            item.setNianLing(DateUtil.getAge(DateFormatUtils.format(DateUtil.parse(Optional.ofNullable(p.getKey().get(4)).orElse("").toString()), "yyyy-MM-dd")));
            item.setMzJiuZhenNum((Integer) p.getKey().get(5));
            item.setZhuYuanNum((Integer) p.getKey().get(6));
            List<JiuZhenXXDTO> jiuZhenXXList = p.getValue().stream().collect(Collectors.groupingBy(q -> ListUtil.toList(q.getJiuZhenYWID(), q.getJiuZhenYWLXDM(), q.getZuZhiJGID(), q.getZuZhiJGMC(), q.getJiuZhenRQ(), q.getJiuZhenKSID(), q.getJiuZhenKSMC()),LinkedHashMap::new, Collectors.toList())).entrySet().stream().map(q -> {
                JiuZhenXXDTO jiuZhenBLXX = new JiuZhenXXDTO();
                jiuZhenBLXX.setJiuZhenYWID(String.valueOf(q.getKey().get(0)));
                jiuZhenBLXX.setJiuZhenYWLXDM(String.valueOf(q.getKey().get(1)));
                jiuZhenBLXX.setZuZhiJGID(String.valueOf(q.getKey().get(2)));
                jiuZhenBLXX.setZuZhiJGMC(String.valueOf(q.getKey().get(3)));
                jiuZhenBLXX.setJiuZhenRQ(DateUtil.parse(Optional.ofNullable(q.getKey().get(4)).orElse("").toString()));
                jiuZhenBLXX.setJiuZhenKSID(String.valueOf(q.getKey().get(5)));
                jiuZhenBLXX.setJiuZhenKSMC(String.valueOf(q.getKey().get(6)));
                jiuZhenBLXX.setBingLiList(q.getValue().stream().map(BingLiSCDTO::getBingLiJLID).toList());
                return jiuZhenBLXX;
            }).sorted(Comparator.comparing(JiuZhenXXDTO::getJiuZhenRQ, Comparator.nullsLast(Date::compareTo))).toList();
            item.setJiuZhenXXList(jiuZhenXXList);
            //为收藏夹赋值
            List<SC_SC_ShouCangJMXListDto> currentShouChangJMXList=shouChangJMXList.stream().filter(o->Objects.equals(o.getBingRenID(),p.getKey().get(0))).toList();
            item.setShouCangJiaList(BeanUtil.copyListProperties(currentShouChangJMXList,ShouCangJiaDto::new));
            return item;
        }).toList();
    }

    /**
     * 获取方案患者信息数量
     *
     * @param fangAnCXLSId  方案查询历史id
     * @return Long
     * @throws TongYongYWException 异常
     * @throws YuanChengException 异常
     */
    public Long getBinRenJBXXCount(String fangAnCXLSId) throws TongYongYWException, YuanChengException {
        FangAnCXLSDTO fangAnCXLS = chaXunFAXXService.getFangAnCXLSByID(fangAnCXLSId);
        if (ObjectUtils.isEmpty(fangAnCXLS)) {
            throw new TongYongYWException("查询方案历史不存在");
        }
        if (StringUtils.isBlank(fangAnCXLS.getGuanJianZi())) {
            return null;
        }

        String bingLiCXJCSql = getBingRenJBXXSQL(fangAnCXLS.getChaXunSQL(), fangAnCXLS.getGuanJianZi());

        //根据合并方式分页获取关键字段
        String guanJianZD = "bingrenid";
        String guanJianZDSql = MessageFormat.format("select count(1) from (select {0} from ({1}) tt group by {0}) lll0 ", guanJianZD, bingLiCXJCSql);
        return linChuangRemoteService.getShuLiang(new ChaXunDto(guanJianZDSql)).getData("数据查询失败");
    }

    private void subFangAn(List<FangAnCondition> conditionList) throws TongYongYWException {
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


        for (FangAnCondition e : conditionList) {
            if (e.getRelatedFangAnQueryCondition() == null) {
                continue;
            }
            RelatedFangAnQueryCondition relatedFangAnQueryCondition = e.getRelatedFangAnQueryCondition();
            FangAnSqlDTO fangAnSql = map.get(relatedFangAnQueryCondition.getFangAnId());
            if (Objects.isNull(fangAnSql) || StringUtils.isBlank(fangAnSql.getSql())) {
                throw new TongYongYWException("条件方案：" + relatedFangAnQueryCondition.getFangAnMC() + "不存在");
            }
            relatedFangAnQueryCondition.setSql(fangAnSql.getSql());
            relatedFangAnQueryCondition.setZiDuanBM(fangAnSql.getZiDuanBM());
        }
    }

    /**
     * 将FangAnTree转化SQLQueryNode
     *
     * @param node     FangAnTree
     * @param fieldMap 字段Map
     * @return SQLQueryNode
     */
    private SQLQueryNode transform(FangAnTreeNode node, Map<String, ShuJuJMXZDDto> fieldMap) {
        if (node == null) {
            return null;
        }

        FangAnCondition condition = node.getCondition();
        String operator = condition == null ? node.getRelation() : condition.getOperator();
        SQLBinaryOperator sqlOperator = SQLBinaryOperator.getSQLBinaryOperator(operator);
        if(sqlOperator==null&&StringUtil.isBlank(operator)){
            log.error("操作符{}不正确", operator);
        }
        SQLQueryNode sqlNode = new SQLQueryNode();
        if (condition == null || NodeTypeEnum.RELATION_NODE.getType().equals(node.getNodeType())) {
            sqlNode = new SQLQueryNode(new SQLQueryObject(sqlOperator));
        } else {
            ShuJuJMXZDDto field = fieldMap.get(condition.getShiTuID().toLowerCase()+"|"+condition.getZiDuanBM().toLowerCase());
            if (Objects.isNull(field)) {
                log.error("根据{},{}获取条件字段信息失败", condition.getShiTuID(),condition.getZiDuanBM());
                return null;
            }
            List<String> valList = Arrays.stream(StrUtil.nullToDefault(condition.getValues(),"").replaceAll("'","''").split(",")).filter(StringUtils::isNoneBlank).toList();
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
        while (list.hasPrevious()) {
            var item = list.previous();
            boolean isGuanXiJD = Objects.isNull(item.getCondition());
            if (0 == index) {
                sqlNode.setRight(getGuanXiNode(transform(item, fieldMap), isGuanXiJD));
            } else if (index == node.getChildrenConditions().size() - 1) {
                sqlNode.setLeft(getGuanXiNode(transform(item, fieldMap), isGuanXiJD));
            } else {
                SQLQueryNode parentSqlNode = new SQLQueryNode(new SQLQueryObject(sqlOperator));
                sqlNode.setLeft(getGuanXiNode(transform(item, fieldMap), isGuanXiJD));
                parentSqlNode.setRight(sqlNode);
                sqlNode = parentSqlNode;
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

    private SQLQueryObject toSQLQueryObject(String operator, ShuJuJMXZDDto field, List<String> valList, RelatedFangAnQueryCondition relatedFangAnQueryCondition) {
        String val = "";
        SQLBinaryOperator sqlOperator = SQLBinaryOperator.getSQLBinaryOperator(operator);
        if (CollUtil.isEmpty(valList)) {
            //子方案查询
            if (FangAnOperator.IN_FANGAN.getType().equals(operator)) {
                sqlOperator = SQLBinaryOperator.IN;
                //获取子方案输出信息
                List<SC_CX_FangAnSCModel> relatedFangAnSCList=fangAnSCRepository.findByFangAnID(relatedFangAnQueryCondition.getFangAnId());
                relatedFangAnSCList.sort(Comparator.comparingInt(SC_CX_FangAnSCModel::getShunXuHao));
                SC_CX_FangAnSCModel shuChuZDXX=relatedFangAnSCList.stream().filter(p->Objects.equals(relatedFangAnQueryCondition.getFangAnSCId(),p.getId())).findFirst().orElse(null);
                String ziDuanBM=relatedFangAnQueryCondition.getZiDuanBM();
                if(shuChuZDXX!=null){
                    ziDuanBM="zd_"+relatedFangAnSCList.indexOf(shuChuZDXX);
                }

                val = SqlUtils.replaceOutputField(relatedFangAnQueryCondition.getSql(), ziDuanBM);
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
            if ((!ShuJuZLXDMEnum.ZIDIAN.getType().equals(field.getShuJuZLXDM())&&!ShuJuZLXDMEnum.ENUM.getType().equals(field.getShuJuZLXDM())&&containFlag) || ShuJuZLXDMEnum.NUMBER.getType().equals(field.getShuJuZLXDM())) {
                val = valList.get(0);
            } else if (ShuJuZLXDMEnum.DATE.getType().equals(field.getShuJuZLXDM())) {
                val = "date'" + valList.get(0) + "'";
            } else {
                val = "'" + valList.get(0) + "'";
            }
        }
        if((ShuJuZLXDMEnum.ZIDIAN.getType().equals(field.getShuJuZLXDM())||ShuJuZLXDMEnum.ENUM.getType().equals(field.getShuJuZLXDM()))&&ObjectUtils.isNotEmpty(sqlOperator)) {
            switch (sqlOperator) {
                case LIKE:
                    sqlOperator = SQLBinaryOperator.IN;
                    break;
                case NOTLIKE:
                    sqlOperator = SQLBinaryOperator.NOTIN;
                    break;
                default:
                    break;
            }
        }

        SQLQueryObject obj = new SQLQueryObject(sqlOperator);
        obj.setFieldName(field.getAlias() + "." + field.getZiDuanBM());
        obj.setVal(val);

        return obj;
    }


    private String getRelatedFieldCondition(List<RelatedFieldCondition> relatedFieldConditions, Map<String, ShuJuJMXZDDto> fieldMap) {
        if (CollUtil.isEmpty(relatedFieldConditions)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for(RelatedFieldCondition e:relatedFieldConditions){
            ShuJuJMXZDDto field = fieldMap.get(e.getShiTuID().toLowerCase()+"|"+e.getZiDuanBM().toLowerCase());
            if(Objects.isNull(field)){
                log.error("根据{},{}获取条件字段信息失败", e.getShiTuID(),e.getZiDuanBM());
                return null;
            }
            List<String> valList = new ArrayList<>();
            if (ShuJuZLXDMEnum.ZIDIAN.getType().equals(field.getShuJuZLXDM()) || ShuJuZLXDMEnum.ENUM.getType().equals(field.getShuJuZLXDM())) {
                valList.addAll(Arrays.stream(StrUtil.nullToDefault(e.getValues(), "").replaceAll("'", "''").split(",")).filter(StringUtils::isNoneBlank).toList());
            } else {
                valList.add(e.getValues());
            }
            SQLQueryObject obj = toSQLQueryObject(e.getOperator(), field, valList, e.getRelatedFangAnQueryCondition());
            if (obj.getText() == null) {
                continue;
            }
            builder.append(obj.getText());
            builder.append(" AND ");
        }

        return builder.toString();
    }

    /**
     * 获取表别名
     *
     * @param tableList       表
     * @param fangAnLXDM      方案类型代码
     * @param jiChuBiaoXXList 基础表信息
     * @return Map
     */
    private Map<String, Tuple.Tuple4<String, String, Boolean, Boolean>> getAliasMap(List<TableDTO> tableList, String fangAnLXDM, List<ShuJuXXMSRso> jiChuBiaoXXList) {
        //Tuple4中T1：别名，T2: 表关联，T3:是否外连表，T4:是否基础表,LinkedHashMap有序,CaseInsensitiveMap不区分大小写
        Map<String, Tuple.Tuple4<String, String, Boolean, Boolean>> aliasMap = new LinkedHashMap<>();
        //基础表
        SchemaTable jiChuBiao = new SchemaTable();
        //区分方案类型，可根据方案类型对应基础表
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
            default:
                break;
        }
        ShuJuXXMSRso jiBenXX = jiChuBiaoXXList.stream().filter(p -> p.getBiaoMing().equals("BR_DA_JIBENXX")).findFirst().orElse(new ShuJuXXMSRso());

        //根据条件中涉及的表或视图设置表别名
        for (int i = 0; i < tableList.size(); i++) {
            TableDTO table = tableList.get(i);
            if (CollUtil.isEmpty(table.getSchemaTableList())) {
                continue;
            }
            //获取模式和表名拼接后的表名
            String tableName = StringUtils.join(table.getSchemaTableList().stream().map(p -> formatBiaoMing("", p.getMoShi(), p.getBiaoMing())).collect(Collectors.toSet()), ",");
            //获取shiTuID，模式和表名拼接后的表名
            String shiTuBM=StringUtils.join(table.getSchemaTableList().stream().map(p -> formatBiaoMing(p.getShiTuID(), p.getMoShi(), p.getBiaoMing())).collect(Collectors.toSet()), ",");
            String shiTuBGX = getShiTuBGX(table, tableName);
            if(Objects.equals(shiTuBGX,formatBiaoMing("", jiBenXX.getShuJuYMC(), jiBenXX.getBiaoMing()))){
                continue;
            }
            aliasMap.put(shiTuBM, Tuple.of("t_" + i, shiTuBGX, false, tableName.equals(formatBiaoMing("", jiChuBiao.getMoShi(), jiChuBiao.getBiaoMing()))));
        }

        if (ObjectUtils.isNotEmpty(jiChuBiao) && !StringUtils.isBlank(jiChuBiao.getBiaoMing()) && !aliasMap.containsKey(formatBiaoMing("", jiChuBiao.getMoShi(), jiChuBiao.getBiaoMing())) && aliasMap.values().stream().noneMatch(Tuple.Tuple4::item4)) {
            aliasMap.put(formatBiaoMing("", jiChuBiao.getMoShi(), jiChuBiao.getBiaoMing()), Tuple.of("t_" + aliasMap.size(), formatBiaoMing("", jiChuBiao.getMoShi(), jiChuBiao.getBiaoMing()), false, true));
        }

        //添加基本信息表
        if (ObjectUtils.isNotEmpty(jiBenXX) && !StringUtils.isBlank(jiBenXX.getBiaoMing())) {
            aliasMap.put(formatBiaoMing("", jiBenXX.getShuJuYMC(), jiBenXX.getBiaoMing()), Tuple.of("t_" + aliasMap.size(), formatBiaoMing("", jiBenXX.getShuJuYMC(), jiBenXX.getBiaoMing()), false, false));
        }

        return aliasMap;
    }

    /**
     * 拼接表名
     *
     * @param aliasMap 表别名
     * @return String
     */
    private String getTable(Map<String, Tuple.Tuple4<String, String, Boolean, Boolean>> aliasMap) {
        List<String> tableNameList = ListUtil.toList();
        aliasMap.forEach((k, v) -> tableNameList.add(v.item2() + " " + v.item1()));

        return " " + CharSequenceUtil.join(",", tableNameList);
    }

    /**
     * 拼接表关联关系
     *
     * @param tableList       表
     * @param aliasMap        表别名
     * @param fangAnLXDM      方案类型代码
     * @param guanJianZi      关键字
     * @param jiChuBiaoXXList 基础表信息
     * @return 表关系sql
     */
    private String getTableJoinRelation(List<TableDTO> tableList, Map<String, Tuple.Tuple4<String, String, Boolean, Boolean>> aliasMap, String fangAnLXDM, String guanJianZi, List<ShuJuXXMSRso> jiChuBiaoXXList) {
        //获取基础表
        Tuple.Tuple4<String, String, Boolean, Boolean> jiChuTable = aliasMap.values().stream().filter(Tuple.Tuple4::item4).findFirst().orElse(null);
        //获取视图中表关系
        StringBuilder builder = new StringBuilder();

        if (Objects.isNull(jiChuTable)) {
            return CharSequenceUtil.replaceLast(builder.toString(), " AND ", " ");
        }
        Map<String, String> jiChuBGX = new HashMap<>(); //基础表关系 first:基础表字段 second:关联表字段，默认同种方案类型于基础表的关联关系一致
        //根据方案类型设置基础表与其它视图/表关系
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
        ShuJuXXMSRso jiBenXX = jiChuBiaoXXList.stream().filter(p -> p.getBiaoMing().equals("BR_DA_JIBENXX")).findFirst().orElse(new ShuJuXXMSRso());
        aliasMap.forEach((k, v) -> {
            //外连暂不考虑,基础表排除
            if (v.item3() || v.item1().equals(jiChuTable.item1())) {
                return;
            }
            //病人基本信息特殊处理
            if (ObjectUtils.isNotEmpty(jiBenXX) && !StringUtils.isBlank(jiBenXX.getBiaoMing()) && formatBiaoMing("", jiBenXX.getShuJuYMC(), jiBenXX.getBiaoMing()).equals(k)) {
                builder.append(jiChuTable.item1()).append(".").append("bingrenid");
                builder.append("=");
                builder.append(v.item1()).append(".").append("id");
                builder.append(" AND ");
                return;
            }
            jiChuBGX.forEach((k1, v1) -> {
                builder.append(jiChuTable.item1()).append(".").append(k1);
                builder.append("=");
                builder.append(v.item1()).append(".").append(v1);
                builder.append(" AND ");
            });
        });

        if (StringUtils.isBlank(guanJianZi)) {
            return CharSequenceUtil.replaceLast(builder.toString(), " AND ", " ");
        }
        //关键字拼接
        String guanJianZiPJ="ll0.jilunr like '%"+guanJianZi+"%'";
        if(Objects.equals(1,quanWenJSConfig.getState())){
            guanJianZiPJ="to_tsvector('zhcfg',ll0.jilunr) @@ to_tsquery('zhcfg','"+guanJianZi+"')";
        }
        //关键字查询条件
        builder.append(MessageFormat.format("exists(select 1 from (select zuhuid,bingrenid,binglijlid,jiuzhenid,''1'' as jiuzhenywlx,jilunr from {0} where zuofeibz=0 union " +
                "select zuhuid,bingrenid,binglijlid,zhuyuanjzid as jiuzhenid,''3'' as jiuzhenywlx,jilunr from {1} where zuofeibz=0) ll0 where {2} and {3}.bingrenid=ll0.bingrenid and {3}.zuhuid=ll0.zuhuid)",
                formatBiaoMingByBXX("BL_MZ_BINGLIJLTEXT", jiChuBiaoXXList),
                formatBiaoMingByBXX("BL_WS_JILUTEXT", jiChuBiaoXXList), guanJianZiPJ, jiChuTable.item1()));
        builder.append(" AND ");

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
                String key = formatBiaoMing(f.getShiTuID(), f.getMoShi(), f.getBiaoMing());
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
        String tenantId = identityService.getTenantId();

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
    private String getQueryFields(List<FangAnSC> fangAnSCList, Map<String, Tuple.Tuple4<String, String, Boolean, Boolean>> aliasMap, String fangAnLXDM) throws TongYongYWException {
        List<String> fields = ListUtil.toList();
        //增加基础表默认输出字段
        Tuple.Tuple4<String, String, Boolean, Boolean> jiChuTable = aliasMap.values().stream().filter(Tuple.Tuple4::item4).findFirst().orElse(null);
        String jiuZhenYWLX = "'' as jiuzhenywlx";
        String jiuZhenRQ = "";
        //根据方案类型区分jiuzhenywlx和jiuzhenrq
        switch (fangAnLXDM) {
            case "1": //门诊
                jiuZhenYWLX = "'1' as jiuzhenywlx";
                jiuZhenRQ = "jiuzhenrq";
                break;
            case "3": //住院
                jiuZhenYWLX = "'3' as jiuzhenywlx";
                jiuZhenRQ = "ruyuansj as jiuzhenrq";
                break;
            default:
                break;
        }
        if (!Objects.isNull(jiChuTable)) {
            fields.add(jiChuTable.item1() + ".id as jiuzhenid");
            if (StringUtil.hasText(jiuZhenYWLX)) {
                fields.add(jiuZhenYWLX);
            }
            if (StringUtil.hasText(jiuZhenRQ)) {
                fields.add(jiChuTable.item1() + "." + jiuZhenRQ);
            }
            fields.add(jiChuTable.item1() + ".zuzhijgid");
            fields.add(jiChuTable.item1() + ".bingrenid");
        }
        //主键添加
        Set<String> zhuJianList=new HashSet<>();
        //数据元
        for (FangAnSC e : Optional.ofNullable(fangAnSCList).orElse(new ArrayList<>())) {
            String key = "";
            String alias = "";
            switch (Optional.ofNullable(e.getZhiBiaoLXDM()).orElse("")) {
                case "2": //检验
                    key = aliasMap.keySet().stream().filter(p -> p.contains("jy_bg_baogaomx")).findFirst().orElse("");
                    alias = aliasMap.containsKey(key) ? aliasMap.get(key).item1() : "";
                    if(StringUtils.isBlank(alias)){
                        throw new TongYongYWException("请配置检验视图查询条件！");
                    }
                    fields.add(MessageFormat.format("(case when {0}.shiyanxmdm=''{1}'' and {0}.jianyanxmid=''{2}'' then concat({0}.shiyanjg,{0}.danwei) else '''' end) as {3}", alias, e.getZhiBiaoID(), e.getZhiBiaoFLID(), "zd_" + fangAnSCList.indexOf(e)));
                    break;
                case "3": //检查
                    key = aliasMap.keySet().stream().filter(p -> p.contains("jc_bg_baogaoxx")).findFirst().orElse("");
                    String shenQingBWKey = aliasMap.keySet().stream().filter(p -> p.contains("jc_sq_shenqingdbw")).findFirst().orElse("");
                    alias = aliasMap.containsKey(key) ? aliasMap.get(key).item1() : "";
                    if(StringUtils.isBlank(alias)){
                        throw new TongYongYWException("请配置检查视图查询条件！");
                    }
                    String shenQingBWAlias = aliasMap.containsKey(shenQingBWKey) ? aliasMap.get(shenQingBWKey).item1() : "";
                    fields.add(MessageFormat.format("(case when {0}.jianchabwid=''{1}'' and {0}.jianchaxmid=''{4}'' then {2}.zhenduanjg else '''' end) as {3}", shenQingBWAlias, e.getZhiBiaoID(), alias, "zd_" + fangAnSCList.indexOf(e),e.getZhiBiaoFLID()));
                    break;
                case "4": //药品
                    //根据方案类型代码，区分医嘱信息对应的表和取值逻辑
                    switch (fangAnLXDM) {
                        case "1": //门诊
                            key = aliasMap.keySet().stream().filter(p -> p.contains("yz_mz_yizhuxx")).findFirst().orElse("");
                            alias = aliasMap.containsKey(key) ? aliasMap.get(key).item1() : "";
                            if(StringUtils.isBlank(alias)){
                                throw new TongYongYWException("请配置药品视图查询条件！");
                            }
                            fields.add(MessageFormat.format("(case when {0}.guigeid=''{1}'' then concat({0}.yicijl,{0}.yicijldw) else '''' end) as {2}", alias, e.getZhiBiaoID(), "zd_" + fangAnSCList.indexOf(e)));
                            break;
                        case "3": //住院
                            key = aliasMap.keySet().stream().filter(p -> p.contains("yz_zy_yizhuxx")).findFirst().orElse("");
                            alias = aliasMap.containsKey(key) ? aliasMap.get(key).item1() : "";
                            if(StringUtils.isBlank(alias)){
                                throw new TongYongYWException("请配置药品视图查询条件！");
                            }
                            fields.add(MessageFormat.format("(case when {0}.guigeid=''{1}'' then concat({0}.yicijl,{0}.yicijldw) else '''' end) as {2}", alias, e.getZhiBiaoID(), "zd_" + fangAnSCList.indexOf(e)));
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    //优先单表匹配
                    key = aliasMap.keySet().stream().filter(p -> p.equals(formatBiaoMing(e.getZhiBiaoFLID(), e.getMoShi(), e.getBiaoMing()))).findFirst().orElse("");
                    //如果key为空且为BR_DA_JIBENXX则去基本信息表中字段
                    if (StringUtils.isBlank(key) && Objects.equals(e.getBiaoMing().toLowerCase(), "br_da_jibenxx")) {
                        key = aliasMap.keySet().stream().filter(p -> p.equals(formatBiaoMing("", e.getMoShi(), e.getBiaoMing()))).findFirst().orElse("");
                    } else if (StringUtils.isBlank(key)) { //根据视图ID，模式和表名模糊匹配
                        key = aliasMap.keySet().stream().filter(p -> p.contains(formatBiaoMing(e.getZhiBiaoFLID(), e.getMoShi(), e.getBiaoMing()))).findFirst().orElse("");
                    }
                    alias = aliasMap.containsKey(key) ? aliasMap.get(key).item1() : "";
                    fields.add(alias + "." + e.getZhiBiaoID() + " as zd_" + fangAnSCList.indexOf(e));
                    break;
            }
            zhuJianList.add(alias + "." + "id as zj_" + alias);
        }
        fields.addAll(zhuJianList.stream().toList());
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
     *
     * @param moShi 模式
     * @param biaoMing 表名
     * @return String
     */
    private String formatBiaoMing(String shiTuID,String moShi, String biaoMing) {
        StringBuilder builder = new StringBuilder();
        if(Objects.equals("br_da_jibenxx",biaoMing.toLowerCase())){
            shiTuID="";
        }
        if (StringUtils.isNotBlank(shiTuID)) {
            builder.append(shiTuID).append(".");
        }
        if (StringUtils.isNotBlank(moShi)) {
            builder.append(moShi).append(".");
        }
        builder.append(biaoMing);
        return builder.toString().toLowerCase();
    }

    /**
     * 根据表信息拼接表
     *
     * @param biaoMing 表名
     * @param biaoXinXiList 表信息列表
     * @return String
     */
    private String formatBiaoMingByBXX(String biaoMing, List<ShuJuXXMSRso> biaoXinXiList) {
        String moShi = biaoXinXiList.stream().filter(p -> p.getBiaoMing().equals(biaoMing.toUpperCase())).findFirst().orElse(new ShuJuXXMSRso()).getShuJuYMC();
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isNotBlank(moShi)) {
            builder.append(moShi).append(".");
        }
        builder.append(biaoMing);
        return builder.toString().toLowerCase();
    }

    /**
     * 获取视图表关系
     *
     * @param table     表信息
     * @param tableName 表组合名
     * @return String
     */
    private String getShiTuBGX(TableDTO table, String tableName) {
        StringBuilder builder = new StringBuilder();
        if (table.getSchemaTableList().size() <= 1) {
            builder.append(tableName);
            return builder.toString();
        }
        //字段名列表
        List<String> ziDuanMingList = new ArrayList<>();
        //表名列表
        Set<String> biaoMingList = new HashSet<>();
        //表结构中配置的关联方式如果为SQL关联则为非内联模式，关联方式为字段关联则为内联模式
        boolean isNeiLian = !"1".equals(table.getGuanLianFSDM());
        //获取视图中表关系
        String relationCondition = Optional.ofNullable(table.getTableRelationConditionList()).orElse("").replaceAll("\n"," ");
        if (!isNeiLian && StringUtils.isBlank(relationCondition)) {
            return builder.toString();
        }
        builder.append("(select ");
        //获取视图中过滤条件
        String filterCondition = Optional.ofNullable(table.getFilterConditionList()).orElse("").replaceAll("\n"," ");
        int index = 0;
        for (SchemaTable f : table.getSchemaTableList()) {
            //视图内联，不传shiTuID拼接
            String key = formatBiaoMing("", f.getMoShi(), f.getBiaoMing());
            for (ShuJuJMXZDDto p : Optional.ofNullable(f.getShuJuJMXZDDtos()).orElse(new ArrayList<>())) {
                ziDuanMingList.add(isNeiLian ? "l" + index + "." + p.getZiDuanBM() : f.getBiaoMing() + "." + p.getZiDuanBM());
            }
            if (!isNeiLian) {
                continue;
            }
            biaoMingList.add(key + " l" + index);
            relationCondition = relationCondition.replaceAll("(?i)" + key, "l" + index);
            filterCondition = filterCondition.replaceAll("(?i)" + key, "l" + index);
            index++;
        }
        builder.append(CollUtil.join(ziDuanMingList, ","));
        builder.append(" from ");
        builder.append(isNeiLian ? CollUtil.join(biaoMingList, ",") : relationCondition);
        builder.append(" where 1=1 ");
        if (StringUtils.isNotBlank(relationCondition) && isNeiLian) {
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

    /**
     * 获取病历查询SQL
     *
     * @param chaXunSQL 查询sql
     * @param guanJianZi 关键字
     * @return String
     * @throws YuanChengException
     */
    private String getBingRenJBXXSQL(String chaXunSQL, String guanJianZi) throws YuanChengException {
        //表信息
        List<ShuJuXXMSRso> biaoXinXiList = gongYongRemoteService.getShuJXXMS(ListUtil.toList("SC_LC_BINGRENYLSJ", "BL_MZ_BINGLIJLTEXT", "BL_WS_JILUTEXT", "JZ_MZ_JIUZHENXX", "JZ_ZY_JIUZHENXX")).getData("获取基础信息表模式信息失败");
        //组合病历查询sql
        StringBuilder builder = new StringBuilder();
        builder.append(MessageFormat.format("select a.bingrenid,a.xingming,a.xingbiedm,a.xingbiemc,a.chushengrq,a.menzhencs,a.zhuyuancs,b.jiuzhenywid,b.jiuzhenywlxdm,b.zuzhijgid,b.zuzhijgmc,b.jiuzhenrq,b.jiuzhenksid,b.jiuzhenksmc,b.binglijlid from {0} a inner join (select c1.zuhuid,c1.bingrenid,c1.id as binglijlid,c1.jiuzhenid as jiuzhenywid,''1'' as jiuzhenywlxdm,c1.zuzhijgid,c1.zuzhijgmc,c1.jilunr,c2.jiuzhenrq,c2.jiuzhenksid,c2.jiuzhenksmc from {1} c1 inner join {3} c2 on c1.zuzhijgid =c2.zuzhijgid and c1.jiuzhenid=c2.id and c1.zuhuid=c2.zuhuid and c1.zuofeibz=0 and c2.zuofeibz=0 union select c3.zuhuid,c3.bingrenid,c3.id as binglijlid,c3.zhuyuanjzid as jiuzhenywid,''3'' as jiuzhenywlxdm,c3.zuzhijgid,c3.zuzhijgmc,c3.jilunr,c4.ruyuansj as jiuzhenrq,c4.dangqianksid as jiuzhenksid,c4.dangqianksmc as jiuzhenksmc from {2} c3 inner join {4} c4 on c3.zuzhijgid =c3.zuzhijgid and c3.zhuyuanjzid =c4.id and c3.zuhuid=c4.zuhuid and c3.zuofeibz=0 and c4.zuofeibz=0) b " +
                        "on a.bingrenid=b.bingrenid and a.zuhuid=b.zuhuid where a.zuofeibz=0 and a.zuhuid=''{5}'' and ",
                formatBiaoMingByBXX("SC_LC_BINGRENYLSJ", biaoXinXiList), formatBiaoMingByBXX("BL_MZ_BINGLIJLTEXT", biaoXinXiList), formatBiaoMingByBXX("BL_WS_JILUTEXT", biaoXinXiList), formatBiaoMingByBXX("JZ_MZ_JIUZHENXX", biaoXinXiList), formatBiaoMingByBXX("JZ_ZY_JIUZHENXX", biaoXinXiList),identityService.getTenantId()));
        if(StringUtils.isBlank(chaXunSQL)&&Objects.equals(1,quanWenJSConfig.getState())){
            builder.append(" to_tsvector('zhcfg',b.jilunr) @@ to_tsquery('zhcfg','").append(guanJianZi).append("')");
        }else if (StringUtils.isBlank(chaXunSQL)) {
            builder.append(" b.jilunr like '%").append(guanJianZi).append("%'");
        } else {
            builder.append(MessageFormat.format(" exists(select 1 from ({0}) ll0 where ll0.bingrenid=a.bingrenid)", chaXunSQL));
        }
        return builder.toString();
    }

    @Override
    public List<BingLiXQXXDto> getBingLiXQ(BingLiXQDto bingLiXQDto) throws YuanChengException {
        List<BingLiXQXXDto> bingLiXQXXDtoList = new ArrayList<>();

        //获取就诊信息
        List<ZhuYuanMZJZXXRso> jiuZhenXX = jiuZhenRemoteService.getZuYuanMZJZXX(
                BeanUtil.copyListProperties(bingLiXQDto.getBingLiJQXQList(), JiuZhenIDYWLXIDRso::new)).getData("获取住院门诊就诊信息失败");

        //获取
        List<JiuluTextRso> jiuluTextRsos = linChuangRemoteService.getZuYuanMZJZXX(bingLiXQDto).getData("获取病历text信息失败");

        jiuZhenXX.forEach(j -> {
            BingLiXQXXDto bingLiXQXXDto = new BingLiXQXXDto();
            bingLiXQXXDto.setKeShiID(j.getJiuZhenKSID());
            bingLiXQXXDto.setKeShiMC(j.getJiuZhenKSMC());
            bingLiXQXXDto.setJiuZhenJS(j.getJiuZhenJS());
            bingLiXQXXDto.setZhuZhiJGMC(j.getZuZhiJGMC());
            bingLiXQXXDto.setZhuZhiJGID(j.getZuZhiJGID());
            bingLiXQXXDto.setJiuZhenYWLX(j.getJiuZhenYWLX());

            List<JiuluTextRso> jiluTest = jiuluTextRsos.stream().filter(n -> Objects.equals(n.getJiuZhenID(), j.getId()) && n.getJiuZhenYWLX().equals(j.getJiuZhenYWLX())).toList();
            bingLiXQXXDto.setJiuluTextDtoList(BeanUtil.copyToList(jiluTest, JiuluTextDto.class));
            bingLiXQXXDtoList.add(bingLiXQXXDto);
        });
        return bingLiXQXXDtoList;

    }
}

package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.constant.ZhuSuoYCZLXEnum;
import cn.mediinfo.grus.shujuzx.constant.ZhuSuoYHBZTConstant;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs.*;
import cn.mediinfo.grus.shujuzx.dto.YinSiGZSZs.SC_ZD_YinSiPZOutDto;
import cn.mediinfo.grus.shujuzx.model.*;
import cn.mediinfo.grus.shujuzx.repository.*;
import cn.mediinfo.grus.shujuzx.service.YinSiGZSZService;
import cn.mediinfo.grus.shujuzx.service.ZhuSuoYCZRZService;
import cn.mediinfo.grus.shujuzx.service.ZhuSuoYGLService;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.lyra.service.LyraIdentityService;
import cn.mediinfo.starter.base.util.*;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.print.DocFlavor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 主索引管理
 */
@Service
public class ZhuSuoYGLServiceImpl implements ZhuSuoYGLService {
    public final BR_DA_XiangSiSYRepository brDaXiangSiSYRepository;
    public final BR_DA_JiaoChaSYRepository brDaJiaoChaSYRepository;
    public final BR_DA_JiBenXXRepository brDaJiBenXXRepository;
    public final BR_DA_HeBingJLRepository brDaHeBingJLRepository;
    public final BR_DA_JieZhiXXRepository brDaJieZhiXXRepository;
    public final BR_DA_KuoZhanXXRepository brDaKuoZhanXXRepository;
    public final BR_ZD_HeBingGZRepository brZdHeBingGZRepository;
    public final BR_ZD_HeBingGZMXRepository brZdHeBingGZMXRepository;
    public final YinSiGZSZService yinSiGZSZService;
    public final ZhuSuoYCZRZService zhuSuoYCZRZService;
    private final LyraIdentityService lyraIdentityService;
    @PersistenceContext
    private final EntityManager entityManager;

    public ZhuSuoYGLServiceImpl(BR_DA_XiangSiSYRepository brDaXiangSiSYRepository, BR_DA_JiaoChaSYRepository brDaJiaoChaSYRepository, BR_DA_JiBenXXRepository brDaJiBenXXRepository, BR_DA_HeBingJLRepository brDaHeBingJLRepository, BR_DA_JieZhiXXRepository brDaJieZhiXXRepository, BR_DA_KuoZhanXXRepository brDaKuoZhanXXRepository, BR_ZD_HeBingGZRepository brZdHeBingGZRepository, BR_ZD_HeBingGZMXRepository brZdHeBingGZMXRepository, YinSiGZSZService yinSiGZSZService, ZhuSuoYCZRZService zhuSuoYCZRZService, LyraIdentityService lyraIdentityService, EntityManager entityManager) {
        this.brDaXiangSiSYRepository = brDaXiangSiSYRepository;
        this.brDaJiaoChaSYRepository = brDaJiaoChaSYRepository;
        this.brDaJiBenXXRepository = brDaJiBenXXRepository;
        this.brDaHeBingJLRepository = brDaHeBingJLRepository;
        this.brDaJieZhiXXRepository = brDaJieZhiXXRepository;
        this.brDaKuoZhanXXRepository = brDaKuoZhanXXRepository;
        this.brZdHeBingGZRepository = brZdHeBingGZRepository;
        this.brZdHeBingGZMXRepository = brZdHeBingGZMXRepository;
        this.yinSiGZSZService = yinSiGZSZService;
        this.zhuSuoYCZRZService = zhuSuoYCZRZService;
        this.lyraIdentityService = lyraIdentityService;
        this.entityManager = entityManager;
    }

    /**
     * 获取主索引的相似索引信息
     *
     * @return BR_DA_JiBenXXByZSYGLDto
     * @throws TongYongYWException 通用异常
     */
    @Override
    public List<BR_DA_JiBenXXByZSYGLDto> getXiangSiSYList(String zhuSuoYBRID) throws TongYongYWException {
        var qModel = QBR_DA_XiangSiSYModel.bR_DA_XiangSiSYModel;
        var xiangSiBRIDList = new JPAQueryFactory(entityManager).select(
                        Projections.bean(BR_DA_XiangSiSYModel.class, qModel.bingRenID2, qModel.xiangSiDu, qModel.bingRenID1))
                .from(qModel)
                .where(qModel.bingRenID1.eq(zhuSuoYBRID).and(qModel.huLueBZ.eq(0)))
                .fetch();
        //获取交叉病人Id列表
        var jiaoChaSYList = brDaJiaoChaSYRepository.findByZhuBingRID(zhuSuoYBRID);
        //相似集合
        var bingRenIDs = xiangSiBRIDList.stream().map(BR_DA_XiangSiSYModel::getBingRenID2).toList();
        var jiaoChaBRIDs = jiaoChaSYList.stream().map(BR_DA_JiaoChaSYModel::getGuanLianBRID).collect(Collectors.toList());
        jiaoChaBRIDs.addAll(bingRenIDs);
        var xiangSiBRXXList = MapUtils.copyListProperties(brDaJiBenXXRepository.findAllById(jiaoChaBRIDs), BR_DA_JiBenXXByZSYGLDto::new);
        var xiangSIBRIDs = xiangSiBRXXList.stream().map(BR_DA_JiBenXXByZSYGLDto::getId).toList();
        var jiaoChaSYCounts = brDaJiaoChaSYRepository.getJiaoChaZhuBRIDList(xiangSIBRIDs);
        //合并记录
        var heBingJLList = brDaHeBingJLRepository.findByBingRenIDIn(xiangSIBRIDs);
        for (var item : xiangSiBRXXList) {
            var xiangSiSY = xiangSiBRIDList.stream().filter(x -> Objects.equals(x.getBingRenID2(), item.getId())).findFirst().orElse(null);
            var jiaoChaSY = jiaoChaSYList.stream().filter(x -> Objects.equals(x.getGuanLianBRID(), item.getId())).findFirst().orElse(null);
            var heBingJL = heBingJLList.stream().filter(x -> Objects.equals(x.getBingRenID(), item.getId())).findFirst().orElse(null);
            if (heBingJL != null) {
                item.setHeBingZTDM(heBingJL.getHeBingZTDM());
                item.setHeBingZTMC(heBingJL.getHeBingZTMC());
            }
            item.setHeBingBZ(jiaoChaSY == null ? 0 : 1);
            if (xiangSiSY != null) {
                item.setXiangSiDu(xiangSiSY.getXiangSiDu());
            }
            if (jiaoChaSY != null) {
                item.setZiDongHBBZ(jiaoChaSY.getZiDongHBBZ());
                item.setHeBingRXM(jiaoChaSY.getHeBingRXM());
                item.setHeBingSJ(jiaoChaSY.getChuangJianSJ());
            }
            item.setNianLing(getNianLing(item.getChuShengRQ()));
            item.setZhuSuoYBRID(zhuSuoYBRID);
            item.setJiaoChaSYCount((int) jiaoChaSYCounts.stream().filter(x -> Objects.equals(x, item.getId())).count());
            item.setXianZhuZXX(StringUtil.concat(item.getXianZhuZXX(), item.getXianZhuZXZMC(), item.getXianZhuZCJMC(), item.getXianZhuZQTXX()));
        }
        return xiangSiBRXXList.stream().sorted(Comparator.comparing(BR_DA_JiBenXXByZSYGLDto::getHeBingBZ).reversed()
                        .thenComparing(Comparator.comparing(BR_DA_JiBenXXByZSYGLDto::getHeBingSJ).reversed())
                        .thenComparing(Comparator.comparing(BR_DA_JiBenXXByZSYGLDto::getXiangSiDu).reversed())
                        .thenComparing(Comparator.comparing(BR_DA_JiBenXXByZSYGLDto::getJiaoChaSYCount).reversed()))
                .collect(Collectors.toList());
    }

    /**
     * 获取主索引详情
     *
     * @return ZhuSuoYXQDto
     * @throws TongYongYWException 通用异常
     */
    @Override
    public ZhuSuoYXQDto getZhuSuoYXQ(String bingRenID, String chaXunMSDM) throws TongYongYWException, ParseException, NoSuchFieldException, IllegalAccessException {
        if (!StringUtil.hasText(chaXunMSDM)) {
            chaXunMSDM = "1";
        }
        var bingRenXX = brDaJiBenXXRepository.findById(bingRenID).orElse(null);
        if (bingRenXX == null) {
            throw new TongYongYWException("未找到相关病人信息");
        }
        List<SC_ZD_YinSiPZOutDto> yinSiGZPZ = yinSiGZSZService.getYinSiGZPZList(chaXunMSDM, lyraIdentityService.getJiGouID());
        //获取交叉索引信息列表
        var jiaoChaModels = brDaJiaoChaSYRepository.findByZhuBingRID(bingRenID);
        var xiangSiBRIDList = jiaoChaModels.stream().map(BR_DA_JiaoChaSYModel::getGuanLianBRID).toList();
        var xiangSiBRList = MapUtils.copyListProperties(brDaJiBenXXRepository.findAllById(xiangSiBRIDList), BR_DA_JiBenXXByZSYXQDto::new);
        //介质信息列表
        var jieZhiXXModels = brDaJieZhiXXRepository.findByBingRenIDOrBingRenIDIn(bingRenID, xiangSiBRIDList);
        List<JieZhiXXDto> jieZhiXXList = MapUtils.copyListProperties(jieZhiXXModels, JieZhiXXDto::new);
        var result = new ZhuSuoYXQDto();
        result.setBingRenXXList(new ArrayList<>());
        result.getBingRenXXList().add(MapUtils.copyProperties(bingRenXX, BR_DA_JiBenXXByZSYXQDto::new));
        result.getBingRenXXList().addAll(xiangSiBRList);
        for (var item : result.getBingRenXXList()) {
            if (item.getJianDangSJ() == null) {
                item.setJianDangSJ(item.getChuangJianSJ());
            }
            item.setNianLing(getNianLing(item.getChuShengRQ()));
            if (!CollectionUtils.isEmpty(yinSiGZPZ)) {
                MapUtils.mergeProperties(getYinShiSJ(item, yinSiGZPZ), item, true);
            }
        }
        for (var jieZhi : jieZhiXXList) {
            if (!StringUtil.hasText(jieZhi.getDiSanFBRID())) {
                jieZhi.setDiSanFBRID(jieZhi.getBingRenID());
            }
        }
        result.setGuanLianYXXList(jieZhiXXList);
        return result;
    }

    /**
     * 获取主索引管理数量
     *
     * @return Integer
     * @throws TongYongYWException 通用异常
     */
    @Override
    public Integer getZhuSuoYinCount(Date kaiShiSJ, Date jieShuSJ, Integer xiangSiDu, String MPI, String xingMing, String lianXiDH, String shenFenZH) throws TongYongYWException, ParseException {
        var qJiBenXX = QBR_DA_JiBenXXModel.bR_DA_JiBenXXModel;
        var qHeBingJL = QBR_DA_HeBingJLModel.bR_DA_HeBingJLModel;
        var count = new JPAQueryFactory(entityManager).select(qJiBenXX.count())
                .leftJoin(qHeBingJL)
                .on(qJiBenXX.id.eq(qHeBingJL.bingRenID))
                .where(QueryDSLUtils.whereIf(!ObjectUtils.isEmpty(kaiShiSJ), qJiBenXX.jianDangSJ.goe(kaiShiSJ)))
                .where(QueryDSLUtils.whereIf(!ObjectUtils.isEmpty(jieShuSJ), qJiBenXX.jianDangSJ.loe(jieShuSJ)))
                .where(QueryDSLUtils.whereIfHasText(MPI, qJiBenXX.id.contains(MPI)))
                .where(QueryDSLUtils.whereIfHasText(xingMing, qJiBenXX.xingMing.contains(xingMing)))
                .where(QueryDSLUtils.whereIfHasText(lianXiDH, qJiBenXX.lianXiDH.contains(lianXiDH)))
                .where(QueryDSLUtils.whereIfHasText(shenFenZH, qJiBenXX.zhengJianHM.contains(shenFenZH)))
                .where(qHeBingJL.heBingZTDM.ne(ZhuSuoYHBZTConstant.HEBINGZTMC_WHB).or(qHeBingJL.heBingZTDM.isNull()))
                .fetchOne();
        if (count != null) {
            return count.intValue();
        }
        return 0;
    }

    /**
     * 获取主索引管理列表
     *
     * @return List<BR_DA_JiBenXXByZSYGLDto>
     * @throws TongYongYWException 通用异常
     */
    @Override
    public List<BR_DA_JiBenXXByZSYGLDto> getZhuSuoYGLList(Integer pageIndex, Integer pageSize, Date kaiShiSJ, Date jieShuSJ, Integer xiangSiDu, String MPI, String xingMing, String lianXiDH, String shenFenZH, String jiuZhenKH) throws TongYongYWException {
        var qJiBenXX = QBR_DA_JiBenXXModel.bR_DA_JiBenXXModel;
        var qHeBingJL = QBR_DA_HeBingJLModel.bR_DA_HeBingJLModel;
        var result = new JPAQueryFactory(entityManager).select(
                        Projections.bean(BR_DA_JiBenXXByZSYGLDto.class, qJiBenXX,
                                qHeBingJL.heBingZTDM, qHeBingJL.heBingZTMC, qHeBingJL.zuiDaXSD, qHeBingJL.heBingShu, qHeBingJL.xiangSiShu))
                .leftJoin(qHeBingJL)
                .on(qJiBenXX.id.eq(qHeBingJL.bingRenID))
                .where(QueryDSLUtils.whereIf(!ObjectUtils.isEmpty(kaiShiSJ), qJiBenXX.jianDangSJ.goe(kaiShiSJ)))
                .where(QueryDSLUtils.whereIf(!ObjectUtils.isEmpty(jieShuSJ), qJiBenXX.jianDangSJ.loe(jieShuSJ)))
                .where(QueryDSLUtils.whereIfHasText(MPI, qJiBenXX.id.contains(MPI)))
                .where(QueryDSLUtils.whereIfHasText(xingMing, qJiBenXX.xingMing.contains(xingMing)))
                .where(QueryDSLUtils.whereIfHasText(lianXiDH, qJiBenXX.lianXiDH.contains(lianXiDH)))
                .where(QueryDSLUtils.whereIfHasText(shenFenZH, qJiBenXX.zhengJianHM.contains(shenFenZH)))
                .where(qHeBingJL.heBingZTDM.ne(ZhuSuoYHBZTConstant.HEBINGZTMC_WHB).or(qHeBingJL.heBingZTDM.isNull()))
                .orderBy(qJiBenXX.jianDangSJ.asc().nullsLast())
                .offset(PageRequestUtil.of(pageIndex, pageSize).getOffset())
                .limit(pageSize)
                .fetch();
        for (var item : result) {
            if (item.getHeBingZTDM() == null) {
                item.setHeBingZTDM(ZhuSuoYHBZTConstant.HEBINGZTDM_WHB);
            }
            if (item.getHeBingZTMC() == null) {
                item.setHeBingZTMC(ZhuSuoYHBZTConstant.HEBINGZTMC_WHB);
            }
            if (item.getZuiDaXSD() == null) {
                item.setZuiDaXSD(0);
            }
            if (item.getHeBingShu() == null) {
                item.setHeBingShu(0);
            }
            if (item.getXiangSiShu() == null) {
                item.setXiangSiShu(0);
            }
            item.setNianLing(getNianLing(item.getChuShengRQ()));
            item.setXianZhuZXX(StringUtil.concat(item.getXianZhuZXX(), item.getXianZhuZXZMC(), item.getXianZhuZCJMC(), item.getXianZhuZQTXX()));
        }
        return result;
    }

    /**
     * 合并页面获取主索引和相似索引信息
     * @return List<BR_DA_JiBenXXByHBXXDto>
     * @throws TongYongYWException 通用异常
     */
    @Override
    public List<BR_DA_JiBenXXByHBXXDto> getZhuSuoYXSList(String bingRenID, Integer xiangSiDu) throws TongYongYWException {
        //获取主索引信息
        var bingRenXX = brDaJiBenXXRepository.findById(bingRenID).orElse(null);
        if(bingRenXX == null){
            throw new TongYongYWException("未找到相关病人信息");
        }
        //获取相似索引病人Id
        var qXSBRModel = QBR_DA_XiangSiSYModel.bR_DA_XiangSiSYModel;
        var xiangSiBRs = new JPAQueryFactory(entityManager).select(Projections.bean(BR_DA_XiangSiSYModel.class,qXSBRModel.bingRenID2,qXSBRModel.xiangSiDu))
                            .from(qXSBRModel)
                .where(qXSBRModel.bingRenID1.eq(bingRenID).and(qXSBRModel.heBingBZ.eq(0)).and(qXSBRModel.huLueBZ.eq(0)))
                .where(QueryDSLUtils.whereIf(xiangSiDu>0,qXSBRModel.xiangSiDu.goe(xiangSiDu)))
                .fetch();
        var xiangSiBRIDs = xiangSiBRs.stream().map(BR_DA_XiangSiSYModel::getBingRenID2).toList();
        //获取相似索引病人信息
        var xiangSiBRList = MapUtils.copyListProperties(brDaJiBenXXRepository.findAllById(xiangSiBRIDs),BR_DA_JiBenXXByHBXXDto::new);
        List<BR_DA_JiBenXXByHBXXDto> result = new ArrayList<>();
        result.add(MapUtils.copyProperties(bingRenXX,BR_DA_JiBenXXByHBXXDto::new));
        //获取介质信息
        var bingRenIDs= result.stream().map(BR_DA_JiBenXXByHBXXDto::getId).toList();
        var jieZhiXXList = MapUtils.copyListProperties(brDaJieZhiXXRepository.findByBingRenIDIn(bingRenIDs),BR_DA_JieZhiXXDto::new);
        //查询病人扩展信息
        var kuoZhanXXList =MapUtils.copyListProperties(brDaKuoZhanXXRepository.findByBingRenIDIn(bingRenIDs),BR_DA_KuoZhanXXDto::new);
        for(var item :xiangSiBRList){
            var firstXSBR = xiangSiBRs.stream().filter(o-> Objects.equals(o.getBingRenID2(), item.getId())).findFirst().orElse(null);
            item.setXiangSiDu(firstXSBR!=null ? firstXSBR.getXiangSiDu(): BigDecimal.ZERO);
            item.setJieZhiXX(jieZhiXXList.stream().filter(o-> Objects.equals(o.getBingRenID(), item.getId())).findFirst().orElse(null));
            item.setKuoZhanXX(kuoZhanXXList.stream().filter(o-> Objects.equals(o.getBingRenID(), item.getId()) && "YouHuiLB".equals(o.getXiangMuDM())).findFirst().orElse(null));
            var nianLing = getNianLing(item.getChuShengRQ());
            var nianLings = nianLing.split(" ");
            if(nianLings.length>=2){
                item.setNianLing(nianLings[0]);
                item.setNianLingDW(nianLings[1]);
            }
            if(nianLings.length>=4){
                item.setNianLing1(nianLings[2]);
                item.setNianLingDW1(nianLings[3]);
            }
        }
        result.addAll(xiangSiBRList);
        return result;
    }

    /**
     * 合并
     * @return Boolean
     * @throws TongYongYWException 通用异常
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean heBing(SaveHeBingDto dto) throws TongYongYWException {
        if (CollectionUtils.isEmpty(dto.getXiangSiBRIDList()))
        {
            throw new TongYongYWException("未选中相似索引");
        }
        //修改主索引病人信息
        var bingRenXX = brDaJiBenXXRepository.findById(dto.getZhuSuoYBRXX().getID()).orElse(null);
        if(bingRenXX == null){
            throw new TongYongYWException("未找到相关病人信息");
        }
        MapUtils.mergeProperties(dto.getZhuSuoYBRXX(),bingRenXX);
        //获取主索引的合并记录
        var zhuSuoYHBJL = brDaHeBingJLRepository.findFirstByBingRenID(dto.getZhuSuoYBRXX().getID());
        if(zhuSuoYHBJL == null){
            throw new TongYongYWException("未找到相关病人合并记录信息");
        }
        zhuSuoYHBJL.setHeBingZTDM(ZhuSuoYHBZTConstant.HEBINGZTDM_HB);
        zhuSuoYHBJL.setHeBingZTMC(ZhuSuoYHBZTConstant.HEBINGZTMC_HB);
        if(ObjectUtils.isEmpty(zhuSuoYHBJL.getHeBingShu())){
            zhuSuoYHBJL.setHeBingShu(dto.getXiangSiBRIDList().size());
        }else {
            zhuSuoYHBJL.setHeBingShu(zhuSuoYHBJL.getHeBingShu() + dto.getXiangSiBRIDList().size());
        }
        if(zhuSuoYHBJL.getXiangSiShu()!=null) {
            zhuSuoYHBJL.setXiangSiShu(zhuSuoYHBJL.getXiangSiShu() - dto.getXiangSiBRIDList().size());
        }
        brDaHeBingJLRepository.save(zhuSuoYHBJL);
        //修改相似索引
        var xiangSiSYList = brDaXiangSiSYRepository.getXiangSiSYList(bingRenXX.getId(),dto.getXiangSiBRIDList());
        xiangSiSYList.forEach(o->o.setHeBingBZ(1));
        brDaXiangSiSYRepository.saveAll(xiangSiSYList);
        //修改相似索引病人信息
        var xiangSiBRList = brDaJiBenXXRepository.findAllById(dto.getXiangSiBRIDList());
        //获取相似病人合并记录信息
        var xiangSiBRHBJLList = brDaHeBingJLRepository.findByBingRenIDIn(dto.getXiangSiBRIDList());
        for (var item : xiangSiBRList){
            var xiangSiBRHBJL = xiangSiBRHBJLList.stream().filter(o-> Objects.equals(o.getBingRenID(), item.getId())).findFirst().orElse(null);
            if (xiangSiBRHBJL != null){
                xiangSiBRHBJL.setHeBingZTDM(ZhuSuoYHBZTConstant.HEBINGZTDM_BHB);
                xiangSiBRHBJL.setHeBingZTMC(ZhuSuoYHBZTConstant.HEBINGZTMC_BHB);
                if(xiangSiBRHBJL.getHeBingShu() == null){
                    xiangSiBRHBJL.setHeBingShu(1);
                }else {
                    xiangSiBRHBJL.setHeBingShu(xiangSiBRHBJL.getHeBingShu() - 1);
                }
                if(xiangSiBRHBJL.getXiangSiShu()!=null) {
                    xiangSiBRHBJL.setXiangSiShu(xiangSiBRHBJL.getXiangSiShu() - 1);
                }
                brDaHeBingJLRepository.save(xiangSiBRHBJL);
            }
            //添加操作日志
            var xiangSiSYModel = xiangSiSYList.stream().findFirst().orElse(null);
            String guiZheID = xiangSiSYModel!=null ? xiangSiSYModel.getGuiZeID():"";
            String xiangXiGZSM ="";
            if(StringUtil.hasText(guiZheID)){
                var guiZe = brZdHeBingGZRepository.findById(guiZheID).orElse(null);
                var guiZeMXs =  brZdHeBingGZMXRepository.findByGuiZeID(guiZheID);
                if(guiZe!=null && !CollectionUtils.isEmpty(guiZeMXs)){
                    xiangXiGZSM = String.format("，合并规则为：%s，阀值%s%%",
                            guiZeMXs.stream().map(x -> x.getMingCheng() + x.getQuanZhong() + "%").collect(Collectors.joining("，")),
                            guiZe.getFaZhi());
                }
            }
            String caoZuoNR = StringUtil.concat("主索引MPI：",dto.getZhuSuoYBRXX().getID(),"，已经合并MPI：",item.getId(),xiangXiGZSM);
            zhuSuoYCZRZService.addCaoZuoRZ(dto.getZhuSuoYBRXX().getID(),dto.getZhuSuoYBRXX().getXingMing(), ZhuSuoYCZLXEnum.HEBING,caoZuoNR,false);
        }
        //插入交叉索引
        var yuanJiaoCSYList = brDaJiaoChaSYRepository.getJiaoChaZhuBRIDList(dto.getXiangSiBRIDList());
        if (!CollectionUtils.isEmpty(yuanJiaoCSYList))
        {
            zhuSuoYHBJL.setHeBingShu((zhuSuoYHBJL.getHeBingShu()!=null?zhuSuoYHBJL.getHeBingShu():0) +yuanJiaoCSYList.size());
        }
        yuanJiaoCSYList.addAll(dto.getXiangSiBRIDList());
        var insertJiaoChaSYList = yuanJiaoCSYList.stream().distinct().map(o->{
            var model = new BR_DA_JiaoChaSYModel();
            model.setZuZhiJGID(ShuJuZXConstant.TONGYONG_JGID);
            model.setZuZhiJGMC(ShuJuZXConstant.TONGYONG_JGMC);
            model.setGuanLianBRID(o);
            model.setZiDongHBBZ(0);
            model.setZhuBingRID(bingRenXX.getId());
            model.setHeBingRID(lyraIdentityService.getYongHuId());
            model.setHeBingRXM(lyraIdentityService.getUserName());
            model.setHeBingSJ(new Date());
            return model;
        }).toList();
        brDaJiaoChaSYRepository.saveAll(insertJiaoChaSYList);
        //删除被合并患者的相似索引信息
        var deleteXiangSiSYList = brDaXiangSiSYRepository.getDeleteXiangSiSYList(dto.getZhuSuoYBRXX().getID(),dto.getXiangSiBRIDList());
        var deleteIDs = deleteXiangSiSYList.stream().map(BR_DA_XiangSiSYModel::getId).toList();
        brDaXiangSiSYRepository.softDelete(deleteIDs);
        //修改被删除相似索引患者的相似数
        var xiangSiHZHBJLList = brDaHeBingJLRepository.findByBingRenIDNotAndBingRenIDIn(dto.getZhuSuoYBRXX().getID(),deleteIDs);
        for (var item :xiangSiHZHBJLList){
            var count = deleteXiangSiSYList.stream()
                    .filter(o -> Objects.equals(o.getBingRenID1(), item.getBingRenID()))
                    .map(BR_DA_XiangSiSYModel::getBingRenID2)
                    .distinct().count();
            item.setXiangSiShu((item.getXiangSiShu()!=null?item.getXiangSiShu():0) - (int)count);
        }
        brDaHeBingJLRepository.saveAll(xiangSiHZHBJLList);
        return true;
    }

    /**
     * 忽略合并
     * @return Boolean
     * @throws TongYongYWException 通用异常
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean huLueHB(HuLueHBDto dto) throws TongYongYWException {
        //修改相似索引的忽略标志
        var xiangSISYList = brDaXiangSiSYRepository.getXiangSiSYList(dto.getZhuSuoYBRID(),List.of(dto.getXiangSiSYBRID()));
        xiangSISYList.forEach(o->o.setHuLueBZ(1));
        brDaXiangSiSYRepository.saveAll(xiangSISYList);
        //修改病人基本信息的相似数
        var zhuSuoYBRXX = brDaJiBenXXRepository.findById(dto.getZhuSuoYBRID()).orElse(null);
        if(zhuSuoYBRXX!=null){
            var zhuSuoYHBJL = brDaHeBingJLRepository.findFirstByBingRenID(dto.getZhuSuoYBRID());
            var xiangSiSY = brDaXiangSiSYRepository.findFirstByBingRenID1AndBingRenID2NotAndHuLueBZOrderByXiangSiDuDesc(zhuSuoYBRXX.getId(),dto.getXiangSiSYBRID(),0);
            if(zhuSuoYHBJL!=null){
                zhuSuoYHBJL.setXiangSiShu(zhuSuoYHBJL.getXiangSiShu()-1);
                zhuSuoYHBJL.setZuiDaXSD(xiangSiSY.getXiangSiDu()!=null?xiangSiSY.getXiangSiDu().intValue():0);
                brDaHeBingJLRepository.save(zhuSuoYHBJL);
            }
        }
        var xiangSiSYBRXX = brDaJiBenXXRepository.findById(dto.getXiangSiSYBRID()).orElse(null);
        if(xiangSiSYBRXX!=null){
            var xiangSiSYYHBJL = brDaHeBingJLRepository.findFirstByBingRenID(dto.getXiangSiSYBRID());
            var xiangSiSY = brDaXiangSiSYRepository.findFirstByBingRenID1AndBingRenID2NotAndHuLueBZOrderByXiangSiDuDesc(xiangSiSYBRXX.getId(),dto.getZhuSuoYBRID(),0);
            if(xiangSiSYYHBJL!=null){
                xiangSiSYYHBJL.setXiangSiShu(xiangSiSYYHBJL.getXiangSiShu()-1);
                xiangSiSYYHBJL.setZuiDaXSD(xiangSiSY.getXiangSiDu()!=null?xiangSiSY.getXiangSiDu().intValue():0);
                brDaHeBingJLRepository.save(xiangSiSYYHBJL);
            }
        }
        //添加操作日志
        String caoZuoNR = StringUtil.concat("主索引MPI：",dto.getZhuSuoYBRID(),",忽略合并MPI：",dto.getXiangSiSYBRID());
        zhuSuoYCZRZService.addCaoZuoRZ(dto.getZhuSuoYBRID(),zhuSuoYBRXX!=null?zhuSuoYBRXX.getXingMing():"", ZhuSuoYCZLXEnum.HULUE,caoZuoNR,false);
        return true;
    }

    /**
     * 主索引修改病人基本信息
     * @return Boolean
     * @throws TongYongYWException 通用异常
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateBingRenJBXX(BR_DA_JiBenXXCreateDto dto) throws TongYongYWException {
        var updateJiBenXX = brDaJiBenXXRepository.findById(dto.getId()).orElse(null);
        if(updateJiBenXX == null){
            throw new TongYongYWException("未找到病人信息");
        }
        //防病人信息被修改
        if(!StringUtil.hasText(dto.getZhengJianHM())){
            dto.setZhengJianHM(null);
        }
        if(!Objects.equals(updateJiBenXX.getXingMing(), dto.getXingMing()) && !Objects.equals(updateJiBenXX.getZhengJianHM(), dto.getZhengJianHM())){
            throw new TongYongYWException("不能同时修改姓名和证件号码");
        }
        MapUtils.mergeProperties(dto,updateJiBenXX);
        brDaJiBenXXRepository.save(updateJiBenXX);
        //添加操作日志
        String caoZuoNR = StringUtil.concat("主索引MPI：",updateJiBenXX.getId(),",修改基本信息");
        zhuSuoYCZRZService.addCaoZuoRZ(updateJiBenXX.getId(),updateJiBenXX.getXingMing(), ZhuSuoYCZLXEnum.XIUGAI,caoZuoNR,false);
        return dto.getId();
    }

    private String getNianLing(Date chuShengRQ) {
        if (ObjectUtils.isEmpty(chuShengRQ)) {
            return "";
        }
        var nianLing = new NianLing(chuShengRQ, new Date()).getAge();
        return nianLing.replaceAll("[\u4e00-\u9fa5]", " $0 ").trim();
    }

    private <T> T getYinShiSJ(T tModel, List<SC_ZD_YinSiPZOutDto> tuoMinPZList) throws NoSuchFieldException, IllegalAccessException {
        if (tModel == null || CollectionUtils.isEmpty(tuoMinPZList)) {
            return tModel;
        }
        for (var tuoMinPZ : tuoMinPZList) {
            Class<?> type = tModel.getClass(); // 获取类型
            Field propertyInfo = type.getDeclaredField(tuoMinPZ.getShuJuYLM()); // 获取属性
            propertyInfo.setAccessible(true);
            String t = propertyInfo.getType().getName();
            if (t.equals("java.lang.String")) {
                Object value = propertyInfo.get(tModel);
                if (ObjectUtils.isEmpty(value) || !StringUtil.hasText(value.toString())) {
                    continue;
                }
                if (Objects.equals(tuoMinPZ.getTuoMinFSDM(), "1")) {
                    //1-全部替换为所需字符，如 *
                    propertyInfo.set(tModel, (tuoMinPZ.getTuoMinGZ() != null ? tuoMinPZ.getTuoMinGZ() : "*").repeat(value.toString().length())); //给对应属性赋值
                } else if (Objects.equals(tuoMinPZ.getTuoMinFSDM(), "2")) {
                    //2-部分替换为设置字符：如：000***000，0为占位符
                    char[] charArray = value.toString().toCharArray();
                    int index = 1;
                    int j = 1;
                    String fuHao2 = "";
                    if (StringUtil.hasText(tuoMinPZ.getTuoMinGZ())) {
                        String[] tuoMinGZs = tuoMinPZ.getTuoMinGZ().split(",");
                        if (tuoMinGZs.length > 0) {
                            index = tuoMinGZs[0].isEmpty() ? 0 : Integer.parseInt(tuoMinGZs[0]);
                        }
                        if (tuoMinGZs.length > 1) {
                            j = tuoMinGZs[1].isEmpty() ? 0 : Integer.parseInt(tuoMinGZs[1]);
                        }
                        if (tuoMinGZs.length > 2) {
                            fuHao2 = tuoMinGZs[2].isEmpty() ? "*" : tuoMinGZs[2];
                        }
                    }
                    for (int i = index; i < index + j; i++) {
                        if (charArray.length - 1 < i) {
                            break;
                        }
                        charArray[i] = fuHao2.charAt(0);
                    }
                    propertyInfo.set(tModel, new String(charArray));//给对应属性赋值
                } else if (Objects.equals(tuoMinPZ.getTuoMinFSDM(), "3")) {
                    //3-正则替换
                    if (StringUtil.hasText(tuoMinPZ.getTuoMinGZ()) && tuoMinPZ.getTuoMinGZ().split(",").length > 1) {
                        Pattern pattern = Pattern.compile(tuoMinPZ.getTuoMinGZ().split(",")[0]);
                        String replaceWord = tuoMinPZ.getTuoMinGZ().split(",")[1];
                        value = pattern.matcher(value.toString()).replaceAll(replaceWord);
                    }
                    propertyInfo.set(tModel, value);//给对应属性赋值
                }
            } else {
                propertyInfo.set(tModel, null);
            }
        }
        return tModel;
    }
}

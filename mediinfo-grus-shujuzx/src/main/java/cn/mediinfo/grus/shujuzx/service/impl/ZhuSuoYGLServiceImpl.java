package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.constant.ZhuSuoYCZLXEnum;
import cn.mediinfo.grus.shujuzx.constant.ZhuSuoYHBZTConstant;
import cn.mediinfo.grus.shujuzx.constant.ZhuSuoYHBZTConstants;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs.*;
import cn.mediinfo.grus.shujuzx.dto.YinSiGZSZs.SC_ZD_YinSiPZOutDto;
import cn.mediinfo.grus.shujuzx.model.*;
import cn.mediinfo.grus.shujuzx.po.JiBenXXPO;
import cn.mediinfo.grus.shujuzx.po.bihuanlc.BiHUanPO;
import cn.mediinfo.grus.shujuzx.repository.*;
import cn.mediinfo.grus.shujuzx.service.YinSiGZSZService;
import cn.mediinfo.grus.shujuzx.service.ZhuSuoYCZRZService;
import cn.mediinfo.grus.shujuzx.service.ZhuSuoYGLService;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.lyra.service.LyraIdentityService;
import cn.mediinfo.starter.base.multitenancy.entity.StringMTEntity;
import cn.mediinfo.starter.base.util.*;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.aspectj.weaver.ast.Var;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.print.DocFlavor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 主索引管理
 */
@Service
@SuppressWarnings("all")
public class ZhuSuoYGLServiceImpl implements ZhuSuoYGLService {
    public final BR_DA_XiangSiSYRepository brDaXiangSiSYRepository;
    public final BR_DA_JiaoChaSYRepository brDaJiaoChaSYRepository;
    public final BR_DA_JiBenXXRepository brDaJiBenXXRepository;
    public final BR_DA_HeBingJLRepository brDaHeBingJLRepository;
    public final BR_DA_JieZhiXXRepository brDaJieZhiXXRepository;
    public final BR_DA_KuoZhanXXRepository brDaKuoZhanXXRepository;
    public final BR_ZD_HeBingGZRepository brZdHeBingGZRepository;
    public final BR_ZD_HeBingGZMXRepository brZdHeBingGZMXRepository;

    private final BR_DA_ZhuSuoYCZRZRepository zhuSuoYCZRZRepository;
    public final YinSiGZSZService yinSiGZSZService;
    public final ZhuSuoYCZRZService zhuSuoYCZRZService;
    private final LyraIdentityService lyraIdentityService;

    public ZhuSuoYGLServiceImpl(BR_DA_XiangSiSYRepository brDaXiangSiSYRepository, BR_DA_JiaoChaSYRepository brDaJiaoChaSYRepository, BR_DA_JiBenXXRepository brDaJiBenXXRepository, BR_DA_HeBingJLRepository brDaHeBingJLRepository, BR_DA_JieZhiXXRepository brDaJieZhiXXRepository, BR_DA_KuoZhanXXRepository brDaKuoZhanXXRepository, BR_ZD_HeBingGZRepository brZdHeBingGZRepository, BR_ZD_HeBingGZMXRepository brZdHeBingGZMXRepository, YinSiGZSZService yinSiGZSZService, ZhuSuoYCZRZService zhuSuoYCZRZService, LyraIdentityService lyraIdentityService,BR_DA_ZhuSuoYCZRZRepository zhuSuoYCZRZRepository) {
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
        this.zhuSuoYCZRZRepository = zhuSuoYCZRZRepository;
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
        var xiangSiBRIDList = new JPAQueryFactory(brDaXiangSiSYRepository.getEntityManager()).select(
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
        return xiangSiBRXXList.stream().sorted(Comparator.comparing(BR_DA_JiBenXXByZSYGLDto::getHeBingBZ,Comparator.nullsFirst(Integer::compareTo)).reversed()
                        .thenComparing(Comparator.comparing(BR_DA_JiBenXXByZSYGLDto::getHeBingSJ,Comparator.nullsFirst(Date::compareTo)).reversed())
                        .thenComparing(Comparator.comparing(BR_DA_JiBenXXByZSYGLDto::getXiangSiDu,Comparator.nullsFirst(BigDecimal::compareTo)).reversed())
                        .thenComparing(Comparator.comparing(BR_DA_JiBenXXByZSYGLDto::getJiaoChaSYCount,Comparator.nullsFirst(Integer::compareTo)).reversed()))
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
        var count = brDaJiBenXXRepository.asQuerydsl()
                .whereIf(!ObjectUtils.isEmpty(kaiShiSJ),o->o.jianDangSJ.goe(kaiShiSJ))
                .whereIf(!ObjectUtils.isEmpty(jieShuSJ),o->o.jianDangSJ.loe(jieShuSJ))
                .whereIf(StringUtils.hasText(MPI),o->o.id.contains(MPI))
                .whereIf(StringUtils.hasText(xingMing),o->o.xingMing.contains(xingMing))
                .whereIf(StringUtils.hasText(lianXiDH),o->o.lianXiDH.contains(lianXiDH))
                .whereIf(StringUtils.hasText(shenFenZH),o->o.zhengJianHM.contains(shenFenZH))
                .leftJoin(brDaHeBingJLRepository.asQuerydsl(),(c,d)->c.id.eq(d.bingRenID), JiBenXXPO::new)
                .where(o->o.getHeBingJLModel().heBingZTDM.ne(ZhuSuoYHBZTConstant.HEBINGZTDM_WHB).or(o.getHeBingJLModel().heBingZTDM.isNull()))
                .select(o->o.getJiBenXXModel().count())
                .fetchOne();
//        var count = new JPAQueryFactory(brDaJiBenXXRepository.getEntityManager()).select(qJiBenXX.count())
//                .from(qJiBenXX)
//                .leftJoin(qHeBingJL)
//                .on(qJiBenXX.id.eq(qHeBingJL.bingRenID))
//                .where(QueryDSLUtils.whereIf(!ObjectUtils.isEmpty(kaiShiSJ), qJiBenXX.jianDangSJ.goe(kaiShiSJ)))
//                .where(QueryDSLUtils.whereIf(!ObjectUtils.isEmpty(jieShuSJ), qJiBenXX.jianDangSJ.loe(jieShuSJ)))
//                .where(QueryDSLUtils.whereIf(StringUtils.hasText(MPI), qJiBenXX.id.contains(MPI)))
//                .where(QueryDSLUtils.whereIf(StringUtils.hasText(xingMing), qJiBenXX.xingMing.contains(xingMing)))
//                .where(QueryDSLUtils.whereIf(StringUtils.hasText(lianXiDH), qJiBenXX.lianXiDH.contains(lianXiDH)))
//                .where(QueryDSLUtils.whereIf(StringUtils.hasText(shenFenZH), qJiBenXX.zhengJianHM.contains(shenFenZH)))
//                .where(qHeBingJL.heBingZTDM.ne(ZhuSuoYHBZTConstant.HEBINGZTMC_WHB).or(qHeBingJL.heBingZTDM.isNull()))
//                .fetchOne();
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
//        var qJiBenXX = QBR_DA_JiBenXXModel.bR_DA_JiBenXXModel;
//        var qHeBingJL = QBR_DA_HeBingJLModel.bR_DA_HeBingJLModel;
        var result = brDaJiBenXXRepository.asQuerydsl()
                .whereIf(!ObjectUtils.isEmpty(kaiShiSJ),o->o.jianDangSJ.goe(kaiShiSJ))
                .whereIf(!ObjectUtils.isEmpty(jieShuSJ),o->o.jianDangSJ.loe(jieShuSJ))
                .whereIf(StringUtils.hasText(MPI), o->o.id.contains(MPI))
                .whereIf(StringUtils.hasText(xingMing), o->o.xingMing.contains(xingMing))
                .whereIf(StringUtils.hasText(lianXiDH), o->o.lianXiDH.contains(lianXiDH))
                .whereIf(StringUtils.hasText(shenFenZH), o->o.zhengJianHM.contains(shenFenZH))
                .leftJoin(brDaHeBingJLRepository.asQuerydsl(),(c,d)->c.id.eq(d.bingRenID), JiBenXXPO::new)
                .where(o->o.getHeBingJLModel().heBingZTDM.ne(ZhuSuoYHBZTConstant.HEBINGZTDM_BHB).or(o.getHeBingJLModel().heBingZTDM.isNull()))
                .whereIf(xiangSiDu!=null && xiangSiDu>0,o->o.getHeBingJLModel().zuiDaXSD.goe(xiangSiDu))
                .orderBy(o->o.getJiBenXXModel().jianDangSJ.asc().nullsLast())
                .select(c -> new Expression<?>[]{
                        c.getJiBenXXModel(),
                        c.getHeBingJLModel().heBingZTDM,
                        c.getHeBingJLModel().heBingZTMC,
                        c.getHeBingJLModel().zuiDaXSD,
                        c.getHeBingJLModel().heBingShu,
                        c.getHeBingJLModel().xiangSiShu
                }, BR_DA_JiBenXXByZSYGLDto.class)
                .fetchPage(pageIndex,pageSize);
//        var result = new JPAQueryFactory(brDaJiBenXXRepository.getEntityManager()).select(
//                        Projections.bean(BR_DA_JiBenXXByZSYGLDto.class, qJiBenXX,
//                                qHeBingJL.heBingZTDM, qHeBingJL.heBingZTMC, qHeBingJL.zuiDaXSD, qHeBingJL.heBingShu, qHeBingJL.xiangSiShu))
//                .from(qJiBenXX)
//                .leftJoin(qHeBingJL)
//                .on(qJiBenXX.id.eq(qHeBingJL.bingRenID))
//                .where(QueryDSLUtils.whereIf(!ObjectUtils.isEmpty(kaiShiSJ), qJiBenXX.jianDangSJ.goe(kaiShiSJ)))
//                .where(QueryDSLUtils.whereIf(!ObjectUtils.isEmpty(jieShuSJ), qJiBenXX.jianDangSJ.loe(jieShuSJ)))
//                .where(QueryDSLUtils.whereIfHasText(MPI, qJiBenXX.id.contains(MPI)))
//                .where(QueryDSLUtils.whereIfHasText(xingMing, qJiBenXX.xingMing.contains(xingMing)))
//                .where(QueryDSLUtils.whereIfHasText(lianXiDH, qJiBenXX.lianXiDH.contains(lianXiDH)))
//                .where(QueryDSLUtils.whereIfHasText(shenFenZH, qJiBenXX.zhengJianHM.contains(shenFenZH)))
//                .where(qHeBingJL.heBingZTDM.ne(ZhuSuoYHBZTConstant.HEBINGZTDM_BHB).or(qHeBingJL.heBingZTDM.isNull()))
//                .where(QueryDSLUtils.whereIf(xiangSiDu>0,qHeBingJL.zuiDaXSD.goe(xiangSiDu)))
//                .orderBy(qJiBenXX.jianDangSJ.asc().nullsLast())
//                .offset(PageRequestUtil.of(pageIndex, pageSize).getOffset())
//                .limit(pageSize)
//                .fetch();
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
        var xiangSiBRs = new JPAQueryFactory(brDaXiangSiSYRepository.getEntityManager()).select(Projections.bean(BR_DA_XiangSiSYModel.class,qXSBRModel.bingRenID2,qXSBRModel.xiangSiDu))
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
    //todo 修改
    /**
     * 取消合并
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean quXiaoHB(HuLueHBDto dto) throws TongYongYWException {

        //region  交叉索引变更(闭包表)
        //todo xiugai
        List<String> guanLianBRIds = brDaJiaoChaSYRepository.asQuerydsl()
                .where(x -> x.zhuBingRID.eq(dto.getXiangSiSYBRID()))
                .select(x -> x.guanLianBRID).fetch();
        guanLianBRIds.add(dto.getXiangSiSYBRID());
        QBR_DA_JiaoChaSYModel brDaJiaoChaSYModel = QBR_DA_JiaoChaSYModel.bR_DA_JiaoChaSYModel;
        List<BR_DA_JiaoChaSYModel> deteleJiaoChaSYList = new JPAQueryFactory(brDaJiaoChaSYRepository.getEntityManager())
                .select(brDaJiaoChaSYModel)
                .from(brDaJiaoChaSYModel)
                .where(brDaJiaoChaSYModel.guanLianBRID.in(guanLianBRIds).and(brDaJiaoChaSYModel.zhuBingRID.eq(dto.getZhuSuoYBRID())))
                .fetch();
        brDaJiaoChaSYRepository.softDelete(deteleJiaoChaSYList);
        //brDaJiaoChaSYRepository.softDelete(brDaJiaoChaSYModel.guanLianBRID.in(guanLianBRIds).and(brDaJiaoChaSYModel.zhuBingRID.eq(dto.getZhuSuoYBRID())));
        //endregion
        //region 修改病人基本信息
        //修改病人基本信息
        BR_DA_JiBenXXModel zhuBingRenXX = brDaJiBenXXRepository.findById(dto.getZhuSuoYBRID()).stream().findFirst().orElse(null);//todo
        if (zhuBingRenXX==null)
        {
            throw new TongYongYWException("未找到主病人信息");
        }

        BR_DA_HeBingJLModel zhuSuoYHBJL = brDaHeBingJLRepository.findFirstByBingRenID(dto.getZhuSuoYBRID());//todo
        zhuSuoYHBJL.setHeBingShu( zhuSuoYHBJL.getHeBingShu()-deteleJiaoChaSYList.size());
        if (brDaJiaoChaSYRepository.existsByZhuBingRIDAndGuanLianBRIDNot(dto.getZhuSuoYBRID(),dto.getXiangSiSYBRID()))
        {
          zhuSuoYHBJL.setHeBingZTDM("0");
          zhuSuoYHBJL.setHeBingZTMC("未合并");
        }
        BR_DA_JiBenXXModel xiangSiBRXX = brDaJiBenXXRepository.findById(dto.getXiangSiSYBRID()).stream().findFirst().orElse(null);//todo
        if (xiangSiBRXX==null)
        {
            throw new TongYongYWException("未找到相似病人信息");
        }
        BR_DA_HeBingJLModel xiangSiHBJL = brDaHeBingJLRepository.findFirstByBingRenID(dto.getXiangSiSYBRID());
        xiangSiHBJL.setHeBingShu(xiangSiHBJL.getHeBingShu() - 1);
        if(brDaJiaoChaSYRepository.existsByZhuBingRID(dto.getXiangSiSYBRID()))
        {
            xiangSiHBJL.setHeBingZTDM("1");//todo
            xiangSiHBJL.setHeBingZTMC("合并后主数据");
        }else {
            List<BR_DA_JiaoChaSYModel> xiangSiJCList = brDaJiaoChaSYRepository.findByGuanLianBRIDAndZhuBingRIDNot(dto.getXiangSiSYBRID(), dto.getZhuSuoYBRID());
            if (!CollectionUtils.isEmpty(xiangSiJCList))
            {
                //被合并患者被其他患者合并时，移除与其他患者的交叉索引和相似索引
                brDaJiaoChaSYRepository.softDelete(xiangSiJCList);
                QBR_DA_XiangSiSYModel brDaXiangSiSYModel = QBR_DA_XiangSiSYModel.bR_DA_XiangSiSYModel;
                //删除相似索引
                brDaXiangSiSYRepository.softDelete((brDaXiangSiSYModel.bingRenID1.eq(dto.getXiangSiSYBRID())
                        .and(brDaXiangSiSYModel.bingRenID2.in(xiangSiJCList.stream()
                                .map(BR_DA_JiaoChaSYModel::getZhuBingRID).toList())))
                        .or(brDaXiangSiSYModel.bingRenID2.eq(dto.getXiangSiSYBRID())
                                .and(brDaXiangSiSYModel.bingRenID1.in(xiangSiJCList.stream()
                                        .map(BR_DA_JiaoChaSYModel::getZhuBingRID).toList()))));

                List<BR_DA_HeBingJLModel> beiHeBXSLHBJList = brDaHeBingJLRepository.findByBingRenIDIn(xiangSiJCList.stream().map(BR_DA_JiaoChaSYModel::getZhuBingRID).toList());
                beiHeBXSLHBJList.forEach(x->{
                    x.setHeBingShu(x.getHeBingShu()-1);
                });
            }
            xiangSiHBJL.setHeBingZTDM("0");
            xiangSiHBJL.setHeBingZTMC("无合并");
        }
        //endregion
        //region 修改相似索引为忽略
        List<BR_DA_XiangSiSYModel> xiangSiSYList = brDaXiangSiSYRepository.asQuerydsl().where(x -> (x.bingRenID1.eq(dto.getZhuSuoYBRID())
                .and(x.bingRenID2.eq(dto.getXiangSiSYBRID())))
                .or(x.bingRenID2.eq(dto.getZhuSuoYBRID()).and(x.bingRenID1.eq(dto.getXiangSiSYBRID())))).fetch();

        xiangSiSYList.forEach(x->{
          x.setHeBingBZ(0);
          x.setHuLueBZ(1);
        });
                QBR_DA_XiangSiSYModel brDaXiangSiSYModel = QBR_DA_XiangSiSYModel.bR_DA_XiangSiSYModel;
                Map<Path<?>,Object> updateMap = new HashMap<>();
                updateMap.put(brDaXiangSiSYModel.heBingBZ,0);
                updateMap.put(brDaXiangSiSYModel.huLueBZ,1);
        brDaXiangSiSYRepository.update(updateMap,brDaXiangSiSYModel.id.in(xiangSiSYList.stream().map(BR_DA_XiangSiSYModel::getId).toList()));
      //  brDaXiangSiSYRepository.saveAll(xiangSiSYList);
        //endregion

        //添加操作日志
        zhuSuoYCZRZService.addCaoZuoRZ(dto.getZhuSuoYBRID(),
                zhuBingRenXX.getXingMing(),
                ZhuSuoYCZLXEnum.QUXIAOHB,
                StringUtil.concat("主索引MPI",dto.getZhuSuoYBRID(),",取消合并MPI：",dto.getXiangSiSYBRID(),"，取消合并理由：",dto.getQuXiaoHBLY()),
                false);

        //region 似索引重新计算
        ArrayList<BR_DA_JiBenXXModel> huanZheList = new ArrayList<>(){};
        huanZheList.add(zhuBingRenXX);
        huanZheList.add(xiangSiBRXX);
        zengLiangPPXSHZ_Start(huanZheList,false);
        //endregion
        return true;
    }
    //todo 缓存获取数据修改
    /**
     * 增量匹配相似患者（增量）
     * @return Boolean
     */
    @Override
    public String zengLiangPPXSHZ() {

        var jiGouID = "0";
        var jiGouMC = "通用";
        //获取筛选开始时间
        // var hasGengXinSJ = _cacheHelper.Exists("ZhuSuoYGL", "GengXinSJ");
        var hasGengXinSJ = false;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // 注意：Java 中月份从 0 开始计数，因此需要加上 1。
        int dayOfMonth = 1; // Java 中日期默认为当月的第一天，因此这里指定为 1 日。

         // 将日期调整为当月最后一天
        calendar.set(year, month, dayOfMonth);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date gengXinSJ = calendar.getTime();

//        if (hasGengXinSJ)
//        {
//            gengXinSJ = _cacheHelper.Get<DateTime>("ZhuSuoYGL", "GengXinSJ");
//        }
        var kaiShiSJ=hasGengXinSJ? gengXinSJ:brDaJiaoChaSYRepository.findFirstByZiDongHBBZOrderByChuangJianSJDesc(1).getChuangJianSJ();
        var result = "";
        //region 取更新的患者列表
        record RecordJiBenXXHeBingJL(QBR_DA_JiBenXXModel jiBenXX,QBR_DA_HeBingJLModel heBingJL) {
        }
        List<BR_DA_JiBenXXModel> huanZheList = brDaJiBenXXRepository.asQuerydsl().where(x -> x.xiuGaiSJ.after(kaiShiSJ))
                .leftJoin(brDaHeBingJLRepository.asQuerydsl(), (x, y) -> x.id.eq(y.bingRenID), RecordJiBenXXHeBingJL::new)
                .where(x -> x.heBingJL.heBingZTDM.ne(ZhuSuoYHBZTConstants.HeBingZTDM_BHB).or(x.heBingJL.heBingZTDM.isNull()))
                .orderBy(x -> x.jiBenXX.xiuGaiSJ.asc())
                .select(x -> x.jiBenXX).fetch().stream().limit(1001)
                .toList();
        //endregion
        try {
            if (!CollectionUtils.isEmpty(huanZheList))
            {
                var updateXiuGaiSJ=huanZheList.stream().findFirst().orElse(null).getXiuGaiSJ();
                if (huanZheList.size()==1001) {
                    //当患者列表的修改时间都一样时，重新取修改时间为这个时间的患者
                    if (huanZheList.get(0).getXiuGaiSJ().equals(huanZheList.get(1000).getXiuGaiSJ()))
                    {
                        huanZheList = brDaJiBenXXRepository.asQuerydsl()
                                .leftJoin(brDaHeBingJLRepository.asQuerydsl(), (x, y) -> x.id.eq(y.bingRenID), RecordJiBenXXHeBingJL::new)
                                .where(x -> x.heBingJL.heBingZTDM.ne(ZhuSuoYHBZTConstants.HeBingZTDM_BHB).or(x.heBingJL.heBingZTDM.isNull()))
                                .orderBy(x -> x.jiBenXX.xiuGaiSJ.desc())
                                .select(x -> x.jiBenXX).fetch().stream()
                                .toList();
                    }else{
                        //防止下一个患者的修改时间和取出的患者列表最后的修改时间一样
                        List<BR_DA_JiBenXXModel> finalHuanZheList = huanZheList;
                        huanZheList = huanZheList.stream().filter(x-> !x.getXiuGaiSJ().equals(finalHuanZheList.get(1000).getXiuGaiSJ())).toList();

                    }
                    zengLiangPPXSHZ_Start(huanZheList,true);
                    //   _cacheHelper.Set("ZhuSuoYGL", "GengXinSJ", updateXiuGaiSJ);
                }
            }
            result= StringUtil.concat("开始时间：",DateUtil.getYYMMDDHHmmss(kaiShiSJ),",本次处理修改的患者：",huanZheList.size(),"人");

        }catch (Exception e)
        {
//            _cacheHelper.Set("ZhuSuoYGL", "GengXinSJ", kaiShiSJ);
//            throw;
        }
        return result;
    }

    private void zengLiangPPXSHZ_Start(List<BR_DA_JiBenXXModel> huanZheList,Boolean ziDongHB) throws TongYongYWException
    {
        //region 获取合并规则
        //获取合并规则
        List<GuiZeListDto> guiZeList = brZdHeBingGZRepository.asQuerydsl()
                .where(x -> x.zuZhiJGID.eq("0")).select(GuiZeListDto.class).fetch();
        List<GuiZeListDto> finalGuiZeList = guiZeList;
        List<GuiZePZDto> guiZeMXList = brZdHeBingGZMXRepository.asQuerydsl()
                .where(m -> m.zuZhiJGID.eq("0")
                        .and(m.guiZeID.in(finalGuiZeList.stream().map(GuiZeListDto::getGuiZeID).toList())))
                .select(GuiZePZDto.class).fetch();
        for (var item:guiZeList)
        {

            item.setGuiZePZList(guiZeMXList.stream().filter(x->x.getGuiZeID().equals(item.getGuiZeID())).toList());
            item.setXiangSiDu(item.getFaZhi().multiply(item.getGuiZePZList().stream()
                    .map(GuiZePZDto::getQuanZhong).reduce(BigDecimal.ZERO, BigDecimal::add))
                    .divide(new BigDecimal(100)));
        }
        guiZeList = guiZeList.stream().filter(x -> !CollectionUtils.isEmpty(x.getGuiZePZList().stream().toList()))
                .sorted(Comparator.comparing(GuiZeListDto::getXiangSiDu).reversed()).toList();
        if (CollectionUtils.isEmpty(guiZeList))
        {
          throw new TongYongYWException("规则未配置");
        }
        //endregion
        //获取患者的相似患者列表
        List<BR_DA_JiBenXXModel> xiangSiHZList = getXiangSiHZList(huanZheList);
        //定义相似患者匹配入参
        List<String> xiangShiHZId = xiangSiHZList.stream().map(StringMTEntity::getId).toList();
        //定义相似患者匹配入参
        List<BR_DA_XiangSiSYModel> yuanXiangSiSYList = brDaXiangSiSYRepository.findByBingRenID1InOrBingRenID2In(xiangShiHZId, xiangShiHZId);

        List<BR_DA_JiaoChaSYModel> yuanJiaoChaSYList = brDaJiaoChaSYRepository.findByZhuBingRIDIn(xiangShiHZId);

        List<BR_DA_HeBingJLModel> heBingJLList = brDaHeBingJLRepository.findByBingRenIDIn(xiangShiHZId);

      //  List<BR_DA_HeBingJLModel> addHeBingJLList=new ArrayList<>();
        List<BR_DA_HeBingJLModel> addHeBingJLList = xiangSiHZList.stream()
                .filter(x -> !heBingJLList.stream().map(BR_DA_HeBingJLModel::getBingRenID).toList()
                        .contains(x.getId())).map(x -> {
            var heBingJL = new BR_DA_HeBingJLModel();
                    heBingJL.setHeBingShu(0);
                    heBingJL.setXiangSiShu(0);
                    heBingJL.setHeBingZTDM("0");
                    heBingJL.setHeBingZTMC("无合并");
                    heBingJL.setBingRenID(x.getId());
                    heBingJL.setZuiDaXSD(0);
                    heBingJL.setZuZhiJGID("0");
                    heBingJL.setZuZhiJGMC("通用");
            return heBingJL;
        }).toList();
        List<BR_DA_JiaoChaSYModel> addJiaoChaSYList=new ArrayList<>();
        List<BR_DA_XiangSiSYModel> addXiangSiSYList=new ArrayList<>();
        for (var bingRenXX:huanZheList)
        {
            if(addJiaoChaSYList.stream().anyMatch(x->x.getGuanLianBRID().equals(bingRenXX.getId())))
            {
                continue;
            }
            xiangSiPPByBRXX2(bingRenXX,xiangSiHZList,guiZeList,yuanXiangSiSYList,addXiangSiSYList,addJiaoChaSYList,yuanJiaoChaSYList,ziDongHB);
        }
        if (!CollectionUtils.isEmpty(addXiangSiSYList))
        {
            brDaXiangSiSYRepository.saveAll(addXiangSiSYList);

            List<String> deleteXiangSiSYIDs = yuanXiangSiSYList.stream()
                    .filter(a -> addXiangSiSYList.stream().anyMatch(
                            b -> Objects.equals(b.getBingRenID1(), a.getBingRenID1()) && Objects.equals(b.getBingRenID2(), a.getBingRenID2())))
                    .map(StringMTEntity::getId)
                    .toList();

            if (!CollectionUtils.isEmpty(deleteXiangSiSYIDs))
            {
                brDaXiangSiSYRepository.softDelete(deleteXiangSiSYIDs);
            }
        }
        if (!CollectionUtils.isEmpty(addJiaoChaSYList))
        {
                brDaJiaoChaSYRepository.saveAll(addJiaoChaSYList);
                List<GuiZeListDto> finalGuiZeList1 = guiZeList;
                List<BR_DA_ZhuSuoYCZRZModel> caoZuoRZList = addJiaoChaSYList.stream().map(x -> {
                    BR_DA_XiangSiSYModel guiZeID = addXiangSiSYList.stream()
                            .filter(m -> m.getBingRenID1().equals(x.getZhuBingRID()) && m.getBingRenID2().equals(x.getGuanLianBRID()))
                            .findFirst().orElse(null);
                    var xiangXiSM="";
                    if (guiZeID!=null)
                    {
                        var guiZe = finalGuiZeList1.stream().filter(m -> m.getGuiZeID().equals(guiZeID.getGuiZeID())).findFirst().orElse(null);
                        if (guiZe!=null)
                        {
                            xiangXiSM=StringUtil.concat("，合并规则为：",
                                    StringUtil.join(",",guiZe.getGuiZePZList().stream().map(o->o.getMingCheng()+o.getQuanZhong()+"%").toList()),
                                    "，阀值",guiZe.getFaZhi(),"%");
                        }
                    }

                    var zhuSuoYCZRZ = new BR_DA_ZhuSuoYCZRZModel();
                    zhuSuoYCZRZ.setZuZhiJGID("0");
                    zhuSuoYCZRZ.setZuZhiJGMC("通用");
                    zhuSuoYCZRZ.setBingRenID(x.getZhuBingRID());
                    zhuSuoYCZRZ.setXingMing(huanZheList.stream()
                            .filter(m->m.getId().equals(x.getZhuBingRID())).findFirst().orElse(new BR_DA_JiBenXXModel()).getXingMing());
                    zhuSuoYCZRZ.setCaoZuoLXDM(ZhuSuoYCZLXEnum.HEBING.getValue());
                    zhuSuoYCZRZ.setCaoZuoLXMC(ZhuSuoYCZLXEnum.HEBING.getDescription());
                    zhuSuoYCZRZ.setCaoZuoRID("0");
                    zhuSuoYCZRZ.setCaoZuoRXM("系统");
                    zhuSuoYCZRZ.setCaoZuoSJ( new Date());
                    zhuSuoYCZRZ.setCaoZuoNR(StringUtil.concat("主索引MPI：",x.getZhuBingRID(),",已经合并MPI：",x.getGuanLianBRID(),xiangXiSM));
                    return zhuSuoYCZRZ;
                }).toList();
                zhuSuoYCZRZRepository.saveAll(caoZuoRZList);
                //移除被合并的相似索引(原有的)
                List<BR_DA_XiangSiSYModel> deleteXiangSiSYList = yuanXiangSiSYList.stream()
                        .filter(x -> x.getHeBingBZ().equals(0))
                        .filter(x -> addJiaoChaSYList.stream()
                                .map(BR_DA_JiaoChaSYModel::getGuanLianBRID).toList()
                                .contains(x.getBingRenID1()) || addJiaoChaSYList.stream()
                                .map(BR_DA_JiaoChaSYModel::getGuanLianBRID).toList().contains(x.getBingRenID2())).toList();
                brDaXiangSiSYRepository.softDelete(deleteXiangSiSYList);
                List<String> bingRenIDs = deleteXiangSiSYList.stream().map(BR_DA_XiangSiSYModel::getBingRenID1).toList();
                addHeBingJLList.stream().filter(x -> bingRenIDs.contains(x.getBingRenID())).forEach(x -> {
                    long count = deleteXiangSiSYList.stream().filter(o -> o.getBingRenID1().equals(x.getBingRenID())).count();
                    x.setXiangSiShu(x.getXiangSiShu()-(int)(count));
                });
            }
            if (!CollectionUtils.isEmpty(addHeBingJLList))
            {
                brDaHeBingJLRepository.saveAll(addHeBingJLList);
            }
            jiSuanHBXSS(xiangSiHZList.stream().map(StringMTEntity::getId).toList());
        }
        /**
         * 计算合并相似数
         * @param bingRenIDs 病人ID集合
         */
        private void jiSuanHBXSS(List<String> bingRenIDs)
        {
            List<BR_DA_HeBingJLModel> allHeBingJL = brDaHeBingJLRepository.findByBingRenIDIn(bingRenIDs);
            List<BR_DA_XiangSiSYModel> allXaingSiSYList = brDaXiangSiSYRepository.findByBingRenID1InAndHuLueBZAndHeBingBZ(bingRenIDs, 0, 0);
            List<BR_DA_JiaoChaSYModel> allJiaoChaSYList = brDaJiaoChaSYRepository.findByZhuBingRIDInOrGuanLianBRIDIn(bingRenIDs, bingRenIDs);
            allHeBingJL.forEach(x->{
                long isZhuSuoYin = allJiaoChaSYList.stream().filter(m -> m.getZhuBingRID().equals(x.getBingRenID())).count();
                long isBeiHeBing = allJiaoChaSYList.stream().filter(m -> m.getGuanLianBRID().equals(x.getBingRenID())).count();
                if (isBeiHeBing>0)
                {
                    x.setHeBingShu((int)isBeiHeBing);
                    x.setHeBingZTDM("2");
                    x.setHeBingZTMC("被合并数据");
                    x.setZuiDaXSD(100);
                } else if (isZhuSuoYin>0) {
                    x.setHeBingShu((int)isZhuSuoYin);
                    x.setHeBingZTDM("1");
                    x.setHeBingZTMC("合并后主数据");
                    x.setZuiDaXSD(100);
                }else {
                    x.setHeBingShu(0);
                    x.setHeBingZTDM("0");
                    x.setHeBingZTMC("未合并");
                    BR_DA_XiangSiSYModel xiangSiSY = allXaingSiSYList.stream()
                            .filter(m -> m.getBingRenID1().equals(x.getBingRenID()))
                            .max(Comparator.comparing(BR_DA_XiangSiSYModel::getXiangSiDu)).orElse(null);
                    x.setZuiDaXSD( xiangSiSY==null?0:xiangSiSY.getXiangSiDu().intValue());
                }
                x.setXiangSiShu((int)allXaingSiSYList.stream().filter(m->m.getBingRenID1().equals(x.getBingRenID())).count());
            });
            brDaHeBingJLRepository.saveAll(allHeBingJL);
        }

    private void xiangSiPPByBRXX2(BR_DA_JiBenXXModel bingRenXX,
                                  List<BR_DA_JiBenXXModel> xiangSiHZList,
                                  List<GuiZeListDto> guiZeList,
                                  List<BR_DA_XiangSiSYModel> yuanXiangSiSYList,
                                  List<BR_DA_XiangSiSYModel> addXiangSiSYList,
                                  List<BR_DA_JiaoChaSYModel> addJiaoChaSYList,
                                  List<BR_DA_JiaoChaSYModel>  yuanJiaoChaSYList,
                                  Boolean ziDongHB)
    {
        List<String> ignoreIds = new ArrayList<>(yuanXiangSiSYList.stream()
                .filter(x -> x.getBingRenID1().equals(bingRenXX.getId()) && x.getHuLueBZ().equals(1)).map(BR_DA_XiangSiSYModel::getBingRenID2).toList());

        ignoreIds.addAll(addXiangSiSYList.stream().filter(x->x.getBingRenID1().equals(bingRenXX.getId())).map(BR_DA_XiangSiSYModel::getBingRenID2).toList());
        List<String> xiangSiBRIDs=new ArrayList<>();

        for (var guiZe:guiZeList)
        {
          var   ziDongHBBZ= guiZe.getXiangSiDu().compareTo(new BigDecimal(100))>=0&&ziDongHB;
            //region 获取相似的患者
            List<BR_DA_JiBenXXModel> query = xiangSiHZList.stream().filter(x -> !x.getId().equals(bingRenXX.getId()) && !ignoreIds.contains(x.getId())).toList();
            for (var ziDuan:guiZe.getGuiZePZList())
            {
                var value = getHuanZheValue(bingRenXX, ziDuan.getDaiMa());
                //s=>s.字段名!=null && s.字段名=上面得到的值
                query= query.stream().filter(item->{
                    var cls= item.getClass();
                    Field fieldM = null;
                    try {
                        fieldM = cls.getDeclaredField(ziDuan.getDaiMa());
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                            var fieldValue= fieldM.get(item);
                            if(fieldValue!=null&&fieldValue.equals(value))
                                return true;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                   return false;
                }).toList();
            }
                var dangQianXSHZList=query.stream().toList();
                xiangSiBRIDs.addAll(dangQianXSHZList.stream().map(BR_DA_JiBenXXModel::getId).toList());
                ignoreIds.addAll(dangQianXSHZList.stream().map(BR_DA_JiBenXXModel::getId).toList());
                //endregion
                for (var dangQianXSHZ:dangQianXSHZList)
                {
                    if (addXiangSiSYList.stream().anyMatch(x->x.getBingRenID1().equals(bingRenXX.getId())&&x.getBingRenID2().equals(dangQianXSHZ.getId())))
                    {
                        continue;
                    }
                    //region 修改相似索引
                    BR_DA_XiangSiSYModel xiangSiSY1 = yuanXiangSiSYList.stream()
                            .filter(x -> x.getBingRenID2().equals(dangQianXSHZ.getId()) && x.getBingRenID1().equals(bingRenXX.getId())).findFirst().orElse(null);
                    var hasXiangSiSY1=xiangSiSY1==null;
                    if(hasXiangSiSY1)
                    {
                        xiangSiSY1=new BR_DA_XiangSiSYModel();
                        xiangSiSY1.setBingRenID1(bingRenXX.getId());
                        xiangSiSY1.setBingRenID2(dangQianXSHZ.getId());
                        xiangSiSY1.setXingMing1(bingRenXX.getXingMing());
                        xiangSiSY1.setXingMing2(dangQianXSHZ.getXingMing());
                        xiangSiSY1.setHeBingBZ(ziDongHBBZ?1:0);
                        xiangSiSY1.setHuLueBZ(0);
                        xiangSiSY1.setGuiZeID(guiZe.getGuiZeID());
                        xiangSiSY1.setGuiZeMC(guiZe.getGuiZeMC());
                        xiangSiSY1.setZuZhiJGID("0");
                        xiangSiSY1.setZuZhiJGMC("通用");
                        addXiangSiSYList.add(xiangSiSY1);
                    }else{
                        if (!(xiangSiSY1.getGuiZeID().equals(guiZe.getGuiZeID())&&xiangSiSY1.getXiangSiDu().equals(guiZe.getXiangSiDu())))
                        {
                            xiangSiSY1=new BR_DA_XiangSiSYModel();
                            xiangSiSY1.setBingRenID1(bingRenXX.getId());
                            xiangSiSY1.setBingRenID2(dangQianXSHZ.getId());
                            xiangSiSY1.setXingMing1(bingRenXX.getXingMing());
                            xiangSiSY1.setXingMing2(dangQianXSHZ.getXingMing());
                            xiangSiSY1.setHeBingBZ(ziDongHBBZ?1:0);
                            xiangSiSY1.setHuLueBZ(0);
                            xiangSiSY1.setGuiZeID(guiZe.getGuiZeID());
                            xiangSiSY1.setGuiZeMC(guiZe.getGuiZeMC());
                            xiangSiSY1.setZuZhiJGID("0");
                            xiangSiSY1.setZuZhiJGMC("通用");
                            addXiangSiSYList.add(xiangSiSY1);
                        }
                    }
                    BR_DA_XiangSiSYModel xiangSiSY2 = yuanXiangSiSYList.stream()
                            .filter(x -> x.getBingRenID2().equals(bingRenXX.getId()) && x.getBingRenID1().equals(dangQianXSHZ.getId()))
                            .findFirst().orElse(null);
                    var hasXiangSiSY2 = xiangSiSY2 == null;
                    if (hasXiangSiSY2)
                    {
                        xiangSiSY2=new BR_DA_XiangSiSYModel();
                        xiangSiSY2.setBingRenID1(dangQianXSHZ.getId());
                        xiangSiSY2.setBingRenID2(bingRenXX.getId());
                        xiangSiSY2.setXingMing1(dangQianXSHZ.getXingMing());
                        xiangSiSY2.setXingMing2(bingRenXX.getXingMing());
                        xiangSiSY2.setHeBingBZ(ziDongHBBZ?1:0);
                        xiangSiSY2.setHuLueBZ(0);
                        xiangSiSY2.setGuiZeID(guiZe.getGuiZeID());
                        xiangSiSY2.setGuiZeMC(guiZe.getGuiZeMC());
                        xiangSiSY2.setZuZhiJGID("0");
                        xiangSiSY2.setZuZhiJGMC("通用");
                        addXiangSiSYList.add(xiangSiSY2);
                    }else{
                       if (!(xiangSiSY2.getGuiZeID().equals(guiZe.getGuiZeID())&&xiangSiSY2.getXiangSiDu().equals(guiZe.getXiangSiDu())))
                       {
                            xiangSiSY2=new BR_DA_XiangSiSYModel();
                            xiangSiSY2.setBingRenID1(dangQianXSHZ.getId());
                            xiangSiSY2.setBingRenID2(bingRenXX.getId());
                            xiangSiSY2.setXingMing1(dangQianXSHZ.getXingMing());
                            xiangSiSY2.setXingMing2(bingRenXX.getXingMing());
                            xiangSiSY2.setHeBingBZ(ziDongHBBZ?1:0);
                            xiangSiSY2.setHuLueBZ(0);
                            xiangSiSY2.setGuiZeID(guiZe.getGuiZeID());
                            xiangSiSY2.setGuiZeMC(guiZe.getGuiZeMC());
                            xiangSiSY2.setZuZhiJGID("0");
                            xiangSiSY2.setZuZhiJGMC("通用");
                            addXiangSiSYList.add(xiangSiSY2);
                       }
                    }
                    //endregion

                    //region 更新交叉索引
                    if (ziDongHBBZ)
                    {
                        //插入本次交叉索引
                        var jiaoChaSY=new BR_DA_JiaoChaSYModel(){{
                            setHeBingRID("0");
                            setHeBingRXM("系统");
                            setHeBingSJ(new Date());
                            setGuanLianBRID(dangQianXSHZ.getId());
                            setZhuBingRID(bingRenXX.getId());
                            setZiDongHBBZ(1);
                            setZuZhiJGID("0");
                            setZuZhiJGMC("通用");
                        }};
                        addJiaoChaSYList.add(jiaoChaSY);
                        //插入被关联患者的原交叉索引
                        List<BR_DA_JiaoChaSYModel> xiangSiHZJCSYList = yuanJiaoChaSYList.stream().filter(x -> x.getZhuBingRID().equals(dangQianXSHZ.getId()))
                                .map(x -> {
                                    var jiaoChaSYModel = new BR_DA_JiaoChaSYModel();
                                    jiaoChaSYModel.setHeBingRID("0");
                                    jiaoChaSYModel.setHeBingRXM("系统");
                                    jiaoChaSYModel.setHeBingSJ(new Date());
                                    jiaoChaSYModel.setGuanLianBRID(x.getGuanLianBRID());
                                    jiaoChaSYModel.setZhuBingRID(bingRenXX.getId());
                                    jiaoChaSYModel.setZiDongHBBZ(1);
                                    jiaoChaSYModel.setZuZhiJGID("0");
                                    jiaoChaSYModel.setZuZhiJGMC("通用");
                                    return jiaoChaSYModel;
                                }).toList();
                        if (!CollectionUtils.isEmpty(xiangSiHZJCSYList))
                        {
                            addJiaoChaSYList.addAll(xiangSiHZJCSYList);
                        }
                    }
                    //endregion
                }
        }
        xiangSiBRIDs.addAll(yuanJiaoChaSYList.stream().filter(x->x.getZhuBingRID().equals(bingRenXX.getId())).map(BR_DA_JiaoChaSYModel::getGuanLianBRID).toList());
    }

//    private Exception<Function<T,Tkey>> whereDataNotNull<T,Tkey>(String propertyName)
//    {
//
//    }

    private Object getHuanZheValue(BR_DA_JiBenXXModel huanZhe, String daiMa)
    {
        return switch (daiMa) {
            case "ShenFenZH" -> huanZhe.getShenFenZH();
            case "XingMing" -> huanZhe.getXingMing();
            case "XingBieDM" -> huanZhe.getXingBieDM();
            case "ChuShengRQ" -> Optional.ofNullable(huanZhe.getChuShengRQ()).orElse(new Date());
            case "ZhengJianHM" -> huanZhe.getZhengJianHM();
            case "HunYinDM" -> huanZhe.getHunYinDM();
            case "GuoJiDM" -> huanZhe.getGuoJiDM();
            case "ChuShengDXX" -> huanZhe.getChuShengDXX();
            case "JiGuanDZXX" -> huanZhe.getJiGuanDZXX();
            case "LianXiDH" -> huanZhe.getLianXiDH();
            case "XianZhuZXX" -> huanZhe.getXianZhuZXX();
            case "DanWeiMC" -> huanZhe.getDanWeiMC();
            case "DanWeiDH" -> huanZhe.getDanWeiDH();
            case "LianXiRXM" -> huanZhe.getLianXiRXM();
            case "LianXiRDH" -> huanZhe.getLianXiRDH();
            default -> huanZhe.getShenFenZH();
        };
    }

    //获取患者的相似患者列表，排除已合并与已忽略的
    private List<BR_DA_JiBenXXModel> getXiangSiHZList(List<BR_DA_JiBenXXModel> huanZheList)
    {
        List<BR_DA_JiBenXXModel> xiangSiHZList = brDaJiBenXXRepository.asQuerydsl().where(x -> (x.zhengJianHM.isNotNull()
                .and(x.zhengJianHM.isNotEmpty())
                .and(x.zhengJianHM.in(huanZheList.stream().map(BR_DA_JiBenXXModel::getZhengJianHM).toList())))
                .or(x.xingMing.isNotNull()
                        .and(x.xingMing.isNotEmpty())
                        .and(x.xingMing.in(huanZheList.stream().map(BR_DA_JiBenXXModel::getXingMing).toList())))).fetch();
        //排除被合并的
        List<BR_DA_JiBenXXModel> finalXiangSiHZList = xiangSiHZList;
        List<String> ignoreIds = brDaJiaoChaSYRepository.asQuerydsl()
                .where(x -> x.guanLianBRID.in(finalXiangSiHZList.stream().map(BR_DA_JiBenXXModel::getId).toList())).select(x -> x.guanLianBRID).fetch();

        xiangSiHZList=xiangSiHZList.stream().filter(x->!ignoreIds.contains(x.getId())).toList();
        return xiangSiHZList;
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
        Class<?> type = tModel.getClass(); // 获取类型
        for (var tuoMinPZ : tuoMinPZList) {
            try {
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
            }catch (NoSuchFieldException ex){
                //do something
            }
        }
        return tModel;
    }
}

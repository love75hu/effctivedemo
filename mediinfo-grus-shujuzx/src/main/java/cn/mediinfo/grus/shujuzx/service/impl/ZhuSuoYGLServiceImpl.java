package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.util.DateUtil;
import cn.mediinfo.cyan.msf.core.util.MapUtils;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import cn.mediinfo.cyan.msf.util.BigDecimalUtil;
import cn.mediinfo.cyan.msf.util.NianLing;
import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.constant.ZhuSuoYCZLXEnum;
import cn.mediinfo.grus.shujuzx.constant.ZhuSuoYHBZTConstant;
import cn.mediinfo.grus.shujuzx.constant.ZhuSuoYHBZTConstants;
import cn.mediinfo.grus.shujuzx.dto.shujuzxzsys.*;
import cn.mediinfo.grus.shujuzx.dto.yinsigzszs.SC_ZD_YinSiPZOutDto;
import cn.mediinfo.grus.shujuzx.model.*;
import cn.mediinfo.grus.shujuzx.po.RecordJiBenXXAndHeBingJL;
import cn.mediinfo.grus.shujuzx.po.RecordJiBenXXAndHeBingJLModel;
import cn.mediinfo.grus.shujuzx.remoteservice.LinChuangRemoteService;
import cn.mediinfo.grus.shujuzx.repository.*;
import cn.mediinfo.grus.shujuzx.service.YinSiGZSZService;
import cn.mediinfo.grus.shujuzx.service.ZhuSuoYCZRZService;
import cn.mediinfo.grus.shujuzx.service.ZhuSuoYGLService;
import cn.mediinfo.grus.shujuzx.utils.ExpressionUtils;
import cn.mediinfo.grus.shujuzx.utils.SpringCache;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 主索引管理
 */
@Service
@Slf4j
public class ZhuSuoYGLServiceImpl implements ZhuSuoYGLService {
    public final BR_DA_XiangSiSYRepository brDaXiangSiSYRepository;
    public final BR_DA_JiaoChaSYRepository brDaJiaoChaSYRepository;
    public final BR_DA_JiBenXXRepository brDaJiBenXXRepository;
    public final BR_DA_HeBingJLRepository brDaHeBingJLRepository;
    public final BR_DA_JieZhiXXRepository brDaJieZhiXXRepository;
    public final BR_DA_KuoZhanXXRepository brDaKuoZhanXXRepository;
    public final BR_ZD_HeBingGZRepository brZdHeBingGZRepository;
    public final BR_ZD_HeBingGZMXRepository brZdHeBingGZMXRepository;
    public final LinChuangRemoteService jiuZhenRemoteService;
    private final BR_DA_ZhuSuoYCZRZRepository zhuSuoYCZRZRepository;
    public final YinSiGZSZService yinSiGZSZService;
    public final ZhuSuoYCZRZService zhuSuoYCZRZService;
    private final LyraIdentityService lyraIdentityService;

    private final SpringCache springCache;


    public ZhuSuoYGLServiceImpl(BR_DA_XiangSiSYRepository brDaXiangSiSYRepository, BR_DA_JiaoChaSYRepository brDaJiaoChaSYRepository, BR_DA_JiBenXXRepository brDaJiBenXXRepository, BR_DA_HeBingJLRepository brDaHeBingJLRepository, BR_DA_JieZhiXXRepository brDaJieZhiXXRepository, BR_DA_KuoZhanXXRepository brDaKuoZhanXXRepository, BR_ZD_HeBingGZRepository brZdHeBingGZRepository, BR_ZD_HeBingGZMXRepository brZdHeBingGZMXRepository, LinChuangRemoteService jiuZhenRemoteService, YinSiGZSZService yinSiGZSZService, ZhuSuoYCZRZService zhuSuoYCZRZService, LyraIdentityService lyraIdentityService, BR_DA_ZhuSuoYCZRZRepository zhuSuoYCZRZRepository, SpringCache springCache) {
        this.brDaXiangSiSYRepository = brDaXiangSiSYRepository;
        this.brDaJiaoChaSYRepository = brDaJiaoChaSYRepository;
        this.brDaJiBenXXRepository = brDaJiBenXXRepository;
        this.brDaHeBingJLRepository = brDaHeBingJLRepository;
        this.brDaJieZhiXXRepository = brDaJieZhiXXRepository;
        this.brDaKuoZhanXXRepository = brDaKuoZhanXXRepository;
        this.brZdHeBingGZRepository = brZdHeBingGZRepository;
        this.brZdHeBingGZMXRepository = brZdHeBingGZMXRepository;
        this.jiuZhenRemoteService = jiuZhenRemoteService;
        this.yinSiGZSZService = yinSiGZSZService;
        this.zhuSuoYCZRZService = zhuSuoYCZRZService;
        this.lyraIdentityService = lyraIdentityService;
        this.zhuSuoYCZRZRepository = zhuSuoYCZRZRepository;
        this.springCache = springCache;
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
        return xiangSiBRXXList.stream().sorted(Comparator.comparing(BR_DA_JiBenXXByZSYGLDto::getHeBingBZ, Comparator.nullsFirst(Integer::compareTo)).reversed()
                        .thenComparing(Comparator.comparing(BR_DA_JiBenXXByZSYGLDto::getHeBingSJ, Comparator.nullsFirst(Date::compareTo)).reversed())
                        .thenComparing(Comparator.comparing(BR_DA_JiBenXXByZSYGLDto::getXiangSiDu, Comparator.nullsFirst(BigDecimal::compareTo)).reversed())
                        .thenComparing(Comparator.comparing(BR_DA_JiBenXXByZSYGLDto::getJiaoChaSYCount, Comparator.nullsFirst(Integer::compareTo)).reversed()))
                .collect(Collectors.toList());
    }

    /**
     * 获取主索引详情
     *
     * @return ZhuSuoYXQDto
     * @throws TongYongYWException 通用异常
     */
    @Override
    public ZhuSuoYXQDto getZhuSuoYXQ(String bingRenID, String chaXunMSDM) throws TongYongYWException, ParseException, NoSuchFieldException, IllegalAccessException, YuanChengException {
        if (!StringUtil.hasText(chaXunMSDM)) {
            chaXunMSDM = "1";
        }
        var zhuSuoYBRXQ = jiuZhenRemoteService.GetBingRenJBXXByBRID(bingRenID).getData("未找到相关信息");

        List<SC_ZD_YinSiPZOutDto> yinSiGZPZ = yinSiGZSZService.getYinSiGZPZList(chaXunMSDM, lyraIdentityService.getJiGouID());

        var result = new ZhuSuoYXQDto();
        result.setBingRenXXList(new ArrayList<>());
        result.getBingRenXXList().add(MapUtils.copyProperties(zhuSuoYBRXQ.getBingRenXX(), BR_DA_JiBenXXByZSYXQDto::new));
        result.getBingRenXXList().addAll(zhuSuoYBRXQ.getXiangSiBRList());
        for (var item : result.getBingRenXXList()) {
            if (item.getJianDangSJ() == null) {
                item.setJianDangSJ(item.getChuangJianSJ());
            }
            item.setNianLing(getNianLing(item.getChuShengRQ()));
            if (!CollectionUtils.isEmpty(yinSiGZPZ)) {
                MapUtils.mergeProperties(ExpressionUtils.getYinShiSJ(item, yinSiGZPZ), item, true);
            }
        }
        for (var jieZhi : zhuSuoYBRXQ.getJieZhiXXList()) {
            if (!StringUtil.hasText(jieZhi.getDiSanFBRID())) {
                jieZhi.setDiSanFBRID(jieZhi.getBingRenID());
            }
        }
        result.setGuanLianYXXList(zhuSuoYBRXQ.getJieZhiXXList());
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
        var count = brDaJiBenXXRepository.asQuerydsl()
                .whereIf(!ObjectUtils.isEmpty(kaiShiSJ), o -> o.jianDangSJ.goe(kaiShiSJ))
                .whereIf(!ObjectUtils.isEmpty(jieShuSJ), o -> o.jianDangSJ.loe(jieShuSJ))
                .whereIf(StringUtils.hasText(MPI), o -> o.id.contains(MPI))
                .whereIf(StringUtils.hasText(xingMing), o -> o.xingMing.contains(xingMing))
                .whereIf(StringUtils.hasText(lianXiDH), o -> o.lianXiDH.contains(lianXiDH))
                .whereIf(StringUtils.hasText(shenFenZH), o -> o.zhengJianHM.contains(shenFenZH))
                .leftJoin(brDaHeBingJLRepository.asQuerydsl(), (c, d) -> c.id.eq(d.bingRenID), RecordJiBenXXAndHeBingJL::new)
                .where(o -> o.heBingJL().heBingZTDM.ne(ZhuSuoYHBZTConstant.HEBINGZTDM_BHB).or(o.heBingJL().heBingZTDM.isNull()))
                .whereIf(xiangSiDu != null && xiangSiDu > 0, o -> o.heBingJL().zuiDaXSD.goe(xiangSiDu))
                .select(o -> o.jiBenXX().count())
                .findFirst();
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
        var jiBenXXList = brDaJiBenXXRepository.asQuerydsl()
                .whereIf(!ObjectUtils.isEmpty(kaiShiSJ), o -> o.jianDangSJ.goe(kaiShiSJ))
                .whereIf(!ObjectUtils.isEmpty(jieShuSJ), o -> o.jianDangSJ.loe(jieShuSJ))
                .whereIf(StringUtils.hasText(MPI), o -> o.id.contains(MPI))
                .whereIf(StringUtils.hasText(xingMing), o -> o.xingMing.contains(xingMing))
                .whereIf(StringUtils.hasText(lianXiDH), o -> o.lianXiDH.contains(lianXiDH))
                .whereIf(StringUtils.hasText(shenFenZH), o -> o.zhengJianHM.contains(shenFenZH))
                .leftJoin(brDaHeBingJLRepository.asQuerydsl(), (c, d) -> c.id.eq(d.bingRenID), RecordJiBenXXAndHeBingJL::new)
                .where(o -> o.heBingJL().heBingZTDM.ne(ZhuSuoYHBZTConstant.HEBINGZTDM_BHB).or(o.heBingJL().heBingZTDM.isNull()))
                .whereIf(xiangSiDu != null && xiangSiDu > 0, o -> o.heBingJL().zuiDaXSD.goe(xiangSiDu))
                .orderBy(o -> o.jiBenXX().jianDangSJ.desc().nullsLast())
                .select(c -> new Expression<?>[]{
                        c.jiBenXX(),
                        c.heBingJL()
                }, RecordJiBenXXAndHeBingJLModel.class)
                .fetchPage(pageIndex, pageSize);
        List<BR_DA_JiBenXXByZSYGLDto> result = new ArrayList<>();
        for (var item : jiBenXXList) {
            var dto = MapUtils.copyProperties(item.jiBenXX(), BR_DA_JiBenXXByZSYGLDto::new);
            if (item.heBingJL() == null || item.heBingJL().getHeBingZTDM() == null) {
                dto.setHeBingZTDM(ZhuSuoYHBZTConstant.HEBINGZTDM_WHB);
            } else {
                dto.setHeBingZTDM(item.heBingJL().getHeBingZTDM());
            }
            if (item.heBingJL() == null || item.heBingJL().getHeBingZTMC() == null) {
                dto.setHeBingZTMC("无合并");
            } else {
                dto.setHeBingZTMC(item.heBingJL().getHeBingZTMC());
            }
            if (item.heBingJL() == null || item.heBingJL().getZuiDaXSD() == null) {
                dto.setZuiDaXSD(0);
            } else {
                dto.setZuiDaXSD(item.heBingJL().getZuiDaXSD());
            }
            if (item.heBingJL() == null || item.heBingJL().getHeBingShu() == null) {
                dto.setHeBingShu(0);
            } else {
                dto.setHeBingShu(item.heBingJL().getHeBingShu());
            }
            if (item.heBingJL() == null || item.heBingJL().getXiangSiShu() == null) {
                dto.setXiangSiShu(0);
            } else {
                dto.setXiangSiShu(item.heBingJL().getXiangSiShu());
            }
            dto.setNianLing(getNianLing(dto.getChuShengRQ()));
            dto.setXianZhuZXX(StringUtil.concat(dto.getXianZhuZXX(), dto.getXianZhuZXZMC(), dto.getXianZhuZCJMC(), dto.getXianZhuZQTXX()));
            result.add(dto);
        }
        return result;
    }

    /**
     * 合并页面获取主索引和相似索引信息
     *
     * @return List<BR_DA_JiBenXXByHBXXDto>
     * @throws TongYongYWException 通用异常
     */
    @Override
    public List<BR_DA_JiBenXXByHBXXDto> getZhuSuoYXSList(String bingRenID, Integer xiangSiDu) throws TongYongYWException {
        //获取主索引信息
        var bingRenXX = brDaJiBenXXRepository.findById(bingRenID).orElse(null);
        if (bingRenXX == null) {
            throw new TongYongYWException("未找到相关病人信息");
        }
        //获取相似索引病人Id
        var xiangSiBRs = brDaXiangSiSYRepository.asQuerydsl()
                .where(o -> o.bingRenID1.eq(bingRenID).and(o.heBingBZ.eq(0)).and(o.huLueBZ.eq(0)))
                .whereIf(Objects.nonNull(xiangSiDu) && xiangSiDu > 0, o -> o.xiangSiDu.goe(xiangSiDu))
                .select(o -> new Expression<?>[]{o.bingRenID2, o.xiangSiDu}, BR_DA_XiangSiSYModel.class)
                .fetch();
        var xiangSiBRIDs = xiangSiBRs.stream().map(BR_DA_XiangSiSYModel::getBingRenID2).toList();
        //获取相似索引病人信息
        var xiangSiBRList = MapUtils.copyListProperties(brDaJiBenXXRepository.findAllById(xiangSiBRIDs), BR_DA_JiBenXXByHBXXDto::new);
        List<BR_DA_JiBenXXByHBXXDto> result = new ArrayList<>();
        result.add(MapUtils.copyProperties(bingRenXX, BR_DA_JiBenXXByHBXXDto::new));
        //获取介质信息
        var bingRenIDs = result.stream().map(BR_DA_JiBenXXByHBXXDto::getId).toList();
        var jieZhiXXList = MapUtils.copyListProperties(brDaJieZhiXXRepository.findByBingRenIDIn(bingRenIDs), BR_DA_JieZhiXXDto::new);
        //查询病人扩展信息
        var kuoZhanXXList = MapUtils.copyListProperties(brDaKuoZhanXXRepository.findByBingRenIDIn(bingRenIDs), BR_DA_KuoZhanXXDto::new);
        for (var item : xiangSiBRList) {
            var firstXSBR = xiangSiBRs.stream().filter(o -> Objects.equals(o.getBingRenID2(), item.getId())).findFirst().orElse(null);
            item.setXiangSiDu(firstXSBR != null ? firstXSBR.getXiangSiDu() : BigDecimal.ZERO);
            item.setJieZhiXX(jieZhiXXList.stream().filter(o -> Objects.equals(o.getBingRenID(), item.getId())).findFirst().orElse(null));
            item.setKuoZhanXX(kuoZhanXXList.stream().filter(o -> Objects.equals(o.getBingRenID(), item.getId()) && "YouHuiLB".equals(o.getXiangMuDM())).findFirst().orElse(null));
            var nianLing = getNianLing(item.getChuShengRQ());
            var nianLings = nianLing.split(" ");
            if (nianLings.length >= 2) {
                item.setNianLing(nianLings[0]);
                item.setNianLingDW(nianLings[1]);
            }
            if (nianLings.length >= 4) {
                item.setNianLing1(nianLings[2]);
                item.setNianLingDW1(nianLings[3]);
            }
        }
        result.addAll(xiangSiBRList);
        return result;
    }

    /**
     * 合并
     *
     * @return Boolean
     * @throws TongYongYWException 通用异常
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean heBing(SaveHeBingDto dto) throws TongYongYWException {

        if (CollectionUtils.isEmpty(dto.getXiangSiBRIDList())) {
            throw new TongYongYWException("未选中相似索引");
        }
        //修改主索引病人信息
        var bingRenXX = brDaJiBenXXRepository.findById(dto.getZhuSuoYBRXX().getID()).orElse(null);
        if (bingRenXX == null) {
            throw new TongYongYWException("未找到相关病人信息");
        }
        MapUtils.mergeProperties(dto.getZhuSuoYBRXX(), bingRenXX);
        brDaJiBenXXRepository.save(bingRenXX);
        //获取主索引的合并记录
        var zhuSuoYHBJL = brDaHeBingJLRepository.findFirstByBingRenID(dto.getZhuSuoYBRXX().getID());
        if (zhuSuoYHBJL == null) {
            throw new TongYongYWException("未找到相关病人合并记录信息");
        }
        zhuSuoYHBJL.setHeBingZTDM(ZhuSuoYHBZTConstant.HEBINGZTDM_HB);
        zhuSuoYHBJL.setHeBingZTMC(ZhuSuoYHBZTConstant.HEBINGZTMC_HB);
        if (ObjectUtils.isEmpty(zhuSuoYHBJL.getHeBingShu())) {
            zhuSuoYHBJL.setHeBingShu(dto.getXiangSiBRIDList().size());
        } else {
            zhuSuoYHBJL.setHeBingShu(zhuSuoYHBJL.getHeBingShu() + dto.getXiangSiBRIDList().size());
        }
        if (zhuSuoYHBJL.getXiangSiShu() != null) {
            zhuSuoYHBJL.setXiangSiShu(zhuSuoYHBJL.getXiangSiShu() - dto.getXiangSiBRIDList().size());
        }
        brDaHeBingJLRepository.save(zhuSuoYHBJL);
        //修改相似索引
        var xiangSiSYList = brDaXiangSiSYRepository.getXiangSiSYList(bingRenXX.getId(), dto.getXiangSiBRIDList());
        xiangSiSYList.forEach(o -> o.setHeBingBZ(1));
        brDaXiangSiSYRepository.saveAll(xiangSiSYList);
        //修改相似索引病人信息
        var xiangSiBRList = brDaJiBenXXRepository.findAllById(dto.getXiangSiBRIDList());
        //获取相似病人合并记录信息
        var xiangSiBRHBJLList = brDaHeBingJLRepository.findByBingRenIDIn(dto.getXiangSiBRIDList());
        for (var item : xiangSiBRList) {
            var xiangSiBRHBJL = xiangSiBRHBJLList.stream().filter(o -> Objects.equals(o.getBingRenID(), item.getId())).findFirst().orElse(null);
            if (xiangSiBRHBJL != null) {
                xiangSiBRHBJL.setHeBingZTDM(ZhuSuoYHBZTConstant.HEBINGZTDM_BHB);
                xiangSiBRHBJL.setHeBingZTMC(ZhuSuoYHBZTConstant.HEBINGZTMC_BHB);
                if (xiangSiBRHBJL.getHeBingShu() == null) {
                    xiangSiBRHBJL.setHeBingShu(1);
                } else {
                    xiangSiBRHBJL.setHeBingShu(xiangSiBRHBJL.getHeBingShu() + 1);
                }
                if (xiangSiBRHBJL.getXiangSiShu() != null) {
                    xiangSiBRHBJL.setXiangSiShu(xiangSiBRHBJL.getXiangSiShu() - 1);
                }
                brDaHeBingJLRepository.save(xiangSiBRHBJL);
            }
            //添加操作日志
            var xiangSiSYModel = xiangSiSYList.stream().findFirst().orElse(null);
            String guiZheID = xiangSiSYModel != null ? xiangSiSYModel.getGuiZeID() : "";
            String xiangXiGZSM = "";
            if (StringUtil.hasText(guiZheID)) {
                var guiZe = brZdHeBingGZRepository.findById(guiZheID).orElse(null);
                var guiZeMXs = brZdHeBingGZMXRepository.findByGuiZeID(guiZheID);
                if (guiZe != null && !CollectionUtils.isEmpty(guiZeMXs)) {
                    xiangXiGZSM = String.format("，合并规则为：%s，阀值%s%%",
                            guiZeMXs.stream().map(x -> x.getMingCheng() + x.getQuanZhong() + "%").collect(Collectors.joining("，")),
                            guiZe.getFaZhi());
                }
            }
            String caoZuoNR = StringUtil.concat("主索引MPI：", dto.getZhuSuoYBRXX().getID(), "，已经合并MPI：", item.getId(), xiangXiGZSM);
            zhuSuoYCZRZService.addCaoZuoRZ(dto.getZhuSuoYBRXX().getID(), dto.getZhuSuoYBRXX().getXingMing(), ZhuSuoYCZLXEnum.HEBING, caoZuoNR, false);
        }
        //插入交叉索引
        var yuanJiaoCSYList = brDaJiaoChaSYRepository.getJiaoChaZhuBRIDList(dto.getXiangSiBRIDList());
        if (!CollectionUtils.isEmpty(yuanJiaoCSYList)) {
            zhuSuoYHBJL.setHeBingShu((zhuSuoYHBJL.getHeBingShu() != null ? zhuSuoYHBJL.getHeBingShu() : 0) + yuanJiaoCSYList.size());
        }
        yuanJiaoCSYList.addAll(dto.getXiangSiBRIDList());
        var insertJiaoChaSYList = yuanJiaoCSYList.stream().distinct().map(o -> {
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
        var deleteXiangSiSYList = brDaXiangSiSYRepository.getDeleteXiangSiSYList(dto.getZhuSuoYBRXX().getID(), dto.getXiangSiBRIDList());
        var deleteIDs = deleteXiangSiSYList.stream().map(BR_DA_XiangSiSYModel::getId).toList();
        brDaXiangSiSYRepository.deleteAllById(deleteIDs);
        //修改被删除相似索引患者的相似数
        var xiangSiHZHBJLList = brDaHeBingJLRepository.findByBingRenIDNotAndBingRenIDIn(dto.getZhuSuoYBRXX().getID(), deleteXiangSiSYList.stream().map(BR_DA_XiangSiSYModel::getBingRenID1).toList());
        for (var item : xiangSiHZHBJLList) {
            var count = deleteXiangSiSYList.stream()
                    .filter(o -> Objects.equals(o.getBingRenID1(), item.getBingRenID()))
                    .map(BR_DA_XiangSiSYModel::getBingRenID2)
                    .distinct().count();
            item.setXiangSiShu((item.getXiangSiShu() != null ? item.getXiangSiShu() : 0) - (int) count);
        }
        brDaHeBingJLRepository.saveAll(xiangSiHZHBJLList);
        return true;
    }

    /**
     * 忽略合并
     *
     * @return Boolean
     * @throws TongYongYWException 通用异常
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean huLueHB(HuLueHBDto dto) throws TongYongYWException {
        //修改相似索引的忽略标志
        var xiangSISYList = brDaXiangSiSYRepository.getXiangSiSYList(dto.getZhuSuoYBRID(), List.of(dto.getXiangSiSYBRID()));
        xiangSISYList.forEach(o -> o.setHuLueBZ(1));
        brDaXiangSiSYRepository.saveAll(xiangSISYList);
        //修改病人基本信息的相似数
        var zhuSuoYBRXX = brDaJiBenXXRepository.findById(dto.getZhuSuoYBRID()).orElse(null);
        if (zhuSuoYBRXX != null) {
            var zhuSuoYHBJL = brDaHeBingJLRepository.findFirstByBingRenID(dto.getZhuSuoYBRID());
            var xiangSiSY = brDaXiangSiSYRepository.findFirstByBingRenID1AndBingRenID2NotAndHuLueBZOrderByXiangSiDuDesc(zhuSuoYBRXX.getId(), dto.getXiangSiSYBRID(), 0);
            if (zhuSuoYHBJL != null) {
                zhuSuoYHBJL.setXiangSiShu(zhuSuoYHBJL.getXiangSiShu() - 1);
                zhuSuoYHBJL.setZuiDaXSD(xiangSiSY.getXiangSiDu() != null ? xiangSiSY.getXiangSiDu().intValue() : 0);
                brDaHeBingJLRepository.save(zhuSuoYHBJL);
            }
        }
        var xiangSiSYBRXX = brDaJiBenXXRepository.findById(dto.getXiangSiSYBRID()).orElse(null);
        if (xiangSiSYBRXX != null) {
            var xiangSiSYYHBJL = brDaHeBingJLRepository.findFirstByBingRenID(dto.getXiangSiSYBRID());
            var xiangSiSY = brDaXiangSiSYRepository.findFirstByBingRenID1AndBingRenID2NotAndHuLueBZOrderByXiangSiDuDesc(xiangSiSYBRXX.getId(), dto.getZhuSuoYBRID(), 0);
            if (xiangSiSYYHBJL != null) {
                xiangSiSYYHBJL.setXiangSiShu(xiangSiSYYHBJL.getXiangSiShu() - 1);
                xiangSiSYYHBJL.setZuiDaXSD(xiangSiSY.getXiangSiDu() != null ? xiangSiSY.getXiangSiDu().intValue() : 0);
                brDaHeBingJLRepository.save(xiangSiSYYHBJL);
            }
        }
        //添加操作日志
        String caoZuoNR = StringUtil.concat("主索引MPI：", dto.getZhuSuoYBRID(), ",忽略合并MPI：", dto.getXiangSiSYBRID());
        zhuSuoYCZRZService.addCaoZuoRZ(dto.getZhuSuoYBRID(), zhuSuoYBRXX != null ? zhuSuoYBRXX.getXingMing() : "", ZhuSuoYCZLXEnum.HULUE, caoZuoNR, false);
        return true;
    }

    /**
     * 主索引修改病人基本信息
     *
     * @return Boolean
     * @throws TongYongYWException 通用异常
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateBingRenJBXX(BR_DA_JiBenXXCreateDto dto) throws TongYongYWException {
        var updateJiBenXX = brDaJiBenXXRepository.findById(dto.getId()).orElse(null);
        if (updateJiBenXX == null) {
            throw new TongYongYWException("未找到病人信息");
        }
        //防病人信息被修改
        if (!StringUtil.hasText(dto.getZhengJianHM())) {

            dto.setZhengJianHM(null);
        }
        if (!Objects.equals(updateJiBenXX.getXingMing(), dto.getXingMing()) && !Objects.equals(updateJiBenXX.getZhengJianHM(), dto.getZhengJianHM())) {
            throw new TongYongYWException("不能同时修改姓名和证件号码");
        }
        MapUtils.mergeProperties(dto, updateJiBenXX);
        brDaJiBenXXRepository.save(updateJiBenXX);
        //添加操作日志
        String caoZuoNR = StringUtil.concat("主索引MPI：", updateJiBenXX.getId(), ",修改基本信息");
        zhuSuoYCZRZService.addCaoZuoRZ(updateJiBenXX.getId(), updateJiBenXX.getXingMing(), ZhuSuoYCZLXEnum.XIUGAI, caoZuoNR, false);
        return dto.getId();

    }

    /**
     * 取消合并
     *
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean quXiaoHB(HuLueHBDto dto) throws TongYongYWException {

        //region  交叉索引变更(闭包表)
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
        brDaJiaoChaSYRepository.deleteAllInBatch(deteleJiaoChaSYList);
        //endregion
        //region 修改病人基本信息
        //修改病人基本信息
        BR_DA_JiBenXXModel zhuBingRenXX = brDaJiBenXXRepository.findById(dto.getZhuSuoYBRID()).stream().findFirst().orElse(null);
        if (zhuBingRenXX == null) {
            throw new TongYongYWException("未找到主病人信息");
        }

        BR_DA_HeBingJLModel zhuSuoYHBJL = brDaHeBingJLRepository.findFirstByBingRenID(dto.getZhuSuoYBRID());
        zhuSuoYHBJL.setHeBingShu(zhuSuoYHBJL.getHeBingShu() - deteleJiaoChaSYList.size());
        if (brDaJiaoChaSYRepository.existsByZhuBingRIDAndGuanLianBRIDNot(dto.getZhuSuoYBRID(), dto.getXiangSiSYBRID())) {
            zhuSuoYHBJL.setHeBingZTDM("0");
            zhuSuoYHBJL.setHeBingZTMC("未合并");
        }
        BR_DA_JiBenXXModel xiangSiBRXX = brDaJiBenXXRepository.findById(dto.getXiangSiSYBRID()).stream().findFirst().orElse(null);//todo
        if (xiangSiBRXX == null) {
            throw new TongYongYWException("未找到相似病人信息");
        }
        BR_DA_HeBingJLModel xiangSiHBJL = brDaHeBingJLRepository.findFirstByBingRenID(dto.getXiangSiSYBRID());
        xiangSiHBJL.setHeBingShu(xiangSiHBJL.getHeBingShu() - 1);
        if (brDaJiaoChaSYRepository.existsByZhuBingRID(dto.getXiangSiSYBRID())) {
            xiangSiHBJL.setHeBingZTDM("1");
            xiangSiHBJL.setHeBingZTMC("合并后主数据");

        } else {
            List<BR_DA_JiaoChaSYModel> xiangSiJCList = brDaJiaoChaSYRepository.findByGuanLianBRIDAndZhuBingRIDNot(dto.getXiangSiSYBRID(), dto.getZhuSuoYBRID());
            if (!CollectionUtils.isEmpty(xiangSiJCList)) {
                //被合并患者被其他患者合并时，移除与其他患者的交叉索引和相似索引
                brDaJiaoChaSYRepository.deleteAllInBatch(xiangSiJCList);
                QBR_DA_XiangSiSYModel brDaXiangSiSYModel = QBR_DA_XiangSiSYModel.bR_DA_XiangSiSYModel;
                //删除相似索引
                brDaXiangSiSYRepository.delete((brDaXiangSiSYModel.bingRenID1.eq(dto.getXiangSiSYBRID())
                        .and(brDaXiangSiSYModel.bingRenID2.in(xiangSiJCList.stream()
                                .map(BR_DA_JiaoChaSYModel::getZhuBingRID).toList())))
                        .or(brDaXiangSiSYModel.bingRenID2.eq(dto.getXiangSiSYBRID())
                                .and(brDaXiangSiSYModel.bingRenID1.in(xiangSiJCList.stream()
                                        .map(BR_DA_JiaoChaSYModel::getZhuBingRID).toList()))));

                List<BR_DA_HeBingJLModel> beiHeBXSLHBJList = brDaHeBingJLRepository.findByBingRenIDIn(xiangSiJCList.stream().map(BR_DA_JiaoChaSYModel::getZhuBingRID).toList());
                beiHeBXSLHBJList.forEach(x -> {
                    x.setHeBingShu(x.getHeBingShu() - 1);
                });
            }
            xiangSiHBJL.setHeBingZTDM("0");
            xiangSiHBJL.setHeBingZTMC("无合并");
        }
        //endregion
        //region 修改相似索引为忽略
//        List<BR_DA_XiangSiSYModel> xiangSiSYList = brDaXiangSiSYRepository.asQuerydsl().where(x -> (x.bingRenID1.eq(dto.getZhuSuoYBRID())
//                .and(x.bingRenID2.eq(dto.getXiangSiSYBRID())))
//                .or(x.bingRenID2.eq(dto.getZhuSuoYBRID()).and(x.bingRenID1.eq(dto.getXiangSiSYBRID())))).fetch();
//
//        xiangSiSYList.forEach(x->{
//          x.setHeBingBZ(0);
//          x.setHuLueBZ(1);
//        });
        brDaXiangSiSYRepository.asUpdateDsl().where(x -> (x.bingRenID1.eq(dto.getZhuSuoYBRID())
                .and(x.bingRenID2.eq(dto.getXiangSiSYBRID())))
                .or(x.bingRenID2.eq(dto.getZhuSuoYBRID()).and(x.bingRenID1.eq(dto.getXiangSiSYBRID())))).set(s -> s.heBingBZ, 0).set(s -> s.huLueBZ, 1).update();
//        brDaXiangSiSYRepository.saveAll(xiangSiSYList);
        //endregion

        //添加操作日志
        zhuSuoYCZRZService.addCaoZuoRZ(dto.getZhuSuoYBRID(),
                zhuBingRenXX.getXingMing(),
                ZhuSuoYCZLXEnum.QUXIAOHB,
                StringUtil.concat("主索引MPI", dto.getZhuSuoYBRID(), ",取消合并MPI：", dto.getXiangSiSYBRID(), "，取消合并理由：", dto.getQuXiaoHBLY()),
                false);

        brDaHeBingJLRepository.save(zhuSuoYHBJL);
        brDaHeBingJLRepository.save(xiangSiHBJL);
        //region 似索引重新计算
        ArrayList<BR_DA_JiBenXXModel> huanZheList = new ArrayList<>() {
        };
        huanZheList.add(zhuBingRenXX);
        huanZheList.add(xiangSiBRXX);
        zengLiangPPXSHZ_Start(huanZheList, false);
        //endregion
        return true;
    }
    //todo 缓存获取数据修改

    /**
     * 增量匹配相似患者（增量）
     *
     * @return Boolean
     */
    @Override
    public String zengLiangPPXSHZ() throws ParseException {
        var jiGouID = "0";
        var jiGouMC = "通用";
        //获取筛选开始时间
        log.info("主索引增量JOB-开始时间：" + DateUtil.getCurrentDate());
        var hasGengXinSJ = springCache.get("GengXinSJ");
        var kaiShiSJ = DateUtil.getCurrentDate();
        Date gengXinSJ = DateUtil.offsetHour(DateUtil.offsetDay(DateUtil.beginOfDay(DateUtil.getCurrentDate()), -1), 1);

        if (StringUtil.hasText(hasGengXinSJ)) {
            gengXinSJ = DateUtil.parse(hasGengXinSJ);
            kaiShiSJ = gengXinSJ;
        } else {
            var brDaJiaoChaSYXX = brDaJiaoChaSYRepository.findFirstByZiDongHBBZOrderByChuangJianSJDesc(1);
            if (brDaJiaoChaSYXX != null) {
                kaiShiSJ = brDaJiaoChaSYXX.getChuangJianSJ();
            }
        }
//        var kaiShiSJ=StringUtil.hasText(hasGengXinSJ)?gengXinSJ: brDaJiaoChaSYRepository.findFirstByZiDongHBBZOrderByChuangJianSJDesc(1).getChuangJianSJ();

        var result = "";
        //region 取更新的患者列表
        record RecordJiBenXXHeBingJL(QBR_DA_JiBenXXModel jiBenXX, QBR_DA_HeBingJLModel heBingJL) {
        }
        Date finalKaiShiSJ = kaiShiSJ;
        List<BR_DA_JiBenXXModel> huanZheList = brDaJiBenXXRepository.asQuerydsl().where(x -> x.xiuGaiSJ.after(finalKaiShiSJ))
                .leftJoin(brDaHeBingJLRepository.asQuerydsl(), (x, y) -> x.id.eq(y.bingRenID), RecordJiBenXXHeBingJL::new)
                .where(x -> x.heBingJL.heBingZTDM.ne(ZhuSuoYHBZTConstants.HeBingZTDM_BHB).or(x.heBingJL.heBingZTDM.isNull()))
                .orderBy(x -> x.jiBenXX.xiuGaiSJ.asc())
                .select(x -> x.jiBenXX).fetch().stream().limit(1001)
                .toList();
        //endregion
        try {
            if (!CollectionUtils.isEmpty(huanZheList)) {
                var updateXiuGaiSJ = huanZheList.stream().sorted(Comparator.comparing(BR_DA_JiBenXXModel::getXiuGaiSJ, Comparator.nullsLast(Comparator.reverseOrder()))).findFirst().orElse(new BR_DA_JiBenXXModel()).getXiuGaiSJ();
                if (huanZheList.size() == 1001) {
                    //当患者列表的修改时间都一样时，重新取修改时间为这个时间的患者
                    if (huanZheList.get(0).getXiuGaiSJ().equals(huanZheList.get(1000).getXiuGaiSJ())) {
                        huanZheList = brDaJiBenXXRepository.asQuerydsl()
                                .leftJoin(brDaHeBingJLRepository.asQuerydsl(), (x, y) -> x.id.eq(y.bingRenID), RecordJiBenXXHeBingJL::new)
                                .where(x -> x.heBingJL.heBingZTDM.ne(ZhuSuoYHBZTConstants.HeBingZTDM_BHB).or(x.heBingJL.heBingZTDM.isNull()))
                                .orderBy(x -> x.jiBenXX.xiuGaiSJ.desc())
                                .select(x -> x.jiBenXX).fetch().stream()
                                .toList();
                    } else {
                        //防止下一个患者的修改时间和取出的患者列表最后的修改时间一样
                        List<BR_DA_JiBenXXModel> finalHuanZheList = huanZheList;
                        huanZheList = huanZheList.stream().filter(x -> !x.getXiuGaiSJ().equals(finalHuanZheList.get(1000).getXiuGaiSJ())).toList();
                    }
                }
                zengLiangPPXSHZ_Start(huanZheList, true);
                springCache.put("GengXinSJ", DateUtil.getYYMMDDHHMMSS(updateXiuGaiSJ));
            }
            result = StringUtil.concat("开始时间：", DateUtil.getYYMMDDHHmmss(kaiShiSJ), ",本次处理修改的患者：", huanZheList.size(), "人");

        } catch (Exception e) {
            springCache.put("GengXinSJ", DateUtil.getYYMMDDHHMMSS(kaiShiSJ));

//            throw;
        }
        return result;
    }

    private void zengLiangPPXSHZ_Start(List<BR_DA_JiBenXXModel> huanZheList, Boolean ziDongHB) throws TongYongYWException {
        log.info("主索引增量合并匹配处理-开始时间：" + DateUtil.getCurrentDate());
        //region 获取合并规则
        //获取合并规则
        List<GuiZeListDto> guiZeList = brZdHeBingGZRepository.asQuerydsl()
                .where(x -> x.zuZhiJGID.eq("0")).select(GuiZeListDto.class).fetch();
        List<GuiZeListDto> finalGuiZeList = guiZeList;
        List<GuiZePZDto> guiZeMXList = brZdHeBingGZMXRepository.asQuerydsl()
                .where(m -> m.zuZhiJGID.eq("0")
                        .and(m.guiZeID.in(finalGuiZeList.stream().map(GuiZeListDto::getGuiZeID).toList())))
                .select(GuiZePZDto.class).fetch();
        for (var item : guiZeList) {

            item.setGuiZePZList(guiZeMXList.stream().filter(x -> x.getGuiZeID().equals(item.getGuiZeID())).toList());
            item.setXiangSiDu(item.getFaZhi().multiply(item.getGuiZePZList().stream()
                            .map(GuiZePZDto::getQuanZhong).reduce(BigDecimal.ZERO, BigDecimal::add))
                    .divide(new BigDecimal(100)));
        }
        guiZeList = guiZeList.stream().filter(x -> !CollectionUtils.isEmpty(x.getGuiZePZList().stream().toList()))
                .sorted(Comparator.comparing(GuiZeListDto::getXiangSiDu).reversed()).toList();
        if (CollectionUtils.isEmpty(guiZeList)) {
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
        List<BR_DA_JiaoChaSYModel> addJiaoChaSYList = new ArrayList<>();
        List<BR_DA_XiangSiSYModel> addXiangSiSYList = new ArrayList<>();
        for (var bingRenXX : huanZheList) {
            if (addJiaoChaSYList.stream().anyMatch(x -> x.getGuanLianBRID().equals(bingRenXX.getId()))) {
                continue;
            }
            xiangSiPPByBRXX2(bingRenXX, xiangSiHZList, guiZeList, yuanXiangSiSYList, addXiangSiSYList, addJiaoChaSYList, yuanJiaoChaSYList, ziDongHB);
        }
        if (!CollectionUtils.isEmpty(addXiangSiSYList)) {
            brDaXiangSiSYRepository.saveAll(addXiangSiSYList);

            List<String> deleteXiangSiSYIDs = yuanXiangSiSYList.stream()
                    .filter(a -> addXiangSiSYList.stream().anyMatch(
                            b -> Objects.equals(b.getBingRenID1(), a.getBingRenID1()) && Objects.equals(b.getBingRenID2(), a.getBingRenID2())))
                    .map(StringMTEntity::getId)
                    .toList();

            if (!CollectionUtils.isEmpty(deleteXiangSiSYIDs)) {
                brDaXiangSiSYRepository.deleteAllById(deleteXiangSiSYIDs);
            }
        }
        if (!CollectionUtils.isEmpty(addJiaoChaSYList)) {
            brDaJiaoChaSYRepository.saveAll(addJiaoChaSYList);
            List<GuiZeListDto> finalGuiZeList1 = guiZeList;
            List<BR_DA_ZhuSuoYCZRZModel> caoZuoRZList = addJiaoChaSYList.stream().map(x -> {
                BR_DA_XiangSiSYModel guiZeID = addXiangSiSYList.stream()
                        .filter(m -> m.getBingRenID1().equals(x.getZhuBingRID()) && m.getBingRenID2().equals(x.getGuanLianBRID()))
                        .findFirst().orElse(null);
                var xiangXiSM = "";
                if (guiZeID != null) {
                    var guiZe = finalGuiZeList1.stream().filter(m -> m.getGuiZeID().equals(guiZeID.getGuiZeID())).findFirst().orElse(null);
                    if (guiZe != null) {
                        xiangXiSM = StringUtil.concat("，合并规则为：",
                                StringUtil.join(",", guiZe.getGuiZePZList().stream().map(o -> o.getMingCheng() + o.getQuanZhong() + "%").toList()),
                                "，阀值", guiZe.getFaZhi(), "%");
                    }
                }

                var zhuSuoYCZRZ = new BR_DA_ZhuSuoYCZRZModel();
                zhuSuoYCZRZ.setZuZhiJGID("0");
                zhuSuoYCZRZ.setZuZhiJGMC("通用");
                zhuSuoYCZRZ.setBingRenID(x.getZhuBingRID());
                zhuSuoYCZRZ.setXingMing(huanZheList.stream()
                        .filter(m -> m.getId().equals(x.getZhuBingRID())).findFirst().orElse(new BR_DA_JiBenXXModel()).getXingMing());
                zhuSuoYCZRZ.setCaoZuoLXDM(ZhuSuoYCZLXEnum.HEBING.getValue());
                zhuSuoYCZRZ.setCaoZuoLXMC(ZhuSuoYCZLXEnum.HEBING.getDescription());
                zhuSuoYCZRZ.setCaoZuoRID("0");
                zhuSuoYCZRZ.setCaoZuoRXM("系统");
                zhuSuoYCZRZ.setCaoZuoSJ(new Date());
                zhuSuoYCZRZ.setCaoZuoNR(StringUtil.concat("主索引MPI：", x.getZhuBingRID(), ",已经合并MPI：", x.getGuanLianBRID(), xiangXiSM));
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
            brDaXiangSiSYRepository.deleteAllInBatch(deleteXiangSiSYList);
            List<String> bingRenIDs = deleteXiangSiSYList.stream().map(BR_DA_XiangSiSYModel::getBingRenID1).toList();
            addHeBingJLList.stream().filter(x -> bingRenIDs.contains(x.getBingRenID())).forEach(x -> {
                long count = deleteXiangSiSYList.stream().filter(o -> o.getBingRenID1().equals(x.getBingRenID())).count();
                x.setXiangSiShu(x.getXiangSiShu() - (int) (count));
            });
        }
        if (!CollectionUtils.isEmpty(addHeBingJLList)) {
            brDaHeBingJLRepository.saveAll(addHeBingJLList);
        }
        log.info("主索引增量合并匹配处理-结束时间：" + DateUtil.getCurrentDate());
        jiSuanHBXSS(xiangSiHZList.stream().map(StringMTEntity::getId).toList());
    }

    /**
     * 计算合并相似数
     *
     * @param bingRenIDs 病人ID集合
     */
    private void jiSuanHBXSS(List<String> bingRenIDs) {
        log.info("主索引处理相似数-开始时间：" + DateUtil.getCurrentDate());
        List<BR_DA_HeBingJLModel> allHeBingJL = brDaHeBingJLRepository.findByBingRenIDIn(bingRenIDs);
        List<BR_DA_XiangSiSYModel> allXaingSiSYList = brDaXiangSiSYRepository.asQuerydsl().where(x -> x.bingRenID1.in(bingRenIDs).and(x.huLueBZ.eq(0)).and(x.heBingBZ.eq(0))).fetch();
        List<BR_DA_JiaoChaSYModel> allJiaoChaSYList = brDaJiaoChaSYRepository.findByZhuBingRIDInOrGuanLianBRIDIn(bingRenIDs, bingRenIDs);
        allHeBingJL.forEach(x -> {
            long isZhuSuoYin = allJiaoChaSYList.stream().filter(m -> m.getZhuBingRID().equals(x.getBingRenID())).count();
            long isBeiHeBing = allJiaoChaSYList.stream().filter(m -> m.getGuanLianBRID().equals(x.getBingRenID())).count();
            if (isBeiHeBing > 0) {
                x.setHeBingShu((int) isBeiHeBing);
                x.setHeBingZTDM("2");
                x.setHeBingZTMC("被合并数据");
                x.setZuiDaXSD(100);
            } else if (isZhuSuoYin > 0) {
                x.setHeBingShu((int) isZhuSuoYin);
                x.setHeBingZTDM("1");
                x.setHeBingZTMC("合并后主数据");
                x.setZuiDaXSD(100);
            } else {
                x.setHeBingShu(0);
                x.setHeBingZTDM("0");
                x.setHeBingZTMC("未合并");
                BR_DA_XiangSiSYModel xiangSiSY = allXaingSiSYList.stream()
                        .filter(m -> m.getBingRenID1().equals(x.getBingRenID()))
                        // .filter(m->m.getXiangSiDu()!=null)
                        .max(Comparator.comparing(BR_DA_XiangSiSYModel::getXiangSiDu)).orElse(null);
                if (xiangSiSY != null) {
                    x.setZuiDaXSD(xiangSiSY.getXiangSiDu() == null ? 0 : xiangSiSY.getXiangSiDu().intValue());
                } else {
                    x.setZuiDaXSD(0);
                }
            }
            x.setXiangSiShu((int) allXaingSiSYList.stream().filter(m -> m.getBingRenID1().equals(x.getBingRenID())).count());
        });
        brDaHeBingJLRepository.saveAll(allHeBingJL);
        log.info("主索引处理相似数-结束时间：" + DateUtil.getCurrentDate());
    }

    private void xiangSiPPByBRXX2(BR_DA_JiBenXXModel bingRenXX,
                                  List<BR_DA_JiBenXXModel> xiangSiHZList,
                                  List<GuiZeListDto> guiZeList,
                                  List<BR_DA_XiangSiSYModel> yuanXiangSiSYList,
                                  List<BR_DA_XiangSiSYModel> addXiangSiSYList,
                                  List<BR_DA_JiaoChaSYModel> addJiaoChaSYList,
                                  List<BR_DA_JiaoChaSYModel> yuanJiaoChaSYList,
                                  Boolean ziDongHB) {
        List<String> ignoreIds = new ArrayList<>(yuanXiangSiSYList.stream()
                .filter(x -> x.getBingRenID1().equals(bingRenXX.getId()) && x.getHuLueBZ().equals(1)).map(BR_DA_XiangSiSYModel::getBingRenID2).toList());

        ignoreIds.addAll(addXiangSiSYList.stream().filter(x -> x.getBingRenID1().equals(bingRenXX.getId())).map(BR_DA_XiangSiSYModel::getBingRenID2).toList());
        List<String> xiangSiBRIDs = new ArrayList<>();

        for (var guiZe : guiZeList) {
            var ziDongHBBZ = guiZe.getXiangSiDu().compareTo(new BigDecimal(100)) >= 0 && ziDongHB;
            //region 获取相似的患者
            List<BR_DA_JiBenXXModel> query = xiangSiHZList.stream().filter(x -> !x.getId().equals(bingRenXX.getId()) && !ignoreIds.contains(x.getId())).toList();
            for (var ziDuan : guiZe.getGuiZePZList()) {
                var value = getHuanZheValue(bingRenXX, ziDuan.getDaiMa());
                //s=>s.字段名!=null && s.字段名=上面得到的值
                query = query.stream().filter(item -> {
                    var cls = item.getClass();
                    Field fieldM = null;
                    try {
                        fieldM = cls.getDeclaredField(StringUtils.uncapitalize(ziDuan.getDaiMa()));
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        fieldM.setAccessible(true);
                        var fieldValue = fieldM.get(item);
                        if (fieldValue != null && fieldValue.equals(value))
                            return true;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    return false;
                }).toList();
            }
            var dangQianXSHZList = query.stream().toList();
            xiangSiBRIDs.addAll(dangQianXSHZList.stream().map(BR_DA_JiBenXXModel::getId).toList());
            ignoreIds.addAll(dangQianXSHZList.stream().map(BR_DA_JiBenXXModel::getId).toList());
            //endregion
            for (var dangQianXSHZ : dangQianXSHZList) {
                if (addXiangSiSYList.stream().anyMatch(x -> x.getBingRenID1().equals(bingRenXX.getId()) && x.getBingRenID2().equals(dangQianXSHZ.getId()))) {
                    continue;
                }
                //region 修改相似索引
                BR_DA_XiangSiSYModel xiangSiSY1 = yuanXiangSiSYList.stream()
                        .filter(x -> x.getBingRenID2().equals(dangQianXSHZ.getId()) && x.getBingRenID1().equals(bingRenXX.getId())).findFirst().orElse(null);
                var hasXiangSiSY1 = xiangSiSY1 == null;
                if (hasXiangSiSY1) {
                    xiangSiSY1 = new BR_DA_XiangSiSYModel();
                    xiangSiSY1.setBingRenID1(bingRenXX.getId());
                    xiangSiSY1.setBingRenID2(dangQianXSHZ.getId());
                    xiangSiSY1.setXingMing1(bingRenXX.getXingMing());
                    xiangSiSY1.setXingMing2(dangQianXSHZ.getXingMing());
                    xiangSiSY1.setXiangSiDu(guiZe.getXiangSiDu());
                    xiangSiSY1.setHeBingBZ(ziDongHBBZ ? 1 : 0);
                    xiangSiSY1.setHuLueBZ(0);
                    xiangSiSY1.setGuiZeID(guiZe.getGuiZeID());
                    xiangSiSY1.setGuiZeMC(guiZe.getGuiZeMC());
                    xiangSiSY1.setZuZhiJGID("0");
                    xiangSiSY1.setZuZhiJGMC("通用");
                    addXiangSiSYList.add(xiangSiSY1);
                } else {
                    if (!(xiangSiSY1.getGuiZeID().equals(guiZe.getGuiZeID()) && BigDecimalUtil.isGreaterOrEqual(xiangSiSY1.getXiangSiDu() == null ? new BigDecimal(0) : xiangSiSY1.getXiangSiDu(), guiZe.getXiangSiDu()))) {
                        xiangSiSY1 = new BR_DA_XiangSiSYModel();
                        xiangSiSY1.setBingRenID1(bingRenXX.getId());
                        xiangSiSY1.setBingRenID2(dangQianXSHZ.getId());
                        xiangSiSY1.setXingMing1(bingRenXX.getXingMing());
                        xiangSiSY1.setXingMing2(dangQianXSHZ.getXingMing());
                        xiangSiSY1.setXiangSiDu(guiZe.getXiangSiDu());
                        xiangSiSY1.setHeBingBZ(ziDongHBBZ ? 1 : 0);
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
                if (hasXiangSiSY2) {
                    xiangSiSY2 = new BR_DA_XiangSiSYModel();
                    xiangSiSY2.setBingRenID1(dangQianXSHZ.getId());
                    xiangSiSY2.setBingRenID2(bingRenXX.getId());
                    xiangSiSY2.setXingMing1(dangQianXSHZ.getXingMing());
                    xiangSiSY2.setXingMing2(bingRenXX.getXingMing());
                    xiangSiSY2.setXiangSiDu(guiZe.getXiangSiDu());
                    xiangSiSY2.setHeBingBZ(ziDongHBBZ ? 1 : 0);
                    xiangSiSY2.setHuLueBZ(0);
                    xiangSiSY2.setGuiZeID(guiZe.getGuiZeID());
                    xiangSiSY2.setGuiZeMC(guiZe.getGuiZeMC());
                    xiangSiSY2.setZuZhiJGID("0");
                    xiangSiSY2.setZuZhiJGMC("通用");
                    addXiangSiSYList.add(xiangSiSY2);
                } else {
                    if (!(xiangSiSY2.getGuiZeID().equals(guiZe.getGuiZeID()) && BigDecimalUtil.isGreaterOrEqual(xiangSiSY2.getXiangSiDu() == null ? new BigDecimal(0) : xiangSiSY2.getXiangSiDu(), guiZe.getXiangSiDu()))) {
                        xiangSiSY2 = new BR_DA_XiangSiSYModel();
                        xiangSiSY2.setBingRenID1(dangQianXSHZ.getId());
                        xiangSiSY2.setBingRenID2(bingRenXX.getId());
                        xiangSiSY2.setXingMing1(dangQianXSHZ.getXingMing());
                        xiangSiSY2.setXingMing2(bingRenXX.getXingMing());
                        xiangSiSY2.setXiangSiDu(guiZe.getXiangSiDu());
                        xiangSiSY2.setHeBingBZ(ziDongHBBZ ? 1 : 0);
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
                if (ziDongHBBZ) {
                    //插入本次交叉索引
                    var jiaoChaSY = new BR_DA_JiaoChaSYModel();
                    jiaoChaSY.setHeBingRID("0");
                    jiaoChaSY.setHeBingRXM("系统");
                    jiaoChaSY.setHeBingSJ(new Date());
                    jiaoChaSY.setGuanLianBRID(dangQianXSHZ.getId());
                    jiaoChaSY.setZhuBingRID(bingRenXX.getId());
                    jiaoChaSY.setZiDongHBBZ(1);
                    jiaoChaSY.setZuZhiJGID("0");
                    jiaoChaSY.setZuZhiJGMC("通用");
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
                    if (!CollectionUtils.isEmpty(xiangSiHZJCSYList)) {
                        addJiaoChaSYList.addAll(xiangSiHZJCSYList);
                    }
                }
                //endregion
            }
        }
        xiangSiBRIDs.addAll(yuanJiaoChaSYList.stream().filter(x -> x.getZhuBingRID().equals(bingRenXX.getId())).map(BR_DA_JiaoChaSYModel::getGuanLianBRID).toList());
    }

    private Object getHuanZheValue(BR_DA_JiBenXXModel huanZhe, String daiMa) {
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
    private List<BR_DA_JiBenXXModel> getXiangSiHZList(List<BR_DA_JiBenXXModel> huanZheList) {
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

        xiangSiHZList = xiangSiHZList.stream().filter(x -> !ignoreIds.contains(x.getId())).toList();
        return xiangSiHZList;
    }

    private String getNianLing(Date chuShengRQ) {
        if (ObjectUtils.isEmpty(chuShengRQ)) {
            return "";
        }
        var nianLing = new NianLing(chuShengRQ, new Date()).getAge();
        return nianLing.replaceAll("[\u4e00-\u9fa5]", " $0 ").trim();
    }
}

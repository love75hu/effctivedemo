package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.constant.ChaXunMSEnum;
import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.dto.QuanShengMZQs.PeiZhiXXDtos;
import cn.mediinfo.grus.shujuzx.dto.QuanShengMZQs.YinSiPZItem;
import cn.mediinfo.grus.shujuzx.dto.QuanShengMZQs.ZhanShiPZItem;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_YinSiPZModel;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_ZhanShiPZModel;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_YinSiPZRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_ZhanShiPZRepository;
import cn.mediinfo.grus.shujuzx.service.QuanShengMZQService;
import cn.mediinfo.starter.base.lyra.service.LyraIdentityService;
import cn.mediinfo.starter.base.util.MapUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 全生命周期
 */
@Service
public class QuanShengMZQServiceImpl implements QuanShengMZQService {
    private final EntityManager entityManager;
    private final LyraIdentityService lyraIdentityService;
    private final SC_ZD_YinSiPZRepository sc_zd_yinSiPZRepository;
    private final SC_ZD_ZhanShiPZRepository sc_zd_zhanShiPZRepository;

    public QuanShengMZQServiceImpl(
            EntityManager entityManager,
            LyraIdentityService lyraIdentityService,
            SC_ZD_YinSiPZRepository sc_zd_yinSiPZRepository,
            SC_ZD_ZhanShiPZRepository sc_zd_zhanShiPZRepository) {
        this.entityManager = entityManager;
        this.lyraIdentityService = lyraIdentityService;
        this.sc_zd_yinSiPZRepository = sc_zd_yinSiPZRepository;
        this.sc_zd_zhanShiPZRepository = sc_zd_zhanShiPZRepository;
    }

    /**
     * 根据查询模式代码获取配置信息
     *
     * @param chaXunMSDM
     * @return
     */
    @Override
    public PeiZhiXXDtos getPeiZhiXXByCXMSDM(String chaXunMSDM) {
        var result = new PeiZhiXXDtos();
        QSC_ZD_YinSiPZModel yinSiPZ = QSC_ZD_YinSiPZModel.sC_ZD_YinSiPZModel;
        var yinSiGZList = new JPAQueryFactory(sc_zd_yinSiPZRepository.getEntityManager())
                .select(yinSiPZ)
                .from(yinSiPZ)
                .where(yinSiPZ.zuZhiJGID.eq(lyraIdentityService.getJiGouID())
                        .or(yinSiPZ.zuZhiJGID.eq(ShuJuZXConstant.TONGYONG_JGID))
                        .and(yinSiPZ.qiYongBZ.eq(1)
                                .and(yinSiPZ.chaXunMSDM.eq(chaXunMSDM)
                                        .or(yinSiPZ.chaXunMSDM.eq(ChaXunMSEnum.TONG_YONG_MO_SHI.getValue())))))
                .orderBy(yinSiPZ.shunXuHao.asc())
                .fetch();
        result.setYinSiGZList(MapUtils.copyListProperties(yinSiGZList
                .stream()
                .filter(x -> Objects.equals(x.getZuZhiJGID(), lyraIdentityService.getJiGouID())
                        && Objects.equals(x.getChaXunMSDM(), chaXunMSDM))
                .toList(), YinSiPZItem::new));
        if (result.getYinSiGZList().isEmpty()) {
            result.setYinSiGZList(MapUtils.copyListProperties(yinSiGZList
                    .stream()
                    .filter(x -> Objects.equals(x.getZuZhiJGID(), ShuJuZXConstant.TONGYONG_JGID)
                            && Objects.equals(x.getChaXunMSDM(), chaXunMSDM))
                    .toList(), YinSiPZItem::new));
        }
        if (result.getYinSiGZList().isEmpty()) {
            result.setYinSiGZList(MapUtils.copyListProperties(yinSiGZList
                    .stream()
                    .filter(x -> Objects.equals(x.getZuZhiJGID(), lyraIdentityService.getJiGouID())
                            && Objects.equals(x.getChaXunMSDM(), ChaXunMSEnum.TONG_YONG_MO_SHI.getValue()))
                    .toList(), YinSiPZItem::new));
        }
        if (result.getYinSiGZList().isEmpty()) {
            result.setYinSiGZList(MapUtils.copyListProperties(yinSiGZList
                    .stream()
                    .filter(x -> Objects.equals(x.getZuZhiJGID(), ShuJuZXConstant.TONGYONG_JGID)
                            && Objects.equals(x.getChaXunMSDM(), ChaXunMSEnum.TONG_YONG_MO_SHI.getValue()))
                    .toList(), YinSiPZItem::new));
        }

        //region 展示配置
        //功能模块
        QSC_ZD_ZhanShiPZModel zhanShiPZ = QSC_ZD_ZhanShiPZModel.sC_ZD_ZhanShiPZModel;
        var zhanShiPZList = new JPAQueryFactory(sc_zd_zhanShiPZRepository.getEntityManager())
                .select(zhanShiPZ)
                .from(zhanShiPZ)
                .where(zhanShiPZ.zuZhiJGID.eq(lyraIdentityService.getJiGouID()).or(zhanShiPZ.zuZhiJGID.eq(ShuJuZXConstant.TONGYONG_JGID)))
                .where(zhanShiPZ.qiYongBZ.eq(1)
                        .and(zhanShiPZ.chaXunMSDM.eq(chaXunMSDM)
                                .or(zhanShiPZ.chaXunMSDM.eq(ChaXunMSEnum.TONG_YONG_MO_SHI.getValue()))))
                .orderBy(zhanShiPZ.shunXuHao.asc())
                .fetch();
        //1功能模块，2门诊记录，3住院记录
        var zhanShiPZLXDMs = Arrays.asList("1", "2", "3");
        List<ZhanShiPZItem> gongNengMKList = new ArrayList<>();
        for (var peiZhiLXDM : zhanShiPZLXDMs) {
            var list = MapUtils.copyListProperties(zhanShiPZList
                    .stream()
                    .filter(x -> Objects.equals(x.getZuZhiJGID(), lyraIdentityService.getJiGouID())
                            && Objects.equals(x.getPeiZhiLXDM(), peiZhiLXDM)
                            && Objects.equals(x.getChaXunMSDM(), chaXunMSDM))
                    .toList(), ZhanShiPZItem::new);
            if (list.isEmpty()) {
                list = MapUtils.copyListProperties(zhanShiPZList
                        .stream()
                        .filter(x -> Objects.equals(x.getZuZhiJGID(), ShuJuZXConstant.TONGYONG_JGID)
                                && Objects.equals(x.getPeiZhiLXDM(), peiZhiLXDM)
                                && Objects.equals(x.getChaXunMSDM(), chaXunMSDM))
                        .toList(), ZhanShiPZItem::new);
            }
            if (list.isEmpty()) {
                list = MapUtils.copyListProperties(zhanShiPZList
                        .stream()
                        .filter(x -> Objects.equals(x.getZuZhiJGID(), lyraIdentityService.getJiGouID())
                                && Objects.equals(x.getPeiZhiLXDM(), peiZhiLXDM)
                                && Objects.equals(x.getChaXunMSDM(), ChaXunMSEnum.TONG_YONG_MO_SHI.getValue()))
                        .toList(), ZhanShiPZItem::new);
            }
            if (list.isEmpty()) {
                list = MapUtils.copyListProperties(zhanShiPZList
                        .stream()
                        .filter(x -> Objects.equals(x.getZuZhiJGID(), ShuJuZXConstant.TONGYONG_JGID)
                                && Objects.equals(x.getPeiZhiLXDM(), peiZhiLXDM)
                                && Objects.equals(x.getChaXunMSDM(), ChaXunMSEnum.TONG_YONG_MO_SHI.getValue()))
                        .toList(), ZhanShiPZItem::new);
            }
            if (!list.isEmpty()) {
                gongNengMKList.addAll(list);
            }

        }
        result.setGongNengMKList(gongNengMKList);
        //endregion
        return result;
    }
}

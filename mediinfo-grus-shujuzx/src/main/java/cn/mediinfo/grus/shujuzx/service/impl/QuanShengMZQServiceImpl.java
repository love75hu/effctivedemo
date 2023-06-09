package cn.mediinfo.grus.shujuzx.service.impl;

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

    public QuanShengMZQServiceImpl(
            EntityManager entityManager,
            LyraIdentityService lyraIdentityService) {
        this.entityManager = entityManager;
        this.lyraIdentityService = lyraIdentityService;
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
        var yinSiGZList = new JPAQueryFactory(entityManager)
                .select(yinSiPZ)
                .from(yinSiPZ)
                .where(yinSiPZ.qiYongBZ.eq(1)
                        .and(yinSiPZ.chaXunMSDM.eq(chaXunMSDM)
                                .or(yinSiPZ.chaXunMSDM.eq("1")))
                        .and(yinSiPZ.zuZhiJGID.eq(lyraIdentityService.getJiGouID())
                                .or(yinSiPZ.zuZhiJGID.eq(ShuJuZXConstant.TONGYONG_JGID))))
                .orderBy(yinSiPZ.shunXuHao.asc())
                .stream()
                .toList();
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
                            && Objects.equals(x.getChaXunMSDM(), "1"))
                    .toList(), YinSiPZItem::new));
        }
        if (result.getYinSiGZList().isEmpty()) {
            result.setYinSiGZList(MapUtils.copyListProperties(yinSiGZList
                    .stream()
                    .filter(x -> Objects.equals(x.getZuZhiJGID(), ShuJuZXConstant.TONGYONG_JGID)
                            && Objects.equals(x.getChaXunMSDM(), "1"))
                    .toList(), YinSiPZItem::new));
        }

        //region 展示配置
        //功能模块
        QSC_ZD_ZhanShiPZModel zhanShiPZ = QSC_ZD_ZhanShiPZModel.sC_ZD_ZhanShiPZModel;
        var zhanShiPZList = new JPAQueryFactory(entityManager)
                .select(zhanShiPZ)
                .from(zhanShiPZ)
                .where(zhanShiPZ.qiYongBZ.eq(1)
                        .and(zhanShiPZ.chaXunMSDM.eq(chaXunMSDM)
                                .or(zhanShiPZ.chaXunMSDM.eq("1")))
                        .and(zhanShiPZ.zuZhiJGID.eq(lyraIdentityService.getJiGouID())
                                .or(zhanShiPZ.zuZhiJGID.eq(ShuJuZXConstant.TONGYONG_JGID))))
                .orderBy(zhanShiPZ.shunXuHao.asc())
                .stream()
                .toList();
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
                                && Objects.equals(x.getChaXunMSDM(), "1"))
                        .toList(), ZhanShiPZItem::new);
            }
            if (list.isEmpty()) {
                list = MapUtils.copyListProperties(zhanShiPZList
                        .stream()
                        .filter(x -> Objects.equals(x.getZuZhiJGID(), ShuJuZXConstant.TONGYONG_JGID)
                                && Objects.equals(x.getPeiZhiLXDM(), peiZhiLXDM)
                                && Objects.equals(x.getChaXunMSDM(), "1"))
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
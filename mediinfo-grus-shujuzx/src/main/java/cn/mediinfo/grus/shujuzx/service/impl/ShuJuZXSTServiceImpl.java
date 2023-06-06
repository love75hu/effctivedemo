package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.dto.ShuJuZXSTs.SC_SC_ShouCangJMXListDto;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXSTs.SC_ST_SanLiuLSTOutDto;
import cn.mediinfo.grus.shujuzx.model.QSC_LC_BingRenYLSJModel;
import cn.mediinfo.grus.shujuzx.repository.SC_LC_BingRenYLSJRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_SC_ShouCangJMXRepository;
import cn.mediinfo.grus.shujuzx.service.ShuJuZXSTService;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.lyra.service.LyraIdentityService;
import cn.mediinfo.starter.base.util.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ShuJuZXSTServiceImpl implements ShuJuZXSTService {
    public final SC_LC_BingRenYLSJRepository scLcBingRenYLSJRepository;
    public final SC_SC_ShouCangJMXRepository scScShouCangJMXRepository;
    @PersistenceContext
    private final EntityManager entityManager;
    private final LyraIdentityService lyraIdentityService;

    public ShuJuZXSTServiceImpl(SC_LC_BingRenYLSJRepository scLcBingRenYLSJRepository, SC_SC_ShouCangJMXRepository scScShouCangJMXRepository, EntityManager entityManager, LyraIdentityService lyraIdentityService) {
        this.scLcBingRenYLSJRepository = scLcBingRenYLSJRepository;
        this.scScShouCangJMXRepository = scScShouCangJMXRepository;
        this.entityManager = entityManager;
        this.lyraIdentityService = lyraIdentityService;
    }

    /**
     * 根据相关条件获取病人基本信息列表条数
     * @return
     * @throws TongYongYWException 通用异常
     */
    @Override
    public Integer getBingRenYLSJCount(String bingRenID, String zhengJianHM, String xingMing, Date jianDangKSRQ, Date jianDangJSRQ) throws TongYongYWException, ParseException {
        var qModel = QSC_LC_BingRenYLSJModel.sC_LC_BingRenYLSJModel;
        var result = new JPAQueryFactory(entityManager).select(qModel.count()).from(qModel)
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(bingRenID),qModel.bingRenID.contains(bingRenID)))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(zhengJianHM),qModel.zhengJianHM.contains(zhengJianHM)))
                .where(QueryDSLUtils.whereIf(jianDangKSRQ!=null,qModel.jianDangSJ.goe(jianDangKSRQ)))
                .where(QueryDSLUtils.whereIf(jianDangJSRQ!=null,qModel.jianDangSJ.loe(DateUtil.getLastDian(jianDangJSRQ))))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(xingMing),
                        qModel.xingMing.contains(xingMing).or(
                                Objects.equals(lyraIdentityService.getShuRuMLX(), "1") ?
                                        qModel.shuRuMA1.toUpperCase().contains(xingMing.toUpperCase())
                                        : Objects.equals(lyraIdentityService.getShuRuMLX(), "2") ?
                                        qModel.shuRuMA2.toUpperCase().contains(xingMing.toUpperCase())
                                        :qModel.shuRuMA3.toUpperCase().contains(xingMing.toUpperCase()))
                        ))

                .fetchOne();
        if (result != null) {
            return result.intValue();
        }
        return 0;
    }

    /**
     * 根据相关条件获取病人基本信息列表
     * @return
     * @throws TongYongYWException 通用异常
     */
    @Override
    public List<SC_ST_SanLiuLSTOutDto> getBingRenYLSJList(String bingRenID, String zhengJianHM, String xingMing, Date jianDangKSRQ, Date jianDangJSRQ, Integer pageIndex, Integer pageSize) throws TongYongYWException, ParseException {
        var qModel = QSC_LC_BingRenYLSJModel.sC_LC_BingRenYLSJModel;
        pageIndex = pageIndex == null ?1 :pageIndex;
        pageSize = pageSize == null ?15:pageSize;
        var models = new JPAQueryFactory(entityManager).select(qModel).from(qModel)
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(bingRenID),qModel.bingRenID.contains(bingRenID)))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(zhengJianHM),qModel.zhengJianHM.contains(zhengJianHM)))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(xingMing),
                        qModel.xingMing.contains(xingMing).or(
                                Objects.equals(lyraIdentityService.getShuRuMLX(), "1") ?
                                        qModel.shuRuMA1.toUpperCase().contains(xingMing.toUpperCase())
                                        : Objects.equals(lyraIdentityService.getShuRuMLX(), "2") ?
                                        qModel.shuRuMA2.toUpperCase().contains(xingMing.toUpperCase())
                                        :qModel.shuRuMA3.toUpperCase().contains(xingMing.toUpperCase()))
                ))
                .where(QueryDSLUtils.whereIf(jianDangKSRQ!=null,qModel.jianDangSJ.goe(jianDangKSRQ)))
                .where(QueryDSLUtils.whereIf(jianDangJSRQ!=null,qModel.jianDangSJ.loe(DateUtil.getLastDian(jianDangJSRQ))))
                .orderBy(qModel.jianDangSJ.desc(),qModel.bingRenID.asc())
                .offset(PageRequestUtil.of(pageIndex, pageSize).getOffset()).limit(pageSize)
                .fetch();
        var result = MapUtils.copyListProperties(models,SC_ST_SanLiuLSTOutDto::new);
        //收藏夹明细
        var shouChangJMXDtos = MapUtils.copyListProperties(scScShouCangJMXRepository.findByShouCangJID(lyraIdentityService.getYongHuId()), SC_SC_ShouCangJMXListDto::new);
        for (var brjbxx :result){
            brjbxx.setShouCangJiaList(shouChangJMXDtos.stream().filter(x-> Objects.equals(x.getBingRenID(), brjbxx.getBingRenID())).toList());
        }
        return result;
    }
}

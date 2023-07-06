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
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        Date jianDangJSRQLast;
        if(jianDangJSRQ!=null){
            jianDangJSRQLast = DateUtil.getLastDian(jianDangJSRQ);
        } else {
            jianDangJSRQLast = null;
        }
        var result = scLcBingRenYLSJRepository.asQuerydsl()
                .whereIf(StringUtils.hasText(bingRenID),o->o.bingRenID.contains(bingRenID))
                .whereIf(StringUtils.hasText(zhengJianHM),o->o.zhengJianHM.contains(zhengJianHM))
                .whereIf(jianDangKSRQ!=null,o->o.jianDangSJ.goe(jianDangKSRQ))
                .whereIf(jianDangJSRQLast!=null,o->o.jianDangSJ.loe(jianDangJSRQLast))
                .whereIf(StringUtils.hasText(xingMing),o->
                        o.xingMing.contains(xingMing).or(
                                Objects.equals(lyraIdentityService.getShuRuMLX(), "1") ?
                                        o.shuRuMA1.toUpperCase().contains(xingMing.toUpperCase())
                                        : Objects.equals(lyraIdentityService.getShuRuMLX(), "2") ?
                                        o.shuRuMA2.toUpperCase().contains(xingMing.toUpperCase())
                                        :o.shuRuMA3.toUpperCase().contains(xingMing.toUpperCase()))
                )
                .select(SimpleExpression::count)
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
        pageIndex = pageIndex == null ?1 :pageIndex;
        pageSize = pageSize == null ?15:pageSize;
        Date finalJianDangJSRQ;
        if(jianDangJSRQ!=null){
            finalJianDangJSRQ = DateUtil.getLastDian(jianDangJSRQ);
        } else {
            finalJianDangJSRQ = jianDangJSRQ;
        }
        var models = scLcBingRenYLSJRepository.asQuerydsl()
                .whereIf(StringUtils.hasText(bingRenID),o->o.bingRenID.contains(bingRenID))
                .whereIf(StringUtils.hasText(zhengJianHM),o->o.zhengJianHM.contains(zhengJianHM))
                .whereIf(StringUtils.hasText(xingMing),
                        o->o.xingMing.contains(xingMing).or(
                                Objects.equals(lyraIdentityService.getShuRuMLX(), "1") ?
                                        o.shuRuMA1.toUpperCase().contains(xingMing.toUpperCase())
                                        : Objects.equals(lyraIdentityService.getShuRuMLX(), "2") ?
                                        o.shuRuMA2.toUpperCase().contains(xingMing.toUpperCase())
                                        :o.shuRuMA3.toUpperCase().contains(xingMing.toUpperCase()))
                )
                .whereIf(jianDangKSRQ!=null,o->o.jianDangSJ.goe(jianDangKSRQ))
                .whereIf(jianDangJSRQ!=null,o-> o.jianDangSJ.loe(finalJianDangJSRQ))
                .orderBy(o->o.jianDangSJ.desc())
                .orderBy(o->o.bingRenID.asc())
                .fetchPage(pageIndex,pageSize);
        var result = MapUtils.copyListProperties(models,SC_ST_SanLiuLSTOutDto::new);
        //收藏夹明细
        var shouChangJMXDtos = MapUtils.copyListProperties(scScShouCangJMXRepository.findByShouCangJID(lyraIdentityService.getYongHuId()), SC_SC_ShouCangJMXListDto::new);
        for (var brjbxx :result){
            brjbxx.setShouCangJiaList(shouChangJMXDtos.stream().filter(x-> Objects.equals(x.getBingRenID(), brjbxx.getBingRenID())).toList());
        }
        return result;
    }
}

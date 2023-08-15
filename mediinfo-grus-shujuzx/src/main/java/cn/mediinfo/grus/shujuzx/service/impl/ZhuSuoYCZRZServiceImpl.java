package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.constant.ZhuSuoYCZLXEnum;
import cn.mediinfo.grus.shujuzx.dto.shujuzxzsys.BR_DA_ZhuSuoYCZRZDto;
import cn.mediinfo.grus.shujuzx.model.BR_DA_ZhuSuoYCZRZModel;
import cn.mediinfo.grus.shujuzx.repository.BR_DA_ZhuSuoYCZRZRepository;
import cn.mediinfo.grus.shujuzx.service.ZhuSuoYCZRZService;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import cn.mediinfo.cyan.msf.core.util.*;
import com.querydsl.core.types.dsl.SimpleExpression;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 主索引操作日志
 */
@Service
public class ZhuSuoYCZRZServiceImpl implements ZhuSuoYCZRZService {
    @PersistenceContext
    private final EntityManager entityManager;
    private final LyraIdentityService lyraIdentityService;
    private final BR_DA_ZhuSuoYCZRZRepository brDaZhuSuoYCZRZRepository;

    public ZhuSuoYCZRZServiceImpl(EntityManager entityManager, LyraIdentityService lyraIdentityService, BR_DA_ZhuSuoYCZRZRepository brDaZhuSuoYCZRZRepository) {
        this.entityManager = entityManager;
        this.lyraIdentityService = lyraIdentityService;
        this.brDaZhuSuoYCZRZRepository = brDaZhuSuoYCZRZRepository;
    }

    @Override
    public List<BR_DA_ZhuSuoYCZRZDto> getZhuSuoYCZRZList(Integer pageIndex, Integer pageSize, Date caoZuoKSRQ, Date caoZuoJSRQ, String caoZuoLXDM, String likeQuery) throws TongYongYWException, ParseException {
        if(pageSize == null ||pageSize==0){
            pageSize = 10;
        }
        var zhuSuoYCZRZModels = brDaZhuSuoYCZRZRepository.asQuerydsl()
                .whereIf(caoZuoKSRQ!=null,o-> {
                    try {
                        return o.caoZuoSJ.goe(DateUtil.get0Dian(caoZuoKSRQ));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .whereIf(caoZuoJSRQ!=null, o-> {
                    try {
                        return o.caoZuoSJ.loe(DateUtil.getLastDian(caoZuoJSRQ));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .whereIf(StringUtils.hasText(caoZuoLXDM),o->o.caoZuoLXDM.eq(caoZuoLXDM))
                .whereIf(StringUtils.hasText(likeQuery),o->o.bingRenID.contains(likeQuery).or(o.xingMing.contains(likeQuery)).or(o.caoZuoRXM.contains(likeQuery)))
                .orderBy(o->o.caoZuoSJ.desc())
                .select(o->o)
                .fetchPage(PageRequestUtil.of(pageIndex, pageSize));
        return MapUtils.copyListProperties(zhuSuoYCZRZModels, BR_DA_ZhuSuoYCZRZDto::new);
    }

    /**
     * 获取主索引操作日志数量
     * @return
     * @throws TongYongYWException 通用异常
     */
    @Override
    public long getZhuSuoYCZRZCount(String caoZuoKSRQ, String caoZuoJSRQ, String caoZuoLXDM, String likeQuery) throws TongYongYWException, ParseException {
        Date finalCaoZuoKSRQ = DateUtil.getDateYYMMDDHHMMSS2(caoZuoKSRQ);
        Date finalcaoZuoJSRQ = DateUtil.getDateYYMMDDHHMMSS2(caoZuoJSRQ);
        return brDaZhuSuoYCZRZRepository.asQuerydsl()
                .whereIf(finalCaoZuoKSRQ!=null,o->o.caoZuoSJ.goe(finalCaoZuoKSRQ))
                .whereIf(finalcaoZuoJSRQ!=null, o->o.caoZuoSJ.loe(finalcaoZuoJSRQ))
                .whereIf(StringUtils.hasText(caoZuoLXDM),o->o.caoZuoLXDM.eq(caoZuoLXDM))
                .whereIf(StringUtils.hasText(likeQuery),o->o.bingRenID.contains(likeQuery).or(o.xingMing.contains(likeQuery)).or(o.caoZuoRXM.contains(likeQuery)))
                .select(SimpleExpression::count)
                .fetchOne();
    }

    @Override
    public Boolean addCaoZuoRZ(String bingRenID, String xingMing, ZhuSuoYCZLXEnum caoZuoLX, String caoZuoNR, Boolean saveChanges) throws TongYongYWException {
        var model = new BR_DA_ZhuSuoYCZRZModel();
        model.setBingRenID(bingRenID);
        model.setCaoZuoLXDM(caoZuoLX.getValue());
        model.setCaoZuoLXMC(caoZuoLX.getDescription());
        model.setCaoZuoRXM(lyraIdentityService.getUserName());
        model.setCaoZuoRID(lyraIdentityService.getYongHuId());
        model.setCaoZuoSJ(new Date());
        model.setZuZhiJGID(lyraIdentityService.getJiGouID());
        model.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
        model.setCaoZuoNR(caoZuoNR);
        model.setXingMing(xingMing);
        brDaZhuSuoYCZRZRepository.save(model);//saveChanges
        return null;
    }
}

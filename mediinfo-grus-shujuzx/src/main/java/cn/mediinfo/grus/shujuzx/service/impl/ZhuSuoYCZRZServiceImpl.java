package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.constant.ZhuSuoYCZLXEnum;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs.BR_DA_ZhuSuoYCZRZDto;
import cn.mediinfo.grus.shujuzx.model.BR_DA_ZhuSuoYCZRZModel;
import cn.mediinfo.grus.shujuzx.model.QBR_DA_ZhuSuoYCZRZModel;
import cn.mediinfo.grus.shujuzx.repository.BR_DA_ZhuSuoYCZRZRepository;
import cn.mediinfo.grus.shujuzx.service.ZhuSuoYCZRZService;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.lyra.service.LyraIdentityService;
import cn.mediinfo.starter.base.util.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.print.DocFlavor;
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
    public List<BR_DA_ZhuSuoYCZRZDto> getZhuSuoYCZRZList(Integer page, Integer pageSize, String caoZuoKSRQ, String caoZuoJSRQ, String caoZuoLXDM, String likeQuery) throws TongYongYWException, ParseException {
        Date finalCaoZuoKSRQ = DateUtil.getDateYYMMDDHHMMSS2(caoZuoKSRQ);
        Date finalcaoZuoJSRQ = DateUtil.getDateYYMMDDHHMMSS2(caoZuoJSRQ);
        if(pageSize == null ||pageSize==0){
            pageSize = 10;
        }
        var zhuSuoYCZRZModels = brDaZhuSuoYCZRZRepository.asQuerydsl()
                .whereIf(finalCaoZuoKSRQ!=null,o->o.caoZuoSJ.goe(finalCaoZuoKSRQ))
                .whereIf(finalcaoZuoJSRQ!=null, o->o.caoZuoSJ.loe(finalcaoZuoJSRQ))
                .whereIf(StringUtils.hasText(caoZuoLXDM),o->o.caoZuoLXDM.eq(caoZuoLXDM))
                .whereIf(StringUtils.hasText(likeQuery),o->o.bingRenID.contains(likeQuery).or(o.xingMing.contains(likeQuery)).or(o.caoZuoRXM.contains(likeQuery)))
                .orderBy(o->o.caoZuoSJ.desc())
                .select(o->o)
                .fetchPage(PageRequestUtil.of(page, pageSize));
        return MapUtils.copyListProperties(zhuSuoYCZRZModels, BR_DA_ZhuSuoYCZRZDto::new);
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

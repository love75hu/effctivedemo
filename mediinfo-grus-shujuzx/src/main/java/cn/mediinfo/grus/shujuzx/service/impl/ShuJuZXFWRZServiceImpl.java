package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.util.DateUtil;
import cn.mediinfo.cyan.msf.core.util.MapUtils;
import cn.mediinfo.cyan.msf.core.util.PageRequestUtil;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.orm.util.QueryDSLUtils;
import cn.mediinfo.grus.shujuzx.dto.shujuzxfwrz.AddFangWenRZDto;
import cn.mediinfo.grus.shujuzx.dto.shujuzxfwrz.ShuJuZXFWRZDto;
import cn.mediinfo.grus.shujuzx.model.QSC_RZ_FangWenLCSJModel;
import cn.mediinfo.grus.shujuzx.model.SC_RZ_FangWenLCSJModel;
import cn.mediinfo.grus.shujuzx.repository.SC_RZ_FangWenLCSJRepository;
import cn.mediinfo.grus.shujuzx.service.ShuJuZXFWRZService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/*
 * 360访问日志
 * */
@Service
public class ShuJuZXFWRZServiceImpl implements ShuJuZXFWRZService {

    private final LyraIdentityService lyraIdentityService;
    private final SC_RZ_FangWenLCSJRepository sc_rz_fangWenLCSJRepository;
    private final EntityManager entityManager;


    public ShuJuZXFWRZServiceImpl(LyraIdentityService lyraIdentityService,
                                  SC_RZ_FangWenLCSJRepository sc_rz_fangWenLCSJRepository, EntityManager entityManager) {
        this.lyraIdentityService = lyraIdentityService;
        this.sc_rz_fangWenLCSJRepository = sc_rz_fangWenLCSJRepository;
        this.entityManager = entityManager;
    }

    /*
     * 添加访问日志
     * */
    @Override
    public Boolean addFangWenRZ(AddFangWenRZDto addFangWenRZDto) {
        var zuZhiJGID = lyraIdentityService.getJiGouID();
        var jiGouMC = lyraIdentityService.getJiGouMC();
        var userID = lyraIdentityService.getYongHuId();
        var userName = lyraIdentityService.getUserName();
        var fangWenRZ = new SC_RZ_FangWenLCSJModel();
        fangWenRZ.setZuZhiJGID(zuZhiJGID);
        fangWenRZ.setZuZhiJGMC(jiGouMC);
        fangWenRZ.setFangWenRID(userID);
        fangWenRZ.setFangWenRXM(userName);
        fangWenRZ.setFangWenSJ(new Date());
        MapUtils.mergeProperties(addFangWenRZDto,fangWenRZ,true);
        sc_rz_fangWenLCSJRepository.save(fangWenRZ);
        return true;
    }

    @Override
    public Integer getFangWenRZCount(Date fangWenKSRQ, Date fangWenJSRQ, String bingRenID, String xingMing, String fangWenRXM) {
        QSC_RZ_FangWenLCSJModel sjModel=QSC_RZ_FangWenLCSJModel.sC_RZ_FangWenLCSJModel;
        return new JPAQueryFactory(sc_rz_fangWenLCSJRepository.getEntityManager())
                .select(sjModel)
                .from(sjModel)
                .where(QueryDSLUtils.whereIf(fangWenKSRQ != null, ()->sjModel.fangWenSJ.goe(fangWenKSRQ)))
                .where(QueryDSLUtils.whereIf(fangWenJSRQ != null, ()->sjModel.fangWenSJ.lt(DateUtil.dateAddDays(fangWenJSRQ, 1))))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(bingRenID), ()->sjModel.bingRenID.eq(bingRenID)))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(xingMing), ()->sjModel.xingMing.eq(xingMing)))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(fangWenRXM), ()->sjModel.fangWenRXM.eq(fangWenRXM)))
                .fetch().size();
    }

    @Override
    public List<ShuJuZXFWRZDto> getFangWenRZList(Date fangWenKSRQ, Date fangWenJSRQ, String bingRenID, String xingMing, String fangWenRXM, Integer pageIndex, Integer pageSize) {
        QSC_RZ_FangWenLCSJModel sjModel = QSC_RZ_FangWenLCSJModel.sC_RZ_FangWenLCSJModel;
        List<SC_RZ_FangWenLCSJModel> list = new JPAQueryFactory(sc_rz_fangWenLCSJRepository.getEntityManager())
                .select(sjModel)
                .from(sjModel)
                .where(QueryDSLUtils.whereIf(fangWenKSRQ != null, ()->sjModel.fangWenSJ.goe(fangWenKSRQ)))
                .where(QueryDSLUtils.whereIf(fangWenJSRQ != null, ()->sjModel.fangWenSJ.lt(DateUtil.dateAddDays(fangWenJSRQ, 1))))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(bingRenID), ()->sjModel.bingRenID.eq(bingRenID)))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(xingMing), ()->sjModel.xingMing.contains(xingMing)))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(fangWenRXM), ()->sjModel.fangWenRXM.contains(fangWenRXM)))
                .orderBy(sjModel.fangWenSJ.desc())
                .offset(PageRequestUtil.of(pageIndex, pageSize).getOffset()).limit(pageSize)
                .fetch();
        return CollectionUtils.isEmpty(list) ? Collections.emptyList() : MapUtils.copyListProperties(list, ShuJuZXFWRZDto::new);
    }
}

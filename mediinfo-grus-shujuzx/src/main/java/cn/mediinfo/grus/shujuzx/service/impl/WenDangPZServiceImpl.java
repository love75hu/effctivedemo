package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.util.MapUtils;
import cn.mediinfo.cyan.msf.core.util.PageRequestUtil;
import cn.mediinfo.cyan.msf.orm.util.QueryDSLUtils;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangDto;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangGreaterDto;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_WenDangModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_WenDangModel;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_WenDangRepository;
import cn.mediinfo.grus.shujuzx.service.WenDangPZService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service
public class WenDangPZServiceImpl implements WenDangPZService {

    private final LyraIdentityService lyraIdentityService;
    private final SC_ZD_WenDangRepository sc_zd_wenDangRepository;
    private final EntityManager entityManager;


    public WenDangPZServiceImpl(LyraIdentityService lyraIdentityService,
                                SC_ZD_WenDangRepository sc_zd_wenDangRepository, EntityManager entityManager) {
        this.lyraIdentityService = lyraIdentityService;
        this.sc_zd_wenDangRepository = sc_zd_wenDangRepository;
        this.entityManager = entityManager;
    }

    /**
     * 获取文档配置列表
     */
    @Override
    public List<SC_ZD_WenDangDto> getWenDangPZList(String leiBieDM, String likeQuery, Integer pageIndex, Integer pageSize) {
        QSC_ZD_WenDangModel sjModel = QSC_ZD_WenDangModel.sC_ZD_WenDangModel;
        List<SC_ZD_WenDangModel> list = new JPAQueryFactory(sc_zd_wenDangRepository.getEntityManager())
                .select(sjModel)
                .from(sjModel)
                .where(QueryDSLUtils.whereIf(StringUtils.hasText(leiBieDM), () -> sjModel.leiBieDM.eq(leiBieDM)))
                .where(QueryDSLUtils.whereIf(StringUtils.hasText(likeQuery), () -> sjModel.wenDangID.contains(likeQuery).or(sjModel.wenDangMC.contains(likeQuery))))
                .orderBy(sjModel.shunXuHao.asc())
                .offset(PageRequestUtil.of(pageIndex, pageSize).getOffset()).limit(pageSize)
                .fetch();
        return CollectionUtils.isEmpty(list) ? Collections.emptyList() : MapUtils.copyListProperties(list, SC_ZD_WenDangDto::new);
    }

    /**
     * 获取文档配置数量
     */
    @Override
    public long getWenDangPZCount(String leiBieDM, String likeQuery) {
        QSC_ZD_WenDangModel sjModel = QSC_ZD_WenDangModel.sC_ZD_WenDangModel;
        var Count = new JPAQueryFactory(sc_zd_wenDangRepository.getEntityManager())
                .select(sjModel)
                .from(sjModel)
                .where(QueryDSLUtils.whereIf(StringUtils.hasText(leiBieDM), () -> sjModel.leiBieDM.eq(leiBieDM)))
                .where(QueryDSLUtils.whereIf(StringUtils.hasText(likeQuery), () -> sjModel.wenDangID.contains(likeQuery).or(sjModel.wenDangMC.contains(likeQuery))))
                .fetch().size();
        return Count;
    }

    /**
     * 新增文档配置
     */
    @Override
    public String addWenDangPZ(SC_ZD_WenDangGreaterDto wenDangGreaterDto) {
        var zuZhiJGID = lyraIdentityService.getJiGouID();
        var jiGouMC = lyraIdentityService.getJiGouMC();
        var WenDang = new SC_ZD_WenDangModel();
        WenDang.setZuZhiJGID(zuZhiJGID);
        WenDang.setZuZhiJGMC(jiGouMC);
        MapUtils.mergeProperties(wenDangGreaterDto, WenDang, true);
        sc_zd_wenDangRepository.save(WenDang);
        return WenDang.getId();
    }

}

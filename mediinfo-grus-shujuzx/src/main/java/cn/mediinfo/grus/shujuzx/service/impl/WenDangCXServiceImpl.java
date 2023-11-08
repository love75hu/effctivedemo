package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.util.MapUtils;
import cn.mediinfo.cyan.msf.core.util.PageRequestUtil;
import cn.mediinfo.cyan.msf.orm.util.QueryDSLUtils;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangDto;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_WenDangModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_WenDangModel;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_WenDangRepository;
import cn.mediinfo.grus.shujuzx.service.WenDangCXService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service
public class WenDangCXServiceImpl implements WenDangCXService {
    private final LyraIdentityService lyraIdentityService;
    private final SC_ZD_WenDangRepository sc_zd_wenDangRepository;
    public WenDangCXServiceImpl (LyraIdentityService lyraIdentityService,SC_ZD_WenDangRepository sc_zd_wenDangRepository){
        this.lyraIdentityService = lyraIdentityService;
        this.sc_zd_wenDangRepository = sc_zd_wenDangRepository;
    }
    /**
     * 获取文档列表
     */
    @Override
    public List<SC_ZD_WenDangDto> getWenDangList(String leiBieDM, String likeQuery, Integer pageIndex, Integer pageSize) {
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
}

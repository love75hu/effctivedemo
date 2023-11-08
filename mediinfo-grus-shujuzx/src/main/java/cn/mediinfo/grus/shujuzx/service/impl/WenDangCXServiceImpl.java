package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.util.MapUtils;
import cn.mediinfo.cyan.msf.core.util.PageRequestUtil;
import cn.mediinfo.cyan.msf.orm.util.QueryDSLUtils;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangDto;
import cn.mediinfo.grus.shujuzx.dto.wendangjls.SC_GW_JiLuXXListDto;
import cn.mediinfo.grus.shujuzx.model.QSC_GW_JiLuXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_WenDangModel;
import cn.mediinfo.grus.shujuzx.repository.SC_GW_JiLuNRRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_GW_JiLuXXRepository;
import cn.mediinfo.grus.shujuzx.service.WenDangCXService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;

@Service
public class WenDangCXServiceImpl implements WenDangCXService {
    private final LyraIdentityService lyraIdentityService;
    private final SC_GW_JiLuNRRepository sc_gw_jiLuNRRepository;
    private final SC_GW_JiLuXXRepository sc_gw_jiLuXXRepository;
    public WenDangCXServiceImpl (LyraIdentityService lyraIdentityService,SC_GW_JiLuNRRepository sc_gw_jiLuNRRepository,SC_GW_JiLuXXRepository sc_gw_jiLuXXRepository){
        this.lyraIdentityService = lyraIdentityService;
        this.sc_gw_jiLuNRRepository = sc_gw_jiLuNRRepository;
        this.sc_gw_jiLuXXRepository = sc_gw_jiLuXXRepository;
    }
    /**
     * 获取文档记录列表
     */
    @Override
    public List<SC_GW_JiLuXXListDto> getWenDangJLList(String wenDangID, String mpi,Date startTime, Date endTime,  Integer pageIndex, Integer pageSize) {
        pageIndex = pageIndex == null ? 1 : pageIndex;
        pageSize = pageSize == null ? 10 : pageSize;
        var result = sc_gw_jiLuXXRepository.asQuerydsl().where(x->x.wenDangID.eq(wenDangID))
                .whereIf(Objects.nonNull(startTime), r -> r.shengChengSJ.goe(startTime))
                .whereIf(Objects.nonNull(endTime), r -> r.shengChengSJ.lt(endTime))
                .orderBy(x->x.shengChengSJ.desc())
                .select(SC_GW_JiLuXXListDto.class)
                .fetchPage(pageIndex,pageSize);
        return result;
    }
}

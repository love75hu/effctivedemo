package cn.mediinfo.grus.shujuzx.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.core.util.CollectorUtil;
import cn.mediinfo.cyan.msf.core.util.MapUtils;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.dto.chaxunfaxx.FangAnCXLSDto;
import cn.mediinfo.grus.shujuzx.dto.chaxunfaxx.FangAnXXDto;
import cn.mediinfo.grus.shujuzx.dto.fangancxls.FangAnCXLSDTO;
import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnCXLSModel;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_FangAnCXLSRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_FangAnNRRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_FangAnXXRepository;
import cn.mediinfo.grus.shujuzx.request.FangAnCXLSSaveRequest;
import cn.mediinfo.grus.shujuzx.service.ChaXunFAXXService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChaXunFAXXServiceImpl implements ChaXunFAXXService {
    private final SC_CX_FangAnXXRepository fangAnXXRepository;
    private final SC_CX_FangAnNRRepository fangAnNRRepository;
    private final SC_CX_FangAnCXLSRepository fangAnCXLSRepository;
    private final LyraIdentityService lyraIdentityService;

    public ChaXunFAXXServiceImpl(SC_CX_FangAnXXRepository fangAnXXRepository,
                                 SC_CX_FangAnNRRepository fangAnNRRepository,
                                 SC_CX_FangAnCXLSRepository fangAnCXLSRepository,
                                 LyraIdentityService lyraIdentityService) {
        this.fangAnXXRepository = fangAnXXRepository;
        this.fangAnNRRepository = fangAnNRRepository;
        this.fangAnCXLSRepository = fangAnCXLSRepository;
        this.lyraIdentityService = lyraIdentityService;
    }

    @Override
    public List<FangAnXXDto> getChaXunFAXXSelect(String likeQuery, String fangAnLXDM, Integer pageIndex, Integer pageSize) {
        return fangAnXXRepository.getFangAnXX(likeQuery, fangAnLXDM, pageIndex, pageSize);
    }

    @Override
    public List<FangAnXXDto> getFangAnXXList(String likeQuery, Integer pageIndex, Integer pageSize) {
        return fangAnXXRepository.getFangAnXX(likeQuery, "", pageIndex, pageSize);
    }

    @Override
    public List<FangAnCXLSDto> getFangAnCXLSList(Integer pageIndex, Integer pageSize) {
        return BeanUtil.copyListProperties(fangAnCXLSRepository.findAll()
                        .stream().sorted(Comparator.comparing(SC_CX_FangAnCXLSModel::getChuangJianSJ, Comparator.reverseOrder()))
                        .collect(CollectorUtil.page(pageIndex, pageSize)),
                FangAnCXLSDto::new, (model, dto) -> {
                    if (model.getChaXunLXDM().equals("2")) {
                        dto.setBiaoTiMC(StringUtil.concat("高级查询", model.getGuanJianZi()));
                    } else {
                        dto.setBiaoTiMC(StringUtil.concat(model.getFangAnMC(), model.getGuanJianZi()));
                    }
                    dto.setId(model.getId());
                    dto.setFangAnID(model.getFangAnID());
                    dto.setChaXunTJMS(model.getChaXunTJMS());
                });
    }

    /**
     * 保存查询方案历史记录
     *
     * @param request
     * @param sql
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveFangAnCXLS(FangAnCXLSSaveRequest request, String sql) {
        String zuZhiJGID = lyraIdentityService.getJiGouID();
        String chaXunLXDM = "2";
        String chaXunLXMC = "高级查询";
        if (StringUtils.hasText(request.getFangAnId()) || CollUtil.isNotEmpty(request.getFangAnSCList())) {
            chaXunLXDM = "1";
            chaXunLXMC = "方案查询";
        }
        SC_CX_FangAnCXLSModel model = new SC_CX_FangAnCXLSModel();
        MapUtils.mergeProperties(request, model);
        model.setZuZhiJGID(Optional.ofNullable(lyraIdentityService.getJiGouID()).orElse(ShuJuZXConstant.TONGYONG_JGID));
        model.setZuZhiJGMC(Optional.ofNullable(lyraIdentityService.getJiGouMC()).orElse(ShuJuZXConstant.TONGYONG_JGMC));
        model.setChaXunLXDM(chaXunLXDM);
        model.setChaXunLXMC(chaXunLXMC);
        if (chaXunLXDM.equals("1")) {
            model.setChaXunSQL(sql);
        }
        model.setChaXunSJ(new Date());
        model.setChaXunRID(lyraIdentityService.getYongHuId());
        model.setChaXunRXM(lyraIdentityService.getUserName());
        var result = fangAnCXLSRepository.save(model);

        return result.getId();
    }

    /**
     * 根据主键ID获取方案查询历史
     *
     * @param id
     * @return
     */
    @Override
    public FangAnCXLSDTO getFangAnCXLSByID(String id) {
        FangAnCXLSDTO result = new FangAnCXLSDTO();
        SC_CX_FangAnCXLSModel model = fangAnCXLSRepository.findById(id).orElse(null);
        if (model==null) {
            return result;
        }
        MapUtils.copyProperties(model, result);
        return result;
    }

    @Override
    public Boolean deleteFangAnCXLS(String id) {
        fangAnCXLSRepository.deleteById(id);
        return true;
    }

    @Override
    public Boolean deleteChaXunFA(String id) {
        var fangAnQuery = fangAnXXRepository.findById(id);
        if (fangAnQuery.isPresent()) {
            var fangAnXX = fangAnQuery.get();
            fangAnXXRepository.deleteById(id);
            fangAnNRRepository.asDeleteDsl().where(p -> p.zuZhiJGID.eq(fangAnXX.getZuZhiJGID()).and(p.fangAnID.eq(fangAnXX.getFangAnID()))).execute();
        }
        return true;
    }
}

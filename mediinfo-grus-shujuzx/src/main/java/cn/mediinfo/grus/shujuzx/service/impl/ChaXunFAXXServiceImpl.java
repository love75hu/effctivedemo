package cn.mediinfo.grus.shujuzx.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.mediinfo.cyan.msf.core.util.*;
import cn.mediinfo.grus.shujuzx.dto.chaxunfaxx.FangAnCXLSDto;
import cn.mediinfo.grus.shujuzx.dto.chaxunfaxx.FangAnSelectXXDto;
import cn.mediinfo.grus.shujuzx.dto.chaxunfaxx.FangAnXXDto;
import cn.mediinfo.grus.shujuzx.dto.fangan.FangAnSCDTO;
import cn.mediinfo.grus.shujuzx.dto.fangancxls.FangAnCXLSDTO;
import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnCXLSModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnSCModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnXXModel;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_FangAnCXLSRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_FangAnNRRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_FangAnSCRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_FangAnXXRepository;
import cn.mediinfo.grus.shujuzx.request.FangAnCXLSSaveRequest;
import cn.mediinfo.grus.shujuzx.service.ChaXunFAXXService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class ChaXunFAXXServiceImpl implements ChaXunFAXXService {
    private final SC_CX_FangAnXXRepository fangAnXXRepository;
    private final SC_CX_FangAnNRRepository fangAnNRRepository;
    private final SC_CX_FangAnCXLSRepository fangAnCXLSRepository;
    private final SC_CX_FangAnSCRepository fangAnSCRepository;
    private final LyraIdentityService lyraIdentityService;

    public ChaXunFAXXServiceImpl(SC_CX_FangAnXXRepository fangAnXXRepository,
                                 SC_CX_FangAnNRRepository fangAnNRRepository,
                                 SC_CX_FangAnCXLSRepository fangAnCXLSRepository,
                                 SC_CX_FangAnSCRepository fangAnSCRepository, LyraIdentityService lyraIdentityService) {
        this.fangAnXXRepository = fangAnXXRepository;
        this.fangAnNRRepository = fangAnNRRepository;
        this.fangAnCXLSRepository = fangAnCXLSRepository;
        this.fangAnSCRepository = fangAnSCRepository;
        this.lyraIdentityService = lyraIdentityService;
    }

    //region 获取方案列表，方案下拉，方案历史，在某方案的方案下拉
    @Override
    public List<FangAnXXDto> getChaXunFAXXSelect(String likeQuery, String fangAnLXDM, Integer pageIndex, Integer pageSize) {
        return fangAnXXRepository.getFangAnXX(lyraIdentityService.getJiGouID(), likeQuery, fangAnLXDM, pageIndex, pageSize);
    }

    @Override
    public List<FangAnXXDto> getFangAnXXList(String likeQuery, Integer pageIndex, Integer pageSize) {
        return fangAnXXRepository.getFangAnXX(lyraIdentityService.getJiGouID(), likeQuery, "", pageIndex, pageSize);
    }

    @Override
    public List<FangAnCXLSDto> getFangAnCXLSList(Integer pageIndex, Integer pageSize) {
        return BeanUtil.copyListProperties(fangAnCXLSRepository.getFangAnCXLSList(lyraIdentityService.getJiGouID(), pageIndex, pageSize),
                FangAnCXLSDto::new, (model, dto) -> {
                    if (StringUtil.hasText(model.getFangAnMC())) {
                        if (StringUtil.hasText(model.getGuanJianZi())) {
                            dto.setBiaoTiMC(StringUtil.concat(model.getFangAnMC(), "+", model.getGuanJianZi()));
                        } else {
                            dto.setBiaoTiMC(model.getFangAnMC());
                        }
                    } else {
                        dto.setBiaoTiMC(model.getGuanJianZi());
                    }
                    dto.setId(model.getId());
                    dto.setFangAnID(model.getFangAnID());
                    dto.setFangAnMC(model.getFangAnMC());
                    dto.setGuanJianZi(model.getGuanJianZi());
                    dto.setChaXunTJMS(model.getChaXunTJMS());
                });
    }

    /**
     * 在某方案的方案下拉
     *
     * @param likeQuery
     * @param fangAnLXDM
     * @return
     */
    @Override
    public List<FangAnSelectXXDto> getZaiMouFAXXSelect(String likeQuery, String fangAnLXDM) {
        List<SC_CX_FangAnXXModel> fangAnXXModelList = fangAnXXRepository.getFangAnXXList(lyraIdentityService.getJiGouID(), likeQuery, fangAnLXDM);
        List<String> fangAnIDs = fangAnXXModelList.stream().map(SC_CX_FangAnXXModel::getFangAnID).toList();
        List<SC_CX_FangAnSCModel> fangAnSCModelList = fangAnSCRepository.findByFangAnIDIn(fangAnIDs);
        List<FangAnSelectXXDto> fangAnSelectXXDtoList = BeanUtil.copyListProperties(fangAnXXModelList, FangAnSelectXXDto::new, (model, dto) -> {
            List<FangAnSCDTO> fangAnSCDTOList = BeanUtil.copyListProperties(fangAnSCModelList.stream().filter(x -> x.getFangAnID().equals(model.getFangAnID())).toList()
                    , FangAnSCDTO::new);
            dto.setFangAnSCDTOList(fangAnSCDTOList);
        });
        return fangAnSelectXXDtoList;
    }

    //endregion

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
        String chaXunLXDM = "2";
        String chaXunLXMC = "高级查询";
        if (StringUtils.hasText(request.getFangAnID())) {
            chaXunLXDM = "1";
            chaXunLXMC = "方案查询";
        }
        SC_CX_FangAnCXLSModel model = new SC_CX_FangAnCXLSModel();
        BeanUtil.mergeProperties(request, model);
        if ("2".equals(chaXunLXDM)) {
            model.setFangAnMC("高级查询");
        }
        model.setZuZhiJGID(lyraIdentityService.getJiGouID());
        model.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
        model.setChaXunLXDM(chaXunLXDM);
        model.setChaXunLXMC(chaXunLXMC);
        if (ObjectUtil.isNotEmpty(request.getRoot())) {
            model.setChaXunTJ(JsonUtil.getBeanToJson(request.getRoot()));
        }
        if (CollUtil.isNotEmpty(request.getFangAnSCList())) {
            model.setChaXunSC(JsonUtil.getBeanToJson(request.getFangAnSCList()));
        }
        if ("1".equals(chaXunLXDM)) {
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
        if (model == null) {
            return result;
        }
        BeanUtil.copyProperties(model, result);
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

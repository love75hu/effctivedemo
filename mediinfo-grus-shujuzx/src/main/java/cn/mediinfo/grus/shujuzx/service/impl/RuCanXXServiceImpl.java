package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.AddRuCanXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.RuCanXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_RuCanXXDto;
import cn.mediinfo.grus.shujuzx.model.SC_BH_RuCanXXModel;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_RuCanXXRepository;
import cn.mediinfo.grus.shujuzx.service.RuCanXXService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 闭环入参信息服务
 */
@Service
public class RuCanXXServiceImpl implements RuCanXXService {
    private final SC_BH_RuCanXXRepository ruCanXXRepository;

    public RuCanXXServiceImpl(SC_BH_RuCanXXRepository ruCanXXRepository) {
        this.ruCanXXRepository = ruCanXXRepository;
    }

    @Override
    public List<RuCanXXDto> getRuCanXXByBiHuanID(String biHuanID)
    {
        return BeanUtil.copyListProperties(ruCanXXRepository.findByBiHuanID(biHuanID),RuCanXXDto::new);
    }
    @Override
    public List<SC_BH_RuCanXXModel> getRuCanXX(String biHuanID)
    {
        return ruCanXXRepository.findByBiHuanID(biHuanID);
    }
    /**
     * 新增闭环入参信息
     */
    public Boolean addRuCanXX(List<AddRuCanXXDto> dto, String biHuanLXDM, String biHuanLXMC, String biHuanID, String biHuanMC)
    {
        ruCanXXRepository.asDeleteDsl().where(n->n.biHuanID.eq(biHuanID)).execute();
        ruCanXXRepository.saveAll(BeanUtil.copyListProperties(dto, SC_BH_RuCanXXModel::new, (d, s) -> {
            s.setZuZhiJGID("0");
            s.setZuZhiJGMC("通用");
            s.setBiHuanID(biHuanID);
            s.setBiHuanLXDM(biHuanLXDM);
            s.setBiHuanLXMC(biHuanLXMC);
            s.setBiHuanMC(biHuanMC);
        }));
        return true;
    }
    public Boolean addFuZhiRCXX(List<SC_BH_RuCanXXModel> canXXModels)
    {
     ruCanXXRepository.insertAll(canXXModels);
     return true;
    }

}
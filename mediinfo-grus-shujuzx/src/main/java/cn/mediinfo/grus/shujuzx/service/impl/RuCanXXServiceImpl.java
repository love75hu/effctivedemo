package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.AddRuCanXXDto;
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

    /**
     * 根据ID获取闭环入参信息     *     * @param id     * @return
     */
    @Override
    public SC_BH_RuCanXXDto getRuCanXXByID(String id) throws WeiZhaoDSJException {
        var result = ruCanXXRepository.asQuerydsl().where(s -> s.id.eq(id)).select(SC_BH_RuCanXXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }


    /**
     * 新增闭环入参信息
     */
    public Boolean addRuCanXX(List<AddRuCanXXDto> dto, String biHuanLXDM, String biHuanLXMC, String biHuanID, String biHuanMC)
    {
        ruCanXXRepository.asDeleteDsl().where(n->n.biHuanID.eq(biHuanID)).execute();
        ruCanXXRepository.saveAll(BeanUtil.copyListProperties(dto, SC_BH_RuCanXXModel::new, (d, s) -> {
            s.setShiTuMC(d.getShiTuMC());
            s.setShiTuID(d.getShiTuID());
            s.setBiHuanID(biHuanID);
            s.setBiHuanLXDM(biHuanLXDM);
            s.setBiHuanLXMC(biHuanLXMC);
            s.setBiHuanMC(biHuanMC);
        }));
        return true;
    }
}
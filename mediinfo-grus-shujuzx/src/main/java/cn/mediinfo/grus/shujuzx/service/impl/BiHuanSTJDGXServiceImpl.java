package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.GuanLianJDDto;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuJDGXDto;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ShiTuJDGXModel;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ShiTuJDGXRepository;
import cn.mediinfo.grus.shujuzx.service.BiHuanSTJDGXService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 闭环视图节点关系服务
 */
@Service
public class BiHuanSTJDGXServiceImpl implements BiHuanSTJDGXService {
    private final SC_BH_ShiTuJDGXRepository shiTuJDGXRepository;
    private final LyraIdentityService lyraIdentityService;
    public BiHuanSTJDGXServiceImpl(SC_BH_ShiTuJDGXRepository shiTuJDGXRepository, LyraIdentityService lyraIdentityService)
    {
        this.shiTuJDGXRepository = shiTuJDGXRepository;
        this.lyraIdentityService = lyraIdentityService;
    }
    @Override
    public SC_BH_ShiTuJDGXDto getShiTuJDGXByID(String id) throws WeiZhaoDSJException {
        var result= shiTuJDGXRepository.asQuerydsl().where(s->s.id.eq(id)).select(SC_BH_ShiTuJDGXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }
    @Override
    public List<GuanLianJDDto> getGuanLianJDXX(String shiTuID) {
        return  shiTuJDGXRepository.getGuanLianJDXX(shiTuID);
    }
    @Override
    public List<SC_BH_ShiTuJDGXDto> getGuanLianJDXXs(List<String> jieDianID)
    {
        return shiTuJDGXRepository.asQuerydsl().where(s->s.jieDianID.in(jieDianID)).select(SC_BH_ShiTuJDGXDto.class).fetch();
    }


    @Override
    public Boolean addGuanLianJDXX(List<GuanLianJDDto> guanLianJDDtos,String shiTuID,
                                   String shiTuMC,String jieDianID,String jieDianMC)
    {
        shiTuJDGXRepository.asDeleteDsl().where(s->s.shiTuID.eq(shiTuID))
                .where(s->s.jieDianID.eq(jieDianID)).execute();
        List<SC_BH_ShiTuJDGXModel> shiTuJDGXModels=new ArrayList<>();
        for (GuanLianJDDto guanLianJDDto:guanLianJDDtos)
        {
            SC_BH_ShiTuJDGXModel shiTuJDGXModel=new SC_BH_ShiTuJDGXModel();
            shiTuJDGXModel.setZuZhiJGID(lyraIdentityService.getJiGouID());
            shiTuJDGXModel.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            shiTuJDGXModel.setGuanLianJDID(guanLianJDDto.getJieDianID());
            shiTuJDGXModel.setGuanLianJDMC(guanLianJDDto.getJieDianMC());
            shiTuJDGXModel.setJieDianID(jieDianID);
            shiTuJDGXModel.setJieDianMC(jieDianMC);
            shiTuJDGXModel.setShiTuID(shiTuID);
            shiTuJDGXModel.setShiTuMC(shiTuMC);
            shiTuJDGXModels.add(shiTuJDGXModel);
        }
        shiTuJDGXRepository.saveAll(shiTuJDGXModels);
        return true;
    }
}

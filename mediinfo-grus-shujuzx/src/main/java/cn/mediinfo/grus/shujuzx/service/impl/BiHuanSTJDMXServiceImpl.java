package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.JieDianNRDto;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuJDMXDto;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ShiTuJDMXModel;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ShiTuJDMXRepository;
import cn.mediinfo.grus.shujuzx.service.BiHuanSTJDMXService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 闭环视图节点明细服务
 */
@Service
public class BiHuanSTJDMXServiceImpl implements BiHuanSTJDMXService {
    private final SC_BH_ShiTuJDMXRepository shiTuJDMXRepository;

    private final LyraIdentityService lyraIdentityService;
    public BiHuanSTJDMXServiceImpl(SC_BH_ShiTuJDMXRepository shiTuJDMXRepository, LyraIdentityService lyraIdentityService)
    {

        this.shiTuJDMXRepository = shiTuJDMXRepository;
        this.lyraIdentityService = lyraIdentityService;
    }

    @Override
    public SC_BH_ShiTuJDMXDto getShiTuJDMXById(String id) throws WeiZhaoDSJException {
        SC_BH_ShiTuJDMXDto result = shiTuJDMXRepository.asQuerydsl().where(s -> s.id.eq(id)).select(SC_BH_ShiTuJDMXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
return result;
    }
    @Override
    public List<JieDianNRDto> getShiTuJDMX(String shiTuID)
    {
        return shiTuJDMXRepository.getJieDianMX(shiTuID);
    }

    @Override
    public List<SC_BH_ShiTuJDMXDto> getShiTuJDMXs(List<String> jieDianID)
    {
                return shiTuJDMXRepository.getJieDianMXs(jieDianID);
    }
    @Override
    public List<JieDianNRDto> getShiTuJDMXByJieDianID(String jieDianID)
    {
        return shiTuJDMXRepository.asQuerydsl()
                .where(n->n.jieDianID.eq(jieDianID))
                .where(n->n.kongZhiSJBZ.eq(1)).select(JieDianNRDto.class).fetch();
    }
    @Override
    public Boolean addShiTuJDMX(List<JieDianNRDto> jieDianNRDtos, String shiTuID,String jieDianMC,String jieDianID,String shiTuMC)
    {
        shiTuJDMXRepository.asDeleteDsl().where(n->n.shiTuID.eq(shiTuID)).where(n->n.jieDianID.eq(jieDianID)).execute();
        List<SC_BH_ShiTuJDMXModel> shiTuJDMXList=new ArrayList<>();
        for (var j:jieDianNRDtos)
        {
            SC_BH_ShiTuJDMXModel shiTuJDMXModel=new SC_BH_ShiTuJDMXModel();
            shiTuJDMXModel.setZuZhiJGID(lyraIdentityService.getJiGouID());
            shiTuJDMXModel.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            shiTuJDMXModel.setZiDuanBM(j.getZiDuanBM());
            shiTuJDMXModel.setZiDuanMC(j.getZiDuanMC());
            shiTuJDMXModel.setKongZhiSJBZ(j.getKongZhiSJBZ());
            shiTuJDMXModel.setYunXuWKBZ(j.getYunXuWKBZ());
            shiTuJDMXModel.setJieDianID(jieDianID);
            shiTuJDMXModel.setJieDianMC(jieDianMC);
            shiTuJDMXModel.setShiTuID(shiTuID);
            shiTuJDMXModel.setShiTuMC(shiTuMC);
            shiTuJDMXList.add(shiTuJDMXModel);
        }
        shiTuJDMXRepository.saveAll(shiTuJDMXList);
        return true;
    }




}

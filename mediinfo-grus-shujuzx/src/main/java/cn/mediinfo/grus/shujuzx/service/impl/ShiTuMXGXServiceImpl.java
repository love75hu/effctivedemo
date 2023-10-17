package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.GuanLianTJZD;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.SC_CX_ShiTuMXGXDto;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ShiTuMXGXModel;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_ShiTuMXGXRepository;
import cn.mediinfo.grus.shujuzx.service.ShiTuMXGXService;
import cn.mediinfo.lyra.extension.service.impl.LyraIdentityServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 视图字段关系服务
 */
@Service
class ShiTuMXGXServiceImpl implements ShiTuMXGXService {
    private final SC_CX_ShiTuMXGXRepository shiTuMXGXRepository;

    private final LyraIdentityServiceImpl lyraIdentityService;

    public ShiTuMXGXServiceImpl(SC_CX_ShiTuMXGXRepository shiTuMXGXRepository, LyraIdentityServiceImpl lyraIdentityService) {
        this.shiTuMXGXRepository = shiTuMXGXRepository;
        this.lyraIdentityService = lyraIdentityService;
    }

    /**
     * 根据ID获取视图字段关系     *     * @param id     * @return
     */
    @Override
    public SC_CX_ShiTuMXGXDto getShiTuMXGXByID(String id) throws WeiZhaoDSJException {
        var result = shiTuMXGXRepository.asQuerydsl().where(s -> s.id.eq(id)).select(SC_CX_ShiTuMXGXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }

    @Override
    public List<GuanLianTJZD> getShiTuMXGXList(String shiTuID,String ziDuanBM,String ziDuanMC) {
      return   shiTuMXGXRepository.asQuerydsl().where(n->n.shiTuID.eq(shiTuID).and(n.ziDuanBM.eq(ziDuanBM))).select(GuanLianTJZD.class).fetch();
    }

    @Override
    public Boolean delectShiTuMXGX(String shiTuID) {
       shiTuMXGXRepository.asDeleteDsl().where(s -> s.shiTuID.eq(shiTuID)).execute();
        return true;
    }
    //根据视图ID获取视图字段关系
    @Override
    public List<cn.mediinfo.grus.shujuzx.dto.shitumx.SC_CX_ShiTuMXGXDto> getShiTuMXGXByShiTuID(Set<String> shiTuID)
    {
        return shiTuMXGXRepository.asQuerydsl().where(s->s.shiTuID.in(shiTuID)).select(cn.mediinfo.grus.shujuzx.dto.shitumx.SC_CX_ShiTuMXGXDto.class).fetch();
    }

    @Override
    public Boolean addShiTuMXGX(String shiTuID, String ziDuanBM, String ziDuanMC, List<GuanLianTJZD> guanLianZDList) {
        shiTuMXGXRepository.asDeleteDsl().where(n->n.shiTuID.eq(shiTuID)).execute();
        List<SC_CX_ShiTuMXGXModel> shiTuMXGXModelList=new ArrayList<>();
        for (var g:guanLianZDList)
        {
            SC_CX_ShiTuMXGXModel s=new SC_CX_ShiTuMXGXModel();
            s.setShiTuID(shiTuID);
            s.setZiDuanBM(ziDuanBM);
            s.setZiDuanMC(ziDuanMC);
            s.setGuanLianZDBM(g.getZiDuanBM());
            s.setGuanLianZDMC(g.getZiDuanBM());
            s.setZuZhiJGID(lyraIdentityService.getJiGouID());
            s.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            shiTuMXGXModelList.add(s);
        }
        shiTuMXGXRepository.saveAll(shiTuMXGXModelList);
        return true;
    }


}
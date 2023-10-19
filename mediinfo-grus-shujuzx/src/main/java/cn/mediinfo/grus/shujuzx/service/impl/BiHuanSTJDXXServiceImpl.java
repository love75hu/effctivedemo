package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.BiHuanJDXXListDto;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.BiHuanSTJDDto;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuJDGXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuJDMXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuJDXXDto;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ShiTuJDXXModel;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ShiTuJDXXRepository;
import cn.mediinfo.grus.shujuzx.service.BiHuanSTJDXXService;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 闭环视图节点信息服务
 */
@Service
public class BiHuanSTJDXXServiceImpl implements BiHuanSTJDXXService {
    private final SC_BH_ShiTuJDXXRepository shiTuJDXXRepository;
    private final  BiHuanSTJDGXServiceImpl biHuanSTJDGXService;
    private final  BiHuanSTJDMXServiceImpl biHuanSTJDMXService;

    public BiHuanSTJDXXServiceImpl(SC_BH_ShiTuJDXXRepository shiTuJDXXRepository, BiHuanSTJDGXServiceImpl biHuanSTJDGXService, BiHuanSTJDMXServiceImpl biHuanSTJDMXService) {
        this.shiTuJDXXRepository = shiTuJDXXRepository;
        this.biHuanSTJDGXService = biHuanSTJDGXService;
        this.biHuanSTJDMXService = biHuanSTJDMXService;
    }
    @Override
    public SC_BH_ShiTuJDXXDto  getShiTuJDXX(String id) throws WeiZhaoDSJException {
        var result=shiTuJDXXRepository.asQuerydsl().where(s->s.id.eq(id)).select(SC_BH_ShiTuJDXXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }
    @Override
    public BiHuanSTJDDto getBiHuanSTJDByID(String id) throws WeiZhaoDSJException {
        SC_BH_ShiTuJDXXDto shiTuJDXX = getShiTuJDXX(id);
        BiHuanSTJDDto biHuanSTJDDto = new BiHuanSTJDDto();
        BeanUtil.copyProperties(shiTuJDXX,biHuanSTJDDto);
        biHuanSTJDDto.setGuanLianJD(biHuanSTJDGXService.getGuanLianJDXX(shiTuJDXX.getShiTuID()));
        biHuanSTJDDto.setJieDianNR( biHuanSTJDMXService.getShiTuJDMX(shiTuJDXX.getShiTuID()));
        return biHuanSTJDDto;
    }

    /**
     * 新增闭环视图节点
     * @param dto 闭环视图节点
     * @return 成功失败
     * @throws WeiZhaoDSJException 未找到异常
     */
    @Override
    public Boolean addBiHuanSTJD(BiHuanSTJDDto dto) throws WeiZhaoDSJException {
        SC_BH_ShiTuJDXXModel scBhShiTuJDXXModel = shiTuJDXXRepository.findById(dto.getId()).orElse(null);
      if (scBhShiTuJDXXModel==null)
      {
          throw new WeiZhaoDSJException("未找到数据");
      }
        //添加关联节点
        biHuanSTJDGXService.addGuanLianJDXX(dto.getGuanLianJD(),
                scBhShiTuJDXXModel.getShiTuID(),
                scBhShiTuJDXXModel.getShiTuMC(),scBhShiTuJDXXModel.getJieDianID(),scBhShiTuJDXXModel.getJieDianMC());
        //添加节点内容
        biHuanSTJDMXService.addShiTuJDMX(dto.getJieDianNR(),
                scBhShiTuJDXXModel.getShiTuID(),
                scBhShiTuJDXXModel.getShiTuMC(),scBhShiTuJDXXModel.getJieDianID(),scBhShiTuJDXXModel.getJieDianMC());

        return true;
    }

    /**
     * 获取节点列表
     * @param shiTuID 视图id
     * @param jieDianMC 节点名称
     * @param qiYongBZ 启用标志
     * @param pageIndex 分页
     * @param pageSize 条数
     */
    @Override
    public List<BiHuanJDXXListDto> getBiHuanJDXXList(String shiTuID,
                                                     String jieDianMC,
                                                     Integer qiYongBZ,
                                                     Integer pageIndex,
                                                     Integer pageSize){
        List<BiHuanJDXXListDto> biHuanJDXXList = shiTuJDXXRepository.getBiHuanJDXXList(shiTuID, jieDianMC, qiYongBZ, pageIndex, pageSize);
        var jieDianIDs=biHuanJDXXList.stream().map(n->n.getJieDianID()).collect(Collectors.toList());
        //获取所有列表下的 节点内容
        var shiTuJDMX=biHuanSTJDMXService.getShiTuJDMXs(jieDianIDs);
        //获取所有列表下的 关联节点
        var guanLianJDXXS= biHuanSTJDGXService.getGuanLianJDXXs(jieDianIDs);

        for (var b:biHuanJDXXList)
        {
            b.setJieDianNR( shiTuJDMX.stream().filter(n->n.getShiTuID().equals(shiTuID)
                    &&n.getJieDianID().equals(b.getJieDianID())).map(SC_BH_ShiTuJDMXDto::getJieDianMC).toList());
            b.setGuanLianJD(guanLianJDXXS.stream().filter(n->n.getShiTuID().equals(shiTuID)
                    &&n.getJieDianID().equals(b.getJieDianID())).map(SC_BH_ShiTuJDGXDto::getGuanLianJDMC).toList());
        }
        return biHuanJDXXList;
    }
    /**
     * 获取节点列表数量
     * @param shiTuID 视图id
     * @param jieDianMC 节点名称
     * @param qiYongBZ 启用标志
     */
    @Override
    public Integer  getBiHuanJDXXCount(String shiTuID,
                                       String jieDianMC,
                                       Integer qiYongBZ)
    {
        return shiTuJDXXRepository.getBiHuanJDXXCount(shiTuID, jieDianMC, qiYongBZ);
    }


}

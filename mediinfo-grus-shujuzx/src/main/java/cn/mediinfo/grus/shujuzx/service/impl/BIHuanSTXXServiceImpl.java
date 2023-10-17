package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.BiHuanSTXXDto;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.BiHuanSTXXTree;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuXXDto;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ShiTuXXModel;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ShiTuXXRepository;
import cn.mediinfo.grus.shujuzx.service.BIHuanSTXXService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 闭环视图信息服务
 */
@Service
public class BIHuanSTXXServiceImpl implements BIHuanSTXXService {
    private final SC_BH_ShiTuXXRepository shiTuXXRepository;
    private final LyraIdentityService lyraIdentityService;

    public BIHuanSTXXServiceImpl(SC_BH_ShiTuXXRepository shiTuXXRepository, LyraIdentityService lyraIdentityService) {
        this.shiTuXXRepository = shiTuXXRepository;
        this.lyraIdentityService = lyraIdentityService;
    }

    /**
     * 根据ID获取闭环视图信息     *     * @param id     * @return
     */
    @Override
    public SC_BH_ShiTuXXDto getShiTuXXByID(String id) throws WeiZhaoDSJException {
        var result = shiTuXXRepository.asQuerydsl().where(s -> s.id.eq(id)).select(SC_BH_ShiTuXXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }

    /**
     * 获取闭环视图信息树
     */
    @Override
    public List<BiHuanSTXXTree> getBiHuanSTXXTree(String likeQuery) {
        List<SC_BH_ShiTuXXDto> shiTuXXList = shiTuXXRepository.getShiTuXXList(likeQuery);
        List<String> collect = shiTuXXList.stream().map(x -> x.getBiHuanLXDM()).distinct().collect(Collectors.toList());
        List<BiHuanSTXXTree> biHuanSTXXTrees = new ArrayList<>();
        for (var s:collect)
        {
            SC_BH_ShiTuXXDto scBhShiTuXXDto = shiTuXXList.stream().filter(x -> x.getBiHuanLXDM().equals(s)).findFirst().orElse(new SC_BH_ShiTuXXDto());
            BiHuanSTXXTree biHuanSTXXTree = BeanUtil.copyProperties(scBhShiTuXXDto, BiHuanSTXXTree::new);
            biHuanSTXXTree.setChildren(BeanUtil.copyListProperties(shiTuXXList.stream()
                    .filter(x->x.getBiHuanLXDM().equals(s)).collect(Collectors.toList()),BiHuanSTXXTree::new));
           biHuanSTXXTrees.add(biHuanSTXXTree);
        }
        return biHuanSTXXTrees;
    }

    /**
     * 添加闭环视图信息
     */
    @Override
    public Boolean addBiHuanSTXX(BiHuanSTXXDto dto) {
        SC_BH_ShiTuXXModel scBhShiTuXXModel = BeanUtil.copyProperties(dto, SC_BH_ShiTuXXModel::new);
        scBhShiTuXXModel.setZuZhiJGID(lyraIdentityService.getJiGouID());
        scBhShiTuXXModel.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
       scBhShiTuXXModel.setShunXuHao(dto.getShunXuHAO()==null?shiTuXXRepository.getMaxShunXuHao():dto.getShunXuHAO());
        shiTuXXRepository.save(scBhShiTuXXModel);
        return true;
    }


}
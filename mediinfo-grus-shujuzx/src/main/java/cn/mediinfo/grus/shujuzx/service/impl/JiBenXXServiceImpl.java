package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.stringgenerator.StringGenerator;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.AddBiHuanXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.BiHuanJBXXTreeDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_JIBENXXDto;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ShiTuMXModel;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_JIBENXXRepository;
import cn.mediinfo.grus.shujuzx.service.JiBenXXService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 闭环信息服务
 */
@Service
class JiBenXXServiceImpl implements JiBenXXService {
    private final SC_BH_JIBENXXRepository jIBENXXRepository;
    private final LyraIdentityService lyraIdentityService;
    private final StringGenerator stringGenerator;

    private final RuCanXXServiceImpl ruCanXXService;


    public JiBenXXServiceImpl(SC_BH_JIBENXXRepository jIBENXXRepository, LyraIdentityService lyraIdentityService, StringGenerator stringGenerator, RuCanXXServiceImpl ruCanXXService) {
        this.jIBENXXRepository = jIBENXXRepository;
        this.lyraIdentityService = lyraIdentityService;
        this.stringGenerator = stringGenerator;
        this.ruCanXXService = ruCanXXService;
    }

    /**
     * 根据ID获取闭环信息     *     * @param id     * @return
     */
    @Override
    public SC_BH_JIBENXXDto getJIBENXXByID(String id) throws WeiZhaoDSJException {
        var result = jIBENXXRepository.asQuerydsl().where(s -> s.id.eq(id)).select(SC_BH_JIBENXXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }

    /**
     * 获取闭环信息树
     */
    @Override
    public List<BiHuanJBXXTreeDto> getBiHuanJBXXTree(String zuZhiJGID, String likeQuery) {
        List<SC_BH_JIBENXXDto> jIBENXXList = jIBENXXRepository.getJIBENXXList(zuZhiJGID,likeQuery);
        List<String> biHuanLXDMs = jIBENXXList.stream().map(x -> x.getBiHuanLXDM()).distinct().collect(Collectors.toList());
        List<BiHuanJBXXTreeDto> biHuanJBXXTreeDtos=new ArrayList<>();
         for(var a:biHuanLXDMs)
         {
             SC_BH_JIBENXXDto scBhJibenxxDto = jIBENXXList.stream().filter(x -> x.getBiHuanLXDM().equals(a)).findFirst().orElse(new SC_BH_JIBENXXDto());
             BiHuanJBXXTreeDto biHuanJBXXTreeDto = BeanUtil.copyProperties(scBhJibenxxDto, BiHuanJBXXTreeDto::new);
             biHuanJBXXTreeDto.setChildrenl(BeanUtil.copyListProperties(jIBENXXList.stream().filter(x -> x.getBiHuanLXDM().equals(a)).collect(Collectors.toList()),BiHuanJBXXTreeDto::new));
             biHuanJBXXTreeDtos.add(biHuanJBXXTreeDto);
         }
        return biHuanJBXXTreeDtos;
    }

    @Override
    public Boolean addBiHuanJBXX(AddBiHuanXXDto dto) {

        SC_BH_ShiTuMXModel shiTuMXModel=new SC_BH_ShiTuMXModel();
        BeanUtil.copyProperties(dto,shiTuMXModel);
        shiTuMXModel.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
        shiTuMXModel.setZuZhiJGID(lyraIdentityService.getJiGouID());
        shiTuMXModel.setShiTuID(stringGenerator.Create());
        ruCanXXService.addRuCanXX(dto.getRuCanXXDtoList(),dto.getBiHuanLXDM(),dto.getBiHuanLXMC(),shiTuMXModel.getShiTuID(),shiTuMXModel.getShiTuMC());
        return true;
    }
}
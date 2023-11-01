package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.stringgenerator.StringGenerator;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.*;
import cn.mediinfo.grus.shujuzx.model.SC_BH_JiBenXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_JieDianXXModel;
import cn.mediinfo.grus.shujuzx.remoteservice.DiZuoRemoteService;
import cn.mediinfo.grus.shujuzx.repository.*;
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

    private final SC_BH_JieDianXXRepository jieDianXXRepository;

    private final SC_BH_JieDianSXRepository jieDianSXRepository;

    private final SC_BH_ZiBiHXXRepository ziBiHXXRepository;

    private final SC_BH_ZiBiHXSLRepository ziBiHXSLRepository;
    private final StringGenerator stringGenerator;
    private final DiZuoRemoteService diZuoRemoteService;

    private final RuCanXXServiceImpl ruCanXXService;


    public JiBenXXServiceImpl(SC_BH_JIBENXXRepository jIBENXXRepository, LyraIdentityService lyraIdentityService, SC_BH_JieDianXXRepository jieDianXXRepository, SC_BH_JieDianSXRepository jieDianSXRepository, SC_BH_ZiBiHXXRepository ziBiHXXRepository, SC_BH_ZiBiHXSLRepository ziBiHXSLRepository, StringGenerator stringGenerator, DiZuoRemoteService diZuoRemoteService, RuCanXXServiceImpl ruCanXXService) {
        this.jIBENXXRepository = jIBENXXRepository;
        this.lyraIdentityService = lyraIdentityService;
        this.jieDianXXRepository = jieDianXXRepository;
        this.jieDianSXRepository = jieDianSXRepository;
        this.ziBiHXXRepository = ziBiHXXRepository;
        this.ziBiHXSLRepository = ziBiHXSLRepository;
        this.stringGenerator = stringGenerator;
        this.diZuoRemoteService = diZuoRemoteService;
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

        SC_BH_JiBenXXModel shiTuMXModel=new SC_BH_JiBenXXModel();
        BeanUtil.copyProperties(dto,shiTuMXModel);
        shiTuMXModel.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
        shiTuMXModel.setZuZhiJGID(lyraIdentityService.getJiGouID());
       // shiTuMXModel.setShiTuID(stringGenerator.Create());
        ruCanXXService.addRuCanXX(dto.getRuCanXXDtoList(),dto.getBiHuanLXDM(),dto.getBiHuanLXMC(),"","");
        jIBENXXRepository.save(shiTuMXModel);
        return true;
    }
    /**
     * 闭环设置下发
     */
    @Override
    public Boolean biHuanSZXF(BiHuanSZXFDto dto) throws TongYongYWException {
        List<BiHuanZZJGDto> xiaFaJGIdGList = new ArrayList<>();
        List<String> jiGouid=dto.getXiaFaJG().stream().map(n->n.getZuZhiJGID()).collect(Collectors.toList());
        //全部下发
        if("0".equals(dto.getXiaFaFS()))
        {
            List<SC_BH_JiBenXXModel> jiBen = jIBENXXRepository.asQuerydsl().where(n -> n.zuZhiJGID.eq("0")).fetch();
            //通用下的节点信息
            List<SC_BH_JieDianXXModel> jieDianXXTYX = jieDianXXRepository.asQuerydsl().where(n -> n.zuZhiJGID.eq("0")).fetch();
            //机构的数据
            List<SC_BH_JieDianXXModel> jieDianXXJGX = jieDianXXRepository.asQuerydsl().where(n -> n.zuZhiJGID.in(jiGouid)).fetch();




        }
        //部分下发
        if ("1".equals(dto.getXiaFaFS()))
        {

        }



        return null;
    }
    /**
     * 闭环设置复制
     */
    @Override
    public String biHuanSZFZ(String biHuanID) {
        return null;
    }
    /**
     * 闭环设置删除
     */
    @Override
    public Boolean biHuanSZSC(String biHuanID) {
        return null;
    }
    /**
     * 闭环设置启用
     */
    @Override
    public Boolean biHuanSZQY(String biHuanID) {
        return null;
    }

}
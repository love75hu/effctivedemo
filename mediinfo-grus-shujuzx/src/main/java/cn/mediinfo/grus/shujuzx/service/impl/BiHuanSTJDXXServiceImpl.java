package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.stringgenerator.StringGenerator;
import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.*;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuJDGXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuJDMXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuJDXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.KeXuanJDDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.KeXuanJDXXDto;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ShiTuJDXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ShiTuXXModel;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_JiBenXXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_JieDianXXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ShiTuJDXXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ShiTuXXRepository;
import cn.mediinfo.grus.shujuzx.service.BiHuanSTJDXXService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 闭环视图节点信息服务
 */
@Service
public class BiHuanSTJDXXServiceImpl implements BiHuanSTJDXXService {
    private final SC_BH_ShiTuJDXXRepository shiTuJDXXRepository;
    private final SC_BH_ShiTuXXRepository shiTuXXRepository;
    private final  BiHuanSTJDGXServiceImpl biHuanSTJDGXService;
    private final  BiHuanSTJDMXServiceImpl biHuanSTJDMXService;
    private final StringGenerator stringGenerator;


    public BiHuanSTJDXXServiceImpl(SC_BH_ShiTuJDXXRepository shiTuJDXXRepository, SC_BH_ShiTuXXRepository shiTuXXRepository, BiHuanSTJDGXServiceImpl biHuanSTJDGXService, BiHuanSTJDMXServiceImpl biHuanSTJDMXService,StringGenerator stringGenerator) {
        this.shiTuJDXXRepository = shiTuJDXXRepository;
        this.shiTuXXRepository = shiTuXXRepository;
        this.biHuanSTJDGXService = biHuanSTJDGXService;
        this.biHuanSTJDMXService = biHuanSTJDMXService;
        this.stringGenerator = stringGenerator;
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
        BiHuanSTJDDto biHuanSTJDDto = BeanUtil.copyProperties(shiTuJDXX,BiHuanSTJDDto::new);
        biHuanSTJDDto.setGuanLianJD(biHuanSTJDGXService.getGuanLianJDXX(shiTuJDXX.getJieDianID()));
        biHuanSTJDDto.setJieDianNR( biHuanSTJDMXService.getShiTuJDMX(shiTuJDXX.getJieDianID()));
        return biHuanSTJDDto;
    }

    /**
     * 新增闭环视图节点
     * @param dto 闭环视图节点
     * @return 成功失败
     * @throws WeiZhaoDSJException 未找到异常
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean addBiHuanSTJD(BiHuanSTJDDto dto) throws WeiZhaoDSJException {

        SC_BH_ShiTuXXModel shiTuXX = shiTuXXRepository.findFirstByShiTuID(dto.getShiTuID());
       if (shiTuXX==null)
       {
              throw new WeiZhaoDSJException("未找到视图信息");
       }

        SC_BH_ShiTuJDXXModel scBhShiTuJDXXModel = new SC_BH_ShiTuJDXXModel();

        BeanUtil.copyProperties(dto,scBhShiTuJDXXModel);
        scBhShiTuJDXXModel.setId(null);
        scBhShiTuJDXXModel.setJieDianID(stringGenerator.Create());
        scBhShiTuJDXXModel.setZuZhiJGID(ShuJuZXConstant.TONGYONG_JGID);
        scBhShiTuJDXXModel.setZuZhiJGMC(ShuJuZXConstant.TONGYONG_JGMC);
        scBhShiTuJDXXModel.setShiTuID(shiTuXX.getShiTuID());
        scBhShiTuJDXXModel.setShiTuMC(shiTuXX.getShiTuMC());
        scBhShiTuJDXXModel.setBiHuanLXDM(shiTuXX.getBiHuanLXDM());
        scBhShiTuJDXXModel.setBiHuanLXMC(shiTuXX.getBiHuanLXMC());


        shiTuJDXXRepository.save(scBhShiTuJDXXModel);
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
     * 获取关联节点信息
     */
    @Override
    public List<GuanLianJDDto> getGuanLianJDXX(String shiTuID,String jieDianID) {

        return shiTuJDXXRepository.getGuanLianJDXX(shiTuID,jieDianID);
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
                                                     String biHuanLXDM,
                                                     String jieDianMC,
                                                     Integer qiYongBZ,
                                                     Integer pageIndex,
                                                     Integer pageSize){
        List<BiHuanJDXXListDto> biHuanJDXXList = shiTuJDXXRepository.getBiHuanJDXXList(shiTuID,biHuanLXDM, jieDianMC, qiYongBZ, pageIndex, pageSize);
        var jieDianIDs=biHuanJDXXList.stream().map(BiHuanJDXXListDto::getJieDianID).collect(Collectors.toList());
        //获取所有列表下的 节点内容
        var shiTuJDMX=biHuanSTJDMXService.getShiTuJDMXs(jieDianIDs);
        //获取所有列表下的 关联节点
        var guanLianJDXXS= biHuanSTJDGXService.getGuanLianJDXXs(jieDianIDs);

        for (var b:biHuanJDXXList)
        {
            b.setJieDianNR( shiTuJDMX.stream().filter(n-> n.getShiTuID().equals(b.getShiTuID())&&
                    n.getJieDianID().equals(b.getJieDianID())).map(SC_BH_ShiTuJDMXDto::getZiDuanMC).toList());
            b.setGuanLianJD(guanLianJDXXS.stream().filter(n->n.getShiTuID().equals(b.getShiTuID())&&
                    n.getJieDianID().equals(b.getJieDianID())).map(SC_BH_ShiTuJDGXDto::getGuanLianJDMC).toList());
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
                                       String biHuanLXDM,
                                       String jieDianMC,
                                       Integer qiYongBZ)
    {
        return shiTuJDXXRepository.getBiHuanJDXXCount(shiTuID, biHuanLXDM,jieDianMC, qiYongBZ);
    }

    /**
     * 获取可选节点内容
     * @param biHuanLXDM 闭环类型代码
     * @return 可选节点内容
     */
    @Override
    public List<KeXuanJDDto> getKeXuanJDBybiHuanLXDM(String biHuanLXDM) {
        List<SC_BH_ShiTuJDXXDto> shiTuJDXXList = shiTuJDXXRepository.asQuerydsl().where(n -> n.biHuanLXDM.eq(biHuanLXDM)).select(SC_BH_ShiTuJDXXDto.class).fetch();
        List<String> shiTuIDs = shiTuJDXXList.stream().map(SC_BH_ShiTuJDXXDto::getShiTuID).distinct().collect(Collectors.toList());
        List<KeXuanJDDto> keXuanJDDtoList=new ArrayList<>();
        for (var s :shiTuIDs)
        {
            KeXuanJDDto keXuanJDDto=new KeXuanJDDto();
            SC_BH_ShiTuJDXXDto scBhShiTuJDXXDto = shiTuJDXXList.stream().filter(n -> n.getShiTuID().equals(s)).findFirst().orElse(new SC_BH_ShiTuJDXXDto());
            keXuanJDDto.setShiTuID(scBhShiTuJDXXDto.getShiTuID());
            keXuanJDDto.setShiTuMC(scBhShiTuJDXXDto.getShiTuMC());
            keXuanJDDto.setKeXuanJDXXDtoList(BeanUtil.copyToList(shiTuJDXXList.stream().filter(n -> n.getShiTuID().equals(s)).collect(Collectors.toList()), KeXuanJDXXDto.class));
            keXuanJDDtoList.add(keXuanJDDto);
        }
        return keXuanJDDtoList;
    }

    /**
     * 节点启用标志
     * @param id 节点id
     * @param qiYongBZ 启用标志
     * @return 成功失败
     */
    @Override
    public Boolean updateJieDianQYBZ(String id,Integer qiYongBZ) {
        shiTuJDXXRepository.asUpdateDsl().set(n->n.qiYongBZ,qiYongBZ).where(n->n.id.eq(id)).execute();
        return true;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean updateBiHuanSTJD(BiHuanSTJDDto dto) throws WeiZhaoDSJException {
        SC_BH_ShiTuJDXXModel scBhShiTuJDXXModel = shiTuJDXXRepository.findById(dto.getId()).orElse(null);
        if (scBhShiTuJDXXModel==null)
        {
            throw new WeiZhaoDSJException("未找到节点信息");
        }
        scBhShiTuJDXXModel.setShunXuHao(dto.getShunXuHao());
        scBhShiTuJDXXModel.setJieDianMC(dto.getJieDianMC());
        scBhShiTuJDXXModel.setShiJianMC(dto.getShiJianMC());
        scBhShiTuJDXXModel.setShiJianDM(dto.getShiJianDM());
        scBhShiTuJDXXModel.setShiJianZDBM(dto.getShiJianZDBM());
        scBhShiTuJDXXModel.setShiJianZDMC(dto.getShiJianZDMC());
        shiTuJDXXRepository.save(scBhShiTuJDXXModel);
        //添加关联节点
        biHuanSTJDGXService.addGuanLianJDXX(dto.getGuanLianJD(),
                scBhShiTuJDXXModel.getShiTuID(),
                scBhShiTuJDXXModel.getShiTuMC(),scBhShiTuJDXXModel.getJieDianID(),scBhShiTuJDXXModel.getJieDianMC());

        biHuanSTJDMXService.addShiTuJDMX(dto.getJieDianNR(),
                scBhShiTuJDXXModel.getShiTuID(),
                scBhShiTuJDXXModel.getShiTuMC(),scBhShiTuJDXXModel.getJieDianID(),scBhShiTuJDXXModel.getJieDianMC());
        return true;

    }

    /**
     * 获取闭环视图节点
     * @param biHuanLXDM 闭环类型代码
     * @return 闭环视图节点
     */
    @Override
    public List<BiHuanSTJDXXDto> getBiHuanSTJD(String biHuanLXDM)
    {
        List<BiHuanJDXXListDto> biHuanJDList = shiTuJDXXRepository.getBiHuanJDList(biHuanLXDM);
      return  biHuanJDList.stream().collect(Collectors.groupingBy(p-> Arrays.asList(p.getShiTuID(),p.getShiTuMC())))
                .entrySet().stream().map(p->{
                    BiHuanSTJDXXDto dto=new BiHuanSTJDXXDto();
                    dto.setShiTuID(p.getKey().get(0));
                    dto.setShiTuMC(p.getKey().get(1));
                    dto.setJieDianNR(BeanUtil.copyListProperties(p.getValue(), JieDianDto::new));
                    return dto;
                }).toList();
    }

    @Override
    public Boolean zuoFeiBHJD(String id) throws WeiZhaoDSJException {
        SC_BH_ShiTuJDXXModel scBhShiTuJDXXModel = shiTuJDXXRepository.findById(id).orElse(null);
        if (scBhShiTuJDXXModel==null)
        {
            throw new WeiZhaoDSJException("未找到节点信息");
        }else {

        }
        shiTuJDXXRepository.asUpdateDsl().where(n->n.id.eq(id)).execute();
        biHuanSTJDMXService.zuoFeiJDMX(scBhShiTuJDXXModel.getJieDianID());
        return true;
    }


}

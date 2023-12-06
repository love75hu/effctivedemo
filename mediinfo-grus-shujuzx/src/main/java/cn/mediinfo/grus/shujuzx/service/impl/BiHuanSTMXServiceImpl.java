package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.*;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuMXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.BiHuanSTRCZDDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.BiHuanZDXXDto;
import cn.mediinfo.grus.shujuzx.dto.shitumx.*;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ShiTuMXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ShiTuXXModel;
import cn.mediinfo.grus.shujuzx.remoteservice.GongYongRemoteService;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ShiTuMXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ShiTuXXRepository;
import cn.mediinfo.grus.shujuzx.service.BiHuanSTMXService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 闭环视图明细服务
 */
@Service
public class BiHuanSTMXServiceImpl implements BiHuanSTMXService {
    private final SC_BH_ShiTuMXRepository shiTuMXRepository;

        private final SC_BH_ShiTuXXRepository shiTuXXRepository;
        private final GongYongRemoteService gongYongRemoteService;

    private final LyraIdentityService lyraIdentityService;

    public BiHuanSTMXServiceImpl(SC_BH_ShiTuMXRepository shiTuMXRepository, SC_BH_ShiTuXXRepository shiTuXXRepository, GongYongRemoteService gongYongRemoteService, LyraIdentityService lyraIdentityService) {
        this.shiTuMXRepository = shiTuMXRepository;
        this.shiTuXXRepository = shiTuXXRepository;
        this.gongYongRemoteService = gongYongRemoteService;
        this.lyraIdentityService = lyraIdentityService;
    }
    @Override
    public SC_BH_ShiTuMXDto getShiTuMXById(String id) throws WeiZhaoDSJException {
        var result= shiTuMXRepository.asQuerydsl().where(s -> s.id.eq(id)).select(SC_BH_ShiTuMXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }

    /**
     * 获取闭环视图字段
     *
     * @return
     */
    @Override
    public List<BiHuanSTZDDto> getBiHuanSTZD( String shiTuID, String ziDuanMC, String biHuanLXDM, Integer chaXunLXDM, Integer pageIndex, Integer pageSize) {

        return shiTuMXRepository.getBiHuanSTZD(shiTuID,ziDuanMC,biHuanLXDM,chaXunLXDM,pageIndex,pageSize);
    }
    /**
     * 获取闭环视图字段数量
     *
     * @return
     */
    @Override
    public Integer getBiHuanSTZDCount( String shiTuID, String ziDuanMC, String biHuanLXDM, Integer chaXunLXDM) {
        return shiTuMXRepository.getBiHuanSTZDCount(shiTuID,ziDuanMC,biHuanLXDM,chaXunLXDM);
    }
    /**
     * 删除闭环视图字段
     *
     * @param id 字段id
     * @return
     */
    @Override
    public Boolean delectBiHuanSTZDByID(String id)
    {
        shiTuMXRepository.deleteById(id);
        return true;
    }
    /**
     * 删除视图下得字段
     *
     * @param shiTuID 视图id
     * @return
     */
    @Override
    public Boolean delectBiHuanSTZDByShiTuID(String shiTuID)
    {
        shiTuMXRepository.asDeleteDsl().where(n->n.shiTuID.eq(shiTuID)).execute();
        return true;
    }
    /**
     * 添加闭环视图字段
     *
     * @param dto
     * @return
     */
    @Override
    public Boolean addBiHuanSTJDMX(List<AddBiHuanSTJDMXDto> dto) {
        if (!dto.isEmpty()) {
            List<SC_BH_ShiTuMXModel> shiTuMXList = shiTuMXRepository.asQuerydsl().where(n -> n.shiTuID.eq(dto.get(0).getShiTuID())).fetch();
            List<String> ziDuanBM = shiTuMXList.stream().map(SC_BH_ShiTuMXModel::getZiDuanBM).toList();
            List<SC_BH_ShiTuMXModel> scBhShiTuMXModels = BeanUtil.copyListProperties(dto.stream()
                    .filter(s -> !ziDuanBM.contains(s.getZiDuanBM())).collect(Collectors.toList()), SC_BH_ShiTuMXModel::new, (d, s) -> {
                s.setZuZhiJGID(lyraIdentityService.getJiGouID());
                s.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            });
            shiTuMXRepository.saveAll(scBhShiTuMXModels);
            return true;
        }
        return false;

    }
    /**
     * 保存闭环视图字段
     *
     * @param dto
     * @return
     */

    @Override
    public Boolean saveBiHuanSTJDMX(SaveBiHuanSTJDMXDto dto) throws WeiZhaoDSJException {

        SC_BH_ShiTuMXModel scBhShiTuMXModel = shiTuMXRepository.findById(dto.getId()).orElse(null);
        if (scBhShiTuMXModel==null)
        {
            throw new WeiZhaoDSJException("未找到异常");
        }
        scBhShiTuMXModel.setRuCanBZ(dto.getRuCanBZ());
        scBhShiTuMXModel.setTiaoJianBZ(dto.getTiaoJianBZ());
        scBhShiTuMXModel.setZiDuanMC(dto.getZiDuanMC());
        shiTuMXRepository.save(scBhShiTuMXModel);
        return true;
    }
    /**
     * 视图下得字段信息
     *
     * @param shiTUID 视图id
     * @return 字段集合
     */
    @Override
    public List<KeXuanZDDto> getShiTUZDXX(String shiTUID) {
        return shiTuMXRepository.getShiTUZDXX(shiTUID);
    }

    /**
     * 获取入参字段信息
     *
     * @param biHuanLXDM 闭环类型代码
     * @return 字段集合
     */
    @Override
    public List<BiHuanSTRCZDDto> getRuChanZDXX(String biHuanLXDM) {
        List<BiHuanSTRCZDDto> biHuanSTRCZDDto=new ArrayList<>();
        var ruCanZDXXList=shiTuMXRepository.getRuChanZDXX(biHuanLXDM);
        List<String> shiTuIDs=ruCanZDXXList.stream().map(KeXuanZDDto::getShiTuID).distinct().toList();
        shiTuIDs.forEach(n->{
            BiHuanSTRCZDDto biHuanSTRCZD=new BiHuanSTRCZDDto();
            KeXuanZDDto keXuanZDDto = ruCanZDXXList.stream().filter(t -> t.getShiTuID().equals(n)).findFirst().orElse(new KeXuanZDDto());
            biHuanSTRCZD.setShiTuID(keXuanZDDto.getShiTuID());
            biHuanSTRCZD.setShiTuMC(keXuanZDDto.getShiTuMC());
            biHuanSTRCZD.setChildren(BeanUtil.copyListProperties( ruCanZDXXList.stream().filter(f -> f.getShiTuID().equals(n)).collect(Collectors.toList()), BiHuanZDXXDto::new));
            biHuanSTRCZDDto.add(biHuanSTRCZD);
        });
        return biHuanSTRCZDDto;
    }

    /**
     * 获取事件信息
     *
     * @param shiTuID 视图id
     * @return 字段集合
     */
    @Override
    public List<BiHuanSTSJXXDto> getShiJianXX(String shiTuID) throws WeiZhaoDSJException, YuanChengException {
        //获取当前视图下得字段
        List<KeXuanZDDto> shiTUZDXX = shiTuMXRepository.getShiTUZDXX(shiTuID);
        //获取视图信息
        SC_BH_ShiTuXXDto scBhShiTuXXModel = shiTuXXRepository.asQuerydsl()
                .where(n -> n.shiTuID.eq(shiTuID))
                .select(SC_BH_ShiTuXXDto.class).fetchFirst();
        if (scBhShiTuXXModel==null)
        {
            throw new WeiZhaoDSJException("未找到异常");
        }
        List<SC_CX_ShiTuXXZDDto> shiTuXXListDto=new ArrayList<>();
        SC_CX_ShiTuXXZDDto shiTuXXListDto1=new SC_CX_ShiTuXXZDDto();
        shiTuXXListDto1.setShiTuID(shiTuID);
        shiTuXXListDto1.setShiTuMC(scBhShiTuXXModel.getShiTuMC());
        shiTuXXListDto1.setShuJuLYLXDM(scBhShiTuXXModel.getShuJuLYLXDM());
        shiTuXXListDto1.setShuJuLYLXMC(scBhShiTuXXModel.getShuJuLYLXMC());
        shiTuXXListDto1.setShuJuLYID(scBhShiTuXXModel.getShuJuLYID());
        shiTuXXListDto1.setShuJuLYMC(scBhShiTuXXModel.getShuJuLYMC());
        List<SC_CX_ShiTuMXByIdDto> shiTuZDMXDtoList=new ArrayList<>();

        for (var s:shiTUZDXX)
        {
            SC_CX_ShiTuMXByIdDto shiTuZDMXDto;
            shiTuZDMXDto = new SC_CX_ShiTuMXByIdDto();
            shiTuZDMXDto.setZiDuanMC(s.getZiDuanMC());
            shiTuZDMXDto.setZiDuanBM(s.getZiDuanBM());
            shiTuZDMXDto.setShiTuID(s.getShiTuID());
            shiTuZDMXDto.setShiTuMC(s.getShiTuMC());
            shiTuZDMXDtoList.add(shiTuZDMXDto);
        }
        shiTuXXListDto1.setShiTuMXDto(shiTuZDMXDtoList);
        shiTuXXListDto.add(shiTuXXListDto1);
        List<FieldDTO> shiTuList = gongYongRemoteService.getShiTuZDXXList(shiTuXXListDto).getData("获取功能服务字段信息失败");

        List<BiHuanSTSJXXDto> biHuanSTSJXXDtoList=new ArrayList<>();
        BiHuanSTSJXXDto biHuanSTSJXXDto=new BiHuanSTSJXXDto();
        biHuanSTSJXXDto.setShiTuID(scBhShiTuXXModel.getShiTuID());
        biHuanSTSJXXDto.setShiTuMC(scBhShiTuXXModel.getShiTuMC());
        var shiTuZDXX=shiTuList.stream().filter(n-> Objects.equals( n.getShuJuZLXDM(),"3")).toList();
        List<ShiJianXXDto> shiJianXXDtoList=new ArrayList<>();

        shiTuZDXX.forEach(n->{
            ShiJianXXDto shiJianXXDto=new ShiJianXXDto();
            BeanUtil.copyProperties(n,shiJianXXDto);
            shiJianXXDto.setZiDuanMC(StringUtil.concat(scBhShiTuXXModel.getShuJuLYMC(),"/",n.getZiDuanMC()) );
            shiJianXXDtoList.add(shiJianXXDto);
        });
        biHuanSTSJXXDto.setChildren(shiJianXXDtoList);
        biHuanSTSJXXDtoList.add(biHuanSTSJXXDto);

        return biHuanSTSJXXDtoList;
    }

    /**
     * 获取闭环类型下字段
     *
     */
    @Override
    public  List<BiHuanSTRCZDDto>  getBiHuanSTZDBybiHuanLXDM(String biHuanLXDM) {

        List<BiHuanSTRCZDDto> biHuanSTRCZDDtos=new ArrayList<>();

        var dataList=shiTuMXRepository.getBiHuanSTZDBybiHuanLXDM(biHuanLXDM);

        List<String> shiTuIDs = dataList.stream().map(BiHuanSTZDDto::getShiTuID).distinct().collect(Collectors.toList());

        shiTuIDs.forEach(n->{
            BiHuanSTRCZDDto biHuanSTRCZDDto=new BiHuanSTRCZDDto();
            BiHuanSTZDDto biHuanSTZDDto = dataList.stream().filter(t -> t.getShiTuID().equals(n)).findFirst().orElse(new BiHuanSTZDDto());
            biHuanSTRCZDDto.setShiTuID(biHuanSTZDDto.getShiTuID());
            biHuanSTRCZDDto.setShiTuMC(biHuanSTZDDto.getShiTuMC());
            biHuanSTRCZDDto.setChildren(BeanUtil.copyListProperties( dataList.stream().filter(f -> f.getShiTuID().equals(n)).collect(Collectors.toList()), BiHuanZDXXDto::new));
            biHuanSTRCZDDtos.add(biHuanSTRCZDDto);
        });
        return biHuanSTRCZDDtos;
    }

}

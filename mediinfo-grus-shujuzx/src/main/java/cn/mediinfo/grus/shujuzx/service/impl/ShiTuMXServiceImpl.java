package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.grus.shujuzx.dto.shitumx.*;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.*;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ShiTuMXModel;
import cn.mediinfo.grus.shujuzx.remoteservice.GongYongRemoteService;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_ShiTuMXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_ShiTuXXRepository;
import cn.mediinfo.grus.shujuzx.service.ShiTuMXGXService;
import cn.mediinfo.grus.shujuzx.service.ShiTuMXService;
import cn.mediinfo.lyra.extension.service.impl.LyraIdentityServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 临床检索视图明细服务
 */
@Service
public class ShiTuMXServiceImpl implements ShiTuMXService {
    private final SC_CX_ShiTuMXRepository shiTuMXRepository;
    private final SC_CX_ShiTuXXRepository shiTuXXRepository;
    private final GongYongRemoteService gongYongRemoteService;

    private final LyraIdentityServiceImpl lyraIdentityService;

    private final ShiTuMXGXService shiTuMXGXService;


    public ShiTuMXServiceImpl(SC_CX_ShiTuMXRepository shiTuMXRepository,
                              SC_CX_ShiTuXXRepository shiTuXXRepository,
                              GongYongRemoteService gongYongRemoteService,
                              LyraIdentityServiceImpl lyraIdentityService, ShiTuMXGXService shiTuMXGXService) {
        this.shiTuMXRepository = shiTuMXRepository;
        this.shiTuXXRepository = shiTuXXRepository;
        this.gongYongRemoteService = gongYongRemoteService;
        this.lyraIdentityService = lyraIdentityService;
        this.shiTuMXGXService = shiTuMXGXService;
    }

    /**
     * 根据ID获取临床检索视图明细     *     * @param id     * @return
     */
    public SC_CX_ShiTuMXDto getShiTuMXByID(String id) throws WeiZhaoDSJException {
        var result = shiTuMXRepository.asQuerydsl().where(s -> s.id.eq(id)).select(SC_CX_ShiTuMXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }

    @Override
    public ShiTuMXDto getShiTuMXGXByID(String id) throws WeiZhaoDSJException {
        SC_CX_ShiTuMXDto shiTuMXByID = getShiTuMXByID(id);
        ShiTuMXDto shiTuMXDto = BeanUtil.copyProperties(shiTuMXByID, ShiTuMXDto::new);
        shiTuMXDto.setGuanLianZDList(shiTuMXGXService.getShiTuMXGXList(shiTuMXByID.getShiTuID(),shiTuMXByID.getZiDuanBM(),shiTuMXByID.getZiDuanMC()));
        return shiTuMXDto;
    }

    @Override
    public List<FieldDTO> listFields(Set<String> shiTuMXIds) throws YuanChengException {
        //查询视图明细
        List<SC_CX_ShiTuMXByIdDto>  shiTuMXModels = shiTuMXRepository.findByIdIs(shiTuMXIds);
        //获取视图id集合
        Set<String> shiTuIds = shiTuMXModels.stream().map(SC_CX_ShiTuMXByIdDto::getShiTuID).collect(java.util.stream.Collectors.toSet());
        //查询视图信息
        List<SC_CX_ShiTuXXByShiTuIDDto> shiTuXXList =  shiTuXXRepository.findByShiTuIDIn(shiTuIds);

        List<cn.mediinfo.grus.shujuzx.dto.shitumx.SC_CX_ShiTuMXGXDto> shiTuMXGX = shiTuMXGXService.getShiTuMXGXByShiTuID(shiTuIds);

        List<SC_CX_ShiTuXXZDDto> shiTuXXZDList = new ArrayList<>();
        for (SC_CX_ShiTuXXByShiTuIDDto shiTuXXByShiTuIDDto :shiTuXXList)
        {
           SC_CX_ShiTuXXZDDto shiTuXXZDDto=new SC_CX_ShiTuXXZDDto();
            BeanUtil.copyProperties(shiTuXXByShiTuIDDto,shiTuXXZDDto);
            List<SC_CX_ShiTuMXByIdDto> ziDuanMX = shiTuMXModels.stream()
                    .filter(n -> n.getShiTuID().equals(shiTuXXByShiTuIDDto.getShiTuID())).collect(Collectors.toList());
            for (SC_CX_ShiTuMXByIdDto z:ziDuanMX)
            {
                List<cn.mediinfo.grus.shujuzx.dto.shitumx.SC_CX_ShiTuMXGXDto> shiTuMXGXDto =
                        shiTuMXGX.stream().filter(n -> n.getZiDuanBM().equals(z.getZiDuanBM())).collect(Collectors.toList());
               z.setShiTuMXGXDto(shiTuMXGXDto);
            }

            shiTuXXZDDto.setShiTuMXDto(ziDuanMX);

            shiTuXXZDList.add(shiTuXXZDDto);

        }
       return gongYongRemoteService.getShiTuZDXXList(shiTuXXZDList).getData("获取功能服务字段信息失败");
    }

    @Override
    public List<TableDTO> listTable(Set<String> shiTuMXIds) {
        return null;
    }

    @Override
    public List<DatabaseDTO> listDatabase(Set<String> shiTuMXIds) {
        return null;
    }

    /**
     * 批量新增视图字段
     *
     */
    @Override
    public Boolean addShiTuMX(AddShiTuMXDto addShiTuMXDto) throws WeiZhaoDSJException, MsfResponseException {
        List<SC_CX_ShiTuMXModel> shiTuMXModels=new ArrayList<>();
        addShiTuMXDto.getZiDuanXXList().forEach(s->{
            SC_CX_ShiTuMXModel shiTuMXModel=new SC_CX_ShiTuMXModel();
            shiTuMXModel.setShiTuID(addShiTuMXDto.getShiTuID());
            shiTuMXModel.setShiTuMC(addShiTuMXDto.getShiTuMC());
            shiTuMXModel.setZuZhiJGID(lyraIdentityService.getJiGouID());
            shiTuMXModel.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            BeanUtil.copyProperties(s,shiTuMXModel);
            shiTuMXModels.add(shiTuMXModel);
        });
        shiTuMXRepository.saveAll(shiTuMXModels);
        return true;
    }

    /**
     * 根据视图id获取视图字段信息列表
     *
     */
    @Override
    public List<ShiTuMXListDto> getShiTuMXList(String shiTuID, String likeQuery, Integer chaXunLX, Integer pageIndex, Integer pageSize) {
        return shiTuMXRepository.getShiTuMXList(shiTuID,likeQuery,chaXunLX,pageIndex,pageSize);
    }

    /**
     * 获取视图字段列表数量
     *
     */
    @Override
    public Integer getShiTuMXCount(String shiTuID, String likeQuery, Integer chaXunLX) {
        return shiTuMXRepository.getShiTuMXCount(shiTuID,likeQuery,chaXunLX);
    }

    /**
     * 获取关联条件字段
     *
     */
    @Override
    public List<GuanLianTJZD> getGuanLianTJZD(String shiTuSTID) {
        return shiTuMXRepository.getGuanLianTJZD(shiTuSTID);
    }

    @Override
    public  List<GuanLianTJZD> getGuanLianTJList(String shiTuSTID) {
        return shiTuMXRepository.getGuanLianTJList(shiTuSTID);
    }

    @Override
    public Boolean updateShiTuMX(String shiTuSTID,String shiTuMC, List<GuanLianTJZD> guanLianTJZDS) {
        shiTuMXRepository.asDeleteDsl().where(n->n.shiTuID.eq(shiTuSTID)).execute();
        List<SC_CX_ShiTuMXModel> shiTuMXModels=new ArrayList<>();
        guanLianTJZDS.forEach(s->{
            SC_CX_ShiTuMXModel shiTuMXModel=new SC_CX_ShiTuMXModel();
            shiTuMXModel.setShiTuID(shiTuSTID);
            shiTuMXModel.setShiTuMC(shiTuMC);
            BeanUtil.copyProperties(s,shiTuMXModel);
            shiTuMXModels.add(shiTuMXModel);
        });
        shiTuMXRepository.saveAll(shiTuMXModels);
        return true;
    }

    /**
     * 作废视图字段
     *
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean zuoFeiShiTMX(String id) throws WeiZhaoDSJException {
        SC_CX_ShiTuMXDto shiTuMXByID = getShiTuMXByID(id);
        shiTuMXGXService.delectShiTuMXGX(shiTuMXByID.getShiTuID());
        shiTuMXRepository.asDeleteDsl().where(n->n.id.eq(id)).execute();
        return true;

    }

    @Override
    public Boolean updateShiTuMX(UpdateShiTuMXDto updateShiTuMXDto) throws WeiZhaoDSJException {
        SC_CX_ShiTuMXDto shiTuMXByID = getShiTuMXByID(updateShiTuMXDto.getId());
        shiTuMXByID.setZiDuanMC(updateShiTuMXDto.getZiDuanMC());
        shiTuMXByID.setTiaoJianBZ(updateShiTuMXDto.getTianJianBZ());
        shiTuMXByID.setShuChuBZ(updateShiTuMXDto.getShuChuBZ());
        shiTuMXByID.setShuChuBXBZ(updateShiTuMXDto.getShuChuBXBZ());
        shiTuMXRepository.save(BeanUtil.copyProperties(shiTuMXByID,SC_CX_ShiTuMXModel::new));
        shiTuMXGXService.addShiTuMXGX(shiTuMXByID.getShiTuID(),shiTuMXByID.getZiDuanBM(),shiTuMXByID.getZiDuanMC(),updateShiTuMXDto.getGuanLianZDList());
        return true;
    }


}
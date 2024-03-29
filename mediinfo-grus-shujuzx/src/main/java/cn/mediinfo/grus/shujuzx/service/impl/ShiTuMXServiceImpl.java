package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.core.util.CollectionUtil;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.dto.shitumx.*;
import cn.mediinfo.grus.shujuzx.dto.shitumx.SC_CX_ShiTuMXGXDto;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.*;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ShiTuMXModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ShiTuXXModel;
import cn.mediinfo.grus.shujuzx.po.shituxx.ShiTuXXPo;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.LingChuangJSPZZDXXRso;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.YuanSuJXXRso;
import cn.mediinfo.grus.shujuzx.remoteservice.GongYongRemoteService;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_ShiTuMXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_ShiTuXXRepository;
import cn.mediinfo.grus.shujuzx.service.ShiTuMXGXService;
import cn.mediinfo.grus.shujuzx.service.ShiTuMXService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 临床检索视图明细服务
 */
@Service
public class ShiTuMXServiceImpl implements ShiTuMXService {
    private final SC_CX_ShiTuMXRepository shiTuMXRepository;
    private final SC_CX_ShiTuXXRepository shiTuXXRepository;
    private final GongYongRemoteService gongYongRemoteService;
    private final ShiTuMXGXService shiTuMXGXService;


    public ShiTuMXServiceImpl(SC_CX_ShiTuMXRepository shiTuMXRepository,
                              SC_CX_ShiTuXXRepository shiTuXXRepository,
                              GongYongRemoteService gongYongRemoteService,
                              ShiTuMXGXService shiTuMXGXService) {
        this.shiTuMXRepository = shiTuMXRepository;
        this.shiTuXXRepository = shiTuXXRepository;
        this.gongYongRemoteService = gongYongRemoteService;
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

    /**
     * 根据ID获取临床检索视图明细     *     * @param id     * @return
     */
    @Override
    public ShiTuMXDto getShiTuMXGXByID(String id) throws WeiZhaoDSJException {
        SC_CX_ShiTuMXDto shiTuMXByID = getShiTuMXByID(id);
        ShiTuMXDto shiTuMXDto = BeanUtil.copyProperties(shiTuMXByID, ShiTuMXDto::new);
        shiTuMXDto.setGuanLianZDList(shiTuMXGXService.getShiTuMXGXList(shiTuMXByID.getShiTuID(), shiTuMXByID.getZiDuanBM(), shiTuMXByID.getZiDuanMC()));
        return shiTuMXDto;
    }

    /**
     * 获取临床检索视图字段
     *
     * @return
     */
    @Override
    public List<FieldDTO> listFields(Set<String> shiTuMXIds) throws YuanChengException {
        //查询视图明细
        List<SC_CX_ShiTuMXByIdDto> shiTuMXModels = shiTuMXRepository.findByIdIs(shiTuMXIds);
        //获取视图id集合
        List<String> shiTuIds = shiTuMXModels.stream().map(SC_CX_ShiTuMXByIdDto::getShiTuID).toList();
        //查询视图信息
        List<SC_CX_ShiTuXXByShiTuIDDto> shiTuXXList = shiTuXXRepository.findByShiTuIDIn(shiTuIds);

        List<cn.mediinfo.grus.shujuzx.dto.shitumx.SC_CX_ShiTuMXGXDto> shiTuMXGX = shiTuMXGXService.getShiTuMXGXByShiTuID(shiTuIds);

        List<SC_CX_ShiTuXXZDDto> shiTuXXZDList = new ArrayList<>();
        for (SC_CX_ShiTuXXByShiTuIDDto shiTuXXByShiTuIDDto : shiTuXXList) {
            SC_CX_ShiTuXXZDDto shiTuXXZDDto = new SC_CX_ShiTuXXZDDto();
            BeanUtil.copyProperties(shiTuXXByShiTuIDDto, shiTuXXZDDto);
            List<SC_CX_ShiTuMXByIdDto> ziDuanMX = shiTuMXModels.stream()
                    .filter(n -> n.getShiTuID().equals(shiTuXXByShiTuIDDto.getShiTuID())).collect(Collectors.toList());
            for (SC_CX_ShiTuMXByIdDto z : ziDuanMX) {
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
    public List<TableDTO> listTable(Set<String> shiTuMXIds) throws YuanChengException {

        //查询视图明细
        List<SC_CX_ShiTuMXByIdDto> shiTuMXModels = shiTuMXRepository.findByIdIs(shiTuMXIds);
        //获取视图id集合
        List<String> shiTuIds = shiTuMXModels.stream().map(SC_CX_ShiTuMXByIdDto::getShiTuID).toList();
        //查询视图信息
        var shiTuXXList = shiTuXXRepository.findByShiTuIDIn(shiTuIds);

        List<ShuJuLYDto> shuJuLYDtos = BeanUtil.copyListProperties(shiTuXXList, ShuJuLYDto::new);

        List<LingChuangJSPZDto> lingChuangJSPZDtos = new ArrayList<>();

        return gongYongRemoteService.getShiTuGLFSGLTJ(lingChuangJSPZDtos).getData("获取功能服务字段信息失败");

    }

    @Override
    public ShuJuXSTXQDto getShuJuSTXQDto(List<String> shiTuIds) throws YuanChengException {
        ShuJuXSTXQDto shuJuXSTXQDto = new ShuJuXSTXQDto();
        //查询视图信息
        List<SC_CX_ShiTuXXByShiTuIDDto> shiTuXXModelList = shiTuXXRepository.findByShiTuIDIn(shiTuIds);
        //查询视图明细
        List<ShiTuMXListDto> shiTuMXModelList = shiTuMXRepository.getShiTuMXLists(shiTuIds, "", 0, 1, 9999);
        //查询视图明细关系
        List<ShuJuJMXZDDto> shiTuMXGXDtoList = shiTuMXGXService.getShiTuMXGXBySTIDs(shiTuIds);
        //公共接口入参组装
        List<LingChuangJSPZDto> lingChuangJSPZDtos = new ArrayList<>();
        for (SC_CX_ShiTuXXByShiTuIDDto shiTuXX : shiTuXXModelList) {
            LingChuangJSPZDto lingChuangJSPZDto = new LingChuangJSPZDto();
            lingChuangJSPZDto.setShuJuLYID(shiTuXX.getShuJuLYID());
            lingChuangJSPZDto.setShuJuLYLXDM(shiTuXX.getShuJuLYLXDM());
            lingChuangJSPZDto.setShiTuID(shiTuXX.getShiTuID());
            lingChuangJSPZDto.setShiTuMC(shiTuXX.getShiTuMC());
            //视图ID关联查找到明细
            List<ShuJuJMXZDDto> shuJuJMXZDDtos = shiTuMXModelList.stream().filter(t -> Objects.equals(t.getShiTuID(), shiTuXX.getShiTuID())).map(t -> {
                ShuJuJMXZDDto shuJuJMXZDDto = new ShuJuJMXZDDto();
                shuJuJMXZDDto.setZiDuanBM(t.getZiDuanBM());
                shuJuJMXZDDto.setZiDuanMC(t.getZiDuanMC());
                List<ShuJuJMXZDDto> shiTuMXGXDto =
                        shiTuMXGXDtoList.stream().filter(n -> Objects.equals(n.getShiTuID(), shiTuXX.getShiTuID()) && Objects.equals(n.getZiDuanBM(), t.getZiDuanBM())).toList();
                shuJuJMXZDDto.setGuanLianTJZDList(shiTuMXGXDto);
                return shuJuJMXZDDto;
            }).toList();
            lingChuangJSPZDto.setShuJuJMXZDDtos(shuJuJMXZDDtos);
            lingChuangJSPZDtos.add(lingChuangJSPZDto);
        }
        if (CollectionUtils.isEmpty(lingChuangJSPZDtos)) {
            return null;
        }
        List<TableDTO> lingChuangJSPZZDList = gongYongRemoteService.getShiTuGLFSGLTJ(lingChuangJSPZDtos).getData("获取功能服务字段信息失败");
        List<FieldDTO> fieldDTOList = new ArrayList<>();
        shuJuXSTXQDto.setTableDTO(lingChuangJSPZZDList);
        List<ShuJuJMXZDDto> shuJuJMXZDDtoList = new ArrayList<>();
        for (TableDTO table : lingChuangJSPZZDList) {
            List<SchemaTable> schemaTable = table.getSchemaTableList();
            schemaTable.forEach(m -> {
                shuJuJMXZDDtoList.addAll(m.getShuJuJMXZDDtos());
            });

        }
        fieldDTOList.addAll(BeanUtil.copyListProperties(shuJuJMXZDDtoList, FieldDTO::new));
        shuJuXSTXQDto.setFieldDTO(fieldDTOList);
        return shuJuXSTXQDto;
    }

    @Override
    public List<DatabaseDTO> listDatabase(Set<String> shiTuMXIds) {
        return null;
    }

    /**
     * 批量新增视图字段
     */
    @Override
    public Boolean addShiTuMX(AddShiTuMXDto addShiTuMXDto) throws WeiZhaoDSJException, MsfResponseException {

        List<String> addziDuanBM = addShiTuMXDto.getZiDuanXXList().stream().map(ZiDuanXXListDto::getZiDuanBM).collect(Collectors.toList());
        List<SC_CX_ShiTuMXModel> shiTuMxList = shiTuMXRepository.asQuerydsl()
                .where(n -> n.shiTuID.eq(addShiTuMXDto.getShiTuID()))
                .where(n -> n.ziDuanBM.in(addziDuanBM)).fetch();

        List<SC_CX_ShiTuMXModel> shiTuMXModels = new ArrayList<>();
        //赋值
        addShiTuMXDto.getZiDuanXXList().forEach(s -> {
            if (shiTuMxList.stream().noneMatch(n -> n.getZiDuanBM().equals(s.getZiDuanBM()))) {
                SC_CX_ShiTuMXModel shiTuMXModel = new SC_CX_ShiTuMXModel();
                shiTuMXModel.setShiTuID(addShiTuMXDto.getShiTuID());
                shiTuMXModel.setShiTuMC(addShiTuMXDto.getShiTuMC());
                shiTuMXModel.setZuZhiJGID(ShuJuZXConstant.TONGYONG_JGID);
                shiTuMXModel.setZuZhiJGMC(ShuJuZXConstant.TONGYONG_JGMC);
                BeanUtil.copyProperties(s, shiTuMXModel);
                if (Objects.equals(s.getShuChuBZ(),"1") )
                {
                    shiTuMXModel.setShuChuBXBZ(0);
                }
                shiTuMXModels.add(shiTuMXModel);
            }
        });
        //更新视图明细
        shiTuMXRepository.saveAll(shiTuMXModels);
        return true;
    }

    /**
     * 根据视图id获取视图字段信息列表
     */
    @Override
    public List<ShiTuMXListDto> getShiTuMXList(String shiTuID, String fuLeiID, String likeQuery, Integer chaXunLX, Integer pageIndex, Integer pageSize) {
        if (!StringUtil.hasText(shiTuID)) {
            List<String> shiTuXXIDList = shiTuXXRepository.getShiTuXXIDList(fuLeiID);
            return shiTuMXRepository.getShiTuMXLists(shiTuXXIDList, likeQuery, chaXunLX, pageIndex, pageSize);
        }
        return shiTuMXRepository.getShiTuMXList(shiTuID, likeQuery, chaXunLX, pageIndex, pageSize);
    }

    /**
     * 获取视图字段列表数量
     */
    @Override
    public Integer getShiTuMXCount(String shiTuID, String fuLeiID, String likeQuery, Integer chaXunLX) {
        if (!StringUtil.hasText(shiTuID)) {
            List<String> shiTuXXIDList = shiTuXXRepository.getShiTuXXIDList(fuLeiID);
            return shiTuMXRepository.getShiTuMXCounts(shiTuXXIDList, likeQuery, chaXunLX);
        }
        return shiTuMXRepository.getShiTuMXCount(shiTuID, likeQuery, chaXunLX);
    }

    /**
     * 获取关联条件字段
     */
    @Override
    public List<GuanLianTJZD> getGuanLianTJZD(String shiTuSTID) {
        return shiTuMXRepository.getGuanLianTJZD(shiTuSTID);
    }

    /**
     * 获取关联条件字段
     */
    @Override
    public List<GuanLianTJZD> getGuanLianTJList(String shiTuSTID) {
        return shiTuMXRepository.getGuanLianTJList(shiTuSTID);
    }

    /**
     * 更新视图字段
     */
    @Override
    public Boolean updateShiTuMX(String shiTuSTID, String shiTuMC, List<GuanLianTJZD> guanLianTJZDS) {
        shiTuMXRepository.asDeleteDsl().where(n -> n.shiTuID.eq(shiTuSTID)).execute();
        List<SC_CX_ShiTuMXModel> shiTuMXModels = new ArrayList<>();
        guanLianTJZDS.forEach(s -> {
            SC_CX_ShiTuMXModel shiTuMXModel = new SC_CX_ShiTuMXModel();
            shiTuMXModel.setShiTuID(shiTuSTID);
            shiTuMXModel.setShiTuMC(shiTuMC);
            BeanUtil.copyProperties(s, shiTuMXModel);
            shiTuMXModels.add(shiTuMXModel);
        });
        shiTuMXRepository.saveAll(shiTuMXModels);
        return true;
    }

    /**
     * 作废视图字段
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean zuoFeiShiTMX(String id) throws WeiZhaoDSJException {
        SC_CX_ShiTuMXDto shiTuMXByID = getShiTuMXByID(id);
        shiTuMXGXService.delectShiTuMXGX(shiTuMXByID.getShiTuID(),shiTuMXByID.getZiDuanBM());
        shiTuMXRepository.asDeleteDsl().where(n -> n.id.eq(id)).execute();
        return true;

    }

    @Override
    public Boolean updateShiTuMX(UpdateShiTuMXDto updateShiTuMXDto) throws WeiZhaoDSJException {
        SC_CX_ShiTuMXModel shiTuMXByID = shiTuMXRepository.findById(updateShiTuMXDto.getId()).orElse(new SC_CX_ShiTuMXModel());
        shiTuMXByID.setZiDuanMC(updateShiTuMXDto.getZiDuanMC());
        shiTuMXByID.setTiaoJianBZ(updateShiTuMXDto.getTiaoJianBZ());
        shiTuMXByID.setShuChuBZ(updateShiTuMXDto.getShuChuBZ());
        shiTuMXByID.setShuChuBXBZ(updateShiTuMXDto.getShuChuBXBZ());
        shiTuMXRepository.save(BeanUtil.copyProperties(shiTuMXByID, SC_CX_ShiTuMXModel::new));
        shiTuMXGXService.addShiTuMXGX(shiTuMXByID.getShiTuID(), shiTuMXByID.getZiDuanBM(), shiTuMXByID.getZiDuanMC(), updateShiTuMXDto.getGuanLianZDList());
        return true;
    }

    /**
     * 根据视图ID获取视图明细
     */
    @Override
    public List<SchemaTable> getFangAnSCZD(List<ShuJuSTDto> shuJuSTDto) throws WeiZhaoDSJException {
        List<String> shiTUIDXX = shuJuSTDto.stream().map(ShuJuSTDto::getShiTuID).collect(Collectors.toList());
        List<String> ziDuanBMXX = new ArrayList<>();
        for (var list : shuJuSTDto.stream().map(ShuJuSTDto::getZiDuanBM).toList()) {
            for (var key : list) {
                ziDuanBMXX.add(key.toUpperCase());
            }
        }
        List<String> shiTuIds = shiTUIDXX;
        var shiTuXXList = shiTuXXRepository.findByShiTuIDIn(shiTuIds);//视图信息
        var shiTuMXZDList = shiTuMXRepository.getShiTuMXSJ(shiTUIDXX, ziDuanBMXX);//视图明细信息
        //公共接口入参组装
        List<LingChuangJSPZDto> lingChuangJSPZDtos = new ArrayList<>();
        //视图信息分组 数据来源ID 数据来源类型DM
        var shiTuXXSet = shiTuXXList.stream().collect(Collectors.groupingBy(s -> new ShiTuXXPo(s.getShuJuLYID(), s.getShuJuLYLXDM()))).keySet();
        for (var key : shiTuXXSet) {
            LingChuangJSPZDto lingChuangJSPZDto = new LingChuangJSPZDto();
            lingChuangJSPZDto.setShuJuLYID(key.ShuJuLYID());
            lingChuangJSPZDto.setShuJuLYLXDM(key.ShuJuLYLXDM());
            var ids = shiTuXXList.stream().filter(t -> Objects.equals(t.getShuJuLYID(), key.ShuJuLYID()) && Objects.equals(t.getShuJuLYLXDM(), key.ShuJuLYLXDM())).map(SC_CX_ShiTuXXByShiTuIDDto::getShiTuID).toList();
            List<ShuJuJMXZDDto> list = shiTuMXZDList.stream().filter(t -> ids.contains(t.getShiTuID())).map(t -> {
                ShuJuJMXZDDto shuJuJMXZDDto = new ShuJuJMXZDDto();
                shuJuJMXZDDto.setZiDuanBM(t.getZiDuanBM());
                shuJuJMXZDDto.setZiDuanMC(t.getZiDuanMC());
                return shuJuJMXZDDto;
            }).toList();
            lingChuangJSPZDto.setShuJuJMXZDDtos(list);
            lingChuangJSPZDtos.add(lingChuangJSPZDto);
        }
        //获取公共接口数据
        List<LingChuangJSPZZDXXRso> lingChuangJSPZZDList = gongYongRemoteService.getlingChuangJSPZZDXX(lingChuangJSPZDtos).getData();
        if (CollectionUtils.isEmpty(lingChuangJSPZZDList)) {
            return null;
        }
        List<SchemaTable> result = new ArrayList<>();
        for (LingChuangJSPZZDXXRso pz : lingChuangJSPZZDList) {
            for (ShuJuJMXZDDto zd : pz.getShiTuMXZDDtos()) {
                var cunZai = result.stream().filter(t -> Objects.equals(t.getMoShi(), zd.getShuJuYMC()) && Objects.equals(t.getBiaoMing(), zd.getBiaoMing())).findFirst().orElse(null);
                ;
                ShuJuJMXZDDto shuJuJMXZDDto = new ShuJuJMXZDDto();
                shuJuJMXZDDto.setMoShi(zd.getShuJuYMC());
                shuJuJMXZDDto.setZiDuanBM(zd.getZiDuanBM());
                shuJuJMXZDDto.setZiDuanMC(zd.getZiDuanMC());
                if (cunZai == null) {
                    SchemaTable schema = new SchemaTable();
                    List<ShuJuJMXZDDto> dto = new ArrayList<>();
                    dto.add(shuJuJMXZDDto);
                    schema.setMoShi(zd.getShuJuYMC());
                    schema.setBiaoMing(zd.getBiaoMing());
                    schema.setShuJuJMXZDDtos(dto);
                    result.add(schema);
                } else {
                    var cunZaiZDMX = cunZai.getShuJuJMXZDDtos();
                    cunZaiZDMX.add(shuJuJMXZDDto);
                }
            }
        }
        return result;
    }

    @Override
    public List<YuanSuJXXRso> getZiDuanXXList(String id, String leiXing, String likeQuery,String shiTuSTID) throws YuanChengException {
        List<YuanSuJXXRso> ziDuanXXList = gongYongRemoteService.getZiDuanXXList(id, leiXing, likeQuery).getData("获取功能服务字段信息失败");
        List<String> shiTuMXZD = shiTuMXRepository.getShiTuMXZD(shiTuSTID);
        // 将列表转换为集合以优化查找性能
        Set<String> shiTuMXZDSet = new HashSet<>(shiTuMXZD);
        //循环视图明细zd

        return ziDuanXXList.stream().filter(n->!shiTuMXZDSet.contains(n.getZiDuanBM())).toList();
    }


}
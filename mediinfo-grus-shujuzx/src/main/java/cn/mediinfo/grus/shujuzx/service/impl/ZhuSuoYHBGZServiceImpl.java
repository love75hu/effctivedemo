package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs.*;
import cn.mediinfo.grus.shujuzx.model.BR_ZD_HeBingGZMXModel;
import cn.mediinfo.grus.shujuzx.model.BR_ZD_HeBingGZModel;
import cn.mediinfo.grus.shujuzx.model.QBR_ZD_HeBingGZMXModel;
import cn.mediinfo.grus.shujuzx.repository.BR_ZD_HeBingGZMXRepository;
import cn.mediinfo.grus.shujuzx.repository.BR_ZD_HeBingGZRepository;
import cn.mediinfo.grus.shujuzx.service.ZhuSuoYHBGZService;
import cn.mediinfo.starter.base.exception.MsfResponseException;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.stringgenerator.StringGenerator;
import cn.mediinfo.starter.base.util.MapUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 主索引合并规则维护
 */
@Service
public class ZhuSuoYHBGZServiceImpl implements ZhuSuoYHBGZService {
    private final BR_ZD_HeBingGZRepository brZdHeBingGZRepository;
    private final BR_ZD_HeBingGZMXRepository brZdHeBingGZMXRepository;
    private final StringGenerator stringGenerator;
    public ZhuSuoYHBGZServiceImpl(BR_ZD_HeBingGZRepository brZdHeBingGZRepository, BR_ZD_HeBingGZMXRepository brZdHeBingGZMXRepository, StringGenerator stringGenerator) {
        this.brZdHeBingGZRepository = brZdHeBingGZRepository;
        this.brZdHeBingGZMXRepository = brZdHeBingGZMXRepository;
        this.stringGenerator = stringGenerator;
    }

    /**
     * 新增规则信息
     * @param  dto DTO
     * @return boolean
     */
    @Transactional(rollbackOn = Exception.class)
    @Override
    public String addGuiZeXX(BR_ZD_HeBingGZCreateDto dto) throws TongYongYWException {
        //规则表添加信息
        var addModel = new BR_ZD_HeBingGZModel();
        MapUtils.mergeProperties(dto,addModel);
        addModel.setZuZhiJGID(ShuJuZXConstant.TONGYONG_JGID);
        addModel.setZuZhiJGMC(ShuJuZXConstant.TONGYONG_JGMC);
        var id = stringGenerator.Create();
        addModel.setGuiZeID(id);
        brZdHeBingGZRepository.save(addModel);

        //规则明细表添加消息
        List<BR_ZD_HeBingGZMXModel> mxModels = new ArrayList<>();
        for(var item :dto.getGuiZeMXList()){
            var model = new BR_ZD_HeBingGZMXModel();
            model.setZuZhiJGID(ShuJuZXConstant.TONGYONG_JGID);
            model.setZuZhiJGMC(ShuJuZXConstant.TONGYONG_JGMC);
            model.setGuiZeID(id);
            model.setGuiZeMC(dto.getGuiZeMC());
            MapUtils.mergeProperties(item,model);
            mxModels.add(model);
        }
        brZdHeBingGZMXRepository.saveAll(mxModels);
        return id;
    }

    /**
     * 修改规则信息
     * @param  heBingGZUpdateDto DTO
     * @return boolean
     */
    @Transactional(rollbackOn = Exception.class)
    @Override
    public String updateGuiZeXX(BR_ZD_HeBingGZUpdateDto heBingGZUpdateDto) throws TongYongYWException {
        var updateModel =brZdHeBingGZRepository.findById(heBingGZUpdateDto.getId()).orElse(null);
        if(updateModel == null){
            throw new TongYongYWException("未找到相关可修改的规则名称");
        }
        MapUtils.mergeProperties(heBingGZUpdateDto,updateModel);
        brZdHeBingGZRepository.save(updateModel);
        //新增明细表字段
        if(!CollectionUtils.isEmpty(heBingGZUpdateDto.getAddGuiZeMXList())){
            var addEntitys = MapUtils.copyListProperties(heBingGZUpdateDto.getAddGuiZeMXList(),BR_ZD_HeBingGZMXModel::new,(dto,model)->{
                model.setZuZhiJGID(ShuJuZXConstant.TONGYONG_JGID);
                model.setZuZhiJGMC(ShuJuZXConstant.TONGYONG_JGMC);
                model.setGuiZeID(heBingGZUpdateDto.getGuiZeID());
                model.setGuiZeMC(heBingGZUpdateDto.getGuiZeMC());
            });
            brZdHeBingGZMXRepository.saveAll(addEntitys);
        }
        //更新明细表字段
        if(!CollectionUtils.isEmpty(heBingGZUpdateDto.getUpdateGuiZeMXList())){
            var updateIdList = heBingGZUpdateDto.getUpdateGuiZeMXList().stream().map(GuiZeMXListDto::getId).toList();
            //获取需要更新的实体
            var updateEntitys = brZdHeBingGZMXRepository.findAllById(updateIdList);
            for (var entity : updateEntitys){
                entity.setGuiZeID(heBingGZUpdateDto.getGuiZeID());
                entity.setGuiZeMC(heBingGZUpdateDto.getGuiZeMC());
            }
            for (var dto : heBingGZUpdateDto.getUpdateGuiZeMXList()){
                updateEntitys.stream().filter(o -> Objects.equals(o.getId(), dto.getId())).findFirst().ifPresent(entity -> MapUtils.mergeProperties(dto, entity));
            }
            brZdHeBingGZMXRepository.saveAll(updateEntitys);
        }
        //作废明细表字段
        brZdHeBingGZMXRepository.softDelete(heBingGZUpdateDto.getDeleteIds());
        return heBingGZUpdateDto.getGuiZeID();
    }

    /**
     * 作废规则
     * @param id id
     * @return boolean
     */
    @Transactional(rollbackOn = Exception.class)
    @Override
    public Integer zuoFeiGuiZeXX(String id) throws TongYongYWException {
        var deleteModel =brZdHeBingGZRepository.findById(id).orElse(null);
        if(deleteModel == null){
            throw new TongYongYWException("未找到相关可修改的规则名称");
        }
        brZdHeBingGZRepository.softDelete(deleteModel);
        var qModel = QBR_ZD_HeBingGZMXModel.bR_ZD_HeBingGZMXModel;
        brZdHeBingGZMXRepository.softDelete(qModel.guiZeID.eq(deleteModel.getGuiZeID()));
        return 1;
    }

    /**
     * 获取规则列表
     * @return 规则DTO
     * @throws TongYongYWException 返回异常
     */
    @Override
    public List<BR_ZD_HeBingGZListDto> getGuiZeList() throws TongYongYWException {
        var models = brZdHeBingGZRepository.findByZuZhiJGIDOrderByFaZhiDesc("0");
        return MapUtils.copyListProperties(models,BR_ZD_HeBingGZListDto::new);
    }

    /**
     * 根据规则ID获取规则信息和规则明细内容
     * @return 规则DTO
     * @throws TongYongYWException 返回异常
     */
    @Override
    public HeBingGZMXDto getGuiZeXXByID(String guiZeID) throws TongYongYWException {
        //获取规则名称和阀值
        var guiZeXX = brZdHeBingGZRepository.findFirstByGuiZeID(guiZeID);
        if(guiZeXX == null){
            throw new TongYongYWException("未找到对应规则");
        }
        //获取规则明细内容
        var guiZeMX = brZdHeBingGZMXRepository.findByGuiZeID(guiZeID);
        //拼装
        var result = new HeBingGZMXDto();
        result.setId(guiZeXX.getId());
        result.setGuiZeMC(guiZeXX.getGuiZeMC());
        result.setFaZhi(guiZeXX.getFaZhi());
        result.setGuiZeMXList(MapUtils.copyListProperties(guiZeMX,GuiZeMXListDto::new));
        return result;
    }
}

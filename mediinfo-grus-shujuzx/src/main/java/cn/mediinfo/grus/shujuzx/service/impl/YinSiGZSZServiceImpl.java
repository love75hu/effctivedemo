package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.dto.YinSiGZSZs.*;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_YinSiGZSZModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_YinSiPZModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_ZhanShiPZModel;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_YinSiGZSZRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_YinSiPZRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_ZhanShiPZRepository;
import cn.mediinfo.grus.shujuzx.service.YinSiGZSZService;
import cn.mediinfo.starter.base.exception.MsfException;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.util.MapUtils;
import jakarta.transaction.Transactional;
import cn.mediinfo.grus.shujuzx.constant.ChaXunMSEnum;
import cn.mediinfo.grus.shujuzx.dto.YinSiGZSZs.SC_ZD_YinSiPZOutDto;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;


/**
 * 隐私规则设置
 */
@Service
public class YinSiGZSZServiceImpl implements YinSiGZSZService {
    private final SC_ZD_YinSiPZRepository yinSiPZRepository;
    private final SC_ZD_YinSiGZSZRepository yinSiGZSZRepository;
    private  final SC_ZD_ZhanShiPZRepository zhanShiPZRepository;
    public YinSiGZSZServiceImpl(SC_ZD_YinSiPZRepository yinSiPZRepository,
                                SC_ZD_YinSiGZSZRepository yinSiGZSZRepository,
                                SC_ZD_ZhanShiPZRepository zhanShiPZRepository) {
        this.yinSiPZRepository = yinSiPZRepository;
        this.yinSiGZSZRepository = yinSiGZSZRepository;
        this.zhanShiPZRepository = zhanShiPZRepository;
    }

    /**
     * 新增隐私规则
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Integer addYinSiGZ(SC_ZD_YinSiGZSZInDto yinSiGZSZInDto)  throws MsfException {
        Boolean existBool = yinSiGZSZRepository.existsByShuJuYMC(yinSiGZSZInDto.getShuJuYMC());
        if (existBool){
            throw new TongYongYWException("数据元名称已存在,请重新确认!");
        }
        SC_ZD_YinSiGZSZModel addModel = new SC_ZD_YinSiGZSZModel();
        MapUtils.mergeProperties(yinSiGZSZInDto,addModel);
        addModel.setZuZhiJGID("0");
        addModel.setZuZhiJGMC("通用");
        yinSiGZSZRepository.save(addModel);
        return 1;
    }

    /**
     * 保存隐私设置
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean saveYinSiSZList(SC_ZD_YinSiPZCreateDto dto) {
        if (dto.getAddList().size() > 0){
            List<SC_ZD_YinSiPZModel> tongYongYSPZs = yinSiPZRepository.findByChaXunMSDMAndZuZhiJGID(dto.getAddList().get(0).getChaXunMSDM(),"0");
            List<SC_ZD_YinSiPZDto> scZdYinSiPZDtos = dto.getAddList().stream().filter(s -> tongYongYSPZs.stream().map(SC_ZD_YinSiPZModel::getShuJuYLM).toList().contains(s.getShuJuYLM())).toList();
            yinSiPZRepository.saveAll(MapUtils.copyListProperties(scZdYinSiPZDtos,SC_ZD_YinSiPZModel::new));
        }
        if (dto.getZuoFeiIds().size() > 0){
            yinSiPZRepository.softDelete(dto.getZuoFeiIds());
        }
        return true;
    }

    /**
     *保存展示配置
     */

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean saveZhanShiPZ(SC_ZD_ZhanShiPZCreateDto dto) {
        if (dto.getAddList().size() > 0){
            zhanShiPZRepository.saveAll(MapUtils.copyListProperties(dto.getAddList(), SC_ZD_ZhanShiPZModel::new, (zhanShiPZDto, model) -> {
                model.setId(null);
                model.setZuZhiJGID(zhanShiPZDto.getZuZhiJGID());
                model.setZuZhiJGMC(zhanShiPZDto.getZuZhiJGMC());
                model.setChaXunMSDM(zhanShiPZDto.getChaXunMSDM());
                model.setChaXunMSMC(zhanShiPZDto.getChaXunMSMC());
            }));
        }
        if (dto.getUpdateList().size() > 0){
            String zuZhiJGID = dto.getUpdateList().get(0).getZuZhiJGID();
            List<String> idList = dto.getUpdateList().stream().map(SC_ZD_ZhanShiPZDto::getId).toList();
            var zhanShiPZList = zhanShiPZRepository.findByZuZhiJGIDAndIdIn(zuZhiJGID,idList);
            for (var item : zhanShiPZList){
                var zhanShiPZ = dto.getUpdateList().stream().filter(x -> Objects.equals(x.getId() , item.getId())).findFirst().orElseGet(null);
                if (Objects.nonNull(zhanShiPZ)){
                    MapUtils.mergeProperties(zhanShiPZ,item,true);
                }
            }
            zhanShiPZRepository.saveAll(zhanShiPZList);
        }
        if (dto.getZuoFeiIds().size() > 0){
            zhanShiPZRepository.softDelete(dto.getZuoFeiIds());
        }
        return true;
    }

    /**
     *修改隐私规则
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Integer updateYinSiGZ(SC_ZD_YinSiGZSZInDto yinSiGZSZInDto) throws MsfException {
        SC_ZD_YinSiGZSZModel updateModel = yinSiGZSZRepository.findById(yinSiGZSZInDto.getId()).orElseGet(null);
        if (Objects.isNull(updateModel)){
            throw new TongYongYWException("未找到相关可修改的信息!");
        }
        MapUtils.mergeProperties(yinSiGZSZInDto,updateModel,true);
        yinSiGZSZRepository.save(updateModel);
        return 1;
    }
    /**
     * 作废隐私规则
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Integer zuoFeiYinSiGZ(String id) throws MsfException {
        if (!yinSiGZSZRepository.existsById(id)){
            throw new TongYongYWException("未找到相关可作废的信息!");
        }
        yinSiGZSZRepository.softDelete(id);
        return 1;
    }
    /**
     * 移除隐私配置
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean zuoFeiYinSiSZ(String id) {
        yinSiPZRepository.softDelete(id);
        return true;
    }
    /**
     * 启用隐私设置
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean qiYongYinSiSZ(String id, Integer qiYongBZ) {
        SC_ZD_YinSiPZModel yinSiPZXX = yinSiPZRepository.findById(id).orElseGet(null);
        if (yinSiPZXX == null) return false;
        yinSiPZXX.setQiYongBZ(qiYongBZ);
        yinSiPZRepository.save(yinSiPZXX);
        return true;
    }
    /**
     *更新隐私规则
     * @param zuZhiJGID  组织机构id
     * @param zuZhiJGMC  组织机构名称
     * @param chaXunMSDM 查询模式代码
     * @param peiZhiLXDM 配置类型代码
     * @return true
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean updateZhanShiPZ(String zuZhiJGID, String zuZhiJGMC, String chaXunMSDM, String peiZhiLXDM) {
        List<SC_ZD_ZhanShiPZModel> yinSiPZList = zhanShiPZRepository.findByZuZhiJGIDOrZuZhiJGIDAndChaXunMSDMAndPeiZhiLXDM("0", zuZhiJGID, chaXunMSDM, peiZhiLXDM);
        //通用数据
        var tongYongSJList = yinSiPZList.stream().filter(x -> "0".equals(x.getZuZhiJGID())).toList();
        //机构数据
        var jiGouSJList = yinSiPZList.stream().filter(x -> Objects.equals(x.getZuZhiJGID(),zuZhiJGID)).toList();
        //取差集
        List<SC_ZD_ZhanShiPZModel> chaJiList = tongYongSJList.stream().filter(t -> !jiGouSJList.contains(t)).toList();
        chaJiList.forEach(zhanShiPZModel->{
            zhanShiPZModel.setId(null);
            zhanShiPZModel.setZuZhiJGID(zuZhiJGID);
            zhanShiPZModel.setZuZhiJGMC(zuZhiJGMC);
        });
        zhanShiPZRepository.saveAll(chaJiList);
        return true;
    }

    /**
     *初始化隐私设置
     * @param zuZhiJGID 组织机构ID
     * @param zuZhiJGMC 组织机构名称
     * @param chaXunMSDM 查询模式代码
     * @return true
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean chuShiHYinSiZS(String zuZhiJGID, String zuZhiJGMC, String chaXunMSDM) {
        //通用数据
        List<SC_ZD_YinSiPZModel> tongYongSJList = yinSiPZRepository.findByChaXunMSDMAndZuZhiJGID(chaXunMSDM, "0");
        //删除机构之前的数据
        yinSiPZRepository.softDelete(yinSiPZRepository.findByChaXunMSDMAndZuZhiJGID(chaXunMSDM, zuZhiJGID));
        //初始化数据
        tongYongSJList.forEach(item->{
            item.setZuZhiJGID(zuZhiJGID);
            item.setZuZhiJGMC(zuZhiJGMC);
        });
        yinSiPZRepository.saveAll(tongYongSJList);
        return true;
    }
    /**
     *初始化隐私规则配置
     * @param zuZhiJGID 组织机构ID
     * @param zuZhiJGMC 组织机构名称
     * @param chaXunMSDM 查询模式代码
     * @return true
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean chuShiHYSGZPZ(String zuZhiJGID, String zuZhiJGMC, String chaXunMSDM) {
        //通用数据
        List<SC_ZD_YinSiPZModel> tongYongSJList = yinSiPZRepository.findByChaXunMSDMAndZuZhiJGID(chaXunMSDM, "0");
        //删除机构之前的数据
        yinSiPZRepository.softDelete(yinSiPZRepository.findByChaXunMSDMAndZuZhiJGID(chaXunMSDM,zuZhiJGID));
        //初始化数据
        tongYongSJList.forEach(item->{
            item.setZuZhiJGID(zuZhiJGID);
            item.setZuZhiJGMC(zuZhiJGMC);
        });
        yinSiPZRepository.saveAll(tongYongSJList);
        return true;
    }
    /**
     *初始化展示设置
     * @param zuZhiJGID 组织机构ID
     * @param zuZhiJGMC 组织机构名称
     * @param chaXunMSDM 查询模式代码
     * @param peiZhiLXDM 配置类型代码
     * @return true
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean chuShiHZhanShiPZ(String zuZhiJGID, String zuZhiJGMC, String chaXunMSDM, String peiZhiLXDM) {
        //通用数据
        var tongYongSJList = zhanShiPZRepository.findByZuZhiJGIDAndChaXunMSDMAndPeiZhiLXDM("0", chaXunMSDM, peiZhiLXDM);
        //删除机构之前的数据
        zhanShiPZRepository.softDelete(zhanShiPZRepository.findByZuZhiJGIDAndChaXunMSDM(zuZhiJGID, chaXunMSDM));
        //初始化数据
        tongYongSJList.forEach(item -> {
            item.setZuZhiJGID(zuZhiJGID);
            item.setZuZhiJGMC(zuZhiJGMC);
        });
        zhanShiPZRepository.saveAll(tongYongSJList);
        return true;
    }
    @Override
    public List<SC_ZD_YinSiPZOutDto> getYinSiGZPZList(String chaXunMSDM, String zuZhiJGID) throws TongYongYWException {
        var canXunMSDMList = List.of(chaXunMSDM, ChaXunMSEnum.TongYongMS.getValue());
        var list = yinSiPZRepository.findByZuZhiJGIDAndChaXunMSDMInAndQiYongBZ(zuZhiJGID,canXunMSDMList,1);
        var result = list.stream().filter(o-> Objects.equals(o.getChaXunMSDM(), chaXunMSDM)).map(o-> MapUtils.copyProperties(o,SC_ZD_YinSiPZOutDto::new)).toList();
        if(CollectionUtils.isEmpty(result)){
             result = list.stream().filter(o-> Objects.equals(o.getChaXunMSDM(), ChaXunMSEnum.TongYongMS.getValue())).map(o-> MapUtils.copyProperties(o,SC_ZD_YinSiPZOutDto::new)).toList();
        }
        return result;
    }
}

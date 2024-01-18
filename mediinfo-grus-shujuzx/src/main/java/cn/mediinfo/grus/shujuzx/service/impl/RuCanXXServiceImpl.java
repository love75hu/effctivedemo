package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.core.util.MapUtil;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.*;
import cn.mediinfo.grus.shujuzx.model.SC_BH_RuCanXXModel;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_RuCanXXRepository;
import cn.mediinfo.grus.shujuzx.service.RuCanXXService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 闭环入参信息服务
 */
@Service
public class RuCanXXServiceImpl implements RuCanXXService {
    private final SC_BH_RuCanXXRepository ruCanXXRepository;

    public RuCanXXServiceImpl(SC_BH_RuCanXXRepository ruCanXXRepository) {
        this.ruCanXXRepository = ruCanXXRepository;
    }

    @Override
    public List<RuCanXXDto> getRuCanXXByBHID(String biHuanID,String zuZhiJGID)
    {
        return BeanUtil.copyListProperties(ruCanXXRepository.findByBiHuanIDAndZuZhiJGID(biHuanID,zuZhiJGID),RuCanXXDto::new);
    }
    @Override
    public List<SC_BH_RuCanXXModel> getRuCanXX(String biHuanID,String zuZhiJGID)
    {
        return ruCanXXRepository.findByBiHuanIDAndZuZhiJGID(biHuanID,zuZhiJGID);
    }

    @Override
    public long delectRuCanXX(String biHuanID,String zuZhiJGID)
    {
        return ruCanXXRepository.deleteByBiHuanIDAndZuZhiJGID(biHuanID,zuZhiJGID);
    }


    @Override
    public  List<BiHuanSTRCZDDto> getBiHuanSTRCZD(String biHuanID, String jiGouID) {

        List<SC_BH_RuCanXXModel> byBiHuanIDAndZuZhiJGID = ruCanXXRepository.findByBiHuanIDAndZuZhiJGID(biHuanID, jiGouID);

        List<String> shiTuIDs = byBiHuanIDAndZuZhiJGID.stream().map(n -> n.getShiTuID()).distinct().collect(Collectors.toList());

        List<BiHuanSTRCZDDto> biHuanSTRCZDDtos=new ArrayList<>();

        shiTuIDs.forEach(n->{
            SC_BH_RuCanXXModel scBhRuCanXXModel = byBiHuanIDAndZuZhiJGID.stream().filter(f -> f.getShiTuID().equals(n)).findFirst().orElse(new SC_BH_RuCanXXModel());
            BiHuanSTRCZDDto biHuanSTRCZDDto=new BiHuanSTRCZDDto();
            biHuanSTRCZDDto.setShiTuMC(scBhRuCanXXModel.getShiTuMC());
            biHuanSTRCZDDto.setShiTuID(scBhRuCanXXModel.getShiTuID());
            biHuanSTRCZDDto.setChildren(BeanUtil.copyListProperties(byBiHuanIDAndZuZhiJGID.stream().filter(j->j.getShiTuID().equals(n)).collect(Collectors.toList()),BiHuanZDXXDto::new));
            biHuanSTRCZDDtos.add(biHuanSTRCZDDto);
        });
        return biHuanSTRCZDDtos;

    }



    /**
     * 新增闭环入参信息
     */
    public Boolean addRuCanXX(List<AddRuCanXXDto> dto,
                              String zuZhiJGID,
                              String zuZhiJGMC,
                              String biHuanLXDM,
                              String biHuanLXMC,
                              String biHuanID,
                              String biHuanMC)
    {
        // 根据给定的biHuanID获取现有记录。
        List<SC_BH_RuCanXXModel> existingRecords = ruCanXXRepository.findByBiHuanIDAndZuZhiJGID(biHuanID,zuZhiJGID);
        // 将DTO映射到模型。
        List<SC_BH_RuCanXXModel> newModels = BeanUtil.copyListProperties(dto, SC_BH_RuCanXXModel::new, (d, s) -> {
            s.setZuZhiJGID(zuZhiJGID);
            s.setZuZhiJGMC(zuZhiJGMC);
            s.setBiHuanID(biHuanID);
            s.setBiHuanMC(biHuanMC);
            s.setBiHuanLXDM(biHuanLXDM);
            s.setBiHuanLXMC(biHuanLXMC);
        });
        // 创建新模型的唯一标识符集合。
        Set<String> newModelIdentifiers = newModels.stream()
                .map(model -> model.getShiTuID() + "_" + model.getZiDuanBM()) // 组合成唯一标识符
                .collect(Collectors.toSet());
        // 确定需要删除的记录。
        List<SC_BH_RuCanXXModel> modelsToDelete = existingRecords.stream()
                .filter(existing -> !newModelIdentifiers.contains(existing.getShiTuID() + "_" + existing.getZiDuanBM()))
                .toList();
        // 执行删除操作。
        if (!modelsToDelete.isEmpty()) {
            ruCanXXRepository.deleteAll(modelsToDelete);
        }
        // 确定更新和插入。
        List<SC_BH_RuCanXXModel> modelsToUpdate = new ArrayList<>();
        List<SC_BH_RuCanXXModel> modelsToInsert = new ArrayList<>();
        for (SC_BH_RuCanXXModel newModel : newModels) {
            SC_BH_RuCanXXModel existing = existingRecords.stream()
                    .filter(e -> (e.getShiTuID() + "_" + e.getZiDuanBM()).equals(newModel.getShiTuID() + "_" + newModel.getZiDuanBM()))
                    .findFirst()
                    .orElse(null);
            if (existing != null) {
                // 准备现有记录进行更新。
                newModel.setId(existing.getId());
                newModel.setZuZhiJGID(zuZhiJGID);
                newModel.setZuZhiJGMC(zuZhiJGMC);
                newModel.setBiHuanID(biHuanID);
                newModel.setBiHuanMC(biHuanMC);
                newModel.setBiHuanLXDM(biHuanLXDM);
                newModel.setBiHuanLXMC(biHuanLXMC);
                modelsToUpdate.add(existing);
            } else {
                // 准备插入新记录。
                modelsToInsert.add(newModel);
            }
        }
        // 执行批量更新和插入操作。
        if (!modelsToUpdate.isEmpty()) {
            ruCanXXRepository.saveAll(modelsToUpdate);
        }
        if (!modelsToInsert.isEmpty()) {
            ruCanXXRepository.saveAll(modelsToInsert);
        }
        return true;
    }
    public Boolean addFuZhiRCXX(List<SC_BH_RuCanXXModel> canXXModels)
    {
     ruCanXXRepository.insertAll(canXXModels);
     return true;
    }

    @Override
    public List<SC_BH_RuCanXXModel> getRuCanXXByBHID(List<String> biHuanID) {
        return ruCanXXRepository.findByBiHuanIDIn(biHuanID);
    }

}
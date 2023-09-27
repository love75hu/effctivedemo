package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs.ZhiBiaoMXListDto;
import cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs.ZhiBiaoXXCreateDto;
import cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs.ZhiBiaoXXListDto;
import cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs.ZhiBiaoXXUpdateDto;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ZhiBiaoXXModel;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_ZhiBiaoXXRepository;
import cn.mediinfo.grus.shujuzx.service.ZhiBiaoXXService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZhiBiaoXXServiceImpl implements ZhiBiaoXXService {
    private final SC_CX_ZhiBiaoXXRepository zhiBiaoXXRepository;

    public ZhiBiaoXXServiceImpl(SC_CX_ZhiBiaoXXRepository zhiBiaoXXRepository) {
        this.zhiBiaoXXRepository = zhiBiaoXXRepository;
    }

    /**
     * 根据指标类型代码获取指标信息列表
     */
    @Override
    public List<ZhiBiaoXXListDto> getZhiBiaoXXListByZBLXDM(String zhiBiaoLXDM, String likeQuery){
        List<SC_CX_ZhiBiaoXXModel> zhibiaoxxlist = zhiBiaoXXRepository.getZhiBiaoXXByZBLXDM(zhiBiaoLXDM,likeQuery);
        return zhibiaoxxlist.stream().
                collect(Collectors.groupingBy(p-> new AbstractMap.SimpleEntry<>(p.getZhiBiaoFLID(),p.getZhiBiaoFLMC())))
                .entrySet().stream()
                .map(p -> {
                    ZhiBiaoXXListDto result = new ZhiBiaoXXListDto();
                    result.setZhiBiaoFLID(p.getKey().getKey());
                    result.setZhiBiaoFLMC(p.getKey().getValue());
                    result.setZhiBiaoMXList(BeanUtil.copyListProperties(p.getValue(),ZhiBiaoMXListDto::new)
                            .stream().sorted(Comparator.comparing(ZhiBiaoMXListDto::getShunXuHao)).toList());
                    return result;
                }).toList();
    }
    /**
     * 根据id移除单个指标
     */
    @Override
    public Boolean zuoFeiZhiBiaoByID(String id){
        zhiBiaoXXRepository.deleteById(id);
        return true;
    }
    /**
     * 根据指标类型代码和指标分类id移除该分类所有指标
     */
    @Override
    public Boolean zuoFeiZhiBiaoFL(String zhiBiaoLXDM,String zhiBiaoFLID){
        zhiBiaoXXRepository.asDeleteDsl().where(p -> p.zhiBiaoLXDM.eq(zhiBiaoLXDM).and(p.zhiBiaoFLID.eq(zhiBiaoFLID))).execute();
        return true;
    }
    /**
     * 添加指标集合
     */
    @Override
    public Boolean addZhiBiaoList(List<ZhiBiaoXXCreateDto> createDtos){
        List<SC_CX_ZhiBiaoXXModel> addList = BeanUtil.copyListProperties(createDtos,SC_CX_ZhiBiaoXXModel::new,(dto,model) -> {
            model.setZuZhiJGID(ShuJuZXConstant.TONGYONG_JGID);
            model.setZuZhiJGMC(ShuJuZXConstant.TONGYONG_JGMC);
        });
        zhiBiaoXXRepository.insertAll(addList);
        return true;
    }
    /**
     * 新增指标信息
     */
    @Override
    public Boolean addZhiBiaoXX(ZhiBiaoXXCreateDto createDto){
        SC_CX_ZhiBiaoXXModel addModel = BeanUtil.copyProperties(createDto,SC_CX_ZhiBiaoXXModel::new,(dto,model) -> {
            model.setZuZhiJGID(ShuJuZXConstant.TONGYONG_JGID);
            model.setZuZhiJGMC(ShuJuZXConstant.TONGYONG_JGMC);
        });
        zhiBiaoXXRepository.insert(addModel);
        return true;
    }
    /**
     * 修改指标信息
     */
    @Override
    public Boolean updateZhiBiaoXX(ZhiBiaoXXUpdateDto updateDto){
        zhiBiaoXXRepository.asUpdateDsl()
                .set(p -> p.zhiBiaoFLMC,updateDto.getZhiBiaoFLMC())
                .set(p -> p.shunXuHao,updateDto.getShunXuHao())
                .where(p -> p.id.eq(updateDto.getId())).execute();
        return true;
    }
}

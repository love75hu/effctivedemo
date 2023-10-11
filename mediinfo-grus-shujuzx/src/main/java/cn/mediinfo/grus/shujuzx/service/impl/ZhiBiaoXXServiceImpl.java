package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.core.util.CollectorUtil;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs.ZhiBiaoMXListDto;
import cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs.ZhiBiaoXXCreateDto;
import cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs.ZhiBiaoXXListDto;
import cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs.ZhiBiaoXXUpdateDto;
import cn.mediinfo.grus.shujuzx.enums.ZhiBiaoLXDMEnum;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ZhiBiaoXXModel;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_ZhiBiaoXXRepository;
import cn.mediinfo.grus.shujuzx.service.ZhiBiaoXXService;
import cn.mediinfo.lyra.extension.service.SequenceService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ZhiBiaoXXServiceImpl implements ZhiBiaoXXService {
    private final SC_CX_ZhiBiaoXXRepository zhiBiaoXXRepository;
    private final SequenceService sequenceService;

    public ZhiBiaoXXServiceImpl(SC_CX_ZhiBiaoXXRepository zhiBiaoXXRepository,
                                SequenceService sequenceService) {
        this.zhiBiaoXXRepository = zhiBiaoXXRepository;
        this.sequenceService = sequenceService;
    }

    /**
     * 根据指标类型代码获取指标信息列表
     */
    @Override
    public List<ZhiBiaoXXListDto> getZhiBiaoXXListByZBLXDM(String zhiBiaoLXDM, String likeQuery) {
        List<ZhiBiaoXXListDto> result = new ArrayList<>();
        List<SC_CX_ZhiBiaoXXModel> zhiBiaoXXList = zhiBiaoXXRepository.getZhiBiaoXXByZBLXDM(zhiBiaoLXDM, likeQuery);
        if (zhiBiaoLXDM.equals(ZhiBiaoLXDMEnum.DRUG.getZhiBiaoLXDM())) {
            List<SC_CX_ZhiBiaoXXModel> fenLeiList = zhiBiaoXXList.stream().filter(p -> StringUtil.notHasText(p.getZhiBiaoID())).
                    sorted(Comparator.comparing(SC_CX_ZhiBiaoXXModel::getShunXuHao)).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(fenLeiList)) {
                List<String> zhiBiaoFLIDs = zhiBiaoXXList.stream().map(SC_CX_ZhiBiaoXXModel::getZhiBiaoFLID).distinct().toList();
                List<SC_CX_ZhiBiaoXXModel> zhiBiaoFLList= zhiBiaoXXRepository.findByZhiBiaoLXDMAndZhiBiaoFLIDInAndZhiBiaoIDNotNull(zhiBiaoLXDM,zhiBiaoFLIDs);
                fenLeiList.addAll(zhiBiaoFLList);
            }
            for (var item : fenLeiList) {
                ZhiBiaoXXListDto dto = new ZhiBiaoXXListDto();
                dto.setId(item.getId());
                dto.setZhiBiaoFLID(item.getZhiBiaoFLID());
                dto.setZhiBiaoFLMC(item.getZhiBiaoFLMC());
                dto.setShunXuHao(item.getShunXuHao());
                dto.setZhiBiaoMXList(BeanUtil.copyListProperties(zhiBiaoXXList.stream().filter(p -> StringUtil.hasText(p.getZhiBiaoID())
                                && p.getZhiBiaoFLID().equals(item.getZhiBiaoFLID()))
                        .sorted(Comparator.comparing(SC_CX_ZhiBiaoXXModel::getShunXuHao, Comparator.nullsLast(Integer::compareTo)))
                        .toList(), ZhiBiaoMXListDto::new));
                result.add(dto);
            }
        } else {
            result = zhiBiaoXXList.stream().
                    collect(Collectors.groupingBy(p -> new AbstractMap.SimpleEntry<>(p.getZhiBiaoFLID(), p.getZhiBiaoFLMC())))
                    .entrySet().stream()
                    .map(p -> {
                        ZhiBiaoXXListDto dto = new ZhiBiaoXXListDto();
                        dto.setZhiBiaoFLID(p.getKey().getKey());
                        dto.setZhiBiaoFLMC(p.getKey().getValue());
                        dto.setZhiBiaoMXList(BeanUtil.copyListProperties(p.getValue().stream()
                                        .filter(value -> StringUtil.hasText(value.getZhiBiaoID())).toList(), ZhiBiaoMXListDto::new)
                                .stream().sorted(Comparator.comparing(ZhiBiaoMXListDto::getShunXuHao, Comparator.nullsLast(Integer::compareTo))).toList());
                        return dto;
                    }).toList();
        }
        return result;
    }

    /**
     * 根据id移除单个指标
     */
    @Override
    public Boolean zuoFeiZhiBiaoByID(String id) {
        zhiBiaoXXRepository.deleteById(id);
        return true;
    }

    /**
     * 根据指标类型代码和指标分类id移除该分类所有指标
     */
    @Override
    public Boolean zuoFeiZhiBiaoFL(String zhiBiaoLXDM, String zhiBiaoFLID) {
        zhiBiaoXXRepository.asDeleteDsl().where(p -> p.zhiBiaoLXDM.eq(zhiBiaoLXDM).and(p.zhiBiaoFLID.eq(zhiBiaoFLID))).execute();
        return true;
    }

    /**
     * 添加指标集合
     */
    @Override
    public Boolean addZhiBiaoList(List<ZhiBiaoXXCreateDto> createDtos) throws TongYongYWException {
        var feiLeiList = createDtos.stream()
                .collect(Collectors.groupingBy(ZhiBiaoXXCreateDto::getZhiBiaoLXDM))
                .entrySet();
        for (var item : feiLeiList) {
            checkZhiBiaoID(item.getKey(), item.getValue().stream().map(ZhiBiaoXXCreateDto::getZhiBiaoID).toList());
        }
        List<SC_CX_ZhiBiaoXXModel> addList = BeanUtil.copyListProperties(createDtos, SC_CX_ZhiBiaoXXModel::new, (dto, model) -> {
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
    public Boolean addZhiBiaoXX(ZhiBiaoXXCreateDto createDto) throws TongYongYWException {
        String zhiBiaoFLID;
        if (StringUtil.notHasText(createDto.getZhiBiaoID()) && createDto.getZhiBiaoLXDM().equals(ZhiBiaoLXDMEnum.DRUG.getZhiBiaoLXDM())) {
            zhiBiaoFLID = sequenceService.getXuHao("SC_CX_ZhiBiaoXX_ZhiBiaoFLID", 7);
            boolean existZhiBiaoMC = zhiBiaoXXRepository.existsZhiBiaoFL(createDto.getZhiBiaoLXDM(), zhiBiaoFLID, createDto.getZhiBiaoFLMC());
            if (existZhiBiaoMC) {
                throw new TongYongYWException("指标分类名称或分类ID已存在，请重新确认! ");
            }
        } else {
            zhiBiaoFLID = "";
        }
        SC_CX_ZhiBiaoXXModel addModel = BeanUtil.copyProperties(createDto, SC_CX_ZhiBiaoXXModel::new, (dto, model) -> {
            model.setZuZhiJGID(ShuJuZXConstant.TONGYONG_JGID);
            model.setZuZhiJGMC(ShuJuZXConstant.TONGYONG_JGMC);
            if (StringUtil.hasText(zhiBiaoFLID)) {
                model.setZhiBiaoFLID(zhiBiaoFLID);
            }
        });
        zhiBiaoXXRepository.insert(addModel);
        return true;
    }

    /**
     * 修改指标信息
     */
    @Override
    public Boolean updateZhiBiaoXX(ZhiBiaoXXUpdateDto updateDto) throws TongYongYWException {
        boolean existZhiBiaoMC = zhiBiaoXXRepository.existsByZhiBiaoLXDMAndZhiBiaoFLMC(ZhiBiaoLXDMEnum.DRUG.getZhiBiaoLXDM(), updateDto.getZhiBiaoFLMC());
        if (existZhiBiaoMC) {
            throw new TongYongYWException("指标分类名称已存在，请重新确认! ");
        }
        zhiBiaoXXRepository.asUpdateDsl()
                .set(p -> p.zhiBiaoFLMC, updateDto.getZhiBiaoFLMC())
                .set(p -> p.shunXuHao, updateDto.getShunXuHao())
                .where(p -> p.id.eq(updateDto.getId())).execute();
        return true;
    }

    private void checkZhiBiaoID(String zhiBiaoLXDM, List<String> zhiBiaoIDs) throws TongYongYWException {
        List<SC_CX_ZhiBiaoXXModel> zhiBiaoXXList = zhiBiaoXXRepository.findByZhiBiaoLXDMAndZhiBiaoIDIn(zhiBiaoLXDM, zhiBiaoIDs);
        if (!CollectionUtils.isEmpty(zhiBiaoXXList)) {
            String zhiBiaoXX = StringUtil.join(",", zhiBiaoXXList.stream().map(p ->
                    StringUtil.concat("[", p.getZhiBiaoID(), "]", p.getZhiBiaoMC())).toList());
            throw new TongYongYWException(StringUtil.concat("指标ID", zhiBiaoXX, "请存在，请重新确认!"));
        }
    }
}

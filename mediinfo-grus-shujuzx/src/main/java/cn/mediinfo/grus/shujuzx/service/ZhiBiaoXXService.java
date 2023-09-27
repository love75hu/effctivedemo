package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs.ZhiBiaoXXCreateDto;
import cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs.ZhiBiaoXXListDto;
import cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs.ZhiBiaoXXUpdateDto;

import java.util.List;

public interface ZhiBiaoXXService {
    /**
     * 根据指标类型代码获取指标信息列表
     */
    List<ZhiBiaoXXListDto> getZhiBiaoXXListByZBLXDM(String zhiBiaoLXDM, String likeQuery);
    /**
     * 根据id移除单个指标
     */
    Boolean zuoFeiZhiBiaoByID(String id);
    /**
     * 根据指标类型代码和指标分类id移除该分类所有指标
     */
    Boolean zuoFeiZhiBiaoFL(String zhiBiaoLXDM,String zhiBiaoFLID);
    /**
     * 添加指标集合
     */
    Boolean addZhiBiaoList(List<ZhiBiaoXXCreateDto> createDtos);
    /**
     * 新增指标信息
     */
    Boolean addZhiBiaoXX(ZhiBiaoXXCreateDto createDto);
    /**
     * 修改指标信息
     */
    Boolean updateZhiBiaoXX(ZhiBiaoXXUpdateDto updateDto);
}

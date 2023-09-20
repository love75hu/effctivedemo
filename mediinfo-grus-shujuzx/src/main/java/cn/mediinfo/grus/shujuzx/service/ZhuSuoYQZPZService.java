package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.grus.shujuzx.dto.shujuzxzsys.*;

import java.util.List;

public interface ZhuSuoYQZPZService {
    /**
     * 新增一个权重分类
     * @param  dto DTO
     * @return boolean
     */
    Boolean addQuanZhongFL(AddQuanZhongFLDto dto) throws TongYongYWException;
    /**
     * 更新一个权重分类
     * @param  dto DTO
     * @return boolean
     */
    Boolean updateQuanZhongFL(UpdateQuanZhongFLDto dto) throws TongYongYWException;
    /**
     * 作废权重分类
     * @param id id
     * @return boolean
     */
    Boolean zuoFeiQuanZhongFL(String id) throws TongYongYWException;
    /**
     * 保存权重配置
     * @param  dto DTO
     * @return boolean
     */
    Boolean saveQuanZhongPZ(SaveQuanZhongPZDto dto) throws TongYongYWException;

    /**
     * 获取权重配置分类列表
     * @return BR_ZD_HeBingQZPZListDto
     * @throws TongYongYWException 通用异常
     */
    List<BR_ZD_HeBingQZPZListDto> getQuanZhongPZFLList() throws TongYongYWException;
    /**
     * 获取权重配置列表(包含分类)
     * @return BR_ZD_HeBingGZQZPZDto
     * @throws TongYongYWException 通用异常
     */
    List<BR_ZD_HeBingGZQZPZDto> getQuanZhongPZList() throws TongYongYWException;

    /**
     * 根据分类ID获取权重配置列表
     * @return List<BR_ZD_HeBingQZPZListDto></BR_ZD_HeBingQZPZListDto>
     * @throws TongYongYWException 通用异常
     */
    List<BR_ZD_HeBingQZPZListDto> getQuanZhongPZListByFLID(String FuLeiID,String LikeQuery) throws  TongYongYWException;
}

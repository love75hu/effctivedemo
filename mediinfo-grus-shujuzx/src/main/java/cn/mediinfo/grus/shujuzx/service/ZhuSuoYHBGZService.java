package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs.BR_ZD_HeBingGZCreateDto;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs.BR_ZD_HeBingGZListDto;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs.BR_ZD_HeBingGZUpdateDto;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs.HeBingGZMXDto;
import cn.mediinfo.starter.base.exception.MsfResponseException;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.response.MsfResponse;

import java.util.List;

public interface ZhuSuoYHBGZService {
    /**
     * 新增规则信息
     * @param  dto DTO
     * @return boolean
     */
    String addGuiZeXX(BR_ZD_HeBingGZCreateDto dto) throws TongYongYWException;
    /**
     * 修改规则信息
     * @param  heBingGZUpdateDto DTO
     * @return boolean
     */
    String updateGuiZeXX(BR_ZD_HeBingGZUpdateDto heBingGZUpdateDto) throws TongYongYWException;
    /**
     * 作废规则
     * @param id id
     * @return boolean
     */
    Integer zuoFeiGuiZeXX(String id) throws TongYongYWException;
    /**
     * 获取规则列表
     * @return 规则DTO
     * @throws TongYongYWException 返回异常
     */
    List<BR_ZD_HeBingGZListDto> getGuiZeList() throws TongYongYWException;

    /**
     * 根据规则ID获取规则信息和规则明细内容
     * @return 规则DTO
     * @throws TongYongYWException 返回异常
     */
    HeBingGZMXDto getGuiZeXXByID(String guiZeID) throws TongYongYWException;
}

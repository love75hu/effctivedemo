package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.grus.shujuzx.dto.wendangjls.SC_GW_JiLuXXSCDto;

public interface WenDangJLXXService  {
    /**
     * 保存共享文档
     * @param dto
     * @return
     * @throws TongYongYWException
     */
    boolean addWenDangJLXX(SC_GW_JiLuXXSCDto dto) throws TongYongYWException;
}

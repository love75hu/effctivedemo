package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.SC_CX_ShiTuMXGXDto;

public interface ShiTuMXGXService {
    /**
     * 根据ID获取视图字段关系     *     * @return
     */
    SC_CX_ShiTuMXGXDto getShiTuMXGXByID(String id) throws MsfResponseException, WeiZhaoDSJException;

    Boolean delectShiTuMXGX(String id);

}
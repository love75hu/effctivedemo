package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_JIBENXXDto;

public interface JiBenXXService {
    /**
     * 根据ID获取闭环信息     *     * @return
     */
    SC_BH_JIBENXXDto getJIBENXXByID(String id) throws MsfResponseException, WeiZhaoDSJException;
}
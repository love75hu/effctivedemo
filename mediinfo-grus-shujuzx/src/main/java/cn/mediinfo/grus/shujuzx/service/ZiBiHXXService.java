package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_ZiBiHXXDto;

public interface ZiBiHXXService {
    /**
     * 根据ID获取子闭环信息     *     * @return
     */
    SC_BH_ZiBiHXXDto getZiBiHXXByID(String id) throws MsfResponseException, WeiZhaoDSJException;
}
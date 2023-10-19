package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_ZiBiHXSLDto;

public interface ZiBiHXSLService {
    /**
     * 根据ID获取子闭环显示列     *     * @return
     */
    SC_BH_ZiBiHXSLDto getZiBiHXSLByID(String id) throws MsfResponseException, WeiZhaoDSJException;
}
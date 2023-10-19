package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_JieDianSXDto;

public interface JieDianSXService {
    /**
     * 根据ID获取闭环节点时效     *     * @return
     */
    SC_BH_JieDianSXDto getJieDianSXByID(String id) throws MsfResponseException, WeiZhaoDSJException;
}
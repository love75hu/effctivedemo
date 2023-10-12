package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuJDMXDto;

public interface BiHuanSTJDMXService {
    SC_BH_ShiTuJDMXDto getShiTuJDMXById(String id) throws WeiZhaoDSJException;
}

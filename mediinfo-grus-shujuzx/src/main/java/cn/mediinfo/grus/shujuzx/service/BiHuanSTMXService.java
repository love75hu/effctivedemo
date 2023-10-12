package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuMXDto;

public interface BiHuanSTMXService {
    SC_BH_ShiTuMXDto getShiTuMXById(String id) throws WeiZhaoDSJException;
}


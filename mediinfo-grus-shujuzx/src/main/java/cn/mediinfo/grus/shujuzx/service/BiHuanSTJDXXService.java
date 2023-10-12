package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuJDXXDto;

public interface BiHuanSTJDXXService {
    SC_BH_ShiTuJDXXDto getShiTuJDXX(String id) throws WeiZhaoDSJException;
}

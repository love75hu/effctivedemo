package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuJDGXDto;

public interface BiHuanSTJDGXService {
    SC_BH_ShiTuJDGXDto getShiTuJDGXByID(String id) throws WeiZhaoDSJException;
}

package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.GuanLianJDDto;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuJDGXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuJDMXDto;

import java.util.List;

public interface BiHuanSTJDGXService {
    SC_BH_ShiTuJDGXDto getShiTuJDGXByID(String id) throws WeiZhaoDSJException;

    List<GuanLianJDDto> getGuanLianJDXX(String shiTuID);
    Boolean addGuanLianJDXX(List<GuanLianJDDto> guanLianJDDtos,String shiTuID,
                            String shiTuMC,String jieDianID,String jieDianMC);
    List<SC_BH_ShiTuJDGXDto> getGuanLianJDXXs(List<String> jieDianID);
}

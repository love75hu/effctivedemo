package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.JieDianNRDto;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuJDMXDto;

import java.util.List;

public interface BiHuanSTJDMXService {
    SC_BH_ShiTuJDMXDto getShiTuJDMXById(String id) throws WeiZhaoDSJException;
    List<JieDianNRDto> getShiTuJDMX(String shiTuID);
    Boolean addShiTuJDMX(List<JieDianNRDto> jieDianNRDtos,String jieDianID,String jieDianMC,String shiTuID,String shiTuMC);
    List<SC_BH_ShiTuJDMXDto> getShiTuJDMXs(List<String> jieDianID);

    List<JieDianNRDto> getShiTuJDMXByJieDianID(String jieDianID);
}

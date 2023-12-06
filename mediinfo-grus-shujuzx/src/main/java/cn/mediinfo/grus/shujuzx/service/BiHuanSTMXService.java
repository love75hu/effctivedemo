package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.*;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuMXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.BiHuanSTRCZDDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BiHuanSTMXService {
    SC_BH_ShiTuMXDto getShiTuMXById(String id) throws WeiZhaoDSJException;
    List<BiHuanSTZDDto> getBiHuanSTZD(  String shiTuID,String ziDuanMC, String biHuanLXDM, Integer chaXunLXDM, Integer pageIndex, Integer pageSize);
    Integer getBiHuanSTZDCount( String shiTuID, String ziDuanMC, String biHuanLXDM, Integer chaXunLXDM);
    Boolean delectBiHuanSTZDByID(String id);
    Boolean delectBiHuanSTZDByShiTuID(String shiTuID);
    Boolean addBiHuanSTJDMX(List<AddBiHuanSTJDMXDto> dto);

    Boolean saveBiHuanSTJDMX(SaveBiHuanSTJDMXDto dto) throws WeiZhaoDSJException;

    List<KeXuanZDDto> getShiTUZDXX(String shiTUID);

    List<BiHuanSTRCZDDto> getRuChanZDXX(String biHuanLXDM);

    List<BiHuanSTSJXXDto>  getShiJianXX(String shiTuID) throws WeiZhaoDSJException, YuanChengException;

    List<BiHuanSTRCZDDto>  getBiHuanSTZDBybiHuanLXDM(String biHuanLXDM);



}


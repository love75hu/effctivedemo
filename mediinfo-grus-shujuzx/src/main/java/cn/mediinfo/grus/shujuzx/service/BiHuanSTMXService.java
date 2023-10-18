package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.AddBiHuanSTJDMXDto;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.BiHuanSTZDDto;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.KeXuanZDDto;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.SaveBiHuanSTJDMXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuMXDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BiHuanSTMXService {
    SC_BH_ShiTuMXDto getShiTuMXById(String id) throws WeiZhaoDSJException;
    List<BiHuanSTZDDto> getBiHuanSTZD(String ziDuanMC, String biHuanLXDM, Integer chaXunLXDM, Integer pageIndex, Integer pageSize);
    Integer getBiHuanSTZDCount(String ziDuanMC, String biHuanLXDM, Integer chaXunLXDM);
    Boolean delectBiHuanSTZDByID(String id);
    Boolean delectBiHuanSTZDByShiTuID(String shiTuID);
    Boolean addBiHuanSTJDMX(List<AddBiHuanSTJDMXDto> dto);

    Boolean saveBiHuanSTJDMX(SaveBiHuanSTJDMXDto dto) throws WeiZhaoDSJException;

    List<KeXuanZDDto> getShiTUZDXX(String shiTUID);

}


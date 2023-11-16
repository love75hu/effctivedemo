package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.BiHuanJDXXListDto;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.BiHuanSTJDDto;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.BiHuanSTJDXXDto;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.GuanLianJDDto;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuJDXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.AddBiHuanSZXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.KeXuanJDDto;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public interface BiHuanSTJDXXService {
    SC_BH_ShiTuJDXXDto getShiTuJDXX(String id) throws WeiZhaoDSJException;

    BiHuanSTJDDto getBiHuanSTJDByID(String id) throws WeiZhaoDSJException;

    Boolean addBiHuanSTJD(BiHuanSTJDDto dto) throws WeiZhaoDSJException;

    List<GuanLianJDDto> getGuanLianJDXX(String shiTuID,String jieDianID);

    List<BiHuanJDXXListDto> getBiHuanJDXXList(String shiTuID,
                                              String biHuanLXDM,
                                              String jieDianMC,
                                              Integer qiYongBZ,
                                              Integer pageIndex,
                                              Integer pageSize);
    Integer  getBiHuanJDXXCount(String shiTuID,
                                String biHuanLXDM,
                                String jieDianMC,
                                Integer qiYongBZ);

    List<KeXuanJDDto> getKeXuanJDBybiHuanLXDM(String biHuanLXDM);

    Boolean updateJieDianQYBZ(String id,Integer qiYongBZ);

    Boolean updateBiHuanSTJD(BiHuanSTJDDto dto) throws WeiZhaoDSJException;

    List<BiHuanSTJDXXDto> getBiHuanSTJD(String biHuanLXDM);



}

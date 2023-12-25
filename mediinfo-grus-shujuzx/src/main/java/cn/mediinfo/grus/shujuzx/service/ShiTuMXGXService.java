package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.grus.shujuzx.dto.shitumx.ShuJuJMXZDDto;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.GuanLianTJZD;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.SC_CX_ShiTuMXGXDto;

import java.util.List;

public interface ShiTuMXGXService {
    /**
     * 根据ID获取视图字段关系     *     * @return
     */
    SC_CX_ShiTuMXGXDto getShiTuMXGXByID(String id) throws MsfResponseException, WeiZhaoDSJException;

    List<GuanLianTJZD> getShiTuMXGXList(String shiTuID,String ziDuanBM,String ziDuanMC);

    Boolean delectShiTuMXGX(String id,String ziDuanBM);
    List<cn.mediinfo.grus.shujuzx.dto.shitumx.SC_CX_ShiTuMXGXDto> getShiTuMXGXByShiTuID(List<String> shiTuID);

    Boolean addShiTuMXGX(String shiTuID,String ziDuanBM,String ziDuanMC, List<GuanLianTJZD> guanLianZDList);

    List<ShuJuJMXZDDto> getShiTuMXGXBySTIDs(List<String> shiTuIDs);

}
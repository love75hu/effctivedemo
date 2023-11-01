package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.AddBiHuanSZXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.BiHuanSZXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_JieDianXXDto;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public interface JieDianXXService {
    /**
     * 根据ID获取闭环节点信息     *     * @return
     */
    SC_BH_JieDianXXDto getJieDianXXByID(String id) throws MsfResponseException, WeiZhaoDSJException;

    String addBiHuanSZXX(AddBiHuanSZXXDto dto);

    List<BiHuanSZXXDto>  getBiHuanSZXXBybiHuanID( String biHuanID);
}
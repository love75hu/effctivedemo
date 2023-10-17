package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.BiHuanSTXXDto;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.BiHuanSTXXTree;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuXXDto;

import java.util.List;

public interface BIHuanSTXXService {
    /**
     * 根据ID获取闭环视图信息     *     * @return
     */
    SC_BH_ShiTuXXDto getShiTuXXByID(String id) throws MsfResponseException, WeiZhaoDSJException;

    List<BiHuanSTXXTree> getBiHuanSTXXTree(String likeQuery);

    Boolean addBiHuanSTXX(BiHuanSTXXDto dto);
}
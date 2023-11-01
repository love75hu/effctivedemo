package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.AddBiHuanXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.BiHuanJBXXTreeDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.BiHuanSZXFDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_JIBENXXDto;

import java.util.List;

public interface JiBenXXService {
    /**
     * 根据ID获取闭环信息     *     * @return
     */
    SC_BH_JIBENXXDto getJIBENXXByID(String id) throws MsfResponseException, WeiZhaoDSJException;

    /**
     * 闭环设置左侧树
     */
    List<BiHuanJBXXTreeDto> getBiHuanJBXXTree(String zuZhiJGID, String likeQuery );

    Boolean addBiHuanJBXX(AddBiHuanXXDto dto);

    /**
     * 闭环设置下发
     */
    Boolean biHuanSZXF(BiHuanSZXFDto dto) throws TongYongYWException;
    /**
     * 闭环设置删除
     */
    String biHuanSZFZ( String biHuanID);

    /**
     * 闭环设置删除
     */
    Boolean biHuanSZSC(String biHuanID);

    /**
     * 闭环设置启用
     */
    Boolean biHuanSZQY(String biHuanID);
}
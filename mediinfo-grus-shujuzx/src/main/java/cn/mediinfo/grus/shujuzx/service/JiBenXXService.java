package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.*;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.*;

import java.util.List;

public interface JiBenXXService {
    /**
     * 根据ID获取闭环信息     *     * @return
     */
    SC_BH_JiBenXXDto getJIBENXXByID(String id) throws MsfResponseException, WeiZhaoDSJException;

    /**
     * 闭环设置左侧树
     */
    List<BiHuanJBXXTreeDto> getBiHuanJBXXTree(String zuZhiJGID, String likeQuery );

    String addBiHuanJBXX(AddBiHuanXXDto dto) throws WeiZhaoDSJException;

    /**
     * 闭环设置下发
     */
    boolean biHuanSZXF(BiHuanSZXFDto dto) throws  YuanChengException, CanShuException;
    /**
     * 闭环设置复制
     */
    String biHuanSZFZ(String biHuanID, String zuZhiJGID, String zuZhiJGMC);

    /**
     * 闭环设置删除
     */
    boolean biHuanSZSC(String id);

    /**
     * 闭环设置启用
     */
    boolean biHuanSZQY(String id, Integer qiyongBZ);

    BiHuanXXDto getBiHuanXXBYID(String id);
    /**
     * 获取闭环基本信息列表
     * @param likeQuery
     * @return
     */
    List<SC_BH_JiBenXXDto> getBiHuanJBXXList(String likeQuery);
}
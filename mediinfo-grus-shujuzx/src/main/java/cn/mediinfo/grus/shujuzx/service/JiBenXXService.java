package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.*;

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

    boolean addBiHuanJBXX(AddBiHuanXXDto dto) throws WeiZhaoDSJException;

    /**
     * 闭环设置下发
     */
    boolean biHuanSZXF(BiHuanSZXFDto dto) throws TongYongYWException, YuanChengException;
    /**
     * 闭环设置复制
     */
    String biHuanSZFZ(String biHuanID, String zuZhiJGID, String zuZhiJGMC);

    /**
     * 闭环设置删除
     */
    boolean biHuanSZSC(String biHuanID);

    /**
     * 闭环设置启用
     */
    boolean biHuanSZQY(String biHuanID,Integer qiyongBZ);

    BiHuanXXDto getBiHuanXXBYID(String biHuanID);
    /**
     * 获取闭环基本信息列表
     * @param likeQuery
     * @return
     */
    List<SC_BH_JIBENXXDto> getBiHuanJBXXList(String likeQuery);
}
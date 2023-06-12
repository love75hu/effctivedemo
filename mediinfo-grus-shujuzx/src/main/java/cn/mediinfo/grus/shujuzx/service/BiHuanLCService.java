package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.BiHuanLCs.SC_ZD_BiHuanLCBJInDto;
import cn.mediinfo.grus.shujuzx.dto.BiHuanLCs.SC_ZD_BiHuanLCInDto;
import cn.mediinfo.grus.shujuzx.dto.BiHuanLCs.SC_ZD_BiHuanLCOutDto;
import cn.mediinfo.starter.base.exception.MsfException;

import java.util.List;

public interface BiHuanLCService {
    /**
     * 根据主键查询一条闭环流程信息
     */
    public List<SC_ZD_BiHuanLCOutDto> getBiHuanLCByID(String id,String zuZhiJGID);
    /**
     * 根据组织机构、闭环类型获取闭环流程信息
     */
    public SC_ZD_BiHuanLCOutDto getBiHuanLCForLX(String zuZhiJGID,String biHuanLXDM,Integer menZhenSYBZ,Integer zhuYuanSYBZ,Integer jiZhenSYBZ,Integer tiJianSYBZ);
    /**
     * 新增一个闭环流程
     */
    public Integer addBiHuanLC(SC_ZD_BiHuanLCInDto biHuanLCDto) throws MsfException;

    /**
     *根据闭环类型代码获取闭环节点列表
     */
    public List<SC_ZD_BiHuanLCOutDto> getBiHuanLCList(String zuZhiJGID, String biHuanLXDM, String likeQuery);
    /**
     * 启用闭环流程
     */
    public Integer qiYongBiHuanLC(String id, String zuZhiJGID) throws MsfException;
    /**
     * 停用闭环流程
     */
    public Integer tingYongBiHuanLC(String id) throws MsfException;
    /**
     * 编辑一条闭环流程
     */
    public Integer updateBiHuanLC(SC_ZD_BiHuanLCBJInDto biHuanLCBJInDto) throws MsfException;
}

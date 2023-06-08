package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.BiHuanLCs.SC_ZD_BiHuanLCOutDto;

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
}

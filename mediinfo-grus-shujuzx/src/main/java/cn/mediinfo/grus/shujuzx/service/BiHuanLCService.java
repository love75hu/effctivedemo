package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.BiHuanLCs.SC_ZD_BiHuanLCOutDto;

import java.util.List;

public interface BiHuanLCService {
    /**
     * 根据主键查询一条闭环流程信息
     */
    public List<SC_ZD_BiHuanLCOutDto> GetBiHuanLCByID(String id,String zuZhiJGID);
}

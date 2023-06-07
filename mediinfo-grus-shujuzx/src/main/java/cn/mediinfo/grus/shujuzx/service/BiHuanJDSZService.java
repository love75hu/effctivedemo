package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.BiHuanJDSZs.SC_ZD_BiHuanJDCreateDto;
import cn.mediinfo.grus.shujuzx.dto.BiHuanJDSZs.SC_ZD_BiHuanJDDto;
import cn.mediinfo.starter.base.exception.TongYongYWException;

public interface BiHuanJDSZService {
    Integer GetBiHuanJDCount(String biHuanLXDM, String likeQuery);

    /**
     * 新增闭环节点
     *
     * @param createDto
     * @return
     * @throws TongYongYWException
     */
    SC_ZD_BiHuanJDDto addBiHuanJD(SC_ZD_BiHuanJDCreateDto createDto) throws TongYongYWException;

}

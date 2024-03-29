package cn.mediinfo.grus.shujuzx.service;


import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.grus.shujuzx.dto.bihuanzs.BiHuanGNDPZ;
import cn.mediinfo.grus.shujuzx.dto.bihuanzs.BiHuanXQDto;
import cn.mediinfo.grus.shujuzx.dto.bihuanzs.ZiDuanBMMC;
import cn.mediinfo.grus.shujuzx.dto.bihuanzs.ZiDuanRCDto;

import java.util.List;

public interface BiHuanZSService {
    BiHuanXQDto getBiHuanXQ(BiHuanGNDPZ biHuanGNDPZ) throws YuanChengException, TongYongYWException;
    BiHuanXQDto getBiHuanXQ2(BiHuanGNDPZ biHuanGNDPZ) throws YuanChengException, TongYongYWException;

    BiHuanXQDto getBiHuanZXJG(String zibiHID,String biHuanID,String ziBiHDCZXBZ,String jieDianID,String zuZhiJGID, List<ZiDuanRCDto> ruCanList) throws YuanChengException, TongYongYWException;
}

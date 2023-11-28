package cn.mediinfo.grus.shujuzx.service;


import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.grus.shujuzx.dto.bihuanzs.BiHuanGNDPZ;
import cn.mediinfo.grus.shujuzx.dto.bihuanzs.BiHuanXQDto;

public interface BiHuanZSService {
    BiHuanXQDto getBiHuanXQ(BiHuanGNDPZ biHuanGNDPZ) throws YuanChengException;
}

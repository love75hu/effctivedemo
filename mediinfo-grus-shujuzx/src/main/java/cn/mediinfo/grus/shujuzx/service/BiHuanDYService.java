package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.grus.shujuzx.dto.bihuandy.SC_BH_DiaoYongPZAddDto;
import cn.mediinfo.grus.shujuzx.dto.bihuandy.SC_BH_DiaoYongPZDto;
import cn.mediinfo.grus.shujuzx.dto.bihuandy.SC_BH_DiaoYongPZUpdateDto;
import cn.mediinfo.grus.shujuzx.dto.bihuandy.ShiTuMXBHPZDto;

import java.util.List;

public interface BiHuanDYService {
    List<ShiTuMXBHPZDto> getShuTuMXForBHPZ(String biHuanLXDM, String likeQuery) throws YuanChengException;
    List<SC_BH_DiaoYongPZDto> getBiHuanPZList(String gongNengDDM);
    SC_BH_DiaoYongPZDto getBiHuanPZXXByID(String id);
    SC_BH_DiaoYongPZDto addBiHuanPZ(SC_BH_DiaoYongPZAddDto addDto) ;
    SC_BH_DiaoYongPZDto updateBiHuanPZ(SC_BH_DiaoYongPZUpdateDto updateDto) throws WeiZhaoDSJException;
    SC_BH_DiaoYongPZDto updateBiHuanPZZT(String id, Integer qiYongBZ)throws WeiZhaoDSJException;
    Boolean zuoFeiBiHuanPZ(String id) throws WeiZhaoDSJException;
}

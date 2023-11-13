package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangDto;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangGreaterDto;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangMBDto;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangUpDateDto;

import java.util.List;

public interface WenDangPZService {

    List<SC_ZD_WenDangDto> getWenDangPZList(String leiBieDM, String likeQuery, Integer pageIndex, Integer pageSize);

    long getWenDangPZCount(String leiBieDM, String likeQuery);

    String addWenDangPZ(SC_ZD_WenDangGreaterDto wenDangGreaterDto) throws TongYongYWException;

    Boolean UpDateWenDangPZ(SC_ZD_WenDangUpDateDto wenDangUpDateDto) throws TongYongYWException;

    SC_ZD_WenDangMBDto getWenDangMBXX(String wenDangID) throws TongYongYWException;

    Boolean UpDateWenDangMBXX(String id, String xmlStr) throws TongYongYWException;

    Boolean zuoFeiWenDangPZ(String id) throws TongYongYWException;
}

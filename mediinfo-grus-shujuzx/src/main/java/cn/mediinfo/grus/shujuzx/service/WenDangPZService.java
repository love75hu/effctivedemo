package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangDto;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangGreaterDto;

import java.util.List;

public interface WenDangPZService {

    List<SC_ZD_WenDangDto> getWenDangPZList(String leiBieDM, String likeQuery, Integer pageIndex, Integer pageSize);

    long getWenDangPZCount(String leiBieDM, String likeQuery);

    String addWenDangPZ(SC_ZD_WenDangGreaterDto wenDangGreaterDto);

}

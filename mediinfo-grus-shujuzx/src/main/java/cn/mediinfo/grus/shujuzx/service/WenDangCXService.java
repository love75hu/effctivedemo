package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangDto;

import java.util.List;

public interface WenDangCXService {

    List<SC_ZD_WenDangDto> getWenDangList(String leiBieDM, String likeQuery, Integer pageIndex, Integer pageSize);
}

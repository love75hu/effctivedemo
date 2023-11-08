package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangDto;
import cn.mediinfo.grus.shujuzx.dto.wendangjls.SC_GW_JiLuXXListDto;

import java.util.Date;
import java.util.List;

public interface WenDangCXService {

    List<SC_GW_JiLuXXListDto> getWenDangJLList(String wenDangID,String mpi, Date startTime, Date endTime, Integer pageIndex, Integer pageSize);
}

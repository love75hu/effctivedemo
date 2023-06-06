package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.YinSiGZSZs.SC_ZD_YinSiPZOutDto;
import cn.mediinfo.starter.base.exception.TongYongYWException;

import java.util.List;

public interface YinSiGZSZService {
    List<SC_ZD_YinSiPZOutDto> getYinSiGZPZList(String chaXunMSDM, String zuZhiJGID) throws TongYongYWException;
}

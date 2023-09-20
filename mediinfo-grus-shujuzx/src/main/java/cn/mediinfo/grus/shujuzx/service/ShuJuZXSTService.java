package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.grus.shujuzx.dto.shujuzxsts.SC_ST_SanLiuLSTOutDto;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ShuJuZXSTService {
    /**
     * 根据相关条件获取病人基本信息列表条数
     * @return
     * @throws TongYongYWException 通用异常
     */
    Integer getBingRenYLSJCount(String bingRenID, String zhengJianHM, String xingMing, Date jianDangKSRQ, Date jianDangJSRQ) throws TongYongYWException, ParseException;
    /**
     * 根据相关条件获取病人基本信息列表
     * @return
     * @throws TongYongYWException 通用异常
     */
    List<SC_ST_SanLiuLSTOutDto> getBingRenYLSJList(String bingRenID, String zhengJianHM, String xingMing, Date jianDangKSRQ, Date jianDangJSRQ, Integer pageIndex, Integer pageSize) throws TongYongYWException, ParseException;
}


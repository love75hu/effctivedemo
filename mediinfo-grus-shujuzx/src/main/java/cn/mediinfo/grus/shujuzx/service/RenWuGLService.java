package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.orm.repository.querydsl.impl.MsfQuerydslExpression;
import cn.mediinfo.grus.shujuzx.dto.renwugls.*;
import cn.mediinfo.grus.shujuzx.model.QSC_RW_JiBenXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_RW_JiBenXXModel;

import java.util.List;

public interface RenWuGLService {
    List<SC_RW_JiBenXXListDto> getJiBenXXList(SC_RW_JiBenXXQueryDto queryDto);

    long getJiBenXXCount(SC_RW_JiBenXXQueryDto queryDto);

    MsfQuerydslExpression<QSC_RW_JiBenXXModel, SC_RW_JiBenXXModel> getQuery(SC_RW_JiBenXXQueryDto queryDto);

    SC_RW_JiBenXXDto getRenWuXXById(String id) throws TongYongYWException;

    List<SC_RW_JiBenXXDto> getRenWuXXByIds(List<String> ids) throws TongYongYWException;

    String addRenWuJBXX(SC_RW_JiBenXXCreateDto createDto) throws TongYongYWException;

    Boolean updateRenWuJBXX(SC_RW_JiBenXXUpdateDto updateDto) throws TongYongYWException;

    Boolean qiYongRW(String id, Integer qiYongBZ) throws TongYongYWException;

    List<SC_RW_ZhiXingRZListDto> getZhiXingRZList(SC_RW_ZhiXingRZQueryDto queryDto);

    List<SC_RW_JiBenXXDto> getJiBenXXDtoList(String ids);

    List<SC_RW_ShuJuYuanDto> getShuJuYuanList(String renWuID);

    Boolean saveShuJuYuanList(SC_RW_ShuJuYuanCreateDto createDto) throws TongYongYWException;

    List<SC_RW_TongYongPZDto> getTongYongPZ();

    Boolean saveTongYongPZ(List<SC_RW_TongYongPZDto> creatDto);

    Boolean saveZhiXingRZ(String id) throws TongYongYWException;

    Boolean saveZhiXingRZList(List<SC_RW_ZhiXingRZCreateDto> creatDto);
}

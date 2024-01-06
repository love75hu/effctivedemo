package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.orm.repository.querydsl.impl.MsfQuerydslExpression;
import cn.mediinfo.grus.shujuzx.dto.renwugls.*;
import cn.mediinfo.grus.shujuzx.model.QSC_RW_JiBenXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_RW_JiBenXXModel;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface RenWuGLService {


    List<SC_RW_JiBenXXListDto> getJiBenXXList(String likeQuery, String fenLeiDM, Integer qiYongBZ, Integer pageIndex, Integer pageSize);


    long getJiBenXXCount(String likeQuery, String fenLeiDM, Integer qiYongBZ);

    SC_RW_JiBenXXDto getRenWuXXById(String id) throws TongYongYWException;

    JiBenXXListDto getRenWuXXByIds(List<String> ids) throws TongYongYWException;

    String addRenWuJBXX(SC_RW_JiBenXXCreateDto createDto) throws TongYongYWException;

    Boolean updateRenWuJBXX(SC_RW_JiBenXXUpdateDto updateDto) throws TongYongYWException;

    @Transactional(rollbackOn = Exception.class)
    Boolean zuoFeiRenWuJBXX(String id) throws TongYongYWException;

    Boolean qiYongRW(String id, Integer qiYongBZ) throws TongYongYWException;


    SC_RW_ZhiXingRZDto getZhiXingRZDto(String id);

    List<SC_RW_ZhiXingRZListDto> getZhiXingRZList(String renWuID, Date zhiXingKSSJ, Date zhiXingJSSJ, Integer pageIndex, Integer pageSize);

    Long getZhiXingRZCount(String renWuID, Date zhiXingKSSJ, Date zhiXingJSSJ);

    List<SC_RW_JiBenXXDto> getJiBenXXDtoList(String ids);

    List<SC_RW_ShuJuYuanDto> getShuJuYuanList(String renWuID);

    Boolean saveShuJuYuanList(SC_RW_ShuJuYuanCreateDto createDto) throws TongYongYWException;

    List<SC_RW_TongYongPZDto> getTongYongPZ();

    Boolean saveTongYongPZ(List<SC_RW_TongYongPZDto> creatDto);

    Boolean saveZhiXingRZ(String id) throws TongYongYWException;

    Boolean saveZhiXingRZList(List<SC_RW_ZhiXingRZCreateDto> creatDto);

    SC_RW_TongYongPZDto getTongYongPZByFLDM(String fenLeiDM);

    String saveRenWuZXList(List<SC_RW_ZhiXingRZCreateDto> creatDto);

    String getJsonStr(String ruCan);

    Boolean qiYongSJY(String id, Integer qiYongBZ) throws TongYongYWException;

    RenWuXQDto getRenWuXQ(String id) throws TongYongYWException;

    List<SC_RW_TongYongPZDto> getTongYongPZNew();

    @Transactional(rollbackOn = Exception.class)
    Boolean saveTongYongPZNew(List<SC_RW_TongYongPZDto> creatDto);
}

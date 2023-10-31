package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.grus.shujuzx.dto.shengmingzqpzs.SC_ZD_ShengMingZQCreateDto;
import cn.mediinfo.grus.shujuzx.dto.shengmingzqpzs.SC_ZD_ShengMingZQListDto;
import cn.mediinfo.grus.shujuzx.dto.shengmingzqpzs.SC_ZD_ShengMingZQUpdateDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ShengMingZQPZService {
    List<SC_ZD_ShengMingZQListDto> getShengMingZQList();

    Boolean addShengMingZQ(SC_ZD_ShengMingZQCreateDto createDto) throws TongYongYWException;

    Boolean updateShengMingZQ(SC_ZD_ShengMingZQUpdateDto updateDto) throws TongYongYWException;

    @Transactional(rollbackOn = Exception.class)
    Boolean zuoFeiShengMingZQ(String id) throws TongYongYWException;
}

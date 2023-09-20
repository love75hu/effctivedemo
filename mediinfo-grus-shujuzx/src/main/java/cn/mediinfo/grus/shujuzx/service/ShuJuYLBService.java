package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.grus.shujuzx.dto.shujuylbs.SC_ZD_ShuJuYLBCreateDto;
import cn.mediinfo.grus.shujuzx.dto.shujuylbs.SC_ZD_ShuJuYLBDto;
import cn.mediinfo.grus.shujuzx.dto.shujuylbs.SC_ZD_ShuJuYLBUpdateDto;

import java.util.List;

public interface ShuJuYLBService {
    /**
     * 新增数据源类别
     *
     * @param createDto
     * @return
     * @throws TongYongYWException
     */
    String addShuJuYLB(SC_ZD_ShuJuYLBCreateDto createDto) throws TongYongYWException;

    /**
     * 修改数据源类别
     *
     * @param updateDto
     * @return
     * @throws TongYongYWException
     * @throws WeiZhaoDSJException
     */
    String updateShuJuYLB(SC_ZD_ShuJuYLBUpdateDto updateDto) throws TongYongYWException, WeiZhaoDSJException;

    /**
     * 根据ID获取数据源类别
     *
     * @param id
     * @return
     */
    SC_ZD_ShuJuYLBDto getShuJuYLBByID(String id);

    /**
     * 作废数据源类别
     *
     * @param id
     * @return
     * @throws WeiZhaoDSJException
     */
    Boolean zuoFeiShuJuYLB(String id) throws WeiZhaoDSJException;

    /**
     * 查询数据源类别list
     *
     * @param zuZhiJGID
     * @param likeQuery
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<SC_ZD_ShuJuYLBDto> getShuJuYLBList(String zuZhiJGID, String likeQuery, Integer pageIndex, Integer pageSize);

    /**
     * 获取数据源类别总行数
     *
     * @param zuZhiJGID
     * @param likeQuery
     * @return
     */
    long getShuJuYLBCount(String zuZhiJGID, String likeQuery);
}

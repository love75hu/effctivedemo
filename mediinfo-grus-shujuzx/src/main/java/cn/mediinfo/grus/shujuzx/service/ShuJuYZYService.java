package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.ShuJuYZYs.SC_ZD_ShuJuYZYCreateDto;
import cn.mediinfo.grus.shujuzx.dto.ShuJuYZYs.SC_ZD_ShuJuYZYDto;
import cn.mediinfo.grus.shujuzx.dto.ShuJuYZYs.SC_ZD_ShuJuYZYListDto;
import cn.mediinfo.grus.shujuzx.dto.ShuJuYZYs.SC_ZD_ShuJuYZYUpdateDto;
import cn.mediinfo.starter.base.exception.CanShuException;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.exception.WeiZhaoDSJException;

import java.util.List;

public interface ShuJuYZYService {
    /**
     * 新增数据源值域
     *
     * @param createDto
     * @return
     * @throws TongYongYWException
     */
    String addShuJuYZY(SC_ZD_ShuJuYZYCreateDto createDto) throws TongYongYWException;

    /**
     * 更新数据源值域
     *
     * @param updateDto
     * @return
     * @throws TongYongYWException
     */
    String updateShuJuYZY(SC_ZD_ShuJuYZYUpdateDto updateDto) throws TongYongYWException;

    /**
     * 作废数据源值域
     *
     * @param id
     * @return
     * @throws TongYongYWException
     */
    Boolean zuoFeiShuJuYZY(String id) throws TongYongYWException;

    /**
     * 根据ID获取数据源值域
     *
     * @param id
     * @return
     */
    SC_ZD_ShuJuYZYDto getShuJuYZYByID(String id);

    /**
     * 获取数据源值域list
     *
     * @param shuJuYLBIDList
     * @return
     * @throws CanShuException
     */
    List<SC_ZD_ShuJuYZYListDto> getShuJuYZYByLBIDList(List<String> shuJuYLBIDList) throws CanShuException;

    /**
     * 根据数据源类别ID获取数据源值域
     *
     * @param shuJuYLBID
     * @return
     */
    List<SC_ZD_ShuJuYZYDto> getShuJuYZYListByLBID(String shuJuYLBID);

    /**
     * 查询数据源值域list
     *
     * @param zuZhiJGID
     * @param shuJuYLBID
     * @param likeQuery
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws WeiZhaoDSJException
     */
    List<SC_ZD_ShuJuYZYDto> getShuJuYZYList(String zuZhiJGID, String shuJuYLBID, String likeQuery, Integer pageIndex, Integer pageSize) throws WeiZhaoDSJException;

    /**
     * 获取数据源值域总行数
     *
     * @param zuZhiJGID
     * @param shuJuYLBID
     * @param likeQuery
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws WeiZhaoDSJException
     */
    long getShuJuYZYCount(String zuZhiJGID, String shuJuYLBID, String likeQuery, Integer pageIndex, Integer pageSize) throws WeiZhaoDSJException;


}

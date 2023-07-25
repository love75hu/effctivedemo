package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.bihuanjdszs.SC_ZD_BiHuanJDCreateDto;
import cn.mediinfo.grus.shujuzx.dto.bihuanjdszs.SC_ZD_BiHuanJDDto;
import cn.mediinfo.grus.shujuzx.dto.bihuanjdszs.SC_ZD_BiHuanJDListDto;
import cn.mediinfo.grus.shujuzx.dto.bihuanjdszs.SC_ZD_BiHuanJDUpdateDto;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.exception.WeiZhaoDSJException;

import java.util.List;

public interface BiHuanJDSZService {
    /**
     * 获取闭环节点list
     *
     * @param biHuanLXDM
     * @param likeQuery
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<SC_ZD_BiHuanJDListDto> getBiHuanJDList(String biHuanLXDM, String likeQuery, Integer pageIndex, Integer pageSize);

    /**
     * 获取闭环节点数量
     *
     * @param biHuanLXDM
     * @param likeQuery
     * @return
     */
    long getBiHuanJDCount(String biHuanLXDM, String likeQuery);

    /**
     * 新增闭环节点
     *
     * @param createDto
     * @return
     * @throws TongYongYWException
     */
    SC_ZD_BiHuanJDDto addBiHuanJD(SC_ZD_BiHuanJDCreateDto createDto) throws TongYongYWException;

    /**
     * 更新闭环节点
     *
     * @param updateDto
     * @return
     * @throws TongYongYWException
     * @throws WeiZhaoDSJException
     */
    SC_ZD_BiHuanJDDto updateBiHuanJD(SC_ZD_BiHuanJDUpdateDto updateDto) throws TongYongYWException, WeiZhaoDSJException;

    /**
     * 根据id获取闭环节点
     *
     * @param id
     * @return
     */
    SC_ZD_BiHuanJDDto getBiHuanJDByID(String id);

    /**
     * 作废闭环节点
     *
     * @param id
     * @return
     * @throws WeiZhaoDSJException
     */
    Boolean zuoFeiBiHuanJD(String id) throws WeiZhaoDSJException;

    /**
     * 根据闭环类型获取闭环节点
     * @param biHuanLXDM
     * @param zhuYuanSYBZ
     * @param menZhenSYBZ
     * @param jiZhenSYBZ
     * @param tiJianSYBZ
     * @return
     */
    List<SC_ZD_BiHuanJDListDto> getBiHuanJDByBHLX(String biHuanLXDM, Integer zhuYuanSYBZ, Integer menZhenSYBZ, Integer jiZhenSYBZ, Integer tiJianSYBZ);
}

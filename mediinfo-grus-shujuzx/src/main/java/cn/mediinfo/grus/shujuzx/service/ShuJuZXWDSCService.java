package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.grus.shujuzx.dto.shujuzxscs.SC_SC_ShouCangJMXInDto;
import cn.mediinfo.grus.shujuzx.dto.shujuzxscs.SC_SC_ShouCangJMXOutDto;
import cn.mediinfo.grus.shujuzx.dto.shujuzxscs.SC_SC_ShouCangJXXInDto;
import cn.mediinfo.grus.shujuzx.dto.shujuzxscs.SC_SC_ShouCangJXXOutDto;

import java.util.List;

public interface ShuJuZXWDSCService {
    /**
     * 新增收藏夹
     *
     * @param shouCangJInDto
     * @return
     */
    Integer addShouCangJia(SC_SC_ShouCangJXXInDto shouCangJInDto) throws TongYongYWException;

    /**
     * 新增收藏夹明细
     *
     * @param shouCangJMXInDto
     * @return
     */
    Integer addShouCangJMX(SC_SC_ShouCangJMXInDto shouCangJMXInDto);

    /**
     * 更新收藏夹
     *
     * @param shouCangJInDto
     * @return
     */
    Integer updateShouCangJia(SC_SC_ShouCangJXXInDto shouCangJInDto) throws TongYongYWException;

    /**
     * 移除某一条收藏夹明细
     *
     * @param id
     * @return
     */
    Integer yiChuShouCangJMX(String id);

    /**
     * 作废收藏夹
     *
     * @param id
     * @return
     */
    Integer zuoFeiShouCangJia(String id) throws TongYongYWException;

    /**
     * 获取当前登录人的收藏夹列表
     *
     * @param likeQuery
     * @return
     */
    List<SC_SC_ShouCangJXXOutDto> getShouCangJiaList(String likeQuery);

    /**
     * 根据收藏夹id和其他查询条件获取收藏夹明细条数
     *
     * @param likeQuery
     * @param shouCangJID
     * @return
     */
    Integer getShouCangJMXCount(String likeQuery, String shouCangJID);

    /**
     * 根据收藏夹id和其他查询条件获取收藏夹明细
     *
     * @param likeQuery
     * @param shouCangJID
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<SC_SC_ShouCangJMXOutDto> getShouCangJMXList(String likeQuery, String shouCangJID, Integer pageIndex, Integer pageSize);

}

package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.grus.shujuzx.events.BaseEventDto;
import cn.mediinfo.grus.shujuzx.dto.bingrenylsjs.SC_LC_BingRenYLSJInDto;

public interface BingRenYLSJService {
    /**
     * 订阅调用更新病人就诊次数-无病人数据新增病人事件
     *
     * @param eventDto 事件参数
     * @return 是否成功
     */
    Boolean updateBingRenYLSJForJZCS(BaseEventDto<Integer> eventDto) throws YuanChengException;

    /**
     * 新增病人医疗事件
     *
     * @param addBingRenDto
     * @return
     */
    Integer addBingRenYLSJ(SC_LC_BingRenYLSJInDto addBingRenDto);

    /**
     * 批量更新病人医疗事件
     *
     * @param shouCiZX
     * @param zhiXingSJ
     * @return
     * @throws TongYongYWException
     */
    Integer updateBingRenYLSJ(Integer shouCiZX, Integer zhiXingSJ) throws YuanChengException;
}

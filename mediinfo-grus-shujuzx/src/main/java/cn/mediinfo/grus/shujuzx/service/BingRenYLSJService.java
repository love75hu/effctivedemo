package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.Events.BaseEventDto;
import cn.mediinfo.grus.shujuzx.dto.BingRenYLSJs.SC_LC_BingRenYLSJInDto;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.exception.YuanChengException;

public interface BingRenYLSJService {
    /**
     * 订阅调用更新病人就诊次数-无病人数据新增病人事件
     *
     * @param eventDto 事件参数
     * @return 是否成功
     */
    Boolean updateBingRenYLSJForJZCS(BaseEventDto<Integer> eventDto);

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

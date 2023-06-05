package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.Events.BaseEventDto;

public interface BingRenYLSJService {
    /**
     * 订阅调用更新病人就诊次数-无病人数据新增病人事件
     * @param eventDto 事件参数
     * @return 是否成功
     */
    Boolean updateBingRenYLSJForJZCS(BaseEventDto<Integer> eventDto);
}

package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.grus.shujuzx.events.BaseEventDto;

public interface XiaoFeiXXService {
    /**
     * 消费更新就诊次数信息(完成接诊-出院-取消出院)消息
     *
     * @param eventDto 消息入参(按照消息标准规范)
     * @return
     */
    void WanChengJZ(BaseEventDto<Integer> eventDto) throws TongYongYWException, YuanChengException;

    void RuYuanDJ(BaseEventDto<Integer> eventDto) throws TongYongYWException, YuanChengException;

    void QuXiaoRY(BaseEventDto<Integer> eventDto) throws TongYongYWException, YuanChengException;
}

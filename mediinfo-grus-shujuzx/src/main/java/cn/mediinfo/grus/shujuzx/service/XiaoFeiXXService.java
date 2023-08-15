package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.Events.BaseEventDto;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;

public interface XiaoFeiXXService {
    /**
     * 消费更新就诊次数信息(完成接诊-出院-取消出院)消息
     *
     * @param eventDto 消息入参(按照消息标准规范)
     * @return
     */
    MsfJpaRepository xiaoFeiGXJZCSXX(BaseEventDto<Integer> eventDto) throws TongYongYWException;
}

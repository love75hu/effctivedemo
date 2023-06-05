package cn.mediinfo.grus.shujuzx.Events;

import cn.mediinfo.starter.base.eventbus.event.IntegrationEvent;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 通用事件dto
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseEventDto<T> extends IntegrationEvent {

    /**
     *消息头
     */
    @Schema(description = "消息头")
    private XiaoXiTouEventDto xiaoXiTou;

    /**
     *业务信息
     */
    @Schema(description = "业务信息")
    private YeWuXXEventDto<T> yeWuXX;
}



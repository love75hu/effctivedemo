package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.eventbus.annotation.EventSubscription;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.grus.shujuzx.events.BaseEventDto;
import cn.mediinfo.grus.shujuzx.service.BingRenYLSJService;
import cn.mediinfo.grus.shujuzx.service.XiaoFeiXXService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 消息消费统一入口(dapr)
 */
@Service
public class XiaoFeiXXServiceImpl implements XiaoFeiXXService {
    private final BingRenYLSJService bingRenYLSJService;

    public XiaoFeiXXServiceImpl(
            BingRenYLSJService bingRenYLSJService) {
        this.bingRenYLSJService = bingRenYLSJService;
    }


    @Override
    @EventSubscription(topic = "JZ_MZ_WanChengJZ")
    public void WanChengJZ(BaseEventDto<Integer> eventDto) throws TongYongYWException {
        updateBingRenYLSJForJZCS(eventDto);
    }

    @Override
    @EventSubscription(topic = "JZ_ZY_RuYuanDJ")
    public void RuYuanDJ(BaseEventDto<Integer> eventDto) throws TongYongYWException {
        updateBingRenYLSJForJZCS(eventDto);
    }

    @Override
    @EventSubscription(topic = "JZ_ZY_QuXiaoRY")
    public void QuXiaoRY(BaseEventDto<Integer> eventDto) throws TongYongYWException {
        updateBingRenYLSJForJZCS(eventDto);
    }

    private void updateBingRenYLSJForJZCS(BaseEventDto<Integer> eventDto) throws TongYongYWException {
        if (ObjectUtils.isEmpty(eventDto.getXiaoXiTou()) || ObjectUtils.isEmpty(eventDto.getXiaoXiTou().getFaSongXTJC())) {
            throw new TongYongYWException("消息头或发送方为空");
        }
        if (eventDto.getYeWuXX() == null) {
            throw new TongYongYWException("业务实体为空");
        }
        bingRenYLSJService.updateBingRenYLSJForJZCS(eventDto);
    }
}

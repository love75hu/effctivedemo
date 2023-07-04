package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.grus.shujuzx.Events.BaseEventDto;
import cn.mediinfo.grus.shujuzx.service.XiaoFeiXXService;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.MaskFormatter;

@RestController
@RequestMapping({"api/v1.0/XiaoFeiXX", "api/v1/XiaoFeiXX"})
@Slf4j
@Validated
public class XiaoFeiXXController {
    private final XiaoFeiXXService xiaoFeiXXService;

    public XiaoFeiXXController(XiaoFeiXXService xiaoFeiXXService)
    {
        this.xiaoFeiXXService=xiaoFeiXXService;
    }

    //todo  [EventSubscription("JZ_MZ_WanChengJZ")]
    @Operation(summary = "消费完成接诊信息")
    @PostMapping("XiaoFeiWCJZXX")
    public MsfJpaRepository XiaoFeiWCJZXX(@RequestBody BaseEventDto<Integer> eventDto) throws TongYongYWException
    {
        return xiaoFeiXXService.xiaoFeiGXJZCSXX(eventDto);
    }
    //todo  [EventSubscription("JZ_ZY_RuYuanDJ")]
    @Operation(summary = "消费入院信息")
    @PostMapping("XiaoFeiCYXX")
    public MsfJpaRepository XiaoFeiCYXX(@RequestBody BaseEventDto<Integer> eventDto) throws TongYongYWException
    {
        return xiaoFeiXXService.xiaoFeiGXJZCSXX(eventDto);
    }
    //todo  [EventSubscription("JZ_ZY_QuXiaoRY")]
    @Operation(summary = "消费取消入院信息")
    @PostMapping("XiaoFeiQXCYXX")
    public MsfJpaRepository XiaoFeiQXCYXX(@RequestBody BaseEventDto<Integer> eventDto) throws TongYongYWException
    {
        return xiaoFeiXXService.xiaoFeiGXJZCSXX(eventDto);
    }

}

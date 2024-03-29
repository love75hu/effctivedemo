package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.grus.shujuzx.events.BaseEventDto;
import cn.mediinfo.grus.shujuzx.service.XiaoFeiXXService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "XiaoFeiXXController", description = "消费信息")
@RequestMapping({"api/v1.0/XiaoFeiXX", "api/v1/XiaoFeiXX"})
@Slf4j
@Validated
public class XiaoFeiXXController {
    private final XiaoFeiXXService xiaoFeiXXService;

    public XiaoFeiXXController(XiaoFeiXXService xiaoFeiXXService) {
        this.xiaoFeiXXService = xiaoFeiXXService;
    }

    //todo  [EventSubscription("JZ_MZ_WanChengJZ")]
    @Operation(summary = "消费完成接诊信息")
    @PostMapping("XiaoFeiWCJZXX")
    public void XiaoFeiWCJZXX(@RequestBody BaseEventDto<Integer> eventDto) throws TongYongYWException, YuanChengException {
        xiaoFeiXXService.WanChengJZ(eventDto);
    }

    //todo  [EventSubscription("JZ_ZY_RuYuanDJ")]
    @Operation(summary = "消费入院信息")
    @PostMapping("XiaoFeiCYXX")
    public void XiaoFeiCYXX(@RequestBody BaseEventDto<Integer> eventDto) throws TongYongYWException, YuanChengException {
        xiaoFeiXXService.RuYuanDJ(eventDto);
    }

    //todo  [EventSubscription("JZ_ZY_QuXiaoRY")]
    @Operation(summary = "消费取消入院信息")
    @PostMapping("XiaoFeiQXCYXX")
    public void XiaoFeiQXCYXX(@RequestBody BaseEventDto<Integer> eventDto) throws TongYongYWException, YuanChengException {
        xiaoFeiXXService.QuXiaoRY(eventDto);
    }

}

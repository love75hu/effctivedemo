package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.grus.shujuzx.service.XiaoFeiXXService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "XiaoFeiXXController", description = "第三方——控制器")
@RequestMapping({"api/v1.0/XiaoFeiXX", "api/v1/XiaoFeiXX"})
@Slf4j
@Validated
public class XiaoFeiXXController {
    private final XiaoFeiXXService xiaoFeiXXService;

    public XiaoFeiXXController(XiaoFeiXXService xiaoFeiXXService)
    {
        this.xiaoFeiXXService=xiaoFeiXXService;
    }

}

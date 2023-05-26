package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.grus.shujuzx.service.ShuJuYLBService;
import cn.mediinfo.grus.shujuzx.service.ShuJuYZYService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "YeWuZDController", description = "360业务字典")
@RequestMapping({"api/v1.0/YeWuZD", "api/v1/YeWuZD"})
@Slf4j
@Validated
public class YeWuZDController {
    private final ShuJuYLBService shuJuYLBService;
    private final ShuJuYZYService shuJuYZYService;
    public YeWuZDController(ShuJuYLBService shuJuYLBService,ShuJuYZYService shuJuYZYService)
    {
        this.shuJuYLBService = shuJuYLBService;
        this.shuJuYZYService=shuJuYZYService;
    }
}

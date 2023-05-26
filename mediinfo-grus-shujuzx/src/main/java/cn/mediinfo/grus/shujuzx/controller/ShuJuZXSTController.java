package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.grus.shujuzx.service.ShuJuZXSTService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "ShuJuZXSTController", description = "数据中心-视图")
@RequestMapping({"api/v1.0/ShuJuZXSC", "api/v1/ShuJuZXSC"})
@Slf4j
@Validated
public class ShuJuZXSTController {
    private final ShuJuZXSTService shuJuZXSTService;
    public ShuJuZXSTController(ShuJuZXSTService shuJuZXSTService)
    {
        this.shuJuZXSTService=shuJuZXSTService;
    }
}

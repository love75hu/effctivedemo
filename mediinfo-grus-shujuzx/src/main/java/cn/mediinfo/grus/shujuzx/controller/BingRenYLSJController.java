package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.grus.shujuzx.service.BingRenYLSJService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "BingRenYLSJController", description = "病人医疗事件")
@RequestMapping({"api/v1.0/BingRenYLSJ", "api/v1/BingRenYLSJ"})
@Slf4j
@Validated
public class BingRenYLSJController {
    private final BingRenYLSJService bingRenYLSJService;
    public BingRenYLSJController(BingRenYLSJService bingRenYLSJService)
    {
        this.bingRenYLSJService=bingRenYLSJService;
    }
}

package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.grus.shujuzx.service.BiHuanLCService;
import cn.mediinfo.grus.shujuzx.service.YinSiGZSZService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "ShuJuZXZDController", description = "临床数据中心字典")
@RequestMapping({"api/v1.0/ShuJuZXZD", "api/v1/ShuJuZXZD"})
@Slf4j
@Validated
public class ShuJuZXZDController {
    private final YinSiGZSZService yinSiGZSZService;
    private final BiHuanLCService biHuanLCService;

    public ShuJuZXZDController(YinSiGZSZService yinSiGZSZService,
                               BiHuanLCService biHuanLCService)
    {
        this.yinSiGZSZService=yinSiGZSZService;
        this.biHuanLCService=biHuanLCService;
    }
}

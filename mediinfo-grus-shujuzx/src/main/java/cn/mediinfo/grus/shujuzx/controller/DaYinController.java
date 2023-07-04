package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.grus.shujuzx.service.DaYinService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"api/v1.0/DaYin", "api/v1/DaYin"})
@Slf4j
@Validated
public class DaYinController {
    private final DaYinService daYinService;
    public DaYinController(DaYinService daYinService)
    {
        this.daYinService=daYinService;
    }
}

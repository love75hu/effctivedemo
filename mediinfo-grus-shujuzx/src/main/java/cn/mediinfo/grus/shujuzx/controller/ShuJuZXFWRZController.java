package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.grus.shujuzx.Events.BaseEventDto;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXFWRZ.AddFangWenRZDto;
import cn.mediinfo.grus.shujuzx.service.ShuJuZXFWRZService;
import cn.mediinfo.grus.shujuzx.service.ZhuSuoYCZRZService;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;
import cn.mediinfo.starter.base.response.MsfResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "ShuJuZXFWRZController", description = "360访问日志")
@RequestMapping({"api/v1.0/ShuJuZXFWRZ", "api/v1/ShuJuZXFWRZ"})
@Slf4j
@Validated
public class ShuJuZXFWRZController {

    private final ShuJuZXFWRZService shuJuZXFWRZService;
    private final ZhuSuoYCZRZService zhuSuoYCZRZService;

    public ShuJuZXFWRZController(ShuJuZXFWRZService shuJuZXFWRZService,
                                 ZhuSuoYCZRZService zhuSuoYCZRZService)
    {
        this.shuJuZXFWRZService=shuJuZXFWRZService;
        this.zhuSuoYCZRZService=zhuSuoYCZRZService;
    }
    @Operation(summary = "添加访问日志")
    @PostMapping("AddFangWenRZ")
    public MsfResponse<Boolean> AddFangWenRZ(@RequestBody AddFangWenRZDto addFangWenRZDto)
    {
        return MsfResponse.success(shuJuZXFWRZService.addFangWenRZ(addFangWenRZDto));
    }



}

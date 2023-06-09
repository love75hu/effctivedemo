package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.grus.shujuzx.dto.QuanShengMZQs.PeiZhiXXDtos;
import cn.mediinfo.grus.shujuzx.service.QuanShengMZQService;
import cn.mediinfo.starter.base.response.MsfResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "HuanZhe360Controller", description = "云数仓-患者360")
@RequestMapping({"api/v1.0/HuanZhe360", "api/v1/HuanZhe360"})
@Slf4j
@Validated
public class HuanZhe360Controller {
    private final QuanShengMZQService quanShengMZQService;

    public HuanZhe360Controller(QuanShengMZQService quanShengMZQService) {
        this.quanShengMZQService = quanShengMZQService;
    }

    /**
     * 根据查询模式代码获取配置信息
     *
     * @param chaXunMSDM
     * @return
     */
    @Operation(summary = "根据查询模式代码获取配置信息")
    @GetMapping("GetPeiZhiXXByCXMSDM")
    public MsfResponse<PeiZhiXXDtos> getPeiZhiXXByCXMSDM(@RequestParam String chaXunMSDM) {
        return MsfResponse.success(quanShengMZQService.getPeiZhiXXByCXMSDM(chaXunMSDM));
    }
}
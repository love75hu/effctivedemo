package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.quanshengmzqs.PeiZhiXXDtos;
import cn.mediinfo.grus.shujuzx.service.QuanShengMZQService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"api/v1.0/HuanZhe", "api/v1/HuanZhe"})
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

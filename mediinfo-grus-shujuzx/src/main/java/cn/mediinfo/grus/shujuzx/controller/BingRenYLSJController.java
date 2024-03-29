package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.bingrenylsjs.SC_LC_BingRenYLSJInDto;
import cn.mediinfo.grus.shujuzx.service.BingRenYLSJService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "BingRenYLSJController", description = "病人医疗事件")
@RequestMapping({"api/v1.0/BingRenYLSJ", "api/v1/BingRenYLSJ"})
@Slf4j
@Validated
public class BingRenYLSJController {
    private final BingRenYLSJService bingRenYLSJService;

    public BingRenYLSJController(BingRenYLSJService bingRenYLSJService) {
        this.bingRenYLSJService = bingRenYLSJService;
    }

    /**
     * 新增病人医疗事件
     *
     * @param addBingRenDto
     * @return
     */
    @Operation(summary = "新增病人医疗事件")
    @PostMapping("AddBingRenYLSJ")
    public MsfResponse<Integer> addBingRenYLSJ(@Validated @RequestBody SC_LC_BingRenYLSJInDto addBingRenDto) {
        return MsfResponse.success(bingRenYLSJService.addBingRenYLSJ(addBingRenDto));
    }

    /**
     * 批量更新病人医疗事件
     *
     * @param shouCiZX
     * @param zhiXingSJ
     * @return
     */
    @Operation(summary = "批量更新病人医疗事件")
    @PostMapping("UpdateBingRenYLSJ")
    public MsfResponse<Integer> updateBingRenYLSJ(@RequestParam Integer shouCiZX,
                                                  @RequestParam Integer zhiXingSJ) throws YuanChengException {
        return MsfResponse.success(bingRenYLSJService.updateBingRenYLSJ(shouCiZX, zhiXingSJ));
    }
}

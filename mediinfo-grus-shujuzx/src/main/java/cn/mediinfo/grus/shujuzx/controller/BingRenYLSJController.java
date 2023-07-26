package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.grus.shujuzx.dto.bingrenylsjs.SC_LC_BingRenYLSJInDto;
import cn.mediinfo.grus.shujuzx.service.BingRenYLSJService;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.exception.YuanChengException;
import cn.mediinfo.starter.base.response.MsfResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
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
     * @throws TongYongYWException
     */
    @Operation(summary = "批量更新病人医疗事件")
    @PostMapping("UpdateBingRenYLSJ")
    public MsfResponse<Integer> updateBingRenYLSJ(@RequestParam Integer shouCiZX,
                                                  @RequestParam Integer zhiXingSJ) throws YuanChengException {
        return MsfResponse.success(bingRenYLSJService.updateBingRenYLSJ(shouCiZX, zhiXingSJ));
    }
}

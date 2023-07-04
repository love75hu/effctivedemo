package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.grus.shujuzx.dto.ShuJuZXSCs.SC_SC_ShouCangJMXInDto;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXSCs.SC_SC_ShouCangJMXOutDto;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXSCs.SC_SC_ShouCangJXXInDto;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXSCs.SC_SC_ShouCangJXXOutDto;
import cn.mediinfo.grus.shujuzx.service.ShuJuZXWDSCService;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.response.MsfResponse;
import cn.mediinfo.starter.base.util.StringUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"api/v1.0/ShuJuZXSC", "api/v1/ShuJuZXSC"})
@Slf4j
@Validated
public class ShuJuZXSCController {
    private final ShuJuZXWDSCService shuJuZXWDSCService;
    public ShuJuZXSCController(ShuJuZXWDSCService shuJuZXWDSCService)
    {
        this.shuJuZXWDSCService=shuJuZXWDSCService;
    }

    @Operation(summary = "新增收藏夹")
    @PostMapping("AddShouCangJia")
    public MsfResponse<Integer> addShouCangJia(@RequestBody SC_SC_ShouCangJXXInDto shouCangJInDto) throws TongYongYWException {
        if (!StringUtil.hasText(shouCangJInDto.getShouCangJMC())) {
            return MsfResponse.fail("收藏夹名称不能为空！");
        }
        return MsfResponse.success(shuJuZXWDSCService.addShouCangJia(shouCangJInDto));
    }

    @Operation(summary = "新增收藏夹明细")
    @PostMapping("AddShouCangJMX")
    public MsfResponse<Integer> addShouCangJMX(@RequestBody SC_SC_ShouCangJMXInDto shouCangJMXInDto) {
        return MsfResponse.success(shuJuZXWDSCService.addShouCangJMX(shouCangJMXInDto));
    }

    @Operation(summary = "更新收藏夹")
    @PutMapping("UpdateShouCangJia")
    public MsfResponse<Integer> updateShouCangJia(@RequestBody SC_SC_ShouCangJXXInDto shouCangJInDto) throws TongYongYWException {
        return MsfResponse.success(shuJuZXWDSCService.updateShouCangJia(shouCangJInDto));
    }

    @Operation(summary = "移除某一条收藏夹明细")
    @DeleteMapping("YiChuShouCangJMX")
    public MsfResponse<Integer> yiChuShouCangJMX(@RequestParam String id){
        return MsfResponse.success(shuJuZXWDSCService.yiChuShouCangJMX(id));
    }

    @Operation(summary = "作废收藏夹")
    @DeleteMapping("ZuoFeiShouCangJia")
    public MsfResponse<Integer> zuoFeiShouCangJia(@RequestParam String id) throws TongYongYWException {
        return MsfResponse.success(shuJuZXWDSCService.zuoFeiShouCangJia(id));
    }

    @Operation(summary = "获取当前登录人的收藏夹列表")
    @GetMapping("GetShouCangJiaList")
    public MsfResponse<List<SC_SC_ShouCangJXXOutDto>> getShouCangJiaList(@RequestParam(required = false) String likeQuery) {
        return MsfResponse.success(shuJuZXWDSCService.getShouCangJiaList(likeQuery));
    }

    @Operation(summary = "根据收藏夹id和其他查询条件获取收藏夹明细条数")
    @GetMapping("GetShouCangJMXCount")
    public MsfResponse<Integer> getShouCangJMXCount(@RequestParam(required = false) String likeQuery, @RequestParam String shouCangJID) {
        return MsfResponse.success(shuJuZXWDSCService.getShouCangJMXCount(likeQuery, shouCangJID));
    }

    @Operation(summary = "根据收藏夹id和其他查询条件获取收藏夹明细")
    @GetMapping("GetShouCangJMXList")
    public MsfResponse<List<SC_SC_ShouCangJMXOutDto>> getShouCangJMXList(String likeQuery, @RequestParam String shouCangJID, @RequestParam(required = false,defaultValue = "1") Integer pageIndex,  @RequestParam(required = false,defaultValue = "15") Integer pageSize) {
        return MsfResponse.success(shuJuZXWDSCService.getShouCangJMXList(likeQuery, shouCangJID, pageIndex, pageSize));
    }
}

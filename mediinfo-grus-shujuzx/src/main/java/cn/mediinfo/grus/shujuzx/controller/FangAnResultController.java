package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.bihuanlcs.BingLiXQDto;
import cn.mediinfo.grus.shujuzx.dto.bihuanlcs.BingLiXQXXDto;
import cn.mediinfo.grus.shujuzx.dto.fangan.FangAnByFACXLSDTO;
import cn.mediinfo.grus.shujuzx.dto.result.BingLiXQDTO;
import cn.mediinfo.grus.shujuzx.dto.result.BingRenJBXXDTO;
import cn.mediinfo.grus.shujuzx.dto.result.QueryResultDTO;
import cn.mediinfo.grus.shujuzx.request.result.BingLiXXQRequest;
import cn.mediinfo.grus.shujuzx.service.FangAnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "FangAnResultController", description = "方案查询结果")
@RequestMapping({"api/v1.0/fangan/cx/result", "api/v1/fangan/cx/result"})
@Slf4j
@Validated
public class FangAnResultController {
    private final FangAnService fangAnService;

    public FangAnResultController(FangAnService fangAnService) {
        this.fangAnService = fangAnService;
    }

    @Operation(summary = "病历查询")
    @GetMapping("/bl/list")
    public MsfResponse<List<BingRenJBXXDTO>> listBLXX(@NotBlank(message="方案查询历史id不能为空") @RequestParam String fangAnCXLSId, @RequestParam(required = false,defaultValue = "1") Integer pageIndex,  @RequestParam(required = false,defaultValue = "10") Integer pageSize) throws TongYongYWException, YuanChengException {
        return MsfResponse.success(fangAnService.getBinRenJBXXList(fangAnCXLSId,pageIndex,pageSize));
    }


    @Operation(summary = "病历查询总数")
    @GetMapping("/bl/count")
    public MsfResponse<Long> countBLXX(@NotBlank(message="方案查询历史id不能为空") @RequestParam String fangAnCXLSId) throws TongYongYWException, YuanChengException {
        return MsfResponse.success(fangAnService.getBinRenJBXXCount(fangAnCXLSId));
    }

//    @Operation(summary = "查询病历详情")
//    @PostMapping("/bl/listBingLiXQ")
//    public MsfResponse<List<BingLiXQDTO>> listBingLiXQ(@RequestBody @Validated BingLiXXQRequest request) {
//        return MsfResponse.success();
//    }

    @Operation(summary = "查询历史方案")
    @GetMapping("/getFangAnCXLS")
    public MsfResponse<FangAnByFACXLSDTO> getFangAnCXLS(@NotBlank(message="方案查询历史id不能为空") @RequestParam String fangAnCXLSId) throws TongYongYWException {
        return MsfResponse.success(fangAnService.getFangAnCXLS(fangAnCXLSId));
    }

    @Operation(summary = "结果列表")
    @GetMapping("/listQueryResult")
    public MsfResponse<List<List<QueryResultDTO>>> listQueryResult(@NotBlank(message="方案查询历史id不能为空") @RequestParam String fangAnCXLSId, @RequestParam(required = false) Integer mergeType, @RequestParam(required = false,defaultValue = "1") Integer pageIndex,  @RequestParam(required = false,defaultValue = "10") Integer pageSize) throws TongYongYWException {
        return MsfResponse.success(fangAnService.getFangAnJGList(fangAnCXLSId,mergeType,pageIndex,pageSize));
    }

    @Operation(summary = "结果列表总数")
    @GetMapping("/countQueryResult")
    public MsfResponse<Long> countQueryResult(@NotBlank(message="方案查询历史id不能为空") @RequestParam String fangAnCXLSId, @RequestParam(required = false) Integer mergeType) throws TongYongYWException {
        return MsfResponse.success(fangAnService.getFangAnJGCount(fangAnCXLSId,mergeType));
    }
    @Operation(summary = "查询病历详情")
    @PostMapping("getBingLiXQ")
    public MsfResponse<List<BingLiXQXXDto>> getBingLiXQ(@RequestBody BingLiXQDto bingLiXQDto) throws YuanChengException {
        return MsfResponse.success(fangAnService.getBingLiXQ(bingLiXQDto));
    }
}


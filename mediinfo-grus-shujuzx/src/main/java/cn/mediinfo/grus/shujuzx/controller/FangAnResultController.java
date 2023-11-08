package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.fangan.FangAnByFACXLSDTO;
import cn.mediinfo.grus.shujuzx.dto.result.BingLiXQDTO;
import cn.mediinfo.grus.shujuzx.dto.result.BingRenJBXXDTO;
import cn.mediinfo.grus.shujuzx.dto.result.QueryResultDTO;
import cn.mediinfo.grus.shujuzx.request.fangancxls.FangAnCXLSByIdRequest;
import cn.mediinfo.grus.shujuzx.request.result.*;
import cn.mediinfo.grus.shujuzx.service.ChaXunFAXXService;
import cn.mediinfo.grus.shujuzx.service.FangAnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public MsfResponse<List<BingRenJBXXDTO>> listBLXX(@Validated BingLiXXQueryPageRequest request) {
        return MsfResponse.success();
    }


    @Operation(summary = "病历查询总数")
    @GetMapping("/bl/count")
    public MsfResponse<Long> countBLXX(@RequestBody @Validated BingLiXXQueryCountRequest request) {
        return MsfResponse.success();
    }

    @Operation(summary = "查询病历详情")
    @PostMapping("/bl/listBingLiXQ")
    public MsfResponse<List<BingLiXQDTO>> listBingLiXQ(@RequestBody @Validated BingLiXXQRequest request) {
        return MsfResponse.success();
    }

    @Operation(summary = "查询历史方案")
    @GetMapping("/getFangAnCXLS")
    public MsfResponse<FangAnByFACXLSDTO> getFangAnCXLS(@RequestParam FangAnCXLSByIdRequest request) throws TongYongYWException {
        return MsfResponse.success(fangAnService.getFangAnCXLS(request));
    }

    @Operation(summary = "结果列表")
    @GetMapping("/listQueryResult")
    public MsfResponse<List<List<QueryResultDTO>>> listQueryResult(QueryResultPageRequest request) {
        return MsfResponse.success();
    }

    @Operation(summary = "结果列表总数")
    @GetMapping("/countQueryResult")
    public MsfResponse<Long> countQueryResult(@Validated @RequestBody QueryResultCountRequest request) throws TongYongYWException {
        return MsfResponse.success(fangAnService.getFangAnJGCount(request));
    }
}


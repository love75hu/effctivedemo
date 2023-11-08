package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangDto;
import cn.mediinfo.grus.shujuzx.dto.wendangjls.SC_GW_JiLuXXListDto;
import cn.mediinfo.grus.shujuzx.service.WenDangCXService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@Tag(name = "WenDangCXController", description = "文档查询")
@RequestMapping({"api/v1.0/WenDangCX", "api/v1/WenDangCX"})
@Slf4j
@Validated
public class WenDangCXController {
    private final WenDangCXService wenDangCXService;

    public WenDangCXController(WenDangCXService wenDangCXService){
        this.wenDangCXService = wenDangCXService;
    }

    /**
     * 获取文档记录列表
     */
    @Operation(summary = "获取文档记录列表")
    @GetMapping("getWenDangJLList")
    public MsfResponse<List<SC_GW_JiLuXXListDto>> getWenDangJLList(
            @RequestParam(required = true) String wenDangID,
            @RequestParam(required = false) String mpi,
            @RequestParam(required = false) Date startTime,
            @RequestParam(required = false) Date endTime,
            @RequestParam(required = false, defaultValue = "1") Integer PageIndex,
            @RequestParam(required = false, defaultValue = "10") Integer PageSize) {
        return MsfResponse.success(wenDangCXService.getWenDangJLList(wenDangID, mpi,startTime,endTime, PageIndex, PageSize));
    }
}

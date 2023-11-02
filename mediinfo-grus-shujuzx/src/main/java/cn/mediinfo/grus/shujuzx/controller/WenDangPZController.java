package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangDto;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangGreaterDto;
import cn.mediinfo.grus.shujuzx.service.WenDangPZService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "WenDangPZController", description = "文档配置")
@RequestMapping({"api/v1.0/WenDangPZ", "api/v1/WenDangPZ"})
@Slf4j
@Validated
public class WenDangPZController {

    private final WenDangPZService wenDangPZService;

    public WenDangPZController(WenDangPZService wenDangPZService) {
        this.wenDangPZService = wenDangPZService;
    }

    /**
     * 获取文档配置列表
     */
    @Operation(summary = "获取文档配置列表")
    @GetMapping("GetWenDangPZList")
    public MsfResponse<List<SC_ZD_WenDangDto>> getWenDangPZList(
            @RequestParam(required = false) String leiBieDM,
            @RequestParam(required = false) String likeQuery,
            @RequestParam(required = false, defaultValue = "1") Integer PageIndex,
            @RequestParam(required = false, defaultValue = "10") Integer PageSize) {
        return MsfResponse.success(wenDangPZService.getWenDangPZList(leiBieDM, likeQuery, PageIndex, PageSize));
    }

    /**
     * 获取文档配置数量
     */
    @Operation(summary = "获取文档配置数量")
    @GetMapping("GetWenDangPZCount")
    public MsfResponse<Long> getWenDangPZCount(
            @RequestParam(required = false) String leiBieDM,
            @RequestParam(required = false) String likeQuery) {
        return MsfResponse.success(wenDangPZService.getWenDangPZCount(leiBieDM, likeQuery));
    }

    /**
     * 新增文档配置
     */
    @Operation(summary = "添加共享文档访问日志")
    @PostMapping("AddWenDangPZ")
    public MsfResponse<String> addWenDangPZ(@RequestBody SC_ZD_WenDangGreaterDto wenDangGreaterDto) {
        return MsfResponse.success(wenDangPZService.addWenDangPZ(wenDangGreaterDto));
    }
}

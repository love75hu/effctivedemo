package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.gongxiangwd.SC_RZ_FangWenGXWDCreateDto;
import cn.mediinfo.grus.shujuzx.dto.gongxiangwd.SC_RZ_FangWenGXWDto;
import cn.mediinfo.grus.shujuzx.dto.shujuzxfwrz.AddFangWenRZDto;
import cn.mediinfo.grus.shujuzx.dto.shujuzxfwrz.ShuJuZXFWRZDto;
import cn.mediinfo.grus.shujuzx.service.ShuJuZXFWRZService;
import cn.mediinfo.grus.shujuzx.service.ZhuSuoYCZRZService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Tag(name = "ShuJuZXFWRZController", description = "360访问日志")
@RequestMapping({"api/v1.0/ShuJuZXFWRZ", "api/v1/ShuJuZXFWRZ"})
@Slf4j
@Validated
public class ShuJuZXFWRZController {

    private final ShuJuZXFWRZService shuJuZXFWRZService;
    private final ZhuSuoYCZRZService zhuSuoYCZRZService;

    public ShuJuZXFWRZController(ShuJuZXFWRZService shuJuZXFWRZService,
                                 ZhuSuoYCZRZService zhuSuoYCZRZService) {
        this.shuJuZXFWRZService = shuJuZXFWRZService;
        this.zhuSuoYCZRZService = zhuSuoYCZRZService;
    }

    @Operation(summary = "添加访问日志")
    @PostMapping("AddFangWenRZ")
    public MsfResponse<Boolean> AddFangWenRZ(@RequestBody AddFangWenRZDto addFangWenRZDto) {
        return MsfResponse.success(shuJuZXFWRZService.addFangWenRZ(addFangWenRZDto));
    }

    @Operation(summary = "根据查询条件查询360访问日志列表数量")
    @GetMapping("GetFangWenRZCount")
    public MsfResponse<Integer> getFangWenRZCount(Date fangWenKSRQ, Date fangWenJSRQ, String bingRenID, String xingMing, String fangWenRXM) {
        return MsfResponse.success(shuJuZXFWRZService.getFangWenRZCount(fangWenKSRQ, fangWenJSRQ, bingRenID, xingMing, fangWenRXM));
    }

    @Operation(summary = "根据查询条件查询360访问日志列表")
    @GetMapping("GetFangWenRZList")
    public MsfResponse<List<ShuJuZXFWRZDto>> getFangWenRZList(@RequestParam(required = false) Date fangWenKSRQ,
                                                              @RequestParam(required = false) Date fangWenJSRQ,
                                                              @RequestParam(required = false) String bingRenID,
                                                              @RequestParam(required = false) String xingMing,
                                                              @RequestParam(required = false) String fangWenRXM,
                                                              @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return MsfResponse.success(shuJuZXFWRZService.getFangWenRZList(fangWenKSRQ, fangWenJSRQ, bingRenID, xingMing, fangWenRXM, pageIndex, pageSize));
    }

    /**
     * 获取共享文档访问日志列表
     */
    @Operation(summary = "获取共享文档访问日志列表")
    @GetMapping("GetFangWenGXWDList")
    public MsfResponse<List<SC_RZ_FangWenGXWDto>> getFangWenGXWDList(
            @RequestParam(required = false) Date fangWenRQKS,
            @RequestParam(required = false) Date fangWenRQJS,
            @RequestParam(required = false) String likeQuery,
            @RequestParam(required = false) String fangWenR,
            @RequestParam(required = false, defaultValue = "1") Integer PageIndex,
            @RequestParam(required = false, defaultValue = "10") Integer PageSize) {
        return MsfResponse.success(shuJuZXFWRZService.getFangWenGXWDList(fangWenRQKS, fangWenRQJS, likeQuery, fangWenR, PageIndex, PageSize));
    }

    
    /**
     * 获取共享文档访问日志数量
     */
    @Operation(summary = "获取共享文档访问日志数量")
    @GetMapping("GetFangWenGXWDCount")
    public MsfResponse<Long> getFangWenGXWDCount(
            @RequestParam(required = false) Date fangWenRQKS,
            @RequestParam(required = false) Date fangWenRQJS,
            @RequestParam(required = false) String likeQuery,
            @RequestParam(required = false) String fangWenR) {
        return MsfResponse.success(shuJuZXFWRZService.getFangWenGXWDCount(fangWenRQKS, fangWenRQJS, likeQuery, fangWenR));
    }

    /**
     * 添加共享文档访问日志
     */
    @Operation(summary = "添加共享文档访问日志")
    @PostMapping("AddFangWenGXWD")
    public MsfResponse<String> addFangWenGXWD(@RequestBody SC_RZ_FangWenGXWDCreateDto fangWenGXWCreateDto) {
        return MsfResponse.success(shuJuZXFWRZService.addFangWenGXWD(fangWenGXWCreateDto));
    }

}

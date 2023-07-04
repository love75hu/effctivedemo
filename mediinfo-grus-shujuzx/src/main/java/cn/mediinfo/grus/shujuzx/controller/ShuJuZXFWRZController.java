package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.grus.shujuzx.dto.ShuJuZXFWRZ.AddFangWenRZDto;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXFWRZ.ShuJuZXFWRZDto;
import cn.mediinfo.grus.shujuzx.service.ShuJuZXFWRZService;
import cn.mediinfo.grus.shujuzx.service.ZhuSuoYCZRZService;
import cn.mediinfo.starter.base.response.MsfResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping({"api/v1.0/ShuJuZXFWRZ", "api/v1/ShuJuZXFWRZ"})
@Slf4j
@Validated
public class ShuJuZXFWRZController {

    private final ShuJuZXFWRZService shuJuZXFWRZService;
    private final ZhuSuoYCZRZService zhuSuoYCZRZService;

    public ShuJuZXFWRZController(ShuJuZXFWRZService shuJuZXFWRZService,
                                 ZhuSuoYCZRZService zhuSuoYCZRZService)
    {
        this.shuJuZXFWRZService=shuJuZXFWRZService;
        this.zhuSuoYCZRZService=zhuSuoYCZRZService;
    }
    @Operation(summary = "添加访问日志")
    @PostMapping("AddFangWenRZ")
    public MsfResponse<Boolean> AddFangWenRZ(@RequestBody AddFangWenRZDto addFangWenRZDto)
    {
        return MsfResponse.success(shuJuZXFWRZService.addFangWenRZ(addFangWenRZDto));
    }

    @Operation(summary = "根据查询条件查询360访问日志列表数量")
    @GetMapping("GetFangWenRZCount")
    public MsfResponse<Integer> getFangWenRZCount(Date fangWenKSRQ, Date fangWenJSRQ, String bingRenID, String xingMing, String fangWenRXM) {
        return MsfResponse.success(shuJuZXFWRZService.getFangWenRZCount(fangWenKSRQ, fangWenJSRQ, bingRenID, xingMing, fangWenRXM));
    }

    @Operation(summary = "根据查询条件查询360访问日志列表")
    @GetMapping("GetFangWenRZList")
    public MsfResponse<List<ShuJuZXFWRZDto>> getFangWenRZList(Date fangWenKSRQ, Date fangWenJSRQ, String bingRenID, String xingMing, String fangWenRXM) {
        // todo:规定统一定义常量、枚举？
        Integer pageIndex=1;
        Integer pageSize=15;
        return MsfResponse.success(shuJuZXFWRZService.getFangWenRZList(fangWenKSRQ, fangWenJSRQ, bingRenID, xingMing, fangWenRXM,pageIndex, pageSize));
    }



}

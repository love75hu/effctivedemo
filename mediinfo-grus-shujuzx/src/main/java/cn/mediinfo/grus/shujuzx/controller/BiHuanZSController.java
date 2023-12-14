package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.bihuanzs.BiHuanGNDPZ;
import cn.mediinfo.grus.shujuzx.dto.bihuanzs.BiHuanRCXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuanzs.BiHuanXQDto;
import cn.mediinfo.grus.shujuzx.dto.bihuanzs.ZiBiHXQDto;
import cn.mediinfo.grus.shujuzx.service.BiHuanZSService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "BiHuanZSController", description = "闭环展示")
@RequestMapping({"api/v1.0/BiHuanZS", "api/v1/BiHuanZS"})
@Slf4j
@Validated
public class BiHuanZSController {

     private final BiHuanZSService biHuanZSService;

    public BiHuanZSController(BiHuanZSService biHuanZSService) {
        this.biHuanZSService = biHuanZSService;
    }

    @Operation(summary = "根据闭环功能点和相关入参获取闭环详情")
    @PostMapping("getBiHuanXQ")
    public MsfResponse<BiHuanXQDto> getBiHuanXQ(@RequestBody BiHuanGNDPZ biHuanGNDPZ) throws YuanChengException
    {
        return MsfResponse.success(biHuanZSService.getBiHuanXQ(biHuanGNDPZ));
    }

    @Operation(summary = "根据闭环ID和组织机构ID获取子闭环详情")
    @PostMapping("getZiBiHXQ")
    public MsfResponse<BiHuanXQDto> getZiBiHXQ(@RequestBody ZiBiHXQDto ziBiHXQDto) throws YuanChengException
    {
        return MsfResponse.success(biHuanZSService.getBiHuanZXJG(ziBiHXQDto.getBiHuanID(),ziBiHXQDto.getZiBiHDCZXBZ(),ziBiHXQDto.getJieDianID(),ziBiHXQDto.getZuZhiJGID(),ziBiHXQDto.getRuCanList()));
    }

    @Operation(summary = "获取闭环入参信息列表")
    @GetMapping("getBiHuanRCXXList")
    public MsfResponse<List<BiHuanRCXXDto>>  getBiHuanRCXXList(@RequestParam List<String> biHuanIDs )
    {
        return MsfResponse.success();
    }
}

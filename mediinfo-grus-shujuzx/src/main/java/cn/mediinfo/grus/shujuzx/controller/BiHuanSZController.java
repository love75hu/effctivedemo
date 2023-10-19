package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.KeXuanZDDto;
import cn.mediinfo.grus.shujuzx.service.BiHuanSTMXService;
import cn.mediinfo.grus.shujuzx.service.JiBenXXService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "BiHuanSZController", description = "闭环设置")
@RequestMapping({"api/v1.0/BiHuanSZ", "api/v1/BiHuanSZ"})
@Slf4j
@Validated
public class BiHuanSZController {

    //闭环基本信息
    private final JiBenXXService jiBenXXService;

    private final BiHuanSTMXService biHuanSTMXService;


    public BiHuanSZController(JiBenXXService jiBenXXService, BiHuanSTMXService biHuanSTMXService) {
        this.jiBenXXService = jiBenXXService;
        this.biHuanSTMXService = biHuanSTMXService;
    }

    @Operation(summary = "获取闭环信息")
    @GetMapping("getBiHuanJBXXTree")
    public MsfResponse<String> getBiHuanJBXXTree(String zuZhiJGID,String likeQuery)
    {
        return MsfResponse.success();

    }
    @Operation(summary = "添加闭环信息")
    @PostMapping("addBiHuanJBXX")
    public MsfResponse<Boolean> addBiHuanJBXX()
    {
        return MsfResponse.success();
    }
    @Operation(summary = "获取入参字段信息")
    @GetMapping("getRuChanZDXX")
    public MsfResponse<List<KeXuanZDDto>> getRuChanZDXX(@NotEmpty(message = "闭环类型代码不能为空")  String biHuanLXDM)
    {
        return MsfResponse.success(biHuanSTMXService.getRuChanZDXX(biHuanLXDM));
    }


}

package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.JieDianNRDto;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.KeXuanZDDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.AddBiHuanSZXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.AddBiHuanXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.BiHuanJBXXTreeDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.BiHuanSZXXDto;
import cn.mediinfo.grus.shujuzx.service.BiHuanSTJDMXService;
import cn.mediinfo.grus.shujuzx.service.BiHuanSTMXService;
import cn.mediinfo.grus.shujuzx.service.JiBenXXService;
import cn.mediinfo.grus.shujuzx.service.JieDianXXService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    private final BiHuanSTJDMXService biHuanSTJDMXService;

    private final JieDianXXService jieDianXXService;


    public BiHuanSZController(JiBenXXService jiBenXXService, BiHuanSTMXService biHuanSTMXService, BiHuanSTJDMXService biHuanSTJDMXService, JieDianXXService jieDianXXService) {
        this.jiBenXXService = jiBenXXService;
        this.biHuanSTMXService = biHuanSTMXService;
        this.biHuanSTJDMXService = biHuanSTJDMXService;
        this.jieDianXXService = jieDianXXService;
    }

    @Operation(summary = "获取闭环信息")
    @GetMapping("getBiHuanJBXXTree")
    public MsfResponse<List<BiHuanJBXXTreeDto>> getBiHuanJBXXTree(String zuZhiJGID, String likeQuery)
    {
        return MsfResponse.success(jiBenXXService.getBiHuanJBXXTree(zuZhiJGID,likeQuery));

    }
    @Operation(summary = "添加闭环信息")
    @PostMapping("addBiHuanJBXX")
    public MsfResponse<Boolean> addBiHuanJBXX(@RequestBody @Validated AddBiHuanXXDto dto)
    {
        return MsfResponse.success(jiBenXXService.addBiHuanJBXX(dto));
    }
    @Operation(summary = "获取入参字段信息")
    @GetMapping("getRuChanZDXX")
    public MsfResponse<List<KeXuanZDDto>> getRuChanZDXX(@NotEmpty(message = "闭环类型代码不能为空")  String biHuanLXDM)
    {
        return MsfResponse.success(biHuanSTMXService.getRuChanZDXX(biHuanLXDM));
    }

    @Operation(summary = "添加入参信息")
    @PostMapping("getBiHuanSZXXByBiHuanID")
    public MsfResponse<String> getBiHuanSZXXByBiHuanID()
    {

                return MsfResponse.success();
    }

    @Operation(summary = "获取入参信息")
    @GetMapping("getRuCanXX")
    public MsfResponse<String> GetBiHuanZDList(String jieDianID)
    {
        return MsfResponse.success();
    }

    @Operation(summary = "获取节点下失效字段")
    @GetMapping("getShiTuJDMXByJieDianID")
    public MsfResponse<List<JieDianNRDto>> getShiTuJDMXByJieDianID(String jieDianID)
    {
        return MsfResponse.success(biHuanSTJDMXService.getShiTuJDMXByJieDianID(jieDianID));
    }

    @Operation(summary = "添加闭环设置信息")
    @PostMapping("addBiHuanSZXX")
    public MsfResponse<String> addBiHuanSZXX(@RequestBody @Validated AddBiHuanSZXXDto dto)
    {
        return MsfResponse.success(jieDianXXService.addBiHuanSZXX(dto));
    }

    @Operation(summary = "根据ID获取闭环节点信息")
    @GetMapping("getBiHuanSZXXBybiHuanID")
    public MsfResponse<List<BiHuanSZXXDto>> getBiHuanSZXXBybiHuanID(@NotEmpty(message = "闭环ID不能为空") String biHuanID)
    {
        return MsfResponse.success(jieDianXXService.getBiHuanSZXXBybiHuanID(biHuanID));
    }




}

package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.BiHuanSTXXDto;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.BiHuanSTXXTree;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.BiHuanSTZDDto;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuXXDto;
import cn.mediinfo.grus.shujuzx.service.impl.BIHuanSTXXServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 闭环管理-节点管理
 */
@RestController
@Tag(name = "JieDianGLController", description = "闭环管理-节点管理")
@RequestMapping({"api/v1.0/JieDianGL", "api/v1/JieDianGL"})
@Slf4j
@Validated
public class JieDianGLController {

    private final BIHuanSTXXServiceImpl biHuanSTXXService;
   public JieDianGLController(BIHuanSTXXServiceImpl biHuanSTXXService) {
       this.biHuanSTXXService = biHuanSTXXService;
   }

    /**
     * 获取闭环视图信息树
     */
    @Operation(summary = "获取闭环视图信息树")
    @GetMapping("getBiHuanSTXXTree")
    public MsfResponse<List<BiHuanSTXXTree>> getBiHuanSTXXTree(String likeQuery)
    {
        return MsfResponse.success(biHuanSTXXService.getBiHuanSTXXTree(likeQuery));
    }

    @Operation(summary = "获取闭环节点基本信息")
    @GetMapping("getBiHuanSTXXByShiTuID")
    public MsfResponse<SC_BH_ShiTuXXDto> getBiHuanSTXXByID (String id) throws WeiZhaoDSJException {
        return MsfResponse.success(biHuanSTXXService.getShiTuXXByID(id));
    }

    @Operation(summary = "获取闭环视图字段")
    @GetMapping("getBiHuanSTZD")
    public MsfResponse<List<BiHuanSTZDDto>> getBiHuanSTZD(String ziDuanMC,String biHuanLXDM,String shuiTuID,String chaXunLXDM,String chaXunZhi)
    {
      return null;
    }

    @Operation(summary = "获取闭环视图字段数量")
    @GetMapping("getBiHuanSTZDCount")
    public MsfResponse<Integer> getBiHuanSTZDCount(String ziDuanMC,String biHuanLXDM,String shuiTuID,String chaXunLXDM,String chaXunZhi)
    {
        return null;
    }

    @Operation(summary = "保存闭环视图信息")
    @PostMapping("addBiHuanSTXX")
    public MsfResponse<Boolean> addBiHuanSTXX(@RequestBody @Validated BiHuanSTXXDto dto) throws WeiZhaoDSJException {
        return MsfResponse.success(biHuanSTXXService.addBiHuanSTXX(dto));
    }



}

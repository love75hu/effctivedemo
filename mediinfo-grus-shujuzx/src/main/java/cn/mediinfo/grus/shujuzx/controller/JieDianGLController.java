package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.*;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.KeXuanJDDto;
import cn.mediinfo.grus.shujuzx.service.impl.BIHuanSTXXServiceImpl;
import cn.mediinfo.grus.shujuzx.service.impl.BiHuanSTJDGXServiceImpl;
import cn.mediinfo.grus.shujuzx.service.impl.BiHuanSTJDXXServiceImpl;
import cn.mediinfo.grus.shujuzx.service.impl.BiHuanSTMXServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.UpdateTimestamp;
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
    private final BiHuanSTMXServiceImpl biHuanSTMXService;
    private final BiHuanSTJDXXServiceImpl biHuanSTJDXXService;
    private final BiHuanSTJDGXServiceImpl biHuanSTJDGXService;

   public JieDianGLController(BIHuanSTXXServiceImpl biHuanSTXXService, BiHuanSTMXServiceImpl biHuanSTMXService, BiHuanSTJDXXServiceImpl biHuanSTJDXXService, BiHuanSTJDGXServiceImpl biHuanSTJDGXService) {
       this.biHuanSTXXService = biHuanSTXXService;
       this.biHuanSTMXService = biHuanSTMXService;
       this.biHuanSTJDXXService = biHuanSTJDXXService;
       this.biHuanSTJDGXService = biHuanSTJDGXService;
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
    @GetMapping("getBiHuanSTXXByID")
    public MsfResponse<SC_BH_ShiTuXXDto> getBiHuanSTXXByID (@NotEmpty(message = "id不能为空") String id) throws WeiZhaoDSJException {
        return MsfResponse.success(biHuanSTXXService.getShiTuXXByID(id));
    }

    @Operation(summary = "获取闭环视图字段")
    @GetMapping("getBiHuanSTZD")
    public MsfResponse<List<BiHuanSTZDDto>> getBiHuanSTZD( String shiTuID,String ziDuanMC,
    String biHuanLXDM,
                                                          Integer chaXunLXDM, Integer pageIndex, Integer pageSize)
    {
      return MsfResponse.success(biHuanSTMXService.getBiHuanSTZD(shiTuID,ziDuanMC,biHuanLXDM,chaXunLXDM,pageIndex,pageSize));
    }

    @Operation(summary = "获取闭环视图字段数量")
    @GetMapping("getBiHuanSTZDCount")
    public MsfResponse<Integer> getBiHuanSTZDCount(String shiTuID, String ziDuanMC, String biHuanLXDM,Integer chaXunLXDM)
    {
        return MsfResponse.success(biHuanSTMXService.getBiHuanSTZDCount(shiTuID,ziDuanMC,biHuanLXDM,chaXunLXDM));
    }

    @Operation(summary = "保存闭环视图信息")
    @PostMapping("addBiHuanSTXX")
    public MsfResponse<String> addBiHuanSTXX(@RequestBody @Validated BiHuanSTXXDto dto) throws WeiZhaoDSJException {
        return MsfResponse.success(biHuanSTXXService.addBiHuanSTXX(dto));
    }

    @Operation(summary = "编辑闭环视图信息")
    @PutMapping("updateBiHuanSTXX")
    public MsfResponse<Boolean> updateBiHuanSTXX(@RequestBody @Validated BiHuanSTXXDto dto) throws WeiZhaoDSJException {
        return MsfResponse.success(biHuanSTXXService.updateBiHuanSTXX(dto));
    }


    @Operation(summary = "作废闭环视图信息")
    @DeleteMapping("zuoFeiBHSTXX")
    public MsfResponse<Boolean> zuoFeiBHSTXX(@NotEmpty(message = "id不能为空") @RequestParam String id) throws WeiZhaoDSJException {
        return MsfResponse.success(biHuanSTXXService.zuoFeiBHSTXX(id));
    }
    @Operation(summary = "添加闭环视图节点字段")
    @PostMapping("addBiHuanSTJDMX")
    public MsfResponse<Boolean> addBiHuanSTJDMX(@RequestBody @Validated List<AddBiHuanSTJDMXDto> dto) throws WeiZhaoDSJException {
        return MsfResponse.success(biHuanSTMXService.addBiHuanSTJDMX(dto));
    }
    @Operation(summary = "移除闭环视图节点字段")
    @DeleteMapping("zuoFeiBHSTJDMX")
    public MsfResponse<Boolean> zuoFeiBHSTJDMX(@NotEmpty(message = "id不能为空") @RequestParam String id){
        return MsfResponse.success(biHuanSTMXService.delectBiHuanSTZDByID(id));
    }
    @Operation(summary = "编辑保存闭环视图节点字段")
    @PostMapping("saveBiHuanSTJDMX")
    public MsfResponse<Boolean> saveBiHuanSTJDMX(@RequestBody @Validated SaveBiHuanSTJDMXDto dto) throws WeiZhaoDSJException {
        return MsfResponse.success(biHuanSTMXService.saveBiHuanSTJDMX(dto));
    }

    @Operation(summary = "根据id 获取闭环视图节点")
    @GetMapping("getBiHuanSTJDByID")
    public MsfResponse<BiHuanSTJDDto> getBiHuanSTJDByID(@NotEmpty(message = "id不能为空") String id) throws WeiZhaoDSJException {
        return MsfResponse.success(biHuanSTJDXXService.getBiHuanSTJDByID(id));
    }
    @Operation(summary = "获取事件信息接口")
    @GetMapping("getShiJianXX")
    public MsfResponse<List<ShiJianXXDto>> getShiJianXX(@NotEmpty(message = "id不能为空")  String shiTuID) throws WeiZhaoDSJException, YuanChengException { //BiHuanSTMXService
        return MsfResponse.success(biHuanSTMXService.getShiJianXX(shiTuID));
    }
    @Operation(summary = "获取关系节点")
    @GetMapping("getGuanLianJDXX")
    public MsfResponse<List<GuanLianJDDto>> getGuanLianJDXX(@NotEmpty(message = "视图id")  String shiTuID,  String jieDianID)
    {
        return MsfResponse.success(biHuanSTJDXXService.getGuanLianJDXX(shiTuID,shiTuID));
    }
    @Operation(summary = "获取视图字段接口")
    @GetMapping("getShiTUZDXX")
    public MsfResponse<List<KeXuanZDDto>> getShiTUZDXX(@NotEmpty(message = "视图id")  String shiTuID)
    {
        return MsfResponse.success(biHuanSTMXService.getShiTUZDXX(shiTuID));
    }

    @Operation(summary = "新增闭环视图节点")
    @PostMapping("addBiHuanSTJD")
    public MsfResponse<Boolean> addBiHuanSTJD(@RequestBody  BiHuanSTJDDto dto) throws WeiZhaoDSJException {

        return MsfResponse.success(biHuanSTJDXXService.addBiHuanSTJD(dto));
    }

    @Operation(summary = "编辑闭环视图节点")
    @PutMapping("updateBiHuanSTJD")
    public MsfResponse<Boolean> updateBiHuanSTJD(@RequestBody  BiHuanSTJDDto dto) throws WeiZhaoDSJException {
        return MsfResponse.success(biHuanSTJDXXService.updateBiHuanSTJD(dto));
    }
    @Operation(summary = "获取节点列表")
    @GetMapping("getBiHuanJDXXList")
    public MsfResponse<List<BiHuanJDXXListDto>> getBiHuanJDXXList(String shiTuID,
                                                                  String biHuanLXDM,
                                                                  String jieDianMC,
                                                                  Integer qiYongBZ,
                                                                  Integer pageIndex,
                                                                  Integer pageSize)
    {
        return MsfResponse.success(biHuanSTJDXXService.getBiHuanJDXXList(shiTuID,biHuanLXDM,jieDianMC,qiYongBZ,pageIndex,pageSize));
    }

    @Operation(summary = "获取节点列表数量")
    @GetMapping("getBiHuanJDXXCount")
    public MsfResponse<Integer> getBiHuanJDXXCount( String shiTuID,
                                                   String biHuanLXDM,
                                                   String jieDianMC,
                                                   Integer qiYongBZ)
    {
        return MsfResponse.success(biHuanSTJDXXService.getBiHuanJDXXCount(shiTuID,biHuanLXDM,jieDianMC,qiYongBZ));
    }

    @Operation(summary = "获取可选节点内容")
    @GetMapping("getKeXuanJDBybiHuanLXDM")
    public MsfResponse<List<KeXuanJDDto>> getKeXuanJDBybiHuanLXDM(@NotEmpty(message = "闭环类型代码")  String biHuanLXDM)
    {
        return MsfResponse.success(biHuanSTJDXXService.getKeXuanJDBybiHuanLXDM(biHuanLXDM));

    }
    @Operation(summary = "节点启用标志")
    @PutMapping("updateJieDianQYBZ")
    public MsfResponse<Boolean> updateJieDianQYBZ( @RequestParam @NotEmpty(message ="id") String id,@RequestParam Integer qiYongBZ)
    {
        return MsfResponse.success(biHuanSTJDXXService.updateJieDianQYBZ(id,qiYongBZ));
    }
    @Operation(summary = "获取闭环视图节点")
    @GetMapping("getBiHuanSTJD")
    public MsfResponse<List<BiHuanSTJDXXDto>> getBiHuanSTJD(@NotEmpty(message = "闭环类型代码不能为空")  String biHuanLXDM)
    {
        return MsfResponse.success(biHuanSTJDXXService.getBiHuanSTJD(biHuanLXDM));
    }

    @Operation(summary = "获取闭环类型下字段")
    @GetMapping("getBiHuanSTZDBybiHuanLXDM")
    public MsfResponse<List<BiHuanSTZDDto>> getBiHuanSTZDBybiHuanLXDM(@NotEmpty(message = "闭环类型代码不能为空") String biHuanLXDM)
    {
    return MsfResponse.success(biHuanSTMXService.getBiHuanSTZDBybiHuanLXDM(biHuanLXDM));
    }

















}

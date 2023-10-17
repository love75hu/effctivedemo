package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.*;
import cn.mediinfo.grus.shujuzx.service.ShiTuMXService;
import cn.mediinfo.grus.shujuzx.service.ShiTuXXService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 综合查询-临床检索配置
 */
@RestController
@Tag(name = "linChuangJSPZController", description = "综合查询-临床检索配置")
@RequestMapping({"api/v1.0/linChuangJSPZ", "api/v1/linChuangJSPZ"})
@Slf4j
@Validated
public class linChuangJSPZController {

    private final ShiTuXXService shiTuXXService;
    private final ShiTuMXService shiTuMXService;


    public linChuangJSPZController(ShiTuXXService shiTuXXService, ShiTuMXService shiTuMXService) {
        this.shiTuXXService = shiTuXXService;
        this.shiTuMXService = shiTuMXService;
    }

    /**
     * 获取临床检索树
     */
    @Operation(summary = "获取临床检索树")
    @GetMapping("getLinChuangJSSTTree")
    public MsfResponse<List<LinChuangJSSTDtoTree>> getLinChuangJSSTTree(
            @RequestParam(required = false) String fuLeiID,
            @RequestParam(required = false) String likeQuery)
    {
        return MsfResponse.success(shiTuXXService.getLinChuangJSSTTree(fuLeiID,likeQuery));
    }
    /**
     * 新增视图分类
     */
    @Operation(summary = "新增视图分类")
    @PostMapping("addShiTuFL")
    public MsfResponse<String> addShiTuFL(@RequestBody @Validated  ShiTuFLDto dto) throws TongYongYWException, WeiZhaoDSJException {
        return MsfResponse.success(shiTuXXService.addShiTuFL(dto));

    }
    /**
     * 编辑视图分类
     */
    @Operation(summary = "编辑视图分类")
    @PutMapping("updateShiTuFL")
    public MsfResponse<Boolean> updateShiTuFL(@RequestBody @Validated ShiTuFLDto dto) throws TongYongYWException {
        return MsfResponse.success(shiTuXXService.updateShiTuFL(dto));
    }
    /**
     * 获取视图分类列表
     */
    @Operation(summary = "获取视图分类列表")
    @GetMapping("getShiTuFLList")
    public MsfResponse<List<ShiTuFLDto>> getShiTuFLList( @RequestParam(required = false,defaultValue = "0") String fuLeiID, String likeQuery)
    {
        return MsfResponse.success(shiTuXXService.getShiTuFLList(fuLeiID,likeQuery));
    }

    /**
     * 新增临床检索视图信息
     */
    @Operation(summary = "根据主键获取视图分类")
    @GetMapping("getShiTuFLByID")
    public MsfResponse<ShiTuFLDto> getShiTuFLByID(String id) throws WeiZhaoDSJException {
        return MsfResponse.success(shiTuXXService.getShiTuFLByID(id));
    }

    /**
     * 编辑视图分类
     */
    @Operation(summary = "新增临床检索视图信息")
    @PostMapping("addShiTuXX")
    public MsfResponse<String> addShiTuXX(@RequestBody @Validated ShiTuXXDto dto)
    {
        return MsfResponse.success(shiTuXXService.addShiTuXX(dto));
    }
    @Operation(summary = "根据主键获取视图信息")
    @GetMapping("getShiTuXXByID")
    public MsfResponse<SC_CX_ShiTuXXDto>getShiTuXXByID(@NotEmpty(message = "id不能为空")  String id) throws MsfResponseException, WeiZhaoDSJException
    {
        return MsfResponse.success(shiTuXXService.getShiTuXXByID(id));
    }


    /**
     * 编辑视图信息
     */
    @Operation(summary = "编辑视图信息")
    @PostMapping("updateShiTuXX")
    public MsfResponse<Boolean> updateShiTuXX(@RequestBody @Validated SC_CX_ShiTuXXDto dto) throws TongYongYWException {
        return MsfResponse.success(shiTuXXService.updateShiTuXX(dto));
    }

    /**
     * 批量新增视图字段
     */
    @Operation(summary = "批量新增视图字段")
    @PostMapping("addShiTuMX")
    public MsfResponse<Boolean> addShiTuMX(@RequestBody @Validated AddShiTuMXDto dto) throws WeiZhaoDSJException, MsfResponseException {
        return MsfResponse.success(shiTuMXService.addShiTuMX(dto));
    }

    @Operation(summary = "根据视图id获取视图字段信息列表")
    @GetMapping("getShiTuMXList")
    public MsfResponse<List<ShiTuMXListDto>>  getShiTuMXList(String shiTuID,  String fuLeiID,
                                                             String likeQuery, Integer chaXunLX, Integer pageIndex, Integer pageSize)
    {
        return MsfResponse.success(shiTuMXService.getShiTuMXList(shiTuID,fuLeiID,likeQuery,chaXunLX,pageIndex,pageSize));
    }
    @Operation(summary = "根据视图id获取视图字段信息列表数量")
    @GetMapping("getShiTuMXCount")
    public MsfResponse<Integer> getShiTuMXCount(String shiTuID,String fuLeiID, String likeQuery, Integer chaXunLX)
    {
        return MsfResponse.success(shiTuMXService.getShiTuMXCount(shiTuID,fuLeiID,likeQuery,chaXunLX));
    }

    @Operation(summary = "获取关联条件字段")
    @GetMapping("getGuanLianTJZD")
    public MsfResponse<List<GuanLianTJZD>> getGuanLianTJZD(@NotEmpty(message = "视图id不能为空") String shiTuSTID)
    {
        return MsfResponse.success(shiTuMXService.getGuanLianTJZD(shiTuSTID));
    }

    @Operation(summary = "根据主键编辑视图字段")
    @GetMapping("getShiTuMXByID")
    public MsfResponse<ShiTuMXDto> getShiTuMXByID(@NotEmpty(message = "视图id不能为空") String id) throws WeiZhaoDSJException {
        return MsfResponse.success(shiTuMXService.getShiTuMXGXByID(id));
    }


    @Operation(summary = "编辑视图字段")
    @PutMapping("updateShiTuMX")
    public MsfResponse<Boolean> updateShiTuMX(@RequestBody @Validated UpdateShiTuMXDto dto) throws WeiZhaoDSJException {
        return MsfResponse.success(shiTuMXService.updateShiTuMX(dto));
    }

    @Operation(summary = "作废视图分类")
    @PutMapping("zuoFeiShiTFL")
    public  MsfResponse<Boolean> zuoFeiShiTFL(@RequestParam @NotEmpty(message = "id不能为空") String id) throws WeiZhaoDSJException {
        return MsfResponse.success(shiTuXXService.zuoFeiShiTuFL(id));
    }

    @Operation(summary = "作废视图明细")
    @PutMapping("zuoFeiShiTMX")
    public MsfResponse<Boolean> zuoFeiShiTMX(@RequestParam @NotEmpty(message = "id不能为空")  String id) throws WeiZhaoDSJException {
        return MsfResponse.success(shiTuMXService.zuoFeiShiTMX(id));
    }
}

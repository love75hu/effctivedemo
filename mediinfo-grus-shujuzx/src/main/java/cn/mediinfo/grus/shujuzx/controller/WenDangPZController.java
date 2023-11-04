package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.cyan.msf.core.response.XiTongResponseCode;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangDto;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangGreaterDto;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangMBDto;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangUpDateDto;
import cn.mediinfo.grus.shujuzx.service.WenDangPZService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
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
    @Operation(summary = "添加文档配置")
    @PostMapping("AddWenDangPZ")
    public MsfResponse<String> addWenDangPZ(@RequestBody SC_ZD_WenDangGreaterDto wenDangGreaterDto) throws TongYongYWException {
        if (!StringUtils.hasText(wenDangGreaterDto.getWenDangID()) || !StringUtils.hasText(wenDangGreaterDto.getWenDangMC())) {
            return MsfResponse.fail(XiTongResponseCode.CANSHUYC, "文档ID和文档名称不可为空！");
        }
        return MsfResponse.success(wenDangPZService.addWenDangPZ(wenDangGreaterDto));
    }

    /**
     * 编辑文档配置
     */
    @Operation(summary = "编辑文档配置")
    @PostMapping("UpDateWenDangPZ")
    public MsfResponse<Boolean> UpDateWenDangPZ(@RequestBody SC_ZD_WenDangUpDateDto wenDangUpDateDto) throws TongYongYWException {
        if (!StringUtils.hasText(wenDangUpDateDto.getId()) ||!StringUtils.hasText(wenDangUpDateDto.getWenDangID()) || !StringUtils.hasText(wenDangUpDateDto.getWenDangMC())) {
            return MsfResponse.fail(XiTongResponseCode.CANSHUYC, "主键ID，文档ID，文档名称不可为空！");
        }
        return MsfResponse.success(wenDangPZService.UpDateWenDangPZ(wenDangUpDateDto));
    }

    /**
     * 根据文档ID获取模板内容
     */
    @Operation(summary = "根据文档ID获取模板内容")
    @GetMapping("GetWenDangMBXX")
    public MsfResponse<SC_ZD_WenDangMBDto> getWenDangMBXX(@RequestParam(required = false) String wenDangID)  throws TongYongYWException{
        return MsfResponse.success(wenDangPZService.getWenDangMBXX(wenDangID));
    }
}

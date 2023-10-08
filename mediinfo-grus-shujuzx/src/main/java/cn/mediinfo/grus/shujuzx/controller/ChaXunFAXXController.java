package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.chaxunfaxx.FangAnCXLSDto;
import cn.mediinfo.grus.shujuzx.dto.chaxunfaxx.FangAnXXDto;
import cn.mediinfo.grus.shujuzx.service.ChaXunFAXXService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "ChaXunFAXXController", description = "查询方案")
@RequestMapping({"api/v1.0/chaXunFAXX", "api/v1/chaXunFAXX"})
@Slf4j
@Validated
public class ChaXunFAXXController {
    private final ChaXunFAXXService chaXunFAXXService;

    public ChaXunFAXXController(ChaXunFAXXService chaXunFAXXService) {
        this.chaXunFAXXService = chaXunFAXXService;
    }

    /**
     * 搜索查询方案下拉选择
     *
     */
    @Operation(summary = "搜索查询方案下拉选择")
    @GetMapping("GetChaXunFAXXSelect")
    public MsfResponse<List<FangAnXXDto>> getChaXunFAXXSelect(@RequestParam(required = false) String likeQuery,
                                                              @RequestParam(required = false) String fangAnLXDM,
                                                              @RequestParam(required = false,defaultValue = "1") Integer pageIndex,
                                                              @RequestParam(required = false,defaultValue = "10") Integer pageSize) {
        return MsfResponse.success(chaXunFAXXService.getChaXunFAXXSelect(likeQuery, fangAnLXDM, pageIndex, pageSize));
    }
    /**
     * 获取方案信息记录
     *
     */
    @Operation(summary = "获取方案信息记录")
    @GetMapping("GetFangAnXXList")
    public MsfResponse<List<FangAnXXDto>> getFangAnXXList(@RequestParam(required = false) String likeQuery,
                                                          @RequestParam(required = false,defaultValue = "1") Integer pageIndex,
                                                          @RequestParam(required = false,defaultValue = "10") Integer pageSize) {
        return MsfResponse.success(chaXunFAXXService.getFangAnXXList(likeQuery, pageIndex, pageSize));
    }
    /**
     * 获取方案查询历史记录
     *
     */
    @Operation(summary = "获取方案查询历史记录")
    @GetMapping("GetFangAnCXLSList")
    public MsfResponse<List<FangAnCXLSDto>> getFangAnCXLSList(@RequestParam(required = false,defaultValue = "1") Integer pageIndex,
                                                              @RequestParam(required = false,defaultValue = "10") Integer pageSize) {
        return MsfResponse.success(chaXunFAXXService.getFangAnCXLSList(pageIndex, pageSize));
    }
    /**
     * 作废查询方案历史
     *
     */
    @Operation(summary = "作废查询方案历史")
    @DeleteMapping("DeleteFangAnCXLS")
    public MsfResponse<Boolean> deleteFangAnCXLS(@RequestParam String id) {
        return MsfResponse.success(chaXunFAXXService.deleteFangAnCXLS(id));
    }

    /**
     * 作废查询方案
     *
     */
    @Operation(summary = "作废查询方案")
    @DeleteMapping("DeleteChaXunFA")
    public MsfResponse<Boolean> deleteChaXunFA(@RequestParam String id) {
        return MsfResponse.success(chaXunFAXXService.deleteChaXunFA(id));
    }
}

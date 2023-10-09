package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.chaxunfaxx.FangAnCXLSDto;
import cn.mediinfo.grus.shujuzx.dto.chaxunfaxx.FangAnXXDto;
import cn.mediinfo.grus.shujuzx.dto.fangan.FangAnQueryDTO;
import cn.mediinfo.grus.shujuzx.request.FangAnCXLSSaveRequest;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnQuerySqlRequest;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnXXSaveRequest;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnXXUpdateRequest;
import cn.mediinfo.grus.shujuzx.service.ChaXunFAXXService;
import cn.mediinfo.grus.shujuzx.service.FangAnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "FangAnXXController", description = "方案信息")
@RequestMapping({"api/v1.0/fanganxx", "api/v1/fanganxx"})
@Slf4j
public class FangAnXXController {

    @Autowired
    private FangAnService fangAnService;
    @Autowired
    private ChaXunFAXXService chaXunFAXXService;

    @Operation(summary = "保存方案")
    @PostMapping("/save")
    public MsfResponse<String> saveFangAnXX(@Validated @RequestBody FangAnXXSaveRequest request) {
        return MsfResponse.success(fangAnService.saveFangAn(request));
    }

    @Operation(summary = "获取方案信息")
    @PostMapping("/get")
    public MsfResponse<FangAnQueryDTO> getFangAnXX(@NotBlank(message = "方案id不能为空") String id){
        return MsfResponse.success();
    }

    @Operation(summary = "更新方案")
    @PutMapping("/update")
    public MsfResponse<Void> updateFangAnXX(@Validated @RequestBody FangAnXXUpdateRequest request){
        return MsfResponse.success();
    }

    @Operation(summary = "查询sql")
    @PostMapping("/getSql")
    public MsfResponse<String> getFangAnSql(@Validated @RequestBody FangAnQuerySqlRequest request){
        return MsfResponse.success();
    }


    @Operation(summary = "保存查询记录")
    @PostMapping("/saveQueryRercord")
    public MsfResponse<String> saveFangAnCXLSSaveRequest(@Validated @RequestBody FangAnCXLSSaveRequest request){
        return MsfResponse.success();
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

package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.fangan.FangAnQueryDTO;
import cn.mediinfo.grus.shujuzx.request.FangAnCXLSSaveRequest;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnQuerySqlRequest;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnXXSaveRequest;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnXXUpdateRequest;
import cn.mediinfo.grus.shujuzx.service.FangAnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "FangAnXXController", description = "方案信息")
@RequestMapping({"api/v1.0/fanganxx", "api/v1/fanganxx"})
@Slf4j
public class FangAnXXController {

    @Autowired
    private FangAnService fangAnService;

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
}

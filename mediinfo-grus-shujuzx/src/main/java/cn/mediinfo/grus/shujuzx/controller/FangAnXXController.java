package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.fangan.FangAnQueryDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnXXSaveRequest;

@RestController
@RequestMapping({"api/v1.0/fanganxx", "api/v1/fanganxx"})
@Slf4j
public class FangAnXXController {

    @Operation(summary = "保存方案")
    @PostMapping("/save")
    public MsfResponse<String> saveFangAnXX(@Validated @RequestBody FangAnXXSaveRequest request) {
        return MsfResponse.success();
    }

    @Operation(summary = "获取方案信息")
    @PostMapping("/get")
    public MsfResponse<FangAnQueryDTO> getFangAnXX(@NotBlank(message = "方案id不能为空") String id){
        return MsfResponse.success();
    }
}

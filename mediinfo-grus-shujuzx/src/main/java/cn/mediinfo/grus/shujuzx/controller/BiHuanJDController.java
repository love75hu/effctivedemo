package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.grus.shujuzx.dto.BiHuanJDSZs.SC_ZD_BiHuanJDCreateDto;
import cn.mediinfo.grus.shujuzx.dto.BiHuanJDSZs.SC_ZD_BiHuanJDDto;
import cn.mediinfo.grus.shujuzx.service.BiHuanJDSZService;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.response.MsfResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cn.mediinfo.grus.shujuzx.service.BiHuanJDSZService;

@RestController
@Tag(name = "BiHuanJDController", description = "闭环节点")
@RequestMapping({"api/v1.0/BiHuanJD", "api/v1/BiHuanJD"})
@Slf4j
@Validated
public class BiHuanJDController {

    private final BiHuanJDSZService biHuanJDService;

    public BiHuanJDController(BiHuanJDSZService biHuanJDService) {
        this.biHuanJDService = biHuanJDService;
    }

    @Operation(summary = "根据闭环类型代码获取闭环节点数量")
    @GetMapping("GetBiHuanJDCount")
    MsfResponse<Integer> GetBiHuanJDCount(String biHuanLXDM, String biHuanJDDM) {
        return MsfResponse.success(biHuanJDService.GetBiHuanJDCount(biHuanLXDM, biHuanJDDM));
    }

    /**
     * 新增一个闭环节点
     *
     * @param createDto
     * @return
     */
    @Operation(summary = "新增一个闭环节点")
    @PostMapping("AddBiHuanJD")
    public MsfResponse<SC_ZD_BiHuanJDDto> addBiHuanJD(@RequestBody SC_ZD_BiHuanJDCreateDto createDto) throws TongYongYWException {
        return MsfResponse.success(biHuanJDService.addBiHuanJD(createDto));
    }


}

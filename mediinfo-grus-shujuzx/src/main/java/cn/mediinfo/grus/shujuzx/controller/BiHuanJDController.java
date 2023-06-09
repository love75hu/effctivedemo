package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.grus.shujuzx.dto.BiHuanJDSZs.SC_ZD_BiHuanJDCreateDto;
import cn.mediinfo.grus.shujuzx.dto.BiHuanJDSZs.SC_ZD_BiHuanJDDto;
import cn.mediinfo.grus.shujuzx.dto.BiHuanJDSZs.SC_ZD_BiHuanJDListDto;
import cn.mediinfo.grus.shujuzx.dto.BiHuanJDSZs.SC_ZD_BiHuanJDUpdateDto;
import cn.mediinfo.grus.shujuzx.service.BiHuanJDSZService;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.exception.WeiZhaoDSJException;
import cn.mediinfo.starter.base.response.MsfResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cn.mediinfo.grus.shujuzx.service.BiHuanJDSZService;

import java.util.List;

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

    /**
     * 根据闭环类型代码获取闭环节点列表
     *
     * @param biHuanLXDM
     * @param likeQuery
     * @return
     */
    @Operation(summary = "根据闭环类型代码获取闭环节点列表")
    @GetMapping("GetBiHuanJDList")
    public MsfResponse<List<SC_ZD_BiHuanJDListDto>> getBiHuanJDList(@RequestParam String biHuanLXDM,
                                                                    @RequestParam(required = false) String likeQuery) {
        return MsfResponse.success(biHuanJDService.getBiHuanJDList(biHuanLXDM, likeQuery, 1, 10));
    }

    /**
     * 根据闭环类型代码获取闭环节点数量
     *
     * @param biHuanLXDM
     * @param likeQuery
     * @return
     */
    @Operation(summary = "根据闭环类型代码获取闭环节点数量")
    @GetMapping("GetBiHuanJDCount")
    public MsfResponse<Long> getBiHuanJDCount(@RequestParam String biHuanLXDM,
                                              @RequestParam(required = false) String likeQuery) {
        return MsfResponse.success(biHuanJDService.getBiHuanJDCount(biHuanLXDM, likeQuery));
    }

    /**
     * 新增一个闭环节点
     *
     * @param createDto
     * @return
     * @throws TongYongYWException
     */
    @Operation(summary = "新增一个闭环节点")
    @PostMapping("AddBiHuanJD")
    public MsfResponse<SC_ZD_BiHuanJDDto> addBiHuanJD(@RequestBody SC_ZD_BiHuanJDCreateDto createDto) throws TongYongYWException {
        return MsfResponse.success(biHuanJDService.addBiHuanJD(createDto));
    }

    /**
     * 更新一个闭环节点
     *
     * @param updateDto
     * @return
     * @throws TongYongYWException
     * @throws WeiZhaoDSJException
     */
    @Operation(summary = "更新一个闭环节点")
    @PutMapping("UpdateBiHuanJD")
    public MsfResponse<SC_ZD_BiHuanJDDto> updateBiHuanJD(@RequestBody SC_ZD_BiHuanJDUpdateDto updateDto) throws TongYongYWException, WeiZhaoDSJException {
        return MsfResponse.success(biHuanJDService.updateBiHuanJD(updateDto));
    }

    /**
     * 根据ID获取闭环节点信息
     *
     * @param id
     * @return
     */
    @Operation(summary = "根据ID获取闭环节点信息")
    @GetMapping("GetBiHuanJDByID")
    public MsfResponse<SC_ZD_BiHuanJDDto> getBiHuanJDByID(String id) {
        return MsfResponse.success(biHuanJDService.getBiHuanJDByID(id));
    }

    /**
     * 作废一条闭环节点
     *
     * @param id
     * @return
     * @throws WeiZhaoDSJException
     */
    @Operation(summary = "作废一条闭环节点")
    @DeleteMapping("ZuoFeiBiHuanJD")
    public MsfResponse<Boolean> zuoFeiBiHuanJD(String id) throws WeiZhaoDSJException {
        return MsfResponse.success(biHuanJDService.zuoFeiBiHuanJD(id));
    }

    /**
     * 根据闭环类型获取闭环节点
     *
     * @param biHuanLXDM
     * @param zhuYuanSYBZ
     * @param menZhenSYBZ
     * @param jiZhenSYBZ
     * @param tiJianSYBZ
     * @return
     */
    @Operation(summary = "根据闭环类型获取闭环节点")
    @GetMapping("GetBiHuanJDByBHLX")
    public MsfResponse<List<SC_ZD_BiHuanJDListDto>> getBiHuanJDByBHLX(@RequestParam String biHuanLXDM,
                                                                      @RequestParam(required = false) Integer zhuYuanSYBZ,
                                                                      @RequestParam(required = false) Integer menZhenSYBZ,
                                                                      @RequestParam(required = false) Integer jiZhenSYBZ,
                                                                      @RequestParam(required = false) Integer tiJianSYBZ) {
        return MsfResponse.success(biHuanJDService.getBiHuanJDByBHLX(biHuanLXDM, zhuYuanSYBZ, menZhenSYBZ, jiZhenSYBZ, tiJianSYBZ));
    }
}

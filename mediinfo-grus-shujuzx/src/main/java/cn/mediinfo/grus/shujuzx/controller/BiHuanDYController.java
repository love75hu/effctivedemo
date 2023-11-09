package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.bihuandy.SC_BH_DiaoYongPZAddDto;
import cn.mediinfo.grus.shujuzx.dto.bihuandy.SC_BH_DiaoYongPZDto;
import cn.mediinfo.grus.shujuzx.dto.bihuandy.SC_BH_DiaoYongPZUpdateDto;
import cn.mediinfo.grus.shujuzx.dto.bihuandy.ShiTuMXBHPZDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_JIBENXXDto;
import cn.mediinfo.grus.shujuzx.service.BiHuanDYService;
import cn.mediinfo.grus.shujuzx.service.JiBenXXService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "BiHuanDYController", description = "闭环调用")
@RequestMapping({"api/v1.0/BiHuanDY", "api/v1/BiHuanDY"})
@Slf4j
@Validated
public class BiHuanDYController {
    private final BiHuanDYService _biHuanDYService;
    private final JiBenXXService _jiBenXXService;

    public BiHuanDYController(BiHuanDYService biHuanDYService, JiBenXXService jiBenXXService) {
        this._biHuanDYService = biHuanDYService;
        this._jiBenXXService = jiBenXXService;
    }

    /**
     * 获取视图明细
     * @param biHuanLXDM
     * @param likeQuery
     * @return
     * @throws YuanChengException
     */
    @Operation(summary = "获取视图明细")
    @GetMapping("getShuTuMXForBHPZ")
    public MsfResponse<List<ShiTuMXBHPZDto>> getShuTuMXForBHPZ(@RequestParam(required = false) String biHuanLXDM, @RequestParam(required = false) String likeQuery) throws YuanChengException {
        if (biHuanLXDM == null) {
            return MsfResponse.fail("闭环类型代码不允许为空!");
        }
        List<ShiTuMXBHPZDto> shiTuMXBHPZs = _biHuanDYService.getShuTuMXForBHPZ(biHuanLXDM, likeQuery);
        return MsfResponse.success(shiTuMXBHPZs);
    }

    /**
     * 获取闭环列表
     * @param likeQuery
     * @return
     */
    @Operation(summary = "获取闭环列表")
    @GetMapping("getBiHuanList")
    public MsfResponse<List<SC_BH_JIBENXXDto>> getBiHuanList(@RequestParam(required = false) String likeQuery) {
        return MsfResponse.success(_jiBenXXService.getBiHuanJBXXList(likeQuery));
    }

    /**
     * 获取闭环配置列表
     * @param gongNengDDM
     * @return
     */
    @Operation(summary = "获取闭环配置列表")
    @GetMapping("getBiHuanPZList")
    public MsfResponse<List<SC_BH_DiaoYongPZDto>> getBiHuanPZList(@RequestParam(required = false) String gongNengDDM) {

        return MsfResponse.success(_biHuanDYService.getBiHuanPZList(gongNengDDM));
    }

    /**
     * 根据id获取闭环配置信息
     * @param id
     * @return
     */
    @Operation(summary = "根据id获取闭环配置信息")
    @GetMapping("getBiHuanPZXXByID")
    public MsfResponse<SC_BH_DiaoYongPZDto> getBiHuanPZXXByID(String id) {
        return MsfResponse.success(_biHuanDYService.getBiHuanPZXXByID(id));
    }

    /**
     * 新增闭环配置
     * @param addDto
     * @return
     */
    @Operation(summary = "新增闭环配置")
    @PostMapping("addBiHuanPZ")
    public MsfResponse<SC_BH_DiaoYongPZDto> addBiHuanPZ(@RequestBody SC_BH_DiaoYongPZAddDto addDto) {
        return MsfResponse.success(_biHuanDYService.addBiHuanPZ(addDto));
    }
    /**
     * 编辑闭环配置
     * @param updateDto
     * @return
     */
    @Operation(summary = "编辑闭环配置")
    @PutMapping("updateBiHuanPZ")
    public MsfResponse<SC_BH_DiaoYongPZDto> updateBiHuanPZ(@RequestBody SC_BH_DiaoYongPZUpdateDto updateDto) throws WeiZhaoDSJException {
        return MsfResponse.success(_biHuanDYService.updateBiHuanPZ(updateDto));
    }

    /**
     * 修改闭环配置状态
     * @param id
     * @param qiYongBZ
     * @return
     * @throws WeiZhaoDSJException
     */
    @Operation(summary = "修改闭环配置状态")
    @PutMapping("updateBiHuanPZZT")
    public MsfResponse<SC_BH_DiaoYongPZDto> updateBiHuanPZZT(String id, Integer qiYongBZ) throws WeiZhaoDSJException{
        return MsfResponse.success(_biHuanDYService.updateBiHuanPZZT(id,qiYongBZ));
    }

    /**
     * 作废闭环配置
     * @param id
     * @return
     * @throws WeiZhaoDSJException
     */
    @Operation(summary = "作废闭环配置")
    @DeleteMapping("zuoFeiBiHuanPZ")
    public MsfResponse<Boolean> zuoFeiBiHuanPZ(String id) throws WeiZhaoDSJException{
        return MsfResponse.success(_biHuanDYService.zuoFeiBiHuanPZ(id));
    }
}

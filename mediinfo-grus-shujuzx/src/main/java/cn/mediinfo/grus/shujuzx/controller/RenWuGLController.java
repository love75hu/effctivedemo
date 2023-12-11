package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.grus.shujuzx.dto.renwugls.*;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.GY_ZD_ShuJuYuanTreeRso;
import cn.mediinfo.grus.shujuzx.remoteservice.GongYongRemoteService;
import cn.mediinfo.grus.shujuzx.service.RenWuGLService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@Tag(name = "RenWuGLController", description = "ETL管理-任务管理")
@RequestMapping({"api/v1.0/RenWuGL", "api/v1/RenWuGL"})
@Slf4j
@Validated
public class RenWuGLController {
    public LyraIdentityService lyraIdentityService;
    public RenWuGLService renWuGLService;
    public GongYongRemoteService gongYongRemoteService;

    public RenWuGLController(LyraIdentityService lyraIdentityService, RenWuGLService renWuGLService, GongYongRemoteService gongYongRemoteService) {
        this.lyraIdentityService = lyraIdentityService;
        this.renWuGLService = renWuGLService;
        this.gongYongRemoteService = gongYongRemoteService;
    }

    /**
     * 获取基本信息列表
     *
     * @param queryDto
     * @return
     */
    @Operation(summary = "获取基本信息列表")
    @PostMapping(path = "GetJiBenXXList")
    public MsfResponse<List<SC_RW_JiBenXXListDto>> getJiBenXXList(@RequestBody SC_RW_JiBenXXQueryDto queryDto) {
        return MsfResponse.success(renWuGLService.getJiBenXXList(queryDto));
    }

    /**
     * 获取基本信息数量
     *
     * @param queryDto
     * @return
     */
    @Operation(summary = "获取基本信息数量")
    @PostMapping(path = "GetJiBenXXCount")
    public MsfResponse<Long> getJiBenXXCount(@RequestBody SC_RW_JiBenXXQueryDto queryDto) {
        return MsfResponse.success(renWuGLService.getJiBenXXCount(queryDto));
    }

    /**
     * 根据ID查询任务基本信息
     *
     * @param id
     * @return
     * @throws TongYongYWException
     */
    @Operation(summary = "根据ID查询任务基本信息")
    @GetMapping(path = "GetRenWuXXById")
    public MsfResponse<SC_RW_JiBenXXDto> getRenWuXXById(@RequestParam String id) throws TongYongYWException {

        return MsfResponse.success(renWuGLService.getRenWuXXById(id));
    }

    /**
     * 新增基本信息
     *
     * @param createDto
     * @return
     */
    @Operation(summary = "新增基本信息")
    @PostMapping(path = "AddRenWuJBXX")
    public MsfResponse<String> addRenWuJBXX(@RequestBody SC_RW_JiBenXXCreateDto createDto) throws TongYongYWException {
        if (!StringUtil.hasText(createDto.getRenWuMC())) {
            throw new TongYongYWException("任务名称不能为空");
        }
        return MsfResponse.success(renWuGLService.addRenWuJBXX(createDto));
    }

    /**
     * 修改基本信息
     *
     * @param updateDto
     * @return
     * @throws TongYongYWException
     */
    @Operation(summary = "修改基本信息")
    @PutMapping(path = "UpdateRenWuJBXX")
    public MsfResponse<Boolean> updateRenWuJBXX(@RequestBody SC_RW_JiBenXXUpdateDto updateDto) throws TongYongYWException {
        if (!StringUtil.hasText(updateDto.getId())) {
            throw new TongYongYWException("主键ID不能为空");
        }
        if (!StringUtil.hasText(updateDto.getRenWuMC())) {
            throw new TongYongYWException("任务名称不能为空");
        }
        return MsfResponse.success(renWuGLService.updateRenWuJBXX(updateDto));
    }

    /**
     * 启用任务
     *
     * @param id       主键
     * @param qiYongBZ 启用标志
     * @return
     * @throws TongYongYWException
     */
    @Operation(summary = "启用任务")
    @PutMapping(path = "QiYongRW")
    public MsfResponse<Boolean> qiYongRW(String id, Integer qiYongBZ) throws TongYongYWException {
        if (!StringUtil.hasText(id)) {
            throw new TongYongYWException("主键ID不能为空");
        }
        if (Objects.isNull(qiYongBZ)) {
            throw new TongYongYWException("启用标志不能为空");
        }
        return MsfResponse.success(renWuGLService.qiYongRW(id, qiYongBZ));
    }

    /**
     * 获取执行列表
     *
     * @param queryDto
     * @return
     */
    @Operation(summary = "获取执行日志列表")
    @PostMapping(path = "GetZhiXingRZList")
    public MsfResponse<List<SC_RW_ZhiXingRZListDto>> getZhiXingRZList(@RequestBody SC_RW_ZhiXingRZQueryDto queryDto) {
        return MsfResponse.success(renWuGLService.getZhiXingRZList(queryDto));
    }

    /**
     * 执行保存
     */
    @Operation(summary = "获取执行日志列表")
    @PostMapping(path = "SaveZhiXingRZList")
    public MsfResponse<Boolean> saveZhiXingRZList(String RenWuID){
        return MsfResponse.success(renWuGLService.saveZhiXingRZ(RenWuID));
    }

    /**
     * 获取数据源分类树
     * @return
     */
    @Operation(summary = "获取数据源分类树")
    @GetMapping(path = "GetShuJuYFLTree")
    public MsfResponse<List<GY_ZD_ShuJuYuanTreeRso>> getShuJuYFLTree() {
        return gongYongRemoteService.getShuJuYFLTree();
    }


    /**
     * 数据源保存
     *
     * @param createDto
     * @return
     * @throws TongYongYWException
     */
    @Operation(summary = "保存数据源")
    @PostMapping(path = "SaveShuJuYuanList")
    public MsfResponse<Boolean> saveShuJuYuanList(@RequestBody SC_RW_ShuJuYuanCreateDto createDto) throws TongYongYWException {
        if (!StringUtil.hasText(createDto.getRenWuID())) {
            throw new TongYongYWException("任务ID不能为空");
        }
        return MsfResponse.success(renWuGLService.saveShuJuYuanList(createDto));
    }


    /**
     * 获取通用列表
     *
     * @return
     */
    @Operation(summary = "获取通用配置列表")
    @GetMapping(path = "GetTongYongPZ")
    public MsfResponse<List<SC_RW_TongYongPZDto>> getTongYongPZ() {
        return MsfResponse.success(renWuGLService.getTongYongPZ());
    }

    /**
     * 保存通用配置列表
     *
     * @param creatDto
     * @return
     * @throws TongYongYWException
     */
    @Operation(summary = "保存通用配置列表")
    @PostMapping(path = "SaveTongYongPZ")
    public MsfResponse<Boolean> saveTongYongPZ(@RequestBody List<SC_RW_TongYongPZDto> creatDto) throws TongYongYWException {
        if (creatDto.size() == 0) {
            throw new TongYongYWException("通用配置列表不能为空");
        }
        return MsfResponse.success(renWuGLService.saveTongYongPZ(creatDto));
    }


}
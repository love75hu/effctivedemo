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
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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

     * @return
     */
    @Operation(summary = "获取基本信息列表")
    @GetMapping(path = "GetJiBenXXList")
    public MsfResponse<List<SC_RW_JiBenXXListDto>> getJiBenXXList(@RequestParam(required = false) String likeQuery,
                                                                  @RequestParam(required = false)String fenLeiDM,
                                                                  @RequestParam Integer qiYongBZ,
                                                                  @RequestParam(required = false,defaultValue = "1") Integer pageIndex,
                                                                  @RequestParam(required = false,defaultValue = "10") Integer pageSize) {
        return MsfResponse.success(renWuGLService.getJiBenXXList(likeQuery,fenLeiDM,qiYongBZ,pageIndex,pageSize));
    }

    /**
     * 获取基本信息数量
     * @param likeQuery
     * @param fenLeiDM
     * @param qiYongBZ
     * @return
     */
    @Operation(summary = "获取基本信息数量")
    @GetMapping(path = "GetJiBenXXCount")
    public MsfResponse<Long> getJiBenXXCount(@RequestParam(required = false) String likeQuery,
                                             @RequestParam(required = false)String fenLeiDM,
                                             @RequestParam Integer qiYongBZ) {
        return MsfResponse.success(renWuGLService.getJiBenXXCount(likeQuery,fenLeiDM,qiYongBZ));
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
     * 批量执行根据IDs查询任务基本信息列表
     *
     * @param ids
     * @return
     * @throws TongYongYWException
     */
    @Operation(summary = "根据ID查询任务基本信息列表")
    @GetMapping(path = "GetRenWuXXListByIds")
    public MsfResponse<JiBenXXListDto> GetRenWuXXListByIds(@RequestParam List<String> ids) throws TongYongYWException {

        if (ids.size()==0){
            return MsfResponse.fail(new TongYongYWException("请选择任务"));
        }
        return MsfResponse.success(renWuGLService.getRenWuXXByIds(ids));
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
     * 作废基本信息
     * @param id
     * @return
     * @throws TongYongYWException
     */
    @Operation(summary = "作废基本信息")
    @DeleteMapping(path = "ZuoFeiRenWuJBXX")
    public MsfResponse<Boolean> ZuoFeiRenWuJBXX(@NotEmpty(message = "主键ID必传！") @RequestParam String id) throws TongYongYWException {

        return MsfResponse.success(renWuGLService.zuoFeiRenWuJBXX(id));
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
     * 根据获取执行日志详情
     * @param id 主键id
     * @return
     */
    @Operation(summary = "根据获取执行日志详情")
    @GetMapping(path = "GetZhiXingRZDto")
    public MsfResponse<SC_RW_ZhiXingRZDto> getZhiXingRZDto(@RequestParam String id){
        return MsfResponse.success(renWuGLService.getZhiXingRZDto(id));
    }

    /**
     * 获取执行列表
     * @param renWuID
     * @param zhiXingKSSJ
     * @param zhiXingJSSJ
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Operation(summary = "获取执行日志列表")
    @GetMapping(path = "GetZhiXingRZList")
    public MsfResponse<List<SC_RW_ZhiXingRZListDto>> getZhiXingRZList(@RequestParam String renWuID,
                                                                      @RequestParam Date zhiXingKSSJ,
                                                                      @RequestParam Date zhiXingJSSJ,
                                                                      @RequestParam(required = false,defaultValue = "1") Integer pageIndex,
                                                                      @RequestParam(required = false,defaultValue = "10") Integer pageSize) {
        return MsfResponse.success(renWuGLService.getZhiXingRZList(renWuID,zhiXingKSSJ,zhiXingJSSJ,pageIndex,pageSize));
    }

    /**
     * 获取执行列表数量
     * @param renWuID
     * @param zhiXingKSSJ
     * @param zhiXingJSSJ
     * @return
     */
    @Operation(summary = "获取执行日志列表")
    @GetMapping(path = "getZhiXingRZCount")
    public MsfResponse<Long> getZhiXingRZCount(@RequestParam String renWuID,
                                                                      @RequestParam Date zhiXingKSSJ,
                                                                      @RequestParam Date zhiXingJSSJ) {
        return MsfResponse.success(renWuGLService.getZhiXingRZCount(renWuID,zhiXingKSSJ,zhiXingJSSJ));
    }
    /**
     * 执行保存
     */
    @Operation(summary = "单条数据执行")
    @PostMapping(path = "SaveZhiXingRZ")
    public MsfResponse<Boolean> saveZhiXingRZ(String RenWuID) throws TongYongYWException {
        return MsfResponse.success(renWuGLService.saveZhiXingRZ(RenWuID));
    }
    /**
     * 批量执行保存
     */
    @Operation(summary = "批量执行")
    @PostMapping(path = "SaveZhiXingRZList")
    public MsfResponse<String> saveZhiXingRZList(@RequestBody List<SC_RW_ZhiXingRZCreateDto> createDto) throws TongYongYWException {
        return MsfResponse.success(renWuGLService.saveRenWuZXList(createDto));
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
        //判断是否存在重复保存的数据源数据
        var shuJuYuanID=createDto.getShuJuYuanDtoList().stream().map(SC_RW_ShuJuYuanDto::getShuJuYID).toList();
        Set<String> set = new HashSet<>();
        for (var item : shuJuYuanID) {
            if (!set.add(item)) {
                var itemDto=createDto.getShuJuYuanDtoList().stream().filter(t->Objects.equals(t.getShuJuYID(),item)).findFirst().orElse(null);
                if (itemDto!=null){
                    throw new TongYongYWException("有相同的数据源名称，不允许保存");
                }
            }
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
        return MsfResponse.success(renWuGLService.getTongYongPZNew());
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
        return MsfResponse.success(renWuGLService.saveTongYongPZNew(creatDto));
    }

    /**
     * 根据分类id获取任务地址
     * @param fenLeiDM
     * @return
     */
    @Operation(summary = "根据分类id获取任务地址")
    @GetMapping(path = "GetTongYongPZByFLDM")
    public  MsfResponse<SC_RW_TongYongPZDto> getTongYongPZByFLDM(@RequestParam String fenLeiDM){
        return MsfResponse.success(renWuGLService.getTongYongPZByFLDM(fenLeiDM));
    }
    @Operation(summary = "数据源列表")
    @GetMapping(path = "GetShuJuYuanList")
    public  MsfResponse<List<SC_RW_ShuJuYuanDto>> getShuJuYuanList(@RequestParam String RenWuID){
        return MsfResponse.success(renWuGLService.getShuJuYuanList(RenWuID));
    }

    /**
     * 批量执行保存
     */
    @Operation(summary = "批量执行测试")
    @PostMapping(path = "SaveRenWuZXList")
    public MsfResponse<String> saveRenWuZXList(@RequestBody List<SC_RW_ZhiXingRZCreateDto> createDto) throws TongYongYWException {
        return MsfResponse.success(renWuGLService.saveRenWuZXList(createDto));
    }

    /**
     * 启用任务
     *
     * @param id       主键
     * @param qiYongBZ 启用标志
     * @return
     * @throws TongYongYWException
     */
    @Operation(summary = "启用数据源")
    @PutMapping(path = "QiYongSJY")
    public MsfResponse<Boolean> qiYongSJY(String id, Integer qiYongBZ) throws TongYongYWException {
        if (!StringUtil.hasText(id)) {
            throw new TongYongYWException("主键ID不能为空");
        }
        if (Objects.isNull(qiYongBZ)) {
            throw new TongYongYWException("启用标志不能为空");
        }
        return MsfResponse.success(renWuGLService.qiYongRW(id, qiYongBZ));
    }
    @Operation(summary = "获取任务详情")
    @GetMapping(path = "getRenWuXQ")
    public MsfResponse<RenWuXQDto> getRenWuXQ(@RequestParam String id) throws TongYongYWException {
        if (!StringUtil.hasText(id)) {
            throw new TongYongYWException("主键ID不能为空");
        }
        return MsfResponse.success(renWuGLService.getRenWuXQ(id));
    }

}

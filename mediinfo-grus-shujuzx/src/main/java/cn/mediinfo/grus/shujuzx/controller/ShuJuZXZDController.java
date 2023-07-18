package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.grus.shujuzx.dto.BiHuanLCs.SC_ZD_BiHuanLCBJInDto;
import cn.mediinfo.grus.shujuzx.dto.BiHuanLCs.SC_ZD_BiHuanLCInDto;
import cn.mediinfo.grus.shujuzx.dto.BiHuanLCs.SC_ZD_BiHuanLCJDSXHDto;
import cn.mediinfo.grus.shujuzx.dto.BiHuanLCs.SC_ZD_BiHuanLCOutDto;
import cn.mediinfo.grus.shujuzx.dto.YinSiGZSZs.*;
import cn.mediinfo.grus.shujuzx.service.BiHuanLCService;
import cn.mediinfo.grus.shujuzx.service.YinSiGZSZService;
import cn.mediinfo.starter.base.exception.MsfException;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.response.MsfResponse;
import cn.mediinfo.starter.base.util.StringUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping({"api/v1.0/ShuJuZXZD/", "api/v1/ShuJuZXZD/"})
@Slf4j
@Validated
public class ShuJuZXZDController {
    private final YinSiGZSZService yinSiGZSZService;
    private final BiHuanLCService biHuanLCService;

    public ShuJuZXZDController(YinSiGZSZService yinSiGZSZService,
                               BiHuanLCService biHuanLCService) {
        this.yinSiGZSZService = yinSiGZSZService;
        this.biHuanLCService = biHuanLCService;
    }

    /**
     * 新增隐私规则
     */
    @PostMapping("AddYinSiGZ")
    @Operation(summary = "新增隐私规则")
    public MsfResponse<Integer> addYinSiGZ(@RequestBody SC_ZD_YinSiGZSZInDto yinSiGZSZInDto) throws MsfException {
        return MsfResponse.success(yinSiGZSZService.addYinSiGZ(yinSiGZSZInDto));
    }

    /**
     * 保存隐私设置
     */
    @PostMapping("SaveYinSiSZList")
    @Operation(summary = "保存隐私设置")
    public MsfResponse<Boolean> saveYinSiSZList(@RequestBody SC_ZD_YinSiPZCreateDto dto) {
        return MsfResponse.success(yinSiGZSZService.saveYinSiSZList(dto));
    }

    /**
     * 保存展示配置
     */
    @PostMapping("SaveZhanShiPZ")
    @Operation(summary = "保存展示配置")
    public MsfResponse<Boolean> saveZhanShiPZ(@RequestBody SC_ZD_ZhanShiPZCreateDto dto) {
        return MsfResponse.success(yinSiGZSZService.saveZhanShiPZ(dto));
    }

    /**
     * 修改隐私规则
     */
    @PutMapping("UpdateYinSiGZ")
    @Operation(summary = "修改隐私规则")
    public MsfResponse<Integer> updateYinSiGZ(@RequestBody SC_ZD_YinSiGZSZInDto yinSiGZSZInDto) throws MsfException {
        return MsfResponse.success(yinSiGZSZService.updateYinSiGZ(yinSiGZSZInDto));
    }

    /**
     * 作废隐私规则
     */
    @DeleteMapping("ZuoFeiYinSiGZ")
    @Operation(summary = "作废隐私规则")
    public MsfResponse<Integer> zuoFeiYinSiGZ(String id) throws MsfException {
        return MsfResponse.success(yinSiGZSZService.zuoFeiYinSiGZ(id));
    }

    /**
     * 移除隐私配置
     */
    @DeleteMapping("ZuoFeiYinSiSZ")
    @Operation(summary = "移除隐私配置")
    public MsfResponse<Boolean> zuoFeiYinSiSZ(String id) {
        return MsfResponse.success(yinSiGZSZService.zuoFeiYinSiSZ(id));
    }

    /**
     * 启用隐私设置
     */
    @GetMapping("QiYongYinSiSZ")
    @Operation(summary = "启用隐私设置")
    public MsfResponse<Boolean> qiYongYinSiSZ(String id, Integer qiYongBZ) {
        return MsfResponse.success(yinSiGZSZService.qiYongYinSiSZ(id, qiYongBZ));
    }

    /**
     * 更新隐私规则
     *
     * @param zuZhiJGID  组织机构id
     * @param zuZhiJGMC  组织机构名称
     * @param chaXunMSDM 查询模式代码
     * @param peiZhiLXDM 配置类型代码
     */
    @PutMapping("UpdateZhanShiPZ")
    @Operation(summary = "更新隐私规则")
    public MsfResponse<Boolean> updateZhanShiPZ(String zuZhiJGID, String zuZhiJGMC, String chaXunMSDM, String peiZhiLXDM) {
        if (!StringUtil.hasText(zuZhiJGID) || !StringUtil.hasText(zuZhiJGMC)) {
            return MsfResponse.fail("组织机构不能为空！");
        }
        if (!StringUtil.hasText(chaXunMSDM)) {
            return MsfResponse.fail("查询模式代码不能为空！");
        }
        if (!StringUtil.hasText(peiZhiLXDM)) {
            return MsfResponse.fail("配置类型代码不能为空！");
        }
        return MsfResponse.success(yinSiGZSZService.updateZhanShiPZ(zuZhiJGID, zuZhiJGMC, chaXunMSDM, peiZhiLXDM));
    }

    /**
     * 初始化隐私设置
     *
     * @param zuZhiJGID  组织机构ID
     * @param zuZhiJGMC  组织机构名称
     * @param chaXunMSDM 查询模式代码
     */
    @GetMapping("ChuShiHYinSiZS")
    @Operation(summary = "初始化隐私设置")
    public MsfResponse<Boolean> chuShiHYinSiZS(String zuZhiJGID, String zuZhiJGMC, String chaXunMSDM) {
        if (!StringUtil.hasText(zuZhiJGID) || !StringUtil.hasText(zuZhiJGMC)) {
            return MsfResponse.fail("组织机构不能为空！");
        }
        if (!StringUtil.hasText(chaXunMSDM)) {
            return MsfResponse.fail("查询模式代码不能为空！");
        }
        return MsfResponse.success(yinSiGZSZService.chuShiHYinSiZS(zuZhiJGID, zuZhiJGMC, chaXunMSDM));
    }

    /**
     * 初始化隐私规则配置
     *
     * @param zuZhiJGID  组织机构ID
     * @param zuZhiJGMC  组织机构名称
     * @param chaXunMSDM 查询模式代码
     * @return true
     */
    @GetMapping("ChuShiHYSGZPZ")
    @Operation(summary = "初始化隐私规则配置")
    public MsfResponse<Boolean> chuShiHYSGZPZ(String zuZhiJGID, String zuZhiJGMC, String chaXunMSDM) {
        if (!StringUtil.hasText(zuZhiJGID) || !StringUtil.hasText(zuZhiJGMC)) {
            return MsfResponse.fail("组织机构不能为空！");
        }
        if (!StringUtil.hasText(chaXunMSDM)) {
            return MsfResponse.fail("查询模式代码不能为空！");
        }
        return MsfResponse.success(yinSiGZSZService.chuShiHYSGZPZ(zuZhiJGID, zuZhiJGMC, chaXunMSDM));
    }

    /**
     * 初始化展示设置
     *
     * @param zuZhiJGID  组织机构ID
     * @param zuZhiJGMC  组织机构名称
     * @param chaXunMSDM 查询模式代码
     * @param peiZhiLXDM 配置类型代码
     */
    @GetMapping("ChuShiHZhanShiPZ")
    @Operation(summary = "初始化展示设置")
    public MsfResponse<Boolean> chuShiHZhanShiPZ(String zuZhiJGID, String zuZhiJGMC, String chaXunMSDM, String peiZhiLXDM) {
        if (!StringUtil.hasText(zuZhiJGID) || !StringUtil.hasText(zuZhiJGMC)) {
            return MsfResponse.fail("组织机构不能为空！");
        }
        if (!StringUtil.hasText(chaXunMSDM)) {
            return MsfResponse.fail("查询模式代码不能为空！");
        }
        if (!StringUtil.hasText(peiZhiLXDM)) {
            return MsfResponse.fail("配置类型代码不能为空！");
        }
        return MsfResponse.success(yinSiGZSZService.chuShiHZhanShiPZ(zuZhiJGID, zuZhiJGMC, chaXunMSDM, peiZhiLXDM));
    }

    /**
     * 根据主键查询一条闭环流程信息
     */
    @GetMapping("GetBiHuanLCByID")
    @Operation(summary = "根据主键查询一条闭环流程信息")
    public MsfResponse<List<SC_ZD_BiHuanLCOutDto>> getBiHuanLCByID(String id, String zuZhiJGID) {
        return MsfResponse.success(biHuanLCService.getBiHuanLCByID(id, zuZhiJGID));
    }

    /**
     * 根据组织机构、闭环类型获取闭环流程信息
     */
    @GetMapping("GetBiHuanLCForLX")
    @Operation(summary = "根据组织机构、闭环类型获取闭环流程信息")
    public MsfResponse<SC_ZD_BiHuanLCOutDto> getBiHuanLCForLX(String zuZhiJGID, String biHuanLXDM, @RequestParam(required = false) Integer menZhenSYBZ, @RequestParam(required = false) Integer zhuYuanSYBZ, @RequestParam(required = false) Integer jiZhenSYBZ, @RequestParam(required = false) Integer tiJianSYBZ) {
        return MsfResponse.success(biHuanLCService.getBiHuanLCForLX(zuZhiJGID, biHuanLXDM, menZhenSYBZ, zhuYuanSYBZ, jiZhenSYBZ, tiJianSYBZ));
    }

    /**
     * 根据主键id修改某个隐私规则
     */
    @GetMapping("GetYinSiGZByID")
    @Operation(summary = "根据主键id修改某个隐私规则")
    public MsfResponse<SC_ZD_YinSiGZSZOutDto> getYinSiGZByID(String id) {
        if (!StringUtil.hasText(id)) {
            return MsfResponse.fail("参数主键ID，不能为空！");
        }
        return MsfResponse.success(yinSiGZSZService.getYinSiGZByID(id));
    }

    /**
     * 获取隐私配置
     *
     * @param chaXunMSDM 查询模式代码
     * @param zuZhiJGID  组织机构id
     * @return List<SC_ZD_YinSiPZOutDto>
     */
    @GetMapping("GetYinSiGZPZList")
    @Operation(summary = "获取隐私配置")
    public MsfResponse<List<SC_ZD_YinSiPZOutDto>> getYinSiGZPZList(String chaXunMSDM, String zuZhiJGID) throws TongYongYWException {
        return MsfResponse.success(yinSiGZSZService.getYinSiGZPZList(chaXunMSDM, zuZhiJGID));
    }

    /**
     * 获取隐私规则列表条数
     */
    @GetMapping("GetYinSiGZSZCount")
    @Operation(summary = "获取隐私规则列表条数")
    public MsfResponse<Integer> getYinSiGZSZCount(String likeQuery) {
        return MsfResponse.success(yinSiGZSZService.getYinSiGZSZCount(likeQuery));
    }

    /**
     * 获取隐私规则列表
     */
    @GetMapping("GetYinSiGZSZList")
    @Operation(summary = "获取隐私规则列表")
    public MsfResponse<List<SC_ZD_YinSiGZSZOutDto>> getYinSiGZSZList(String likeQuery, @RequestParam(required = false, defaultValue = "1") Integer pageIndex, @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return MsfResponse.success(yinSiGZSZService.getYinSiGZSZList(likeQuery, pageIndex, pageSize));
    }

    /**
     * 获取隐私规则设置数据元列表
     *
     * @param chaXunMSDM 查询模式
     * @param zuZhiJGID  组织机构ID
     */
    @GetMapping("GetYinSiGZSZSJYList")
    @Operation(summary = "获取隐私规则设置数据元列表")
    public MsfResponse<List<SC_ZD_YinSiGZSZOutDto>> getYinSiGZSZSJYList(String chaXunMSDM, String zuZhiJGID) {
        if (!StringUtil.hasText(chaXunMSDM)) {
            return MsfResponse.fail("查询模式代码不能为空！");
        }
        return MsfResponse.success(yinSiGZSZService.getYinSiGZSZSJYList(chaXunMSDM, zuZhiJGID));
    }

    /**
     * 获取隐私设置列表
     *
     * @param chaXunMSDM 查询模式代码
     * @param zuZhiJGID  组织机构ID
     * @param likeQuery  条件
     * @return 隐私配置集合
     */
    @GetMapping("GetYinSiSZList")
    @Operation(summary = "获取隐私设置列表")
    public MsfResponse<List<SC_ZD_YinSiPZDto>> getYinSiSZList(String chaXunMSDM, String zuZhiJGID, String likeQuery) {
        if (!StringUtil.hasText(chaXunMSDM)) {
            return MsfResponse.fail("查询模式代码不能为空！");
        }
        if (!StringUtil.hasText(zuZhiJGID)) {
            return MsfResponse.fail("组织机构不能为空！");
        }
        return MsfResponse.success(yinSiGZSZService.getYinSiSZList(chaXunMSDM, zuZhiJGID, likeQuery));
    }

    /**
     * 获取展示配置列表
     *
     * @param zuZhiJGID  组织机构ID
     * @param chaXunMSDM 查询模式代码
     * @param peiZhiLXDM 配置类型代码
     * @return 展示配置DTO集合
     */
    @GetMapping("GetZhanShiPZList")
    @Operation(summary = "获取展示配置列表")
    public MsfResponse<List<SC_ZD_ZhanShiPZDto>> getZhanShiPZList(String zuZhiJGID, String chaXunMSDM, String peiZhiLXDM) {
        if (!StringUtil.hasText(zuZhiJGID)) {
            return MsfResponse.fail("组织机构不能为空！");
        }
        if (!StringUtil.hasText(chaXunMSDM)) {
            return MsfResponse.fail("查询模式代码不能为空！");
        }
        if (!StringUtil.hasText(peiZhiLXDM)) {
            return MsfResponse.fail("配置类型不能为空！");
        }
        return MsfResponse.success(yinSiGZSZService.getZhanShiPZList(zuZhiJGID, chaXunMSDM, peiZhiLXDM));
    }

    /**
     * 新增一个闭环流程
     */
    @PostMapping("AddBiHuanLC")
    @Operation(summary = "新增一个闭环流程")
    public MsfResponse<Integer> addBiHuanLC(@RequestBody SC_ZD_BiHuanLCInDto biHuanLCDto) throws MsfException {
        return MsfResponse.success(biHuanLCService.addBiHuanLC(biHuanLCDto));
    }

    /**
     *根据闭环类型代码获取闭环节点列表
     */
    @GetMapping("GetBiHuanLCList")
    @Operation(summary = "根据闭环类型代码获取闭环节点列表")
    public MsfResponse<List<SC_ZD_BiHuanLCOutDto>> getBiHuanLCList(String zuZhiJGID, String biHuanLXDM, @RequestParam(required = false) String likeQuery){
        return MsfResponse.success(biHuanLCService.getBiHuanLCList(zuZhiJGID, biHuanLXDM, likeQuery));
    }
    /**
     * 启用闭环流程
     */
    @PutMapping("QiYongBiHuanLC")
    @Operation(summary = "启用闭环流程")
    public MsfResponse<Integer> qiYongBiHuanLC(String id, String zuZhiJGID) throws MsfException{
        return MsfResponse.success(biHuanLCService.qiYongBiHuanLC(id, zuZhiJGID));
    }
    /**
     * 停用闭环流程
     */
    @PutMapping("TingYongBiHuanLC")
    @Operation(summary = "停用闭环流程")
    public MsfResponse<Integer> tingYongBiHuanLC(String id) throws MsfException{
        if (!StringUtil.hasText(id)){
            return MsfResponse.fail("入参id不能为空！");
        }
        return MsfResponse.success(biHuanLCService.tingYongBiHuanLC(id));
    }
    /**
     * 编辑一条闭环流程
     */
    @PutMapping("UpdateBiHuanLC")
    @Operation(summary = "编辑一条闭环流程")
    public MsfResponse<Integer> updateBiHuanLC(@RequestBody SC_ZD_BiHuanLCBJInDto biHuanLCBJInDto) throws MsfException{
        return MsfResponse.success(biHuanLCService.updateBiHuanLC(biHuanLCBJInDto));
    }
    /**
     * 更新闭环节点流程顺序号
     */
    @PutMapping("UpdateBiHuanLCJDSXH")
    @Operation(summary = "更新闭环节点流程顺序号")
    public MsfResponse<Integer> updateBiHuanLCJDSXH(@RequestBody List<SC_ZD_BiHuanLCJDSXHDto> jdSxhDtos){
        return MsfResponse.success(biHuanLCService.updateBiHuanLCJDSXH(jdSxhDtos));
    }
    /**
     * 更新闭环流程
     */
    @PutMapping("UpdateBiHuanLCList")
    @Operation(summary = "更新闭环流程")
    public MsfResponse<Integer> updateBiHuanLCList(String zuZhiJGID, String zuZhiJGMC, String biHuanLXDM){
        return MsfResponse.success(biHuanLCService.updateBiHuanLCList(zuZhiJGID, zuZhiJGMC, biHuanLXDM));
    }
    /**
     * 作废一条闭环流程
     */
    @DeleteMapping("ZuoFeiBiHuanLC")
    @Operation(summary = "作废一条闭环流程")
    public MsfResponse<Integer> zuoFeiBiHuanLC(String id) throws TongYongYWException{
        return MsfResponse.success(biHuanLCService.zuoFeiBiHuanLC(id));
    }
}

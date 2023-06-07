package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.grus.shujuzx.dto.BiHuanLCs.SC_ZD_BiHuanLCOutDto;
import cn.mediinfo.grus.shujuzx.dto.YinSiGZSZs.SC_ZD_YinSiGZSZInDto;
import cn.mediinfo.grus.shujuzx.dto.YinSiGZSZs.SC_ZD_YinSiPZCreateDto;
import cn.mediinfo.grus.shujuzx.dto.YinSiGZSZs.SC_ZD_ZhanShiPZCreateDto;
import cn.mediinfo.grus.shujuzx.service.BiHuanLCService;
import cn.mediinfo.grus.shujuzx.service.YinSiGZSZService;
import cn.mediinfo.starter.base.exception.MsfException;
import cn.mediinfo.starter.base.response.MsfResponse;
import cn.mediinfo.starter.base.util.StringUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "ShuJuZXZDController", description = "临床数据中心字典")
@RequestMapping({"api/v1.0/ShuJuZXZD/", "api/v1/ShuJuZXZD/"})
@Slf4j
@Validated
public class ShuJuZXZDController {
    private final YinSiGZSZService yinSiGZSZService;
    private final BiHuanLCService biHuanLCService;

    public ShuJuZXZDController(YinSiGZSZService yinSiGZSZService,
                               BiHuanLCService biHuanLCService)
    {
        this.yinSiGZSZService=yinSiGZSZService;
        this.biHuanLCService=biHuanLCService;
    }

    /**
     *新增隐私规则
     */
    @PostMapping("AddYinSiGZ")
    @Operation(summary = "新增隐私规则")
    public MsfResponse<Integer> addYinSiGZ(SC_ZD_YinSiGZSZInDto yinSiGZSZInDto) throws MsfException{
        return MsfResponse.success(yinSiGZSZService.addYinSiGZ(yinSiGZSZInDto));
    }

    /**
     * 保存隐私设置
     */
    @PostMapping("SaveYinSiSZList")
    @Operation(summary = "保存隐私设置")
    public MsfResponse<Boolean> saveYinSiSZList(SC_ZD_YinSiPZCreateDto dto){
        return MsfResponse.success(yinSiGZSZService.saveYinSiSZList(dto));
    }
    /**
     *保存展示配置
     */
    @PostMapping("SaveZhanShiPZ")
    @Operation(summary = "保存展示配置")
    public MsfResponse<Boolean> saveZhanShiPZ(SC_ZD_ZhanShiPZCreateDto dto){
        return MsfResponse.success(yinSiGZSZService.saveZhanShiPZ(dto));
    }
    /**
     *修改隐私规则
     */
    @PostMapping("UpdateYinSiGZ")
    @Operation(summary = "修改隐私规则")
    public MsfResponse<Integer> updateYinSiGZ(SC_ZD_YinSiGZSZInDto yinSiGZSZInDto) throws MsfException{
        return MsfResponse.success(yinSiGZSZService.updateYinSiGZ(yinSiGZSZInDto));
    }
    /**
     * 作废隐私规则
     */
    @PostMapping("ZuoFeiYinSiGZ")
    @Operation(summary = "作废隐私规则")
    public MsfResponse<Integer> zuoFeiYinSiGZ(String id) throws MsfException{
        return MsfResponse.success(yinSiGZSZService.zuoFeiYinSiGZ(id));
    }
    /**
     * 移除隐私配置
     */
    @PostMapping("ZuoFeiYinSiSZ")
    @Operation(summary = "移除隐私配置")
    public MsfResponse<Boolean> zuoFeiYinSiSZ(String id){
        return MsfResponse.success(yinSiGZSZService.zuoFeiYinSiSZ(id));
    }
    /**
     * 启用隐私设置
     */
    @PostMapping("QiYongYinSiSZ")
    @Operation(summary = "启用隐私设置")
    public MsfResponse<Boolean> qiYongYinSiSZ(String id, Integer qiYongBZ){
        return MsfResponse.success(yinSiGZSZService.qiYongYinSiSZ(id,qiYongBZ));
    }
    /**
     *更新隐私规则
     * @param zuZhiJGID  组织机构id
     * @param zuZhiJGMC  组织机构名称
     * @param chaXunMSDM 查询模式代码
     * @param peiZhiLXDM 配置类型代码
     */
    @GetMapping("UpdateZhanShiPZ")
    @Operation(summary = "更新隐私规则")
    public MsfResponse<Boolean> updateZhanShiPZ(String zuZhiJGID, String zuZhiJGMC, String chaXunMSDM, String peiZhiLXDM) {
        if (!StringUtil.hasText(zuZhiJGID) || !StringUtil.hasText(zuZhiJGMC)){
            return MsfResponse.fail("组织机构不能为空！");
        }
        if (!StringUtil.hasText(chaXunMSDM)){
            return MsfResponse.fail("查询模式代码不能为空！");
        }
        if (!StringUtil.hasText(peiZhiLXDM)){
            return MsfResponse.fail("配置类型代码不能为空！");
        }
        return MsfResponse.success(yinSiGZSZService.updateZhanShiPZ(zuZhiJGID, zuZhiJGMC, chaXunMSDM, peiZhiLXDM));
    }
    /**
     *初始化隐私设置
     * @param zuZhiJGID 组织机构ID
     * @param zuZhiJGMC 组织机构名称
     * @param chaXunMSDM 查询模式代码
     */
    @GetMapping("ChuShiHYinSiZS")
    @Operation(summary = "初始化隐私设置")
    public MsfResponse<Boolean> chuShiHYinSiZS(String zuZhiJGID, String zuZhiJGMC, String chaXunMSDM){
        if (!StringUtil.hasText(zuZhiJGID) || !StringUtil.hasText(zuZhiJGMC)){
            return MsfResponse.fail("组织机构不能为空！");
        }
        if (!StringUtil.hasText(chaXunMSDM)){
            return MsfResponse.fail("查询模式代码不能为空！");
        }
        return MsfResponse.success(yinSiGZSZService.chuShiHYinSiZS(zuZhiJGID, zuZhiJGMC, chaXunMSDM));
    }
    /**
     *初始化隐私规则配置
     * @param zuZhiJGID 组织机构ID
     * @param zuZhiJGMC 组织机构名称
     * @param chaXunMSDM 查询模式代码
     * @return true
     */
    @GetMapping("ChuShiHYSGZPZ")
    @Operation(summary = "初始化隐私规则配置")
    public MsfResponse<Boolean>  chuShiHYSGZPZ(String zuZhiJGID, String zuZhiJGMC, String chaXunMSDM){
        if (!StringUtil.hasText(zuZhiJGID) || !StringUtil.hasText(zuZhiJGMC)){
            return MsfResponse.fail("组织机构不能为空！");
        }
        if (!StringUtil.hasText(chaXunMSDM)){
            return MsfResponse.fail("查询模式代码不能为空！");
        }
        return MsfResponse.success(yinSiGZSZService.chuShiHYSGZPZ(zuZhiJGID, zuZhiJGMC, chaXunMSDM));
    }
    /**
     *初始化展示设置
     * @param zuZhiJGID 组织机构ID
     * @param zuZhiJGMC 组织机构名称
     * @param chaXunMSDM 查询模式代码
     * @param peiZhiLXDM 配置类型代码
     */
    @GetMapping("ChuShiHZhanShiPZ")
    @Operation(summary = "初始化展示设置")
    public MsfResponse<Boolean> chuShiHZhanShiPZ(String zuZhiJGID, String zuZhiJGMC, String chaXunMSDM, String peiZhiLXDM){
        if (!StringUtil.hasText(zuZhiJGID) || !StringUtil.hasText(zuZhiJGMC)){
            return MsfResponse.fail("组织机构不能为空！");
        }
        if (!StringUtil.hasText(chaXunMSDM)){
            return MsfResponse.fail("查询模式代码不能为空！");
        }
        if (!StringUtil.hasText(peiZhiLXDM)){
            return MsfResponse.fail("配置类型代码不能为空！");
        }
        return MsfResponse.success(yinSiGZSZService.chuShiHZhanShiPZ(zuZhiJGID, zuZhiJGMC, chaXunMSDM, peiZhiLXDM));
    }
    /**
     * 根据主键查询一条闭环流程信息
     */
    @GetMapping("GetBiHuanLCByID")
    @Operation(summary = "根据主键查询一条闭环流程信息")
    public MsfResponse<List<SC_ZD_BiHuanLCOutDto>> GetBiHuanLCByID(String id, String zuZhiJGID){
        return MsfResponse.success(biHuanLCService.GetBiHuanLCByID(id, zuZhiJGID));
    }
}

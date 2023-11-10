package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.cyan.msf.core.response.XiTongResponseCode;
import cn.mediinfo.grus.shujuzx.constant.ZhuSuoYCZLXEnum;
import cn.mediinfo.grus.shujuzx.dto.shujuzxzsys.*;
import cn.mediinfo.grus.shujuzx.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@Tag(name = "ShuJuZXZSYController", description = "数据中心-主索引")
@RequestMapping({"api/v1.0/ShuJuZXZSY", "api/v1/ShuJuZXZSY"})
@Slf4j
@Validated
public class ShuJuZXZSYController {

    private final ZhuSuoYGLService zhuSuoYGLService;
    private final ZhuSuoYCZRZService zhuSuoYCZRZService;
    private final ZhuSuoYHBGZService zhuSuoYHBGZService;
    private final ZhuSuoYQZPZService zhuSuoYQZPZService;
    private final BingRenJBXXService bingRenJBXXService;



    public ShuJuZXZSYController(ZhuSuoYGLService zhuSuoYGLService,
                                ZhuSuoYCZRZService zhuSuoYCZRZService,
                                ZhuSuoYHBGZService zhuSuoYHBGZService,
                                ZhuSuoYQZPZService zhuSuoYQZPZService,
                                BingRenJBXXService bingRenJBXXService)
    {
        this.zhuSuoYGLService=zhuSuoYGLService;
        this.zhuSuoYCZRZService=zhuSuoYCZRZService;
        this.zhuSuoYHBGZService=zhuSuoYHBGZService;
        this.zhuSuoYQZPZService=zhuSuoYQZPZService;
        this.bingRenJBXXService=bingRenJBXXService;
    }

    /**
     * 新增规则信息
     * @param  heBingGZCreateDto DTO
     * @return boolean
     */
    @Operation(summary = "新增规则信息")
    @PostMapping("AddGuiZeXX")
    public MsfResponse<String> addGuiZeXX(@RequestBody BR_ZD_HeBingGZCreateDto heBingGZCreateDto) throws TongYongYWException {
        if (!StringUtils.hasText(heBingGZCreateDto.getGuiZeMC())) {
            return MsfResponse.fail(XiTongResponseCode.CANSHUYC, "规则名称不能为空");
        }
        if (CollectionUtils.isEmpty(heBingGZCreateDto.getGuiZeMXList())) {
            return MsfResponse.fail(XiTongResponseCode.CANSHUYC, "未选择任何字段,请重新确认");
        }
        if (heBingGZCreateDto.getFaZhi().compareTo(BigDecimal.ZERO) < 0 || heBingGZCreateDto.getFaZhi().compareTo(new BigDecimal(100))>0)  {
            return MsfResponse.fail(XiTongResponseCode.CANSHUYC, "阀值不在规定值内,请重新确认!");
        }
        var result = zhuSuoYHBGZService.addGuiZeXX(heBingGZCreateDto);
        return MsfResponse.success(result);
    }

    /**
     * 修改规则信息
     * @param  heBingGZUpdateDto DTO
     * @return boolean
     */
    @Operation(summary = "修改规则信息")
    @PostMapping("UpdateGuiZeXX")
    public MsfResponse<String> updateGuiZeXX(@RequestBody BR_ZD_HeBingGZUpdateDto heBingGZUpdateDto) throws TongYongYWException {
        if (!StringUtils.hasText(heBingGZUpdateDto.getGuiZeMC())) {
            return MsfResponse.fail(XiTongResponseCode.CANSHUYC, "规则名称不能为空");
        }
        if (CollectionUtils.isEmpty(heBingGZUpdateDto.getAddGuiZeMXList())
          &&CollectionUtils.isEmpty(heBingGZUpdateDto.getUpdateGuiZeMXList())
          &&CollectionUtils.isEmpty(heBingGZUpdateDto.getDeleteIds()) ) {
            return MsfResponse.fail(XiTongResponseCode.CANSHUYC, "未选择任何字段,请重新确认");
        }
        if (heBingGZUpdateDto.getFaZhi().compareTo(BigDecimal.ZERO) < 0 || heBingGZUpdateDto.getFaZhi().compareTo(new BigDecimal(100))>0)  {
            return MsfResponse.fail(XiTongResponseCode.CANSHUYC, "阀值不在规定值内,请重新确认!");
        }
        var result = zhuSuoYHBGZService.updateGuiZeXX(heBingGZUpdateDto);
        return MsfResponse.success(result);
    }

    /**
     * 作废规则
     * @param id id
     * @return boolean
     */
    @Operation(summary = "作废规则")
    @DeleteMapping("ZuoFeiGuiZeXX")
    public MsfResponse<Integer> zuoFeiGuiZeXX(@NotEmpty(message = "id不能为空") String id) throws TongYongYWException {
        return MsfResponse.success(zhuSuoYHBGZService.zuoFeiGuiZeXX(id));
    }

    /**
     * 新增一个权重分类
     * @param  dto DTO
     * @return boolean
     */
    @Operation(summary = "新增一个权重分类")
    @PostMapping("AddQuanZhongFL")
    public MsfResponse<Boolean> addQuanZhongFL(@RequestBody AddQuanZhongFLDto dto) throws TongYongYWException {
        var result = zhuSuoYQZPZService.addQuanZhongFL(dto);
        return MsfResponse.success(result);
    }

    /**
     * 更新一个权重分类
     * @param  dto DTO
     * @return boolean
     */
    @Operation(summary = "更新一个权重分类")
    @PutMapping("UpdateQuanZhongFL")
    public MsfResponse<Boolean> updateQuanZhongFL(@RequestBody UpdateQuanZhongFLDto dto) throws TongYongYWException {
        var result = zhuSuoYQZPZService.updateQuanZhongFL(dto);
        return MsfResponse.success(result);
    }

    /**
     * 作废权重分类
     * @param id id
     * @return boolean
     */
    @Operation(summary = "作废权重分类")
    @DeleteMapping("ZuoFeiQuanZhongFL")
    public MsfResponse<Boolean> zuoFeiQuanZhongFL(@NotEmpty(message = "id不能为空")String id) throws TongYongYWException {
        return MsfResponse.success(zhuSuoYQZPZService.zuoFeiQuanZhongFL(id));
    }

    /**
     * 保存权重配置
     * @param dto dto
     * @return boolean
     */
    @Operation(summary = "保存权重配置")
    @PostMapping("saveQuanZhongPZ")
    public MsfResponse<Boolean> saveQuanZhongPZ(@RequestBody SaveQuanZhongPZDto dto) throws TongYongYWException {
        return MsfResponse.success(zhuSuoYQZPZService.saveQuanZhongPZ(dto));
    }

    /**
     * 获取规则列表
     * @return 规则DTO
     * @throws TongYongYWException 返回异常
     */
    @Operation(summary = "获取规则列表")
    @GetMapping("GetGuiZeList")
    public MsfResponse<List<BR_ZD_HeBingGZListDto>> getGuiZeList() throws  TongYongYWException {
        var data = zhuSuoYHBGZService.getGuiZeList();
        return MsfResponse.success(data);
    }

    /**
     * 根据规则ID获取规则信息和规则明细内容
     * @return 规则DTO
     * @throws TongYongYWException 返回异常
     */
    @Operation(summary = "根据规则ID获取规则信息和规则明细内容")
    @GetMapping("GetGuiZeXXByID")
    public MsfResponse<HeBingGZMXDto> getGuiZeXXByID(@NotEmpty(message = "查询规则ID不能为空")String guiZeID) throws  TongYongYWException {
        var data = zhuSuoYHBGZService.getGuiZeXXByID(guiZeID);
        return MsfResponse.success(data);
    }

    /**
     * 获取权重配置分类列表
     * @return BR_ZD_HeBingQZPZListDto
     * @throws TongYongYWException 通用异常
     */
    @Operation(summary = "获取权重配置分类列表")
    @GetMapping("GetQuanZhongPZFLList")
    public MsfResponse<List<BR_ZD_HeBingQZPZListDto>> getQuanZhongPZFLList() throws  TongYongYWException {
        var data = zhuSuoYQZPZService.getQuanZhongPZFLList();
        return MsfResponse.success(data);
    }

    /**
     * 获取权重配置列表(包含分类)
     * @return BR_ZD_HeBingGZQZPZDto
     * @throws TongYongYWException 通用异常
     */
    @Operation(summary = "获取权重配置列表(包含分类)")
    @GetMapping("GetQuanZhongPZList")
    public MsfResponse<List<BR_ZD_HeBingGZQZPZDto>> getQuanZhongPZList() throws  TongYongYWException {
        var data = zhuSuoYQZPZService.getQuanZhongPZList();
        return MsfResponse.success(data);
    }

    /**
     * 根据分类ID获取权重配置列表
     * @return List<BR_ZD_HeBingQZPZListDto></BR_ZD_HeBingQZPZListDto>
     * @throws TongYongYWException 通用异常
     */
    @Operation(summary = "根据分类ID获取权重配置列表")
    @GetMapping("GetQuanZhongPZListByFLID")
    public MsfResponse<List<BR_ZD_HeBingQZPZListDto>> getQuanZhongPZListByFLID(String FuLeiID,String LikeQuery) throws  TongYongYWException {
        var data = zhuSuoYQZPZService.getQuanZhongPZListByFLID(FuLeiID,LikeQuery);
        return MsfResponse.success(data);
    }

    /**
     * 获取主索引的相似索引信息
     * @return BR_DA_JiBenXXByZSYGLDto
     * @throws TongYongYWException 通用异常
     */
    @Operation(summary = "获取主索引的相似索引信息")
    @GetMapping("GetXiangSiSYList")
    public MsfResponse<List<BR_DA_JiBenXXByZSYGLDto>> getXiangSiSYList(String zhuSuoYBRID) throws  TongYongYWException {
        var data = zhuSuoYGLService.getXiangSiSYList(zhuSuoYBRID);
        return MsfResponse.success(data);
    }

    /**
     * 获取主索引操作日志数量
     * @return
     * @throws TongYongYWException 通用异常
     */
    @Operation(summary = "获取主索引操作日志数量")
    @GetMapping("GetZhuSuoYCZRZCount")
    public MsfResponse<Long> getZhuSuoYCZRZCount(@RequestParam(required = false) Date caoZuoKSRQ,@RequestParam(required = false) Date caoZuoJSRQ,String caoZuoLXDM,String likeQuery) throws TongYongYWException, ParseException {
        var data = zhuSuoYCZRZService.getZhuSuoYCZRZCount(caoZuoKSRQ,caoZuoJSRQ,caoZuoLXDM,likeQuery);
        return MsfResponse.success(data);
    }

    /**
     * 获取主索引操作日志列表
     * @return
     * @throws TongYongYWException 通用异常
     */
    @Operation(summary = "获取主索引操作日志列表")
    @GetMapping("GetZhuSuoYCZRZList")
    public MsfResponse<List<BR_DA_ZhuSuoYCZRZDto>> getZhuSuoYCZRZList(Integer pageIndex,Integer pageSize,@RequestParam(required = false) Date caoZuoKSRQ,@RequestParam(required = false)Date caoZuoJSRQ,String caoZuoLXDM,String likeQuery) throws TongYongYWException, ParseException {
        var data = zhuSuoYCZRZService.getZhuSuoYCZRZList(pageIndex,pageSize,caoZuoKSRQ,caoZuoJSRQ,caoZuoLXDM,likeQuery);
        return MsfResponse.success(data);
    }

    /**
     * 操作日志
     * @return
     * @throws TongYongYWException 通用异常
     */
    @Operation(summary = "获取主索引操作日志列表")
    @GetMapping("AddCaoZuoRZ")
    public MsfResponse<Boolean> addCaoZuoRZ(String bingRenID, String xingMing, ZhuSuoYCZLXEnum caoZuoLX, String caoZuoNR, Boolean saveChanges) throws TongYongYWException {
        var data = zhuSuoYCZRZService.addCaoZuoRZ(bingRenID,xingMing,caoZuoLX,caoZuoNR,saveChanges);
        return MsfResponse.success(data);
    }

    /**
     * 获取主索引详情
     * @return
     * @throws TongYongYWException 通用异常
     */
    @Operation(summary = "获取主索引详情")
    @GetMapping("GetZhuSuoYXQ")
    public MsfResponse<ZhuSuoYXQDto> getZhuSuoYXQ(String bingRenID,String chaXunMSDM) throws TongYongYWException, ParseException, NoSuchFieldException, IllegalAccessException, YuanChengException {
        var data = zhuSuoYGLService.getZhuSuoYXQ(bingRenID,chaXunMSDM);
        return MsfResponse.success(data);
    }

    /**
     * 获取主索引管理数量
     * @return
     * @throws TongYongYWException 通用异常
     */
    @Operation(summary = "获取主索引管理数量")
    @GetMapping("GetZhuSuoYinCount")
    public MsfResponse<Integer> getZhuSuoYinCount(Date kaiShiSJ, Date jieShuSJ, Integer xiangSiDu, String MPI, String xingMing, String lianXiDH, String shenFenZH) throws TongYongYWException, ParseException {
        var data = zhuSuoYGLService.getZhuSuoYinCount(kaiShiSJ,jieShuSJ,xiangSiDu,MPI,xingMing,lianXiDH,shenFenZH);
        return MsfResponse.success(data);
    }

    /**
     * 获取主索引管理列表
     * @return
     * @throws TongYongYWException 通用异常
     */
    @Operation(summary = "获取主索引管理列表")
    @GetMapping("GetZhuSuoYGLList")
    public MsfResponse<List<BR_DA_JiBenXXByZSYGLDto>> getZhuSuoYGLList(Integer pageIndex, Integer pageSize, Date kaiShiSJ, Date jieShuSJ, Integer xiangSiDu, String MPI, String xingMing, String lianXiDH, String shenFenZH, String jiuZhenKH) throws TongYongYWException, ParseException {
        var data = zhuSuoYGLService.getZhuSuoYGLList(pageIndex,pageSize,kaiShiSJ,jieShuSJ,xiangSiDu,MPI,xingMing,lianXiDH,shenFenZH,jiuZhenKH);
        return MsfResponse.success(data);
    }

    /**
     * 合并页面获取主索引和相似索引信息
     * @return List<BR_DA_JiBenXXByHBXXDto>
     * @throws TongYongYWException 通用异常
     */
    @Operation(summary = "合并页面获取主索引和相似索引信息")
    @GetMapping("GetZhuSuoYXSList")
    public MsfResponse<List<BR_DA_JiBenXXByHBXXDto>> getZhuSuoYXSList(String bingRenID, Integer xiangSiDu) throws TongYongYWException {
        return MsfResponse.success(zhuSuoYGLService.getZhuSuoYXSList(bingRenID,xiangSiDu));
    }

    /**
     * 合并
     * @return Boolean
     * @throws TongYongYWException 通用异常
     */
    @Operation(summary = "合并")
    @PostMapping("HeBing")
    public MsfResponse<Boolean> heBing(@RequestBody SaveHeBingDto saveDto) throws TongYongYWException {
        return MsfResponse.success(zhuSuoYGLService.heBing(saveDto));
    }

    /**
     * 忽略合并
     * @return Boolean
     * @throws TongYongYWException 通用异常
     */
    @Operation(summary = "忽略合并")
    @PostMapping("HuLueHB")
    public MsfResponse<Boolean> huLueHB(@RequestBody HuLueHBDto saveDto) throws TongYongYWException {
        return MsfResponse.success(zhuSuoYGLService.huLueHB(saveDto));
    }

    /**
     * 主索引修改病人基本信息
     * @return Boolean
     * @throws TongYongYWException 通用异常
     */
    @Operation(summary = "主索引修改病人基本信息")
    @PostMapping("UpdateBingRenJBXX")
    public MsfResponse<String> updateBingRenJBXX(@RequestBody BR_DA_JiBenXXCreateDto saveDto) throws TongYongYWException {
        return MsfResponse.success(zhuSuoYGLService.updateBingRenJBXX(saveDto));
    }


    @Operation(summary = "取消合并")
    @PostMapping("QuXiaoHB")
    public MsfResponse<Boolean> QuXiaoHB(@RequestBody HuLueHBDto saveDto) throws TongYongYWException {
        return MsfResponse.success(zhuSuoYGLService.quXiaoHB(saveDto));
    }

    @Operation(summary = "增量匹配相似患者")
    @GetMapping("ZengLiangPPXSHZ")
    public MsfResponse<String> ZengLiangPPXSHZ() {
        return MsfResponse.success(zhuSuoYGLService.zengLiangPPXSHZ());
    }


}

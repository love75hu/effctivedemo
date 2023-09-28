package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs.ZhiBiaoXXCreateDto;
import cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs.ZhiBiaoXXListDto;
import cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs.ZhiBiaoXXUpdateDto;
import cn.mediinfo.grus.shujuzx.service.ZhiBiaoXXService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping({"api/v1.0/zhibiaoxx", "api/v1/zhibiaoxx"})
@Slf4j
@Validated
public class ZhiBiaoXXController {
    private final ZhiBiaoXXService zhiBiaoXXService;

    public ZhiBiaoXXController(ZhiBiaoXXService zhiBiaoXXService) {
        this.zhiBiaoXXService = zhiBiaoXXService;
    }

    /**
     * 根据指标类型代码获取指标信息列表
     */
    @GetMapping("GetZhiBiaoXXListByZBLXDM")
    @Operation(summary = "根据指标类型代码获取指标信息列表")
    public MsfResponse<List<ZhiBiaoXXListDto>> getZhiBiaoXXListByZBLXDM(@NotEmpty(message = "指标类型代码不能为空") String zhiBiaoLXDM,
                                                                        @RequestParam(required = false) String likeQuery) {
        return MsfResponse.success(zhiBiaoXXService.getZhiBiaoXXListByZBLXDM(zhiBiaoLXDM,likeQuery));
    }

    /**
     * 根据id移除单个指标
     */
    @DeleteMapping("ZuoFeiZhiBiaoByID")
    @Operation(summary = "根据id移除单个指标")
    public MsfResponse<Boolean> zuoFeiZhiBiaoByID(@NotEmpty(message = "id不能为空") @RequestParam String id){
        return MsfResponse.success(zhiBiaoXXService.zuoFeiZhiBiaoByID(id));
    }
    /**
     * 根据指标类型代码和指标分类id移除该分类所有指标
     */
    @DeleteMapping("ZuoFeiZhiBiaoFL")
    @Operation(summary = "根据指标类型代码和指标分类id移除该分类所有指标")
    public MsfResponse<Boolean> zuoFeiZhiBiaoFL(@NotEmpty(message = "指标类型代码不能为空") @RequestParam String zhiBiaoLXDM,
                                                @NotEmpty(message = "指标分类ID不能为空") @RequestParam String zhiBiaoFLID){
        return MsfResponse.success(zhiBiaoXXService.zuoFeiZhiBiaoFL(zhiBiaoLXDM,zhiBiaoFLID));
    }
    /**
     * 添加指标集合
     */
    @PostMapping("AddZhiBiaoList")
    @Operation(summary = "添加指标集合")
    public MsfResponse<Boolean> addZhiBiaoList(@RequestBody List<ZhiBiaoXXCreateDto> createDtos){
        return MsfResponse.success(zhiBiaoXXService.addZhiBiaoList(createDtos));
    }
    /**
     * 新增指标信息
     */
    @PostMapping("AddZhiBiaoXX")
    @Operation(summary = "新增指标信息")
    public MsfResponse<Boolean> addZhiBiaoXX(@RequestBody ZhiBiaoXXCreateDto createDto){
        return MsfResponse.success(zhiBiaoXXService.addZhiBiaoXX(createDto));
    }
    /**
     * 修改指标信息
     */
    @PutMapping("UpdateZhiBiaoXX")
    @Operation(summary = "修改指标信息")
    public MsfResponse<Boolean> updateZhiBiaoXX(@RequestBody ZhiBiaoXXUpdateDto updateDto){
        return MsfResponse.success(zhiBiaoXXService.updateZhiBiaoXX(updateDto));
    }
}

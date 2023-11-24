package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.cyan.msf.core.response.XiTongResponseCode;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.grus.shujuzx.dto.shengmingzqpzs.SC_ZD_ShengMingZQCreateDto;
import cn.mediinfo.grus.shujuzx.dto.shengmingzqpzs.SC_ZD_ShengMingZQDto;
import cn.mediinfo.grus.shujuzx.dto.shengmingzqpzs.SC_ZD_ShengMingZQListDto;
import cn.mediinfo.grus.shujuzx.dto.shengmingzqpzs.SC_ZD_ShengMingZQUpdateDto;
import cn.mediinfo.grus.shujuzx.dto.shujuyzys.SC_ZD_ShuJuYZYDto;
import cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs.ZhiBiaoXXCreateDto;
import cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs.ZhiBiaoXXListDto;
import cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs.ZhiBiaoXXUpdateDto;
import cn.mediinfo.grus.shujuzx.service.ShengMingZQPZService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "ShengMingZQPZController", description = "生命周期配置")
@RequestMapping({"api/v1.0/shengmingzqpz", "api/v1/shengmingzqpz"})
@Slf4j
public class ShengMingZQPZController {
    private final ShengMingZQPZService shengMingZQPZService;
    public ShengMingZQPZController(ShengMingZQPZService shengMingZQPZService){
        this.shengMingZQPZService=shengMingZQPZService;
    }

    /**
     * 获取生命周期字典List
     * @return List<SC_ZD_ShengMingZQListDto>
     */
    @GetMapping("GetShengMingZQList")
    @Operation(summary = "获取生命周期字典List")
    public MsfResponse<List<SC_ZD_ShengMingZQListDto>> getShengMingZQList() {
        return MsfResponse.success(shengMingZQPZService.getShengMingZQList());
    }

    /**
     * 新增生命周期字典
     * @param createDto
     * @return
     * @throws TongYongYWException
     */
    @PostMapping("AddShengMingZQ")
    @Operation(summary = "新增生命周期字典")
    public MsfResponse<Boolean> addShengMingZQ(@RequestBody SC_ZD_ShengMingZQCreateDto createDto) throws TongYongYWException {
        if (!StringUtil.hasText(createDto.getShengMingZQMC())) {
            return MsfResponse.fail(XiTongResponseCode.CANSHUYC,"生命周期名称不能为空！");
        }
        return MsfResponse.success(shengMingZQPZService.addShengMingZQ(createDto));
    }
    /**
     * 修改指标信息
     */
    @PutMapping("UpdateShengMingZQ")
    @Operation(summary = "修改指标信息")
    public MsfResponse<Boolean> updateShengMingZQ(@RequestBody SC_ZD_ShengMingZQUpdateDto updateDto) throws TongYongYWException {
        if (!StringUtil.hasText(updateDto.getId()) || !StringUtil.hasText(updateDto.getShengMingZQMC())) {
            return MsfResponse.fail(XiTongResponseCode.CANSHUYC,"主键Id和生命周期名称不能为空！");
        }
        return MsfResponse.success(shengMingZQPZService.updateShengMingZQ(updateDto));
    }
    /**
     * 作废规则
     * @param id id
     * @return boolean
     */
    @Operation(summary = "作废规则")
    @DeleteMapping("ZuoFeiShengMingZQ")
    public MsfResponse<Boolean> zuoFeiShengMingZQ(@NotEmpty(message = "id不能为空") String id) throws TongYongYWException {
        return MsfResponse.success(shengMingZQPZService.zuoFeiShengMingZQ(id));
    }

    /**
     * 根据ID获取生命周期
     *
     * @param id
     * @return
     * @throws
     */
    @Operation(summary = "根据ID获取生命周期")
    @GetMapping("GetShengMingZQByID")
    public MsfResponse<SC_ZD_ShengMingZQDto> getShengMingZQByID(@NotEmpty(message = "id不能为空！") String id) throws TongYongYWException {
        return MsfResponse.success(shengMingZQPZService.getShengMingZQByID(id));
    }

}

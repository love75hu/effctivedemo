package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.exception.CanShuException;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.shujuylbs.SC_ZD_ShuJuYLBCreateDto;
import cn.mediinfo.grus.shujuzx.dto.shujuylbs.SC_ZD_ShuJuYLBDto;
import cn.mediinfo.grus.shujuzx.dto.shujuylbs.SC_ZD_ShuJuYLBUpdateDto;
import cn.mediinfo.grus.shujuzx.dto.shujuyzys.SC_ZD_ShuJuYZYCreateDto;
import cn.mediinfo.grus.shujuzx.dto.shujuyzys.SC_ZD_ShuJuYZYDto;
import cn.mediinfo.grus.shujuzx.dto.shujuyzys.SC_ZD_ShuJuYZYListDto;
import cn.mediinfo.grus.shujuzx.dto.shujuyzys.SC_ZD_ShuJuYZYUpdateDto;
import cn.mediinfo.grus.shujuzx.service.ShuJuYLBService;
import cn.mediinfo.grus.shujuzx.service.ShuJuYZYService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "YeWuZDController", description = "业务字典")
@RequestMapping({"api/v1.0/YeWuZD", "api/v1/YeWuZD"})
@Slf4j
@Validated
public class YeWuZDController {
    private final ShuJuYLBService shuJuYLBService;
    private final ShuJuYZYService shuJuYZYService;

    public YeWuZDController(ShuJuYLBService shuJuYLBService, ShuJuYZYService shuJuYZYService) {
        this.shuJuYLBService = shuJuYLBService;
        this.shuJuYZYService = shuJuYZYService;
    }

    /**
     * 新增数据源类别
     *
     * @param createDto
     * @return
     * @throws TongYongYWException
     */
    @Operation(summary = "新增数据源类别")
    @PostMapping("AddShuJuYLB")
    public MsfResponse<String> addShuJuYLB(@RequestBody SC_ZD_ShuJuYLBCreateDto createDto) throws TongYongYWException {
        return MsfResponse.success(shuJuYLBService.addShuJuYLB(createDto));
    }

    /**
     * 更新数据源类别
     *
     * @param updateDto
     * @return
     * @throws TongYongYWException
     * @throws WeiZhaoDSJException
     */
    @Operation(summary = "更新数据源类别")
    @PutMapping("UpdateShuJuYLB")
    public MsfResponse<String> updateShuJuYLB(@RequestBody SC_ZD_ShuJuYLBUpdateDto updateDto) throws TongYongYWException, WeiZhaoDSJException {
        return MsfResponse.success(shuJuYLBService.updateShuJuYLB(updateDto));
    }

    /**
     * 根据id获取数据源类别
     *
     * @param id
     * @return
     * @throws
     */
    @Operation(summary = "根据ID获取数据源类别")
    @GetMapping("GetShuJuYLBByID")
    public MsfResponse<SC_ZD_ShuJuYLBDto> getShuJuYLBByID(@NotEmpty(message = "主键ID必传！") String id) {
        return MsfResponse.success(shuJuYLBService.getShuJuYLBByID(id));
    }

    /**
     * 作废数据源类别
     *
     * @param id
     * @return
     * @throws TongYongYWException
     */
    @Operation(summary = "作废数据源类别")
    @DeleteMapping("ZuoFeiShuJuYLB/{id}")
    public MsfResponse<Boolean> zuoFeiShuJuYLB(@PathVariable @NotEmpty(message = "主键ID必传！") String id) throws WeiZhaoDSJException {
        return MsfResponse.success(shuJuYLBService.zuoFeiShuJuYLB(id));
    }

    /**
     * 获取数据源类别列表
     *
     * @param zuZhiJGID
     * @param likeQuery
     * @return
     */
    @Operation(summary = "获取数据源类别列表")
    @GetMapping("GetShuJuYLBList")
    public MsfResponse<List<SC_ZD_ShuJuYLBDto>> getShuJuYLBList(@RequestParam(required = false) String zuZhiJGID,
                                                                @RequestParam(required = false) String likeQuery,
                                                                @RequestParam(required = false) Integer PageIndex,
                                                                @RequestParam(required = false) Integer PageSize) {
        return MsfResponse.success(shuJuYLBService.getShuJuYLBList(zuZhiJGID, likeQuery, PageIndex, PageSize));
    }

    /**
     * 查询数据源类别数量
     *
     * @param zuZhiJGID
     * @param likeQuery
     * @return
     */
    @Operation(summary = "查询数据源类别数量")
    @GetMapping("GetShuJuYLBCount")
    public MsfResponse<Long> getShuJuYLBCount(@RequestParam(required = false) String zuZhiJGID,
                                              @RequestParam(required = false) String likeQuery) {
        return MsfResponse.success(shuJuYLBService.getShuJuYLBCount(zuZhiJGID, likeQuery));
    }

    /**
     * 新增数据源值域
     *
     * @param createDto
     * @return
     * @throws TongYongYWException
     */
    @Operation(summary = "新增数据源值域")
    @PostMapping("AddShuJuYZY")
    public MsfResponse<String> addShuJuYZY(@RequestBody SC_ZD_ShuJuYZYCreateDto createDto) throws TongYongYWException {
        return MsfResponse.success(shuJuYZYService.addShuJuYZY(createDto));
    }

    /**
     * 修改数据源值域
     *
     * @param updateDto
     * @return
     * @throws TongYongYWException
     */
    @Operation(summary = "修改数据源值域")
    @PutMapping("UpdateShuJuYZY")
    public MsfResponse<String> updateShuJuYZY(@RequestBody SC_ZD_ShuJuYZYUpdateDto updateDto) throws TongYongYWException {
        return MsfResponse.success(shuJuYZYService.updateShuJuYZY(updateDto));
    }

    /**
     * 作废数据源值域
     *
     * @param id
     * @return
     * @throws TongYongYWException
     */
    @Operation(summary = "作废数据源值域")
    @DeleteMapping("ZuoFeiShuJuYZY/{id}")
    public MsfResponse<Boolean> zuoFeiShuJuYZY(@PathVariable @NotEmpty(message = "主键ID必传！") String id) throws TongYongYWException {
        return MsfResponse.success(shuJuYZYService.zuoFeiShuJuYZY(id));
    }

    /**
     * 根据ID获取数据源值域
     *
     * @param id
     * @return
     * @throws
     */
    @Operation(summary = "根据ID获取数据源值域")
    @GetMapping("GetShuJuYZYByID")
    public MsfResponse<SC_ZD_ShuJuYZYDto> getShuJuYZYByID(@NotEmpty(message = "主键ID必传！") String id) {
        return MsfResponse.success(shuJuYZYService.getShuJuYZYByID(id));
    }

    /**
     * 批量获取数据源值域list
     *
     * @param shuJuYLBIDList
     * @return
     * @throws CanShuException
     */
    @Operation(summary = "批量获取数据源值域list")
    @GetMapping("GetShuJuYZYByLBIDList")
    public MsfResponse<List<SC_ZD_ShuJuYZYListDto>> getShuJuYZYByLBIDList(@RequestParam List<String> shuJuYLBIDList) throws CanShuException {
        return MsfResponse.success(shuJuYZYService.getShuJuYZYByLBIDList(shuJuYLBIDList));
    }

    /**
     * 根据数据源类别ID查询值域list
     *
     * @param shuJuYLBID
     * @return
     * @throws
     */
    @Operation(summary = "根据数据源类别ID查询值域list")
    @GetMapping("GetShuJuYZYListByLBID")
    public MsfResponse<List<SC_ZD_ShuJuYZYDto>> getShuJuYZYListByLBID(@RequestParam String shuJuYLBID) {
        return MsfResponse.success(shuJuYZYService.getShuJuYZYListByLBID(shuJuYLBID));
    }

    /**
     * 查询数据源值域list
     *
     * @param zuZhiJGID
     * @param likeQuery
     * @return
     * @throws WeiZhaoDSJException
     */
    @Operation(summary = "查询数据源值域list")
    @GetMapping("GetShuJuYZYList")
    public MsfResponse<List<SC_ZD_ShuJuYZYDto>> getShuJuYZYList(@RequestParam(required = false) String zuZhiJGID,
                                                                @RequestParam String shuJuYLBID,
                                                                @RequestParam(required = false) String likeQuery) throws WeiZhaoDSJException {
        return MsfResponse.success(shuJuYZYService.getShuJuYZYList(zuZhiJGID, shuJuYLBID, likeQuery, 1, 10));
    }

    /**
     * 获取数据源值域总行数
     *
     * @param zuZhiJGID
     * @param likeQuery
     * @return
     * @throws WeiZhaoDSJException
     */
    @Operation(summary = "获取数据源值域总行数")
    @GetMapping("GetShuJuYZYCount")
    public MsfResponse<Long> getShuJuYZYCount(@RequestParam (required = false) String zuZhiJGID,
                                              @RequestParam String shuJuYLBID,
                                              @RequestParam(required = false) String likeQuery) throws WeiZhaoDSJException {
        return MsfResponse.success(shuJuYZYService.getShuJuYZYCount(zuZhiJGID, shuJuYLBID, likeQuery, 1, 10));
    }
}

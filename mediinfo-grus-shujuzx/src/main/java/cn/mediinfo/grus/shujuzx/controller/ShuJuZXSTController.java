package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.grus.shujuzx.dto.ShuJuZXSTs.SC_ST_SanLiuLSTOutDto;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs.BR_DA_ZhuSuoYCZRZDto;
import cn.mediinfo.grus.shujuzx.service.ShuJuZXSTService;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.response.MsfResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@Tag(name = "ShuJuZXSTController", description = "数据中心-视图")
@RequestMapping({"api/v1.0/ShuJuZXST", "api/v1/ShuJuZXST"})
@Slf4j
@Validated
public class ShuJuZXSTController {
    private final ShuJuZXSTService shuJuZXSTService;
    public ShuJuZXSTController(ShuJuZXSTService shuJuZXSTService)
    {
        this.shuJuZXSTService=shuJuZXSTService;
    }

    /**
     * 根据相关条件获取病人基本信息列表条数
     * @return
     * @throws TongYongYWException 通用异常
     */
    @Operation(summary = "获取主索引操作日志列表")
    @GetMapping("GetBingRenYLSJCount")
    public MsfResponse<Integer> getBingRenYLSJCount(String bingRenID, String zhengJianHM, String xingMing, Date jianDangKSRQ, Date jianDangJSRQ) throws TongYongYWException, ParseException {
        var data = shuJuZXSTService.getBingRenYLSJCount(bingRenID,zhengJianHM,xingMing,jianDangKSRQ,jianDangJSRQ);
        return MsfResponse.success(data);
    }

    /**
     * 根据相关条件获取病人基本信息列表
     * @return
     * @throws TongYongYWException 通用异常
     */
    @Operation(summary = "根据相关条件获取病人基本信息列表")
    @GetMapping("GetBingRenYLSJList")
    public MsfResponse<List<SC_ST_SanLiuLSTOutDto>> getBingRenYLSJList(String bingRenID, String zhengJianHM, String xingMing, Date jianDangKSRQ, Date jianDangJSRQ, Integer pageIndex, Integer pageSize) throws TongYongYWException, ParseException {
        var data = shuJuZXSTService.getBingRenYLSJList(bingRenID,zhengJianHM,xingMing,jianDangKSRQ,jianDangJSRQ,pageIndex,pageSize);
        return MsfResponse.success(data);
    }
}

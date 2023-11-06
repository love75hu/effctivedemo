package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.shitumx.ShiTuMXZHCXDto;
import cn.mediinfo.grus.shujuzx.service.LinChuangJSSCXXZService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.patterns.IfPointcut;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * 综合查询-临床检索输出选项值
 */
@RestController
@Tag(name = "LinChuangJSSCXXZController", description = "综合查询-临床检索-输出项选择")
@RequestMapping({"api/v1.0/LinChuangJSSCXXZ", "api/v1/LinChuangJSSCXXZ"})
@Slf4j
@Validated
public class LinChuangJSSCXXZController {
    private final LinChuangJSSCXXZService _linChuangJSSCXXZService;
    public LinChuangJSSCXXZController(LinChuangJSSCXXZService linChuangJSSCXXZService) {
        this._linChuangJSSCXXZService = linChuangJSSCXXZService;
    }
    /**
     *综合查询输出项选择列表
     * @param yeWuLX  0全部 ,1门诊 ,2住院 ,3急诊 ,9公卫
     * @param jieKouLX 1：输出标志   0：条件标志
     * @param likeQuery 字段名称模糊查询
     * @return
     */
    @Operation(summary = "综合查询输出项选择列表")
    @GetMapping(path = "GetShuTuMXForZHCX")
    public MsfResponse<List<ShiTuMXZHCXDto>> getShuTuMXForZHCX(@RequestParam(required = false) Integer yeWuLX,
                                                               @RequestParam(required = false) Integer jieKouLX,
                                                               @RequestParam(required = false) String likeQuery) throws YuanChengException {
        if (yeWuLX == null){
            return MsfResponse.fail("业务类型不允许为空!");
        }
        if (jieKouLX == null){
            return MsfResponse.fail("接口类型不允许为空!");
        }
        List<ShiTuMXZHCXDto> list = _linChuangJSSCXXZService.getShuTuMXForZHCX(yeWuLX, jieKouLX,likeQuery);
        return MsfResponse.success(list) ;
    }
}

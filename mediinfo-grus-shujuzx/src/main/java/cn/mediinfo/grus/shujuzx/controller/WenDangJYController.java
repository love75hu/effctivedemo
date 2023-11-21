package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.wendangjys.VerifyXMLDto;
import cn.mediinfo.grus.shujuzx.service.WenDangJYService;
import cn.mediinfo.grus.shujuzx.service.WenDangPZService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RestController
@Tag(name = "WenDangJYController", description = "共享文档-文档校验")
@RequestMapping({"api/v1.0/WenDangJY", "api/v1/WenDangJY"})
@Slf4j
@Validated
public class WenDangJYController {
    private final WenDangJYService wenDangJYService;
    public WenDangJYController(WenDangJYService wenDangJYService){
        this.wenDangJYService = wenDangJYService;
    }

    
    @Operation(summary = "获取文档配置列表")
    @PostMapping("VerifyCDADoc")
    public MsfResponse<String> VerifyCDADoc(@RequestBody VerifyXMLDto dto) throws ParserConfigurationException, IOException, SAXException {
        return MsfResponse.success(wenDangJYService.VerifyCDADoc(dto));
    }
}

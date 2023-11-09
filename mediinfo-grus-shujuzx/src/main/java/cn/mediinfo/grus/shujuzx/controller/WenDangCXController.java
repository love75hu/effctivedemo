package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.cyan.msf.security.annotation.AnonymousAccess;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangDto;
import cn.mediinfo.grus.shujuzx.dto.wendangjls.SC_GW_JiLuXXListDto;
import cn.mediinfo.grus.shujuzx.dto.wendangjls.SC_ZD_WenDangGroupListDto;
import cn.mediinfo.grus.shujuzx.service.WenDangCXService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@Tag(name = "WenDangCXController", description = "文档查询")
@RequestMapping({"api/v1.0/WenDangCX", "api/v1/WenDangCX"})
@Slf4j
@Validated
public class WenDangCXController {
    private final WenDangCXService wenDangCXService;

    public WenDangCXController(WenDangCXService wenDangCXService){
        this.wenDangCXService = wenDangCXService;
    }

    /**
     * 获取文档分组列表
     */
    @Operation(summary = "获取文档分组列表")
    @GetMapping("getWenDangGroupList")
    public MsfResponse<List<SC_ZD_WenDangGroupListDto>> getWenDangGroupList() {
        return MsfResponse.success(wenDangCXService.getWenDangGroupList());
    }

    /**
     * 获取文档记录列表
     *
     * @param wenDangID
     * @param likeQueryType
     * @param likeQuery
     * @param startTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Operation(summary = "获取文档记录列表")
    @GetMapping("getWenDangJLList")
    public MsfResponse<List<SC_GW_JiLuXXListDto>> getWenDangJLList(
            @RequestParam(required = false) String wenDangID,
            @RequestParam(required = false) String likeQueryType,
            @RequestParam(required = false) String likeQuery,
            @RequestParam(required = false) Date startTime,
            @RequestParam(required = false) Date endTime,
            @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return MsfResponse.success(wenDangCXService.getWenDangJLList(wenDangID, likeQueryType,likeQuery,startTime,endTime, pageIndex, pageSize));
    }

    /**
     * 获取文档记录数量
     *
     * @param wenDangID
     * @param likeQueryType
     * @param likeQuery
     * @param startTime
     * @param endTime
     * @return
     */
    @Operation(summary = "获取文档记录数量")
    @GetMapping("GetWenDangJLCount")
    public MsfResponse<Long> getWenDangJLCount(
            @RequestParam(required = false) String wenDangID,
            @RequestParam(required = false) String likeQueryType,
            @RequestParam(required = false) String likeQuery,
            @RequestParam(required = false) Date startTime,
            @RequestParam(required = false) Date endTime) {
        return MsfResponse.success(wenDangCXService.getWenDangJLCount(wenDangID, likeQueryType,likeQuery,startTime,endTime));
    }

    /**
     * 导出文档内容
     *
     * @param yeWuIDList
     * @return
     */
    @Operation(summary = "导出文档内容")
    @GetMapping("ExportWenDangNR")
    @AnonymousAccess
    public void exportWenDangNR(
            HttpServletResponse response,
            @RequestParam(required = false) String yeWuIDList) throws IOException {
        wenDangCXService.exportWenDangNR(response,yeWuIDList);
    }

    /*@GetMapping(value = "GetTest")
    public void getTest(HttpServletResponse response) throws IOException {
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=tmp.zip");
        ZipOutputStream zos=new ZipOutputStream(response.getOutputStream());

        zos.putNextEntry(new ZipEntry("tmp.xml"));
        String content="<?xml version=\"1.0\" encoding=\"UTF-8?><root>开个玩笑</root>";
        zos.write(content.getBytes(StandardCharsets.UTF_8));
        zos.closeEntry();

        zos.close();
    }*/
}

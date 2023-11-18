package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangDto;
import cn.mediinfo.grus.shujuzx.dto.wenDang.XML_JieDian;
import cn.mediinfo.grus.shujuzx.dto.wenDang.XML_JieDianSX;
import cn.mediinfo.grus.shujuzx.dto.wendangscs.WenDangSCQueryDto;
import cn.mediinfo.grus.shujuzx.dto.wendangscs.WenDangSJJDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;


import java.io.IOException;
import java.util.List;

public interface WenDangSCService {

    /**
     * 生成共享文档数据集
     */
    List<WenDangSJJDto> getGongXiangWDSJJ(WenDangSCQueryDto dto) throws IOException, NoSuchFieldException, IllegalAccessException, YuanChengException;
    /**
     * 导出文档内容
     *
     * @param
     * @return
     */
    void exportWenDangSJJ(HttpServletResponse response, WenDangSCQueryDto dto) throws IOException, NoSuchFieldException, IllegalAccessException, YuanChengException;
}

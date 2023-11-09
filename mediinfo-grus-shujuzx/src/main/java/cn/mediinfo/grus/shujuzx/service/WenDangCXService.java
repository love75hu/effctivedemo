package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangDto;
import cn.mediinfo.grus.shujuzx.dto.wendangjls.SC_GW_JiLuXXListDto;
import cn.mediinfo.grus.shujuzx.dto.wendangjls.SC_ZD_WenDangGroupListDto;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface WenDangCXService {

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
    List<SC_GW_JiLuXXListDto> getWenDangJLList(String wenDangID,String likeQueryType, String likeQuery, Date startTime, Date endTime, Integer pageIndex, Integer pageSize);

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
    Long getWenDangJLCount(String wenDangID, String likeQueryType,String likeQuery, Date startTime, Date endTime);

    /**
     * 获取文档分组列表
     */
    List<SC_ZD_WenDangGroupListDto> getWenDangGroupList();

    /**
     * 导出文档内容
     *
     * @param yeWuIDList
     * @return
     */
    void exportWenDangNR(HttpServletResponse response,String yeWuIDList) throws IOException;
}

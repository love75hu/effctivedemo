package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.grus.shujuzx.common.fangan.condition.FangAnTreeNode;
import cn.mediinfo.grus.shujuzx.dto.fangan.FangAnByFACXLSDTO;
import cn.mediinfo.grus.shujuzx.dto.fangan.FangAnHZXXDTO;
import cn.mediinfo.grus.shujuzx.dto.fangan.FangAnQueryDTO;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnSC;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnXXSaveRequest;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnXXUpdateRequest;
import cn.mediinfo.grus.shujuzx.request.fangancxls.FangAnCXLSByIdRequest;
import cn.mediinfo.grus.shujuzx.request.result.QueryResultCountRequest;
import cn.mediinfo.grus.shujuzx.request.result.QueryResultPageRequest;

import java.util.List;

public interface FangAnService {

    /**
     * 保存方案
     *
     * @param request FangAnXXSaveRequest
     * @return String 方案id
     */
    String saveFangAn(FangAnXXSaveRequest request) throws YuanChengException;

    /**
     * 获取方案信息
     *
     * @param id 主键ID
     * @return
     * @throws YuanChengException
     */
    FangAnQueryDTO getFangAnXX(String id) throws TongYongYWException;

    /**
     * 根据方案查询历史获取方案信息
     * @param request
     * @return
     * @throws TongYongYWException
     */
    FangAnByFACXLSDTO getFangAnCXLS(FangAnCXLSByIdRequest request) throws TongYongYWException;

    /**
     * 更新方案
     *
     * @param request
     * @return
     * @throws YuanChengException
     * @throws TongYongYWException
     */
    Boolean updateFangAnXX(FangAnXXUpdateRequest request) throws YuanChengException, TongYongYWException;

    /**
     * 获取方案sql
     *
     * @param root         方案树
     * @param fangAnSCList 方案输出项
     * @param fangAnLXDM   方案类型代码
     * @param type         查询类别 0获取sql 1查询结果
     * @return
     * @throws YuanChengException
     */
    String getSql(FangAnTreeNode root, List<FangAnSC> fangAnSCList, String fangAnLXDM, Integer type) throws YuanChengException;

    /**
     * 获取结果列表总数
     * @param request
     * @return
     * @throws TongYongYWException
     */
    Long getFangAnJGCount(QueryResultCountRequest request) throws TongYongYWException;

    /**
     * 获取方案患者信息
     * @param request
     * @return
     * @throws TongYongYWException
     */
    List<FangAnHZXXDTO> getFangAnHZXXList(QueryResultPageRequest request) throws TongYongYWException;
}

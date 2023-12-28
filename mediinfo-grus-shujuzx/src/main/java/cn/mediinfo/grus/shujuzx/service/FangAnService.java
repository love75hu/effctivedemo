package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.grus.shujuzx.common.fangan.condition.FangAnTreeNode;
import cn.mediinfo.grus.shujuzx.dto.bihuanlcs.BingLiXQDto;
import cn.mediinfo.grus.shujuzx.dto.bihuanlcs.BingLiXQXXDto;
import cn.mediinfo.grus.shujuzx.dto.fangan.FangAnByFACXLSDTO;
import cn.mediinfo.grus.shujuzx.dto.fangan.FangAnQueryDTO;
import cn.mediinfo.grus.shujuzx.dto.result.BingRenJBXXDTO;
import cn.mediinfo.grus.shujuzx.dto.result.QueryResultDTO;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnSC;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnXXSaveRequest;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnXXUpdateRequest;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface FangAnService {

    /**
     * 保存方案
     *
     * @param request FangAnXXSaveRequest
     * @return String 方案id
     */
    String saveFangAn(FangAnXXSaveRequest request) throws YuanChengException, TongYongYWException;

    /**
     * 获取方案信息
     *
     * @param fangAnID 方案ID
     * @return FangAnQueryDTO
     * @throws YuanChengException
     */
    FangAnQueryDTO getFangAnXX(String fangAnID) throws TongYongYWException, WeiZhaoDSJException;

    /**
     * 根据方案查询历史获取方案信息
     *
     * @param fangAnCXLSId 方案查询历史id
     * @return FangAnByFACXLSDTO
     * @throws TongYongYWException
     * @throws WeiZhaoDSJException
     */
    FangAnByFACXLSDTO getFangAnCXLS(String fangAnCXLSId) throws TongYongYWException, WeiZhaoDSJException;

    /**
     * 更新方案
     *
     * @param request
     * @return Boolean
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
     * @param guanJianZi   关键字
     * @return String
     * @throws YuanChengException
     */
    String getSql(FangAnTreeNode root, List<FangAnSC> fangAnSCList, String fangAnLXDM, String guanJianZi) throws YuanChengException, TongYongYWException;

    /**
     * 获取结果列表总数
     *
     * @param fangAnCXLSId 方案查询历史id
     * @param mergeType 合并类型，1-患者，2-就诊
     * @return Long
     * @throws TongYongYWException
     */
    Long getFangAnJGCount(String fangAnCXLSId, Integer mergeType) throws TongYongYWException, YuanChengException;

    /**
     * 获取方案查询结果
     *
     * @param fangAnCXLSId 方案查询历史id
     * @param mergeType 合并类型，1-患者，2-就诊
     * @param pageIndex 页码
     * @param pageSize 每页数量
     * @param isShowBQ 是否显示标签
     * @return List<List<QueryResultDTO>>
     * @throws TongYongYWException
     */
    List<List<QueryResultDTO>> getFangAnJGList(String fangAnCXLSId, Integer mergeType, Integer pageIndex, Integer pageSize, boolean isShowBQ) throws TongYongYWException, YuanChengException;

    /**
     * 获取方案结果Excel
     *
     * @param fangAnCXLSId 方案查询历史id
     * @param mergeType 合并类型，1-患者，2-就诊
     * @param pageSize 页码
     * @return List<XSSFWorkbook>
     * @throws TongYongYWException
     * @throws RuntimeException
     */
    List<ByteArrayOutputStream> getFangAnJGExcelList(String fangAnCXLSId, Integer mergeType, Integer pageSize) throws TongYongYWException, RuntimeException, YuanChengException;

    /**
     * 获取方案患者信息
     *
     * @param fangAnCXLSId 方案查询历史id
     * @param pageIndex 页码
     * @param pageSize 每页数量
     * @return List<BingRenJBXXDTO>
     * @throws TongYongYWException
     */
    List<BingRenJBXXDTO> getBinRenJBXXList(String fangAnCXLSId, Integer pageIndex, Integer pageSize) throws TongYongYWException, YuanChengException;

    /**
     * 获取方案患者信息数量
     * @param fangAnCXLSId 方案查询历史id
     * @return Long
     * @throws TongYongYWException
     */
    Long getBinRenJBXXCount(String fangAnCXLSId) throws TongYongYWException, YuanChengException;

    List<BingLiXQXXDto> getBingLiXQ(BingLiXQDto bingLiXQDto) throws YuanChengException;
}

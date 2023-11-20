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
    FangAnQueryDTO getFangAnXX(String id) throws TongYongYWException, WeiZhaoDSJException;

    /**
     * 根据方案查询历史获取方案信息
     * @param fangAnCXLSId
     * @return
     * @throws TongYongYWException
     */
    FangAnByFACXLSDTO getFangAnCXLS(String fangAnCXLSId) throws TongYongYWException, WeiZhaoDSJException;

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
     * @param guanJianZi   关键字
     * @return
     * @throws YuanChengException
     */
    String getSql(FangAnTreeNode root, List<FangAnSC> fangAnSCList, String fangAnLXDM, String guanJianZi) throws YuanChengException;

    /**
     * 获取结果列表总数
     *
     * @param fangAnCXLSId
     * @param mergeType
     * @return
     * @throws TongYongYWException
     */
    Long getFangAnJGCount(String fangAnCXLSId, Integer mergeType) throws TongYongYWException;

    /**
     * 获取方案查询结果
     *
     * @param fangAnCXLSId
     * @param mergeType
     * @param pageIndex
     * @param pageSize
     * @param isShowBQ
     * @return
     * @throws TongYongYWException
     */
    List<List<QueryResultDTO>> getFangAnJGList(String fangAnCXLSId, Integer mergeType, Integer pageIndex, Integer pageSize, boolean isShowBQ) throws TongYongYWException;

    /**
     * 获取方案患者信息
     *
     * @param fangAnCXLSId
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws TongYongYWException
     */
    List<BingRenJBXXDTO> getBinRenJBXXList(String fangAnCXLSId, Integer pageIndex, Integer pageSize) throws TongYongYWException, YuanChengException;

    /**
     * 获取方案患者信息数量
     * @param fangAnCXLSId
     * @return
     * @throws TongYongYWException
     */
    Long getBinRenJBXXCount(String fangAnCXLSId) throws TongYongYWException, YuanChengException;

    List<BingLiXQXXDto> getBingLiXQ(BingLiXQDto bingLiXQDto) throws YuanChengException;
}

package cn.mediinfo.grus.shujuzx.manager;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.grus.shujuzx.dto.fangan.FangAnQueryDTO;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnXXSaveRequest;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnXXUpdateRequest;

public interface FangAnManager {

    /**
     * 根据方案主键ID获取方案信息
     *
     * @param id
     * @return
     * @throws TongYongYWException
     */
    FangAnQueryDTO getFangAnXXByID(String id) throws TongYongYWException;

    /**
     * 根据方案ID获取方案信息
     *
     * @param fangAnId
     * @return
     * @throws TongYongYWException
     */
    FangAnQueryDTO getFangAnXXByFAID(String fangAnId) throws TongYongYWException;

    /**
     * 保存方案
     *
     * @param request FangAnXXSaveRequest
     * @param sql     sql语句
     * @return 方案id
     */
    String saveFangAn(FangAnXXSaveRequest request, String sql);

    /**
     * 更新方案信息
     *
     * @param request
     * @param sql
     * @return
     * @throws TongYongYWException
     */
    Boolean updateFangAnXX(FangAnXXUpdateRequest request, String sql) throws TongYongYWException;
}

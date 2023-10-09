package cn.mediinfo.grus.shujuzx.manager;

import cn.mediinfo.grus.shujuzx.request.fangan.FangAnXXSaveRequest;

public interface FangAnManager {

    /**
     *  保存方案
     * @param request FangAnXXSaveRequest
     * @param sql sql语句
     * @return 方案id
     */
    String saveFangAn(FangAnXXSaveRequest request, String sql);
}

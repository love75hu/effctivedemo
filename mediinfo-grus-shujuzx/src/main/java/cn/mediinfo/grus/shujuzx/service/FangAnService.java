package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.common.fangan.condition.FangAnTreeNode;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnSC;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnXXSaveRequest;

import java.util.List;

public interface FangAnService {

    /**
     * 保存方案
     * @param request FangAnXXSaveRequest
     * @return String 方案id
     */
    String saveFangAn(FangAnXXSaveRequest request);

    /**
     * 获取方案sql
     * @param root 方案树
     * @param fangAnSCList 方案输出项
     * @return sql
     */
    String getSql(FangAnTreeNode root, List<FangAnSC> fangAnSCList);


}

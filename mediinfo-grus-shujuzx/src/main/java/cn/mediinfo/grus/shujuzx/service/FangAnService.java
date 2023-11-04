package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.grus.shujuzx.common.fangan.condition.FangAnTreeNode;
import cn.mediinfo.grus.shujuzx.dto.fangansc.FangAnSCDTO;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnSC;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnXXSaveRequest;

import java.util.List;

public interface FangAnService {

    /**
     * 保存方案
     * @param request FangAnXXSaveRequest
     * @return String 方案id
     */
    String saveFangAn(FangAnXXSaveRequest request) throws YuanChengException;

    /**
     * 获取方案sql
     * @param root 方案树
     * @param fangAnSCList 方案输出项
     * @param fangAnLXDM 方案类型代码
     * @param type 查询类别 0获取sql 1查询结果
     * @return
     * @throws YuanChengException
     */
    String getSql(FangAnTreeNode root, List<FangAnSC> fangAnSCList, String fangAnLXDM, Integer type) throws YuanChengException;


}

package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.fangansc.FangAnSCDTO;
import cn.mediinfo.grus.shujuzx.dto.fangansc.FangAnSCFieldDTO;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnSC;

import java.util.List;
import java.util.Set;

public interface FangAnSCService {

    /**
     * 查询方案所有输出项
     * @param fangAnSCList List
     * @return FangAnSCDTO 输出项
     */
    FangAnSCDTO getAllFangAnSC(List<FangAnSC> fangAnSCList);

    /**
     * 查询方案输出字段id
     * @param fangAnSCIds 方案输出id
     * @return List 方案输出字段
     */
    List<FangAnSCFieldDTO> listFangAnSCFieldByIds(Set<String> fangAnSCIds);
}

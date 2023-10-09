package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.bo.RelatedFangAnBO;
import cn.mediinfo.grus.shujuzx.dto.fangannr.FangAnSqlDTO;

import java.util.List;

public interface FangAnNRService {

    /**
     * 查询关联方案信息
     * @param relatedFangAnList RelatedFangAnBO
     * @return List
     */
    List<FangAnSqlDTO>  listFangAnSql(List<RelatedFangAnBO> relatedFangAnList);
}

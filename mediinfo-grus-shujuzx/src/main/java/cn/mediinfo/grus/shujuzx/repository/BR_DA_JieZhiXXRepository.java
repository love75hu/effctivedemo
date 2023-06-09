package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.BR_DA_JieZhiXXModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;

import java.util.List;

public interface BR_DA_JieZhiXXRepository extends MsfJpaRepository<BR_DA_JieZhiXXModel, String> {
    List<BR_DA_JieZhiXXModel> findByBingRenIDOrBingRenIDIn(String bingRenID, List<String> bingRenIDs);
    List<BR_DA_JieZhiXXModel> findByBingRenIDIn(List<String> bingRenIDs);
}

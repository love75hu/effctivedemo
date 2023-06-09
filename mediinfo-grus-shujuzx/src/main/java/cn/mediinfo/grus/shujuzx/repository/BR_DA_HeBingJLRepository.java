package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.BR_DA_HeBingJLModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;

import java.util.List;

public interface BR_DA_HeBingJLRepository extends MsfJpaRepository<BR_DA_HeBingJLModel, String> {
    List<BR_DA_HeBingJLModel> findByBingRenIDIn(List<String> bingRenIDs);
    BR_DA_HeBingJLModel findFirstByBingRenID(String bingRenID);
    List<BR_DA_HeBingJLModel> findByBingRenIDNotAndBingRenIDIn(String bingRenID,List<String> bingRenIDList);
}

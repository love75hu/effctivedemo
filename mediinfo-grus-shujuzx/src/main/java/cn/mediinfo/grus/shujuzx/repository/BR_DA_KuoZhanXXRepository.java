package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.BR_DA_KuoZhanXXModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;

import java.util.List;

public interface BR_DA_KuoZhanXXRepository extends MsfJpaRepository<BR_DA_KuoZhanXXModel, String>{
    List<BR_DA_KuoZhanXXModel> findByBingRenIDIn(List<String> bingRenIDs);
}

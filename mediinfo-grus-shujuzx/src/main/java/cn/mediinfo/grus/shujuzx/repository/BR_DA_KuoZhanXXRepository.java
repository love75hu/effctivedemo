package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.BR_DA_KuoZhanXXModel;
import cn.mediinfo.grus.shujuzx.model.QBR_DA_KuoZhanXXModel;

import java.util.List;
@MsfDataSource("datasourcebr")
public interface BR_DA_KuoZhanXXRepository extends MsfJpaRepository<QBR_DA_KuoZhanXXModel,BR_DA_KuoZhanXXModel, String> {
    List<BR_DA_KuoZhanXXModel> findByBingRenIDIn(List<String> bingRenIDs);
}

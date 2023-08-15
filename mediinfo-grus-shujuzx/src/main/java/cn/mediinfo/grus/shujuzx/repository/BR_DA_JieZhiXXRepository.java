package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.BR_DA_JieZhiXXModel;
import cn.mediinfo.grus.shujuzx.model.QBR_DA_JieZhiXXModel;

import java.util.List;
@MsfDataSource("datasourcebr")
public interface BR_DA_JieZhiXXRepository extends MsfJpaRepository<QBR_DA_JieZhiXXModel,BR_DA_JieZhiXXModel, String> {
    List<BR_DA_JieZhiXXModel> findByBingRenIDOrBingRenIDIn(String bingRenID, List<String> bingRenIDs);
    List<BR_DA_JieZhiXXModel> findByBingRenIDIn(List<String> bingRenIDs);
}

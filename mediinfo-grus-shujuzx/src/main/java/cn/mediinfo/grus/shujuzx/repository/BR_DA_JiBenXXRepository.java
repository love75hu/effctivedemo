package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.BR_DA_JiBenXXModel;
import cn.mediinfo.grus.shujuzx.model.QBR_DA_JiBenXXModel;
@MsfDataSource("datasourcebr")
public interface BR_DA_JiBenXXRepository extends MsfJpaRepository<QBR_DA_JiBenXXModel,BR_DA_JiBenXXModel, String> {

}

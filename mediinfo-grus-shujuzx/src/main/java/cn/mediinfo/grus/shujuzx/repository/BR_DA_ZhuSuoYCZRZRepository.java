package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.BR_DA_ZhuSuoYCZRZModel;
import cn.mediinfo.grus.shujuzx.model.QBR_DA_ZhuSuoYCZRZModel;

@MsfDataSource("datasourcebr")
public interface BR_DA_ZhuSuoYCZRZRepository extends MsfJpaRepository<QBR_DA_ZhuSuoYCZRZModel,BR_DA_ZhuSuoYCZRZModel, String> {
}

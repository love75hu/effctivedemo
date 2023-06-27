package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.BR_DA_JiBenXXModel;
import cn.mediinfo.grus.shujuzx.model.QBR_DA_JiBenXXModel;
import cn.mediinfo.grus.shujuzx.model.QBR_DA_JiaoChaSYModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;
import cn.mediinfo.starter.base.orm.jpa.MsfDataSource;

@MsfDataSource("datasourcebr")
public interface BR_DA_JiBenXXRepository extends MsfJpaRepository<QBR_DA_JiBenXXModel,BR_DA_JiBenXXModel, String>{

}

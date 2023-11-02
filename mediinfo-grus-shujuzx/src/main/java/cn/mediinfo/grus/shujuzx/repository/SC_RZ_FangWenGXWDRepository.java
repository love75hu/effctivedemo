package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_RZ_FangWenGXWDModel;
import cn.mediinfo.grus.shujuzx.model.SC_RZ_FangWenGXWDModel;

@MsfDataSource("datasourcesjzx")
public interface SC_RZ_FangWenGXWDRepository extends MsfJpaRepository<QSC_RZ_FangWenGXWDModel, SC_RZ_FangWenGXWDModel, String> {

}

package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_GW_JiLuNRModel;
import cn.mediinfo.grus.shujuzx.model.SC_GW_JiLuNRModel;

@MsfDataSource("datasourcesjzx")
public interface SC_GW_JiLuNRRepository extends MsfJpaRepository<QSC_GW_JiLuNRModel, SC_GW_JiLuNRModel,String> {
}

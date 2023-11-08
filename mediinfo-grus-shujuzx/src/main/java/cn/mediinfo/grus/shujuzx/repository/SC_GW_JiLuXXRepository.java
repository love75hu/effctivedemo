package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_GW_JiLuXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_GW_JiLuXXModel;

@MsfDataSource("datasourcesjzx")
public interface SC_GW_JiLuXXRepository extends MsfJpaRepository<QSC_GW_JiLuXXModel, SC_GW_JiLuXXModel, String> {
}

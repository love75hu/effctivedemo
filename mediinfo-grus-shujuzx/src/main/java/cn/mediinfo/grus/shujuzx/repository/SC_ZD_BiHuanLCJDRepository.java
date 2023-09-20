package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_BiHuanLCJDModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_BiHuanLCJDModel;

@MsfDataSource("datasourcesjzx")
public interface SC_ZD_BiHuanLCJDRepository extends MsfJpaRepository<QSC_ZD_BiHuanLCJDModel,SC_ZD_BiHuanLCJDModel, String> {
}

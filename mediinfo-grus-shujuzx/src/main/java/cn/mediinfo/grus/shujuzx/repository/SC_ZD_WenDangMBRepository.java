package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_WenDangMBModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_WenDangMBModel;

@MsfDataSource("datasourcesjzx")
public interface SC_ZD_WenDangMBRepository extends MsfJpaRepository<QSC_ZD_WenDangMBModel, SC_ZD_WenDangMBModel, String> {
}

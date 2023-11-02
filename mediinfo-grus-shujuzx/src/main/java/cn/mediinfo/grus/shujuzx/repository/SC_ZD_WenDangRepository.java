package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_RZ_FangWenLCSJModel;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_WenDangModel;
import cn.mediinfo.grus.shujuzx.model.SC_RZ_FangWenLCSJModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_WenDangModel;

@MsfDataSource("datasourcesjzx")
public interface SC_ZD_WenDangRepository extends MsfJpaRepository<QSC_ZD_WenDangModel, SC_ZD_WenDangModel, String> {
}

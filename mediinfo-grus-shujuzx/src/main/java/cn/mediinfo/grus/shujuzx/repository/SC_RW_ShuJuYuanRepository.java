package cn.mediinfo.grus.shujuzx.repository;


import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_RW_ShuJuYuanModel;
import cn.mediinfo.grus.shujuzx.model.SC_RW_ShuJuYuanModel;

@MsfDataSource("datasourcesjzx")
public interface SC_RW_ShuJuYuanRepository extends MsfJpaRepository<QSC_RW_ShuJuYuanModel, SC_RW_ShuJuYuanModel, String> {

}

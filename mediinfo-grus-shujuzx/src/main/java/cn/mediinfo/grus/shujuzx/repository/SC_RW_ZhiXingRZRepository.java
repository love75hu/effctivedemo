package cn.mediinfo.grus.shujuzx.repository;


import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_RW_ZhiXingRZModel;
import cn.mediinfo.grus.shujuzx.model.SC_RW_ZhiXingRZModel;

@MsfDataSource("datasourcesjzx")
public interface SC_RW_ZhiXingRZRepository extends MsfJpaRepository<QSC_RW_ZhiXingRZModel, SC_RW_ZhiXingRZModel, String> {
}

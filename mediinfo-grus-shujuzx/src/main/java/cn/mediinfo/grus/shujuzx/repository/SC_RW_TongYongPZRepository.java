package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_RW_TongYongPZModel;
import cn.mediinfo.grus.shujuzx.model.SC_RW_TongYongPZModel;

@MsfDataSource("datasourcesjzx")
public interface SC_RW_TongYongPZRepository extends MsfJpaRepository<QSC_RW_TongYongPZModel, SC_RW_TongYongPZModel, String> {
}

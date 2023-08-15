package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_ShuJuYLBModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_ShuJuYLBModel;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;

@MsfDataSource("datasourcesjzx")
public interface SC_ZD_ShuJuYLBRepository extends MsfJpaRepository<QSC_ZD_ShuJuYLBModel, SC_ZD_ShuJuYLBModel, String> {
    Boolean existsByShuJuYLBID(String shuJuYLBID);

    Boolean existsByIdIsNotAndShuJuYLBID(String id, String ShuJuYLBID);
}

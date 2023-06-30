package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.QSC_ZD_ShuJuYLBModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_ShuJuYLBModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;
import cn.mediinfo.starter.base.orm.jpa.MsfDataSource;

@MsfDataSource("datasourcesjzx")
public interface SC_ZD_ShuJuYLBRepository extends MsfJpaRepository<QSC_ZD_ShuJuYLBModel, SC_ZD_ShuJuYLBModel, String> {
    Boolean existsByShuJuYLBID(String shuJuYLBID);

    Boolean existsByIdIsNotAndShuJuYLBID(String id, String ShuJuYLBID);
}

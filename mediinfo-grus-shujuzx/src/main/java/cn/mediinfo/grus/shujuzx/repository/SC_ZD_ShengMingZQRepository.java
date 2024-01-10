package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;

import cn.mediinfo.grus.shujuzx.model.QSC_ZD_ShengMingZQModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_ShengMingZQModel;

import java.util.List;

@MsfDataSource("datasourcesjzx")
public interface SC_ZD_ShengMingZQRepository extends MsfJpaRepository<QSC_ZD_ShengMingZQModel, SC_ZD_ShengMingZQModel, String> {

    Boolean existsByIdIsNotAndShengMingZQMC(String id, String ShengMingZQMC);

}

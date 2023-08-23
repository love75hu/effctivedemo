package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_SC_ShouCangJXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_SC_ShouCangJXXModel;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;

@MsfDataSource("datasourcesjzx")
public interface SC_SC_ShouCangJXXRepository extends MsfJpaRepository<QSC_SC_ShouCangJXXModel,SC_SC_ShouCangJXXModel, String> {
    Boolean existsByShouCangJMCAndYongHuID(String shouCangJMC,String yongHuID);

}

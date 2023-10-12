package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_ShiTuJDMXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ShiTuJDMXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@MsfDataSource("datasourcesjzx")
public interface SC_BH_ShiTuJDMXRepository extends MsfJpaRepository<QSC_BH_ShiTuJDMXModel, SC_BH_ShiTuJDMXModel, String>, JpaSpecificationExecutor<SC_BH_ShiTuJDMXModel> {
}
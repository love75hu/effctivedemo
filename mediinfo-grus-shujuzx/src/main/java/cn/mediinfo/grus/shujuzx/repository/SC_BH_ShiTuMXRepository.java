package cn.mediinfo.grus.shujuzx.repository;


import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_ShiTuMXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ShiTuMXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@MsfDataSource("datasourcesjzx")
public interface SC_BH_ShiTuMXRepository extends MsfJpaRepository<QSC_BH_ShiTuMXModel, SC_BH_ShiTuMXModel, String>, JpaSpecificationExecutor<SC_BH_ShiTuMXModel> {
}
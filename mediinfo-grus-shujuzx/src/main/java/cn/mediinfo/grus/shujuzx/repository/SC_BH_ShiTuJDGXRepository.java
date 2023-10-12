package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_ShiTuJDGXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ShiTuJDGXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@MsfDataSource("datasourcesjzx")
public interface SC_BH_ShiTuJDGXRepository extends MsfJpaRepository<QSC_BH_ShiTuJDGXModel, SC_BH_ShiTuJDGXModel, String>, JpaSpecificationExecutor<SC_BH_ShiTuJDGXModel> {
}
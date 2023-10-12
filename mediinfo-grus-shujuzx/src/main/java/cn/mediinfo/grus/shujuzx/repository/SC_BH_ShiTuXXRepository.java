package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_ShiTuXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ShiTuXXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@MsfDataSource("datasourcesjzx")
public interface SC_BH_ShiTuXXRepository extends MsfJpaRepository<QSC_BH_ShiTuXXModel, SC_BH_ShiTuXXModel, String>, JpaSpecificationExecutor<SC_BH_ShiTuXXModel> {
}
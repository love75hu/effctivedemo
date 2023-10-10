package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_CX_ShiTuMXGXModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ShiTuMXGXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@MsfDataSource("datasourcesjzx")
public interface SC_CX_ShiTuMXGXRepository extends MsfJpaRepository<QSC_CX_ShiTuMXGXModel, SC_CX_ShiTuMXGXModel, String>, JpaSpecificationExecutor<SC_CX_ShiTuMXGXModel> {
}
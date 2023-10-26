package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_RuCanXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_RuCanXXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
@MsfDataSource("datasourcesjzx")
public interface SC_BH_RuCanXXRepository extends MsfJpaRepository<QSC_BH_RuCanXXModel, SC_BH_RuCanXXModel, String>, JpaSpecificationExecutor<SC_BH_RuCanXXModel> {
}
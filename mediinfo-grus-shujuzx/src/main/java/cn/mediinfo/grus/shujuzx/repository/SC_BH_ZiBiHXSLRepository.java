package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_ZiBiHXSLModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ZiBiHXSLModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SC_BH_ZiBiHXSLRepository extends MsfJpaRepository<QSC_BH_ZiBiHXSLModel, SC_BH_ZiBiHXSLModel, String>, JpaSpecificationExecutor<SC_BH_ZiBiHXSLModel> {
}
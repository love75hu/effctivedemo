package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_ZiBiHXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ZiBiHXXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SC_BH_ZiBiHXXRepository extends MsfJpaRepository<QSC_BH_ZiBiHXXModel, SC_BH_ZiBiHXXModel, String>, JpaSpecificationExecutor<SC_BH_ZiBiHXXModel> {
}
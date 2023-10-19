package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_JieDianXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_JieDianXXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SC_BH_JieDianXXRepository extends MsfJpaRepository<QSC_BH_JieDianXXModel, SC_BH_JieDianXXModel, String>, JpaSpecificationExecutor<SC_BH_JieDianXXModel> {
}
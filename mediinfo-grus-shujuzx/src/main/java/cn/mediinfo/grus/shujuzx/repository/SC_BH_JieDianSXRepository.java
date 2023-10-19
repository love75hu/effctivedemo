package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_JieDianSXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_JieDianSXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SC_BH_JieDianSXRepository extends MsfJpaRepository<QSC_BH_JieDianSXModel, SC_BH_JieDianSXModel, String>, JpaSpecificationExecutor<SC_BH_JieDianSXModel> {
}
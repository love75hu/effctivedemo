package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_JIBENXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_JIBENXXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SC_BH_JIBENXXRepository extends MsfJpaRepository<QSC_BH_JIBENXXModel, SC_BH_JIBENXXModel, String>, JpaSpecificationExecutor<SC_BH_JIBENXXModel> {
}
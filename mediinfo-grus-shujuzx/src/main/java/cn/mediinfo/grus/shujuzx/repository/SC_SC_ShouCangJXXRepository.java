package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.QSC_SC_ShouCangJXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_SC_ShouCangJXXModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;

public interface SC_SC_ShouCangJXXRepository extends MsfJpaRepository<QSC_SC_ShouCangJXXModel,SC_SC_ShouCangJXXModel, String> {
    Boolean existsByShouCangJMC(String shouCangJMC);

}

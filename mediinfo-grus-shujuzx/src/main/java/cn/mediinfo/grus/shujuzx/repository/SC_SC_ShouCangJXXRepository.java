package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.SC_SC_ShouCangJXXModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;

public interface SC_SC_ShouCangJXXRepository extends MsfJpaRepository<SC_SC_ShouCangJXXModel, String> {
    Boolean existsByShouCangJMC(String shouCangJMC);

}

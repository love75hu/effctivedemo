package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.QSC_RZ_FangWenLCSJModel;
import cn.mediinfo.grus.shujuzx.model.SC_RZ_FangWenLCSJModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;
import cn.mediinfo.starter.base.orm.jpa.MsfDataSource;

@MsfDataSource("datasourcesjzx")
public interface SC_RZ_FangWenLCSJRepository extends MsfJpaRepository<QSC_RZ_FangWenLCSJModel,SC_RZ_FangWenLCSJModel, String> {
}

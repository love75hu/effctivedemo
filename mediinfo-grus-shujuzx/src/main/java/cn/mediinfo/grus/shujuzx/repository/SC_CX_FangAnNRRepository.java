package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_CX_FangAnNRModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnNRModel;

@MsfDataSource("datasourcesjzx")
public interface SC_CX_FangAnNRRepository extends MsfJpaRepository<QSC_CX_FangAnNRModel, SC_CX_FangAnNRModel, String> {
    SC_CX_FangAnNRModel findByFangAnID(String fangAnID);
}

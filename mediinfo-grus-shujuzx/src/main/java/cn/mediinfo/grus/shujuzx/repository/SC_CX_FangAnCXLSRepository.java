package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_CX_FangAnCXLSModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnCXLSModel;
@MsfDataSource("datasourcesjzx")
public interface SC_CX_FangAnCXLSRepository extends MsfJpaRepository<QSC_CX_FangAnCXLSModel, SC_CX_FangAnCXLSModel, String> {
}

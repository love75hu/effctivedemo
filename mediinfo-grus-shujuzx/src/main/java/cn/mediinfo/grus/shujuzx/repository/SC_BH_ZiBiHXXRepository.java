package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_ZiBiHXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ZiBiHXXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
@MsfDataSource("datasourcesjzx")
public interface SC_BH_ZiBiHXXRepository extends MsfJpaRepository<QSC_BH_ZiBiHXXModel, SC_BH_ZiBiHXXModel, String>, JpaSpecificationExecutor<SC_BH_ZiBiHXXModel> {
 default List<SC_BH_ZiBiHXXModel>  getZiBiHXXList(List<String> biHuanID)
 {
     return asQuerydsl().where(n->n.biHuanID.in(biHuanID)).fetch();
 }
}
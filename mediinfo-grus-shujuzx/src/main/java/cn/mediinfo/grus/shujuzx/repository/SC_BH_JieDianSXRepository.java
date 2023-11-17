package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_JieDianSXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_JieDianSXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@MsfDataSource("datasourcesjzx")
public interface SC_BH_JieDianSXRepository extends MsfJpaRepository<QSC_BH_JieDianSXModel, SC_BH_JieDianSXModel, String>, JpaSpecificationExecutor<SC_BH_JieDianSXModel> {
    default List<SC_BH_JieDianSXModel> jieDianSXList(List<String> biHuanID) {
        return asQuerydsl().where(n -> n.biHuanID.in(biHuanID)).fetch();
    }

    List<SC_BH_JieDianSXModel>  findByBiHuanIDAndZuZhiJGIDAndZuZhiJGMC(String biHuanID,String zuZhiJGID,String zuZhiJGMC);
}
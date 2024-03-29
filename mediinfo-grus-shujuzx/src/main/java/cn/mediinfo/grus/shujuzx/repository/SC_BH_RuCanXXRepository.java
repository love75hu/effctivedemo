package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_RuCanXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_RuCanXXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@MsfDataSource("datasourcesjzx")
public interface SC_BH_RuCanXXRepository extends MsfJpaRepository<QSC_BH_RuCanXXModel, SC_BH_RuCanXXModel, String>, JpaSpecificationExecutor<SC_BH_RuCanXXModel> {


    List<SC_BH_RuCanXXModel> findByBiHuanID(String biHuanID);

    List<SC_BH_RuCanXXModel> findByBiHuanIDAndZuZhiJGID(String biHuanID,String zuZhiJGID);

    List<SC_BH_RuCanXXModel> findByBiHuanIDInAndZuZhiJGID(List<String> biHuanIDs,String zuZhiJGID);
    List<SC_BH_RuCanXXModel> findByBiHuanIDIn(List<String> biHuanIDs);

    default long deleteByBiHuanIDAndZuZhiJGID(String biHuanID,String zuZhiJGID)
    {
        return this.asDeleteDsl().where(n->n.biHuanID.eq(biHuanID).and(n.zuZhiJGID.eq(zuZhiJGID))).execute();
    }
}
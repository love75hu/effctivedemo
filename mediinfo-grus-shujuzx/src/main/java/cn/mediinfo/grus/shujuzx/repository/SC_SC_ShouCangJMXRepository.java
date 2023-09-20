package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_SC_ShouCangJMXModel;
import cn.mediinfo.grus.shujuzx.model.SC_SC_ShouCangJMXModel;

import java.util.List;
@MsfDataSource("datasourcesjzx")
public interface SC_SC_ShouCangJMXRepository extends MsfJpaRepository<QSC_SC_ShouCangJMXModel,SC_SC_ShouCangJMXModel, String> {
    List<SC_SC_ShouCangJMXModel> findByShouCangJID(String shouCangJID);

    List<SC_SC_ShouCangJMXModel> findByShouCangJIDAndZuZhiJGID(String shouCangJID, String jiGouID);
    List<SC_SC_ShouCangJMXModel> findByShouCangRIDAndBingRenIDIn(String shouCangRID,List<String> bingRenIDs);
}

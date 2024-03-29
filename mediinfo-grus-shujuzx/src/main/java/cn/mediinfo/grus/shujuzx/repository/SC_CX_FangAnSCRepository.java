package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_CX_FangAnSCModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnSCModel;

import java.util.List;
import java.util.Set;

@MsfDataSource("datasourcesjzx")
public interface SC_CX_FangAnSCRepository extends MsfJpaRepository<QSC_CX_FangAnSCModel, SC_CX_FangAnSCModel, String> {
    List<SC_CX_FangAnSCModel> findByFangAnID(String fangAnID);

    List<SC_CX_FangAnSCModel> findByIdIn(Set<String> idList);


    List<SC_CX_FangAnSCModel> findByFangAnIDIn(List<String> fangAnIDList);
}

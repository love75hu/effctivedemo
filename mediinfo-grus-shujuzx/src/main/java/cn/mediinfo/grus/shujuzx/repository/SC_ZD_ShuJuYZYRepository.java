package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.QSC_ZD_ShuJuYZYModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_ShuJuYZYModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;
import cn.mediinfo.starter.base.orm.jpa.MsfDataSource;

import java.util.List;
@MsfDataSource("datasourcesjzx")
public interface SC_ZD_ShuJuYZYRepository extends MsfJpaRepository<QSC_ZD_ShuJuYZYModel,SC_ZD_ShuJuYZYModel, String> {
    Boolean existsByShuJuYLBIDAndZhiYuID(String shuJuYLBID, String zhiYuID);

    Boolean existsByIdIsNotAndShuJuYLBIDAndZhiYuID(String id, String ShuJuYLBID, String zhiYuID);

    List<SC_ZD_ShuJuYZYModel> findByZuZhiJGIDAndShuJuYLBID(String zuZhiJGID, String shuJuYLBID);

    List<SC_ZD_ShuJuYZYModel> findByShuJuYLBIDIn(List<String> shuJuYLBIDs);

    List<SC_ZD_ShuJuYZYModel> findByShuJuYLBID(String shuJuYLBID);
}

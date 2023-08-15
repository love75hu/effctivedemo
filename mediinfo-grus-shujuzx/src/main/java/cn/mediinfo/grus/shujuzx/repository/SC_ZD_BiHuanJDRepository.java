package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_BiHuanJDModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_BiHuanJDModel;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;

import java.util.List;
@MsfDataSource("datasourcesjzx")
public interface SC_ZD_BiHuanJDRepository extends MsfJpaRepository<QSC_ZD_BiHuanJDModel,SC_ZD_BiHuanJDModel, String> {

    List<SC_ZD_BiHuanJDModel> findByZuZhiJGID(String zuZhiJGID);

    Boolean existsByZuZhiJGIDAndBiHuanLXDMAndJieDianMC(String zuZhiJGID, String biHuanLXDM, String jieDianMC);

    Boolean existsByZuZhiJGIDAndJieDianID(String zuZhiJGID, String jieDianID);
}

package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.SC_ZD_BiHuanJDModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;

import java.util.List;

public interface SC_ZD_BiHuanJDRepository extends MsfJpaRepository<SC_ZD_BiHuanJDModel, String> {

    List<SC_ZD_BiHuanJDModel> findByZuZhiJGID(String zuZhiJGID);

    Boolean existsByZuZhiJGIDAndBiHuanLXDMAndJieDianMC(String zuZhiJGID, String biHuanLXDM, String jieDianMC);

    Boolean existsByZuZhiJGIDAndJieDianID(String zuZhiJGID, String jieDianID);
}

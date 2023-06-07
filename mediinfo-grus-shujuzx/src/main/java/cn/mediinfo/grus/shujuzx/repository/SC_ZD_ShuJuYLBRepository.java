package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.SC_ZD_ShuJuYLBModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;

public interface SC_ZD_ShuJuYLBRepository extends MsfJpaRepository<SC_ZD_ShuJuYLBModel, String> {
    Boolean existsByShuJuYLBID(String shuJuYLBID);

    Boolean existsByIdIsNotAndShuJuYLBID(String id, String ShuJuYLBID);
}

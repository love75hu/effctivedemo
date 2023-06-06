package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.SC_ZD_YinSiPZModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;

import java.util.List;

public interface SC_ZD_YinSiPZRepository extends MsfJpaRepository<SC_ZD_YinSiPZModel, String> {
    List<SC_ZD_YinSiPZModel> findByZuZhiJGIDAndChaXunMSDMInAndQiYongBZ(String zuZhiJGID,List<String> chaXunMSDM,Integer qiYongBZ);
}

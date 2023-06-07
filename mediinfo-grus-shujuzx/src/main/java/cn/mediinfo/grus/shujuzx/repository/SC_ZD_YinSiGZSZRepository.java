package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.SC_ZD_YinSiGZSZModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_YinSiPZModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;

import java.util.List;

public interface SC_ZD_YinSiGZSZRepository extends MsfJpaRepository<SC_ZD_YinSiGZSZModel, String> {

    /**
     *判断数据源名称是否存在
     */
    Boolean existsByShuJuYMC(String shuJuYMC);


}

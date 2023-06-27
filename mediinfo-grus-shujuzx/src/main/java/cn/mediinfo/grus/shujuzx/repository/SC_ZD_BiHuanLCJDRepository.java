package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.QSC_ZD_BiHuanLCJDModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_BiHuanLCJDModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;
import cn.mediinfo.starter.base.orm.jpa.MsfDataSource;

@MsfDataSource("datasourcesjzx")
public interface SC_ZD_BiHuanLCJDRepository extends MsfJpaRepository<QSC_ZD_BiHuanLCJDModel,SC_ZD_BiHuanLCJDModel, String> {
}

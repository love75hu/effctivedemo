package cn.mediinfo.grus.shujuzx.repository;


import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.dto.renwugls.SC_RW_JiBenXXDto;
import cn.mediinfo.grus.shujuzx.model.QSC_RW_JiBenXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_RW_JiBenXXModel;

@MsfDataSource("datasourcesjzx")
public interface SC_RW_JiBenXXRepository extends MsfJpaRepository<QSC_RW_JiBenXXModel, SC_RW_JiBenXXModel, String> {
    Boolean existsByRenWuMC(String renWuMC);
    Boolean existsByIdIsNotAndRenWuMC(String id,String renWuMC);
    SC_RW_JiBenXXModel findFirstByRenWuID(String RenWuID);
    SC_RW_JiBenXXModel findFirstByRenWuIDOrRenWuMC(String renWuID,String renWuMC);
}

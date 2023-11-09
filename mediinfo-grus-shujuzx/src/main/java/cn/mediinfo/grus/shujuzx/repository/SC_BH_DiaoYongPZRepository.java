package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.dto.bihuandy.SC_BH_DiaoYongPZDto;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_DiaoYongPZModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_DiaoYongPZModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@MsfDataSource("datasourcesjzx")
public interface SC_BH_DiaoYongPZRepository extends MsfJpaRepository<QSC_BH_DiaoYongPZModel, SC_BH_DiaoYongPZModel, String>, JpaSpecificationExecutor<SC_BH_DiaoYongPZModel> {
    default List<SC_BH_DiaoYongPZDto> getBiHuanPZList(String gongNengDDM) {
        return asQuerydsl().where(x -> x.gongNengDDM.eq(gongNengDDM)).select(SC_BH_DiaoYongPZDto.class).fetch();
    }
}

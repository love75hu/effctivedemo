package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.JieDianNRDto;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuJDMXDto;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_ShiTuJDMXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ShiTuJDMXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@MsfDataSource("datasourcesjzx")
public interface SC_BH_ShiTuJDMXRepository extends MsfJpaRepository<QSC_BH_ShiTuJDMXModel, SC_BH_ShiTuJDMXModel, String>, JpaSpecificationExecutor<SC_BH_ShiTuJDMXModel> {

    default List<JieDianNRDto> getJieDianMX(String jieDianID)
    {
        return this.asQuerydsl().where(n->n.jieDianID.eq(jieDianID)).select(JieDianNRDto.class).fetch();
    }
    default List<SC_BH_ShiTuJDMXDto> getJieDianMXs(List<String> jieDianID)
    {
        return this.asQuerydsl().where(n->n.jieDianID.in(jieDianID)).select(SC_BH_ShiTuJDMXDto.class).fetch();
    }
}
package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.GuanLianJDDto;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_ShiTuJDGXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ShiTuJDGXModel;
import com.querydsl.core.types.Expression;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@MsfDataSource("datasourcesjzx")
public interface SC_BH_ShiTuJDGXRepository extends MsfJpaRepository<QSC_BH_ShiTuJDGXModel, SC_BH_ShiTuJDGXModel, String>, JpaSpecificationExecutor<SC_BH_ShiTuJDGXModel> {
    default List<GuanLianJDDto> getGuanLianJDXX(String jieDianID)
    {
        return this.asQuerydsl().where(n->n.jieDianID.eq(jieDianID))
                .select(e->new Expression<?>[] {
                        e.guanLianJDID.as("jieDianID"),
                        e.guanLianJDMC.as("jieDianMC")
                },GuanLianJDDto.class).fetch();
    }


    List<SC_BH_ShiTuJDGXModel> findByJieDianIDIn(List<String> jieDianID);


}
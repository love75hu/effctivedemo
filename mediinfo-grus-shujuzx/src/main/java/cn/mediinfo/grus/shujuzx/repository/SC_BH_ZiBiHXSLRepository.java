package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_ZiBiHXSLDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.ZiBiHXSLDto;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_ZiBiHXSLModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ZiBiHXSLModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
@MsfDataSource("datasourcesjzx")
public interface SC_BH_ZiBiHXSLRepository extends MsfJpaRepository<QSC_BH_ZiBiHXSLModel, SC_BH_ZiBiHXSLModel, String>, JpaSpecificationExecutor<SC_BH_ZiBiHXSLModel> {
    default List<SC_BH_ZiBiHXSLModel> getZiBiHXXList(List<String> biHuanID) {
        return asQuerydsl().where(n -> n.biHuanID.in(biHuanID)).fetch();
    }

    List<SC_BH_ZiBiHXSLModel> findByBiHuanIDAndZuZhiJGIDAndZuZhiJGMC(String biHuanID, String zuZhiJGID, String zuZhiJGMC);

    List<SC_BH_ZiBiHXSLModel> findByBiHuanIDAndZuZhiJGID(String biHuanID, String zuZhiJGID);
    List<SC_BH_ZiBiHXSLModel> findByBiHuanIDIn(List<String> biHuanID);

    default List<SC_BH_ZiBiHXSLDto> ziBiHXSLList(String biHuanID, String jiGouID)
    {
        return asQuerydsl()
                .where(n -> n.biHuanID.eq(biHuanID))
                .where(n->n.zuZhiJGID.eq(jiGouID))
                .orderBy(n->n.shunXuHao.asc())
                .select(SC_BH_ZiBiHXSLDto.class).fetch();

    }
    default List<ZiBiHXSLDto>  ziBiHXSLDtoList(String biHuanID,String jieDianID,String jiGouID)
    {
        return asQuerydsl()
                .where(n -> n.biHuanID.eq(biHuanID))
                .where(n->n.zuZhiJGID.eq(jiGouID))
                .where(n -> n.jieDianID.eq(jieDianID)).select(ZiBiHXSLDto.class).fetch();
    }
    default void  deleteByBiHuanID(String biHuanID,String jieDianID,String jiGouID)
    {
        asDeleteDsl().where(n->n.biHuanID.eq(biHuanID))
                .where(n->n.zuZhiJGID.eq(jiGouID))
                .where(n->n.jieDianID.eq(jieDianID)).execute();
    }
}
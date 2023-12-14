package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_ZiBiHXSLDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_ZiBiHXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.ZiBiHXXDto;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_ZiBiHXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ZiBiHXXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
@MsfDataSource("datasourcesjzx")
public interface SC_BH_ZiBiHXXRepository extends MsfJpaRepository<QSC_BH_ZiBiHXXModel, SC_BH_ZiBiHXXModel, String>, JpaSpecificationExecutor<SC_BH_ZiBiHXXModel> {
 default List<SC_BH_ZiBiHXXModel>  getZiBiHXXList(List<String> biHuanID)
 {
     return asQuerydsl().where(n->n.biHuanID.in(biHuanID)).fetch();
 }
 List<SC_BH_ZiBiHXXModel> findByBiHuanIDAndZuZhiJGIDAndZuZhiJGMC(String biHuanID,String zuZhiJGID,String zuZhiJGMC);

 List<SC_BH_ZiBiHXXModel> findByBiHuanIDAndZuZhiJGID(String biHuanID,String zuZhiJGID);

 SC_BH_ZiBiHXXModel findFirstByBiHuanIDAndZuZhiJGID(String biHuanID,String zuZhiJGID);

 List<SC_BH_ZiBiHXXModel> findByBiHuanIDIn(List<String> biHuanID);

 default  List<SC_BH_ZiBiHXXDto> ziBiHXXList(String biHuanID,String jiGouID)
 {
     return asQuerydsl()
         .where(n -> n.biHuanID.eq(biHuanID))
         .where(n->n.zuZhiJGID.eq(jiGouID))
         .select(SC_BH_ZiBiHXXDto.class).fetch();
 }

 default ZiBiHXXDto ziBiHXXDto(String biHuanID,String jieDianID,String jiGouID)
 {
     return asQuerydsl()
             .where(n -> n.biHuanID.eq(biHuanID))
             .where(n->n.zuZhiJGID.eq(jiGouID))
             .where(n -> n.jieDianID.eq(jieDianID)).select(ZiBiHXXDto.class).fetchFirst();
 }
    default void  deleteByBiHuanID(String biHuanID,String jieDianID,String jiGouID)
    {
        asDeleteDsl().where(n->n.biHuanID.eq(biHuanID))
                .where(n->n.zuZhiJGID.eq(jiGouID))
                .where(n->n.jieDianID.eq(jieDianID)).execute();
    }
}
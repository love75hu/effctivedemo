package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_JIBENXXDto;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_JiBenXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_JiBenXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_JieDianXXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@MsfDataSource("datasourcesjzx")
public interface SC_BH_JIBENXXRepository extends MsfJpaRepository<QSC_BH_JiBenXXModel, SC_BH_JiBenXXModel, String>, JpaSpecificationExecutor<SC_BH_JiBenXXModel> {
    default List<SC_BH_JIBENXXDto> getJIBENXXList(String zuZhiJGID, String likeQuery)
    {
        return asQuerydsl().where(n->n.zuZhiJGID.eq(zuZhiJGID).and(n.biHuanMC.like("%"+likeQuery+"%")))
                .select(SC_BH_JIBENXXDto.class).fetch();
    }

    //
    default List<SC_BH_JIBENXXDto> getjiBeNXXBybiHuanID(String zuZhiJGID,String biHuanID)
    {
        return asQuerydsl()
                .where(n->n.zuZhiJGID.eq(zuZhiJGID).and(n.biHuanID.eq(biHuanID)))
                .select(SC_BH_JIBENXXDto.class).fetch();
    }
    default List<SC_BH_JiBenXXModel> jiBenTYX()
    {
        return asQuerydsl().where(n->n.zuZhiJGID.eq("0")).fetch();
    }
    default List<SC_BH_JiBenXXModel> jiBenJGX(List<String> zuZhiJGID)
    {
        return asQuerydsl().where(n->n.zuZhiJGID.in(zuZhiJGID)).fetch();
    }
}
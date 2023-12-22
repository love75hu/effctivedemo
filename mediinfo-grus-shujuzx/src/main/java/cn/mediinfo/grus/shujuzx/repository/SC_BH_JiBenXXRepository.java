package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_JiBenXXDto;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_JiBenXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_JiBenXXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@MsfDataSource("datasourcesjzx")
public interface SC_BH_JiBenXXRepository extends MsfJpaRepository<QSC_BH_JiBenXXModel, SC_BH_JiBenXXModel, String>, JpaSpecificationExecutor<SC_BH_JiBenXXModel> {
    default List<SC_BH_JiBenXXDto> getJIBENXXList(String zuZhiJGID, String likeQuery)
    {
        return asQuerydsl().where(n->n.zuZhiJGID.eq(zuZhiJGID).and(n.biHuanMC.like("%"+likeQuery+"%")))
                .select(SC_BH_JiBenXXDto.class).fetch();
    }

    //
    default List<SC_BH_JiBenXXDto> getjiBeNXXBybiHuanID(String zuZhiJGID, String biHuanID)
    {
        return asQuerydsl()
                .where(n->n.zuZhiJGID.eq(zuZhiJGID).and(n.biHuanID.eq(biHuanID)))
                .select(SC_BH_JiBenXXDto.class).fetch();
    }
    default List<SC_BH_JiBenXXModel> jiBenTYX()
    {
        return asQuerydsl().where(n->n.zuZhiJGID.eq("0")).fetch();
    }
    default List<SC_BH_JiBenXXModel> jiBenJGX(List<String> zuZhiJGID)
    {
        return asQuerydsl().where(n->n.zuZhiJGID.in(zuZhiJGID)).fetch();
    }

    /**
     * 获取闭环基本信息列表
     *
     * @param zuZhiJGID
     * @param likeQuery
     * @return
     */
    default List<SC_BH_JiBenXXDto> getJiBenXXList(String zuZhiJGID, String likeQuery) {
        return asQuerydsl().where(n->n.zuZhiJGID.eq(zuZhiJGID))
                .whereIf(likeQuery != null, n -> n.biHuanMC.contains(likeQuery))
                .select(SC_BH_JiBenXXDto.class).fetch();
    }
    default  void biHuanSZQY(String biHuanID,Integer qiyongBZ)
    {
        asUpdateDsl().where(n->n.biHuanID.eq(biHuanID)).set(n->n.qiYongBZ,qiyongBZ).execute();
    }

    SC_BH_JiBenXXModel  findFirstByBiHuanID(String biHuanID);


    SC_BH_JiBenXXModel findFirstByBiHuanIDAndZuZhiJGID(String biHuanID, String zuZhiJGID);

    List<SC_BH_JiBenXXModel> findByBiHuanIDIn(List<String> biHuanID);
}
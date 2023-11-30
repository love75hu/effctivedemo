package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.BiHuanSTXXTree;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.BiHuanSTZDDto;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuXXDto;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_ShiTuXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ShiTuXXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@MsfDataSource("datasourcesjzx")
public interface SC_BH_ShiTuXXRepository extends MsfJpaRepository<QSC_BH_ShiTuXXModel, SC_BH_ShiTuXXModel, String>, JpaSpecificationExecutor<SC_BH_ShiTuXXModel> {
    default List<SC_BH_ShiTuXXDto> getShiTuXXList(String likeQuery) {
        return asQuerydsl().whereIf(StringUtil.hasText(likeQuery),n->n.shiTuMC.like("%" + likeQuery + "%"))
                .orderBy(s->s.shunXuHao.asc()).select(SC_BH_ShiTuXXDto.class).fetch();
    }
    @Query("select max(s.shunXuHao) from SC_BH_ShiTuXXModel s")
    Integer getMaxShunXuHao();
    default List<SC_BH_ShiTuXXDto> getShiTuXXListByBiHuanLXDM(String biHuanLXDm,String likeQuery) {
        return asQuerydsl()
                .where(x->x.biHuanLXDM.eq(biHuanLXDm))
                .whereIf(StringUtil.hasText(likeQuery),n->n.shiTuMC.like("%" + likeQuery + "%"))
                .orderBy(s->s.shunXuHao.asc()).select(SC_BH_ShiTuXXDto.class).fetch();
    }

    SC_BH_ShiTuXXModel  findFirstByShiTuID(String shiTuID);



}
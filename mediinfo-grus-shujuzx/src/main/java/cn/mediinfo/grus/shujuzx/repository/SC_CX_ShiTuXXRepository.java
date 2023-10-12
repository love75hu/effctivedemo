package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.cyan.msf.orm.util.QueryDSLUtils;
import cn.mediinfo.grus.shujuzx.dto.shitumx.SC_CX_ShiTuXXByShiTuIDDto;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.SC_CX_ShiTuXXDto;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.ShiTuFLDto;
import cn.mediinfo.grus.shujuzx.model.QSC_CX_ShiTuXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ShiTuXXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@MsfDataSource("datasourcesjzx")
public interface SC_CX_ShiTuXXRepository extends MsfJpaRepository<QSC_CX_ShiTuXXModel, SC_CX_ShiTuXXModel, String>, JpaSpecificationExecutor<SC_CX_ShiTuXXModel>
{
    /**
     * 根据视图名称判断是否存在
     * @param fuLeiMC 视图名称
     * @return 是否存在
     */
    Boolean existsByFuLeiMC(String fuLeiMC);

    /**
     * 获取最大顺序号
     * @return 是否存在
     */
    @Query("select max(s.shunXuHao) from SC_CX_ShiTuXXModel s where s.fuLeiID='0'")
    Integer getMaxShunXuHao();

    /**
     * 根据父类id获取最大顺序号
     * @param fuLeiID 父类ID
     * @return 是否存在
     */
    @Query("select max(s.shunXuHao) from SC_CX_ShiTuXXModel s where s.fuLeiID=:fuLeiID")
    Integer getMaxShunXuHaoByFuLeiID(String fuLeiID);

    /**
     * 数据视图信息
     */
    default  List<SC_CX_ShiTuXXModel> shiTuXXList(String likeQuery){
        return this.asQuerydsl().whereIf(StringUtil.hasText(likeQuery), s -> s.fuLeiMC.like("%" + likeQuery + "%").or(s.shiTuMC.like("%" + likeQuery + "%")))
                .orderBy(s -> s.shunXuHao.asc()).fetch();
    };

    default List<ShiTuFLDto> getShiTuFLList(String fuLeiID, String likeQuery)
    {
        return this.asQuerydsl()
                .where(s -> s.fuLeiID.eq(fuLeiID))
                .whereIf(StringUtil.hasText(likeQuery), s -> s.fuLeiMC.like(likeQuery))
                .select(e-> QueryDSLUtils.bean(
                        ShiTuFLDto.class,
                        e.id,
                        e.fuLeiMC.as("shiTuFLMC"))).fetch();
    }
    default SC_CX_ShiTuXXDto getShiTuXXByShiTuID(String shiTuID)
    {
        return this.asQuerydsl()
                .where(s ->s.shiTuID.eq(shiTuID))
                .select(SC_CX_ShiTuXXDto.class)
                .fetchFirst();
    }
    default List<SC_CX_ShiTuXXByShiTuIDDto> findByShiTuIDIn(Set<String> shiTuIDs)
    {
        return this.asQuerydsl().where(s -> s.shiTuID.in(shiTuIDs)).select(SC_CX_ShiTuXXByShiTuIDDto.class).fetch();
    }

}
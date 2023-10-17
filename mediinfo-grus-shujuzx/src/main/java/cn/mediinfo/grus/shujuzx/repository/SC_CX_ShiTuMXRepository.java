package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.dto.shitumx.SC_CX_ShiTuMXByIdDto;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.GuanLianTJZD;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.ShiTuMXListDto;
import cn.mediinfo.grus.shujuzx.model.QSC_CX_ShiTuMXModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ShiTuMXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

@MsfDataSource("datasourcesjzx")
public interface SC_CX_ShiTuMXRepository extends MsfJpaRepository<QSC_CX_ShiTuMXModel, SC_CX_ShiTuMXModel, String>, JpaSpecificationExecutor<SC_CX_ShiTuMXModel> {


    default List<SC_CX_ShiTuMXByIdDto>  findByIdIs(Set<String> ids)
    {
        return this.asQuerydsl().where(e->e.id.in(ids))
                .select(SC_CX_ShiTuMXByIdDto.class)
                .fetch();
    }
    default List<ShiTuMXListDto> getShiTuMXList(String shiTuID, String likeQuery,Integer chaXunLX, Integer pageIndex, Integer pageSize)
    {
        return this.asQuerydsl()
                .where(e->e.shiTuID.eq(shiTuID))
                .whereIf(StringUtil.hasText(likeQuery), e -> e.ziDuanMC.contains(likeQuery))
                .whereIf(chaXunLX.equals(1), e -> e.tiaoJianBZ.eq(1))
                .whereIf(chaXunLX.equals(2),e->e.shuChuBXBZ.eq(1))
                .select(ShiTuMXListDto.class)
                .fetch();
    }

    default Integer getShiTuMXCount(String shiTuID, String likeQuery,Integer chaXunLX)
    {
        return this.asQuerydsl().where(e->e.shiTuID.eq(shiTuID))
                .whereIf(StringUtil.hasText(likeQuery), e -> e.ziDuanMC.contains(likeQuery))
                .whereIf(chaXunLX.equals(1), e -> e.tiaoJianBZ.eq(1))
                .whereIf(chaXunLX.equals(2),e->e.shuChuBXBZ.eq(1))
                .select(ShiTuMXListDto.class).fetch().size();
    }
    default List<GuanLianTJZD> getGuanLianTJZD(String shiTuSTID)
    {
        return this.asQuerydsl().where(e->e.shiTuID.eq(shiTuSTID))
                .where(e->e.tiaoJianBZ.eq(1))
                .select(GuanLianTJZD.class)
                .fetch();
    }
    default List<GuanLianTJZD> getGuanLianTJList(String shiTuSTID)
    {
        return this.asQuerydsl().where(e->e.shiTuID.eq(shiTuSTID))
                .select(GuanLianTJZD.class)
                .fetch();
    }

}
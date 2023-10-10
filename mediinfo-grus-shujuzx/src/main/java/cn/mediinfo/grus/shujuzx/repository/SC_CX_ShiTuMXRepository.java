package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.GuanLianTJZD;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.ShiTuMXListDto;
import cn.mediinfo.grus.shujuzx.model.QSC_CX_ShiTuMXModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ShiTuMXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@MsfDataSource("datasourcesjzx")
public interface SC_CX_ShiTuMXRepository extends MsfJpaRepository<QSC_CX_ShiTuMXModel, SC_CX_ShiTuMXModel, String>, JpaSpecificationExecutor<SC_CX_ShiTuMXModel> {

    default List<ShiTuMXListDto> getShiTuMXList(String shiTuSTID, String likeQuery,Integer chaXunLX, Integer pageIndex, Integer pageSize)
    {
        return this.asQuerydsl().where(e->e.shiTuID.eq(shiTuSTID))
                .whereIf(StringUtil.hasText(likeQuery), e -> e.ziDuanMC.like("%" + likeQuery + "%"))
                .whereIf(chaXunLX.equals(1), e -> e.tiaoJianBZ.eq(1))
                .whereIf(chaXunLX.equals(2),e->e.shuChuBXBZ.eq(1))
                .select(ShiTuMXListDto.class)
                .fetchPage(pageIndex,pageSize);
    }

    default Integer getShiTuMXCount(String shiTuSTID, String likeQuery,Integer chaXunLX)
    {
        return this.asQuerydsl().where(e->e.shiTuID.eq(shiTuSTID))
                .whereIf(StringUtil.hasText(likeQuery), e -> e.ziDuanMC.like("%" + likeQuery + "%"))
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
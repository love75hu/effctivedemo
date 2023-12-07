package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.dto.shitumx.SC_CX_ShiTuXXByShiTuIDDto;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.GuanLianTJZD;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.ShiTuMXListDto;
import cn.mediinfo.grus.shujuzx.model.QSC_CX_ShiTuMXGXModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ShiTuMXGXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

@MsfDataSource("datasourcesjzx")
public interface SC_CX_ShiTuMXGXRepository extends MsfJpaRepository<QSC_CX_ShiTuMXGXModel, SC_CX_ShiTuMXGXModel, String>, JpaSpecificationExecutor<SC_CX_ShiTuMXGXModel> {
    /**
     * 根据视图IDS获取视图明细关联数据
     * @param shiTuIDs
     * @return
     */
    default List<GuanLianTJZD> findByShiTuMXGXIDIn(List<String> shiTuIDs)
    {
        return this.asQuerydsl().where(s -> s.shiTuID.in(shiTuIDs)).select(GuanLianTJZD.class).fetch();
    }
}
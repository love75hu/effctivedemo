package cn.mediinfo.grus.shujuzx.repository;


import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.BiHuanSTZDDto;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.KeXuanZDDto;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_ShiTuMXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ShiTuMXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@MsfDataSource("datasourcesjzx")
public interface SC_BH_ShiTuMXRepository extends MsfJpaRepository<QSC_BH_ShiTuMXModel, SC_BH_ShiTuMXModel, String>, JpaSpecificationExecutor<SC_BH_ShiTuMXModel> {


    default List<BiHuanSTZDDto> getBiHuanSTZD(String shiTuID ,String ziDuanMC, String biHuanLXDM, Integer chaXunLXDM, Integer pageIndex, Integer pageSize)
    {
        return this.asQuerydsl()
                .whereIf(StringUtil.hasText(shiTuID),e->e.shiTuID.eq(shiTuID))
                .whereIf(StringUtil.hasText(biHuanLXDM),e->e.biHuanLXDM.eq(biHuanLXDM))
                .whereIf(StringUtil.hasText(ziDuanMC), e -> e.ziDuanMC.contains(ziDuanMC))
                .whereIf(chaXunLXDM.equals(1), e -> e.tiaoJianBZ.eq(1))
                .whereIf(chaXunLXDM.equals(2),e->e.ruCanBZ.eq(1))
                .select(BiHuanSTZDDto.class)
                .fetchPage(pageIndex,pageSize);
    }

    default Integer getBiHuanSTZDCount(String shiTuID ,String ziDuanMC, String biHuanLXDM, Integer chaXunLXDM)
    {
        return this.asQuerydsl()
                .whereIf(StringUtil.hasText(shiTuID),e->e.shiTuID.eq(shiTuID))
                .whereIf(StringUtil.hasText(biHuanLXDM),e->e.biHuanLXDM.eq(biHuanLXDM))
                .whereIf(StringUtil.hasText(ziDuanMC), e -> e.ziDuanMC.contains(ziDuanMC))
                .whereIf(chaXunLXDM.equals(1), e -> e.tiaoJianBZ.eq(1))
                .whereIf(chaXunLXDM.equals(2),e->e.ruCanBZ.eq(1)).select(BiHuanSTZDDto.class).fetch().size();
    }



    default List<KeXuanZDDto> getShiTUZDXX(String shiTUID)
    {
        return this.asQuerydsl().where(e->e.shiTuID.eq(shiTUID)).select(KeXuanZDDto.class).fetch();
    }

    default List<KeXuanZDDto> getRuChanZDXX(String biHuanLXDM)
    {
        return this.asQuerydsl().where(e->e.biHuanLXDM.eq(biHuanLXDM)).where(e->e.ruCanBZ.eq(1)).select(KeXuanZDDto.class).fetch();
    }


}
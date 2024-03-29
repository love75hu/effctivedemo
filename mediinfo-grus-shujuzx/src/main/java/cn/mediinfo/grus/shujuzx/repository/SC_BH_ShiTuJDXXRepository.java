package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.BiHuanJDXXListDto;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.GuanLianJDDto;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_ShiTuJDXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ShiTuJDXXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@MsfDataSource("datasourcesjzx")
public interface SC_BH_ShiTuJDXXRepository extends MsfJpaRepository<QSC_BH_ShiTuJDXXModel, SC_BH_ShiTuJDXXModel, String>, JpaSpecificationExecutor<SC_BH_ShiTuJDXXModel> {

    SC_BH_ShiTuJDXXModel findFirstByJieDianID(String jieDianID);

    List<SC_BH_ShiTuJDXXModel> findByJieDianIDIn(List<String> jieDianID);
    List<SC_BH_ShiTuJDXXModel> findByShiTuIDIn(List<String> shiTuID);
   default List<BiHuanJDXXListDto> getBiHuanJDXXList(String shiTuID,
                                                     String biHuanLXDM,
                                                     String jieDianMC,
                                                     Integer qiYongBZ,
                                                     Integer pageIndex,
                                                     Integer pageSize)
   {
      return this.asQuerydsl().whereIf(StringUtil.hasText(shiTuID),n->n.shiTuID.eq(shiTuID))
              .whereIf(StringUtil.hasText(biHuanLXDM),n->n.biHuanLXDM.eq(biHuanLXDM))
               .whereIf(StringUtil.hasText(jieDianMC),n->n.jieDianMC.contains(jieDianMC))
               .whereIf(Objects.equals(qiYongBZ,1), n->n.qiYongBZ.eq(qiYongBZ))
               .select(BiHuanJDXXListDto.class).fetchPage(pageIndex,pageSize);
   }
   default List<BiHuanJDXXListDto> getBiHuanJDList(String biHuanLXDM)
   {
        return this.asQuerydsl().where(n->n.biHuanLXDM.eq(biHuanLXDM))
                .select(BiHuanJDXXListDto.class).fetch();
   }
    default Integer getBiHuanJDXXCount(String shiTuID,
                                                      String biHuanLXDM,
                                                      String jieDianMC,
                                                      Integer qiYongBZ)
    {
        return this.asQuerydsl().whereIf(StringUtil.hasText(shiTuID),n->n.shiTuID.eq(shiTuID))
                .whereIf(StringUtil.hasText(biHuanLXDM),n->n.biHuanLXDM.eq(biHuanLXDM))
                .whereIf(StringUtil.hasText(jieDianMC),n->n.jieDianMC.contains(jieDianMC))
                .whereIf(Objects.equals(qiYongBZ,1),n->n.qiYongBZ.eq(qiYongBZ))
                .select(BiHuanJDXXListDto.class).fetch().size();
    }

    default  List<GuanLianJDDto> getGuanLianJDXX(String shiTuID,String jieDianID)
    {
        return this.asQuerydsl()
            .where(n->n.shiTuID.eq(shiTuID))
            .whereIf(StringUtils.hasText(jieDianID), n->n.jieDianID.ne(jieDianID))
            .orderBy(n->n.shunXuHao.asc())
            .select(GuanLianJDDto.class).fetch();
    }
}
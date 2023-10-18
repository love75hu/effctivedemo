package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.BiHuanJDXXListDto;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.GuanLianJDDto;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_ShiTuJDXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ShiTuJDXXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@MsfDataSource("datasourcesjzx")
public interface SC_BH_ShiTuJDXXRepository extends MsfJpaRepository<QSC_BH_ShiTuJDXXModel, SC_BH_ShiTuJDXXModel, String>, JpaSpecificationExecutor<SC_BH_ShiTuJDXXModel> {

   default List<BiHuanJDXXListDto> getBiHuanJDXXList(String shiTuID,
                                                     String jieDianMC,
                                                     Integer qiYongBZ,
                                                     Integer pageIndex,
                                                     Integer pageSize)
   {
      return this.asQuerydsl().where(n->n.shiTuID.eq(shiTuID))
               .whereIf(StringUtil.hasText(jieDianMC),n->n.jieDianMC.contains(jieDianMC))
               .whereIf(qiYongBZ.equals(1),n->n.qiYongBZ.eq(qiYongBZ))
               .select(BiHuanJDXXListDto.class).fetchPage(pageIndex,pageSize);
   }
    default Integer getBiHuanJDXXCount(String shiTuID,
                                                      String jieDianMC,
                                                      Integer qiYongBZ)
    {
        return this.asQuerydsl().where(n->n.shiTuID.eq(shiTuID))
                .whereIf(StringUtil.hasText(jieDianMC),n->n.jieDianMC.contains(jieDianMC))
                .whereIf(qiYongBZ.equals(1),n->n.qiYongBZ.eq(qiYongBZ))
                .select(BiHuanJDXXListDto.class).fetch().size();
    }
}
package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.BiHuanSZXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.JieDianSXXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_JieDianXXDto;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_JieDianXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_JieDianXXModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@MsfDataSource("datasourcesjzx")
public interface SC_BH_JieDianXXRepository extends MsfJpaRepository<QSC_BH_JieDianXXModel, SC_BH_JieDianXXModel, String>, JpaSpecificationExecutor<SC_BH_JieDianXXModel> {
    default List<SC_BH_JieDianXXModel> jieDianXXList(List<String> biHuanID) {
        return this.asQuerydsl().where(n -> n.zuZhiJGID.eq("0").and(n.biHuanID.in(biHuanID))).fetch();
    }

    List<SC_BH_JieDianXXModel> findByBiHuanIDAndZuZhiJGIDAndZuZhiJGMC(String biHuanID, String zuZhiJGID, String zuZhiJGMC);

    List<SC_BH_JieDianXXModel> findByBiHuanIDAndZuZhiJGIDOrderByShunXuHao(String biHuanID, String zuZhiJGID);

    List<SC_BH_JieDianXXModel> findByBiHuanIDIn(List<String> biHuanID);

    default List<BiHuanSZXXDto> jieDianXXList(String biHuanID, String jiGouID) {
        return this.asQuerydsl()
                .where(n -> n.biHuanID.eq(biHuanID))
                .where(n -> n.zuZhiJGID.eq(jiGouID))
                .orderBy(n -> n.shunXuHao.asc())
                .select(BiHuanSZXXDto.class).fetch();
    }

    default List<JieDianSXXXDto> byJieDianIDBiHuanID(String biHuanID, String jieDianID, String jiGouID) {
        return this.asQuerydsl()
                .whereIf(StringUtil.hasText(jieDianID), n -> n.jieDianID.ne(jieDianID))
                .where(n -> n.zuZhiJGID.eq(jiGouID))
                .where(n -> n.biHuanID.eq(biHuanID)).select(JieDianSXXXDto.class).fetch();
    }

    default SC_BH_JieDianXXDto jieDianSXXXDto(String biHuanID, String jieDianID, String jiGouID) {
        return this.asQuerydsl()
                .where(n -> n.biHuanID.eq(biHuanID))
                .where(n -> n.zuZhiJGID.eq(jiGouID))
                .where(n -> n.jieDianID.eq(jieDianID)).select(SC_BH_JieDianXXDto.class).fetchFirst();
    }

    default boolean jieDianYC(String jieDianXXID, String yinCangBZ) {
        this.asUpdateDsl()
                .where(n -> n.id.eq(jieDianXXID))
                .set(n -> n.yinCangBZ, yinCangBZ)
                .execute();
        return true;
    }
}
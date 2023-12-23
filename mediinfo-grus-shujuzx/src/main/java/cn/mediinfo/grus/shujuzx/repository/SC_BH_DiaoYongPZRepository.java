package cn.mediinfo.grus.shujuzx.repository;

import cn.hutool.core.util.ObjectUtil;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.dto.bihuandy.SC_BH_DiaoYongPZDto;
import cn.mediinfo.grus.shujuzx.model.QSC_BH_DiaoYongPZModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_DiaoYongPZModel;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@MsfDataSource("datasourcesjzx")
public interface SC_BH_DiaoYongPZRepository extends MsfJpaRepository<QSC_BH_DiaoYongPZModel, SC_BH_DiaoYongPZModel, String>, JpaSpecificationExecutor<SC_BH_DiaoYongPZModel> {
    default List<SC_BH_DiaoYongPZDto> getBiHuanPZList(String zuZhiJGID, String gongNengDDM, Integer qiYongBZ) {
        return asQuerydsl().where(x -> x.zuZhiJGID.eq(zuZhiJGID)).where(x -> x.gongNengDDM.eq(gongNengDDM))
                .whereIf(qiYongBZ == 1, x -> x.qiYongBZ.eq(1))
                .orderBy(n->n.shunXuHao.asc())
                .select(SC_BH_DiaoYongPZDto.class).fetch();
    }
}

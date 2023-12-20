package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.dto.chaxunfaxx.FangAnXXDto;
import cn.mediinfo.grus.shujuzx.model.QSC_CX_FangAnCXLSModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnCXLSModel;
import com.querydsl.core.types.Projections;

import java.util.List;

@MsfDataSource("datasourcesjzx")
public interface SC_CX_FangAnCXLSRepository extends MsfJpaRepository<QSC_CX_FangAnCXLSModel, SC_CX_FangAnCXLSModel, String> {

    default List<SC_CX_FangAnCXLSModel> getFangAnCXLSList(String zuZhiJGID, int pageIndex, int pageSize) {
        return this.asQuerydsl()
                .where(x->x.zuZhiJGID.eq(zuZhiJGID))
                .orderBy(p -> p.chuangJianSJ.desc())
                .fetchPage(pageIndex, pageSize);
    }
}

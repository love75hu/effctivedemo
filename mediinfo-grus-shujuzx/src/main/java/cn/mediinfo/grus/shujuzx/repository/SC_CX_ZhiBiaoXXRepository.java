package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ZhiBiaoXXModel;
import cn.mediinfo.grus.shujuzx.model.QSC_CX_ZhiBiaoXXModel;

import java.util.List;

@MsfDataSource("datasourcesjzx")
public interface SC_CX_ZhiBiaoXXRepository extends MsfJpaRepository<QSC_CX_ZhiBiaoXXModel, SC_CX_ZhiBiaoXXModel, String> {

    default List<SC_CX_ZhiBiaoXXModel> getZhiBiaoXXByZBLXDM(String zhiBiaoLXDM, String likeQuery){
        return this.asQuerydsl().where(p -> p.zhiBiaoLXDM.eq(zhiBiaoLXDM))
                .whereIf(StringUtil.hasText(likeQuery),p -> p.zhiBiaoMC.contains(likeQuery))
                .fetch();
    }
}

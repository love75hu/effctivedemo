package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ZhiBiaoXXModel;
import cn.mediinfo.grus.shujuzx.model.QSC_CX_ZhiBiaoXXModel;

import java.util.List;

@MsfDataSource("datasourcesjzx")
public interface SC_CX_ZhiBiaoXXRepository extends MsfJpaRepository<QSC_CX_ZhiBiaoXXModel, SC_CX_ZhiBiaoXXModel, String> {

    boolean existsByZhiBiaoLXDMAndZhiBiaoFLMC(String zhiBiaoLXDM,String zhiBiaoFLMC);
    List<SC_CX_ZhiBiaoXXModel> findByZhiBiaoLXDMAndZhiBiaoIDIn(String zhiBiaoLXDM,List<String> zhiBiaoIDs);
    default boolean existsZhiBiaoFL(String zhiBiaoLXDM,String zhiBiaoFLID,String zhiBiaoFLMC){
        return this.asQuerydsl()
                .where(p -> p.zhiBiaoLXDM.eq(zhiBiaoLXDM))
                .where(p -> p.zhiBiaoFLID.eq(zhiBiaoFLID).or(p.zhiBiaoFLMC.eq(zhiBiaoFLMC)))
                .exists();
    }
    default List<SC_CX_ZhiBiaoXXModel> getZhiBiaoXXByZBLXDM(String zhiBiaoLXDM, String likeQuery){
        return this.asQuerydsl().where(p -> p.zhiBiaoLXDM.eq(zhiBiaoLXDM))
                .whereIf(StringUtil.hasText(likeQuery),p -> p.zhiBiaoMC.contains(likeQuery))
                .fetch();
    }
}

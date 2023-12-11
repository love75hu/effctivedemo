package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.enums.ZhiBiaoLXDMEnum;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ZhiBiaoXXModel;
import cn.mediinfo.grus.shujuzx.model.QSC_CX_ZhiBiaoXXModel;

import java.util.List;

@MsfDataSource("datasourcesjzx")
public interface SC_CX_ZhiBiaoXXRepository extends MsfJpaRepository<QSC_CX_ZhiBiaoXXModel, SC_CX_ZhiBiaoXXModel, String> {

    boolean existsByZuZhiJGIDAndZhiBiaoLXDMAndZhiBiaoFLMC(String zuZhiJGID,String zhiBiaoLXDM,String zhiBiaoFLMC);
    List<SC_CX_ZhiBiaoXXModel> findByZuZhiJGIDAndZhiBiaoLXDMAndZhiBiaoIDIn(String zuZhiJGID,String zhiBiaoLXDM,List<String> zhiBiaoIDs);
    List<SC_CX_ZhiBiaoXXModel> findByZuZhiJGIDAndZhiBiaoLXDMAndZhiBiaoFLIDInAndZhiBiaoIDIsNull(String zuZhiJGID,String zhiBiaoLXDM,List<String> zhiBiaoFLID);
    default boolean existsZhiBiaoFL(String zuZhiJGID,String zhiBiaoLXDM,String zhiBiaoFLID,String zhiBiaoFLMC){
        return this.asQuerydsl()
                .where(p->p.zuZhiJGID.eq(zuZhiJGID))
                .where(p -> p.zhiBiaoLXDM.eq(zhiBiaoLXDM))
                .where(p -> p.zhiBiaoFLID.eq(zhiBiaoFLID).or(p.zhiBiaoFLMC.eq(zhiBiaoFLMC)))
                .exists();
    }
    default List<SC_CX_ZhiBiaoXXModel> getZhiBiaoXXByZBLXDM(String zuZhiJGID,String zhiBiaoLXDM, String likeQuery){
        return this.asQuerydsl()
                .where(p->p.zuZhiJGID.eq(zuZhiJGID))
                .where(p -> p.zhiBiaoLXDM.eq(zhiBiaoLXDM))
                .whereIf(StringUtil.hasText(likeQuery) && !ZhiBiaoLXDMEnum.YAO_PIN.getZhiBiaoLXDM().equals(zhiBiaoLXDM),
                        p -> p.zhiBiaoMC.contains(likeQuery))
                .whereIf(StringUtil.hasText(likeQuery) && ZhiBiaoLXDMEnum.YAO_PIN.getZhiBiaoLXDM().equals(zhiBiaoLXDM),
                        p -> p.zhiBiaoMC.contains(likeQuery).or(p.zhiBiaoFLMC.contains(likeQuery)))
                .fetch();
    }
}

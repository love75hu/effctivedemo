package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.grus.shujuzx.dto.chaxunfaxx.FangAnXXDto;
import cn.mediinfo.grus.shujuzx.model.QSC_CX_FangAnXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnXXModel;

import java.util.List;

public interface SC_CX_FangAnXXRepository extends MsfJpaRepository<QSC_CX_FangAnXXModel, SC_CX_FangAnXXModel, String> {

    default List<FangAnXXDto> getFangAnXX(String likeQuery,String fangAnLXDM,int pageIndex, int pageSize){
         return this.asQuerydsl()
                 .whereIf(StringUtil.hasText(likeQuery),p->p.fangAnMC.contains(likeQuery).or(p.guanJianZi.contains(likeQuery)))
                 .whereIf(StringUtil.hasText(fangAnLXDM),p->p.fangAnLXDM.eq(fangAnLXDM))
                 .orderBy(p->p.chuangJianSJ.desc())
                 .select(FangAnXXDto.class)
                 .fetchPage(pageIndex,pageSize);
    }
}

package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.dto.chaxunfaxx.FangAnXXDto;
import cn.mediinfo.grus.shujuzx.model.QSC_CX_FangAnXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnXXModel;
import com.querydsl.core.types.Projections;

import java.util.List;

@MsfDataSource("datasourcesjzx")
public interface SC_CX_FangAnXXRepository extends MsfJpaRepository<QSC_CX_FangAnXXModel, SC_CX_FangAnXXModel, String> {

    default List<FangAnXXDto> getFangAnXX(String zuZhiJGID,String likeQuery, String fangAnLXDM, int pageIndex, int pageSize) {
        return this.asQuerydsl()
                .where(x->x.zuZhiJGID.eq(zuZhiJGID))
                .whereIf(StringUtil.hasText(likeQuery), p -> p.fangAnMC.contains(likeQuery).or(p.guanJianZi.contains(likeQuery)))
                .whereIf(StringUtil.hasText(fangAnLXDM), p -> p.fangAnLXDM.eq(fangAnLXDM))
                .orderBy(p -> p.chuangJianSJ.desc())
                .select(p -> Projections.bean(
                        FangAnXXDto.class,
                        p.id.as("id"),
                        p.fangAnLXDM.as("fangAnLXDM"),
                        p.fangAnLXMC.as("fangAnLXMC"),
                        p.fangAnID.as("fangAnID"),
                        p.fangAnMC.as("fangAnMC"),
                        p.guanJianZi.as("guanJianZi"),
                        p.chaXunTJMS.as("chaXunTJMS"),
                        p.fangAnMC.append("+").append(p.guanJianZi).as("biaoTiMC")
                ))
                .fetchPage(pageIndex, pageSize);
    }

    SC_CX_FangAnXXModel findByFangAnID(String fangAnID);

    default List<SC_CX_FangAnXXModel> getFangAnXXList(String zuZhiJGID,String likeQuery, String fangAnLXDM) {
        return this.asQuerydsl()
                .where(x->x.zuZhiJGID.eq(zuZhiJGID))
                .whereIf(StringUtil.hasText(likeQuery), p -> p.fangAnMC.contains(likeQuery).or(p.guanJianZi.contains(likeQuery)))
                .whereIf(StringUtil.hasText(fangAnLXDM), p -> p.fangAnLXDM.eq(fangAnLXDM))
                .orderBy(p -> p.chuangJianSJ.desc())
                .fetch();
    }
}

package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_ShuJuYZYModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_ShuJuYZYModel;
import org.springframework.util.StringUtils;

import java.util.List;

@MsfDataSource("datasourcesjzx")
public interface SC_ZD_ShuJuYZYRepository extends MsfJpaRepository<QSC_ZD_ShuJuYZYModel, SC_ZD_ShuJuYZYModel, String> {
    Boolean existsByShuJuYLBIDAndZhiYuID(String shuJuYLBID, String zhiYuID);

    Boolean existsByIdIsNotAndShuJuYLBIDAndZhiYuID(String id, String ShuJuYLBID, String zhiYuID);

    List<SC_ZD_ShuJuYZYModel> findByZuZhiJGIDAndShuJuYLBID(String zuZhiJGID, String shuJuYLBID);

    List<SC_ZD_ShuJuYZYModel> findByShuJuYLBIDIn(List<String> shuJuYLBIDs);

    List<SC_ZD_ShuJuYZYModel> findByShuJuYLBID(String shuJuYLBID);

    default List<SC_ZD_ShuJuYZYModel> getShuJuYZYList(String zuZhiJGID, String shuJuYLBID, String likeQuery, String shuRuMLX, Integer pageIndex, Integer pageSize) {
        return this.asQuerydsl()
                .where(x -> x.shuJuYLBID.eq(shuJuYLBID))
                .whereIf(StringUtils.hasText(zuZhiJGID), x -> x.zuZhiJGID.eq(zuZhiJGID))
                .whereIf(StringUtils.hasText(likeQuery), x -> x.biaoZhunMC
                        .like("%" + likeQuery + "%")
                        .or(x.biaoZhunDM.like("%" + likeQuery + "%"))
                        .or("1".equals(shuRuMLX) ? x.shuRuMa1.like("%" + likeQuery.toUpperCase() + "%")
                                : "2".equals(shuRuMLX) ? x.shuRuMa2.like("%" + likeQuery.toUpperCase() + "%")
                                : x.shuRuMa3.like("%" + likeQuery.toUpperCase() + "%")))
                .fetchPage(pageIndex, pageSize);
    }

    default Integer getShuJuYZYListCount(String zuZhiJGID, String shuJuYLBID, String likeQuery, String shuRuMLX) {
        return this.asQuerydsl()
                .where(x -> x.shuJuYLBID.eq(shuJuYLBID))
                .whereIf(StringUtils.hasText(zuZhiJGID), x -> x.zuZhiJGID.eq(zuZhiJGID))
                .whereIf(StringUtils.hasText(likeQuery), x -> x.biaoZhunMC
                        .like("%" + likeQuery + "%")
                        .or(x.biaoZhunDM.like("%" + likeQuery + "%"))
                        .or("1".equals(shuRuMLX) ? x.shuRuMa1.like("%" + likeQuery.toUpperCase() + "%")
                                : "2".equals(shuRuMLX) ? x.shuRuMa2.like("%" + likeQuery.toUpperCase() + "%")
                                : x.shuRuMa3.like("%" + likeQuery.toUpperCase() + "%")))
                .fetch()
                .size();
    }
}

package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.BR_DA_JiaoChaSYModel;
import cn.mediinfo.grus.shujuzx.model.QBR_DA_JiaoChaSYModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BR_DA_JiaoChaSYRepository extends MsfJpaRepository<QBR_DA_JiaoChaSYModel,BR_DA_JiaoChaSYModel, String> {

    List<BR_DA_JiaoChaSYModel> findByZhuBingRID(String zhuSuoYBRID);

    @Query("SELECT s.zhuBingRID from BR_DA_JiaoChaSYModel s where s.zhuBingRID in :xiangSiBRIDs")
    List<String> getJiaoChaZhuBRIDList(List<String> xiangSiBRIDs);
}

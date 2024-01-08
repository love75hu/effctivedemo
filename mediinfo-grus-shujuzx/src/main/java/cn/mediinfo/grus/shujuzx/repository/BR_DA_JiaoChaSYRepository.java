package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.BR_DA_JiaoChaSYModel;
import cn.mediinfo.grus.shujuzx.model.QBR_DA_JiaoChaSYModel;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
@MsfDataSource("datasourcebr")
public interface BR_DA_JiaoChaSYRepository extends MsfJpaRepository<QBR_DA_JiaoChaSYModel,BR_DA_JiaoChaSYModel, String> {

    List<BR_DA_JiaoChaSYModel> findByZhuBingRID(String zhuSuoYBRID);

    @Query("SELECT s.guanLianBRID from BR_DA_JiaoChaSYModel s where s.zhuBingRID in :xiangSiBRIDs")
    List<String> getJiaoChaZhuBRIDList(List<String> xiangSiBRIDs);

    Boolean existsByZhuBingRIDAndGuanLianBRIDNot(String zhuBingRID,String guanLianBRID);

    Boolean existsByZhuBingRID(String zhuBingRID);

    List<BR_DA_JiaoChaSYModel> findByGuanLianBRIDAndZhuBingRIDNot(String guanLianBRID,String zhuBingRID);

    List<BR_DA_JiaoChaSYModel> findByZhuBingRIDIn(List<String> zhuBingRID);

    List<BR_DA_JiaoChaSYModel> findByZhuBingRIDInOrGuanLianBRIDIn(List<String> zhuBingRID,List<String> guanLianBRID);

    BR_DA_JiaoChaSYModel findFirstByZiDongHBBZOrderByChuangJianSJDesc(Integer ziDongHBBZ);




}

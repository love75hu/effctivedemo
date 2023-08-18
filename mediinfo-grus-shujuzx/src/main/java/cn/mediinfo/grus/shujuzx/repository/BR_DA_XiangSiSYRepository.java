package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.BR_DA_XiangSiSYModel;
import cn.mediinfo.grus.shujuzx.model.QBR_DA_XiangSiSYModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
@MsfDataSource("datasourcebr")
public interface BR_DA_XiangSiSYRepository extends MsfJpaRepository<QBR_DA_XiangSiSYModel,BR_DA_XiangSiSYModel, String> {
    @Query("select s from BR_DA_XiangSiSYModel s where (s.bingRenID1 = :bingRenID and s.bingRenID2 in(:xiangSiBRIDList)) or (s.bingRenID2 = :bingRenID and s.bingRenID1 in(:xiangSiBRIDList))")
    List<BR_DA_XiangSiSYModel> getXiangSiSYList(@Param("bingRenID")String bingRenID, @Param("xiangSiBRIDList")List<String> xiangSiBRIDList);
    @Query("select s from BR_DA_XiangSiSYModel s where s.heBingBZ=0 and s.huLueBZ =0 and ((s.bingRenID1 in (:xiangSiBRIDList) and s.bingRenID2 <> :bingRenID) or (s.bingRenID2 in(:xiangSiBRIDList) and s.bingRenID1 <>:bingRenID))")
    List<BR_DA_XiangSiSYModel> getDeleteXiangSiSYList(@Param("bingRenID")String bingRenID, @Param("xiangSiBRIDList")List<String> xiangSiBRIDList);
    BR_DA_XiangSiSYModel findFirstByBingRenID1AndBingRenID2NotAndHuLueBZOrderByXiangSiDuDesc(String bingRenID1,String bingRenID2,Integer huLueBZ);

    List<BR_DA_XiangSiSYModel> findByBingRenID1InOrBingRenID2In(List<String> bingRenID1,List<String> bingRenID2);

    List<BR_DA_XiangSiSYModel> findByBingRenID1InAndHuLueBZAndHeBingBZ(List<String> bingRenID1,Integer huLueBZ,Integer heBingBZ);
}

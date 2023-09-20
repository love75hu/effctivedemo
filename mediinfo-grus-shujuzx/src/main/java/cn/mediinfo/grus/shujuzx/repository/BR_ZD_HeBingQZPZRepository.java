package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.BR_ZD_HeBingQZPZModel;
import cn.mediinfo.grus.shujuzx.model.QBR_ZD_HeBingQZPZModel;

import java.util.List;
@MsfDataSource("datasourcebr")
public interface BR_ZD_HeBingQZPZRepository extends MsfJpaRepository<QBR_ZD_HeBingQZPZModel,BR_ZD_HeBingQZPZModel, String> {
    Boolean existsByMoJiBZAndFuLeiMC(Integer moJiBZ,String fuLeiMC);
    Boolean existsByMoJiBZAndFuLeiMCAndIdNot(Integer moJiBZ,String fuLeiMC,String id);
    List<BR_ZD_HeBingQZPZModel> findByMoJiBZAndZuoFeiBZOrderByShunXuHao(Integer moJiBZ,Integer zuoFeiBZ);
    List<BR_ZD_HeBingQZPZModel> findByZuZhiJGID(String zuZhiJGID);
}

package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.BR_ZD_HeBingGZModel;
import cn.mediinfo.grus.shujuzx.model.QBR_ZD_HeBingGZModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;
import cn.mediinfo.starter.base.orm.jpa.MsfDataSource;

import java.util.List;
@MsfDataSource("datasourcebr")
public interface BR_ZD_HeBingGZRepository extends MsfJpaRepository<QBR_ZD_HeBingGZModel,BR_ZD_HeBingGZModel, String> {

    List<BR_ZD_HeBingGZModel> findByZuZhiJGIDOrderByFaZhiDesc(String zuZhiJGID);
    BR_ZD_HeBingGZModel findFirstByGuiZeID(String guiZheID);
}

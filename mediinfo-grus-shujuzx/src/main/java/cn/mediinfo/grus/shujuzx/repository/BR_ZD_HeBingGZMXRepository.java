package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.BR_ZD_HeBingGZMXModel;
import cn.mediinfo.grus.shujuzx.model.QBR_ZD_HeBingGZMXModel;

import java.util.List;
@MsfDataSource("datasourcebr")
public interface BR_ZD_HeBingGZMXRepository extends MsfJpaRepository<QBR_ZD_HeBingGZMXModel,BR_ZD_HeBingGZMXModel, String> {
    List<BR_ZD_HeBingGZMXModel> findByGuiZeID(String guiZeID);
}

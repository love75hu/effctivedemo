package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.BR_ZD_HeBingGZMXModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;

import java.util.List;

public interface BR_ZD_HeBingGZMXRepository extends MsfJpaRepository<BR_ZD_HeBingGZMXModel, String> {
    List<BR_ZD_HeBingGZMXModel> findByGuiZeID(String guiZeID);
}

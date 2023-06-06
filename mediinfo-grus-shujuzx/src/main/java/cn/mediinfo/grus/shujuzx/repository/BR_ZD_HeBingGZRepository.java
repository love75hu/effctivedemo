package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.BR_ZD_HeBingGZModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;

import java.util.List;

public interface BR_ZD_HeBingGZRepository extends MsfJpaRepository<BR_ZD_HeBingGZModel, String> {

    List<BR_ZD_HeBingGZModel> findByZuZhiJGIDOrderByFaZhiDesc(String zuZhiJGID);
    BR_ZD_HeBingGZModel findFirstByGuiZeID(String guiZheID);
}

package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.SC_LC_BingRenYLSJModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;

public interface SC_LC_BingRenYLSJRepository extends MsfJpaRepository<SC_LC_BingRenYLSJModel, String> {

    /**
     * 根据病人ID查询病人医疗数据
     * @param bingRenID 病人ID
     */
    SC_LC_BingRenYLSJModel findFirstByBingRenID(String bingRenID);
}

package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.QSC_LC_BingRenYLSJModel;
import cn.mediinfo.grus.shujuzx.model.SC_LC_BingRenYLSJModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;

import java.util.List;

public interface SC_LC_BingRenYLSJRepository extends MsfJpaRepository<QSC_LC_BingRenYLSJModel,SC_LC_BingRenYLSJModel, String> {

    /**
     * 根据病人ID查询病人医疗数据
     *
     * @param bingRenID 病人ID
     */
    SC_LC_BingRenYLSJModel findFirstByBingRenID(String bingRenID);

    /**
     * 根据病人ID查询病人医疗数据
     *
     * @param bingRenID
     * @return
     */
    SC_LC_BingRenYLSJModel findByBingRenID(String bingRenID);

    /**
     * 根据病人IDList查询病人医疗数据
     *
     * @param bingRenID
     * @return
     */
    List<SC_LC_BingRenYLSJModel> findByBingRenIDIn(List<String> bingRenID);
}

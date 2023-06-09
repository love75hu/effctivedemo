package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.SC_ZD_YinSiPZModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;

import java.util.List;

public interface SC_ZD_YinSiPZRepository extends MsfJpaRepository<SC_ZD_YinSiPZModel, String> {

    /**
     * 获取查询模式代码和组织架构id下的隐私配置信息集合
     * @return
     */
    List<SC_ZD_YinSiPZModel> findByZuZhiJGIDAndChaXunMSDM(String zuZhiJGID,String chaXunMSDM);

    List<SC_ZD_YinSiPZModel> findByZuZhiJGIDAndChaXunMSDMInAndQiYongBZ(String zuZhiJGID,List<String> chaXunMSDM,Integer qiYongBZ);
}

package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.QSC_ZD_YinSiGZSZModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_YinSiGZSZModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_YinSiPZModel;
import cn.mediinfo.grus.shujuzx.po.yinsigzsz.YinSiGZSZSJYpPO;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;
import cn.mediinfo.starter.base.orm.jpa.MsfDataSource;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
@MsfDataSource("datasourcesjzx")
public interface SC_ZD_YinSiGZSZRepository extends MsfJpaRepository<QSC_ZD_YinSiGZSZModel,SC_ZD_YinSiGZSZModel, String> {
    /**
     *判断数据源名称是否存在
     */
    Boolean existsByShuJuYMC(String shuJuYMC);
    /**
     *获取组织机构下和查询模式代码下的隐私规则配置和隐私配置
     */
    @Query("select new cn.mediinfo.grus.shujuzx.po.yinsigzsz.YinSiGZSZSJYpPO(a,b) from SC_ZD_YinSiGZSZModel as a left join SC_ZD_YinSiPZModel as b on a.shuJuYLM = b.shuJuYLM where b.zuZhiJGID = :zuZhiJGID and b.chaXunMSDM =:chaXunMSDM")
    List<YinSiGZSZSJYpPO> getYinSiGZPZPOList(String zuZhiJGID,String chaXunMSDM);

    List<SC_ZD_YinSiGZSZModel> findByZuZhiJGID(String zuZhiJGID);
}

package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.grus.shujuzx.model.SC_ZD_ZhanShiPZModel;
import cn.mediinfo.starter.base.orm.MsfJpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SC_ZD_ZhanShiPZRepository extends MsfJpaRepository<SC_ZD_ZhanShiPZModel, String> {
    /**
     *获取组织机构下和ids下的展示配置数据
     */
    public List<SC_ZD_ZhanShiPZModel> findByZuZhiJGIDAndIdIn(String zuZhiJGID,List<String> idList);

    /**
     *根据组织机构id和查询模式代码及配置类型ID获取展示配置信息集合
     * @param zuZhiJGID 组织结构id
     * @param chaXunMSDM 查询模式代码
     * @param peiZhiLXDM 配置类型代码
     * @return 展示配置信息集合
     */
    @Query("select a from SC_ZD_ZhanShiPZModel as a where(a.zuZhiJGID = '0' or a.zuZhiJGID = :zuZhiJGID) and a.chaXunMSDM = :chaXunMSDM and a.peiZhiLXDM = :peiZhiLXDM")
    public List<SC_ZD_ZhanShiPZModel> getZhanShiPZList(String zuZhiJGID,String chaXunMSDM,String peiZhiLXDM);
    /**
     *
     * @param zuZhiJGID 组织结构id
     * @param chaXunMSDM 查询模式代码
     * @param peiZhiLXDM 配置类型代码
     * @return 展示配置信息集合
     */
    public List<SC_ZD_ZhanShiPZModel> findByZuZhiJGIDAndChaXunMSDMAndPeiZhiLXDM(String zuZhiJGID,String chaXunMSDM,String peiZhiLXDM);
    /**
     *
     * @param zuZhiJGID 组织结构id
     * @param chaXunMSDM 查询模式代码
     * @return 展示配置信息集合
     */
    public List<SC_ZD_ZhanShiPZModel> findByZuZhiJGIDAndChaXunMSDM(String zuZhiJGID,String chaXunMSDM);
}

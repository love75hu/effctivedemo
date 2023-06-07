package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.YinSiGZSZs.SC_ZD_YinSiGZSZInDto;
import cn.mediinfo.grus.shujuzx.dto.YinSiGZSZs.SC_ZD_YinSiPZCreateDto;
import cn.mediinfo.grus.shujuzx.dto.YinSiGZSZs.SC_ZD_YinSiPZOutDto;
import cn.mediinfo.grus.shujuzx.dto.YinSiGZSZs.SC_ZD_ZhanShiPZCreateDto;
import cn.mediinfo.starter.base.exception.MsfException;
import cn.mediinfo.starter.base.exception.TongYongYWException;

import java.util.List;

public interface YinSiGZSZService {
    /**
     * 新增隐私规则
     */
    public Integer addYinSiGZ(SC_ZD_YinSiGZSZInDto yinSiGZSZInDto) throws MsfException;
    /**
     * 保存隐私设置
     */
    public  Boolean saveYinSiSZList(SC_ZD_YinSiPZCreateDto dto);
    /**
     * 保存展示配置
     */
    public Boolean saveZhanShiPZ(SC_ZD_ZhanShiPZCreateDto dto);
    /**
     * 修改隐私规则
     */
    public Integer updateYinSiGZ(SC_ZD_YinSiGZSZInDto yinSiGZSZInDto) throws MsfException;
    /**
     * 作废隐私规则
     */
    public Integer zuoFeiYinSiGZ(String id) throws MsfException;
    /**
     * 移除隐私配置
     */
    public Boolean zuoFeiYinSiSZ(String id);
    /**
     * 启用隐私设置
     */
    public Boolean qiYongYinSiSZ(String id, Integer qiYongBZ);

    /**
     *更新隐私规则
     * @param zuZhiJGID  组织机构id
     * @param zuZhiJGMC  组织机构名称
     * @param chaXunMSDM 查询模式代码
     * @param peiZhiLXDM 配置类型代码
     * @return true
     */
    public Boolean updateZhanShiPZ(String zuZhiJGID, String zuZhiJGMC, String chaXunMSDM, String peiZhiLXDM);

    /**
     *初始化隐私设置
     * @param zuZhiJGID 组织机构ID
     * @param zuZhiJGMC 组织机构名称
     * @param chaXunMSDM 查询模式代码
     * @return true
     */
    public Boolean chuShiHYinSiZS(String zuZhiJGID, String zuZhiJGMC, String chaXunMSDM);
    /**
     *初始化隐私规则配置
     * @param zuZhiJGID 组织机构ID
     * @param zuZhiJGMC 组织机构名称
     * @param chaXunMSDM 查询模式代码
     * @return true
     */
    public Boolean chuShiHYSGZPZ(String zuZhiJGID, String zuZhiJGMC, String chaXunMSDM);
    /**
     *初始化展示设置
     * @param zuZhiJGID 组织机构ID
     * @param zuZhiJGMC 组织机构名称
     * @param chaXunMSDM 查询模式代码
     * @param peiZhiLXDM 配置类型代码
     * @return true
     */
    public Boolean chuShiHZhanShiPZ(String zuZhiJGID, String zuZhiJGMC, String chaXunMSDM, String peiZhiLXDM);

    List<SC_ZD_YinSiPZOutDto> getYinSiGZPZList(String chaXunMSDM, String zuZhiJGID) throws TongYongYWException;
}

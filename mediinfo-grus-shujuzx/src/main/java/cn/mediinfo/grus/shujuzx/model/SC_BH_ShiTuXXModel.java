package cn.mediinfo.grus.shujuzx.model;

import lombok.Data;
import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

/*** 闭环视图信息*/
@Data
@Entity
@Table(name = "sc_bh_shituxx")
public class SC_BH_ShiTuXXModel extends StringMTEntity {
    /**
     * 组织机构ID
     */
    @Column(columnDefinition = "组织机构ID")
    private String zuZhiJGID;
    /**
     * 组织机构名称
     */
    @Column(columnDefinition = "组织机构名称")
    private String zuZhiJGMC;
    /**
     * 闭环类型代码
     */
    @Column(columnDefinition = "闭环类型代码")
    private String biHuanLXDM;
    /**
     * 闭环类型名称
     */
    @Column(columnDefinition = "闭环类型名称")
    private String biHuanLXMC;
    /**
     * 视图ID
     */
    @Column(columnDefinition = "视图ID")
    private String shiTuID;
    /**
     * 视图名称
     */
    @Column(columnDefinition = "视图名称")
    private String shiTuMC;
    /**
     * 视图类型代码
     */
    @Column(columnDefinition = "视图类型代码")
    private String shiTuLXDM;
    /**
     * 视图类型名称 [1：列存储；2：行存储]
     */
    @Column(columnDefinition = "视图类型名称 [1：列存储；2：行存储]")
    private String shiTuLXMC;
    /**
     * 数据来源类型代码
     */
    @Column(columnDefinition = "数据来源类型代码")
    private String shuJuLYLXDM;
    /**
     * 数据来源类型名称[1.数据集2.数据视图]
     */
    @Column(columnDefinition = "数据来源类型名称[1.数据集2.数据视图]")
    private String shuJuLYLXMC;
    /**
     * 数据来源ID[1.数据集ID2.数据视图ID]
     */
    @Column(columnDefinition = "数据来源ID[1.数据集ID2.数据视图ID]")
    private String shuJuLYID;
    /**
     * 数据来源名称
     */
    @Column(columnDefinition = "数据来源名称")
    private String shuJuLYMC;
    /**
     * 顺序号
     */
    @Column(columnDefinition = "顺序号")
    private Integer shunXuHao;
}
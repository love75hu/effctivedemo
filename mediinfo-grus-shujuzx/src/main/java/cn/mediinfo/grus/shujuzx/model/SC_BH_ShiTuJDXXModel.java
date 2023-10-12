package cn.mediinfo.grus.shujuzx.model;

import lombok.Data;
import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

/*** 闭环视图节点*/
@Data
@Entity
@Table(name = "sc_bh_shitujdxx")
public class SC_BH_ShiTuJDXXModel extends StringMTEntity {
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
     * 节点ID
     */
    @Column(columnDefinition = "节点ID")
    private String jieDianID;
    /**
     * 节点名称
     */
    @Column(columnDefinition = "节点名称")
    private String jieDianMC;
    /**
     * 顺序号
     */
    @Column(columnDefinition = "顺序号")
    private Integer shunXuHao;
    /**
     * 事件字段编码
     */
    @Column(columnDefinition = "事件字段编码")
    private String shiJianZDBM;
    /**
     * 事件字段名称
     */
    @Column(columnDefinition = "事件字段名称")
    private String shiJianZDMC;
    /**
     * 事件代码
     */
    @Column(columnDefinition = "事件代码")
    private String shiJianDM;
    /**
     * 事件名称
     */
    @Column(columnDefinition = "事件名称")
    private String shiJianMC;
    /**
     * 启用标志
     */
    @Column(columnDefinition = "启用标志")
    private Integer qiYongBZ;
}
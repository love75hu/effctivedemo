package cn.mediinfo.grus.shujuzx.model;

import lombok.Data;
import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

/*** 闭环视图节点关系*/
@Data
@Entity
@Table(name = "sc_bh_shitujdgx")
public class SC_BH_ShiTuJDGXModel extends StringMTEntity {
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
     * 关联节点id
     */
    @Column(columnDefinition = "关联节点id")
    private String guanLianJDID;
    /**
     * 关联节点名称
     */
    @Column(columnDefinition = "关联节点名称")
    private String guanLianJDMC;
    /**
     * 顺序号
     */
    @Column(columnDefinition = "顺序号")
    private Integer shunXuHao;
}
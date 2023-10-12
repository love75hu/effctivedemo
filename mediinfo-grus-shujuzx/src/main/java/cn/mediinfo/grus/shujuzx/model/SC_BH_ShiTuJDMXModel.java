package cn.mediinfo.grus.shujuzx.model;

import lombok.Data;
import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

/*** 视图节点明细*/
@Data
@Entity
@Table(name = "sc_bh_shitujdmx")
public class SC_BH_ShiTuJDMXModel extends StringMTEntity {
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
     * 字段编码
     */
    @Column(columnDefinition = "字段编码")
    private String ziDuanBM;
    /**
     * 字段名称
     */
    @Column(columnDefinition = "字段名称")
    private String ziDuanMC;
    /**
     * 控制时间标志
     */
    @Column(columnDefinition = "控制时间标志")
    private Integer kongZhiSJBZ;
    /**
     * 允许为空标志
     */
    @Column(columnDefinition = "允许为空标志")
    private Integer yunXuWKBZ;
    /**
     * 顺序号
     */
    @Column(columnDefinition = "顺序号")
    private Integer shunXuHao;
}
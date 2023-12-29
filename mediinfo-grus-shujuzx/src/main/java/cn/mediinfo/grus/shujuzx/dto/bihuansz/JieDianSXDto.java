package cn.mediinfo.grus.shujuzx.dto.bihuansz;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class JieDianSXDto {
    private String id;
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
     * 闭环ID
     */
    @Column(columnDefinition = "闭环ID")
    private String biHuanID;
    /**
     * 闭环名称
     */
    @Column(columnDefinition = "闭环名称")
    private String biHuanMC;
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
     * 关联节点ID【时效起始节点】
     */
    @Column(columnDefinition = "关联节点ID【时效起始节点】")
    private String guanLianJDID;
    /**
     * 关联节点名称
     */
    @Column(columnDefinition = "关联节点名称")
    private String guanLianJDMC;
    /**
     * 运算符代码
     */
    @Column(columnDefinition = "运算符代码")
    private String yunSuanFDM;
    /**
     * 运算符名称
     */
    @Column(columnDefinition = "运算符名称")
    private String yunSuanFMC;
    /**
     * 时效
     */
    @Column(columnDefinition = "时效")
    private BigDecimal shiXiao;
    /**
     * 单位代码
     */
    @Column(columnDefinition = "单位代码")
    private String danWeiDM;
    /**
     * 单位名称
     */
    @Column(columnDefinition = "单位名称")
    private String danWeiMC;
}

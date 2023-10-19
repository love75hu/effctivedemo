package cn.mediinfo.grus.shujuzx.dto.bihuansz;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 闭环节点时效Dto
 */
@Data
public class SC_BH_JieDianSXDto  {

    private String id;
    /**
     * 组织机构ID
     */
    @Schema(description = "组织机构ID")
    private String zuZhiJGID;
    /**
     * 组织机构名称
     */
    @Schema(description = "组织机构名称")
    private String zuZhiJGMC;
    /**
     * 闭环ID
     */
    @Schema(description = "闭环ID")
    private String biHuanID;
    /**
     * 闭环名称
     */
    @Schema(description = "闭环名称")
    private String biHuanMC;
    /**
     * 节点ID
     */
    @Schema(description = "节点ID")
    private String jieDianID;
    /**
     * 节点名称
     */
    @Schema(description = "节点名称")
    private String jieDianMC;
    /**
     * 关联节点ID【时效起始节点】
     */
    @Schema(description = "关联节点ID【时效起始节点】")
    private String guanLianJDID;
    /**
     * 关联节点名称
     */
    @Schema(description = "关联节点名称")
    private String guanLianJDMC;
    /**
     * 运算符代码
     */
    @Schema(description = "运算符代码")
    private String yunSuanFDM;
    /**
     * 运算符名称
     */
    @Schema(description = "运算符名称")
    private String yunSuanFMC;
    /**
     * 时效
     */
    @Schema(description = "时效")
    private BigDecimal shiXiao;
    /**
     * 单位代码
     */
    @Schema(description = "单位代码")
    private String danWeiDM;
    /**
     * 单位名称
     */
    @Schema(description = "单位名称")
    private String danWeiMC;
}
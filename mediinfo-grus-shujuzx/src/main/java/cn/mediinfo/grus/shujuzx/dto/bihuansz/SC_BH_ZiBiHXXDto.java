package cn.mediinfo.grus.shujuzx.dto.bihuansz;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 子闭环信息Dto
 */
@Data
public class SC_BH_ZiBiHXXDto{
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
     * 视图ID
     */
    @Schema(description = "视图ID")
    private String shiTuID;
    /**
     * 视图名称
     */
    @Schema(description = "视图名称")
    private String shiTuMC;
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
     * 子闭环ID
     */
    @Schema(description = "子闭环ID")
    private String ziBiHID;
    /**
     * 子闭环名称
     */
    @Schema(description = "子闭环名称")
    private String ziBiHMC;
    /**
     * 关联字段编码
     */
    @Schema(description = "关联字段编码")
    private String guanLianZDBM;
    /**
     * 关联字段名称
     */
    @Schema(description = "关联字段名称")
    private String guanLianZDMC;
    /**
     * 闭环子表ID
     */
    @Schema(description = "闭环子表ID")
    private String ziBiHSTID;
    /**
     * 闭环子表名称
     */
    @Schema(description = "子闭环视图名称")
    private String ziBiHSTMC;
    /**
     * 闭环子表代码
     */
    @Schema(description = "闭环子表代码")
    private String ziBiHZDBM;
    /**
     * 闭环子表名称
     */
    @Schema(description = "子闭环字段名称")
    private String ziBiHZDMC;
}
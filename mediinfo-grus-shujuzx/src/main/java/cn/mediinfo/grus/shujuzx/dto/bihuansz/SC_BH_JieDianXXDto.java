package cn.mediinfo.grus.shujuzx.dto.bihuansz;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 闭环节点信息Dto
 */
@Data
public class SC_BH_JieDianXXDto {
    private String  id;
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
     * 显示名称
     */
    @Schema(description = "显示名称")
    private String xianShiMC;
    /**
     * 隐藏标志
     */
    @Schema(description = "隐藏标志")
    private Integer yinCangBZ;
    /**
     * 必须节点标志
     */
    @Schema(description = "必须节点标志")
    private Integer biXuBZ;
    /**
     * 并行节点标志
     */
    @Schema(description = "并行节点标志")
    private Integer bingXingBZ;
    /**
     * 顺序号
     */
    @Schema(description = "顺序号")
    private Integer shunXuHao;
}
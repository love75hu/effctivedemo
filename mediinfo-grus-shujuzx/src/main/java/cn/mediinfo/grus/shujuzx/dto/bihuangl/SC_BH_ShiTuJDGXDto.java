package cn.mediinfo.grus.shujuzx.dto.bihuangl;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 闭环视图节点关系Dto
 */
@Data
public class SC_BH_ShiTuJDGXDto  {
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
     * 关联节点id
     */
    @Schema(description = "关联节点id")
    private String guanLianJDID;
    /**
     * 关联节点名称
     */
    @Schema(description = "关联节点名称")
    private String guanLianJDMC;
    /**
     * 顺序号
     */
    @Schema(description = "顺序号")
    private Integer shunXuHao;
}
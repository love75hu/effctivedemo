package cn.mediinfo.grus.shujuzx.dto.bihuangl;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 闭环视图节点Dto
 */
@Data
public class SC_BH_ShiTuJDXXDto {
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
     * 闭环类型代码
     */
    @Schema(description = "闭环类型代码")
    private String biHuanLXDM;
    /**
     * 闭环类型名称
     */
    @Schema(description = "闭环类型名称")
    private String biHuanLXMC;
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
     * 顺序号
     */
    @Schema(description = "顺序号")
    private Integer shunXuHao;
    /**
     * 事件字段编码
     */
    @Schema(description = "事件字段编码")
    private String shiJianZDBM;
    /**
     * 事件字段名称
     */
    @Schema(description = "事件字段名称")
    private String shiJianZDMC;
    /**
     * 事件代码
     */
    @Schema(description = "事件代码")
    private String shiJianDM;
    /**
     * 事件名称
     */
    @Schema(description = "事件名称")
    private String shiJianMC;
    /**
     * 启用标志
     */
    @Schema(description = "启用标志")
    private Integer qiYongBZ;
}
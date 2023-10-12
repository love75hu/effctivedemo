package cn.mediinfo.grus.shujuzx.dto.bihuangl;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 闭环视图信息Dto
 */
@Data
public class SC_BH_ShiTuXXDto {
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
     * 视图类型代码
     */
    @Schema(description = "视图类型代码")
    private String shiTuLXDM;
    /**
     * 视图类型名称 [1：列存储；2：行存储]
     */
    @Schema(description = "视图类型名称 [1：列存储；2：行存储]")
    private String shiTuLXMC;
    /**
     * 数据来源类型代码
     */
    @Schema(description = "数据来源类型代码")
    private String shuJuLYLXDM;
    /**
     * 数据来源类型名称[1.数据集2.数据视图]
     */
    @Schema(description = "数据来源类型名称[1.数据集2.数据视图]")
    private String shuJuLYLXMC;
    /**
     * 数据来源ID[1.数据集ID2.数据视图ID]
     */
    @Schema(description = "数据来源ID[1.数据集ID2.数据视图ID]")
    private String shuJuLYID;
    /**
     * 数据来源名称
     */
    @Schema(description = "数据来源名称")
    private String shuJuLYMC;
    /**
     * 顺序号
     */
    @Schema(description = "顺序号")
    private Integer shunXuHao;
}
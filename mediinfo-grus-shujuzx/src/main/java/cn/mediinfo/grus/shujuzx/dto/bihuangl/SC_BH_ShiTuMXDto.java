package cn.mediinfo.grus.shujuzx.dto.bihuangl;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 闭环视图明细Dto
 */
@Data
public class SC_BH_ShiTuMXDto  {
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
     * 字段编码
     */
    @Schema(description = "字段编码")
    private String ziDuanBM;
    /**
     * 字段名称
     */
    @Schema(description = "字段名称")
    private String ziDuanMC;
    /**
     * 条件标志
     */
    @Schema(description = "条件标志")
    private Integer tiaoJianBZ;
    /**
     * 入参标志
     */
    @Schema(description = "入参标志")
    private Integer ruCanBZ;
}
package cn.mediinfo.grus.shujuzx.dto.zonghecx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 临床检索视图明细Dto
 */
@Data
public class SC_CX_ShiTuMXDto{
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
     * 输出标志
     */
    @Schema(description = "输出标志")
    private Integer shuChuBZ;
    /**
     * 输出必选标志
     */
    @Schema(description = "输出必选标志")
    private Integer shuChuBXBZ;
}
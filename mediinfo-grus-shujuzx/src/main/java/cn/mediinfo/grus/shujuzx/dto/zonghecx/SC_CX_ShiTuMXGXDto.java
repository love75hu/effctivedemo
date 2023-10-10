package cn.mediinfo.grus.shujuzx.dto.zonghecx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 视图字段关系Dto
 */
@Data
public class SC_CX_ShiTuMXGXDto{
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
     * 顺序号
     */
    @Schema(description = "顺序号")
    private Integer sHUXUHAO;
}
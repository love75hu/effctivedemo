package cn.mediinfo.grus.shujuzx.dto.bihuansz;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RuCanXXDto {


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
}

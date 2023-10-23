package cn.mediinfo.grus.shujuzx.dto.JieDianGL;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BiHuanSTZDDto {
    private String id;
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

    /**
     * 数据来源名称
     */
    @Schema(description = "数据来源名称")
    private String shuJuLYMC;

}

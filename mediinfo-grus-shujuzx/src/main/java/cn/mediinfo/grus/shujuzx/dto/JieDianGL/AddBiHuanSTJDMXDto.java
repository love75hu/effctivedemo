package cn.mediinfo.grus.shujuzx.dto.JieDianGL;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AddBiHuanSTJDMXDto {
    @Schema(description = "视图名称")
    private String shiTuMC;
    @Schema(description = "视图ID")
    private String shiTuID;
    @Schema(description = "字段编码")
    private String ziDuanBM;
    @Schema(description = "字段名称")
    private String ziDuanMC;
    @Schema(description = "闭环类型代码")
    private String biHuanLXDM;
    @Schema(description = "闭环类型名称")
    private String biHuanLXMC;
    @Schema(description = "条件标志")
    private Integer tiaoJianBZ;
    @Schema(description = "入参标志")
    private Integer ruCanBZ;
}

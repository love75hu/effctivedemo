package cn.mediinfo.grus.shujuzx.dto.JieDianGL;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SaveBiHuanSTJDMXDto {
    @Schema(description = "id")
    private String id;
    @Schema(description = "条件标志")
    private Integer tiaoJianBZ;
    @Schema(description = "入参标志")
    private Integer ruCanBZ;
    @Schema(description = "字段名称")
    private String ziDuanMC;
}

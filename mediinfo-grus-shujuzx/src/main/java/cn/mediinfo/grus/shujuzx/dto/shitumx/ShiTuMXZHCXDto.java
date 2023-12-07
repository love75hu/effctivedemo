package cn.mediinfo.grus.shujuzx.dto.shitumx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ShiTuMXZHCXDto {
    @Schema(description = "父类ID")
    private String fuLeiID;
    @Schema(description = "父类名称")
    private String fuLeiMC;
    @Schema(description = "视图字段明细")
    private List<ShuJuJMXZDDto> ziDuanList;
}

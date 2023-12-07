package cn.mediinfo.grus.shujuzx.dto.bihuandy;

import cn.mediinfo.grus.shujuzx.dto.shitumx.ShuJuJMXZDDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ShiTuMXBHPZDto {
    @Schema(description = "视图ID")
    private String shiTuID;
    @Schema(description = "视图名称")
    private String shiTuMC;
    @Schema(description = "视图字段明细")
    private List<ShuJuJMXZDDto> ziDuanList;
}

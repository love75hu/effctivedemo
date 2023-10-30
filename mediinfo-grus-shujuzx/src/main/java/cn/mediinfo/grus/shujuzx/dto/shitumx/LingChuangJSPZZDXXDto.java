package cn.mediinfo.grus.shujuzx.dto.shitumx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class LingChuangJSPZZDXXDto {
    @Schema(description = "数据来源类型代码")
    private String shuJuLYLXDM;
    @Schema(description = "数据来源ID")
    private String shuJuLYID;
    @Schema(description = "视图字段明细")
    private List<ShiTuZDMXDto> shiTuMXZDDtos;
}

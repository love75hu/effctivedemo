package cn.mediinfo.grus.shujuzx.dto.shitumx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
@Data
public class ShiTuCXDTO {
    @Schema(description = "视图明细")
    public List<ShiTuMXZHCXDto> shiTuMXZHCXDtos;
    @Schema(description = "数据明细字段")
    public List<ShuJuJMXZDDto> shuJuJMXZDDtos;

}

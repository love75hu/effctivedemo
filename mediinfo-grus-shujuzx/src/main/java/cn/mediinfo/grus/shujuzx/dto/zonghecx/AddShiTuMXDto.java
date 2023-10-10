package cn.mediinfo.grus.shujuzx.dto.zonghecx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AddShiTuMXDto {

    @Schema(description = "视图ID")
    private String shiTuID;
    @Schema(description = "视图名称")
    private String shiTuMC;
    private List<ZiDuanXXListDto> ziDuanXXList;

}

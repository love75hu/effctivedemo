package cn.mediinfo.grus.shujuzx.dto.zonghecx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class GuanLianTJZD {
    @Schema(description = "视图ID")
    private String shiTuID;
    @Schema(description = "字段编码")
    private String ziDuanBM;
    @Schema(description = "字段名称")
    private String ziDuanMC;
}

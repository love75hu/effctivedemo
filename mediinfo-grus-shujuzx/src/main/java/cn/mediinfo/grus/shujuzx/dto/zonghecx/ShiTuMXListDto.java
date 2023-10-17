package cn.mediinfo.grus.shujuzx.dto.zonghecx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ShiTuMXListDto {

    private String id;
    @Schema(description = "视图名称")
    private String shiTuMC;
    @Schema(description = "字段编码")
    private String ziDuanBM;
    @Schema(description = "字段名称")
    private String ziDuanMC;
    @Schema(description = "条件标志")
    private Integer tiaoJianBZ;
    @Schema(description = "输出标志")
    private Integer shuChuBZ;
    @Schema(description = "顺序号")
    private Integer shuXuHao;

}

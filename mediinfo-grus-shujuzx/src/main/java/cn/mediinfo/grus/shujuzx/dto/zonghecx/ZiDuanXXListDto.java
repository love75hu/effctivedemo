package cn.mediinfo.grus.shujuzx.dto.zonghecx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ZiDuanXXListDto {
    @Schema(description = "字段编码")
    private String ziDuanBM;
    @Schema(description = "字段名称")
    private String ziDuanMC;
    @Schema(description = "条件标志")
    private Integer tiaoJianBZ;
    @Schema(description = "输出标志")
    private Integer shuChuBZ;
    @Schema(description = "顺序号")
    private String shunXuHao;
}

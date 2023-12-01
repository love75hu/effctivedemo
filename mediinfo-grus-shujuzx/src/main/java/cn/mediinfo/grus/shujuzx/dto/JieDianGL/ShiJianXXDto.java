package cn.mediinfo.grus.shujuzx.dto.JieDianGL;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ShiJianXXDto {
    @Schema(description = "字段编码")
    private String ziDuanBM;
    @Schema(description = "字段名称")
    private String ziDuanMC;
    @Schema(description = "领域代码")
    private String lingYuDM;
    @Schema(description = "领域名称")
    private String lingYuMC;
    @Schema(description = "数据源类别ID")
    private String shuJuYLBID;
    @Schema(description = "数据源类别名称")
    private String shuJuYLBMC;
}

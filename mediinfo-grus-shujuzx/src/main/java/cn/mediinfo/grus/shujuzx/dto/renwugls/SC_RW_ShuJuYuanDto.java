package cn.mediinfo.grus.shujuzx.dto.renwugls;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SC_RW_ShuJuYuanDto {
    @Schema(description = "id")
    private String id;
    @Schema(description = "系统名称")
    private String xiTongMC;
    @Schema(description = "厂商名称")
    private String changShangMC;

    @Schema(description = "数据源ID,对应GY_ZD_SHUJUYUAN.ID")
    private String shuJuYID;
    @Schema(description = "数据源名称")
    private String shuJuYMC;
    @Schema(description = "启用标志")
    private Integer qiYongBZ;
}

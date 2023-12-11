package cn.mediinfo.grus.shujuzx.dto.renwugls;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SC_RW_ShuJuYuanDto {
    @Schema(description = "id")
    private String id;
    @Schema(description = "组织机构ID")
    private String zuZhiJGID;
    @Schema(description = "组织机构名称")
    private String zuZhiJGMC;
    @Schema(description = "任务id")
    private String renWuID;
    @Schema(description = "任务名称")
    private String renWuMC;
    @Schema(description = "数据源ID,对应GY_ZD_SHUJUYUAN.ID")
    private String shuJuYID;
    @Schema(description = "数据源名称")
    private String shuJuYMC;
    @Schema(description = "启用标志")
    private Integer qiYongBZ;
}

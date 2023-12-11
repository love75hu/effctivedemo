package cn.mediinfo.grus.shujuzx.dto.renwugls;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SC_RW_JiBenXXCreateDto {
    @Schema(description = "任务名称")
    private String renWuMC;
    @Schema(description = "任务说明")
    private String renWuSM;
    @Schema(description = "任务参数")
    private String renWuCS;
    @Schema(description = "任务地址")
    private String renWuDZ;
    @Schema(description = "分类代码[SC0017]")
    private String fenLeiDM;
    @Schema(description = "分类名称")
    private String fenLeiMC;
    @Schema(description = "执行频率代码[SC0018]")
    private String zhiXingPLDM;
    @Schema(description = "执行频率名称")
    private String zhiXingPLMC;

}

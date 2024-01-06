package cn.mediinfo.grus.shujuzx.dto.renwugls;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class RenWuXQDto {
    @Schema(description = "任务名称")
    private String renWuMC;
    @Schema(description = "任务说明")
    private String renWuSM;
    @Schema(description = "任务参数")
    private String renWuCS;
    @Schema(description = "分类名称")
    private String fenLeiMC;
    @Schema(description = "执行频率名称")
    private String zhiXingPLMC;
    @Schema(description = "任务地址")
    private String renWuDZ;
    public List<SC_RW_ShuJuYuanDto> shuJuYuanDtoList;
}

package cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ZhiBiaoMXListDto {
    @Schema(description = "主键")
    private String id;
    @Schema(description = "指标ID")
    private String zhiBiaoID;
    @Schema(description = "指标名称")
    private String zhiBiaoMC;
    @Schema(description = "顺序号")
    private Integer shunXuHao;

}

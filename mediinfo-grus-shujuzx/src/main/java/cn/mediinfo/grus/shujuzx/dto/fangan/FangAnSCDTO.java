package cn.mediinfo.grus.shujuzx.dto.fangan;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "方案输出字段信息")
public class FangAnSCDTO {

    @Schema(description = "id")
    private String id;

    @Schema(description = "指标id")
    private String zhiBiaoID;

    @Schema(description = "指标名称")
    private String zhiBiaoMC;
}

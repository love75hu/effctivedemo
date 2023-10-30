package cn.mediinfo.grus.shujuzx.dto.shitumx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ZhiYuDto {
    @Schema(description = "值域代码")
    private String zhiYuDM;
    @Schema(description = "值域名称")
    private String zhiYuMC;
}

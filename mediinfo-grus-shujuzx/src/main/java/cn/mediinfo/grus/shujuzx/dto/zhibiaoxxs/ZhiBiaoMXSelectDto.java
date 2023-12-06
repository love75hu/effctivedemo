package cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ZhiBiaoMXSelectDto {
    @Schema(description = "值域id")
    private String zhiYuID;
    @Schema(description = "值域名称")
    private String zhiYuMC;
}

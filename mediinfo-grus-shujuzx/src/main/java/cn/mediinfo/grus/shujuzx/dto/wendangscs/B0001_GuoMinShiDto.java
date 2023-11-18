package cn.mediinfo.grus.shujuzx.dto.wendangscs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class B0001_GuoMinShiDto {
    /**
     * 药物过敏源
     */
    @Schema(description = "药物过敏源")
    private String guoMinYDM;
    /**
     * 药物过敏源
     */
    @Schema(description = "药物过敏源")
    private String guoMinYMC;
}

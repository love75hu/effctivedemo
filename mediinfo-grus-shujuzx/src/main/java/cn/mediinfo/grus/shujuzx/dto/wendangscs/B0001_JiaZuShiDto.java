package cn.mediinfo.grus.shujuzx.dto.wendangscs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class B0001_JiaZuShiDto {
    /**
     * 家族性疾病名称代码
     */
    @Schema(description = "家族性疾病名称代码")
    private String jiaZuJBDM;
    /**
     * 家族性疾病名称
     */
    @Schema(description = "家族性疾病名称")
    private String jiaZuJBMC;
}

package cn.mediinfo.grus.shujuzx.dto.wendangscs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class B0001_BaoLuShiDto {
    /**
     * 环境危险因素暴露类别代码
     */
    @Schema(description = "环境危险因素暴露类别代码")
    private String baoLuSLBDM;
    /**
     * 环境危险因素暴露类别名称
     */
    @Schema(description = "环境危险因素暴露类别名称")
    private String baoLuSLBMC;
}

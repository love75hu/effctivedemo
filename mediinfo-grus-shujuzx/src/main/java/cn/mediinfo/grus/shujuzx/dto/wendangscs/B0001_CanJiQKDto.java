package cn.mediinfo.grus.shujuzx.dto.wendangscs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class B0001_CanJiQKDto {
    /**
     * 残疾情况代码
     */
    @Schema(description = "残疾情况代码")
    private String canJiQKDM;
    /**
     * 残疾情况名称
     */
    @Schema(description = "残疾情况名称")
    private String canJiQKMC;
}

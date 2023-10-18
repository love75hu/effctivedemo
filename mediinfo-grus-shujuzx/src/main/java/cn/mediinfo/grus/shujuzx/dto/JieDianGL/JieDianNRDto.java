package cn.mediinfo.grus.shujuzx.dto.JieDianGL;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class JieDianNRDto {
    /**
     * 节点ID
     */
    @Schema(description = "节点ID")
    private String jieDianID;
    /**
     * 节点名称
     */
    @Schema(description = "节点名称")
    private String jieDianMC;

    /**
     * 控制时间标志
     */
    @Schema(description = "控制时间标志")
    private Integer kongZhiSJBZ;
    /**
     * 允许为空标志
     */
    @Schema(description = "允许为空标志")
    private Integer yunXuWKBZ;
}

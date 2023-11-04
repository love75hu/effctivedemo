package cn.mediinfo.grus.shujuzx.remotedto.GongYong;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ShuJuXXMSRso {
    /**
     * 数据源名称（模式）
     */
    @Schema(description = "数据源名称")
    private String shuJuYMC;
    /**
     * 表名
     */
    @Schema(description = "表名")
    private String biaoMing;
}

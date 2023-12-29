package cn.mediinfo.grus.shujuzx.remotedto.GongYong;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class GY_YW_YinSiGZXXRso {
    public String Id;
    /**
     * 数据元名称
     */
    @Schema(description = "数据元名称")
    private String shuJuYMC;
    /**
     * 数据元列名
     */
    @Schema(description = "数据元列名")
    private String shuJuYLM;
    /**
     * 脱敏规则
     */
    @Schema(description = "脱敏规则")
    private String tuoMinGZ;
    /**
     * 规则说明
     */
    @Schema(description = "规则说明")
    private String guiZeSM;
    /**
     * 脱敏方式代码
     */
    @Schema(description = "脱敏方式代码")
    private String tuoMinFSDM;
    /**
     * 脱敏方式代码
     */
    @Schema(description = "脱敏方式代码")
    private String tuoMinFSMC;
}

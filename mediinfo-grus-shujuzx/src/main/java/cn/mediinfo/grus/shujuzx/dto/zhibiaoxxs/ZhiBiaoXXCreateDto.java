package cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs;

import io.swagger.v3.oas.annotations.media.Schema;

public class ZhiBiaoXXCreateDto {
    @Schema(description = "指标类型代码【SC0009】")
    private String zhiBiaoLXDM;
    @Schema(description = "指标类型名称")
    private String zhiBiaoLXMC;
    @Schema(description = "指标分类ID")
    private String zhiBiaoFLID;
    @Schema(description = "指标分类名称")
    private String zhiBiaoFLMC;
    @Schema(description = "指标ID")
    private String zhiBiaoID;
    @Schema(description = "指标名称")
    private String zhiBiaoMC;
    @Schema(description = "顺序号")
    private Integer shunXuHao;

}

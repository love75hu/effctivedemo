package cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ZhiBiaoXXUpdateDto {
    @Schema(description = "主键")
    private String id;
    @Schema(description = "指标分类名称")
    private String zhiBiaoFLMC;
    @Schema(description = "顺序号")
    private Integer shunXuHao;
}

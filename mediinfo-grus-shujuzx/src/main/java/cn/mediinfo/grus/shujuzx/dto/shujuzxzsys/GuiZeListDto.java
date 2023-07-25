package cn.mediinfo.grus.shujuzx.dto.shujuzxzsys;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GuiZeListDto {
    @Schema(description = "规则ID")
    private String guiZeID;
    @Schema(description = "规则名称")
    private String guiZeMC;
    @Schema(description = "规则明细配置列表")
    private List<GuiZePZDto> guiZePZList;
    @Schema(description = "阀值")
    private BigDecimal faZhi;
    @Schema(description = "相似度（阀值*规则明细总权重）")
    private BigDecimal xiangSiDu;


}

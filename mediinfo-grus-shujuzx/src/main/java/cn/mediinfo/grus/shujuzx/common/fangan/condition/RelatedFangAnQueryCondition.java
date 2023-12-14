package cn.mediinfo.grus.shujuzx.common.fangan.condition;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "关联方案查询条件")
public class RelatedFangAnQueryCondition {

    @Schema(description = "方案id")
    private String fangAnId;

    @Schema(description = "方案名称")
    private String fangAnMC;

    @Schema(description = "方案输出id")
    private String fangAnSCId;

    @Schema(description = "方案输出名称")
    private String fangAnSCMC;

    @Schema(description = "方案sql", hidden = true)
    private String sql;

    @Schema(description = "输出字段", hidden = true)
    private String ziDuanBM;
}

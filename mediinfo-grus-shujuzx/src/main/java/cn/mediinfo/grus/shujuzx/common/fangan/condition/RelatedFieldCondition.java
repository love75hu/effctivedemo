package cn.mediinfo.grus.shujuzx.common.fangan.condition;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Schema(description = "方案关联子条件")
public class RelatedFieldCondition {
    @Schema(description = "视图id")
    private String shiTuID;

    @Schema(description = "字段编码")
    private String ziDuanBM;

    @Schema(description = "字段名称")
    private String ziDuanMC;

    @Schema(description = "数据值类型代码")
    private String shuJuZLXDM;

    @Schema(description = "领域代码")
    private String lingYuDM;

    @Schema(description = "数据源类别ID")
    private String shuJuYLBID;

    @Schema(description = "操作符")
    private String operator;

    @Schema(description = "查询值")
    private String values;

    @Schema(description = "显示值")
    private String names;

    @Schema(description = "关联方案查询条件")
    private RelatedFangAnQueryCondition relatedFangAnQueryCondition;
}

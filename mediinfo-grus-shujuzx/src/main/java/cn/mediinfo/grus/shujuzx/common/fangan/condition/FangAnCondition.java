package cn.mediinfo.grus.shujuzx.common.fangan.condition;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Schema(description = "方案条件")
public class FangAnCondition {

    @Schema(description = "视图明细id")
    private String shiTuMXID;

    @Schema(description = "视图id")
    private String shiTuID;

    @Schema(description = "字段名称")
    private String ziDuanMC;

    @Schema(description = "操作符")
    private String operator;

    @Schema(description = "查询值")
    private String values;

    @Schema(description = "关联方案查询条件")
    private RelatedFangAnQueryCondition relatedFangAnQueryCondition;


    @Schema(description = "关联子字段条件")
    private List<RelatedFieldCondition> relatedFieldConditions;
}

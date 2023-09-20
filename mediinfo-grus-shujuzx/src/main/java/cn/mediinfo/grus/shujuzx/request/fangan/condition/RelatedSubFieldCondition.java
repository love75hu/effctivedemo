package cn.mediinfo.grus.shujuzx.request.fangan.condition;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Schema(description = "方案关联条件")
public class RelatedSubFieldCondition {

    @Schema(description = "字段id")
    private String ziDuanId;

    @Schema(description = "字段名称")
    private String ziDuanMc;

    @Schema(description = "数据集id")
    private String shuJuJiId;

    @Schema(description = "操作符")
    private String operator;

    @Schema(description = "查询值")
    private List<FanganConditionValue> values;
}

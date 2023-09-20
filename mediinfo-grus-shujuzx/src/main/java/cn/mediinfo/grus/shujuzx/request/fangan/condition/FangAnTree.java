package cn.mediinfo.grus.shujuzx.request.fangan.condition;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "方案树")
public class FangAnTree {

    @Schema(description = "查询节点")
    private FangAnCondition condition;

    @Schema(description = "1-关系节点，2-条件节点")
    private String nodeType;

    @Schema(description = "关系，and-并且，or-或者，()-括号")
    private String relation;

    @Schema(description = "左节点")
    private FangAnTree left;

    @Schema(description = "右节点")
    private FangAnTree right;
}

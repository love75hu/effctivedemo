package cn.mediinfo.grus.shujuzx.common.fangan.condition;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *  方案树
 */
@Getter
@Setter
@ToString
@Schema(description = "方案树")
public class FangAnTreeNode {

    @Schema(description = "查询节点")
    private FangAnCondition condition;

    @Schema(description = "节点类型，1-关系节点，2-条件节点")
    private Integer nodeType;

    @Schema(description = "关系，and-并且，or-或者，()-括号")
    private String relation;

    @Schema(description = "左节点")
    private FangAnTreeNode left;

    @Schema(description = "右节点")
    private FangAnTreeNode right;
}

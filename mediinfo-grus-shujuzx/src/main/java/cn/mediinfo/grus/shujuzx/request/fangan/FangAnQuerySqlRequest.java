package cn.mediinfo.grus.shujuzx.request.fangan;

import cn.mediinfo.grus.shujuzx.common.fangan.condition.FangAnTreeNode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 方案保存
 */
@Getter
@Setter
@ToString
@Schema(description = "方案sql查询")
public class FangAnQuerySqlRequest {

    @Schema(description = "方案条件树根节点")
    @NotBlank(message = "方案条件树根节点不能为空")
    private FangAnTreeNode root;

    @Schema(description = "方案输出项")
    @NotEmpty(message = "方案输出项不能为空")
    private List<FangAnSC> fangAnSCList;
}

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
@Schema(description = "方案更新")
public class FangAnXXUpdateRequest {

    @Schema(description = "方案id")
    @NotBlank(message = "方案ID不能为空")
    private String id;

    @Schema(description = "方案条件树根节点")
    private FangAnTreeNode root;

    @Schema(description = "方案输出项")
    @NotEmpty(message = "方案输出项不能为空")
    private List<FangAnSC> fangAnSCList;

    @Schema(description = "关键字")
    private String guanJianZi;
}

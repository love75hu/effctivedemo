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
@Schema(description = "方案保存")
public class FangAnXXSaveRequest {

    @Schema(description = "方案类型代码;0-全部1-门诊2-急诊3-住院4-公卫")
    @NotBlank(message = "方案类型代码不能为空")
    private String fangAnLXDM;

    @Schema(description = "方案类型名称;0-全部1-门诊2-急诊3-住院4-公卫")
    @NotBlank(message = "方案类型名称不能为空")
    private String fangAnLXMC;

    @Schema(description = "方案名称")
    @NotBlank(message = "方案名称不能为空")
    private String fangAnMC;

    @Schema(description = "方案条件树根节点")
    private FangAnTreeNode root;

    @Schema(description = "方案输出项")
    @NotEmpty(message = "方案输出项不能为空")
    private List<FangAnSC> fangAnSCList;

    @Schema(description = "关键字")
    private String guanJianZi;

    @Schema(description = "查询条件描述")
    private String chaXunTJMS;
}

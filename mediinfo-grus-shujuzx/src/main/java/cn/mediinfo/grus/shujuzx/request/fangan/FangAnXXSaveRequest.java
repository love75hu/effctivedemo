package cn.mediinfo.grus.shujuzx.request.fangan;

import cn.mediinfo.grus.shujuzx.request.fangan.condition.FangAnTree;
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
    private String fangAnLxdm;

    @Schema(description = "方案类型名称;0-全部1-门诊2-急诊3-住院4-公卫")
    @NotBlank(message = "方案类型名称不能为空")
    private String fangAnLxmc;

    @Schema(description = "方案条件树根节点")
    private FangAnTree root;

    @Schema(description = "指标id")
    @NotEmpty(message = "方案输出项不能为空")
    private List<String> zhiBiaoIds;

    @Schema(description = "关键字")
    private String guanJianZi;
}

package cn.mediinfo.grus.shujuzx.request;

import cn.mediinfo.grus.shujuzx.common.fangan.condition.FangAnTreeNode;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnSC;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Schema(description = "查询记录保存")
public class FangAnCXLSSaveRequest {


    @Schema(description = "方案类型代码;0-全部1-门诊2-急诊3-住院4-公卫")
    @NotBlank(message = "方案类型代码不能为空")
    private String fangAnLXDM;

    @Schema(description = "id")
    private String fangAnId;

    @Schema(description = "方案名称")
    private String fangAnMC;

    @Schema(description = "关键字")
    private String guanJianZi;

    @Schema(description = "方案条件树根节点")
    private FangAnTreeNode root;

    @Schema(description = "方案输出项")
    @NotEmpty(message = "方案输出项不能为空")
    private List<FangAnSC> fangAnSCList;

}

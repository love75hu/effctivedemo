package cn.mediinfo.grus.shujuzx.dto.fangan;

import cn.mediinfo.grus.shujuzx.common.fangan.condition.FangAnTreeNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Schema(description = "方案输出字段信息")
public class FangAnQueryDTO {

    @Schema(description = "id")
    private String id;

    @Schema(description = "方案id")
    private String fangAnID;

    @Schema(description = "方案类型代码;0-全部1-门诊2-急诊3-住院4-公卫")
    private String fangAnLXDM;

    @Schema(description = "方案类型名称;0-全部1-门诊2-急诊3-住院4-公卫")
    private String fangAnLXMC;

    @Schema(description = "方案名称")
    private String fangAnMC;

    @Schema(description = "方案条件树根节点")
    private FangAnTreeNode root;

    @Schema(description = "方案输出项")
    private List<FangAnSCDTO> fangAnSCList;

    @Schema(description = "关键字")
    private String guanJianZi;

    @Schema(description = "查询条件描述")
    private String chaXunTJMS;
}

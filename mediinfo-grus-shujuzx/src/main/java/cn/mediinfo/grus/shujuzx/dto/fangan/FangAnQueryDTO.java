package cn.mediinfo.grus.shujuzx.dto.fangan;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import cn.mediinfo.grus.shujuzx.request.fangan.condition.FangAnTree;

import java.util.List;

@Getter
@Setter
@ToString
@Schema(description = "方案输出字段信息")
public class FangAnQueryDTO {

    @Schema(description = "id")
    private String id;

    @Schema(description = "方案类型代码;0-全部1-门诊2-急诊3-住院4-公卫")
    private String fangAnLxdm;

    @Schema(description = "方案类型名称;0-全部1-门诊2-急诊3-住院4-公卫")
    private String fangAnLxmc;

    @Schema(description = "方案条件树根节点")
    private FangAnTree root;

    @Schema(description = "方案输出项")
    private List<FangAnSCDTO> fangAnSCList;

    @Schema(description = "关键字")
    private String guanJianZi;
}

package cn.mediinfo.grus.shujuzx.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Schema(description = "就诊基本信息")
public class JiuZhenXXDTO {

    @Schema(description = "就诊id")
    private String jiuZhenYWID;

    @Schema(description = "就诊类型")
    private String jiuZhenYWLXDM;

    @Schema(description = "就诊日期")
    private Date jiuZhenRQ;

    @Schema(description = "组织机构id")
    private String zuZhiJGID;

    @Schema(description = "组织机构名称")
    private String zuZhiJGMC;

    @Schema(description = "就诊科室ID")
    private String jiuZhenKSID;

    @Schema(description = "就诊科室名称")
    private String jiuZhenKSMC;

    @Schema(description = "病历ID列表")
    private List<String> bingLiList;
}

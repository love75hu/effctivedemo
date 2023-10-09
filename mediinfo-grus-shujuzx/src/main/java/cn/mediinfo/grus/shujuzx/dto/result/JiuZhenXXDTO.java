package cn.mediinfo.grus.shujuzx.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Schema(description = "就诊基本信息")
public class JiuZhenXXDTO {

    @Schema(description = "就诊id")
    private String jiuzhenYWId;

    @Schema(description = "就诊类型")
    private String jiuzhenYWLXDM;

    @Schema(description = "就诊日期")
    private Date jiuzhenRQ;

    @Schema(description = "病人id")
    private String bingRenId;

    @Schema(description = "组织机构id")
    private String zuZhiJGID;

    @Schema(description = "组织机构名称")
    private String zuZhiJGMC;

    @Schema(description = "就诊科室ID")
    private String jiuZhenKsId;

    @Schema(description = "就诊科室名称")
    private String jiuZhenKsMC;

}

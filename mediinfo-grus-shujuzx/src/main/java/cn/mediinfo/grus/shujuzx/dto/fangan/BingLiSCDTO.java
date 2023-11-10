package cn.mediinfo.grus.shujuzx.dto.fangan;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Schema(description = "病历输出")
public class BingLiSCDTO {
    @Schema(description = "病人ID")
    private String bingRenID;
    @Schema(description = "姓名")
    private String xingMing;
    @Schema(description = "性别代码")
    private String xingBieDM;
    @Schema(description = "性别名称")
    private String xingBieMC;
    @Schema(description = "出生日期")
    private Date chuShengRQ;
    @Schema(description = "门诊次数")
    private Integer menZhenCS;
    @Schema(description = "住院次数")
    private Integer zhuYuanCS;
    @Schema(description = "就诊ID")
    private String jiuZhenYWID;
    @Schema(description = "就诊业务类型")
    private String jiuZhenYWLXDM;
    @Schema(description = "组织机构ID")
    private String zuZhiJGID;
    @Schema(description = "组织机构名称")
    private String zuZhiJGMC;
    @Schema(description = "就诊日期")
    private Date jiuZhenRQ;
    @Schema(description = "就诊科室ID")
    private String jiuZhenKSID;
    @Schema(description = "就诊科室名称")
    private String jiuZhenKSMC;
    @Schema(description = "病历记录ID")
    private String bingLiJLID;
}

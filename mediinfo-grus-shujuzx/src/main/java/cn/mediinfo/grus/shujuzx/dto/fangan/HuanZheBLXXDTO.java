package cn.mediinfo.grus.shujuzx.dto.fangan;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Schema(description = "患者病历信息")
public class HuanZheBLXXDTO {
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
    private List<JiuZhenBLXXDTO> jiuZhenList;
}

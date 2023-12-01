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
@Schema(description = "病人基本信息")
public class BingRenJBXXDTO {

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

    @Schema(description = "年龄")
    private Integer nianLing;

    @Schema(description = "门诊就诊次数")
    private Integer mzJiuZhenNum;

    @Schema(description = "住院次数")
    private Integer zhuYuanNum;

    @Schema(description = "就诊信息")
    private List<JiuZhenXXDTO> jiuZhenXXList;

    @Schema(description = "收藏夹信息")
    private List<ShouCangJiaDto> shouCangJiaList;
}

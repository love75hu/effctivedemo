package cn.mediinfo.grus.shujuzx.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Schema(description = "病历详情信息")
public class BingLiXQDTO {

    @Schema(description = "病历ID")
    private String bingLiId;

    @Schema(description = "病历名称")
    private String bingLiMC;

    @Schema(description = "病历内容")
    private String bingLiNR;

    @Schema(description = "病历时间")
    private Date bingLiSJ;

    @Schema(description = "就诊id")
    private String jiuzhenId;

    @Schema(description = "就诊类型")
    private String jiuzhenLXDM;

    @Schema(description = "病人id")
    private String bingRenId;
}

package cn.mediinfo.grus.shujuzx.dto.fangan;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Schema(description = "就诊病历信息")
public class JiuZhenBLXXDTO {
    @Schema(description = "就诊ID")
    private String jiuZhenID;
    @Schema(description = "就诊业务类型")
    private String jiuZhenYWLX;
    @Schema(description = "组织机构ID")
    private String zuZhiJGID;
    @Schema(description = "组织机构名称")
    private String zuZhiJGMC;
    @Schema(description = "病历ID列表")
    private List<String> bingLiList;
}

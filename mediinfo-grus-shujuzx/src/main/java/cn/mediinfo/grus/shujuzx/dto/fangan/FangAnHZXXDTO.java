package cn.mediinfo.grus.shujuzx.dto.fangan;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "根据方案查询出的患者信息")
public class FangAnHZXXDTO {
    @Schema(description = "组织机构id")
    private String zuZhiJGID;
    @Schema(description = "病人ID")
    private String bingRenID;
}

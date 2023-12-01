package cn.mediinfo.grus.shujuzx.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "收藏夹")
public class ShouCangJiaDto {
    @Schema(description = "收藏夹ID")
    private String shouCangJID;

    @Schema(description = "收藏夹名称")
    private String shouCangJMC;
}

package cn.mediinfo.grus.shujuzx.dto.zhibiaoxxs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Data
public class ZhiBiaoXXListDto {
    @Schema(description = "指标分类ID")
    private String zhiBiaoFLID;
    @Schema(description = "指标分类名称")
    private String zhiBiaoFLMC;
    @Schema(description = "指标明细集合")
    private List<ZhiBiaoMXListDto> zhiBiaoMXList;
}

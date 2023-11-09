package cn.mediinfo.grus.shujuzx.dto.wendangjls;

import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class SC_ZD_WenDangGroupListDto {
    @Schema(description = "类别代码[SC0010]")
    private String leiBieDM;

    @Schema(description = "类别名称")
    private String leiBieMC;

    @Schema(description = "子数据")
    private List<SC_ZD_WenDangDto> itemList;
}

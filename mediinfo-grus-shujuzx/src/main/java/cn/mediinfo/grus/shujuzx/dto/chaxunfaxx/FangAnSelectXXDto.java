package cn.mediinfo.grus.shujuzx.dto.chaxunfaxx;

import cn.mediinfo.grus.shujuzx.dto.fangan.FangAnSCDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class FangAnSelectXXDto {
    @Schema(description = "主键")
    private String id;
    @Schema(description = "方案类型代码")
    private String fangAnLXDM;
    @Schema(description = "方案类型名称")
    private String fangAnLXMC;
    @Schema(description = "方案ID")
    private String fangAnID;
    @Schema(description = "方案名称")
    private String fangAnMC;
    @Schema(description = "关键字")
    private String guanJianZi;
    @Schema(description = "方案输出项")
    private List<FangAnSCDTO> fangAnSCDTOList;

}

package cn.mediinfo.grus.shujuzx.dto.chaxunfaxx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FangAnCXLSDto {
    @Schema(description = "主键")
    private String id;
    @Schema(description = "方案ID")
    private String fangAnID;
    @Schema(description = "方案名称")
    private String fangAnMC;
    @Schema(description = "方案查询类型代码")
    private String fangAnLXDM;
    @Schema(description = "方案查询类型名称")
    private String fangAnLXMC;
    @Schema(description = "关键字")
    private String GuanJianZi;
    @Schema(description = "标题名称")
    private String biaoTiMC;
    @Schema(description = "查询条件描述")
    private String chaXunTJMS;
}

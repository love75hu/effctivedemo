package cn.mediinfo.grus.shujuzx.dto.wenDang;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SC_ZD_WenDangMBDto {
    @Schema(description = "主键id")
    private String id;

    @Schema(description = "文档ID")
    private String wenDangID;

    @Schema(description = "文档名称")
    private String wenDangMC;

    @Schema(description = "模板内容 ")
    private String muBanNR;
}
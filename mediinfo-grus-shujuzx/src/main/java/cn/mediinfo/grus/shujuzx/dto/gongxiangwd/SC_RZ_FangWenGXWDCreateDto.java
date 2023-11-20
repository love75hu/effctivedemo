package cn.mediinfo.grus.shujuzx.dto.gongxiangwd;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class SC_RZ_FangWenGXWDCreateDto {
    @Schema(description = "访问人ID")
    private String fangWenRID;

    @Schema(description = "访问人姓名")
    private String fangWenRXM;

    @Schema(description = "访问时间")
    private Date fangWenSJ;

    @Schema(description = "访问机构ID")
    private String fangWenJGID;

    @Schema(description = "访问机构名称")
    private String fangWenJGMC;

    @Schema(description = "访问系统ID")
    private String fangWenXTID;

    @Schema(description = "访问系统名称")
    private String fangWenXTMC;

    @Schema(description = "文档记录ID")
    private String wenDangJLID;

    @Schema(description = "文档ID")
    private String wenDangID;

    @Schema(description = "文档名称")
    private String wenDangMC;

    @Schema(description = "病人ID")
    private String bingRenID;

    @Schema(description = "姓名")
    private String xingMing;
}
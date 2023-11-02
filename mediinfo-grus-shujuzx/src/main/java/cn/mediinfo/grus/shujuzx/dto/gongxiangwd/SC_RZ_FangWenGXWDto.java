package cn.mediinfo.grus.shujuzx.dto.gongxiangwd;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Data
public class SC_RZ_FangWenGXWDto {
    @Schema(description = "主键")
    private String id;

    @Schema(description = "组织机构ID")
    private String zuZhiJGID;

    @Schema(description = "组织机构名称")
    private String zuZhiJGMC;

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

    @Schema(description = "查询模式代码【SC0002】")
    private String chaXunMSDM;

    @Schema(description = "查询模式名称")
    private String chaXunMSMC;

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
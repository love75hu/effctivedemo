package cn.mediinfo.grus.shujuzx.dto.wenDang;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SC_ZD_WenDangDto {
    @Schema(description = "主键")
    private String id;

    @Schema(description = "组织机构ID")
    private String zuZhiJGID;

    @Schema(description = "组织机构名称")
    private String zuZhiJGMC;

    @Schema(description = "文档ID")
    private String wenDangID;

    @Schema(description = "文档名称")
    private String wenDangMC;

    @Schema(description = "类别代码[SC0010]")
    private String leiBieDM;

    @Schema(description = "类别名称")
    private String leiBieMC;

    @Schema(description = "版本代码[SC0011]")
    private String banBenDM;

    @Schema(description = "版本名称")
    private String banBenMC;

    @Schema(description = "标准ID")
    private String biaoZhunID;

    @Schema(description = "标准名称")
    private String biaoZhunMC;

    @Schema(description = "OID")
    private String oID;

    @Schema(description = "描述")
    private String miaoShu;

    @Schema(description = "顺序号")
    private Integer shunXuHao;

    @Schema(description = "启用标志")
    private Integer qiYongBZ;
}
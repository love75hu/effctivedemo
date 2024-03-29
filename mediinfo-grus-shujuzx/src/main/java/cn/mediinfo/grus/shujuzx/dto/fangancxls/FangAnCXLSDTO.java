package cn.mediinfo.grus.shujuzx.dto.fangancxls;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Schema(description = "方案查询历史")
public class FangAnCXLSDTO {
    @Schema(description = "方案查询历史id")
    private String id;
    @Schema(description = "查询类型代码")
    private String chaXunLXDM;
    @Schema(description = "查询类型名称(1-方案查询，2-高级查询)")
    private String chaXunLXMC;
    @Schema(description = "方案id")
    private String fangAnID;
    @Schema(description = "方案名称")
    private String fangAnMC;
    @Schema(description = "关键字")
    private String guanJianZi;
    @Schema(description = "查询条件描述")
    private String chaXunTJMS;
    @Schema(description = "查询sql")
    private String chaXunSQL;
    @Schema(description = "查询时间")
    private Date chaXunSJ;
    @Schema(description = "查询人id")
    private String chaXunRID;
    @Schema(description = "查询人姓名")
    private String chaXunRXM;
    @Column(columnDefinition = "查询条件;JSON字符串")
    private String chaXunTJ;
    @Column(columnDefinition = "查询输出：JSON字符串")
    private String chaXunSC;
    @Column(columnDefinition = "方案类型代码;0-全部1-门诊2-急诊3-住院4-公卫")
    private String fangAnLXDM;
    @Column(columnDefinition = "方案类型名称")
    private String fangAnLXMC;
}
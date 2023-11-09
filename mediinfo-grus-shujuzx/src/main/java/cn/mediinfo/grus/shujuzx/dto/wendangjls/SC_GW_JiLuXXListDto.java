package cn.mediinfo.grus.shujuzx.dto.wendangjls;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class SC_GW_JiLuXXListDto {
    /**
     * 租户ID
     */
    @Column(columnDefinition = "租户ID")
    private String zuHuID;
    /**
     * 租户名称
     */
    @Column(columnDefinition = "租户名称")
    private String zuHuMC;
    /**
     * 组织机构ID
     */
    @Column(columnDefinition = "组织机构ID")
    private String zuZhiJGID;
    /**
     * 组织机构名称
     */
    @Column(columnDefinition = "组织机构名称")
    private String zuZhiJGMC;
    /**
     * 文档ID
     */
    @Column(columnDefinition = "文档ID")
    private String wenDangID;
    /**
     * 文档名称
     */
    @Column(columnDefinition = "文档名称")
    private String wenDangMC;
    /**
     * 生成时间
     */
    @Column(columnDefinition = "生成时间")
    private Date shengChengSJ;
    /**
     * 病人ID
     */
    @Column(columnDefinition = "病人ID")
    private String bingRenID;
    /**
     * 姓名
     */
    @Column(columnDefinition = "姓名")
    private String xingMing;
    /**
     * 性别名称
     */
    @Column(columnDefinition = "性别名称")
    private String xingBieMC;
    /**
     * 出生日期
     */
    @Column(columnDefinition = "出生日期")
    private Date chuShengRQ;
    /**
     * 证件类型名称
     */
    @Column(columnDefinition = "证件类型名称")
    private String zhengJianLXMC;
    /**
     * 证件号码
     */
    @Column(columnDefinition = "证件号码")
    private String zhengJianHM;
    /**
     * 数据来源名称
     */
    @Column(columnDefinition = "数据来源名称")
    private String shuJuLYMC;
}

package cn.mediinfo.grus.shujuzx.dto.wendangjls;

import cn.mediinfo.grus.shujuzx.dto.wendangnrs.SC_GW_JiLuNRDto;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class SC_GW_JiLuXXExportDto {
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
     * 性别代码
     */
    @Column(columnDefinition = "性别代码")
    private String xingBieDM;
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
     * 证件类型代码
     */
    @Column(columnDefinition = "证件类型代码")
    private String zhengJianLXDM;
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
     * 业务时间
     */
    @Column(columnDefinition = "业务时间")
    private Date yeWuSJ;
    /**
     * 业务主键ID
     */
    @Column(columnDefinition = "业务主键ID")
    private String yeWuZJID;
    /**
     * 数据来源代码
     */
    @Column(columnDefinition = "数据来源代码")
    private String shuJuLYDM;
    /**
     * 数据来源名称
     */
    @Column(columnDefinition = "数据来源名称")
    private String shuJuLYMC;
    /**
     * 压缩方式代码
     */
    @Column(columnDefinition = "压缩方式代码")
    private String yaSuoFSDM;
    /**
     * 内容
     */
    @Column(columnDefinition = "内容")
    private String neiRong;
}

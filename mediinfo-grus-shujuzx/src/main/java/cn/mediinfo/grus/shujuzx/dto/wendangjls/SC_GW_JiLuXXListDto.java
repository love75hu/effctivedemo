package cn.mediinfo.grus.shujuzx.dto.wendangjls;

import jakarta.persistence.Column;
import lombok.Data;

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
     * 文档ID
     */
    @Column(columnDefinition = "文档名称")
    private String wenDangMC;
    /**
     * 类别代码[SC0010]
     */
    @Column(columnDefinition = "类别代码[SC0010]")
    private String leiBieDM;
    /**
     * 类别代码[SC0010]
     */
    @Column(columnDefinition = "类别名称")
    private String leiBieMC;
    /**
     * 版本代码[SC0011]
     */
    @Column(columnDefinition = "版本代码[SC0011]")
    private String banBenDM;
    /**
     * 版本名称
     */
    @Column(columnDefinition = "版本名称")
    private String banBenMC;
    /**
     * 标准ID
     */
    @Column(columnDefinition = "标准ID")
    private String biaoZhunID;
    /**
     * 标准名称
     */
    @Column(columnDefinition = "标准名称")
    private String biaoZhunMC;
    /**
     * OID
     */
    @Column(columnDefinition = "OID")
    private String oID;
    /**
     * 描述
     */
    @Column(columnDefinition = "描述")
    private String miaoShu;
    /**
     * 顺序号
     */
    @Column(columnDefinition = "顺序号")
    private Integer shunXuHao;
    /**
     * 启用标志
     */
    @Column(columnDefinition = "启用标志")
    private Integer qiYongBZ;
}

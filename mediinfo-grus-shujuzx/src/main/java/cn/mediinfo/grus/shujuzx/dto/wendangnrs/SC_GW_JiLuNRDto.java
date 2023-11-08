package cn.mediinfo.grus.shujuzx.dto.wendangnrs;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SC_GW_JiLuNRDto {
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
     * 文档记录ID
     */
    @Column(columnDefinition = "文档记录ID")
    private String wenDangJLID;
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
     * 内容
     */
    @Column(columnDefinition = "内容")
    private String NeiRong;
    /**
     * 压缩方式代码
     */
    @Column(columnDefinition = "压缩方式代码")
    private String yaSuoFSDM;
    /**
     * 压缩方式名称
     */
    @Column(columnDefinition = "压缩方式名称")
    private String yaSuoFSMC;
}

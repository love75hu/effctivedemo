package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

/*** 共享文档记录内容*/
@Data
@Entity
@Table(name="SC_GW_JiLuNRModel")
public class SC_GW_JiLuNRModel extends StringMTEntity {
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

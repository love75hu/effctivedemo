package cn.mediinfo.grus.shujuzx.model;

import lombok.Data;
import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

/*** 方案查询历史*/
@Data
@Entity
@Table(name = "sc_cx_fangancxls")
public class SC_CX_FangAnCXLSModel extends StringMTEntity {
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
     * 查询类型代码
     */
    @Column(columnDefinition = "查询类型代码")
    private String chaXunLXDM;
    /**
     * 查询类型名称(1-方案查询，2-高级查询)
     */
    @Column(columnDefinition = "查询类型名称(1-方案查询，2-高级查询)")
    private String chaXunLXMC;
    /**
     * 方案ID
     */
    @Column(columnDefinition = "方案ID")
    private String fangAnID;
    /**
     * 方案名称
     */
    @Column(columnDefinition = "方案名称")
    private String fangAnMC;
    /**
     * 关键字
     */
    @Column(columnDefinition = "关键字")
    private String guanJianZi;
    /**
     * 查询条件描述
     */
    @Column(columnDefinition = "查询条件描述")
    private String chaXunTJMS;
    /**
     * 查询条件;JSON字符串
     */
    @Column(columnDefinition = "查询条件;JSON字符串")
    private String chaXunTJ;
    /**
     * 查询SQL
     */
    @Column(columnDefinition = "查询SQL")
    private String chaXunSQL;
    /**
     * 查询输出：JSON字符串
     */
    @Column(columnDefinition = "查询输出：JSON字符串")
    private String chaXunSC;
    /**
     * 查询耗时;单位：ms
     */
    @Column(columnDefinition = "查询耗时;单位：ms")
    private Integer chaXunHS;
    /**
     * 查询时间
     */
    @Column(columnDefinition = "查询时间")
    private Date chaXunSJ;
    /**
     * 查询人ID
     */
    @Column(columnDefinition = "查询人ID")
    private String chaXunRID;
    /**
     * 查询人姓名
     */
    @Column(columnDefinition = "查询人姓名")
    private String chaXunRXM;
}
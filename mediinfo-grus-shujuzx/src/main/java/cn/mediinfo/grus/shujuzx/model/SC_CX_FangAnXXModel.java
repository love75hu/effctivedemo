package cn.mediinfo.grus.shujuzx.model;

import lombok.Data;
import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

/*** 检索方案信息*/
@Data
@Entity
@Table(name = "sc_cx_fanganxx")
public class SC_CX_FangAnXXModel extends StringMTEntity {
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
     * 方案类型代码;0-全部1-门诊2-急诊3-住院4-公卫
     */
    @Column(columnDefinition = "方案类型代码;0-全部1-门诊2-急诊3-住院4-公卫")
    private String fangAnLXDM;
    /**
     * 方案类型名称
     */
    @Column(columnDefinition = "方案类型名称")
    private String fangAnLXMC;
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
}
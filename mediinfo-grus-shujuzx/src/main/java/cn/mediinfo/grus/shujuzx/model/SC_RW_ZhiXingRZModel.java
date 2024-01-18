package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="sc_rw_zhixingrz")
public class SC_RW_ZhiXingRZModel extends StringMTEntity {
    @Column(columnDefinition = "组织机构ID")
    private String zuZhiJGID;
    @Column(columnDefinition = "组织机构名称")
    private String zuZhiJGMC;
    @Column(columnDefinition = "任务id")
    private String renWuID;
    @Column(columnDefinition = "任务名称")
    private String renWuMC;
    @Column(columnDefinition = "执行时间")
    private Date zhiXingSJ;
    @Column(columnDefinition = "执行开始时间")
    private Date zhiXingKSSJ;
    @Column(columnDefinition = "执行结束时间")
    private Date zhiXingJSSJ;
    @Column(columnDefinition = "执行耗时")
    private Integer zhiXingHS;
    @Column(columnDefinition = "执行状态代码")
    private String zhiXingZTDM;
    @Column(columnDefinition = "执行状态名称")
    private String zhiXingZTMC;

    @Column(columnDefinition = "执行方式代码[SC0020]")
    private String zhiXingFSDM;
    @Column(columnDefinition = "执行方式名称")
    private String zhiXingFSMC;

    @Column(columnDefinition = "入参")
    private String ruCan;

    @Column(columnDefinition = "日志内容")
    private String riZhiNR;
    @Column(columnDefinition = "执行人id")
    private String zhiXingRID;
    @Column(columnDefinition = "执行人id")
    private String zhiXingRXM;

}

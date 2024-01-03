package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="sc_rw_shujuyuan")
public class SC_RW_ShuJuYuanModel extends StringMTEntity {
    @Column(columnDefinition = "组织机构ID")
    private String zuZhiJGID;
    @Column(columnDefinition = "组织机构名称")
    private String zuZhiJGMC;
    @Column(columnDefinition = "任务id")
    private String renWuID;
    @Column(columnDefinition = "任务名称")
    private String renWuMC;
    @Column(columnDefinition = "数据源ID,对应GY_ZD_SHUJUYUAN.ID")
    private String shuJuYID;
    @Column(columnDefinition = "数据源名称")
    private String shuJuYMC;
    @Column(columnDefinition = "启用标志")
    private Integer qiYongBZ;
    @Column(columnDefinition = "业务最新时间")
    private Date yeWuZXSJ;
}

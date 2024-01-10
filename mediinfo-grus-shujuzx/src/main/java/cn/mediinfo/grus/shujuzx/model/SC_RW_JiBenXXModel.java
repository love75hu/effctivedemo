package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity

@Table(name="sc_rw_jibenxx")
public class SC_RW_JiBenXXModel  extends StringMTEntity {
    @Column(columnDefinition = "组织机构ID")
    private String zuZhiJGID;
    @Column(columnDefinition = "组织机构名称")
    private String zuZhiJGMC;
    @Column(columnDefinition = "任务id")
    private String renWuID;
    @Column(columnDefinition = "任务名称")
    private String renWuMC;
    @Column(columnDefinition = "任务说明")
    private String renWuSM;
    @Column(columnDefinition = "任务参数")
    private String renWuCS;
    @Column(columnDefinition = "任务地址")
    private String renWuDZ;
    @Column(columnDefinition = "分类代码[SC0017]")
    private String fenLeiDM;
    @Column(columnDefinition = "分类名称")
    private String fenLeiMC;
    @Column(columnDefinition = "执行频率代码[SC0018]")
    private String zhiXingPLDM;
    @Column(columnDefinition = "执行频率名称")
    private String zhiXingPLMC;
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
    @Column(columnDefinition = "最近执行日志id")
    private String zuiJinZXRZID;
    @Column(columnDefinition = "启用标志")
    private Integer qiYongBZ;
    @Column(columnDefinition = "备注")
    private String beiZhu;
    @Column(columnDefinition = "顺序号")
    private Integer shunXuHao;

}

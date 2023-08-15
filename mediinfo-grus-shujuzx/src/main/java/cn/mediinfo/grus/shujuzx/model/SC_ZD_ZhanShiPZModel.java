package cn.mediinfo.grus.shujuzx.model;
import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name="sc_zd_zhanshipz")
public class SC_ZD_ZhanShiPZModel extends StringMTEntity {
   @Column(columnDefinition = "查询模式代码")
   private String chaXunMSDM;
   @Column(columnDefinition = "查询模式名称")
   private String chaXunMSMC;
   @Column(columnDefinition = "功能ID")
   private String gongNengID;
   @Column(columnDefinition = "功能名称")
   private String gongNengMC;
   @Column(columnDefinition = "自定义名称")
   private String ziDingYMC;
   @Column(columnDefinition = "配置类型代码")
   private String peiZhiLXDM;
   @Column(columnDefinition = "配置类型名称")
   private String peiZhiLXMC;
   @Column(columnDefinition = "路径类型代码")
   private String luJingLXDM;
   @Column(columnDefinition = "路径类型名称")
   private String luJingLXMC;
   @Column(columnDefinition = "路径")
   private String luJing;
   @Column(columnDefinition = "图标")
   private String tuBiao;
   @Column(columnDefinition = "启用标志")
   private Integer qiYongBZ;
   @Column(columnDefinition = "顺序号")
   private Integer shunXuHao;

   private String zuZhiJGID;

   private String zuZhiJGMC;

}

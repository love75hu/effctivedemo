package cn.mediinfo.grus.shujuzx.model;


import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_zd_yinsipz")
public class SC_ZD_YinSiPZModel extends StringMTEntity {
   @Column(columnDefinition = "查询模式代码")
   private String chaXunMSDM;
   @Column(columnDefinition = "查询模式名称")
   private String chaXunMSMC;
   @Column(columnDefinition = "数据元名称")
   private String shuJuYMC;
   @Column(columnDefinition = "数据元列名")
   private String shuJuYLM;
   @Column(columnDefinition = "脱敏方式代码【DC0001】")
   private String tuoMinFSDM;
   @Column(columnDefinition = "脱敏方式名称")
   private String tuoMinFSMC;
   @Column(columnDefinition = "脱敏规则")
   private String tuoMinGZ;
   @Column(columnDefinition = "脱敏说明")
   private String tuoMinSM;
   @Column(columnDefinition = "启用标志")
   private Integer qiYongBZ;
   @Column(columnDefinition = "顺序号")
   private Integer shunXuHao;

   private String zuZhiJGID;

   private String zuZhiJGMC;

}

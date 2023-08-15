package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_zd_shujuyzy")
public class SC_ZD_ShuJuYZYModel extends StringMTEntity {
   @Column(columnDefinition = "值域ID")
   private String zhiYuID;
   @Column(columnDefinition = "值域名称")
   private String zhiYuMC;
   @Column(columnDefinition = "数据源类别ID")
   private String shuJuYLBID;
   @Column(columnDefinition = "数据源类别名称")
   private String shuJuYLBMC;
   @Column(columnDefinition = "标准代码")
   private String biaoZhunDM;
   @Column(columnDefinition = "标准名称")
   private String biaoZhunMC;
   @Column(columnDefinition = "代码级别")
   private Integer daiMaJB;
   @Column(columnDefinition = "父级代码（非父级标准代码）")
   private String fuJiDM;
   @Column(columnDefinition = "末级标志")
   private Integer moJiBZ;
   @Column(columnDefinition = "备注说明")
   private String beiZhuSM;
   @Column(columnDefinition = "输入码1")
   private String shuRuMa1;
   @Column(columnDefinition = "输入码2")
   private String shuRuMa2;
   @Column(columnDefinition = "输入码3")
   private String shuRuMa3;
   @Column(columnDefinition = "顺序号")
   private Integer shunXuHao;

   private String zuZhiJGID;

   private String zuZhiJGMC;

}

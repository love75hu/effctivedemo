package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="sc_rz_fangwenlcsj")
public class SC_RZ_FangWenLCSJModel extends StringMTEntity {
   @Column(columnDefinition = "访问人ID")
   private String fangWenRID;
   @Column(columnDefinition = "访问人姓名")
   private String fangWenRXM;
   @Column(columnDefinition = "访问时间")
   private Date fangWenSJ;
   @Column(columnDefinition = "访问机构ID")
   private String fangWenJGID;
   @Column(columnDefinition = "访问机构名称")
   private String fangWenJGMC;
   @Column(columnDefinition = "查询模式代码")
   private String chaXunMSDM;
   @Column(columnDefinition = "查询模式名称")
   private String chaXunMSMC;
   @Column(columnDefinition = "病人ID")
   private String bingRenID;
   @Column(columnDefinition = "姓名")
   private String xingMing;
   @Column(columnDefinition = "功能ID")
   private String gongNengID;
   @Column(columnDefinition = "功能名称")
   private String gongNengMC;

   private String zuZhiJGID;

   private String zuZhiJGMC;

}

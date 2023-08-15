package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="sc_sc_shoucangjmx")
public class SC_SC_ShouCangJMXModel extends StringMTEntity {
   @Column(columnDefinition = "病人ID")
   private String bingRenID;
   @Column(columnDefinition = "姓名")
   private String xingMing;
   @Column(columnDefinition = "收藏夹ID")
   private String shouCangJID;
   @Column(columnDefinition = "收藏夹名称")
   private String shouCangJMC;
   @Column(columnDefinition = "收藏人ID")
   private String shouCangRID;
   @Column(columnDefinition = "收藏人姓名")
   private String shouCangRXM;
   @Column(columnDefinition = "收藏时间")
   private Date shouCangSJ;

   private String zuZhiJGID;

   private String zuZhiJGMC;

}

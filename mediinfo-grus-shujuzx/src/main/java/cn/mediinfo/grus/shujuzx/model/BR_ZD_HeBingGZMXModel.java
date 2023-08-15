package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name="br_zd_hebinggzmx")
public class BR_ZD_HeBingGZMXModel extends StringMTEntity {
   @Column(columnDefinition = "规则ID")
   private String guiZeID;
   @Column(columnDefinition = "规则名称")
   private String guiZeMC;
   @Column(columnDefinition = "合并权重ID")
   private String heBingQZID;
   @Column(columnDefinition = "代码")
   private String daiMa;
   @Column(columnDefinition = "名称")
   private String mingCheng;
   @Column(columnDefinition = "权重(1-100)")
   private BigDecimal quanZhong;

   private String zuZhiJGID;

   private String zuZhiJGMC;

}

package cn.mediinfo.grus.shujuzx.model;
import java.util.*;

import cn.mediinfo.starter.base.multitenancy.entity.StringMTEntity;
import lombok.Data;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name="br_da_zhusuoyczrz")
public class BR_DA_ZhuSuoYCZRZModel extends StringMTEntity {
   @Column(columnDefinition = "病人ID")
   private String bingRenID;
   @Column(columnDefinition = "姓名")
   private String xingMing;
   @Column(columnDefinition = "操作类型代码")
   private String caoZuoLXDM;
   @Column(columnDefinition = "操作类型名称[1.合并2.取消合并3.忽略4.修改5.新增]")
   private String caoZuoLXMC;
   @Column(columnDefinition = "操作人ID")
   private String caoZuoRID;
   @Column(columnDefinition = "操作人姓名")
   private String caoZuoRXM;
   @Column(columnDefinition = "操作时间")
   private Date caoZuoSJ;
   @Column(columnDefinition = "操作内容")
   private String caoZuoNR;

   private String zuZhiJGID;

   private String zuZhiJGMC;

}

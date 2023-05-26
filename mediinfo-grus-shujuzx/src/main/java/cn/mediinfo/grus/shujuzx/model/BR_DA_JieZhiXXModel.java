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
@Table(name="br_da_jiezhixx")
public class BR_DA_JieZhiXXModel extends StringMTEntity {
   @Column(columnDefinition = "介质号")
   private String jieZhiHao;
   @Column(columnDefinition = "类型代码[1,病历本,2,就诊卡.3,医保卡.4,磁卡.5,条码.6,公费证号.7.电子健康码]")
   private String leiXingDM;
   @Column(columnDefinition = "类型名称")
   private String leiXingMC;
   @Column(columnDefinition = "病人ID")
   private String bingRenID;
   @Column(columnDefinition = "费用类别ID")
   private String feiYongLBID;
   @Column(columnDefinition = "费用类别名称")
   private String feiYongLBMC;
   @Column(columnDefinition = "费用性质ID")
   private String feiYongXZID;
   @Column(columnDefinition = "费用性质名称")
   private String feiYongXZMC;
   @Column(columnDefinition = "发卡时间")
   private Date faKaSJ;
   @Column(columnDefinition = "发卡人ID")
   private String faKaRID;
   @Column(columnDefinition = "发卡人姓名")
   private String faKaRXM;
   @Column(columnDefinition = "介质状态[0,正常,1,挂失.2,作废.3,退卡][7011]")
   private String jieZhiZT;
   @Column(columnDefinition = "第三方病人ID")
   private String diSanFBRID;

   private String zuZhiJGID;

   private String zuZhiJGMC;

}

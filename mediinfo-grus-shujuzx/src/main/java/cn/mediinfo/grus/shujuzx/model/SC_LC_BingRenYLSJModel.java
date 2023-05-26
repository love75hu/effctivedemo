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
@Table(name="sc_lc_bingrenylsj")
public class SC_LC_BingRenYLSJModel extends StringMTEntity {
   @Column(columnDefinition = "居民主索引，病人基本信息表主键")
   private String bingRenID;
   @Column(columnDefinition = "姓名")
   private String xingMing;
   @Column(columnDefinition = "姓名全拼")
   private String xingMingQP;
   @Column(columnDefinition = "输入码1(拼音码)")
   private String shuRuMA1;
   @Column(columnDefinition = "输入码2(五笔码)")
   private String shuRuMA2;
   @Column(columnDefinition = "输入码3(自定义码)")
   private String shuRuMA3;
   @Column(columnDefinition = "性别代码[0460]")
   private String xingBieDM;
   @Column(columnDefinition = "性别名称")
   private String xingBieMC;
   @Column(columnDefinition = "出生日期")
   private Date chuShengRQ;
   @Column(columnDefinition = "证件类型代码[0001]")
   private String zhengJianLXDM;
   @Column(columnDefinition = "证件类型名称")
   private String zhengJianLXMC;
   @Column(columnDefinition = "证件号码")
   private String zhengJianHM;
   @Column(columnDefinition = "门诊次数")
   private Integer menZhenCS;
   @Column(columnDefinition = "急诊次数")
   private Integer jiZhenCS;
   @Column(columnDefinition = "住院次数")
   private Integer zhuYuanCS;
   @Column(columnDefinition = "公卫次数")
   private Integer gongWeiCS;
   @Column(columnDefinition = "建档人ID")
   private String jianDangRID;
   @Column(columnDefinition = "建档人姓名")
   private String jianDangRXM;
   @Column(columnDefinition = "建档时间")
   private Date jianDangSJ;

   private String zuZhiJGID;

   private String zuZhiJGMC;

}

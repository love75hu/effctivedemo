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
@Table(name="br_da_jiaochasy")
public class BR_DA_JiaoChaSYModel extends StringMTEntity {
   @Column(columnDefinition = "关联病人ID")
   private String guanLianBRID;
   @Column(columnDefinition = "自动合并标志")
   private Integer ziDongHBBZ;
   @Column(columnDefinition = "主病人ID[顶级病人ID]")
   private String zhuBingRID;
   @Column(columnDefinition = "合并人ID")
   private String heBingRID;
   @Column(columnDefinition = "合并人姓名")
   private String heBingRXM;
   @Column(columnDefinition = "合并时间")
   private Date heBingSJ;

   private String zuZhiJGID;

   private String zuZhiJGMC;

}

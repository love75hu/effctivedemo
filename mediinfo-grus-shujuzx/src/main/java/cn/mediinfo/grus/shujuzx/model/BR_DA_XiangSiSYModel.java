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
@Table(name="br_da_xiangsisy")
public class BR_DA_XiangSiSYModel extends StringMTEntity {
   @Column(columnDefinition = "病人ID1")
   private String bingRenID1;
   @Column(columnDefinition = "姓名1")
   private String xingMing1;
   @Column(columnDefinition = "病人ID2")
   private String bingRenID2;
   @Column(columnDefinition = "姓名2")
   private String xingMing2;
   @Column(columnDefinition = "相似度(0-100)")
   private BigDecimal xiangSiDu;
   @Column(columnDefinition = "规则ID")
   private String guiZeID;
   @Column(columnDefinition = "规则名称")
   private String guiZeMC;
   @Column(columnDefinition = "合并标志(0-未合并；1-合并)")
   private Integer heBingBZ;
   @Column(columnDefinition = "忽略标志")
   private Integer huLueBZ;

   private String zuZhiJGID;

   private String zuZhiJGMC;

}

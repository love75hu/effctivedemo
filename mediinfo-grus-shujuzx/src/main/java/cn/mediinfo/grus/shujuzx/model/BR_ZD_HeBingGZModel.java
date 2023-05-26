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
@Table(name="br_zd_hebinggz")
public class BR_ZD_HeBingGZModel extends StringMTEntity {
   @Column(columnDefinition = "规则ID")
   private String guiZeID;
   @Column(columnDefinition = "规则名称")
   private String guiZeMC;
   @Column(columnDefinition = "阀值")
   private BigDecimal faZhi;
   @Column(columnDefinition = "顺序号")
   private Integer shunXuHao;

   private String zuZhiJGID;

   private String zuZhiJGMC;

}

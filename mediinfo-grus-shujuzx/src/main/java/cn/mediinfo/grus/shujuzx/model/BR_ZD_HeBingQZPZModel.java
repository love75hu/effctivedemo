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
@Table(name="br_zd_hebingqzpz")
public class BR_ZD_HeBingQZPZModel extends StringMTEntity {
   @Column(columnDefinition = "合并权重ID")
   private String heBingQZID;
   @Column(columnDefinition = "代码")
   private String daiMa;
   @Column(columnDefinition = "名称")
   private String mingCheng;
   @Column(columnDefinition = "权重(1-100)")
   private BigDecimal quanZhong;
   @Column(columnDefinition = "分类ID")
   private String fuLeiID;
   @Column(columnDefinition = "分类名称")
   private String fuLeiMC;
   @Column(columnDefinition = "顺序号")
   private Integer shunXuHao;
   @Column(columnDefinition = "末级标志")
   private Integer moJiBZ;

   private String zuZhiJGID;

   private String zuZhiJGMC;

}

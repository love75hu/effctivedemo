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
@Table(name="sc_zd_bihuanlcjd")
public class SC_ZD_BiHuanLCJDModel extends StringMTEntity {
   @Column(columnDefinition = "流程ID")
   private String liuChengID;
   @Column(columnDefinition = "流程名称")
   private String liuChengMC;
   @Column(columnDefinition = "闭环类型代码【DC0004】")
   private String biHuanLXDM;
   @Column(columnDefinition = "闭环类型名称")
   private String biHuanLXMC;
   @Column(columnDefinition = "节点ID")
   private String jieDianID;
   @Column(columnDefinition = "节点名称")
   private String jieDianMC;
   @Column(columnDefinition = "节点自定义名称")
   private String jieDianZDYMC;
   @Column(columnDefinition = "顺序号")
   private Integer shunXuHao;

   private String zuZhiJGID;

   private String zuZhiJGMC;

}

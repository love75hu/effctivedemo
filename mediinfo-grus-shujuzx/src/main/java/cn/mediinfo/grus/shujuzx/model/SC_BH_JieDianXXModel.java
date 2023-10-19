package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 闭环节点信息
 */
@Data
@Entity
@Table(name="sc_bh_jiedianxx")
public class SC_BH_JieDianXXModel extends StringMTEntity {
   /**
    * 组织机构ID
    */
   @Column(columnDefinition = "组织机构ID")
   private String zuZhiJGID;
   /**
    * 组织机构名称
    */
   @Column(columnDefinition = "组织机构名称")
   private String zuZhiJGMC;
   /**
    * 闭环ID
    */
   @Column(columnDefinition = "闭环ID")
   private String biHuanID;
   /**
    * 闭环名称
    */
   @Column(columnDefinition = "闭环名称")
   private String biHuanMC;
   /**
    * 视图ID
    */
   @Column(columnDefinition = "视图ID")
   private String shiTuID;
   /**
    * 视图名称
    */
   @Column(columnDefinition = "视图名称")
   private String shiTuMC;
   /**
    * 节点ID
    */
   @Column(columnDefinition = "节点ID")
   private String jieDianID;
   /**
    * 节点名称
    */
   @Column(columnDefinition = "节点名称")
   private String jieDianMC;
   /**
    * 显示名称
    */
   @Column(columnDefinition = "显示名称")
   private String xianShiMC;
   /**
    * 隐藏标志
    */
   @Column(columnDefinition = "隐藏标志")
   private Integer yinCangBZ;
   /**
    * 必须节点标志
    */
   @Column(columnDefinition = "必须节点标志")
   private Integer biXuBZ;
   /**
    * 并行节点标志
    */
   @Column(columnDefinition = "并行节点标志")
   private Integer bingXingBZ;
   /**
    * 顺序号
    */
   @Column(columnDefinition = "顺序号")
   private Integer shunXuHao;

}

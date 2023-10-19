package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 子闭环信息
 */
@Data
@Entity
@Table(name="sc_bh_zibihxx")
public class SC_BH_ZiBiHXXModel extends StringMTEntity {

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
    * 子闭环ID
    */
   @Column(columnDefinition = "子闭环ID")
   private String ziBiHID;
   /**
    * 子闭环名称
    */
   @Column(columnDefinition = "子闭环名称")
   private String ziBiHMC;
   /**
    * 关联字段编码
    */
   @Column(columnDefinition = "关联字段编码")
   private String guanLianZDBM;
   /**
    * 关联字段名称
    */
   @Column(columnDefinition = "关联字段名称")
   private String guanLianZDMC;
}

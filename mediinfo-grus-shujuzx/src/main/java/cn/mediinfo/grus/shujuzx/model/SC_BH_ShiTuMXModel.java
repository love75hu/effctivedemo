package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_bh_shitumx")
public class SC_BH_ShiTuMXModel extends StringMTEntity {
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
    * 闭环类型代码
    */
   @Column(columnDefinition = "闭环类型代码")
   private String biHuanLXDM;
   /**
    * 闭环类型名称
    */
   @Column(columnDefinition = "闭环类型名称")
   private String biHuanLXMC;
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
    * 字段编码
    */
   @Column(columnDefinition = "字段编码")
   private String ziDuanBM;
   /**
    * 字段名称
    */
   @Column(columnDefinition = "字段名称")
   private String ziDuanMC;
   /**
    * 条件标志
    */
   @Column(columnDefinition = "条件标志")
   private Integer tiaoJianBZ;
   /**
    * 入参标志
    */
   @Column(columnDefinition = "入参标志")
   private Integer ruCanBZ;

}

package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 视图字段关系
 */
@Data
@Entity
@Table(name="sc_cx_shitumxgx")
public class SC_CX_ShiTuMXGXModel extends StringMTEntity {
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
    * 关联字段编码
    */
   @Column(columnDefinition = "关联字段编码")
   private String guanLianZDBM;
   /**
    * 关联字段名称
    */
   @Column(columnDefinition = "关联字段名称")
   private String guanLianZDMC;
   /**
    * 顺序号
    */
   @Column(columnDefinition = "顺序号")
   private Integer sHUXUHAO;
}
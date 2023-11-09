package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 检索方案内容
 */
@Data
@Entity
@Table(name="sc_cx_fangannr")
public class SC_CX_FangAnNRModel extends StringMTEntity {
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
    * 方案ID
    */
   @Column(columnDefinition = "方案ID")
   private String fangAnID;
   /**
    * 方案名称
    */
   @Column(columnDefinition = "方案名称")
   private String fangAnMC;
   /**
    * 查询条件;JSON字符串
    */
   @Column(columnDefinition = "查询条件;JSON字符串")
   private String chaXunTJ;
   /**
    * 查询SQL
    */
   @Column(columnDefinition = "查询SQL")
   private String chaXunSQL;
}
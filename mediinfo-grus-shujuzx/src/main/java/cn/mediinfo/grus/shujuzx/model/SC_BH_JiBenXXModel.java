package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_bh_jibenxx")
public class SC_BH_JiBenXXModel extends StringMTEntity {

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
    * 顺序号
    */
   @Column(columnDefinition = "顺序号")
   private Integer shunXuHao;
   /**
    * 启用标志
    */
   @Column(columnDefinition = "启用标志")
   private Integer qiYongBZ;
}

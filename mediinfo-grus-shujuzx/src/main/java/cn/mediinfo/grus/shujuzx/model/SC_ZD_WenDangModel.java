package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_zd_wendang")
public class SC_ZD_WenDangModel extends StringMTEntity {
   @Column(columnDefinition = "组织机构ID")
   private String zuZhiJGID;

   @Column(columnDefinition = "组织机构名称")
   private String zuZhiJGMC;

   @Column(columnDefinition = "文档ID")
   private String wenDangID;

   @Column(columnDefinition = "文档名称")
   private String wenDangMC;

   @Column(columnDefinition = "类别代码[SC0010]")
   private String leiBieDM;

   @Column(columnDefinition = "类别名称")
   private String leiBieMC;

   @Column(columnDefinition = "版本代码[SC0011]")
   private String banBenDM;

   @Column(columnDefinition = "版本名称")
   private String banBenMC;

   @Column(columnDefinition = "标准ID")
   private String biaoZhunID;

   @Column(columnDefinition = "标准名称")
   private String biaoZhunMC;

   @Column(columnDefinition = "OID")
   private String oID;

   @Column(columnDefinition = "描述")
   private String miaoShu;

   @Column(columnDefinition = "顺序号")
   private Integer shunXuHao;

   @Column(columnDefinition = "启用标志")
   private Integer qiYongBZ;
}

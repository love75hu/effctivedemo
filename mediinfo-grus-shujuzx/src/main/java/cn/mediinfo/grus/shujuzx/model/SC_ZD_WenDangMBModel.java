package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_zd_wendangmb")
public class SC_ZD_WenDangMBModel extends StringMTEntity {
   @Column(columnDefinition = "组织机构ID")
   private String zuZhiJGID;

   @Column(columnDefinition = "组织机构名称")
   private String zuZhiJGMC;

   @Column(columnDefinition = "文档ID")
   private String wenDangID;

   @Column(columnDefinition = "文档名称")
   private String wenDangMC;

   @Column(columnDefinition = "模板内容")
   private String muBanNR;
}

package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="sc_rz_fangwengxwd")
public class SC_RZ_FangWenGXWDModel extends StringMTEntity {
   @Column(columnDefinition = "组织机构ID")
   private String zuZhiJGID;
   @Column(columnDefinition = "组织机构名称")
   private String zuZhiJGMC;
   @Column(columnDefinition = "访问人ID")
   private String fangWenRID;
   @Column(columnDefinition = "访问人姓名")
   private String fangWenRXM;
   @Column(columnDefinition = "访问时间")
   private Date fangWenSJ;
   @Column(columnDefinition = "访问机构ID")
   private String fangWenJGID;
   @Column(columnDefinition = "访问机构名称")
   private String fangWenJGMC;
   @Column(columnDefinition = "访问系统ID")
   private String fangWenXTID;
   @Column(columnDefinition = "访问系统名称")
   private String fangWenXTMC;
   @Column(columnDefinition = "文档记录ID")
   private String wenDangJLID;
   @Column(columnDefinition = "文档ID")
   private String wenDangID;
   @Column(columnDefinition = "文档名称")
   private String wenDangMC;
   @Column(columnDefinition = "病人ID")
   private String bingRenID;
   @Column(columnDefinition = "姓名")
   private String xingMing;
}

package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="br_da_kuozhanxx")
public class BR_DA_KuoZhanXXModel extends StringMTEntity {
   @Column(columnDefinition = "病人ID")
   private String bingRenID;
   @Column(columnDefinition = "项目代码")
   private String xiangMuDM;
   @Column(columnDefinition = "项目名称")
   private String xiangMuMC;
   @Column(columnDefinition = "项目值代码")
   private String xiangMuZDM;
   @Column(columnDefinition = "项目值名称")
   private String xiangMuZMC;

   private String zuZhiJGID;

   private String zuZhiJGMC;

}

package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_sc_shoucangjxx")
public class SC_SC_ShouCangJXXModel extends StringMTEntity {
   @Column(columnDefinition = "用户ID")
   private String yongHuID;
   @Column(columnDefinition = "用户姓名")
   private String yongHuXM;
   @Column(columnDefinition = "收藏夹名称")
   private String shouCangJMC;
   @Column(columnDefinition = "顺序号")
   private Integer shunXuHao;
   @Column(columnDefinition = "备注")
   private String beiZhu;

   private String zuZhiJGID;

   private String zuZhiJGMC;

}

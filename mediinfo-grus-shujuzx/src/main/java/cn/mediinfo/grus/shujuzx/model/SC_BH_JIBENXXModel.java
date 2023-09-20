package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_bh_jibenxx")
public class SC_BH_JIBENXXModel extends StringMTEntity {

   private String zuZhiJGID;

   private String zuZhiJGMC;

   private String biHuanLXDM;

   private String biHuanLXMC;

   private String biHuanID;

   private String biHuanMC;

   private Integer shunXuHao;

   private Integer qiYongBZ;

}

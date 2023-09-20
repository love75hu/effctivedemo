package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name="sc_bh_jiediansx")
public class SC_BH_JieDianSXModel extends StringMTEntity {

   private String zuZhiJGID;

   private String zuZhiJGMC;

   private String biHuanID;

   private String biHuanMC;

   private String jieDianID;

   private String jieDianMC;

   private String guanLianJDID;

   private String guanLianJDMC;

   private String yunSuanFDM;

   private String yunSuanFMC;

   private BigDecimal shiXiao;

   private String danWeiDM;

   private String danWeiMC;

}

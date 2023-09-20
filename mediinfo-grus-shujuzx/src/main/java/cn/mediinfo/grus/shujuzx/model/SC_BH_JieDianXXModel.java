package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_bh_jiedianxx")
public class SC_BH_JieDianXXModel extends StringMTEntity {

   private String zuZhiJGID;

   private String zuZhiJGMC;

   private String biHuanID;

   private String biHuanMC;

   private String shiTuID;

   private String shiTuMC;

   private String jieDianID;

   private String jieDianMC;

   private String xianShiMC;

   private Integer yinCangBZ;

   private Integer biXuBZ;

   private Integer bingXingBZ;

   private Integer shunXuHao;

}

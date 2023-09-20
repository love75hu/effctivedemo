package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_bh_shituxx")
public class SC_BH_ShiTuXXModel extends StringMTEntity {

   private String zuZhiJGID;

   private String zuZhiJGMC;

   private String biHuanLXDM;

   private String biHuanLXMC;

   private String shiTuID;

   private String shiTuMC;

   private String shiTuLXDM;

   private String shiTuLXMC;

   private String shuJuLYLXDM;

   private String shuJuLYLXMC;

   private String shuJuLYID;

   private String shuJuLYMC;

   private Integer shunXuHao;

}

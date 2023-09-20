package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_bh_shitujdxx")
public class SC_BH_ShiTuJDXXModel extends StringMTEntity {

   private String zuZhiJGID;

   private String zuZhiJGMC;

   private String biHuanLXDM;

   private String biHuanLXMC;

   private String shiTuID;

   private String shiTuMC;

   private String jieDianID;

   private String jieDianMC;

   private Integer shunXuHao;

   private String shiJianZDBM;

   private String shiJianZDMC;

   private String shiJianDM;

   private String shiJianMC;

   private Integer qiYongBZ;

}

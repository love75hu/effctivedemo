package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="gy_zd_shujustxx")
public class GY_ZD_ShuJuSTXXModel extends StringMTEntity {

   private String zuZhiJGID;

   private String zuZhiJGMC;

   private String shuJuSTID;

   private String shuJuSTMC;

   private String fuLeiID;

   private String fuLeiMC;

   private String guanLianFSDM;

   private String guanLianFSMC;

   private String guanLianGX;

   private String guanLianSQL;

   private String guoLvTJ;

   private Integer shunXuHao;

   private Integer moJiBZ;

}

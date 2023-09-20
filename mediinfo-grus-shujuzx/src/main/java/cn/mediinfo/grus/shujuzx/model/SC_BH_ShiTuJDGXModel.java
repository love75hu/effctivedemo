package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_bh_shitujdgx")
public class SC_BH_ShiTuJDGXModel extends StringMTEntity {

   private String zuZhiJGID;

   private String zuZhiJGMC;

   private String shiTuID;

   private String shiTuMC;

   private String jieDianID;

   private String jieDianMC;

   private String guanLianJDID;

   private String guanLianJDMC;

   private Integer shunXuHao;

}

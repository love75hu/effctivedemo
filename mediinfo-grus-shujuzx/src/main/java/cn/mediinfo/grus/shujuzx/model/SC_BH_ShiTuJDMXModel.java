package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_bh_shitujdmx")
public class SC_BH_ShiTuJDMXModel extends StringMTEntity {

   private String zuZhiJGID;

   private String zuZhiJGMC;

   private String jieDianID;

   private String jieDianMC;

   private String shiTuID;

   private String shiTuMC;

   private String ziDuanBM;

   private String ziDuanMC;

   private Integer kongZhiSJBZ;

   private Integer yunXuWKBZ;

   private Integer shunXuHao;

}

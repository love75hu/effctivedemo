package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="gy_zd_shujustmx")
public class GY_ZD_ShuJuSTMXModel extends StringMTEntity {

   private String zuZhiJGID;

   private String zuZhiJGMC;

   private String shuJuSTID;

   private String shuJuSTMC;

   private String shuJuJID;

   private String shuJuJMC;

   private String ziDuanBM;

   private String ziDuanMC;

   private Integer shunXuHao;

}

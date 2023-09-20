package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_bh_diaoyongpz")
public class SC_BH_DiaoYongPZModel extends StringMTEntity {

   private String zuZhiJGID;

   private String zuZhiJGMC;

   private String gongNengDDM;

   private String gongNengDMC;

   private String biHuanID;

   private String biHuanMC;

   private String tiaoJian;

   private Integer shunXuHao;

   private Integer qiYongBZ;

}

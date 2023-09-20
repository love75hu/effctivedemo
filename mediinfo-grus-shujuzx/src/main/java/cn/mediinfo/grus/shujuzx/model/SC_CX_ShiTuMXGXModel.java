package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_cx_shitumxgx")
public class SC_CX_ShiTuMXGXModel extends StringMTEntity {

   private String zuZhiJGID;

   private String zuZhiJGMC;

   private String shiTuID;

   private String shiTuMC;

   private String ziDuanBM;

   private String ziDuanMC;

   private String guanLianZDBM;

   private String guanLianZDMC;

   private Integer sHUXUHAO;

}

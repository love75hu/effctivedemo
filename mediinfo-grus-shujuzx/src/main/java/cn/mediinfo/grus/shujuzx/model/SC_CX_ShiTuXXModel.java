package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_cx_shituxx")
public class SC_CX_ShiTuXXModel extends StringMTEntity {

   private String zuZhiJGID;

   private String zuZhiJGMC;

   private String shiTuID;

   private String shiTuMC;

   private String fuLeiID;

   private String fuLeiMC;

   private Integer moJiBZ;

   private String shuJuLYLXDM;

   private String shuJuLYLXMC;

   private String shuJuLYID;

   private String shuJuLYMC;

   private Integer menZhenSYBZ;

   private Integer zhuYuanSYBZ;

   private Integer jiZhenSYBZ;

   private Integer gongWeiZYBZ;

   private Integer shunXuHao;

}

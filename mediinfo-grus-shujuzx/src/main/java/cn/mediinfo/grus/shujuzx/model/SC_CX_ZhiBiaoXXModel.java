package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_cx_zhibiaoxx")
public class SC_CX_ZhiBiaoXXModel extends StringMTEntity {

   private String zuZhiJGID;

   private String zuZhiJGMC;

   private String zhiBiaoLXDM;

   private String zhiBiaoLXMC;

   private String zhiBiaoFLID;

   private String zhiBiaoFLMC;

   private String zhiBiaoID;

   private String zhiBiaoMC;

   private Integer shunXuHao;

}

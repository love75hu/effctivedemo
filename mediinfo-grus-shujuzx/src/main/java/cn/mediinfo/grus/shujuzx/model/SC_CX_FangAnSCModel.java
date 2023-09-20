package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_cx_fangansc")
public class SC_CX_FangAnSCModel extends StringMTEntity {

   private String zuZhiJGID;

   private String zuZhiJGMC;

   private String fangAnID;

   private String fangAnMC;

   private String zhiBiaoLXDM;

   private String zhiBiaoLXMC;

   private String zhiBiaoFLID;

   private String zhiBiaoFLMC;

   private String zhiBiaoID;

   private String zhiBiaoMC;

   private Integer biXuanBZ;

   private Integer shunXuHao;

}

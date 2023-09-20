package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_cx_fanganxx")
public class SC_CX_FangAnXXModel extends StringMTEntity {

   private String zuZhiJGID;

   private String zuZhiJGMC;

   private String fangAnLXDM;

   private String fangAnLXMC;

   private String fangAnID;

   private String fangAnMC;

   private String guanJianZi;

   private String chaXunTJMS;

}

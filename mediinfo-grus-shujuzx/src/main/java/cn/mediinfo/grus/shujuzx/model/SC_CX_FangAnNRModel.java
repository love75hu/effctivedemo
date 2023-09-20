package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_cx_fangannr")
public class SC_CX_FangAnNRModel extends StringMTEntity {

   private String zuZhiJGID;

   private String zuZhiJGMC;

   private String fangAnID;

   private String fangAnMC;

   private String chaXunTJ;

   private String chaXunSQL;

}

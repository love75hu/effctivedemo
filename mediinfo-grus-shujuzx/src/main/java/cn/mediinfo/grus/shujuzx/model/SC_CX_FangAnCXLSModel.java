package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="sc_cx_fangancxls")
public class SC_CX_FangAnCXLSModel extends StringMTEntity {

   private String zuZhiJGID;

   private String zuZhiJGMC;

   private String chaXunLXDM;

   private String chaXunLXMC;

   private String fangAnID;

   private String fangAnMC;

   private String guanJianZi;

   private String chaXunTJMS;

   private String chaXunSQL;

   private Integer chaXunHS;

   private Date chaXunSJ;

   private String chaXunRID;

   private String chaXunRXM;

}

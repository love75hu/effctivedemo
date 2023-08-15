package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="sc_zd_bihuanjd")
public class SC_ZD_BiHuanJDModel extends StringMTEntity {
   @Column(columnDefinition = "闭环类型代码【DC0004】")
   private String biHuanLXDM;
   @Column(columnDefinition = "闭环类型名称")
   private String biHuanLXMC;
   @Column(columnDefinition = "节点ID")
   private String jieDianID;
   @Column(columnDefinition = "节点名称")
   private String jieDianMC;
   @Column(columnDefinition = "备注")
   private String beiZhu;
   @Column(columnDefinition = "顺序号")
   private Integer shunXuHao;
   @Column(columnDefinition = "门诊使用标志")
   private Integer menZhenSYBZ;
   @Column(columnDefinition = "住院使用标志")
   private Integer zhuYuanSYBZ;
   @Column(columnDefinition = "急诊使用标志")
   private Integer jiZhenSYBZ;
   @Column(columnDefinition = "体检使用标志")
   private Integer tiJianSYBZ;

   private String zuZhiJGID;

   private String zuZhiJGMC;

}

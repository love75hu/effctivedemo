package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_zd_bihuanlc")
public class SC_ZD_BiHuanLCModel extends StringMTEntity {
   @Column(columnDefinition = "闭环类型代码【DC0004】")
   private String biHuanLXDM;
   @Column(columnDefinition = "闭环类型名称")
   private String biHuanLXMC;
   @Column(columnDefinition = "流程ID")
   private String liuChengID;
   @Column(columnDefinition = "流程名称")
   private String liuChengMC;
   @Column(columnDefinition = "流程描述")
   private String liuChengMS;
   @Column(columnDefinition = "门诊使用标志")
   private Integer menZhenSYBZ;
   @Column(columnDefinition = "住院使用标志")
   private Integer zhuYuanSYBZ;
   @Column(columnDefinition = "急诊使用标志")
   private Integer jiZhenSYBZ;
   @Column(columnDefinition = "体检使用标志")
   private Integer tiJianSYBZ;
   @Column(columnDefinition = "顺序号")
   private Integer shunXuHao;
   @Column(columnDefinition = "启用标志")
   private Integer qiYongBZ;

   private String zuZhiJGID;

   private String zuZhiJGMC;

}

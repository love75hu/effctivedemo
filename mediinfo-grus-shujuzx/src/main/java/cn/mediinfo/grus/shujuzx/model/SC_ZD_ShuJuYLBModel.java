package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_zd_shujuylb")
public class SC_ZD_ShuJuYLBModel extends StringMTEntity {
   @Column(columnDefinition = "类别ID")
   private String shuJuYLBID;
   @Column(columnDefinition = "类别名称")
   private String shuJuYLBMC;
   @Column(columnDefinition = "标准类型：1-国际，2-国标，3-行标，4-业务，99-平台字典")
   private String biaoZhunLX;
   @Column(columnDefinition = "标准说明")
   private String biaoZhunSM;
   @Column(columnDefinition = "代码值域存储对象")
   private String zHIYUCC;
   @Column(columnDefinition = "数据元分类代码")
   private String shuJuYFLDM;
   @Column(columnDefinition = "数据元分类名称")
   private String shuJuYFLMC;
   @Column(columnDefinition = "OID")
   private String oID;
   @Column(columnDefinition = "备注")
   private String beiZhu;
   @Column(columnDefinition = "输入码1")
   private String shuRuMa1;
   @Column(columnDefinition = "输入码2")
   private String shuRuMa2;
   @Column(columnDefinition = "输入码3")
   private String shuRuMa3;
   @Column(columnDefinition = "顺序号")
   private Integer shunXuHao;

   private String zuZhiJGID;

   private String zuZhiJGMC;

}

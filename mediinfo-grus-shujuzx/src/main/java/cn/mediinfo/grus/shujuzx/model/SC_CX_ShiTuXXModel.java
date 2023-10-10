package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;


/**
 * 临床检索视图信息
 */
@Data
@Entity
@Table(name="sc_cx_shituxx")
public class SC_CX_ShiTuXXModel extends StringMTEntity {
   /**
    * 组织机构ID
    */
   @Column(columnDefinition = "组织机构ID")
   private String zuZhiJGID;
   /**
    * 组织机构名称
    */
   @Column(columnDefinition = "组织机构名称")
   private String zuZhiJGMC;
   /**
    * 视图ID
    */
   @Column(columnDefinition = "视图ID")
   private String shiTuID;
   /**
    * 视图名称
    */
   @Column(columnDefinition = "视图名称")
   private String shiTuMC;
   /**
    * 父类ID
    */
   @Column(columnDefinition = "父类ID")
   private String fuLeiID;
   /**
    * 父类名称
    */
   @Column(columnDefinition = "父类名称")
   private String fuLeiMC;
   /**
    * 末级标志
    */
   @Column(columnDefinition = "末级标志")
   private Integer moJiBZ;
   /**
    * 数据来源类型代码
    */
   @Column(columnDefinition = "数据来源类型代码")
   private String shuJuLYLXDM;
   /**
    * 数据来源类型名称[1.数据集2.数据视图]
    */
   @Column(columnDefinition = "数据来源类型名称[1.数据集2.数据视图]")
   private String shuJuLYLXMC;
   /**
    * 数据来源ID[1.数据集ID2.数据视图ID]
    */
   @Column(columnDefinition = "数据来源ID[1.数据集ID2.数据视图ID]")
   private String shuJuLYID;
   /**
    * 数据来源名称
    */
   @Column(columnDefinition = "数据来源名称")
   private String shuJuLYMC;
   /**
    * 门诊使用标志
    */
   @Column(columnDefinition = "门诊使用标志")
   private Integer menZhenSYBZ;
   /**
    * 住院使用标志
    */
   @Column(columnDefinition = "住院使用标志")
   private Integer zhuYuanSYBZ;
   /**
    * 急诊使用标志
    */
   @Column(columnDefinition = "急诊使用标志")
   private Integer jiZhenSYBZ;
   /**
    * 公卫使用标志
    */
   @Column(columnDefinition = "公卫使用标志")
   private Integer gongWeiZYBZ;
   /**
    * 顺序号
    */
   @Column(columnDefinition = "顺序号")
   private Integer shunXuHao;

}

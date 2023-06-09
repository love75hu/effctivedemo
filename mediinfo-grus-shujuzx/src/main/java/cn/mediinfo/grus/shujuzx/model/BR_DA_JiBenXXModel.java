package cn.mediinfo.grus.shujuzx.model;
import java.util.*;

import cn.mediinfo.starter.base.multitenancy.entity.StringMTEntity;
import lombok.Data;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name="br_da_jibenxx")
public class BR_DA_JiBenXXModel extends StringMTEntity {
   @Column(columnDefinition = "居民主索引")
   private String mPI;
   @Column(columnDefinition = "姓名")
   private String xingMing;
   @Column(columnDefinition = "姓名全拼")
   private String xingMingQP;
   @Column(columnDefinition = "输入码1(拼音码)")
   private String shuRuMa1;
   @Column(columnDefinition = "输入码2(五笔码)")
   private String shuRuMa2;
   @Column(columnDefinition = "输入码3(自定义码)")
   private String shuRuMa3;
   @Column(columnDefinition = "性别代码[0460]")
   private String xingBieDM;
   @Column(columnDefinition = "性别名称")
   private String xingBieMC;
   @Column(columnDefinition = "出生日期")
   private Date chuShengRQ;
   @Column(columnDefinition = "证件类型代码[0001]")
   private String zhengJianLXDM;
   @Column(columnDefinition = "证件类型名称")
   private String zhengJianLXMC;
   @Column(columnDefinition = "证件号码")
   private String zhengJianHM;
   @Column(columnDefinition = "身份证号")
   private String shenFenZH;
   @Column(columnDefinition = "婚姻代码[0461]")
   private String hunYinDM;
   @Column(columnDefinition = "婚姻名称")
   private String hunYinMC;
   @Column(columnDefinition = "职业代码[0462]")
   private String zhiYeDM;
   @Column(columnDefinition = "职业名称")
   private String zhiYeMC;
   @Column(columnDefinition = "国籍代码[0463]")
   private String guoJiDM;
   @Column(columnDefinition = "国籍名称")
   private String guoJiMC;
   @Column(columnDefinition = "民族代码[0454]")
   private String minZuDM;
   @Column(columnDefinition = "民族名称")
   private String minZuMC;
   @Column(columnDefinition = "出生地信息")
   private String chuShengDXX;
   @Column(columnDefinition = "出生地省份代码(国家统一编码)[0459]\"")
   private String chuShengDSFDM;
   @Column(columnDefinition = "出生地省份名称")
   private String chuShengDSFMC;
   @Column(columnDefinition = "出生地市地区代码(国家统一编码)[0459]")
   private String chuShengDSDQDM;
   @Column(columnDefinition = "出生地市地区名称")
   private String chuShengDSDQMC;
   @Column(columnDefinition = "出生地县区代码(国家统一编码)[0459]")
   private String chuShengDXQDM;
   @Column(columnDefinition = "出生地县区名称")
   private String chuShengDXQMC;
   @Column(columnDefinition = "籍贯地址信息")
   private String jiGuanDZXX;
   @Column(columnDefinition = "籍贯省份代码(国家统一编码)[0459]")
   private String jiGuanSFDM;
   @Column(columnDefinition = "籍贯省份名称")
   private String jiGuanSFMC;
   @Column(columnDefinition = "籍贯市地区代码(国家统一编码)[0459]")
   private String jiGuanSDQDM;
   @Column(columnDefinition = "籍贯市地区名称")
   private String jiGuanSDQMC;
   @Column(columnDefinition = "籍贯县区代码(国家统一编码)[0459]")
   private String jiGuanXQDM;
   @Column(columnDefinition = "籍贯县区名称")
   private String jiGuanXQMC;
   @Column(columnDefinition = "联系电话")
   private String lianXiDH;
   @Column(columnDefinition = "现住址信息")
   private String xianZhuZXX;
   @Column(columnDefinition = "现住址省份代码(国家统一编码)[0459]")
   private String xianZhuZSFDM;
   @Column(columnDefinition = "现住址省份名称")
   private String xianZhuZSFMC;
   @Column(columnDefinition = "现住址市地区代码(国家统一编码)[0459]")
   private String xianZhuZSDQDM;
   @Column(columnDefinition = "现住址市地区名称")
   private String xianZhuZSDQMC;
   @Column(columnDefinition = "现住址县区代码(国家统一编码)[0459]")
   private String xianZhuZXQDM;
   @Column(columnDefinition = "现住址县区名称")
   private String xianZhuZXQMC;
   @Column(columnDefinition = "现住址乡镇代码(标准编码)[0478]")
   private String xianZhuZXZDM;
   @Column(columnDefinition = "现住址乡镇名称")
   private String xianZhuZXZMC;
   @Column(columnDefinition = "现住址村级代码(标准编码)[0479]")
   private String xianZhuZCJDM;
   @Column(columnDefinition = "现住址村级名称")
   private String xianZhuZCJMC;
   @Column(columnDefinition = "现住址其他信息(如：门牌代码等）")
   private String xianZhuZQTXX;
   @Column(columnDefinition = "现住址邮编")
   private String xianZhuZYB;
   @Column(columnDefinition = "户口地址信息")
   private String huKouDZXX;
   @Column(columnDefinition = "户口地址省份代码(国家统一编码)[0459]")
   private String huKouDZSFDM;
   @Column(columnDefinition = "户口地址省份名称")
   private String huKouDZSFMC;
   @Column(columnDefinition = "户口地址市地区代码(国家统一编码)[0459]")
   private String huKouDZSDQDM;
   @Column(columnDefinition = "户口地址市地区名称")
   private String huKouDZSDQMC;
   @Column(columnDefinition = "户口地址县区代码(国家统一编码)[0459]")
   private String huKouDZXQDM;
   @Column(columnDefinition = "户口地址县区名称")
   private String huKouDZXQMC;
   @Column(columnDefinition = "户口地址乡镇代码(标准编码)[0478]")
   private String huKouDZXZDM;
   @Column(columnDefinition = "户口地址乡镇名称")
   private String huKouDZXZMC;
   @Column(columnDefinition = "户口地址村级代码(标准编码)[0479]")
   private String huKouDZCJDM;
   @Column(columnDefinition = "户口地址村级名称")
   private String huKouDZCJMC;
   @Column(columnDefinition = "户口地址其他信息(如：门牌代码等）")
   private String huKouDZQTXX;
   @Column(columnDefinition = "户口邮编")
   private String huKouYB;
   @Column(columnDefinition = "单位名称")
   private String danWeiMC;
   @Column(columnDefinition = "单位地址信息")
   private String danWeiDZXX;
   @Column(columnDefinition = "单位地址省份代码(国家统一编码)[0459]")
   private String danWeiDZSFDM;
   @Column(columnDefinition = "单位地址省份名称")
   private String danWeiDZSFMC;
   @Column(columnDefinition = "单位地址市地区代码(国家统一编码)[0459]")
   private String danWeiDZSDQDM;
   @Column(columnDefinition = "单位地址市地区名称")
   private String danWeiDZSDQMC;
   @Column(columnDefinition = "单位地址县区代码(国家统一编码)[0459]")
   private String danWeiDZXQDM;
   @Column(columnDefinition = "单位地址县区名称")
   private String danWeiDZXQMC;
   @Column(columnDefinition = "单位地址乡镇代码(标准编码)[0478]")
   private String danWeiDZXZDM;
   @Column(columnDefinition = "单位地址乡镇名称")
   private String danWeiDZXZMC;
   @Column(columnDefinition = "单位地址村级代码(标准编码)[0479]")
   private String danWeiDZCJDM;
   @Column(columnDefinition = "单位地址村级名称")
   private String danWeiDZCJMC;
   @Column(columnDefinition = "单位地址其他信息(如：门牌代码等）")
   private String danWeiDZQTXX;
   @Column(columnDefinition = "单位电话")
   private String danWeiDH;
   @Column(columnDefinition = "单位邮编")
   private String danWeiYB;
   @Column(columnDefinition = "联系人姓名")
   private String lianXiRXM;
   @Column(columnDefinition = "与联系人关系代码[0465]")
   private String guanXiDM;
   @Column(columnDefinition = "与联系人关系名称")
   private String guanXiMC;
   @Column(columnDefinition = "联系人电话")
   private String lianXiRDH;
   @Column(columnDefinition = "联系人地址信息")
   private String lianXiRDZXX;
   @Column(columnDefinition = "联系人地址省份代码(国家统一编码)[0459]")
   private String lianXiRDZSFDM;
   @Column(columnDefinition = "联系人地址省份名称")
   private String lianXiRDZSFMC;
   @Column(columnDefinition = "联系人地址市地区代码(国家统一编码)[0459]")
   private String lianXiRDZSDQDM;
   @Column(columnDefinition = "联系人地址市地区名称")
   private String lianXiRDZSDQMC;
   @Column(columnDefinition = "联系人地址县区代码(国家统一编码)[0459]")
   private String lianXiRDZXQDM;
   @Column(columnDefinition = "联系人地址县区名称")
   private String lianXiRDZXQMC;
   @Column(columnDefinition = "联系人地址乡镇代码(标准编码)[0478]")
   private String lianXiRDZXZDM;
   @Column(columnDefinition = "联系人地址乡镇名称")
   private String lianXiRDZXZMC;
   @Column(columnDefinition = "联系人地址村级代码(标准编码)[0479]")
   private String lianXiRDZCJDM;
   @Column(columnDefinition = "联系人地址村级名称")
   private String lianXiRDZCJMC;
   @Column(columnDefinition = "联系人地址其他信息(如：门牌代码等）")
   private String lianXiRDZQTXX;
   @Column(columnDefinition = "建档人ID")
   private String jianDangRID;
   @Column(columnDefinition = "建档人姓名")
   private String jianDangRXM;
   @Column(columnDefinition = "建档时间")
   private Date jianDangSJ;
   @Column(columnDefinition = "合并状态代码0-无合并；1-合并后主数据；2-被合并数据")
   private String heBingZTDM;
   @Column(columnDefinition = "合并状态名称")
   private String heBingZTMC;
   @Column(columnDefinition = "最大相似度(0-100)")
   private Integer zuiDaXSD;
   @Column(columnDefinition = "合并数")
   private Integer heBingShu;
   @Column(columnDefinition = "相似数")
   private Integer xiangSiShu;
   @Column(columnDefinition = "第三方病人ID")
   private String diSanFBRID;

   private String zuZhiJGID;

   private String zuZhiJGMC;

}
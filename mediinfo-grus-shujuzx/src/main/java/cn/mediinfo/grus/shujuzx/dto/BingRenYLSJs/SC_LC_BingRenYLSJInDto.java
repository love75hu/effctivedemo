package cn.mediinfo.grus.shujuzx.dto.BingRenYLSJs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class SC_LC_BingRenYLSJInDto {

    /**
   *组织机构ID
    */
   @Schema(description = "组织机构ID")
   private String zuZhiJGID;

    /**
   *组织机构名称
    */
   @Schema(description = "组织机构名称")
   private String zuZhiJGMC;

    /**
   *居民主索引，病人基本信息表主键
    */
   @Schema(description = "居民主索引，病人基本信息表主键")
   private String bingRenID;

    /**
   *姓名
    */
   @Schema(description = "姓名")
   private String xingMing;

    /**
   *姓名全拼
    */
   @Schema(description = "姓名全拼")
   private String xingMingQP;

    /**
   *输入码1(拼音码)
    */
   @Schema(description = "输入码1(拼音码)")
   private String shuRuMA1;

    /**
   *输入码2(五笔码)
    */
   @Schema(description = "输入码2(五笔码)")
   private String shuRuMA2;

    /**
   *输入码3(自定义码)
    */
   @Schema(description = "输入码3(自定义码)")
   private String shuRuMA3;

    /**
   *性别代码[0460]
    */
   @Schema(description = "性别代码[0460]")
   private String xingBieDM;

    /**
   *性别名称
    */
   @Schema(description = "性别名称")
   private String xingBieMC;

    /**
   *出生日期
    */
   @Schema(description = "出生日期")
   private Date chuShengRQ;

    /**
   *证件类型代码[0001]
    */
   @Schema(description = "证件类型代码[0001]")
   private String zhengJianLXDM;

    /**
   *证件类型名称
    */
   @Schema(description = "证件类型名称")
   private String zhengJianLXMC;

    /**
   *证件号码
    */
   @Schema(description = "证件号码")
   private String zhengJianHM;

    /**
   *门诊次数
    */
   @Schema(description = "门诊次数")
   private Integer menZhenCS;

    /**
   *急诊次数
    */
   @Schema(description = "急诊次数")
   private Integer jiZhenCS;

    /**
   *住院次数
    */
   @Schema(description = "住院次数")
   private Integer zhuYuanCS;

    /**
   *公卫次数
    */
   @Schema(description = "公卫次数")
   private Integer gongWeiCS;

    /**
   *建档人ID
    */
   @Schema(description = "建档人ID")
   private String jianDangRID;

    /**
   *建档人姓名
    */
   @Schema(description = "建档人姓名")
   private String jianDangRXM;

    /**
   *建档时间
    */
   @Schema(description = "建档时间")
   private Date jianDangSJ;

    /**
   *就诊来源[1-门诊，2-急诊，3-住院，4-公卫]
    */
   @Schema(description = "就诊来源[1-门诊，2-急诊，3-住院，4-公卫]")
   private String jiuZhenLYDM;

}

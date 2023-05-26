package cn.mediinfo.grus.shujuzx.dto.ShuJuZXSTs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class SC_ST_SanLiuLSTOutDto {

    /**
   *主键
    */
   @Schema(description = "主键")
   private String id;

    /**
   *姓名
    */
   @Schema(description = "姓名")
   private String xingMing;

    /**
   *病人ID
    */
   @Schema(description = "病人ID")
   private String bingRenID;

    /**
   *索引
    */
   @Schema(description = "索引")
   private String mPI;

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
   *收藏夹明细
    */
   @Schema(description = "收藏夹明细")
   private List<SC_SC_ShouCangJMXListDto> shouCangJiaList;

}

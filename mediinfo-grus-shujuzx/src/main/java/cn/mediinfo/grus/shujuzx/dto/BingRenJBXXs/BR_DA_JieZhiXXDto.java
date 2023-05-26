package cn.mediinfo.grus.shujuzx.dto.BingRenJBXXs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
/**
*病人介质信息
*/
@Data
public class BR_DA_JieZhiXXDto {

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
   *介质号
    */
   @Schema(description = "介质号")
   private String jieZhiHao;

    /**
   *类型代码[1,病历本,2,就诊卡.3,医保卡.4,磁卡.5,条码.6,公费证号.7.电子健康码]
    */
   @Schema(description = "类型代码[1,病历本,2,就诊卡.3,医保卡.4,磁卡.5,条码.6,公费证号.7.电子健康码]")
   private String leiXingDM;

    /**
   *类型名称
    */
   @Schema(description = "类型名称")
   private String leiXingMC;

    /**
   *病人ID
    */
   @Schema(description = "病人ID")
   private String bingRenID;

    /**
   *费用类别ID
    */
   @Schema(description = "费用类别ID")
   private String feiYongLBID;

    /**
   *费用类别名称
    */
   @Schema(description = "费用类别名称")
   private String feiYongLBMC;

    /**
   *费用性质ID
    */
   @Schema(description = "费用性质ID")
   private String feiYongXZID;

    /**
   *费用性质名称
    */
   @Schema(description = "费用性质名称")
   private String feiYongXZMC;

    /**
   *发卡时间
    */
   @Schema(description = "发卡时间")
   private Date faKaSJ;

    /**
   *发卡人ID
    */
   @Schema(description = "发卡人ID")
   private String faKaRID;

    /**
   *发卡人姓名
    */
   @Schema(description = "发卡人姓名")
   private String faKaRXM;

    /**
   *介质状态[0,正常,1,挂失.2,作废.3,退卡][7011]
    */
   @Schema(description = "介质状态[0,正常,1,挂失.2,作废.3,退卡][7011]")
   private String jieZhiZT;

    /**
   *Id
    */
   @Schema(description = "Id")
   private String id;

}

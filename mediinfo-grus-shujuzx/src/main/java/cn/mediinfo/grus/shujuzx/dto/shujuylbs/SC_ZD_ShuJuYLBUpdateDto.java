package cn.mediinfo.grus.shujuzx.dto.shujuylbs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
public class SC_ZD_ShuJuYLBUpdateDto {

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
   *类别ID
    */
   @Schema(description = "类别ID")
   private String shuJuYLBID;

    /**
   *类别名称
    */
   @Schema(description = "类别名称")
   private String shuJuYLBMC;

    /**
   *标准类型：1-国际，2-国标，3-行标，4-业务，99-平台字典
    */
   @Schema(description = "标准类型：1-国际，2-国标，3-行标，4-业务，99-平台字典")
   private String biaoZhunLX;

    /**
   *标准说明
    */
   @Schema(description = "标准说明")
   private String biaoZhunSM;

    /**
   *代码值域存储对象
    */
   @Schema(description = "代码值域存储对象")
   private String zhiYuCC;

    /**
   *数据元分类代码
    */
   @Schema(description = "数据元分类代码")
   private String shuJuYFLDM;

    /**
   *数据元分类名称
    */
   @Schema(description = "数据元分类名称")
   private String shuJuYFLMC;

    /**
   *OID
    */
   @Schema(description = "OID")
   private String oID;

    /**
   *备注
    */
   @Schema(description = "备注")
   private String beiZhu;

    /**
   *输入码1
    */
   @Schema(description = "输入码1")
   private String shuRuMa1;

    /**
   *输入码2
    */
   @Schema(description = "输入码2")
   private String shuRuMa2;

    /**
   *输入码3
    */
   @Schema(description = "输入码3")
   private String shuRuMa3;

    /**
   *顺序号
    */
   @Schema(description = "顺序号")
   private Integer shunXuHao;

    /**
   *Id
    */
   @Schema(description = "Id")
   private String id;

}

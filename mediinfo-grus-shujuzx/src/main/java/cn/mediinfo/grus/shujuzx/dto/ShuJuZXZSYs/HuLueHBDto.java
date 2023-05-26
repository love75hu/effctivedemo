package cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
/**
*保存合并提交信息
*/
@Data
public class HuLueHBDto {

    /**
   *主索引病人ID
    */
   @Schema(description = "主索引病人ID")
   private String zhuSuoYBRID;

    /**
   *相似索引病人ID
    */
   @Schema(description = "相似索引病人ID")
   private String xiangSiSYBRID;

    /**
   *取消合并理由
    */
   @Schema(description = "取消合并理由")
   private String quXiaoHBLY;

}

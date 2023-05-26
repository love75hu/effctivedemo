package cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
/**
*主索引详情
*/
@Data
public class ZhuSuoYHBListDto {

   private List<BR_DA_JiBenXXDto> bingRenXXList;

   private List<JieZhiXXDto> guanLianYXXList;

}

package cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
/**
*主索引详情
*/
@Data
public class ZhuSuoYXQDto {

   private List<BR_DA_JiBenXXByZSYXQDto> bingRenXXList;

   private List<JieZhiXXDto> guanLianYXXList;

}

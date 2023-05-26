package cn.mediinfo.grus.shujuzx.remotedto.GongYong;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class GetCanShuQueryRso {

   private String canshumc;

   private String morenzhi;

   private String zuZhiJGID;

}

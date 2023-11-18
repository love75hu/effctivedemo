package cn.mediinfo.grus.shujuzx.dto.wendangscs;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class WenDangSC_JZXXDto {
    /**
     *就诊业务Id
     */
    @Schema(description = "就诊业务Id")
    private String jiuZhenYWID;

    /**
     *就诊日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Schema(description = "就诊日期")
    private Date jiuZhenRQ;


    /**
     *就诊业务类型
     */
    @Schema(description = "就诊业务类型")
    private String jiuZhenYWLX;

    /**
     *就诊业务类型名称
     */
    @Schema(description = "就诊业务类型名称")
    private String jiuZhenYWLXMC;


    /**
     *就诊科室名称
     */
    @Schema(description = "就诊科室名称")
    private String jiuZhenKSMC;

    /**
     *就诊机构名称
     */
    @Schema(description = "就诊机构名称")
    private String jiuZhenJGMC;

}

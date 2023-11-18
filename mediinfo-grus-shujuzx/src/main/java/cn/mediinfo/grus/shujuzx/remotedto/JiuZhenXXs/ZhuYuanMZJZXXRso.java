package cn.mediinfo.grus.shujuzx.remotedto.JiuZhenXXs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class ZhuYuanMZJZXXRso {

    private String id;
    /**
     *组织机构ID
     */
    @Column(columnDefinition = "组织机构ID")
    private String zuZhiJGID;

    /**
     *组织机构名称
     */
    @Column(columnDefinition = "组织机构名称")
    private String zuZhiJGMC;

    /**
     *就诊科室ID
     */
    @Schema(description = "就诊科室ID")
    private String jiuZhenKSID;

    /**
     *就诊科室名称
     */
    @Schema(description = "就诊科室名称")
    private String jiuZhenKSMC;

    /**
     *就诊时间
     */
    @Schema(description = "就诊时间")
    private String jiuZhenJS;

    @Schema(description = "就诊业务类型")
    private String jiuZhenYWLX;

}

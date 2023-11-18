package cn.mediinfo.grus.shujuzx.dto.bihuanlcs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BingLiXQXXDto {
    /**
    * 组织机构id
    */
    private String zhuZhiJGID;
    /**
     * 组织机构名称
     */
    private String zhuZhiJGMC;
    /**
     * 科室id
     */
    private String keShiID;
    /**
     * 科室名称
     */
    private String keShiMC;
    /**
     * 就诊时间
     */
    private String jiuZhenJS;
    /**
     * 就诊业务类型
     */
    private String jiuZhenYWLX;
    /**
     * 病历记录text
     */
    private List<JiuluTextDto> jiuluTextDtoList;
}

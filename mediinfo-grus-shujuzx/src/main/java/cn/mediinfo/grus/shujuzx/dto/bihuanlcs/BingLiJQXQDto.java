package cn.mediinfo.grus.shujuzx.dto.bihuanlcs;

import lombok.Data;

import java.util.List;


@Data
public class BingLiJQXQDto {
    /**
     * 组织机构id
     */
    private String zhuZhiJGID;
    /**
     * 就诊业务类型
     */
    private String jiuZhenYWLX;
    /**
     * 就诊id
     */
    private String jiuZhenId;
    /**
     * 病历textid
     */
    List<String> bingliJLTEXTIDList;

}

package cn.mediinfo.grus.shujuzx.dto.bihuanlcs;

import lombok.Data;

import java.util.List;

@Data
public class BingLiXQDto {
    /**
     * 病人id
     */
    private String bingRenID;
    /**
     * 关键字
     */
    private String guanJianZi;
    /**
     * 病历记录文本dto
     */
    List<BingLiJQXQDto> bingLiJQXQList;

}

package cn.mediinfo.grus.shujuzx.dto.bihuanzs;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 闭环执行Dto
 */
@Data
public class ShiTuZXJGDto {
    List<Map<String, Object>> shiTuZXJG;

    /**
     * 数据来源ID[1.数据集ID2.数据视图ID]
     */
    private String shuJuLYID;
    /**
     * 数据来源类型代码
     */
    private String shuJuLYLXDM;
}

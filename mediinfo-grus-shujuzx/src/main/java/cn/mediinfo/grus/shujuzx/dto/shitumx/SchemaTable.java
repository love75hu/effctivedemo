package cn.mediinfo.grus.shujuzx.dto.shitumx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * schema+table
 */
@Getter
@Setter
@ToString
public class SchemaTable {

    /**
     * 表名
     */
    private String biaoMing;

    /**
     * schema
     */
    private String moShi;

    /**
     * 数据来源类型代码
     */
    @Schema(description = "数据来源类型代码")
    private String shuJuLYLXDM;
    /**
     * 数据来源ID[1.数据集ID2.数据视图ID]
     */
    @Schema(description = "数据来源ID[1.数据集ID2.数据视图ID]")
    private String shuJuLYID;
    /**
     * 视图ID
     */
    @Schema(description = "视图ID")
    private String shiTuID;
    /**
     * 视图名称
     */
    @Schema(description = "视图名称")
    private String shiTuMC;

    /**
     * 字段
     */
    private List<ShuJuJMXZDDto> shuJuJMXZDDtos;
}

package cn.mediinfo.grus.shujuzx.dto.shitumx;

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
     * 字段
     */
    private List<ShuJuJMXZDDto> shuJuJMXZDDtos;
}

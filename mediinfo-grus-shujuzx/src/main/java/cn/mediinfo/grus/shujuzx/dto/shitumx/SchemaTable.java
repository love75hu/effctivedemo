package cn.mediinfo.grus.shujuzx.dto.shitumx;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
}

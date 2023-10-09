package cn.mediinfo.grus.shujuzx.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 字段数据类型
 */
@AllArgsConstructor
@Getter
public enum ShuJuZLXDMEnum {

    NUMBER("1", "数字"),
    STRING("2", "字符串"),
    TIME("5", "时间"),
    DATE("6", "日期"),
    DATETIME("7", "日期时间");


    private final String type;

    private final String desc;
}

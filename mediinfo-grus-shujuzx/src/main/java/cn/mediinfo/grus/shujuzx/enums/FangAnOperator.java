package cn.mediinfo.grus.shujuzx.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 字段数据类型
 */
@AllArgsConstructor
@Getter
public enum FangAnOperator {
    CONTAIN("LIKE","包含"),
    NOTCONTAIN("NOT LIKE","不包含"),
    IN_FANGAN("ZiFangAn","在某方案件")
    ;

    public static boolean isContain(String operator){
        return CONTAIN.getType().equalsIgnoreCase(operator) || NOTCONTAIN.getType().equalsIgnoreCase(operator);
    }

    private final String type;

    private final String desc;
}

package cn.mediinfo.grus.shujuzx.sql.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * sql关系型二元操作符
 */
@Getter
@AllArgsConstructor
public enum SQLBinaryOperator {


    /**
     * 关系操作符
     */
    EQUALITY("=", "等于"),
    LIKE("LIKE", "包含"),
    NOTLIKE("NOT LIKE", "不包含"),
    GREATERTHAN(">", "大于"),
    GREATERTHANOREQUAL(">=", "大于等于"),
    LESSTHAN("<", "小于"),
    LESSTHANOREQUAL("<=", "小于等于"),
    NOTEQUAL("<>", "不等于"),
    IN("IN", ""),
    NOTIN("NOT IN", ""),
    ISNULL("IS NULL", "空值"),
    ISNOTNULL("IS NOT NULL", "空值"),


    /**
     * 逻辑操作符
     */
    AND("AND", "并且"),
    OR("OR", "或者"),
    PARENTHESES("()", "括号");

    /**
     * 操作符
     */
    private final String symbol;
    /**
     * 描述
     */
    private final String desc;


    public boolean isRelational() {
        return switch (this) {
            case EQUALITY, LIKE, NOTLIKE, GREATERTHAN, GREATERTHANOREQUAL, LESSTHAN, LESSTHANOREQUAL, NOTEQUAL, IN, NOTIN, ISNULL, ISNOTNULL ->
                    true;
            default -> false;
        };
    }
}

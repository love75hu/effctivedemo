package cn.mediinfo.grus.shujuzx.sql.ast;

import cn.mediinfo.grus.shujuzx.sql.enums.SQLBinaryOperator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SQLQueryObject {

    /**
     * 字段名
     */
    private String fieldName;

    /**
     * 关系操作操作符
     */
    private SQLBinaryOperator operator;

    /**
     * 值
     */
    private String val;

    /**
     * sql表达式
     */
    private String text;

    public SQLQueryObject(SQLBinaryOperator operator) {
        this.fieldName = null;
        this.operator = operator;
        this.val = null;
    }

    public SQLQueryObject(String fieldName, SQLBinaryOperator operator, String val) {
        this.fieldName = fieldName;
        this.operator = operator;
        this.val = val;
    }

    public String getText() {
        if (!this.operator.isRelational()) {
            return this.operator.getSymbol();
        }
        if (this.operator == SQLBinaryOperator.IN || this.operator == SQLBinaryOperator.NOTIN) {
            return this.fieldName + " " + this.operator.getSymbol() + " (" + this.val + " )";
        } else if (this.operator == SQLBinaryOperator.LIKE || this.operator == SQLBinaryOperator.NOTLIKE) {
            return this.fieldName + " " + this.operator.getSymbol() + " %" + this.val + " %";
        } else {
            return this.fieldName + " " + this.operator.getSymbol() + " " + this.val + " ";
        }
    }


}

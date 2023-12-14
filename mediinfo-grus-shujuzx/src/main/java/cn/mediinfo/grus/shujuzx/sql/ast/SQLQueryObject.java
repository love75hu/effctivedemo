package cn.mediinfo.grus.shujuzx.sql.ast;

import cn.mediinfo.grus.shujuzx.sql.enums.SQLBinaryOperator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@ToString
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

    /**
     * 子sql
     */
    private String subSql;

    public SQLQueryObject() {
    }

    public SQLQueryObject(SQLBinaryOperator operator) {
        this.fieldName = null;
        this.operator = operator;
        this.val = null;
    }

    public SQLQueryObject(String text) {
        this.fieldName = null;
        this.operator = null;
        this.val = null;
        this.text = text;
    }

    public SQLQueryObject(String fieldName, SQLBinaryOperator operator, String val) {
        this.fieldName = fieldName;
        this.operator = operator;
        this.val = val;
    }

    public String getText() {
        if (this.operator == null) {
            return this.text;
        }
        if (!this.operator.isRelational()) {
            return this.operator.getSymbol();
        }
        String subSqlStr="";
        if (this.operator == SQLBinaryOperator.IN || this.operator == SQLBinaryOperator.NOTIN) {
            subSqlStr = this.fieldName + " " + this.operator.getSymbol() + " (" + this.val + " )";
        } else if (this.operator == SQLBinaryOperator.LIKE || this.operator == SQLBinaryOperator.NOTLIKE) {
            subSqlStr = this.fieldName + " " + this.operator.getSymbol() + " '%" + this.val + "%'";
        } else if (this.operator == SQLBinaryOperator.ISNULL || this.operator == SQLBinaryOperator.ISNOTNULL) {
            subSqlStr = this.fieldName + " " + this.operator.getSymbol();
        } else {
            subSqlStr = this.fieldName + " " + this.operator.getSymbol() + " " + this.val + " ";
        }
        if (StringUtils.isNoneBlank(this.subSql)) {
            subSqlStr = "(" + subSql + subSqlStr + ")";
        }

        return subSqlStr;
    }


}

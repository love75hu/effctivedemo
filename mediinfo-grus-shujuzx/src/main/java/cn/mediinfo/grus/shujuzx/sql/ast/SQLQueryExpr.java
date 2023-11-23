package cn.mediinfo.grus.shujuzx.sql.ast;

import cn.mediinfo.cyan.msf.core.util.StringUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;


/**
 * sql查询表达式
 */
@Getter
@Setter
@ToString
public class SQLQueryExpr {

    private SQLQueryNode root;

    public SQLQueryExpr() {
    }

    public SQLQueryExpr(SQLQueryNode root) {
        this.root = root;
    }

    /**
     * 构建sql
     *
     * @param node SQLQueryNode
     * @return sql语句
     */
    public String buildSQL(SQLQueryNode node) {
        if (node == null) {
            return "";
        }
        String right = buildSQL(node.getRight());
        String left = buildSQL(node.getLeft());
        String text = Optional.ofNullable(node.getValue()).orElse(new SQLQueryObject()).getText();
        if (StringUtil.isBlank(text)) {
            return "";
        }
        right = (right.isEmpty() ? "" : " " + right);
        return switch (text) {
            case "SELECT", "FROM", "WHERE" -> text + " " + left + right + " ";
            case "AND", "OR" -> (left.isEmpty() ? "" : left + " ") + text + right;
            case "()" -> "(" + left + ")";
            default -> text;
        };
    }

}

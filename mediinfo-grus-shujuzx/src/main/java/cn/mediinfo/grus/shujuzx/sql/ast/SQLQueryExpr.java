package cn.mediinfo.grus.shujuzx.sql.ast;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * sql查询表达式
 */
@Getter
@Setter
@ToString
public class SQLQueryExpr {

    private SQLQueryNode root;

    public SQLQueryExpr(){}

    public SQLQueryExpr(SQLQueryNode root){
        this.root = root;
    }

    /**
     * 构建sql
     * @param node SQLQueryNode
     * @return sql语句
     */
    public String buildSQL(SQLQueryNode node) {
        if (node == null) {
            return "";
        }
        String right = buildSQL(node.getRight());
        String left = buildSQL(node.getLeft());

        return switch (node.getValue().getText()) {
            case "SELECT", "FROM", "WHERE" -> node.getValue().getText() + " " + left + (right.isEmpty() ? "" : " " + right) + " ";
            case "AND", "OR" ->
                    (left.isEmpty() ? "" : left + " ") + node.getValue().getText() + (right.isEmpty() ? "" : " " + right);
            case "()" -> "(" + left + ")";
            default -> node.getValue().getText();
        };
    }

}

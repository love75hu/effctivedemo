package cn.mediinfo.grus.shujuzx.sql.ast;

import cn.mediinfo.cyan.msf.core.util.JsonUtil;



public class SQLQueryExpr {

    private SQLQueryNode root;


    public SQLQueryExpr(String rootValue) {
        this.root = new SQLQueryNode(rootValue);
    }

    public SQLQueryNode getRoot() {
        return root;
    }

    public String buildSQL(SQLQueryNode node) {
        if (node == null) {
            return "";
        }
        String left = buildSQL(node.getLeft());
        String right = buildSQL(node.getRight());
        return switch (node.getValue()) {
            case "SELECT", "FROM", "WHERE" -> node.getValue() + " " + left + (right.isEmpty() ? "" : " " + right) + " ";
            case "AND", "OR" ->
                    (left.isEmpty() ? "" : left + " ") + node.getValue() + (right.isEmpty() ? "" : " " + right);
            case "()" -> "(" + left + ")";
            default -> node.getValue();
        };
    }

    public static void main(String[] args) {

        SQLQueryExpr tree = new SQLQueryExpr("SELECT");
        tree.root.setLeft(new SQLQueryNode("id,username,ismale"));
        tree.root.setRight(new SQLQueryNode("FROM"));
        tree.root.getRight().setLeft(new SQLQueryNode("userinfo"));
        tree.root.getRight().setRight(new SQLQueryNode("WHERE"));

        SQLQueryNode where = tree.root.getRight().getRight();

        where.setLeft(new SQLQueryNode("AND"));
        where.getLeft().setLeft(new SQLQueryNode("age > 20"));
        where.getLeft().setRight(new SQLQueryNode("AND"));

        SQLQueryNode age = where.getLeft().getRight();

        age.setLeft(new SQLQueryNode("level > 5"));
        age.setRight(new SQLQueryNode("AND"));

        SQLQueryNode level = age.getRight();

        level.setLeft(new SQLQueryNode("deleted = 0"));
        level.setRight(new SQLQueryNode("AND"));

        SQLQueryNode parentheses = level.getRight();
        parentheses.setLeft(new SQLQueryNode("()"));
        parentheses.setRight(new SQLQueryNode("1=1"));
        parentheses.getLeft().setLeft(new SQLQueryNode("OR"));

        SQLQueryNode or = parentheses.getLeft().getLeft();

        or.setLeft(new SQLQueryNode("age > 5"));
        or.setRight(new SQLQueryNode("level < 3"));


        String sql = tree.buildSQL(tree.root);
        System.out.println(sql);
        System.out.println(JsonUtil.getBeanToJson(tree.root));
    }
}

package cn.mediinfo.grus.shujuzx.sql.ast;

import cn.mediinfo.cyan.msf.core.util.JsonUtil;



public class SQLQueryExprExample {

    private SQLQueryNodeExample root;


    public SQLQueryExprExample(String rootValue) {
        this.root = new SQLQueryNodeExample(rootValue);
    }

    public SQLQueryNodeExample getRoot() {
        return root;
    }

    public String buildSQL(SQLQueryNodeExample node) {
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

        SQLQueryExprExample tree = new SQLQueryExprExample("SELECT");
        tree.root.setLeft(new SQLQueryNodeExample("id,username,ismale"));
        tree.root.setRight(new SQLQueryNodeExample("FROM"));
        tree.root.getRight().setLeft(new SQLQueryNodeExample("userinfo"));
        tree.root.getRight().setRight(new SQLQueryNodeExample("WHERE"));

        SQLQueryNodeExample where = tree.root.getRight().getRight();

        where.setLeft(new SQLQueryNodeExample("AND"));
        where.getLeft().setLeft(new SQLQueryNodeExample("age > 20"));
        where.getLeft().setRight(new SQLQueryNodeExample("AND"));

        SQLQueryNodeExample age = where.getLeft().getRight();

        age.setLeft(new SQLQueryNodeExample("level > 5"));
        age.setRight(new SQLQueryNodeExample("AND"));

        SQLQueryNodeExample level = age.getRight();

        level.setLeft(new SQLQueryNodeExample("deleted = 0"));
        level.setRight(new SQLQueryNodeExample("AND"));

        SQLQueryNodeExample parentheses = level.getRight();
        parentheses.setLeft(new SQLQueryNodeExample("()"));
        parentheses.setRight(new SQLQueryNodeExample("1=1"));
        parentheses.getLeft().setLeft(new SQLQueryNodeExample("OR"));

        SQLQueryNodeExample or = parentheses.getLeft().getLeft();

        or.setLeft(new SQLQueryNodeExample("age > 5"));
        or.setRight(new SQLQueryNodeExample("level < 3"));


        String sql = tree.buildSQL(tree.root);
        System.out.println(sql);
        System.out.println(JsonUtil.getBeanToJson(tree.root));
    }
}

package cn.mediinfo.grus.shujuzx.sql.ast;


public class SQLQueryNode {

   private String value;
   private SQLQueryNode left;
   private SQLQueryNode right;

    public SQLQueryNode(String value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SQLQueryNode getLeft() {
        return left;
    }

    public void setLeft(SQLQueryNode left) {
        this.left = left;
    }

    public SQLQueryNode getRight() {
        return right;
    }

    public void setRight(SQLQueryNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "SQLQueryNode{" +
                "value='" + value + '\'' +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}

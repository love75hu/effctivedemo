package cn.mediinfo.grus.shujuzx.sql.ast;


public class SQLQueryNodeExample {

   private String value;
   private SQLQueryNodeExample left;
   private SQLQueryNodeExample right;

    public SQLQueryNodeExample(String value) {
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

    public SQLQueryNodeExample getLeft() {
        return left;
    }

    public void setLeft(SQLQueryNodeExample left) {
        this.left = left;
    }

    public SQLQueryNodeExample getRight() {
        return right;
    }

    public void setRight(SQLQueryNodeExample right) {
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

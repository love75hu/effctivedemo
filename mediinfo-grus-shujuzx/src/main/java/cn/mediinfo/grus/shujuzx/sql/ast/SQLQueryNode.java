package cn.mediinfo.grus.shujuzx.sql.ast;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SQLQueryNode {

    /**
     * 表达式节点
     */
    private SQLQueryObject value;
    /**
     * 左节点
     */
    private SQLQueryNode left;
    /**
     * 右节点
     */
    private SQLQueryNode right;

    public SQLQueryNode(){}

    public SQLQueryNode(SQLQueryObject a) {
        this.value = a;
        this.left = null;
        this.right = null;
    }


}

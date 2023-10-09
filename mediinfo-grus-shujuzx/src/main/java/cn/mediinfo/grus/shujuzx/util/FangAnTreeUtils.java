package cn.mediinfo.grus.shujuzx.util;

import cn.mediinfo.grus.shujuzx.common.fangan.condition.FangAnCondition;
import cn.mediinfo.grus.shujuzx.common.fangan.condition.FangAnTreeNode;
import cn.mediinfo.grus.shujuzx.enums.NodeTypeEnum;

import java.util.List;

/**
 * FangAnTree工具类
 */
public final class FangAnTreeUtils {

    private FangAnTreeUtils(){}

    /**
     * 获取所有条件节点
     * @param root FangAnTree
     * @param conditionList  条件节点
     */
    public static void getConditionList(FangAnTreeNode root, List<FangAnCondition> conditionList)  {
        if (root == null) {
            return;
        }
        if (NodeTypeEnum.CONDITION_NODE.getType().equals(root.getNodeType())) {
            conditionList.add(root.getCondition());
        }

        getConditionList(root.getLeft(), conditionList);
        getConditionList(root.getRight(), conditionList);
    }
}

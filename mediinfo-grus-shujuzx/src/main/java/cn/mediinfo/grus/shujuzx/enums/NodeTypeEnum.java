package cn.mediinfo.grus.shujuzx.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NodeTypeEnum {

    RELATION_NODE(1, "关系节点"),
    CONDITION_NODE(2, "条件节点");


    private final Integer type;

    private final String msg;
}

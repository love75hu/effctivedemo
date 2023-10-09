package cn.mediinfo.grus.shujuzx.dto.shitumx;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 表
 */
@Getter
@Setter
@ToString
public class TableDTO {


    /**
     * 关联的表
     */
    private List<SchemaTable> schemaTableList;

    /**
     * 表关联关系
     */
    private List<TableRelationCondition> tableRelationConditionList;

    /**
     * 表过滤条件
     */
    private List<SqlFilterCondition> filterConditionList;

}

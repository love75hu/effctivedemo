package cn.mediinfo.grus.shujuzx.dto.shitumx;

import io.swagger.v3.oas.annotations.media.Schema;
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
    private String tableRelationConditionList;

    /**
     * 表过滤条件
     */
    private String filterConditionList;

    /**
     * 关联方式代码[0.默认不关联 1.SQL关联2.字段关联]
     */
    @Schema(description = "关联方式代码")
    private String guanLianFSDM;
}

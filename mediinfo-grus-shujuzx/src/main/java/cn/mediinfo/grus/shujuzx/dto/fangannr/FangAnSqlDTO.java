package cn.mediinfo.grus.shujuzx.dto.fangannr;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *  方案 sql+输出字段
 */
@Getter
@Setter
@ToString
public class FangAnSqlDTO {

    /**
     * 方案ID
     */
    private String fangAnId;

    /**
     * sql
     */
    private String sql;

    /**
     * 字段编码
     */
    private String ziDuanBM;
}

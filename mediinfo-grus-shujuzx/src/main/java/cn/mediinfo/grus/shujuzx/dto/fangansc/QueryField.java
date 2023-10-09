package cn.mediinfo.grus.shujuzx.dto.fangansc;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "数据元输出项")
public class QueryField {


    /**
     * 字段编码
     */
    private String ziDuanBM;

    /**
     * 字段名称
     */
    private String ziDuanMC;

    /**
     * 表名
     */
    private String biaoMing;

    /**
     * schema
     */
    private String moShi;
}

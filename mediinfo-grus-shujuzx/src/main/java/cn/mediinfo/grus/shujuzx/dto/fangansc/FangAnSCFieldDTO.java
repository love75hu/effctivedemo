package cn.mediinfo.grus.shujuzx.dto.fangansc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 方案输出字段
 */
@Getter
@Setter
@ToString
public class FangAnSCFieldDTO {
    public FangAnSCFieldDTO(String id,String ziDuanBM){
        this.id=id;
        this.ziDuanBM=ziDuanBM;
    }

    /**
     * id
     */
    private String id;

    /**
     * 字段编码
     */
    private String ziDuanBM;
}

package cn.mediinfo.grus.shujuzx.remotedto.linchuang;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "查询")
public class ChaXunDto {

    public ChaXunDto(String sql){
        this.sql=sql;
    }

    @Schema(description = "SQL语句")
    private String sql;
}

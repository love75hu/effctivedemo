package cn.mediinfo.grus.shujuzx.dto.shitumx;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class ShuJuSTDto {
    /**
     * 视图ID
     */
    @Column(columnDefinition = "视图ID")
    private String shiTuID;
    private List<String> ziDuanBM;

}

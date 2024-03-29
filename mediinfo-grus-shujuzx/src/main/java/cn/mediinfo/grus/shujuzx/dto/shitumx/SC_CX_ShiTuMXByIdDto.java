package cn.mediinfo.grus.shujuzx.dto.shitumx;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class SC_CX_ShiTuMXByIdDto {
    /**
     * 视图ID
     */
    @Column(columnDefinition = "视图ID")
    private String shiTuID;
    /**
     * 视图名称
     */
    @Column(columnDefinition = "视图名称")
    private String shiTuMC;
    /**
     * 字段编码
     */
    @Column(columnDefinition = "字段编码")
    private String ziDuanBM;
    /**
     * 字段名称
     */
    @Column(columnDefinition = "字段名称")
    private String ziDuanMC;
    @Column(columnDefinition = "关联字段信息")
    private List<SC_CX_ShiTuMXGXDto> shiTuMXGXDto;


}

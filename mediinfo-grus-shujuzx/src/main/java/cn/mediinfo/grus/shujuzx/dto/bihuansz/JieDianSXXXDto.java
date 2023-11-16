package cn.mediinfo.grus.shujuzx.dto.bihuansz;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class JieDianSXXXDto {
    @Column(columnDefinition = "节点ID")
    private String jieDianID;
    @Column(columnDefinition = "节点名称")
    private String jieDianMC;
}

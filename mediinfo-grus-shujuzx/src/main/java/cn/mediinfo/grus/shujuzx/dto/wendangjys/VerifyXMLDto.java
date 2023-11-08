package cn.mediinfo.grus.shujuzx.dto.wendangjys;

import io.swagger.v3.oas.models.media.XML;
import lombok.Data;

@Data
public class VerifyXMLDto {
    private String xmlstr;
    private String oID;
}

package cn.mediinfo.grus.shujuzx.dto.bihuansz;

import lombok.Data;

import java.util.List;

@Data
public class KeXuanJDDto {

    private String shiTuID;
    private String shiTuMC;
    private List<KeXuanJDXXDto> keXuanJDXXDtoList;

}

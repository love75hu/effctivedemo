package cn.mediinfo.grus.shujuzx.remotedto.JiuZhenXXs;


import cn.mediinfo.grus.shujuzx.dto.shujuzxzsys.BR_DA_JiBenXXByZSYXQDto;
import cn.mediinfo.grus.shujuzx.dto.shujuzxzsys.JieZhiXXDto;
import cn.mediinfo.grus.shujuzx.model.BR_DA_JiBenXXModel;
import lombok.Data;

import java.util.List;


@Data
public class BingRenZSYXXRso {

    private BR_DA_JiBenXXModel bingRenXX;

    private List<JieZhiXXDto> jieZhiXXList;

    private List<BR_DA_JiBenXXByZSYXQDto> xiangSiBRList;
}

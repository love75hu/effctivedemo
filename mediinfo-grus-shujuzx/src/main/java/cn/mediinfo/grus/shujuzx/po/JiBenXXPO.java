package cn.mediinfo.grus.shujuzx.po;

import cn.mediinfo.grus.shujuzx.model.QBR_DA_HeBingJLModel;
import cn.mediinfo.grus.shujuzx.model.QBR_DA_JiBenXXModel;
import lombok.Data;

@Data
public class JiBenXXPO {
    private QBR_DA_JiBenXXModel jiBenXXModel;
    private QBR_DA_HeBingJLModel heBingJLModel;

    public JiBenXXPO(QBR_DA_JiBenXXModel jiBenXXModel, QBR_DA_HeBingJLModel heBingJLModel) {
        this.jiBenXXModel = jiBenXXModel;
        this.heBingJLModel = heBingJLModel;
    }
}

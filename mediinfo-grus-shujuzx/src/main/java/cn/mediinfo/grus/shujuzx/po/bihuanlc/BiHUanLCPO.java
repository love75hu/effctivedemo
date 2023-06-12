package cn.mediinfo.grus.shujuzx.po.bihuanlc;

import cn.mediinfo.grus.shujuzx.model.QSC_ZD_BiHuanLCJDModel;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_BiHuanLCModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_BiHuanLCJDModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_BiHuanLCModel;
import lombok.Data;

@Data
public class BiHUanLCPO {
    private QSC_ZD_BiHuanLCModel lcModel;
    private QSC_ZD_BiHuanLCJDModel lcjdModel;

    public BiHUanLCPO(QSC_ZD_BiHuanLCModel lcModel, QSC_ZD_BiHuanLCJDModel lcjdModel) {
        this.lcModel = lcModel;
        this.lcjdModel = lcjdModel;
    }
}

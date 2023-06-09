package cn.mediinfo.grus.shujuzx.po.bihuanlc;

import cn.mediinfo.grus.shujuzx.model.SC_ZD_BiHuanLCJDModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_BiHuanLCModel;
import lombok.Data;

@Data
public class BiHUanPO {
    private SC_ZD_BiHuanLCModel lcModel;
    private SC_ZD_BiHuanLCJDModel lcjdModel;

    public BiHUanPO(SC_ZD_BiHuanLCModel lcModel, SC_ZD_BiHuanLCJDModel lcjdModel) {
        this.lcModel = lcModel;
        this.lcjdModel = lcjdModel;
    }
}

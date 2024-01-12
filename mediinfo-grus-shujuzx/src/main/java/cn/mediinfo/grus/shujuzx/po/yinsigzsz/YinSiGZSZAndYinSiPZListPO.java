package cn.mediinfo.grus.shujuzx.po.yinsigzsz;

import cn.mediinfo.grus.shujuzx.model.SC_ZD_YinSiPZModel;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.GY_YW_YinSiGZXXRso;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class YinSiGZSZAndYinSiPZListPO {

    private GY_YW_YinSiGZXXRso yinSiGZSZModel;
    // private SC_ZD_YinSiGZSZModel yinSiGZSZModel;
    private List<SC_ZD_YinSiPZModel> yinSiPZModels = new ArrayList<>();

    public YinSiGZSZAndYinSiPZListPO(GY_YW_YinSiGZXXRso yinSiGZSZModel, List<SC_ZD_YinSiPZModel> yinSiPZModels) {
        this.yinSiGZSZModel = yinSiGZSZModel;
        this.yinSiPZModels = yinSiPZModels;
    }
}

package cn.mediinfo.grus.shujuzx.manager;

import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnNRModel;

import java.util.List;
import java.util.Set;

public interface FangAnNRManager {


    List<SC_CX_FangAnNRModel> listByFangAnIds(Set<String> fangAnIds);
}

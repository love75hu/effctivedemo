package cn.mediinfo.grus.shujuzx.manager.impl;

import cn.mediinfo.grus.shujuzx.manager.FangAnNRManager;
import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnNRModel;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_FangAnNRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class FangAnNRManagerImpl implements FangAnNRManager {

    @Autowired
    private SC_CX_FangAnNRRepository fangAnNRRepository;

    @Override
    public List<SC_CX_FangAnNRModel> listByFangAnIds(Set<String> fangAnIds) {
        return  fangAnNRRepository.asQuerydsl()
                .where(e->e.fangAnID.in(fangAnIds))
                .fetch();
    }
}

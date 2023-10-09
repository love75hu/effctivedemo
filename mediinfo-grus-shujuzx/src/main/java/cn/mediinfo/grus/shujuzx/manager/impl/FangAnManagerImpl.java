package cn.mediinfo.grus.shujuzx.manager.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.IdUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.core.util.JacksonUtil;
import cn.mediinfo.grus.shujuzx.manager.FangAnManager;
import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnNRModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnSCModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnXXModel;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_FangAnNRRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_FangAnSCRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_FangAnXXRepository;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnXXSaveRequest;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class FangAnManagerImpl  implements FangAnManager {

    @Autowired
    private LyraIdentityService lyraIdentityService;

    @Autowired
    private SC_CX_FangAnXXRepository fangAnXXRepository;

    @Autowired
    private SC_CX_FangAnNRRepository fangAnNRRepository;

    @Autowired
    private SC_CX_FangAnSCRepository fangAnSCRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveFangAn(FangAnXXSaveRequest request, String sql) {

        String fangAnId = IdUtil.objectId();
        String zuZhiJGID = lyraIdentityService.getJiGouID();
        String zuZhiJGMC = lyraIdentityService.getJiGouMC();

        //检索方案信息
        SC_CX_FangAnXXModel fangAnXXModel = new SC_CX_FangAnXXModel();
        fangAnXXModel.setFangAnID(fangAnId);
        fangAnXXModel.setFangAnLXDM(request.getFangAnLXDM());
        fangAnXXModel.setFangAnLXMC(request.getFangAnLXMC());
        fangAnXXModel.setFangAnMC(request.getFangAnMC());
        fangAnXXModel.setGuanJianZi(request.getGuanJianZi());
        fangAnXXModel.setChaXunTJMS(sql);
        fangAnXXModel.setZuZhiJGID(zuZhiJGID);
        fangAnXXModel.setZuZhiJGMC(zuZhiJGMC);

        //检索方案内容
        SC_CX_FangAnNRModel fangAnNRModel = new SC_CX_FangAnNRModel();
        fangAnNRModel.setFangAnID(fangAnId);
        fangAnNRModel.setFangAnMC(request.getFangAnMC());
        fangAnNRModel.setChaXunSQL(sql);
        fangAnNRModel.setChaXunTJ(JacksonUtil.getBeanToJson(request.getRoot()));
        fangAnNRModel.setZuZhiJGID(zuZhiJGID);
        fangAnNRModel.setZuZhiJGMC(zuZhiJGMC);

        //输出
        List<SC_CX_FangAnSCModel> fangAnSCModelList = ListUtil.toList();
        request.getFangAnSCList().forEach(e -> {
            SC_CX_FangAnSCModel fangAnSCModel = BeanUtil.copyProperties(e, SC_CX_FangAnSCModel.class);
            fangAnSCModel.setFangAnID(fangAnId);
            fangAnSCModel.setFangAnMC(request.getFangAnMC());
            fangAnSCModel.setZhiBiaoFLMC("");
            fangAnSCModel.setZuZhiJGID(zuZhiJGID);
            fangAnSCModel.setZuZhiJGMC(zuZhiJGMC);
            fangAnSCModelList.add(fangAnSCModel);
        });

        fangAnXXRepository.save(fangAnXXModel);
        fangAnNRRepository.save(fangAnNRModel);
        fangAnSCRepository.saveAll(fangAnSCModelList);

        return fangAnId;
    }
}

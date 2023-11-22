package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.dto.fangansc.FangAnSCDTO;
import cn.mediinfo.grus.shujuzx.dto.fangansc.FangAnSCFieldDTO;
import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnSCModel;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_FangAnSCRepository;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnSC;
import cn.mediinfo.grus.shujuzx.service.FangAnSCService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 方案输出项
 */
@Service
public class FangAnSCServiceImpl implements FangAnSCService {
    private final SC_CX_FangAnSCRepository fangAnSCRepository;

    public FangAnSCServiceImpl(SC_CX_FangAnSCRepository fangAnSCRepository) {
        this.fangAnSCRepository = fangAnSCRepository;
    }

    @Override
    public FangAnSCDTO getAllFangAnSC(List<FangAnSC> fangAnSCList) {
        return null;
    }

    /**
     *查询方案输出字段id
     *
     * @param fangAnSCIds 方案输出id
     * @return List 方案输出字段
     */
    @Override
    public List<FangAnSCFieldDTO> listFangAnSCFieldByIds(Set<String> fangAnSCIds) {
        List<SC_CX_FangAnSCModel> fangAnSCList=fangAnSCRepository.findByIdIn(fangAnSCIds);
        return fangAnSCList.stream().filter(p->"1".equals(p.getZhiBiaoLXDM())).map(p->new FangAnSCFieldDTO(p.getId(),p.getZhiBiaoID())).collect(Collectors.toList());
    }
}

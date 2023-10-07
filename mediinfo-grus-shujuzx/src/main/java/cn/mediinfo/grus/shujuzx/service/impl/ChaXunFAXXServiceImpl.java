package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.core.util.CollectorUtil;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.grus.shujuzx.dto.chaxunfaxx.FangAnCXLSDto;
import cn.mediinfo.grus.shujuzx.dto.chaxunfaxx.FangAnXXDto;
import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnCXLSModel;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_FangAnCXLSRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_FangAnNRRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_FangAnXXRepository;
import cn.mediinfo.grus.shujuzx.service.ChaXunFAXXService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ChaXunFAXXServiceImpl implements ChaXunFAXXService {
    private final SC_CX_FangAnXXRepository fangAnXXRepository;
    private final SC_CX_FangAnNRRepository fangAnNRRepository;
    private final SC_CX_FangAnCXLSRepository fangAnCXLSRepository;

    public ChaXunFAXXServiceImpl(SC_CX_FangAnXXRepository fangAnXXRepository,
                                 SC_CX_FangAnNRRepository fangAnNRRepository,
                                 SC_CX_FangAnCXLSRepository fangAnCXLSRepository) {
        this.fangAnXXRepository = fangAnXXRepository;
        this.fangAnNRRepository = fangAnNRRepository;
        this.fangAnCXLSRepository = fangAnCXLSRepository;
    }
    @Override
    public List<FangAnXXDto> getChaXunFAXXSelect(String likeQuery, String fangAnLXDM, Integer pageIndex, Integer pageSize){
        return fangAnXXRepository.getFangAnXX(likeQuery,fangAnLXDM,pageIndex,pageSize);
    }
    @Override
    public List<FangAnXXDto> getFangAnXXList(String likeQuery, Integer pageIndex, Integer pageSize){
        return fangAnXXRepository.getFangAnXX(likeQuery,"",pageIndex,pageSize);
    }
    @Override
    public List<FangAnCXLSDto> getFangAnCXLSList(Integer pageIndex, Integer pageSize){
        return BeanUtil.copyListProperties(fangAnCXLSRepository.findAll()
                        .stream().sorted(Comparator.comparing(SC_CX_FangAnCXLSModel::getChuangJianSJ,Comparator.reverseOrder()))
                .collect(CollectorUtil.page(pageIndex,pageSize)),
                FangAnCXLSDto::new,(model,dto)-> {
                    if(model.getChaXunLXDM().equals("2")){
                        dto.setBiaoTiMC(StringUtil.concat("高级查询",model.getGuanJianZi()));
                    }
                    else {
                        dto.setBiaoTiMC(StringUtil.concat(model.getFangAnMC(),model.getGuanJianZi()));
                    }
                    dto.setId(model.getId());
                    dto.setFangAnID(model.getFangAnID());
                    dto.setChaXunTJMS(model.getChaXunTJMS());
                });
    }

    @Override
    public Boolean deleteFangAnCXLS(String id){
        fangAnCXLSRepository.deleteById(id);
        return true;
    }

    @Override
    public Boolean deleteChaXunFA(String id){
        var fangAnQuery = fangAnXXRepository.findById(id);
        if(fangAnQuery.isPresent()) {
            var fangAnXX = fangAnQuery.get();
            fangAnXXRepository.deleteById(id);
            fangAnNRRepository.asDeleteDsl().where(p -> p.zuZhiJGID.eq(fangAnXX.getZuZhiJGID()).and(p.fangAnID.eq(fangAnXX.getFangAnID()))).execute();
        }
        return true;
    }
}

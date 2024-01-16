package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.dto.renwugls.SC_RW_ZhiXingRZCreateDto;
import cn.mediinfo.grus.shujuzx.repository.SC_RW_JiBenXXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_RW_TongYongPZRepository;
import cn.mediinfo.grus.shujuzx.service.RenWuDDService;
import cn.mediinfo.grus.shujuzx.service.RenWuGLService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RenWuDDServiceImpl implements RenWuDDService {
    private final SC_RW_JiBenXXRepository jiBenXXRepository;
    private final SC_RW_TongYongPZRepository tongYongPZRepository;
    private final RenWuGLService renWuGLService;
    public RenWuDDServiceImpl(SC_RW_JiBenXXRepository jiBenXXRepository,SC_RW_TongYongPZRepository tongYongPZRepository,RenWuGLService renWuGLService){
        this.jiBenXXRepository=jiBenXXRepository;
        this.tongYongPZRepository=tongYongPZRepository;
        this.renWuGLService=renWuGLService;
    }
    @Override
    public String saveRenWuZX(String renWuMC,String zhiXingFSDM,String zhiXingFSMC){
        var jibenxx=jiBenXXRepository.asQuerydsl().where(t->t.renWuMC.eq(renWuMC).and(t.qiYongBZ.eq(1))).fetchFirst();

        if (jibenxx!=null){
            List<SC_RW_ZhiXingRZCreateDto> createDtoList=new ArrayList<>();
            SC_RW_ZhiXingRZCreateDto createDto=new SC_RW_ZhiXingRZCreateDto();
            createDto.setRenWuID(jibenxx.getRenWuID());
            createDto.setRenWuMC(jibenxx.getRenWuMC());
            createDto.setRuCan(jibenxx.getRenWuCS());
            createDto.setZhiXingfFSDM(zhiXingFSDM);
            createDto.setZhiXingfFSMC(zhiXingFSMC);
            createDtoList.add(createDto);
            renWuGLService.saveRenWuZXList(createDtoList);
        }
        else{
            return "找不到任务";
        }

        return "执行完成";
    }

}

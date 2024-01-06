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
    public Boolean saveRenWuZX(String renWuMC){
        //region
//        var result = jiBenXXRepository.asQuerydsl()
//                .where(t->t.renWuMC.eq(renWuMC).and(t.qiYongBZ.eq(1)))
//                .leftJoin(tongYongPZRepository.asQuerydsl(), (jiBenXX, tongYongPZ) -> jiBenXX.fenLeiDM.eq(tongYongPZ.fenLeiDM), JiBenXXAndTongYongPZPO::new)
//                .select(q -> new Expression[]{
//                                q.jiBenXX().id,
//                                q.jiBenXX().renWuID,
//                                q.jiBenXX().renWuMC,
//                                q.jiBenXX().renWuSM,
//                                q.jiBenXX().fenLeiMC,
//                                q.jiBenXX().zhiXingPLMC,
//                                q.jiBenXX().zhiXingZTMC,
//                                q.jiBenXX().zhiXingZTDM,
//                                q.jiBenXX().beiZhu,
//                                q.jiBenXX().zuiJinZXRZID,
//                                q.jiBenXX().zhiXingHS,
//                                q.jiBenXX().qiYongBZ,
//                                q.jiBenXX().zhiXingKSSJ.as("zuiJinZXSJ"),
//                                q.jiBenXX().renWuDZ,
//                                q.tongYongPZ().fuWuQIP,
//                                q.tongYongPZ().fuWuQDK,
//                                q.tongYongPZ().renWuDZ.as("fuWuQRWDZ")
//                        }
//                        , SC_RW_JiBenXXListDto.class).fetchFirst();
//        if (result!=null){
//            if (!Objects.isNull(result.getFuWuQIP()) && !Objects.equals(result.getFuWuQIP(),"")
//            && !Objects.isNull(result.getFuWuQDK()) && !Objects.equals(result.getFuWuQDK(),"")
//                    && !Objects.isNull(result.getFuWuQRWDZ()) && !Objects.equals(result.getFuWuQRWDZ(),"")) {
//                if (Objects.isNull(result.getRenWuDZ()) || Objects.equals(result.getRenWuDZ(),"")){
//
//                }
//            }
//
//        }
        //endregion
        var jibenxx=jiBenXXRepository.asQuerydsl().where(t->t.renWuMC.eq(renWuMC).and(t.qiYongBZ.eq(1))).fetchFirst();

        if (jibenxx!=null){
            List<SC_RW_ZhiXingRZCreateDto> createDtoList=new ArrayList<>();
            SC_RW_ZhiXingRZCreateDto createDto=new SC_RW_ZhiXingRZCreateDto();
            createDto.setRenWuID(jibenxx.getRenWuID());
            createDto.setRenWuMC(jibenxx.getRenWuMC());
            createDto.setRuCan(jibenxx.getRenWuCS());
            createDtoList.add(createDto);
            renWuGLService.saveRenWuZXList(createDtoList);
        }

        return true;
    }

}

package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.dto.ShuJuZXFWRZ.AddFangWenRZDto;
import cn.mediinfo.grus.shujuzx.model.SC_RZ_FangWenLCSJModel;
import cn.mediinfo.grus.shujuzx.repository.SC_RZ_FangWenLCSJRepository;
import cn.mediinfo.grus.shujuzx.service.ShuJuZXFWRZService;
import cn.mediinfo.starter.base.lyra.service.LyraIdentityService;
import cn.mediinfo.starter.base.util.MapUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/*
 * 360访问日志
 * */
@Service
public class ShuJuZXFWRZServiceImpl implements ShuJuZXFWRZService {

    private final LyraIdentityService lyraIdentityService;
    private final SC_RZ_FangWenLCSJRepository sc_rz_fangWenLCSJRepository;

    public ShuJuZXFWRZServiceImpl(LyraIdentityService lyraIdentityService,
                                  SC_RZ_FangWenLCSJRepository sc_rz_fangWenLCSJRepository) {
        this.lyraIdentityService = lyraIdentityService;
        this.sc_rz_fangWenLCSJRepository = sc_rz_fangWenLCSJRepository;
    }

    /*
     * 添加访问日志
     * */
    @Override
    public Boolean addFangWenRZ(AddFangWenRZDto addFangWenRZDto) {
        var zuZhiJGID = lyraIdentityService.getJiGouID();
        var jiGouMC = lyraIdentityService.getJiGouMC();
        var userID = lyraIdentityService.getYongHuId();
        var userName = lyraIdentityService.getUserName();
        var fangWenRZ = new SC_RZ_FangWenLCSJModel();
        fangWenRZ.setZuZhiJGID(zuZhiJGID);
        fangWenRZ.setZuZhiJGMC(jiGouMC);
        fangWenRZ.setFangWenRID(userID);
        fangWenRZ.setFangWenRXM(userName);
        fangWenRZ.setFangWenSJ(new Date());
        MapUtils.mergeProperties(fangWenRZ, addFangWenRZDto);
        sc_rz_fangWenLCSJRepository.save(fangWenRZ);
        return true;
    }
}

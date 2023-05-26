package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.repository.SC_ZD_BiHuanJDRepository;
import cn.mediinfo.grus.shujuzx.service.BiHuanJDSZService;
import cn.mediinfo.starter.base.lyra.service.LyraIdentityService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class BiHuanJDSZServiceImpl implements BiHuanJDSZService {
    private final SC_ZD_BiHuanJDRepository sc_zd_biHuanJDRepository;
    private final EntityManager entityManager;
    private final LyraIdentityService lyraIdentityService;
    public BiHuanJDSZServiceImpl(SC_ZD_BiHuanJDRepository scZdBiHuanJDRepository,
                                 EntityManager entityManager,
                                 LyraIdentityService lyraIdentityService) {
        this.sc_zd_biHuanJDRepository = scZdBiHuanJDRepository;
        this.entityManager=entityManager;
        this.lyraIdentityService=lyraIdentityService;

    }
    @Override
    public Integer GetBiHuanJDCount(String biHuanLXDM, String likeQuery) {

       var data= sc_zd_biHuanJDRepository.findAll();
        return 1;
    }
}

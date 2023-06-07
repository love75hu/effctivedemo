package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.dto.BiHuanJDSZs.SC_ZD_BiHuanJDCreateDto;
import cn.mediinfo.grus.shujuzx.dto.BiHuanJDSZs.SC_ZD_BiHuanJDDto;
import cn.mediinfo.grus.shujuzx.dto.BiHuanJDSZs.SC_ZD_BiHuanJDUpdateDto;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_BiHuanJDModel;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_BiHuanJDRepository;
import cn.mediinfo.grus.shujuzx.service.BiHuanJDSZService;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.lyra.service.LyraIdentityService;
import cn.mediinfo.starter.base.lyra.service.SequenceService;
import cn.mediinfo.starter.base.util.MapUtils;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BiHuanJDSZServiceImpl implements BiHuanJDSZService {
    private final SC_ZD_BiHuanJDRepository sc_zd_biHuanJDRepository;
    private final EntityManager entityManager;
    private final LyraIdentityService lyraIdentityService;
    private final SequenceService sequenceService;

    public BiHuanJDSZServiceImpl(
            SC_ZD_BiHuanJDRepository scZdBiHuanJDRepository,
            EntityManager entityManager,
            LyraIdentityService lyraIdentityService,
            SequenceService sequenceService) {
        this.sc_zd_biHuanJDRepository = scZdBiHuanJDRepository;
        this.entityManager = entityManager;
        this.lyraIdentityService = lyraIdentityService;
        this.sequenceService = sequenceService;
    }

    @Override
    public Integer GetBiHuanJDCount(String biHuanLXDM, String likeQuery) {

        var data = sc_zd_biHuanJDRepository.findAll();
        return 1;
    }

    /**
     * 新增闭环节点
     *
     * @param createDto
     * @return
     * @throws TongYongYWException
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public SC_ZD_BiHuanJDDto addBiHuanJD(SC_ZD_BiHuanJDCreateDto createDto) throws TongYongYWException {
        var JieDianMC = sc_zd_biHuanJDRepository.existsByBiHuanLXDMAndJieDianMC(createDto.getBiHuanLXDM(), createDto.getJieDianMC());
        if (JieDianMC) {
            throw new TongYongYWException("节点名称已存在!");
        }
        createDto.setJieDianID(sequenceService.getXuHao("SC_ZD_BiHuan_JieDianID", 6));
        var JieDianID = sc_zd_biHuanJDRepository.existsByJieDianID(createDto.getJieDianID());
        if (JieDianID) {
            throw new TongYongYWException("节点ID已存在!");
        }
        var entity = MapUtils.copyProperties(createDto, SC_ZD_BiHuanJDModel::new);
        entity.setZuZhiJGID(ShuJuZXConstant.TONGYONG_JGID);
        entity.setZuZhiJGMC(ShuJuZXConstant.TONGYONG_JGMC);
        sc_zd_biHuanJDRepository.save(entity);
        return MapUtils.copyProperties(entity, SC_ZD_BiHuanJDDto::new);
    }

//    public SC_ZD_BiHuanJDDto updateBiHuanJD(SC_ZD_BiHuanJDUpdateDto updateDto) {
//
//    }

}

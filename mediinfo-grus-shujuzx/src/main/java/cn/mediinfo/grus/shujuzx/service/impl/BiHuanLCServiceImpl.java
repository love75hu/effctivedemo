package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.dto.BiHuanLCs.SC_ZD_BiHuanLCJDDto;
import cn.mediinfo.grus.shujuzx.dto.BiHuanLCs.SC_ZD_BiHuanLCOutDto;
import cn.mediinfo.grus.shujuzx.model.*;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_BiHuanLCJDRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_BiHuanLCRepository;
import cn.mediinfo.grus.shujuzx.service.BiHuanLCService;
import cn.mediinfo.starter.base.util.MapUtils;
import cn.mediinfo.starter.base.util.QueryDSLUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class BiHuanLCServiceImpl implements BiHuanLCService {
    private final SC_ZD_BiHuanLCRepository biHuanLCRepository;
    private final SC_ZD_BiHuanLCJDRepository biHuanLCJDRepository;
    @PersistenceContext
    private final EntityManager entityManager;

    public BiHuanLCServiceImpl(SC_ZD_BiHuanLCRepository biHuanLCRepository, SC_ZD_BiHuanLCJDRepository biHuanLCJDRepository, EntityManager entityManager) {
        this.biHuanLCRepository = biHuanLCRepository;
        this.biHuanLCJDRepository = biHuanLCJDRepository;
        this.entityManager = entityManager;
    }


    /**
     * 根据主键查询一条闭环流程信息
     */
    record BiHUanDto(SC_ZD_BiHuanLCModel lcModel, SC_ZD_BiHuanLCJDModel lcjdModel ){}
    @Override
    public List<SC_ZD_BiHuanLCOutDto> getBiHuanLCByID(String id, String zuZhiJGID) {
        QSC_ZD_BiHuanLCModel biHuanLCModel  = QSC_ZD_BiHuanLCModel.sC_ZD_BiHuanLCModel;
        QSC_ZD_BiHuanLCJDModel biHuanJDModel = QSC_ZD_BiHuanLCJDModel.sC_ZD_BiHuanLCJDModel;
        List<BiHUanDto> biHUanDtos = new JPAQueryFactory(entityManager).select(QueryDSLUtils.record(BiHUanDto.class, biHuanLCModel, biHuanJDModel)).from(biHuanLCModel).where(biHuanLCModel.id.eq(id)).leftJoin(biHuanJDModel).on(biHuanJDModel.zuZhiJGID.eq(zuZhiJGID).and(biHuanLCModel.liuChengID.eq(biHuanJDModel.liuChengID))).fetch();
        //查询出所有的流程节点信息
        var allbhlcjd = biHUanDtos.stream().map(t->t.lcjdModel.getLiuChengID()).distinct().toList();
        List<SC_ZD_BiHuanLCModel> scZdBiHuanLCModels = biHUanDtos.stream().map(t -> t.lcModel).distinct().filter(t -> allbhlcjd.contains(t.getLiuChengID())).sorted(Comparator.comparing(SC_ZD_BiHuanLCModel::getShunXuHao)).toList();
        return  MapUtils.copyListProperties(scZdBiHuanLCModels,SC_ZD_BiHuanLCOutDto::new);
    }

    /**
     * 根据组织机构、闭环类型获取闭环流程信息
     */
    @Override
    public SC_ZD_BiHuanLCOutDto getBiHuanLCForLX(String zuZhiJGID, String biHuanLXDM, Integer menZhenSYBZ, Integer zhuYuanSYBZ, Integer jiZhenSYBZ, Integer tiJianSYBZ) {

        QSC_ZD_BiHuanLCModel biHuanLCModel  = QSC_ZD_BiHuanLCModel.sC_ZD_BiHuanLCModel;
        QSC_ZD_BiHuanLCJDModel biHuanJDModel = QSC_ZD_BiHuanLCJDModel.sC_ZD_BiHuanLCJDModel;
        List<BiHUanDto> biHUanDtos = new JPAQueryFactory(entityManager)
                .select(QueryDSLUtils.record(BiHUanDto.class, biHuanLCModel, biHuanJDModel))
                .from(biHuanLCModel).where(biHuanLCModel.zuZhiJGID.eq(zuZhiJGID).and(biHuanLCModel.biHuanLXDM.eq(biHuanLXDM)))
                .leftJoin(biHuanJDModel).on(biHuanJDModel.zuZhiJGID.eq(zuZhiJGID).and(biHuanLCModel.liuChengID.eq(biHuanJDModel.liuChengID))).fetch();
        //查询出所有的流程节点信息
        var allbhlcjd = biHUanDtos.stream().map(t->t.lcjdModel.getLiuChengID()).distinct().toList();
        SC_ZD_BiHuanLCModel liuChengModel = biHUanDtos.stream().map(t -> t.lcModel).distinct().filter(t -> allbhlcjd.contains(t.getLiuChengID())).sorted(Comparator.comparing(SC_ZD_BiHuanLCModel::getShunXuHao)).findFirst().orElseGet(null);
        return MapUtils.copyProperties(liuChengModel,SC_ZD_BiHuanLCOutDto::new);
    }
}

package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.dto.BiHuanLCs.SC_ZD_BiHuanLCBJInDto;
import cn.mediinfo.grus.shujuzx.dto.BiHuanLCs.SC_ZD_BiHuanLCInDto;
import cn.mediinfo.grus.shujuzx.dto.BiHuanLCs.SC_ZD_BiHuanLCJDDto;
import cn.mediinfo.grus.shujuzx.dto.BiHuanLCs.SC_ZD_BiHuanLCOutDto;
import cn.mediinfo.grus.shujuzx.model.*;
import cn.mediinfo.grus.shujuzx.po.bihuanlc.BiHUanLCPO;
import cn.mediinfo.grus.shujuzx.po.bihuanlc.BiHUanPO;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_BiHuanLCJDRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_BiHuanLCRepository;
import cn.mediinfo.grus.shujuzx.service.BiHuanLCService;
import cn.mediinfo.grus.shujuzx.utils.ExpressionUtils;
import cn.mediinfo.starter.base.exception.MsfException;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.lyra.service.SequenceService;
import cn.mediinfo.starter.base.querydsl.Queryable;
import cn.mediinfo.starter.base.querydsl.QuerydslJoinExpression;
import cn.mediinfo.starter.base.util.CollectorUtil;
import cn.mediinfo.starter.base.util.MapUtils;
import cn.mediinfo.starter.base.util.QueryDSLUtils;
import cn.mediinfo.starter.base.util.StringUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class BiHuanLCServiceImpl implements BiHuanLCService {
    private final SC_ZD_BiHuanLCRepository biHuanLCRepository;
    private final SC_ZD_BiHuanLCJDRepository biHuanLCJDRepository;
    @PersistenceContext
    private final EntityManager entityManager;

    private final SequenceService sequenceService;

    public BiHuanLCServiceImpl(SC_ZD_BiHuanLCRepository biHuanLCRepository, SC_ZD_BiHuanLCJDRepository biHuanLCJDRepository, EntityManager entityManager,SequenceService sequenceService) {
        this.biHuanLCRepository = biHuanLCRepository;
        this.biHuanLCJDRepository = biHuanLCJDRepository;
        this.entityManager = entityManager;
        this.sequenceService = sequenceService;
    }

    /**
     * 根据主键查询一条闭环流程信息
     */
    @Override
    public List<SC_ZD_BiHuanLCOutDto> getBiHuanLCByID(String id, String zuZhiJGID) {
        QSC_ZD_BiHuanLCModel biHuanLCModel  = QSC_ZD_BiHuanLCModel.sC_ZD_BiHuanLCModel;
        QSC_ZD_BiHuanLCJDModel biHuanJDModel = QSC_ZD_BiHuanLCJDModel.sC_ZD_BiHuanLCJDModel;
        List<BiHUanPO> biHUanDtos = new JPAQueryFactory(entityManager)
                .select(QueryDSLUtils.record(BiHUanPO.class, biHuanLCModel, biHuanJDModel))
                .from(biHuanLCModel).where(biHuanLCModel.id.eq(id))
                .leftJoin(biHuanJDModel)
                .on(biHuanJDModel.zuZhiJGID.eq(zuZhiJGID).and(biHuanLCModel.liuChengID.eq(biHuanJDModel.liuChengID))).fetch();
        //查询出所有的流程节点信息
        var allbhlcjd = biHUanDtos.stream().map(t->t.getLcjdModel().getLiuChengID()).distinct().toList();
        List<SC_ZD_BiHuanLCModel> scZdBiHuanLCModels = biHUanDtos.stream().map(BiHUanPO::getLcModel)
                .filter(ExpressionUtils.distinctByKeys(SC_ZD_BiHuanLCModel::getLiuChengID))
                .filter(t -> allbhlcjd.contains(t.getLiuChengID()))
                .sorted(Comparator.comparing(SC_ZD_BiHuanLCModel::getShunXuHao)).toList();
        return  MapUtils.copyListProperties(scZdBiHuanLCModels,SC_ZD_BiHuanLCOutDto::new);
    }

    /**
     * 根据组织机构、闭环类型获取闭环流程信息
     */
    @Override
    public SC_ZD_BiHuanLCOutDto getBiHuanLCForLX(String zuZhiJGID, String biHuanLXDM, Integer menZhenSYBZ, Integer zhuYuanSYBZ, Integer jiZhenSYBZ, Integer tiJianSYBZ) {

        QSC_ZD_BiHuanLCModel biHuanLCModel  = QSC_ZD_BiHuanLCModel.sC_ZD_BiHuanLCModel;
        QSC_ZD_BiHuanLCJDModel biHuanJDModel = QSC_ZD_BiHuanLCJDModel.sC_ZD_BiHuanLCJDModel;
        List<BiHUanPO> biHUanDtos = new JPAQueryFactory(entityManager)
                .select(QueryDSLUtils.record(BiHUanPO.class, biHuanLCModel, biHuanJDModel))
                .from(biHuanLCModel).where(biHuanLCModel.zuZhiJGID.eq(zuZhiJGID).and(biHuanLCModel.biHuanLXDM.eq(biHuanLXDM)))
                .leftJoin(biHuanJDModel).on(biHuanJDModel.zuZhiJGID.eq(zuZhiJGID).and(biHuanLCModel.liuChengID.eq(biHuanJDModel.liuChengID))).fetch();
        //查询出所有的流程节点信息
        var allbhlcjd = biHUanDtos.stream().map(t->t.getLcjdModel().getLiuChengID()).distinct().toList();
        SC_ZD_BiHuanLCModel liuChengModel = biHUanDtos.stream().map(BiHUanPO::getLcModel).filter(ExpressionUtils.distinctByKeys(SC_ZD_BiHuanLCModel::getLiuChengID)).filter(t -> allbhlcjd.contains(t.getLiuChengID())).sorted(Comparator.comparing(SC_ZD_BiHuanLCModel::getShunXuHao)).findFirst().orElseGet(null);
        return MapUtils.copyProperties(liuChengModel,SC_ZD_BiHuanLCOutDto::new);
    }
    /**
     * 新增一个闭环流程
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Integer addBiHuanLC(SC_ZD_BiHuanLCInDto biHuanLCDto) throws MsfException {
      Boolean existBool =  biHuanLCRepository.existsByZuZhiJGIDAndBiHuanLXDMAndLiuChengMC(biHuanLCDto.getZuZhiJGID(),biHuanLCDto.getBiHuanLXDM(),biHuanLCDto.getLiuChengMC());
      if (existBool){
          throw  new TongYongYWException("流程名称已存在,请重新确认! ");
      }
        SC_ZD_BiHuanLCModel biHuanLCModel = new SC_ZD_BiHuanLCModel();
        String liuChengID = sequenceService.getXuHao("SC_ZD_BIHUANLC",6);
        MapUtils.mergeProperties(biHuanLCDto,biHuanLCModel,false,(dto,model)->{
            model.setLiuChengID(liuChengID);
            model.setQiYongBZ(0);
        });
        biHuanLCRepository.save(biHuanLCModel);
        List<SC_ZD_BiHuanLCJDModel> scZdBiHuanLCJDModels = MapUtils.copyListProperties(biHuanLCDto.getBiHuanJDLCList(), SC_ZD_BiHuanLCJDModel::new, (dto, model) -> {
            model.setZuZhiJGID(biHuanLCDto.getZuZhiJGID());
            model.setZuZhiJGMC(biHuanLCDto.getZuZhiJGMC());
            model.setBiHuanLXDM(biHuanLCDto.getBiHuanLXDM());
            model.setBiHuanLXMC(biHuanLCDto.getBiHuanLXMC());
            model.setLiuChengID(liuChengID);
        });
        biHuanLCJDRepository.saveAll(scZdBiHuanLCJDModels);
        return 0;
    }
    /**
     *根据闭环类型代码获取闭环节点列表
     */
    @Override
    public List<SC_ZD_BiHuanLCOutDto> getBiHuanLCList(String zuZhiJGID, String biHuanLXDM, String likeQuery) {
        QSC_ZD_BiHuanLCModel biHuanLCModel = QSC_ZD_BiHuanLCModel.sC_ZD_BiHuanLCModel;
        QSC_ZD_BiHuanLCJDModel biHuanLCJDModel = QSC_ZD_BiHuanLCJDModel.sC_ZD_BiHuanLCJDModel;
//        List<BiHUanPO> biHuanLCXXList = new JPAQueryFactory(entityManager).select(Projections.bean(BiHUanPO.class, biHuanLCModel, biHuanLCJDModel)).from(biHuanLCModel)
//                .where(biHuanLCModel.zuZhiJGID.eq(zuZhiJGID).and(biHuanLCModel.biHuanLXDM.eq(biHuanLXDM))
//                        .and(QueryDSLUtils.whereIfHasText(likeQuery, ()->biHuanLCModel.biHuanLXMC.contains(likeQuery))))
//                .leftJoin(biHuanLCJDModel)
//                .on(biHuanLCJDModel.zuZhiJGID.eq(zuZhiJGID).and(biHuanLCModel.liuChengID.eq(biHuanLCJDModel.liuChengID)))
//                .fetch();
                List<BiHUanPO> biHuanLCXXList = biHuanLCRepository.asQuerydsl().where(c -> c.zuZhiJGID.eq(zuZhiJGID).and(c.biHuanLXDM.eq(biHuanLXDM)))
                .whereIf(StringUtil.hasText(likeQuery), t -> t.biHuanLXMC.contains(likeQuery))
                .leftJoin(biHuanLCJDRepository.asQuerydsl(), (c, d) -> d.zuZhiJGID.eq(zuZhiJGID).and(c.liuChengID.eq(d.liuChengID)), BiHUanLCPO::new)
                .select(c -> new Expression<?>[]{c.getLcModel(), c.getLcjdModel()}, BiHUanPO.class).fetch();
        //查询出所有的流程节点信息
        List<String> allbhlcjd = biHuanLCXXList.stream().map(t->t.getLcjdModel().getLiuChengID()).distinct().toList();
        List<SC_ZD_BiHuanLCModel> scZdBiHuanLCModels = biHuanLCXXList.stream().map(BiHUanPO::getLcModel).filter(ExpressionUtils.distinctByKeys(SC_ZD_BiHuanLCModel::getLiuChengID)).filter(t -> allbhlcjd.contains(t.getLiuChengID())).sorted(Comparator.comparing(SC_ZD_BiHuanLCModel::getShunXuHao)).toList();
        return MapUtils.copyListProperties(scZdBiHuanLCModels,SC_ZD_BiHuanLCOutDto::new);
    }
    /**
     * 启用闭环流程
     */
    @Override
    public Integer qiYongBiHuanLC(String id, String zuZhiJGID) throws MsfException{
        SC_ZD_BiHuanLCModel biHuanLCXX = biHuanLCRepository.findById(id).orElseGet(() -> null);
        if (Objects.isNull(biHuanLCXX)){
            throw  new TongYongYWException("未找到相关信息,请重新确认！");
        }
        Integer biHuanLCModelCount = biHuanLCRepository.getBiHuanLCModelList(zuZhiJGID, biHuanLCXX.getBiHuanLXDM());
        if (biHuanLCModelCount > 0){
            throw new TongYongYWException("相同使用范围内已有其他闭环流程被启用,请重新确认！");
        }
       // biHuanLCXX.setQiYongBZ(1);
        QSC_ZD_BiHuanLCModel model = QSC_ZD_BiHuanLCModel.sC_ZD_BiHuanLCModel;
        Map<Path<?>,Integer> map = new HashMap<>();
        map.put(model.qiYongBZ,1);
        //todo update
        biHuanLCRepository.update(map,model.id.eq(id));
       // biHuanLCRepository.save(biHuanLCXX);
        return 1;
    }
    /**
     * 停用闭环流程
     */
    @Override
    public Integer tingYongBiHuanLC(String id) throws MsfException {
        SC_ZD_BiHuanLCModel biHuanLCXX = biHuanLCRepository.findById(id).orElseGet(() -> null);
        if (Objects.isNull(biHuanLCXX)){
            throw  new TongYongYWException("未找到相关信息,请重新确认！");
        }
       // biHuanLCXX.setQiYongBZ(0);
        QSC_ZD_BiHuanLCModel model = QSC_ZD_BiHuanLCModel.sC_ZD_BiHuanLCModel;
        Map<Path<?>,Integer> map = new HashMap<>();
        map.put(model.qiYongBZ,0);
        //todo update
        biHuanLCRepository.update(map,model.id.eq(id));
        return 1;
    }
    /**
     * 编辑一条闭环流程
     */
    @Override
    public Integer updateBiHuanLC(SC_ZD_BiHuanLCBJInDto biHuanLCBJInDto) throws MsfException{
        Boolean existBool = biHuanLCRepository.existsByZuZhiJGIDAndBiHuanLXDMAndLiuChengMCAndIdIsNot(biHuanLCBJInDto.getZuZhiJGID(), biHuanLCBJInDto.getBiHuanLXDM(), biHuanLCBJInDto.getLiuChengMC(), biHuanLCBJInDto.getId());
        if (existBool){
            throw new TongYongYWException("流程名称已存在,请重新确认！");
        }
        Integer biHuanLCListCount = biHuanLCRepository.getBiHuanLCList(biHuanLCBJInDto.getZuZhiJGID(), biHuanLCBJInDto.getBiHuanLXDM(), biHuanLCBJInDto.getId(), biHuanLCBJInDto.getZhuYuanSYBZ(), biHuanLCBJInDto.getMenZhenSYBZ(), biHuanLCBJInDto.getJiZhenSYBZ(), biHuanLCBJInDto.getTiJianSYBZ());
        if (biHuanLCListCount > 0){
            throw new TongYongYWException("相同使用范围内已有其他闭环流程被启用,请重新确认！");
        }
        SC_ZD_BiHuanLCModel updateModel = biHuanLCRepository.findById(biHuanLCBJInDto.getId()).orElseGet(()->null);
        MapUtils.mergeProperties(biHuanLCBJInDto,updateModel,true);
        if (!CollectionUtils.isEmpty(biHuanLCBJInDto.getAddbiHuanJDLCList())){
            List<SC_ZD_BiHuanLCJDModel> addJDList = MapUtils.copyListProperties(biHuanLCBJInDto.getAddbiHuanJDLCList(), SC_ZD_BiHuanLCJDModel::new, (dto, model) -> {
                model.setZuZhiJGID(updateModel.getZuZhiJGID());
                model.setZuZhiJGMC(updateModel.getZuZhiJGMC());
                model.setBiHuanLXDM(updateModel.getBiHuanLXDM());
                model.setBiHuanLXMC(updateModel.getBiHuanLXMC());
                model.setLiuChengID(updateModel.getLiuChengID());
            });
        biHuanLCJDRepository.saveAll(addJDList);
        }
        if (!CollectionUtils.isEmpty(biHuanLCBJInDto.getUpdatebiHuanJDLCList())){
            List<String> jieDianIDList = biHuanLCBJInDto.getUpdatebiHuanJDLCList().stream().map(p -> p.getId()).toList();
            QSC_ZD_BiHuanLCJDModel biHuanLCJDModel = QSC_ZD_BiHuanLCJDModel.sC_ZD_BiHuanLCJDModel;
            List<SC_ZD_BiHuanLCJDModel> liuChengJDList = new JPAQueryFactory(entityManager).select(biHuanLCJDModel).from(biHuanLCJDModel).where(biHuanLCJDModel.id.in(jieDianIDList)).fetch();
            if (Objects.nonNull(liuChengJDList)){
                liuChengJDList.forEach(item->{
                    SC_ZD_BiHuanLCJDDto scZdBiHuanLCJDDto = biHuanLCBJInDto.getUpdatebiHuanJDLCList().stream().filter(t -> t.getId().equals(item.getId())).findFirst().orElseGet(() -> null);
                    MapUtils.mergeProperties(scZdBiHuanLCJDDto,item);
                });
            biHuanLCJDRepository.saveAll(liuChengJDList);
            }
        }
        if (!CollectionUtils.isEmpty(biHuanLCBJInDto.getDeleteIds())){
            QSC_ZD_BiHuanLCJDModel biHuanLCJDModel = QSC_ZD_BiHuanLCJDModel.sC_ZD_BiHuanLCJDModel;
            biHuanLCJDRepository.softDelete(biHuanLCJDModel.id.in(biHuanLCBJInDto.getDeleteIds()));
        }
        return 0;
    }
}

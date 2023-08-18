package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.util.MapUtils;
import cn.mediinfo.grus.shujuzx.dto.bihuanlcs.*;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_BiHuanLCJDModel;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_BiHuanLCModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_BiHuanLCJDModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_BiHuanLCModel;
import cn.mediinfo.grus.shujuzx.po.bihuanlc.BiHUanLCPO;
import cn.mediinfo.grus.shujuzx.po.bihuanlc.BiHUanPO;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_BiHuanLCJDRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_BiHuanLCRepository;
import cn.mediinfo.grus.shujuzx.service.BiHuanLCService;
import cn.mediinfo.grus.shujuzx.utils.ExpressionUtils;
import cn.mediinfo.cyan.msf.core.exception.MsfException;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.lyra.extension.service.SequenceService;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.orm.util.QueryDSLUtils;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class BiHuanLCServiceImpl implements BiHuanLCService {
    private final SC_ZD_BiHuanLCRepository biHuanLCRepository;
    private final SC_ZD_BiHuanLCJDRepository biHuanLCJDRepository;

    private final SequenceService sequenceService;

    public BiHuanLCServiceImpl(SC_ZD_BiHuanLCRepository biHuanLCRepository, SC_ZD_BiHuanLCJDRepository biHuanLCJDRepository, SequenceService sequenceService) {
        this.biHuanLCRepository = biHuanLCRepository;
        this.biHuanLCJDRepository = biHuanLCJDRepository;
        this.sequenceService = sequenceService;
    }

    /**
     * 根据主键查询一条闭环流程信息
     */
    @Override
    public List<SC_ZD_BiHuanLCOutDto> getBiHuanLCByID(String id, String zuZhiJGID) {
        QSC_ZD_BiHuanLCModel biHuanLCModel = QSC_ZD_BiHuanLCModel.sC_ZD_BiHuanLCModel;
        QSC_ZD_BiHuanLCJDModel biHuanJDModel = QSC_ZD_BiHuanLCJDModel.sC_ZD_BiHuanLCJDModel;
        List<BiHUanPO> biHUanDtos = new JPAQueryFactory(biHuanLCRepository.getEntityManager())
                .select(QueryDSLUtils.record(BiHUanPO.class, biHuanLCModel, biHuanJDModel))
                .from(biHuanLCModel).where(biHuanLCModel.id.eq(id))
                .leftJoin(biHuanJDModel)
                .on(biHuanJDModel.zuZhiJGID.eq(zuZhiJGID).and(biHuanLCModel.liuChengID.eq(biHuanJDModel.liuChengID))).fetch();
        //查询出所有的流程节点信息
        var bhlcjdList = biHUanDtos.stream().map(BiHUanPO::getLcjdModel).distinct().toList();
        List<SC_ZD_BiHuanLCModel> scZdBiHuanLCModels = biHUanDtos.stream()
                .map(BiHUanPO::getLcModel)
                .filter(ExpressionUtils.distinctByKeys(SC_ZD_BiHuanLCModel::getLiuChengID))
                .map(t -> {
                    t.setShunXuHao(Objects.isNull(t.getShunXuHao()) ? 0 : t.getShunXuHao());
                    return t;
                }).sorted(Comparator.comparing(SC_ZD_BiHuanLCModel::getShunXuHao)).toList();
        return MapUtils.copyListProperties(scZdBiHuanLCModels, SC_ZD_BiHuanLCOutDto::new, (model, dto) -> {
            dto.setBiHuanJDLCList(MapUtils.copyListProperties(bhlcjdList.stream().filter(x -> Objects.equals(x.getLiuChengID(), model.getLiuChengID())).sorted(Comparator.comparing(SC_ZD_BiHuanLCJDModel::getShunXuHao)).toList(), SC_ZD_BiHuanLCJDDto::new));
        });
    }

    /**
     * 根据组织机构、闭环类型获取闭环流程信息
     */
    @Override
    public SC_ZD_BiHuanLCOutDto getBiHuanLCForLX(String zuZhiJGID, String biHuanLXDM, Integer menZhenSYBZ, Integer zhuYuanSYBZ, Integer jiZhenSYBZ, Integer tiJianSYBZ) {
        QSC_ZD_BiHuanLCModel biHuanLCModel = QSC_ZD_BiHuanLCModel.sC_ZD_BiHuanLCModel;
        QSC_ZD_BiHuanLCJDModel biHuanJDModel = QSC_ZD_BiHuanLCJDModel.sC_ZD_BiHuanLCJDModel;
        List<BiHUanPO> biHUanDtos = new JPAQueryFactory(biHuanLCRepository.getEntityManager())
                .select(QueryDSLUtils.record(BiHUanPO.class, biHuanLCModel, biHuanJDModel))
                .from(biHuanLCModel).where(biHuanLCModel.zuZhiJGID.eq(zuZhiJGID).and(biHuanLCModel.biHuanLXDM.eq(biHuanLXDM)))
                .leftJoin(biHuanJDModel).on(biHuanJDModel.zuZhiJGID.eq(zuZhiJGID).and(biHuanLCModel.liuChengID.eq(biHuanJDModel.liuChengID))).fetch();
        //查询出所有的流程节点信息
        var allbhlcjd = biHUanDtos.stream().map(BiHUanPO::getLcjdModel).distinct().toList();
        SC_ZD_BiHuanLCModel scZdBiHuanLCModel = biHUanDtos.stream()
                .map(BiHUanPO::getLcModel)
                .filter(ExpressionUtils.distinctByKeys(SC_ZD_BiHuanLCModel::getLiuChengID))
                .map(t -> {
                    t.setShunXuHao(Objects.isNull(t.getShunXuHao()) ? 0 : t.getShunXuHao());
                    return t;
                }).findFirst().orElseGet(()->null);
        return MapUtils.copyProperties(scZdBiHuanLCModel, SC_ZD_BiHuanLCOutDto::new, (model, dto) -> {
            dto.setBiHuanJDLCList(MapUtils.copyListProperties(allbhlcjd.stream().filter(x -> Objects.equals(x.getLiuChengID(), model.getLiuChengID())).sorted(Comparator.comparing(SC_ZD_BiHuanLCJDModel::getShunXuHao)).toList(), SC_ZD_BiHuanLCJDDto::new));
        });

    }

    /**
     * 新增一个闭环流程
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Integer addBiHuanLC(SC_ZD_BiHuanLCInDto biHuanLCDto) throws MsfException {
        Boolean existBool = biHuanLCRepository.existsByZuZhiJGIDAndBiHuanLXDMAndLiuChengMC(biHuanLCDto.getZuZhiJGID(), biHuanLCDto.getBiHuanLXDM(), biHuanLCDto.getLiuChengMC());
        if (existBool) {
            throw new TongYongYWException("流程名称已存在,请重新确认! ");
        }
        SC_ZD_BiHuanLCModel biHuanLCModel = new SC_ZD_BiHuanLCModel();
        String liuChengID = sequenceService.getXuHao("SC_ZD_BIHUANLC", 6);
        MapUtils.mergeProperties(biHuanLCDto, biHuanLCModel, false, (dto, model) -> {
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
     * 根据闭环类型代码获取闭环节点列表
     */
    @Override
    public List<SC_ZD_BiHuanLCOutDto> getBiHuanLCList(String zuZhiJGID, String biHuanLXDM, String likeQuery) {
        List<BiHUanPO> biHuanLCXXList = biHuanLCRepository
                .asQuerydsl()
                .where(c -> c.zuZhiJGID.eq(zuZhiJGID).and(c.biHuanLXDM.eq(biHuanLXDM)))
                .whereIf(StringUtil.hasText(likeQuery), t -> t.biHuanLXMC.contains(likeQuery))
                .leftJoin(biHuanLCJDRepository.asQuerydsl(), (c, d) -> d.zuZhiJGID.eq(zuZhiJGID).and(c.liuChengID.eq(d.liuChengID)),
                        BiHUanLCPO::new)
                .select(c -> new Expression<?>[]{c.getLcModel(), c.getLcjdModel()}, BiHUanPO.class).fetch();
        //查询出所有的流程节点信息
        var allbhlcjd = biHuanLCXXList.stream().map(BiHUanPO::getLcjdModel).distinct().toList();
        List<SC_ZD_BiHuanLCModel> scZdBiHuanLCModels = biHuanLCXXList.stream()
                .map(BiHUanPO::getLcModel)
                .filter(ExpressionUtils.distinctByKeys(SC_ZD_BiHuanLCModel::getLiuChengID))
                .map(t -> {
                    t.setShunXuHao(Objects.isNull(t.getShunXuHao()) ? 0 : t.getShunXuHao());
                    return t;
                }).sorted(Comparator.comparing(SC_ZD_BiHuanLCModel::getShunXuHao)).toList();
        return MapUtils.copyListProperties(scZdBiHuanLCModels, SC_ZD_BiHuanLCOutDto::new, (model, dto) -> {
            dto.setBiHuanJDLCList(MapUtils.copyListProperties(allbhlcjd.stream().filter(x -> Objects.equals(x.getLiuChengID(), model.getLiuChengID())).sorted(Comparator.comparing(SC_ZD_BiHuanLCJDModel::getShunXuHao)).toList(), SC_ZD_BiHuanLCJDDto::new));
        });
    }

    /**
     * 启用闭环流程
     */
    @Override
    public Integer qiYongBiHuanLC(String id, String zuZhiJGID) throws MsfException {
        SC_ZD_BiHuanLCModel biHuanLCXX = biHuanLCRepository.findById(id).orElseGet(() -> null);
        if (Objects.isNull(biHuanLCXX)) {
            throw new TongYongYWException("未找到相关信息,请重新确认！");
        }
        Integer biHuanLCModelCount = biHuanLCRepository.getBiHuanLCModelList(zuZhiJGID, biHuanLCXX.getBiHuanLXDM());
        if (biHuanLCModelCount > 0) {
            throw new TongYongYWException("相同使用范围内已有其他闭环流程被启用,请重新确认！");
        }

        biHuanLCRepository.asUpdateDsl().set(s->s.qiYongBZ,"1").where(x->x.id.eq(id)).execute();

        return 1;
    }

    /**
     * 停用闭环流程
     */
    @Override
    public Integer tingYongBiHuanLC(String id) throws MsfException {
        SC_ZD_BiHuanLCModel biHuanLCXX = biHuanLCRepository.findById(id).orElseGet(() -> null);
        if (Objects.isNull(biHuanLCXX)) {
            throw new TongYongYWException("未找到相关信息,请重新确认！");
        }
        biHuanLCRepository.asUpdateDsl().set(s->s.qiYongBZ,"0").where(x->x.id.eq(id)).execute();
        return 1;
    }

    /**
     * 编辑一条闭环流程
     */
    @Override
    public Integer updateBiHuanLC(SC_ZD_BiHuanLCBJInDto biHuanLCBJInDto) throws MsfException {
        Boolean existBool = biHuanLCRepository.existsByZuZhiJGIDAndBiHuanLXDMAndLiuChengMCAndIdIsNot(biHuanLCBJInDto.getZuZhiJGID(), biHuanLCBJInDto.getBiHuanLXDM(), biHuanLCBJInDto.getLiuChengMC(), biHuanLCBJInDto.getId());
        if (existBool) {
            throw new TongYongYWException("流程名称已存在,请重新确认！");
        }
        Integer biHuanLCListCount = biHuanLCRepository.getBiHuanLCList(biHuanLCBJInDto.getZuZhiJGID(), biHuanLCBJInDto.getBiHuanLXDM(), biHuanLCBJInDto.getId(), biHuanLCBJInDto.getZhuYuanSYBZ(), biHuanLCBJInDto.getMenZhenSYBZ(), biHuanLCBJInDto.getJiZhenSYBZ(), biHuanLCBJInDto.getTiJianSYBZ());
        if (biHuanLCListCount > 0) {
            throw new TongYongYWException("相同使用范围内已有其他闭环流程被启用,请重新确认！");
        }
        SC_ZD_BiHuanLCModel updateModel = biHuanLCRepository.findById(biHuanLCBJInDto.getId()).orElseGet(() -> null);
        MapUtils.mergeProperties(biHuanLCBJInDto, updateModel, true);
        if (!CollectionUtils.isEmpty(biHuanLCBJInDto.getAddbiHuanJDLCList())) {
            List<SC_ZD_BiHuanLCJDModel> addJDList = MapUtils.copyListProperties(biHuanLCBJInDto.getAddbiHuanJDLCList(), SC_ZD_BiHuanLCJDModel::new, (dto, model) -> {
                model.setZuZhiJGID(updateModel.getZuZhiJGID());
                model.setZuZhiJGMC(updateModel.getZuZhiJGMC());
                model.setBiHuanLXDM(updateModel.getBiHuanLXDM());
                model.setBiHuanLXMC(updateModel.getBiHuanLXMC());
                model.setLiuChengID(updateModel.getLiuChengID());
            });
            biHuanLCJDRepository.saveAll(addJDList);
        }
        if (!CollectionUtils.isEmpty(biHuanLCBJInDto.getUpdatebiHuanJDLCList())) {
            List<String> jieDianIDList = biHuanLCBJInDto.getUpdatebiHuanJDLCList().stream().map(SC_ZD_BiHuanLCJDDto::getId).toList();
            QSC_ZD_BiHuanLCJDModel biHuanLCJDModel = QSC_ZD_BiHuanLCJDModel.sC_ZD_BiHuanLCJDModel;
            List<SC_ZD_BiHuanLCJDModel> liuChengJDList = new JPAQueryFactory(biHuanLCJDRepository.getEntityManager()).select(biHuanLCJDModel).from(biHuanLCJDModel).where(biHuanLCJDModel.id.in(jieDianIDList)).fetch();
            if (Objects.nonNull(liuChengJDList)) {
                liuChengJDList.forEach(item -> {
                    SC_ZD_BiHuanLCJDDto scZdBiHuanLCJDDto = biHuanLCBJInDto.getUpdatebiHuanJDLCList().stream().filter(t -> t.getId().equals(item.getId())).findFirst().orElseGet(() -> null);
                    MapUtils.mergeProperties(scZdBiHuanLCJDDto, item);
                });
                biHuanLCJDRepository.saveAll(liuChengJDList);
            }
        }
        if (!CollectionUtils.isEmpty(biHuanLCBJInDto.getDeleteIds())) {
            QSC_ZD_BiHuanLCJDModel biHuanLCJDModel = QSC_ZD_BiHuanLCJDModel.sC_ZD_BiHuanLCJDModel;
            biHuanLCJDRepository.delete(biHuanLCJDModel.id.in(biHuanLCBJInDto.getDeleteIds()));
        }
        return 0;
    }

    /**
     * 更新闭环节点流程顺序号
     */
    @Override
    public Integer updateBiHuanLCJDSXH(List<SC_ZD_BiHuanLCJDSXHDto> jdSxhDtos) {
        List<String> idList = jdSxhDtos.stream().map(SC_ZD_BiHuanLCJDSXHDto::getId).toList();
        List<SC_ZD_BiHuanLCJDModel> biHuanLCJDList = biHuanLCJDRepository.asQuerydsl().whereIn(t -> t.id, idList, 999).select(SC_ZD_BiHuanLCJDModel.class).fetch();
        biHuanLCJDList.forEach(item -> {
            Integer shunXuHao = jdSxhDtos.stream().filter(t -> Objects.equals(t.getId(), item.getId())).map(SC_ZD_BiHuanLCJDSXHDto::getShunXuHao).findFirst().orElseGet(() -> -1);
            item.setShunXuHao(shunXuHao == -1 ? item.getShunXuHao() : null);
        });
        biHuanLCJDRepository.saveAll(biHuanLCJDList);
        return 1;
    }

    /**
     * 更新闭环流程
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Integer updateBiHuanLCList(String zuZhiJGID, String zuZhiJGMC, String biHuanLXDM) {
        List<String> zuZhiJGIDList = List.of("0", zuZhiJGID);
        List<SC_ZD_BiHuanLCModel> biHuanLCList = biHuanLCRepository.asQuerydsl().whereIn(t -> t.zuZhiJGID, zuZhiJGIDList, 999).where(t -> t.biHuanLXDM.eq(biHuanLXDM)).select(SC_ZD_BiHuanLCModel.class).fetch();
        List<SC_ZD_BiHuanLCModel> tongYongList = biHuanLCList.stream().filter(p -> "0".equals(p.getZuZhiJGID())).toList();
        List<String> yiCunZaiList = biHuanLCList.stream().filter(p -> Objects.equals(p.getZuZhiJGID(), zuZhiJGID)).map(SC_ZD_BiHuanLCModel::getLiuChengID).toList();
        List<SC_ZD_BiHuanLCModel> weiCunZaiList = tongYongList.stream().filter(p -> !yiCunZaiList.contains(p.getLiuChengID())).toList();
        if (!CollectionUtils.isEmpty(weiCunZaiList)) {
            List<SC_ZD_BiHuanLCJDModel> addBiHuanLCJDList = new ArrayList<>();
            List<String> biHuanLCIDList = weiCunZaiList.stream().map(SC_ZD_BiHuanLCModel::getLiuChengID).toList();
            List<SC_ZD_BiHuanLCJDModel> biHuanLCJDList = biHuanLCJDRepository.asQuerydsl().where(t -> t.zuZhiJGID.eq("0")).whereIn(p -> p.liuChengID, biHuanLCIDList, 999).select(SC_ZD_BiHuanLCJDModel.class).fetch();
            weiCunZaiList.forEach(item -> {
                item.setId(null);
                item.setZuZhiJGID(zuZhiJGID);
                item.setZuZhiJGMC(zuZhiJGMC);
                var jdList = biHuanLCJDList.stream().filter(x -> Objects.equals(x.getLiuChengID(), item.getLiuChengID()))
                        .map(t -> MapUtils.copyProperties(t, SC_ZD_BiHuanLCJDModel::new, (model, update) -> {
                            update.setId(null);
                            update.setZuZhiJGID(zuZhiJGID);
                            update.setZuZhiJGMC(zuZhiJGMC);
                        })).toList();
                addBiHuanLCJDList.addAll(jdList);
            });
            biHuanLCRepository.saveAll(weiCunZaiList);
            biHuanLCJDRepository.saveAll(addBiHuanLCJDList);
        }
        return 1;
    }

    /**
     * 作废一条闭环流程
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Integer zuoFeiBiHuanLC(String id) throws TongYongYWException {
        SC_ZD_BiHuanLCModel biHuanLCXX = biHuanLCRepository.findById(id).orElseGet(() -> null);
        if (Objects.isNull(biHuanLCXX)) {
            throw new TongYongYWException("未找到该流程信息，请确认！");
        }
        biHuanLCRepository.deleteById(id);
        QSC_ZD_BiHuanLCModel biHuanLCModel = QSC_ZD_BiHuanLCModel.sC_ZD_BiHuanLCModel;
        biHuanLCJDRepository.delete(biHuanLCModel.zuZhiJGID.eq(biHuanLCXX.getZuZhiJGID()).and(biHuanLCModel.liuChengID.eq(biHuanLCXX.getLiuChengID())));
        return 1;
    }
}

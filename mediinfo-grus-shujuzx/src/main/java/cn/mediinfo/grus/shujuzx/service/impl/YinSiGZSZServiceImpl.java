package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.dto.YinSiGZSZs.*;
import cn.mediinfo.grus.shujuzx.model.*;
import cn.mediinfo.grus.shujuzx.po.yinsigzsz.YinSiGZSZAndYinSiPZListPO;
import cn.mediinfo.grus.shujuzx.po.yinsigzsz.YinSiGZSZSJYpPO;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_YinSiGZSZRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_YinSiPZRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_ZhanShiPZRepository;
import cn.mediinfo.grus.shujuzx.service.YinSiGZSZService;
import cn.mediinfo.grus.shujuzx.utils.ExpressionUtils;
import cn.mediinfo.starter.base.exception.MsfException;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.util.*;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import cn.mediinfo.grus.shujuzx.constant.ChaXunMSEnum;
import cn.mediinfo.grus.shujuzx.dto.YinSiGZSZs.SC_ZD_YinSiPZOutDto;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;


/**
 * 隐私规则设置
 */
@Service
public class YinSiGZSZServiceImpl implements YinSiGZSZService {
    private final SC_ZD_YinSiPZRepository yinSiPZRepository;
    private final SC_ZD_YinSiGZSZRepository yinSiGZSZRepository;
    private final SC_ZD_ZhanShiPZRepository zhanShiPZRepository;

    public YinSiGZSZServiceImpl(SC_ZD_YinSiPZRepository yinSiPZRepository,
                                SC_ZD_YinSiGZSZRepository yinSiGZSZRepository,
                                SC_ZD_ZhanShiPZRepository zhanShiPZRepository) {
        this.yinSiPZRepository = yinSiPZRepository;
        this.yinSiGZSZRepository = yinSiGZSZRepository;
        this.zhanShiPZRepository = zhanShiPZRepository;
    }

    /**
     * 新增隐私规则
     */
    @Override
    //@Transactional(rollbackOn = Exception.class)
    public Integer addYinSiGZ(SC_ZD_YinSiGZSZInDto yinSiGZSZInDto) throws MsfException {
        Boolean existBool = yinSiGZSZRepository.existsByShuJuYMC(yinSiGZSZInDto.getShuJuYMC());
        if (existBool) {
            throw new TongYongYWException("数据元名称已存在,请重新确认!");
        }
        SC_ZD_YinSiGZSZModel addModel = new SC_ZD_YinSiGZSZModel();
        MapUtils.mergeProperties(yinSiGZSZInDto, addModel);
        addModel.setZuZhiJGID("0");
        addModel.setZuZhiJGMC("通用");
        yinSiGZSZRepository.save(addModel);
        return 1;
    }

    /**
     * 保存隐私设置
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean saveYinSiSZList(SC_ZD_YinSiPZCreateDto dto) {
        if (dto.getAddList().size() > 0) {
            List<SC_ZD_YinSiPZModel> tongYongYSPZs = yinSiPZRepository.findByZuZhiJGIDAndChaXunMSDM("0", dto.getAddList().get(0).getChaXunMSDM());
            List<SC_ZD_YinSiPZDto> scZdYinSiPZDtos = dto.getAddList().stream().filter(s -> tongYongYSPZs.stream().map(SC_ZD_YinSiPZModel::getShuJuYLM).toList().contains(s.getShuJuYLM())).toList();
            yinSiPZRepository.saveAll(MapUtils.copyListProperties(scZdYinSiPZDtos, SC_ZD_YinSiPZModel::new));
        }
        if (dto.getZuoFeiIds().size() > 0) {
            yinSiPZRepository.softDelete(dto.getZuoFeiIds());
        }
        return true;
    }

    /**
     * 保存展示配置
     */

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean saveZhanShiPZ(SC_ZD_ZhanShiPZCreateDto dto) {
        if (dto.getAddList().size() > 0) {
            zhanShiPZRepository.saveAll(MapUtils.copyListProperties(dto.getAddList(), SC_ZD_ZhanShiPZModel::new, (zhanShiPZDto, model) -> {
                model.setId(null);
                model.setZuZhiJGID(zhanShiPZDto.getZuZhiJGID());
                model.setZuZhiJGMC(zhanShiPZDto.getZuZhiJGMC());
                model.setChaXunMSDM(zhanShiPZDto.getChaXunMSDM());
                model.setChaXunMSMC(zhanShiPZDto.getChaXunMSMC());
            }));
        }
        if (dto.getUpdateList().size() > 0) {
            String zuZhiJGID = dto.getUpdateList().get(0).getZuZhiJGID();
            List<String> idList = dto.getUpdateList().stream().map(SC_ZD_ZhanShiPZDto::getId).toList();
            var zhanShiPZList = zhanShiPZRepository.findByZuZhiJGIDAndIdIn(zuZhiJGID, idList);
            for (var item : zhanShiPZList) {
                var zhanShiPZ = dto.getUpdateList().stream().filter(x -> Objects.equals(x.getId(), item.getId())).findFirst().orElseGet(()->null);
                //if (Objects.nonNull(zhanShiPZ)) {
                    MapUtils.mergeProperties(zhanShiPZ, item, true);
                //}
            }
            zhanShiPZRepository.saveAll(zhanShiPZList);
        }
        if (dto.getZuoFeiIds().size() > 0) {
            QSC_ZD_ZhanShiPZModel zhanShiPZModel = QSC_ZD_ZhanShiPZModel.sC_ZD_ZhanShiPZModel;
            zhanShiPZRepository.softDelete(zhanShiPZModel.id.in(dto.getZuoFeiIds()));
        }
        return true;
    }

    /**
     * 修改隐私规则
     */
    @Override
    public Integer updateYinSiGZ(SC_ZD_YinSiGZSZInDto yinSiGZSZInDto) throws MsfException {
        SC_ZD_YinSiGZSZModel updateModel = yinSiGZSZRepository.findById(yinSiGZSZInDto.getId()).orElseGet(null);
        if (Objects.isNull(updateModel)) {
            throw new TongYongYWException("未找到相关可修改的信息!");
        }
        MapUtils.mergeProperties(yinSiGZSZInDto, updateModel, true);
        yinSiGZSZRepository.save(updateModel);
        return 1;
    }

    /**
     * 作废隐私规则
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Integer zuoFeiYinSiGZ(String id) throws MsfException {
        if (!yinSiGZSZRepository.existsById(id)) {
            throw new TongYongYWException("未找到相关可作废的信息!");
        }
        yinSiGZSZRepository.softDelete(id);
        return 1;
    }

    /**
     * 移除隐私配置
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean zuoFeiYinSiSZ(String id) {
        yinSiPZRepository.softDelete(id);
        return true;
    }

    /**
     * 启用隐私设置
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean qiYongYinSiSZ(String id, Integer qiYongBZ) {
        SC_ZD_YinSiPZModel yinSiPZXX = yinSiPZRepository.findById(id).orElseGet(null);
        if (yinSiPZXX == null) return false;
        yinSiPZXX.setQiYongBZ(qiYongBZ);
        yinSiPZRepository.save(yinSiPZXX);
        return true;
    }

    /**
     * 更新隐私规则
     *
     * @param zuZhiJGID  组织机构id
     * @param zuZhiJGMC  组织机构名称
     * @param chaXunMSDM 查询模式代码
     * @param peiZhiLXDM 配置类型代码
     * @return true
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean updateZhanShiPZ(String zuZhiJGID, String zuZhiJGMC, String chaXunMSDM, String peiZhiLXDM) {
        List<SC_ZD_ZhanShiPZModel> yinSiPZList = zhanShiPZRepository.getZhanShiPZList(zuZhiJGID, chaXunMSDM, peiZhiLXDM);
        //通用数据
        var tongYongSJList = yinSiPZList.stream().filter(x -> ShuJuZXConstant.TONGYONG_JGID.equals(x.getZuZhiJGID())).toList();
        //机构数据
        var jiGouSJList = yinSiPZList.stream().filter(x -> Objects.equals(x.getZuZhiJGID(), zuZhiJGID)).toList();
        //取差集
        List<SC_ZD_ZhanShiPZModel> chaJiList = tongYongSJList.stream().filter(t -> !jiGouSJList.contains(t)).toList();
        chaJiList.forEach(zhanShiPZModel -> {
            zhanShiPZModel.setId(null);
            zhanShiPZModel.setZuZhiJGID(zuZhiJGID);
            zhanShiPZModel.setZuZhiJGMC(zuZhiJGMC);
        });
        zhanShiPZRepository.saveAll(chaJiList);
        return true;
    }

    /**
     * 初始化隐私设置
     *
     * @param zuZhiJGID  组织机构ID
     * @param zuZhiJGMC  组织机构名称
     * @param chaXunMSDM 查询模式代码
     * @return true
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean chuShiHYinSiZS(String zuZhiJGID, String zuZhiJGMC, String chaXunMSDM) {
        //通用数据t zuzhijg zaiqian
        List<SC_ZD_YinSiPZModel> tongYongSJList = yinSiPZRepository.findByZuZhiJGIDAndChaXunMSDM("0",chaXunMSDM);
        //删除机构之前的数据
        yinSiPZRepository.softDelete(yinSiPZRepository.findByZuZhiJGIDAndChaXunMSDM(zuZhiJGID,chaXunMSDM));
        //初始化数据
        tongYongSJList.forEach(item -> {
            item.setZuZhiJGID(zuZhiJGID);
            item.setZuZhiJGMC(zuZhiJGMC);
        });
        yinSiPZRepository.saveAll(tongYongSJList);
        return true;
    }

    /**
     * 初始化隐私规则配置
     *
     * @param zuZhiJGID  组织机构ID
     * @param zuZhiJGMC  组织机构名称
     * @param chaXunMSDM 查询模式代码
     * @return true
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean chuShiHYSGZPZ(String zuZhiJGID, String zuZhiJGMC, String chaXunMSDM) {
        //通用数据 todo  zuzhijg zaiqian
        List<SC_ZD_YinSiPZModel> tongYongSJList = yinSiPZRepository.findByZuZhiJGIDAndChaXunMSDM("0",chaXunMSDM);
        //删除机构之前的数据
        yinSiPZRepository.softDelete(yinSiPZRepository.findByZuZhiJGIDAndChaXunMSDM(zuZhiJGID,chaXunMSDM));
        //初始化数据
        tongYongSJList.forEach(item -> {
            item.setZuZhiJGID(zuZhiJGID);
            item.setZuZhiJGMC(zuZhiJGMC);
        });
        yinSiPZRepository.saveAll(tongYongSJList);
        return true;
    }

    /**
     * 初始化展示设置
     *
     * @param zuZhiJGID  组织机构ID
     * @param zuZhiJGMC  组织机构名称
     * @param chaXunMSDM 查询模式代码
     * @param peiZhiLXDM 配置类型代码
     * @return true
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean chuShiHZhanShiPZ(String zuZhiJGID, String zuZhiJGMC, String chaXunMSDM, String peiZhiLXDM) {
        //通用数据
        var tongYongSJList = zhanShiPZRepository.findByZuZhiJGIDAndChaXunMSDMAndPeiZhiLXDM(ShuJuZXConstant.TONGYONG_JGID, chaXunMSDM, peiZhiLXDM);
        //删除机构之前的数据
        zhanShiPZRepository.softDelete(zhanShiPZRepository.findByZuZhiJGIDAndChaXunMSDM(zuZhiJGID, chaXunMSDM));
        //初始化数据
        tongYongSJList.forEach(item -> {
            item.setId(null);
            item.setZuZhiJGID(zuZhiJGID);
            item.setZuZhiJGMC(zuZhiJGMC);
        });
        zhanShiPZRepository.saveAll(tongYongSJList);
        return true;
    }

    @Override
    public List<SC_ZD_YinSiPZOutDto> getYinSiGZPZList(String chaXunMSDM, String zuZhiJGID) throws TongYongYWException {
        var canXunMSDMList = List.of(chaXunMSDM, ChaXunMSEnum.TONG_YONG_MO_SHI.getValue());
        var list = yinSiPZRepository.findByZuZhiJGIDAndChaXunMSDMInAndQiYongBZ(zuZhiJGID, canXunMSDMList, 1);
        var result = list.stream().filter(o -> Objects.equals(o.getChaXunMSDM(), chaXunMSDM)).map(o -> MapUtils.copyProperties(o, SC_ZD_YinSiPZOutDto::new)).toList();
        if (CollectionUtils.isEmpty(result)) {
            result = list.stream().filter(o -> Objects.equals(o.getChaXunMSDM(), ChaXunMSEnum.TONG_YONG_MO_SHI.getValue())).map(o -> MapUtils.copyProperties(o, SC_ZD_YinSiPZOutDto::new)).toList();
        }
        return result;
    }

    /**
     * 根据主键id修改某个隐私规则
     */
    @Override
    public SC_ZD_YinSiGZSZOutDto getYinSiGZByID(String id) {
        return MapUtils.copyProperties(yinSiGZSZRepository.findById(id).orElseGet(null), SC_ZD_YinSiGZSZOutDto::new);
    }

    /**
     * 获取隐私规则列表条数
     */
    @Override
    public Integer getYinSiGZSZCount(String likeQuery) {
        QSC_ZD_YinSiGZSZModel yinSiGZSZModel = QSC_ZD_YinSiGZSZModel.sC_ZD_YinSiGZSZModel;
        JPAQuery<SC_ZD_YinSiGZSZModel> jpaQuery = new JPAQueryFactory(yinSiGZSZRepository.getEntityManager())
                .select(yinSiGZSZModel).from(yinSiGZSZModel)
                .where(yinSiGZSZModel.zuZhiJGID.eq("0"))
                .where(QueryDSLUtils.whereIfHasText(likeQuery,()->yinSiGZSZModel.shuJuYMC.contains(likeQuery)));
        return jpaQuery.fetch().size();
    }

    /**
     * 获取隐私规则列表
     */
    @Override
    public List<SC_ZD_YinSiGZSZOutDto> getYinSiGZSZList(String likeQuery,Integer pageIndex, Integer pageSize) {
        QSC_ZD_YinSiGZSZModel yinSiGZSZModel = QSC_ZD_YinSiGZSZModel.sC_ZD_YinSiGZSZModel;
        JPAQuery<SC_ZD_YinSiGZSZModel> jpaQuery = new JPAQueryFactory(yinSiGZSZRepository.getEntityManager()).select(yinSiGZSZModel).from(yinSiGZSZModel).where(yinSiGZSZModel.zuZhiJGID.eq("0"))
                .where(QueryDSLUtils.whereIfHasText(likeQuery,()->yinSiGZSZModel.shuJuYMC.contains(likeQuery)));
        List<SC_ZD_YinSiGZSZModel> yinSiGZSZModelList = jpaQuery.orderBy(yinSiGZSZModel.shunXuHao.asc())
                .offset(PageRequestUtil.of(pageIndex, pageSize).getOffset()).limit(pageSize).fetch();
        return MapUtils.copyListProperties(yinSiGZSZModelList, SC_ZD_YinSiGZSZOutDto::new);
    }

    /**
     * 获取隐私规则设置数据元列表
     */
    //todo tichu poceng
    @Override
    public List<SC_ZD_YinSiGZSZOutDto> getYinSiGZSZSJYList(String chaXunMSDM, String zuZhiJGID) {
        List<SC_ZD_YinSiGZSZModel> yinSiGZSZModelList = yinSiGZSZRepository.findByZuZhiJGID(zuZhiJGID);
        List<SC_ZD_YinSiPZModel> yinSiPZModelList = yinSiPZRepository.findByZuZhiJGIDAndChaXunMSDM(zuZhiJGID, chaXunMSDM);
        List<SC_ZD_YinSiGZSZOutDto> scZdYinSiGZSZOutDtos = yinSiGZSZModelList.stream().collect(CollectorUtil.groupJoin(yinSiPZModelList,
                        (guiZeSZ, yinSiPZ) -> Objects.equals(guiZeSZ.getShuJuYLM(), yinSiPZ.getShuJuYLM()), YinSiGZSZAndYinSiPZListPO::new)).stream()
                .flatMap(szpz -> szpz.getYinSiPZModels().stream().map(pz -> new YinSiGZSZSJYpPO(szpz.getYinSiGZSZModel(), pz))).map(t -> {
                    SC_ZD_YinSiGZSZOutDto yinSiGZSZOutDto = new SC_ZD_YinSiGZSZOutDto();
                    yinSiGZSZOutDto.setId(t.getYinSiGZSZModel().getId());
                    yinSiGZSZOutDto.setShuJuYMC(t.getYinSiGZSZModel().getShuJuYMC());
                    yinSiGZSZOutDto.setShuJuYLM(t.getYinSiGZSZModel().getShuJuYLM());
                    yinSiGZSZOutDto.setTuoMinFSDM(t.getYinSiGZSZModel().getTuoMinFSDM());
                    yinSiGZSZOutDto.setTuoMinFSMC(t.getYinSiGZSZModel().getTuoMinFSMC());
                    yinSiGZSZOutDto.setTuoMinGZ(t.getYinSiGZSZModel().getTuoMinGZ());
                    yinSiGZSZOutDto.setTuoMinSM(t.getYinSiGZSZModel().getTuoMinSM());
                    yinSiGZSZOutDto.setShunXuHao(t.getYinSiGZSZModel().getShunXuHao());
                    yinSiGZSZOutDto.setShiFouXZ(StringUtil.hasText(t.getYinSiPZModel().getId()));//是否选中
                    return yinSiGZSZOutDto;
                }).sorted(Comparator.comparing(SC_ZD_YinSiGZSZOutDto::getShunXuHao))
                .filter(ExpressionUtils.distinctByKeys(SC_ZD_YinSiGZSZOutDto::getShunXuHao)).toList();
        return scZdYinSiGZSZOutDtos;
    }

    /**
     * 获取隐私设置列表
     *
     * @param chaXunMSDM 查询模式代码
     * @param zuZhiJGID  组织机构ID
     * @param likeQuery  条件
     * @return 隐私配置集合
     */
    @Override
    public List<SC_ZD_YinSiPZDto> getYinSiSZList(String chaXunMSDM, String zuZhiJGID, String likeQuery) {
        QSC_ZD_YinSiPZModel yinSiPZModel = QSC_ZD_YinSiPZModel.sC_ZD_YinSiPZModel;
        JPAQuery<SC_ZD_YinSiPZModel> jpaQuery = new JPAQueryFactory(yinSiPZRepository.getEntityManager()).select(yinSiPZModel).from(yinSiPZModel)
                .where(yinSiPZModel.zuZhiJGID.eq(zuZhiJGID).and(yinSiPZModel.chaXunMSDM.eq(chaXunMSDM)));
        if (StringUtil.hasText(likeQuery)) {
            jpaQuery.where(yinSiPZModel.shuJuYLM.contains(likeQuery.toUpperCase()).or(yinSiPZModel.shuJuYMC.contains(likeQuery.toUpperCase())));
        }
        return MapUtils.copyListProperties(jpaQuery.orderBy(yinSiPZModel.shunXuHao.asc()).fetch(), SC_ZD_YinSiPZDto::new);
    }

    /**
     * 获取展示配置列表
     * @param zuZhiJGID  组织机构ID
     * @param chaXunMSDM 查询模式代码
     * @param peiZhiLXDM 配置类型代码
     * @return 展示配置DTO集合
     */
    @Override
    public List<SC_ZD_ZhanShiPZDto> getZhanShiPZList(String zuZhiJGID, String chaXunMSDM, String peiZhiLXDM) {
        //获取当前数据列表
        List<SC_ZD_ZhanShiPZModel> zhanShiPZModelList = zhanShiPZRepository.findByZuZhiJGIDAndChaXunMSDMAndPeiZhiLXDM(zuZhiJGID, chaXunMSDM, peiZhiLXDM);
        if (zhanShiPZModelList.size() == 0) { //如果列表数据为空 则向上获取数据
            if (!"0".equals(zuZhiJGID)) {  //非通用机构 向上获取数据
                zhanShiPZModelList = zhanShiPZRepository.findByZuZhiJGIDAndChaXunMSDMAndPeiZhiLXDM("0", chaXunMSDM, peiZhiLXDM);
            } else if (!"1".equals(chaXunMSDM)) { // 非通用查询模式 向通用模式取数据
                zhanShiPZModelList = zhanShiPZRepository.findByZuZhiJGIDAndChaXunMSDMAndPeiZhiLXDM(zuZhiJGID, "1", peiZhiLXDM);
            }
            zhanShiPZModelList.forEach(item->item.setId("0"));
        }
        return zhanShiPZModelList.stream()
                .sorted(Comparator.comparing(SC_ZD_ZhanShiPZModel::getShunXuHao,Comparator.nullsLast(Integer::compareTo)).thenComparing(SC_ZD_ZhanShiPZModel::getId))
                .map(t->MapUtils.copyProperties(t,SC_ZD_ZhanShiPZDto::new)).toList();
    }
}

package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs.*;
import cn.mediinfo.grus.shujuzx.model.BR_ZD_HeBingQZPZModel;
import cn.mediinfo.grus.shujuzx.model.QBR_ZD_HeBingQZPZModel;
import cn.mediinfo.grus.shujuzx.repository.BR_ZD_HeBingQZPZRepository;
import cn.mediinfo.grus.shujuzx.service.ZhuSuoYQZPZService;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.stringgenerator.StringGenerator;
import cn.mediinfo.starter.base.util.MapUtils;
import cn.mediinfo.starter.base.util.QueryDSLUtils;
import cn.mediinfo.starter.base.util.StringUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class ZhuSuoYQZPZServiceImpl implements ZhuSuoYQZPZService {
    private final BR_ZD_HeBingQZPZRepository brZdHeBingQZPZRepository;
    private final StringGenerator stringGenerator;
    @PersistenceContext
    private final EntityManager entityManager;
    public ZhuSuoYQZPZServiceImpl(BR_ZD_HeBingQZPZRepository brZdHeBingQZPZRepository, StringGenerator stringGenerator, EntityManager entityManager) {
        this.brZdHeBingQZPZRepository = brZdHeBingQZPZRepository;
        this.stringGenerator = stringGenerator;
        this.entityManager = entityManager;
    }

    /**
     * 新增一个权重分类
     * @param  dto DTO
     * @return boolean
     */
    @Override
    public Boolean addQuanZhongFL(AddQuanZhongFLDto dto) throws TongYongYWException {
        if(!brZdHeBingQZPZRepository.existsByMoJiBZAndFuLeiMC(0,dto.getFuLeiMC())){
            return  false;
        }
        var addModel = new BR_ZD_HeBingQZPZModel();
        MapUtils.mergeProperties(dto,addModel);
        addModel.setZuZhiJGID(ShuJuZXConstant.TONGYONG_JGID);
        addModel.setZuZhiJGMC(ShuJuZXConstant.TONGYONG_JGMC);
        addModel.setFuLeiID(stringGenerator.Create());
        addModel.setMoJiBZ(0);
        brZdHeBingQZPZRepository.save(addModel);
        return true;
    }

    /**
     * 更新一个权重分类
     * @param  dto DTO
     * @return boolean
     */
    @Override
    public Boolean updateQuanZhongFL(UpdateQuanZhongFLDto dto) throws TongYongYWException {
        if(brZdHeBingQZPZRepository.existsByMoJiBZAndFuLeiMCAndIdNot(0,dto.getFuLeiMC(),dto.getId())){
            return  false;
        }
        var updateModel = brZdHeBingQZPZRepository.findById(dto.getId()).orElse(null);
        if(updateModel == null){
            throw new TongYongYWException("未找到相关的权重分类");
        }
        updateModel.setFuLeiMC(dto.getFuLeiMC());
        updateModel.setShunXuHao(dto.getShunXuHao());
        brZdHeBingQZPZRepository.save(updateModel);
        return true;
    }

    /**
     * 作废权重分类
     * @param id id
     * @return boolean
     */
    @Override
    public Boolean zuoFeiQuanZhongFL(String id) throws TongYongYWException {
        var deleteModel =brZdHeBingQZPZRepository.findById(id).orElse(null);
        if(deleteModel == null){
            throw new TongYongYWException("未找到相关的权重分类");
        }
        brZdHeBingQZPZRepository.softDelete(deleteModel);
        return true;
    }

    /**
     * 保存权重配置
     * @param dto dto
     * @return boolean
     */
    @Transactional(rollbackOn = Exception.class)
    @Override
    public Boolean saveQuanZhongPZ(SaveQuanZhongPZDto dto) throws TongYongYWException {
        //新增
        if(!CollectionUtils.isEmpty(dto.getAddList())){
            var addZhanShiPZList =MapUtils.copyListProperties(dto.getAddList(),BR_ZD_HeBingQZPZModel::new,(o,model)->{
                model.setZuZhiJGID(ShuJuZXConstant.TONGYONG_JGID);
                model.setZuZhiJGMC(ShuJuZXConstant.TONGYONG_JGMC);
                model.setHeBingQZID(stringGenerator.Create());
                model.setMoJiBZ(1);
            });
            brZdHeBingQZPZRepository.saveAll(addZhanShiPZList);
        }
        //更新
        if(!CollectionUtils.isEmpty(dto.getUpdateList())){
            var updateIdList = dto.getUpdateList().stream().map(BR_ZD_HeBingQZPZListDto::getId).toList();
            //获取需要更新的实体
            var zhanShiPZList = brZdHeBingQZPZRepository.findAllById(updateIdList);
            for (var entity :zhanShiPZList){
                dto.getUpdateList().stream().filter(o -> Objects.equals(o.getId(), entity.getId())).findFirst().ifPresent(o -> MapUtils.mergeProperties(o, entity));
            }
            brZdHeBingQZPZRepository.saveAll(zhanShiPZList);
        }
        //作废
        if(!CollectionUtils.isEmpty(dto.getZuoFeiIds())){
            brZdHeBingQZPZRepository.softDelete(dto.getZuoFeiIds());
        }
        return true;
    }

    /**
     * 获取权重配置分类列表
     * @return BR_ZD_HeBingQZPZListDto
     * @throws TongYongYWException 通用异常
     */
    @Override
    public List<BR_ZD_HeBingQZPZListDto> getQuanZhongPZFLList() throws TongYongYWException {
        var models = brZdHeBingQZPZRepository.findByMoJiBZAndZuoFeiBZOrderByShunXuHao(0,0);
        return MapUtils.copyListProperties(models,BR_ZD_HeBingQZPZListDto::new);
    }

    /**
     * 获取权重配置列表(包含分类)
     * @return BR_ZD_HeBingGZQZPZDto
     * @throws TongYongYWException 通用异常
     */
    @Override
    public List<BR_ZD_HeBingGZQZPZDto> getQuanZhongPZList() throws TongYongYWException {
        List<BR_ZD_HeBingGZQZPZDto> result = new ArrayList<>();
        var models =brZdHeBingQZPZRepository.findByZuZhiJGID(ShuJuZXConstant.TONGYONG_JGID);
        // 获取权重分类 末级标志 0 是分类
        List<Map<String, String>> fenLeiList = models.stream()
                .filter(x -> x.getMoJiBZ() == 0)
                .sorted(Comparator.comparingInt(BR_ZD_HeBingQZPZModel::getShunXuHao))
                .map(x -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("FuLeiMC", x.getFuLeiMC());
                    map.put("FuLeiID", x.getFuLeiID());
                    return map;
                })
                .distinct()
                .toList();
        for(var item :fenLeiList){
            List<QuanZhongPZListDto> quanZhongPZList = models.stream()
                    .filter(x -> x.getMoJiBZ() == 1 && Objects.equals(x.getFuLeiID(), item.get("FuLeiID")))
                    .sorted(Comparator.comparingInt(BR_ZD_HeBingQZPZModel::getShunXuHao))
                    .map(x -> MapUtils.copyProperties(x,QuanZhongPZListDto::new))
                    .toList();
            if(CollectionUtils.isEmpty(quanZhongPZList)){
                continue;
            }
            var itemDto = new BR_ZD_HeBingGZQZPZDto();
            itemDto.setFenLeiMC(item.get("FuLeiMC"));
            itemDto.setQuanZhongPZList(quanZhongPZList);
            result.add(itemDto);
        }
        return result;
    }

    /**
     * 根据分类ID获取权重配置列表
     * @return List<BR_ZD_HeBingQZPZListDto>
     * @throws TongYongYWException 通用异常
     */
    @Override
    public List<BR_ZD_HeBingQZPZListDto> getQuanZhongPZListByFLID(String FuLeiID, String LikeQuery) throws TongYongYWException {
        var qModel = QBR_ZD_HeBingQZPZModel.bR_ZD_HeBingQZPZModel;
        var modelList = new JPAQueryFactory(entityManager).select(qModel).from(qModel)
                .where(qModel.fuLeiID.eq(FuLeiID).and(qModel.moJiBZ.eq(1)).and(qModel.zuoFeiBZ.eq(0)))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(LikeQuery),qModel.daiMa.contains(LikeQuery)))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(LikeQuery),qModel.mingCheng.contains(LikeQuery)))
                .orderBy(qModel.shunXuHao.asc())
                .fetch();
        return MapUtils.copyListProperties(modelList,BR_ZD_HeBingQZPZListDto::new);
    }
}

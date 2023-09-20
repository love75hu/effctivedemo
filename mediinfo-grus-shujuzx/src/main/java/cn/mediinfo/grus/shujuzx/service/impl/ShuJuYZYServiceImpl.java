package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.CanShuException;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.MapUtils;
import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.dto.shujuyzys.*;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_ShuJuYLBModel;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_ShuJuYZYModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_ShuJuYZYModel;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_ShuJuYLBRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_ShuJuYZYRepository;
import cn.mediinfo.grus.shujuzx.service.ShuJuYZYService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ShuJuYZYServiceImpl implements ShuJuYZYService {
    private final SC_ZD_ShuJuYLBRepository sc_zd_shuJuYLBRepository;
    private final SC_ZD_ShuJuYZYRepository sc_zd_shuJuYZYRepository;
    private final LyraIdentityService lyraIdentityService;

    public ShuJuYZYServiceImpl(
            SC_ZD_ShuJuYLBRepository sc_zd_shuJuYLBRepository,
            SC_ZD_ShuJuYZYRepository sc_zd_shuJuYZYRepository,
            LyraIdentityService lyraIdentityService) {
        this.sc_zd_shuJuYLBRepository = sc_zd_shuJuYLBRepository;
        this.sc_zd_shuJuYZYRepository = sc_zd_shuJuYZYRepository;
        this.lyraIdentityService = lyraIdentityService;
    }

    /**
     * 新增数据源值域
     *
     * @param createDto
     * @return
     * @throws TongYongYWException
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public String addShuJuYZY(SC_ZD_ShuJuYZYCreateDto createDto) throws TongYongYWException {
        //查询是否存在
        var shiFouCZ = sc_zd_shuJuYZYRepository.existsByShuJuYLBIDAndZhiYuID(createDto.getShuJuYLBID(), createDto.getZhiYuID());
        //判断是否存在数据元类别
        if (shiFouCZ) {
            throw new TongYongYWException("数据源值域已存在:" + createDto.getShuJuYLBID() + "!");
        }

        //构造
        var entity = MapUtils.copyProperties(createDto, SC_ZD_ShuJuYZYModel::new, (s, t) -> {
            if (t.getShunXuHao() == null) {
                t.setShunXuHao(0);
            }
            t.setZuZhiJGID(ShuJuZXConstant.TONGYONG_JGID);
            t.setZuZhiJGMC(ShuJuZXConstant.TONGYONG_JGMC);
        });

        //保存
        var result = sc_zd_shuJuYZYRepository.save(entity);
        return result.getId();
    }

    /**
     * 修改数据源值域
     *
     * @param updateDto
     * @return
     * @throws TongYongYWException
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public String updateShuJuYZY(SC_ZD_ShuJuYZYUpdateDto updateDto) throws TongYongYWException {
        //查询是否存在
        var shiFouCZ = sc_zd_shuJuYZYRepository.existsByIdIsNotAndShuJuYLBIDAndZhiYuID(updateDto.getId(), updateDto.getShuJuYLBID(), updateDto.getZhiYuID());
        //判断是否存在数据元类别
        if (shiFouCZ) {
            throw new TongYongYWException("数据源值域已存在!");
        }
        var entity = sc_zd_shuJuYZYRepository.findById(updateDto.getId()).orElse(null);
        if (null == entity) {
            throw new TongYongYWException("该数据源值域不存在!");
        }
        //把dto赋值到entity
        MapUtils.mergeProperties(updateDto, entity);
        if (null == entity.getShunXuHao()) {
            entity.setShunXuHao(0);
        }
        var result = sc_zd_shuJuYZYRepository.save(entity);
        return result.getId();
    }

    /**
     * 作废数据源值域
     *
     * @param id
     * @return
     * @throws TongYongYWException
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean zuoFeiShuJuYZY(String id) throws TongYongYWException {
        var entity = sc_zd_shuJuYZYRepository.findById(id).orElse(null);
        if (null == entity) {
            throw new TongYongYWException("该数据源值域不存在!");
        }
        //删除数据源值域
        sc_zd_shuJuYZYRepository.delete(entity);
        return true;
    }

    /**
     * 根据ID获取数据源值域
     *
     * @param id
     * @return
     * @throws
     */
    @Override
    public SC_ZD_ShuJuYZYDto getShuJuYZYByID(String id) {
        var shuJuYZY = sc_zd_shuJuYZYRepository.findById(id).orElse(null);
        return MapUtils.copyProperties(shuJuYZY, SC_ZD_ShuJuYZYDto::new);
    }

    /**
     * 批量获取数据源值域list
     *
     * @param shuJuYLBIDList
     * @return
     * @throws CanShuException
     */
    @Override
    public List<SC_ZD_ShuJuYZYListDto> getShuJuYZYByLBIDList(List<String> shuJuYLBIDList) throws CanShuException {
        if (null == shuJuYLBIDList || shuJuYLBIDList.isEmpty()) {
            throw new CanShuException("数据源类别ID集合不能为空！");
        }

        var shuJuYZYEntity = sc_zd_shuJuYZYRepository.findByShuJuYLBIDIn(shuJuYLBIDList);
        var shuJuYZYList = MapUtils.copyListProperties(shuJuYZYEntity, SC_ZD_ShuJuYZYItemDto::new);
        //根据数据源类别id进行分组
        var groupList = shuJuYZYList.stream().collect(Collectors.groupingBy(SC_ZD_ShuJuYZYItemDto::getShuJuYLBID));
        List<SC_ZD_ShuJuYZYListDto> result = new ArrayList<>();
        //循环处理数据
        for (var item : groupList.entrySet()) {
            //查询到的数据源值域
            var list = MapUtils.copyListProperties(item.getValue().stream().toList(), SC_ZD_ShuJuYZYItemDto::new);
            var shuJuYZYDto = new SC_ZD_ShuJuYZYListDto();
            //赋值dto
            shuJuYZYDto.setShuJuYLBID(item.getKey());
            shuJuYZYDto.setZhiYuList(list.stream().sorted(Comparator.comparing(SC_ZD_ShuJuYZYItemDto::getBiaoZhunDM)).toList());
            result.add(shuJuYZYDto);
        }
        //进行排序
        return result.stream().sorted(Comparator.comparing(SC_ZD_ShuJuYZYListDto::getShuJuYLBID)).toList();
    }

    /**
     * 根据数据源类别ID获取数据源值域
     *
     * @param shuJuYLBID
     * @return
     * @throws
     */
    @Override
    public List<SC_ZD_ShuJuYZYDto> getShuJuYZYListByLBID(String shuJuYLBID) {
        var entityList = sc_zd_shuJuYZYRepository.findByShuJuYLBID(shuJuYLBID);

        return MapUtils.copyListProperties(entityList, SC_ZD_ShuJuYZYDto::new)
                .stream()
                .sorted(Comparator.comparing(SC_ZD_ShuJuYZYDto::getShunXuHao, Comparator.nullsLast(Integer::compareTo)))
                .toList();
    }

    /**
     * 查询数据源值域list
     *
     * @param zuZhiJGID
     * @param shuJuYLBID
     * @param likeQuery
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws WeiZhaoDSJException
     */
    @Override
    public List<SC_ZD_ShuJuYZYDto> getShuJuYZYList(String zuZhiJGID, String shuJuYLBID, String likeQuery, Integer pageIndex, Integer pageSize) throws WeiZhaoDSJException {
        QSC_ZD_ShuJuYLBModel shuJuYLB = QSC_ZD_ShuJuYLBModel.sC_ZD_ShuJuYLBModel;
        var factoryLB = new JPAQueryFactory(sc_zd_shuJuYLBRepository.getEntityManager());
        var queryLB = factoryLB.select(shuJuYLB).from(shuJuYLB).where(shuJuYLB.shuJuYLBID.eq(shuJuYLBID));
        if (StringUtils.hasText(zuZhiJGID)) {
            queryLB.where(shuJuYLB.zuZhiJGID.eq(zuZhiJGID));
        }
        if (queryLB.fetch().isEmpty()) {
            throw new WeiZhaoDSJException("未找到此数据源类别");
        }

        QSC_ZD_ShuJuYZYModel shuJuYZY = QSC_ZD_ShuJuYZYModel.sC_ZD_ShuJuYZYModel;
        var factory = new JPAQueryFactory(sc_zd_shuJuYZYRepository.getEntityManager());
        var query = factory.select(shuJuYZY).from(shuJuYZY);

        query.where(shuJuYZY.shuJuYLBID.eq(shuJuYLBID));
        //组织机构过滤
        if (StringUtils.hasText(zuZhiJGID)) {
            query.where(shuJuYZY.zuZhiJGID.eq(zuZhiJGID));
        }
        //获取输入码类型
        var shuRuMLX = lyraIdentityService.getShuRuMLX();
        //输入码过滤
        if (StringUtils.hasText(likeQuery)) {
            query.where(shuJuYZY.biaoZhunDM.like("%" + likeQuery + "%")
                    .or(shuJuYZY.biaoZhunMC.like("%" + likeQuery + "%")));
            if ("1".equals(shuRuMLX)) {
                query.where(shuJuYZY.shuRuMa1.toLowerCase().like("%" + likeQuery.toLowerCase() + "%"));
            } else if ("2".equals(shuRuMLX)) {
                query.where(shuJuYZY.shuRuMa2.toLowerCase().like("%" + likeQuery.toLowerCase() + "%"));
            } else {
                query.where(shuJuYZY.shuRuMa3.toLowerCase().like("%" + likeQuery.toLowerCase() + "%"));
            }
        }
        query.orderBy(shuJuYZY.shunXuHao.asc());

        //分页处理
        var shuJuYZYList=query.limit(pageSize).offset((long) (pageIndex - 1) *pageSize).fetch();
        // var shuJuYZYList = query.offset(PageRequestUtil.of(pageIndex, pageSize).getOffset()).limit(pageIndex).fetch();
        return MapUtils.copyListProperties(shuJuYZYList, SC_ZD_ShuJuYZYDto::new);
    }

    /**
     * 获取数据源值域总行数
     *
     * @param zuZhiJGID
     * @param shuJuYLBID
     * @param likeQuery
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws WeiZhaoDSJException
     */
    @Override
    public long getShuJuYZYCount(String zuZhiJGID, String shuJuYLBID, String likeQuery, Integer pageIndex, Integer pageSize) throws WeiZhaoDSJException {
        QSC_ZD_ShuJuYLBModel shuJuYLB = QSC_ZD_ShuJuYLBModel.sC_ZD_ShuJuYLBModel;
        var factoryLB = new JPAQueryFactory(sc_zd_shuJuYLBRepository.getEntityManager());
        var queryLB = factoryLB.select(shuJuYLB).from(shuJuYLB).where(shuJuYLB.shuJuYLBID.eq(shuJuYLBID));
        if (StringUtils.hasText(zuZhiJGID)) {
            queryLB.where(shuJuYLB.zuZhiJGID.eq(zuZhiJGID));
        }
        if (queryLB.fetch().isEmpty()) {
            throw new WeiZhaoDSJException("未找到此数据源类别");
        }

        QSC_ZD_ShuJuYZYModel shuJuYZY = QSC_ZD_ShuJuYZYModel.sC_ZD_ShuJuYZYModel;
        var factory = new JPAQueryFactory(sc_zd_shuJuYZYRepository.getEntityManager());
        var query = factory.select(shuJuYZY).from(shuJuYZY);

        query.where(shuJuYZY.shuJuYLBID.eq(shuJuYLBID));
        //组织机构过滤
        if (StringUtils.hasText(zuZhiJGID)) {
            query.where(shuJuYZY.zuZhiJGID.eq(zuZhiJGID));
        }
        //获取输入码类型
        var shuRuMLX = lyraIdentityService.getShuRuMLX();
        //输入码过滤
        if (StringUtils.hasText(likeQuery)) {
            query.where(shuJuYZY.biaoZhunDM.like("%" + likeQuery + "%")
                    .or(shuJuYZY.biaoZhunMC.like("%" + likeQuery + "%")));
            if ("1".equals(shuRuMLX)) {
                query.where(shuJuYZY.shuRuMa1.toLowerCase().like("%" + likeQuery.toLowerCase() + "%"));
            } else if ("2".equals(shuRuMLX)) {
                query.where(shuJuYZY.shuRuMa2.toLowerCase().like("%" + likeQuery.toLowerCase() + "%"));
            } else {
                query.where(shuJuYZY.shuRuMa3.toLowerCase().like("%" + likeQuery.toLowerCase() + "%"));
            }
        }
        return query.fetch().size();
    }
}

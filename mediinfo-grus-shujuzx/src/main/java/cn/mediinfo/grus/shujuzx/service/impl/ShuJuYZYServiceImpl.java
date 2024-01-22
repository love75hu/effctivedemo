package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.CanShuException;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
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

        //获取输入码类型
        var shuRuMLX = lyraIdentityService.getShuRuMLX();
        var shuJuYZYList = sc_zd_shuJuYZYRepository.getShuJuYZYList(zuZhiJGID, shuJuYLBID, likeQuery, shuRuMLX, pageIndex, pageSize);
        return BeanUtil.copyListProperties(shuJuYZYList, SC_ZD_ShuJuYZYDto::new);
    }

    /**
     * 获取数据源值域总行数
     *
     * @param zuZhiJGID
     * @param shuJuYLBID
     * @param likeQuery
     * @return
     * @throws WeiZhaoDSJException
     */
    @Override
    public long getShuJuYZYCount(String zuZhiJGID, String shuJuYLBID, String likeQuery) throws WeiZhaoDSJException {
        QSC_ZD_ShuJuYLBModel shuJuYLB = QSC_ZD_ShuJuYLBModel.sC_ZD_ShuJuYLBModel;
        var factoryLB = new JPAQueryFactory(sc_zd_shuJuYLBRepository.getEntityManager());
        var queryLB = factoryLB.select(shuJuYLB).from(shuJuYLB).where(shuJuYLB.shuJuYLBID.eq(shuJuYLBID));
        if (StringUtils.hasText(zuZhiJGID)) {
            queryLB.where(shuJuYLB.zuZhiJGID.eq(zuZhiJGID));
        }
        if (queryLB.fetch().isEmpty()) {
            throw new WeiZhaoDSJException("未找到此数据源类别");
        }

        //获取输入码类型
        var shuRuMLX = lyraIdentityService.getShuRuMLX();
        return sc_zd_shuJuYZYRepository.getShuJuYZYListCount(zuZhiJGID, shuJuYLBID, likeQuery, shuRuMLX);
    }

    /**
     * 根据数据源类别ID获取数据源值域树
     *
     * @param shuJuYLBID
     * @return
     * @throws
     */
    @Override
    public List<TreeDto> getShuJuYZYTreeListByLBID(String shuJuYLBID) {
        var entityList = sc_zd_shuJuYZYRepository.findByShuJuYLBID(shuJuYLBID).stream().toList();
        List<TreeDto> resultList = new ArrayList<>();
        //拼装成树形结构、获取一级
        List<String> allSJYZYIDs = entityList.stream().map(SC_ZD_ShuJuYZYModel::getBiaoZhunDM).toList();
        List<SC_ZD_ShuJuYZYModel> yiJiZYList = entityList.stream().filter(x -> !allSJYZYIDs.contains(x.getFuJiDM())).toList();
        //设置 resultList
        setResultList(resultList, entityList, yiJiZYList);
        return resultList;
    }

    private void setResultList(List<TreeDto> resultList, List<SC_ZD_ShuJuYZYModel> allZhiYuList, List<SC_ZD_ShuJuYZYModel> yiJiZYList) {
        for (SC_ZD_ShuJuYZYModel item : yiJiZYList) {
            TreeDto root = new TreeDto();
            root.setDaiMa(item.getBiaoZhunDM());
            root.setId(item.getId());
            root.setName(item.getBiaoZhunMC());
            root.setLabel(item.getBiaoZhunMC());
            root.setValue(item.getBiaoZhunDM());
            root.setPId("0");
            root.setShangJiSJYZYDM(item.getFuJiDM());
            root.setMoJiBZ(item.getMoJiBZ());
            root.setChildren(new ArrayList<TreeDto>());
            //  root.setJiGouQXBZ((JiGouIDs.contains(ZuZhiJGConstant.STRING_ZERO) || JiGouIDs.contains(item.getZuZhiJGID())) ? ZuZhiJGConstant.INTEGER_1 : ZuZhiJGConstant.INTEGER_0);
            List<SC_ZD_ShuJuYZYModel> childrenSJYZY = new ArrayList<>();
            childrenSJYZY = allZhiYuList.stream().filter(x -> item.getBiaoZhunDM().equals(x.getFuJiDM())).toList();
            resultList.add(getDownChildren(allZhiYuList, childrenSJYZY, root));
        }
    }

    private TreeDto getDownChildren(List<SC_ZD_ShuJuYZYModel> allData, List<SC_ZD_ShuJuYZYModel> child, TreeDto parent) {
        List<SC_ZD_ShuJuYZYModel> childList = child.stream().sorted(Comparator.comparing(SC_ZD_ShuJuYZYModel::getShunXuHao, Comparator.nullsLast(Integer::compareTo))).toList();
        for (SC_ZD_ShuJuYZYModel item : childList) {
            List<SC_ZD_ShuJuYZYModel> list = allData.stream().filter(o -> item.getBiaoZhunDM().equals(o.getFuJiDM())).toList();
            if (list.size() > 0) {
                setValues(allData, parent, item, list, "0");
            } else {
                setValues(null, parent, item, null, "1");
            }
        }
        return parent;
    }

    /**
     * 设置值
     *
     * @param allData
     * @param parent
     * @param item
     * @param list
     * @param flag
     */
    private void setValues(List<SC_ZD_ShuJuYZYModel> allData, TreeDto parent, SC_ZD_ShuJuYZYModel item,
                           List<SC_ZD_ShuJuYZYModel> list, String flag) {
        TreeDto childNode = new TreeDto();
        childNode.setDaiMa(item.getBiaoZhunDM());
        childNode.setId(item.getId());
        childNode.setName(item.getBiaoZhunMC());
        childNode.setLabel(item.getBiaoZhunMC());
        childNode.setValue(item.getBiaoZhunDM());
        childNode.setPId(parent.getId());
        childNode.setShangJiSJYZYDM(item.getFuJiDM());
        childNode.setMoJiBZ(item.getMoJiBZ());
        if ("0".equals(flag)) {
            childNode.setChildren(new ArrayList<TreeDto>());
            parent.getChildren().add(getDownChildren(allData, list, childNode));
        } else {
            parent.getChildren().add(childNode);
        }

    }
}

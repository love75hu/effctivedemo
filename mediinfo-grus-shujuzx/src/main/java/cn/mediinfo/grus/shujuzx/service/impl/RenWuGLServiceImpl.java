package cn.mediinfo.grus.shujuzx.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.grus.shujuzx.dto.renwugls.*;
import cn.mediinfo.grus.shujuzx.model.*;
import cn.mediinfo.grus.shujuzx.po.renwugl.TongYongPZPO;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.GY_ZD_ShuJuYuanTreeRso;
import cn.mediinfo.grus.shujuzx.remoteservice.GongYongRemoteService;
import cn.mediinfo.grus.shujuzx.repository.*;
import cn.mediinfo.grus.shujuzx.service.RenWuGLService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import cn.mediinfo.lyra.extension.service.SequenceService;
import com.querydsl.core.types.Expression;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class RenWuGLServiceImpl implements RenWuGLService {
    private final SequenceService sequenceService;
    private final SC_RW_JiBenXXRepository jiBenXXRepository;
    private final LyraIdentityService lyraIdentityService;
    private final SC_RW_ZhiXingRZRepository zhiXingRZRepository;
    private final SC_RW_ShuJuYuanRepository shuJuYuanRepository;
    private final SC_RW_TongYongPZRepository tongYongPZRepository;
    private final SC_ZD_ShuJuYZYRepository shuJuYZYRepository;
    private final SC_ZD_ShuJuYLBRepository shuJuYLBRepository;
    private final GongYongRemoteService gongYongRemoteService;


    public RenWuGLServiceImpl(SequenceService sequenceService, SC_RW_JiBenXXRepository jiBenXXRepository, LyraIdentityService lyraIdentityService,
                              SC_RW_ZhiXingRZRepository zhiXingRZRepository, SC_RW_ShuJuYuanRepository shuJuYuanRepository, SC_RW_TongYongPZRepository tongYongPZRepository,
                              SC_ZD_ShuJuYZYRepository shuJuYZYRepository, SC_ZD_ShuJuYLBRepository shuJuYLBRepository, GongYongRemoteService gongYongRemoteService) {
        this.lyraIdentityService = lyraIdentityService;
        this.jiBenXXRepository = jiBenXXRepository;
        this.sequenceService = sequenceService;
        this.zhiXingRZRepository = zhiXingRZRepository;
        this.shuJuYuanRepository = shuJuYuanRepository;
        this.tongYongPZRepository = tongYongPZRepository;
        this.shuJuYZYRepository = shuJuYZYRepository;
        this.shuJuYLBRepository = shuJuYLBRepository;
        this.gongYongRemoteService = gongYongRemoteService;
    }

    /**
     * 获取列表
     *
     * @param likeQuery
     * @param fenLeiDM
     * @param qiYongBZ
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<SC_RW_JiBenXXListDto> getJiBenXXList(String likeQuery, String fenLeiDM, Integer qiYongBZ, Integer pageIndex, Integer pageSize) {
        var result = jiBenXXRepository.asQuerydsl()
                .whereIf(StringUtil.hasText(likeQuery), t -> t.renWuMC.contains(likeQuery))
                .whereIf(StringUtil.hasText(fenLeiDM), t -> t.fenLeiDM.eq(fenLeiDM))
                .whereIf(Objects.equals(qiYongBZ, 1), t -> t.qiYongBZ.eq(qiYongBZ))
                //.where(t->t.qiYongBZ.eq(qiYongBZ))
                .select(SC_RW_JiBenXXListDto.class)
                .fetchPage(pageIndex, pageSize);
        return result;
    }

    /**
     * 获取列表数量
     *
     * @param likeQuery
     * @param fenLeiDM
     * @param qiYongBZ
     * @return
     */
    @Override
    public long getJiBenXXCount(String likeQuery, String fenLeiDM, Integer qiYongBZ) {
        return jiBenXXRepository.asQuerydsl()
                .whereIf(StringUtil.hasText(likeQuery), t -> t.renWuMC.contains(likeQuery))
                .whereIf(StringUtil.hasText(fenLeiDM), t -> t.fenLeiDM.eq(fenLeiDM))
                .whereIf(Objects.equals(qiYongBZ, 1), t -> t.qiYongBZ.eq(qiYongBZ)).count();
    }

    @Override
    public SC_RW_JiBenXXDto getRenWuXXById(String id) throws TongYongYWException {
        var result = jiBenXXRepository.findById(id).orElse(null);
        if (Objects.isNull(result)) {
            throw new TongYongYWException("该任务基本信息不存在！");
        }
        return BeanUtil.copyProperties(result, SC_RW_JiBenXXDto::new);
    }

    /**
     * 根据ID查询任务基本信息
     *
     * @param ids
     * @return
     * @throws TongYongYWException
     */
    @Override
    public List<SC_RW_JiBenXXDto> getRenWuXXByIds(List<String> ids) throws TongYongYWException {
        var result = jiBenXXRepository.findAllById(ids);
        if (result.size() == 0) {
            throw new TongYongYWException("该任务基本信息不存在！");
        }
        return BeanUtil.copyListProperties(result, SC_RW_JiBenXXDto::new);
    }

    /**
     * 新增任务基本信息
     *
     * @param createDto
     * @return
     * @throws TongYongYWException
     */

    @Override
    @Transactional(rollbackOn = Exception.class)
    public String addRenWuJBXX(SC_RW_JiBenXXCreateDto createDto) throws TongYongYWException {
        //查询是否存在
        var shiFouCZ = jiBenXXRepository.existsByRenWuMC(createDto.getRenWuMC());
        //判断是否存在数据元类别
        if (shiFouCZ) {
            throw new TongYongYWException("该任务名称已存在:" + createDto.getRenWuMC() + "!");
        }
        if (!StringUtil.hasText(createDto.getZhiXingPLDM()) && StringUtil.hasText(createDto.getZhiXingPLMC())) {
            var dto = BeanUtil.copyProperties(createDto, SC_RW_JiBenXXDto::new);
            createDto.setZhiXingPLDM(saveZhiXingPZZD(dto));
        }

        var entity = BeanUtil.copyProperties(createDto, SC_RW_JiBenXXModel::new, (s, t) -> {
            t.setRenWuID(sequenceService.getXuHao("SC_RW_JiBenXX_RenWuID", 7));
            t.setZuZhiJGID(lyraIdentityService.getJiGouID());
            t.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
        });
        jiBenXXRepository.save(entity);
        return entity.getId();
    }

    /**
     * 编辑任务基本信息
     *
     * @param updateDto
     * @return
     * @throws TongYongYWException
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean updateRenWuJBXX(SC_RW_JiBenXXUpdateDto updateDto) throws TongYongYWException {
        var entity = jiBenXXRepository.findById(updateDto.getId()).orElse(null);
        if (Objects.isNull(entity)) {
            throw new TongYongYWException("该任务基本信息不存在");
        }
        //查询是否存在
        var shiFouCZ = jiBenXXRepository.existsByIdIsNotAndRenWuMC(updateDto.getId(), updateDto.getRenWuMC());
        //判断是否存在数据元类别
        if (shiFouCZ) {
            throw new TongYongYWException("该任务名称已存在!");
        }
        if (!StringUtil.hasText(updateDto.getZhiXingPLDM()) && StringUtil.hasText(updateDto.getZhiXingPLMC())) {
            var dto = BeanUtil.copyProperties(updateDto, SC_RW_JiBenXXDto::new);
            updateDto.setZhiXingPLDM(saveZhiXingPZZD(dto));
        }
        //把dto赋值到entity
        BeanUtil.mergeProperties(updateDto, entity);
        jiBenXXRepository.save(entity);
        return true;
    }

    /**
     * 启用任务
     *
     * @param id
     * @param qiYongBZ
     * @return
     * @throws TongYongYWException
     */
    @Override
    public Boolean qiYongRW(String id, Integer qiYongBZ) throws TongYongYWException {
        var entity = jiBenXXRepository.findById(id).orElse(null);
        if (Objects.isNull(entity)) {
            throw new TongYongYWException("该任务基本信息不存在");
        }
        entity.setQiYongBZ(qiYongBZ);
        jiBenXXRepository.save(entity);
        return true;
    }

    /**
     * 根据id查询日志详情
     * @param id
     * @return
     */
    @Override
    public SC_RW_ZhiXingRZDto getZhiXingRZDto(String id){
        var entity= zhiXingRZRepository.findById(id).orElse(null);
        return BeanUtil.copyProperties(entity,SC_RW_ZhiXingRZDto::new);
    }
    /**
     * 获取列表
     *
     * @param renWuID
     * @param zhiXingKSSJ
     * @param zhiXingJSSJ
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<SC_RW_ZhiXingRZListDto> getZhiXingRZList(String renWuID, Date zhiXingKSSJ, Date zhiXingJSSJ, Integer pageIndex, Integer pageSize) {
        return zhiXingRZRepository.asQuerydsl().where(t -> t.renWuID.eq(renWuID).and(t.zhiXingKSSJ.after(zhiXingKSSJ)).and(t.zhiXingJSSJ.before(zhiXingJSSJ)))
                .orderBy(t -> t.zhiXingSJ.desc())
                .select(SC_RW_ZhiXingRZListDto.class)
                .fetchPage(pageIndex, pageSize);
    }

    @Override
    public Long getZhiXingRZCount(String renWuID, Date zhiXingKSSJ, Date zhiXingJSSJ) {
        return zhiXingRZRepository.asQuerydsl().where(t -> t.renWuID.eq(renWuID).and(t.zhiXingKSSJ.after(zhiXingKSSJ)).and(t.zhiXingJSSJ.before(zhiXingJSSJ)))
                .select(SC_RW_ZhiXingRZListDto.class)
                .count();
    }

    /**
     * 根据idList获取任务基本信息
     *
     * @param ids
     * @return
     */
    @Override
    public List<SC_RW_JiBenXXDto> getJiBenXXDtoList(String ids) {
        var idList = ids.split(",");
        return zhiXingRZRepository.asQuerydsl().where(t -> t.id.in(idList)).select(SC_RW_JiBenXXDto.class).fetch();

    }

    /**
     * 根据任务ID获取数据源列表
     *
     * @param renWuID
     * @return
     */
    @Override
    public List<SC_RW_ShuJuYuanDto> getShuJuYuanList(String renWuID) {

        List<GY_ZD_ShuJuYuanTreeRso> ShuJuYuanTree = gongYongRemoteService.getShuJuYFLTree().getData();
        List<GY_ZD_ShuJuYuanTreeRso> treeChild = new ArrayList<>();
        ShuJuYuanTree.forEach(item->{
           //var array=  Stream.concat(item.getChildren().stream(), treeChild.stream()).toList();
           treeChild.addAll(Stream.concat(item.getChildren().stream(), treeChild.stream()).toList());
        });
        var result=shuJuYuanRepository.asQuerydsl().where(t -> t.renWuID.eq(renWuID)).fetch();
       var entity=  BeanUtil.copyListProperties(result,SC_RW_ShuJuYuanDto::new,(s,t)->{
            var itemchild=treeChild.stream().filter(q->Objects.equals(q.getId(),t.getShuJuYID())).findFirst().get();
            t.setChangShangMC(itemchild.getChangShangMC());
            t.setXiTongMC(itemchild.getXiTongMC());
        });

        return entity;
    }


    /**
     * 数据源列表保存
     *
     * @param createDto
     * @return
     * @throws TongYongYWException
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean saveShuJuYuanList(SC_RW_ShuJuYuanCreateDto createDto) throws TongYongYWException {
        var renWuDto=jiBenXXRepository.findFirstByRenWuID(createDto.getRenWuID());
        if (renWuDto == null) {
            throw new TongYongYWException("当前任务基本信息不存在！");
        }
        var shuJuYuanList = shuJuYuanRepository.asQuerydsl().where(t -> t.renWuID.eq(createDto.getRenWuID())).fetch();
        var shuJuYIDs = shuJuYuanList.stream().map(SC_RW_ShuJuYuanModel::getShuJuYID).toList();
        var createShuJuYIDs = createDto.getShuJuYuanDtoList().stream().map(SC_RW_ShuJuYuanDto::getShuJuYID).toList();
        //求差值
        List<String> addIDList = new ArrayList<String>(createShuJuYIDs);
        List<String> delIDList = new ArrayList<String>(shuJuYIDs);
        addIDList.removeAll(shuJuYIDs);
        delIDList.removeAll(createShuJuYIDs);
        //需新增的数据源
        List<SC_RW_ShuJuYuanDto> addList = createDto.getShuJuYuanDtoList().stream().filter(t -> addIDList.contains(t.getShuJuYID())).toList();

        var entity = BeanUtil.copyProperties(addList, SC_RW_ShuJuYuanModel::new, (s, t) -> {
            t.setZuZhiJGID(lyraIdentityService.getJiGouID());
            t.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            t.setRenWuID(renWuDto.getRenWuID());
            t.setRenWuMC(renWuDto.getRenWuMC());
            //t.setQiYongBZ(1);//默认启用
        });
        shuJuYuanRepository.save(entity);
        //需删除的数据源
        shuJuYuanRepository.asDeleteDsl().where(t -> t.shuJuYID.in(delIDList));
        return true;
    }

    /**
     * 获取通用配置列表
     *
     * @return
     */
    @Override
    public List<SC_RW_TongYongPZDto> getTongYongPZ() {
        return shuJuYZYRepository.asQuerydsl()
                .where(q -> q.shuJuYLBID.eq("SC0021"))
                .orderBy(q -> q.shunXuHao.asc())
                .leftJoin(tongYongPZRepository.asQuerydsl(), (shuJuYZY, tongYongPZ) -> shuJuYZY.zhiYuID.eq(tongYongPZ.fenLeiDM), TongYongPZPO::new)
                .select(q -> new Expression<?>[]{
                        q.tongYongPZ().zuZhiJGID,
                        q.tongYongPZ().zuZhiJGMC,
                        q.tongYongPZ().renWuDZ,
                        q.tongYongPZ().fuWuQIP,
                        q.tongYongPZ().id,
                        q.tongYongPZ().fuWuQDK,
                        q.shuJuYZY().zhiYuID.as("fenLeiDM"),
                        q.shuJuYZY().zhiYuMC.as("fenLeiMC")
                }, SC_RW_TongYongPZDto.class).fetch();

    }

    /**
     * 保存通用配置
     *
     * @param creatDto
     * @return
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean saveTongYongPZ(List<SC_RW_TongYongPZDto> creatDto) {
        List<SC_RW_TongYongPZModel> result = new ArrayList<>();
        //新增
        var addDto = creatDto.stream().filter(t -> Objects.isNull(t.getId())).toList();
        var addEntity = BeanUtil.copyListProperties(addDto, SC_RW_TongYongPZModel::new, (s, t) -> {
            t.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            t.setZuZhiJGID(lyraIdentityService.getJiGouID());
        });
        tongYongPZRepository.saveAll(addEntity);
        //修改
        var updateDto = creatDto.stream().filter(t -> !Objects.isNull(t.getId())).toList();
        var ids = updateDto.stream().map(t -> t.getId()).toList();
        var tongYongPZList = tongYongPZRepository.asQuerydsl().where(t -> t.id.in(ids)).fetch();
        for (var item : tongYongPZList) {
            var tongYongPZItem = updateDto.stream().filter(q -> Objects.equals(q.getId(), item.getId())).findFirst().orElse(null);
            BeanUtil.mergeProperties(tongYongPZItem, item, true);
        }

        tongYongPZRepository.saveAll(tongYongPZList);
        return true;
    }

    /**
     * 保存执行频率字典
     *
     * @param dto
     * @return
     * @throws TongYongYWException
     */
    public String saveZhiXingPZZD(SC_RW_JiBenXXDto dto) throws TongYongYWException {
        String zhiYuID = "";
        //region 判断执行频率是否存在 不存在的话 新增到值域表

        var shuJuYLBDto = shuJuYLBRepository.asQuerydsl().where(t -> t.shuJuYLBID.eq("SC0018")).fetchOne();
        if (!Objects.isNull(shuJuYLBDto)) {
            //查询最大的顺序号
            var shuJuYZYList = shuJuYZYRepository.asQuerydsl().where(t -> t.shuJuYLBID.eq("SC0018")).select(SC_ZD_ShuJuYZYModel.class).fetch();
            // 获取shuJuYZYList集合中的SC_ZD_ShuJuYZYModel对象中的shunXuHao的最大值
            Optional<SC_ZD_ShuJuYZYModel> maxModel = shuJuYZYList.stream()
                    .max(Comparator.comparing(SC_ZD_ShuJuYZYModel::getShunXuHao));
            // 获取最大值
            int maxShunXuHao = maxModel.map(SC_ZD_ShuJuYZYModel::getShunXuHao).orElse(0);
            var zhuYuID = sequenceService.getXuHao("SC_ZD_ShuJuYZY_ZhiYuID", 7);
            SC_ZD_ShuJuYZYModel shuJuYZYDto = new SC_ZD_ShuJuYZYModel();
            shuJuYZYDto.setZuZhiJGID(lyraIdentityService.getJiGouID());
            shuJuYZYDto.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            shuJuYZYDto.setShuJuYLBID(shuJuYLBDto.getShuJuYLBID());
            shuJuYZYDto.setShuJuYLBMC(shuJuYLBDto.getShuJuYLBMC());
            shuJuYZYDto.setZhiYuMC(dto.getZhiXingPLMC());
            shuJuYZYDto.setMoJiBZ(1);
            shuJuYZYDto.setZhiYuID(zhuYuID);
            shuJuYZYDto.setBiaoZhunDM(zhuYuID);
            shuJuYZYDto.setBiaoZhunMC(dto.getZhiXingPLMC());
            shuJuYZYDto.setShunXuHao(maxShunXuHao + 1);
            shuJuYZYRepository.save(shuJuYZYDto);
            zhiYuID = shuJuYZYDto.getZhiYuID();
        } else {
            throw new TongYongYWException("执行频率数据源类别不存在！");
        }

        return zhiYuID;

        //endregion
    }

    /**
     * 保存执行信息
     *
     * @param id
     * @return
     */
    @Override
    public Boolean saveZhiXingRZ(String id) throws TongYongYWException {
        //var jiBenxx=jiBenXXRepository.findById(id).orElse(null);
        //var jiBenxx=jiBenXXRepository.asQuerydsl().where(t->t.renWuID.eq(id)).select(SC_RW_JiBenXXDto.class).fetchOne();
        var jiBenxx = jiBenXXRepository.findFirstByRenWuID(id);
        var zhixingkssj = new Date();
        if (Objects.isNull(jiBenxx)) {
            throw new TongYongYWException("该任务基本信息不存在！");
        }

        var entity = BeanUtil.copyProperties(jiBenxx, SC_RW_ZhiXingRZModel::new, (s, t) -> {
            t.setId(null);
            t.setZuZhiJGID(lyraIdentityService.getJiGouID());
            t.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            t.setZhiXingKSSJ(zhixingkssj);
            t.setZhiXingQYWZXSJ(zhixingkssj);
            t.setZhiXingHYWZXSJ(new Date());
            t.setRuCan(jiBenxx.getRenWuCS());

        });
        zhiXingRZRepository.save(entity);
        return true;
    }

    @Override
    public Boolean saveZhiXingRZList(List<SC_RW_ZhiXingRZCreateDto> creatDto) {
        var renWuIDs = creatDto.stream().map(SC_RW_ZhiXingRZCreateDto::getRenWuID).toList();
        var zhixingkssj = new Date();

        var jiBenXXList = jiBenXXRepository.asQuerydsl().where(t -> t.renWuID.in(renWuIDs)).fetch();
        var entity = BeanUtil.copyProperties(jiBenXXList, SC_RW_ZhiXingRZModel::new, (s, t) -> {
            t.setZuZhiJGID(lyraIdentityService.getJiGouID());
            t.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            t.setZhiXingKSSJ(zhixingkssj);
            var item = creatDto.stream().filter(q -> Objects.equals(q.getRenWuID(), t.getRenWuID())).findFirst();
            t.setRuCan(item.get().getRuCan());
        });
        zhiXingRZRepository.save(entity);
        return true;
    }

    @Override
    public SC_RW_TongYongPZDto getTongYongPZByFLDM(String fenLeiDM) {
        return shuJuYZYRepository.asQuerydsl()
                .where(q -> q.shuJuYLBID.eq("SC0021").and(q.zhiYuID.eq(fenLeiDM)))
                .orderBy(q -> q.shunXuHao.asc())
                .leftJoin(tongYongPZRepository.asQuerydsl(), (shuJuYZY, tongYongPZ) -> shuJuYZY.zhiYuID.eq(tongYongPZ.fenLeiDM), TongYongPZPO::new)
                .select(q -> new Expression<?>[]{
                        q.tongYongPZ().zuZhiJGID,
                        q.tongYongPZ().zuZhiJGMC,
//                        q.tongYongPZ().renWuDZ,
                        q.tongYongPZ().fuWuQIP,
                        q.tongYongPZ().fuWuQIP.concat(":").concat(q.tongYongPZ().fuWuQDK).concat("/").concat(q.tongYongPZ().renWuDZ).as("renWuDZ"),
                        q.tongYongPZ().id,
                        q.tongYongPZ().fuWuQDK,
                        q.shuJuYZY().zhiYuID.as("fenLeiDM"),
                        q.shuJuYZY().zhiYuMC.as("fenLeiMC")
                }, SC_RW_TongYongPZDto.class).fetchOne();

    }


}

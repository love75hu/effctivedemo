package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.*;
import cn.mediinfo.grus.shujuzx.model.SC_BH_JieDianSXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_JieDianXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ZiBiHXSLModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ZiBiHXXModel;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_JieDianSXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_JieDianXXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ZiBiHXSLRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ZiBiHXXRepository;
import cn.mediinfo.grus.shujuzx.service.JieDianXXService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import com.querydsl.core.util.BeanUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 闭环节点信息服务
 */
@Service
public class JieDianXXServiceImpl implements JieDianXXService {
    private final SC_BH_JieDianXXRepository jieDianXXRepository;

    private final SC_BH_JieDianSXRepository jieDianSXRepository;

    private final SC_BH_ZiBiHXXRepository ziBiHXXRepository;

    private final SC_BH_ZiBiHXSLRepository ziBiHXSLRepository;

    private final LyraIdentityService lyraIdentityService;

    public JieDianXXServiceImpl(SC_BH_JieDianXXRepository jieDianXXRepository, SC_BH_JieDianSXRepository jieDianSXRepository, SC_BH_ZiBiHXXRepository ziBiHXXRepository, SC_BH_ZiBiHXSLRepository ziBiHXSLRepository, LyraIdentityService lyraIdentityService) {
        this.jieDianXXRepository = jieDianXXRepository;
        this.jieDianSXRepository = jieDianSXRepository;
        this.ziBiHXXRepository = ziBiHXXRepository;
        this.ziBiHXSLRepository = ziBiHXSLRepository;
        this.lyraIdentityService = lyraIdentityService;
    }

    /**
     * 根据ID获取闭环节点信息     *     * @param id     * @return
     */
    @Override
    public SC_BH_JieDianXXDto getJieDianXXByID(String id) throws WeiZhaoDSJException {
        var result = jieDianXXRepository.asQuerydsl().where(s -> s.id.eq(id)).select(SC_BH_JieDianXXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }

    //添加闭环设置信息
    @Override
    @Transactional(rollbackOn = Exception.class)
    public String addBiHuanSZXX(AddBiHuanSZXXDto dto) {
        // 从数据库获取现有的节点信息、时效信息和显示字段
        List<SC_BH_JieDianXXModel> existingJieDianXX = jieDianXXRepository.findByBiHuanID(dto.getBiHuanID());
        List<SC_BH_JieDianSXModel> existingJieDianSX = jieDianSXRepository.findByBiHuanID(dto.getBiHuanID());
        List<SC_BH_ZiBiHXSLModel> existingZiBiHXSL = ziBiHXSLRepository.findByBiHuanID(dto.getBiHuanID());
        List<SC_BH_ZiBiHXXModel> existingZiBiHXX = ziBiHXXRepository.findByBiHuanID(dto.getBiHuanID());

        // 创建集合以跟踪要保留的记录ID
        Set<String> retainedJieDianXXIds = new HashSet<>();
        Set<String> retainedJieDianSXIds = new HashSet<>();
        Set<String> retainedZiBiHXSLIds = new HashSet<>();
        Set<String> retainedZiBiHXXLIds = new HashSet<>();

        // 准备更新或插入的记录列表
        List<SC_BH_JieDianXXModel> jieDianXXToInsert = new ArrayList<>();
        List<SC_BH_JieDianXXModel> jieDianXXToUpdate = new ArrayList<>();
        List<SC_BH_JieDianSXModel> jieDianSXToInsert = new ArrayList<>();
        List<SC_BH_JieDianSXModel> jieDianSXToUpdate = new ArrayList<>();
        List<SC_BH_ZiBiHXSLModel> ziBiHXSLToInsert = new ArrayList<>();
        List<SC_BH_ZiBiHXSLModel> ziBiHXSLToUpdate = new ArrayList<>();
        List<SC_BH_ZiBiHXXModel> ziBiHXXToInsert = new ArrayList<>();
        List<SC_BH_ZiBiHXXModel> ziBiHXXToUpdate = new ArrayList<>();

        for (var b : dto.getJieDianXXSZDtoList()) {
            SC_BH_JieDianXXModel jieDianXXModel = BeanUtil.copyProperties(b, SC_BH_JieDianXXModel.class);
            jieDianXXModel.setBiHuanID(dto.getBiHuanID());
            jieDianXXModel.setBiHuanMC(dto.getBiHuanMC());
            jieDianXXModel.setShunXuHao(b.getShunXuHao());
            processRecordForUpdateOrInsert(jieDianXXModel, existingJieDianXX, jieDianXXToInsert, jieDianXXToUpdate, retainedJieDianXXIds);
            for (var s : b.getJieDianSXList()) {
                SC_BH_JieDianSXModel jieDianSXModel = BeanUtil.copyProperties(s, SC_BH_JieDianSXModel.class);
                jieDianSXModel.setBiHuanID(dto.getBiHuanID());
                jieDianSXModel.setBiHuanMC(dto.getBiHuanMC());
                processRecordForUpdateOrInsert(jieDianSXModel, existingJieDianSX, jieDianSXToInsert, jieDianSXToUpdate, retainedJieDianSXIds);
            }
            if (Objects.nonNull(b.getZiBiHXXDto()))
            {
                    SC_BH_ZiBiHXXModel scBhZiBiHXXModel = existingZiBiHXX.stream().filter(n -> n.getJieDianID().equals(b.getJieDianID())).findFirst().orElse(null);
                   if (scBhZiBiHXXModel!=null)
                   {
                       scBhZiBiHXXModel.setGuanLianZDBM(b.getZiBiHXXDto().getGuanLianZDBM());
                       scBhZiBiHXXModel.setGuanLianZDMC(b.getZiBiHXXDto().getGuanLianZDMC());
                       scBhZiBiHXXModel.setShiTuID(b.getZiBiHXXDto().getShiTuID());
                       scBhZiBiHXXModel.setShiTuMC(b.getZiBiHXXDto().getShiTuMC());
                       scBhZiBiHXXModel.setZiBiHID(b.getZiBiHXXDto().getZiBiHID());
                       scBhZiBiHXXModel.setZiBiHMC(b.getZiBiHXXDto().getZiBiHMC());
                       scBhZiBiHXXModel.setZiBiHSTID(b.getZiBiHXXDto().getZiBiHSTID());
                       scBhZiBiHXXModel.setZiBiHSTMC(b.getZiBiHXXDto().getZiBiHSTMC());
                       scBhZiBiHXXModel.setZiBiHZDBM(b.getZiBiHXXDto().getZiBiHZDBM());
                       scBhZiBiHXXModel.setZiBiHZDMC(b.getZiBiHXXDto().getZiBiHZDMC());
                       scBhZiBiHXXModel.setZuZhiJGID(b.getZiBiHXXDto().getZuZhiJGID());
                       scBhZiBiHXXModel.setZuZhiJGMC(b.getZiBiHXXDto().getZuZhiJGMC());
                       ziBiHXXToUpdate.add(scBhZiBiHXXModel);
                       retainedZiBiHXXLIds.add(b.getZiBiHXXDto().getId());
                   }else {
                       SC_BH_ZiBiHXXModel ziBiHXXModel = BeanUtil.copyProperties(b.getZiBiHXXDto(), SC_BH_ZiBiHXXModel.class);
                       ziBiHXXModel.setBiHuanID(dto.getBiHuanID());
                       ziBiHXXModel.setBiHuanMC(dto.getBiHuanMC());
                       ziBiHXXModel.setJieDianID(b.getJieDianID());
                       ziBiHXXModel.setJieDianMC(b.getJieDianMC());
                       ziBiHXXToInsert.add(ziBiHXXModel);
                   }
            }
            for (var x : b.getZiBiHXSLDtoList()) {
                SC_BH_ZiBiHXSLModel ziBiHXSLModel = BeanUtil.copyProperties(x, SC_BH_ZiBiHXSLModel.class);
                ziBiHXSLModel.setBiHuanID(dto.getBiHuanID());
                ziBiHXSLModel.setBiHuanMC(dto.getBiHuanMC());
                processRecordForUpdateOrInsert(ziBiHXSLModel, existingZiBiHXSL, ziBiHXSLToInsert, ziBiHXSLToUpdate, retainedZiBiHXSLIds);
            }
        }
        // 更新或插入记录
        if (!jieDianXXToInsert.isEmpty()) {
                jieDianXXRepository.saveAll(jieDianXXToInsert);
           // jieDianXXRepository.asUpdateBatchDsl()
        }
        if (!jieDianSXToInsert.isEmpty()) {
            jieDianSXRepository.saveAll(jieDianSXToInsert);
        }
        if (!ziBiHXSLToInsert.isEmpty()) {
            ziBiHXSLRepository.saveAll(ziBiHXSLToInsert);
        }
        if (!ziBiHXXToInsert.isEmpty())
        {
            ziBiHXXRepository.saveAll(ziBiHXXToInsert);
        }
        // 执行更新操作
        if (!jieDianXXToUpdate.isEmpty()) {
            jieDianXXRepository.asUpdateBatchDsl(jieDianXXToUpdate)
                    .set(q->q.xianShiMC,(q,t)->t.getXianShiMC())
                    .set(q->q.biXuBZ,(q,t)->t.getBiXuBZ())
                    .set(q->q.bingXingBZ,(q,t)->t.getBingXingBZ())
                    .set(q->q.yinCangBZ,(q,t)->t.getYinCangBZ())
                    .where((q,t)->q.id.eq(t.getId())).execute();
        }
        if (!jieDianSXToUpdate.isEmpty()) {
            jieDianSXRepository.asUpdateBatchDsl(jieDianSXToUpdate)
                    .set(q->q.jieDianID,(q,t)->t.getJieDianID())
                    .set(q->q.jieDianMC,(q,t)->t.getJieDianMC())
                    .set(q->q.guanLianJDID,(q,t)->t.getGuanLianJDID())
                    .set(q->q.guanLianJDMC,(q,t)->t.getGuanLianJDMC())
                    .set(q->q.yunSuanFDM,(q,t)->t.getYunSuanFDM())
                    .set(q->q.yunSuanFMC,(q,t)->t.getYunSuanFMC())
                    .set(q->q.shiXiao,(q,t)->t.getShiXiao())
                    .set(q->q.danWeiDM,(q,t)->t.getDanWeiDM())
                    .set(q->q.danWeiMC,(q,t)->t.getDanWeiMC())
                    .where((q,t)->q.id.eq(t.getId())).execute();
        }
        if (!ziBiHXSLToUpdate.isEmpty()) {
            ziBiHXSLRepository.asUpdateBatchDsl(ziBiHXSLToUpdate)
                    .set(q->q.jieDianID,(q,t)->t.getJieDianID())
                    .set(q->q.jieDianMC,(q,t)->t.getJieDianMC())
                    .set(q->q.ziBiHID,(q,t)->t.getZiBiHID())
                    .set(q->q.ziBiHMC,(q,t)->t.getZiBiHMC())
                    .set(q->q.shiTuID,(q,t)->t.getShiTuID())
                    .set(q->q.shiTuMC,(q,t)->t.getShiTuMC())
                    .set(q->q.ziDuanBM,(q,t)->t.getZiDuanBM())
                    .set(q->q.ziDuanMC,(q,t)->t.getZiDuanMC())
                    .set(q->q.shunXuHao,(q,t)->t.getShunXuHao())
                    .where((q,t)->q.id.eq(t.getId())).execute();
        }
        if (!ziBiHXXToUpdate.isEmpty())
        {
            ziBiHXXRepository.asUpdateBatchDsl(ziBiHXXToUpdate)
                    .set(q->q.guanLianZDBM,(q,t)->t.getGuanLianZDBM())
                    .set(q->q.guanLianZDMC,(q,t)->t.getGuanLianZDMC())
                    .set(q->q.shiTuID,(q,t)->t.getShiTuID())
                    .set(q->q.shiTuMC,(q,t)->t.getShiTuMC())
                    .set(q->q.ziBiHID,(q,t)->t.getZiBiHID())
                    .set(q->q.ziBiHMC,(q,t)->t.getZiBiHMC())
                    .set(q->q.ziBiHSTID,(q,t)->t.getZiBiHSTID())
                    .set(q->q.ziBiHSTMC,(q,t)->t.getZiBiHSTMC())
                    .set(q->q.zuZhiJGID,(q,t)->t.getZuZhiJGID())
                    .set(q->q.zuZhiJGMC,(q,t)->t.getZuZhiJGMC())
                    .set(q->q.ziBiHZDBM,(q,t)->t.getZiBiHZDBM())
                    .set(q->q.ziBiHZDMC,(q,t)->t.getZiBiHZDMC())
                    .where((q,t)->q.id.eq(t.getId())).execute();
        }
        // 删除数据库中但不在保留ID列表中的记录
        List<SC_BH_JieDianXXModel> jieDianXXToDelete = existingJieDianXX.stream()
                .filter(existing -> !retainedJieDianXXIds.contains(existing.getId()))
                .toList();
        if (!jieDianXXToDelete.isEmpty()) {
            jieDianXXRepository.deleteAll(jieDianXXToDelete);
        }
        List<SC_BH_JieDianSXModel> jieDianSXToDelete = existingJieDianSX.stream()
                .filter(existing -> !retainedJieDianSXIds.contains(existing.getId()))
                .toList();
        if (!jieDianSXToDelete.isEmpty()) {
            jieDianSXRepository.deleteAll(jieDianSXToDelete);
        }
        List<SC_BH_ZiBiHXSLModel> ziBiHXSLToDelete = existingZiBiHXSL.stream()
                .filter(existing -> !retainedZiBiHXSLIds.contains(existing.getId()))
                .toList();
        if (!ziBiHXSLToDelete.isEmpty()) {
            ziBiHXSLRepository.deleteAll(ziBiHXSLToDelete);
        }
        List<SC_BH_ZiBiHXXModel> ziBiHXXToDelete=existingZiBiHXX.stream()
                .filter(existing->!retainedZiBiHXXLIds.contains(existing.getId()))
                .toList();
        if (!ziBiHXXToDelete.isEmpty())
        {
            ziBiHXXRepository.deleteAll(ziBiHXXToDelete);
        }

            return dto.getBiHuanID();
    }

    private <T> void processRecordForUpdateOrInsert(T record, List<T> existingRecords, List<T> toInsert, List<T> toUpdate, Set<String> retainedIds) {
        String id =  org.dromara.hutool.core.bean.BeanUtil.getProperty(record, "id");
            T existingRecord = existingRecords.stream().filter(e ->  org.dromara.hutool.core.bean.BeanUtil.getProperty(e, "id").equals(id)).findFirst().orElse(null);

        if (existingRecord != null) {
            // 如果记录存在，准备更新
            org.dromara.hutool.core.bean.BeanUtil.setProperty(record, "id", org.dromara.hutool.core.bean.BeanUtil.getProperty(existingRecord, "id"));
            org.dromara.hutool.core.bean.BeanUtil.setProperty(record, "zuHuID", org.dromara.hutool.core.bean.BeanUtil.getProperty(existingRecord, "zuHuID"));
            org.dromara.hutool.core.bean.BeanUtil.setProperty(record, "zuHuMC", org.dromara.hutool.core.bean.BeanUtil.getProperty(existingRecord, "zuHuMC"));
            toUpdate.add(record);
            retainedIds.add(id);
        } else if (StringUtil.hasText(id)) {
            // 如果ID存在但记录不存在于数据库中，您可以选择如何处理（忽略或视为新增）
            toInsert.add(record);
        } else {
            // 如果ID为空，表示新增
            toInsert.add(record);
        }
    }

//添加闭环设置信息
//    @Override
//    @Transactional(rollbackOn = Exception.class)
//    public String addBiHuanSZXX(AddBiHuanSZXXDto dto) {
//
//        SC_BH_JieDianXXModel scBhJieDianXXModel = jieDianXXRepository.findById(dto.getJieDianXXSZDtoList().getShangJiJDID()).orElse(null);
//
//          SC_BH_JieDianXXModel jieDianXX = null; //todo 实例化
//
//        SC_BH_JieDianXXModel jieDianXXModel = BeanUtil.copyProperties(dto.getJieDianXXSZDtoList(), SC_BH_JieDianXXModel.class);
//        jieDianXXModel.setBiHuanID(dto.getBiHuanID());
//        jieDianXXModel.setBiHuanMC(dto.getBiHuanMC());
//        if (scBhJieDianXXModel==null)
//        {
//            if (dto.getJieDianXXSZDtoList().getId()!=null)
//            {
//                SC_BH_JieDianXXModel scBhJieDianXXModel1 = jieDianXXRepository.findById(dto.getJieDianXXSZDtoList().getId()).orElse(new SC_BH_JieDianXXModel());
//                BeanUtil.copyProperties(scBhJieDianXXModel1,jieDianXXModel);
//
//                jieDianXX= jieDianXXRepository.save(scBhJieDianXXModel1);
//            }else {
//                jieDianXXModel.setShunXuHao(1);
//                jieDianXX = jieDianXXRepository.save(jieDianXXModel);
//            }
//
//        }else {
//            if (!StringUtil.hasText(dto.getJieDianXXSZDtoList().getId()) ) {
//                //todo 查询操作修改
//                jieDianXXRepository.asDeleteDsl().where(n->n.biHuanID.eq(dto.getBiHuanID()))
//                        .where(n->n.jieDianID.eq(dto.getJieDianXXSZDtoList().getJieDianID())).execute();
//
//                jieDianXXModel.setShunXuHao(scBhJieDianXXModel.getShunXuHao() + 1);
//                //todo
//                List<SC_BH_JieDianXXModel> fetch = jieDianXXRepository.asQuerydsl().where(n -> n.shunXuHao.gt(scBhJieDianXXModel.getShunXuHao())).fetch();
//                for (var a : fetch) {
//                    a.setShunXuHao(a.getShunXuHao() + 1);
//                }
//                jieDianXXRepository.saveAll(fetch);
//                jieDianXX = jieDianXXRepository.save(jieDianXXModel);
//            }else {
//                SC_BH_JieDianXXModel scBhJieDianXXModel1 = jieDianXXRepository.findById(dto.getJieDianXXSZDtoList().getId()).orElse(new SC_BH_JieDianXXModel());
//               BeanUtil.copyProperties(scBhJieDianXXModel1,jieDianXXModel);
//
//                jieDianXX= jieDianXXRepository.save(scBhJieDianXXModel1);
//            }
//        }
//        //节点时效
//        List<SC_BH_JieDianSXModel> jieDianSX=new ArrayList<>();
//        //子闭环信息
//        List<SC_BH_ZiBiHXXModel> ziBiHXX= new ArrayList<>();
//        //子闭环显示列
//        List<SC_BH_ZiBiHXSLModel> ziBiHXSL=new ArrayList<>();
//
//            var jieDianXXSZ=dto.getJieDianXXSZDtoList();
//            if (BeanUtil.isNotEmpty(jieDianXXSZ.getZiBiHXXDto()))
//            {
//              var ziBiHXXModel=  BeanUtil.copyProperties(jieDianXXSZ.getZiBiHXXDto(),SC_BH_ZiBiHXXModel.class);
//
//                ziBiHXXModel.setBiHuanID(dto.getBiHuanID());
//                ziBiHXXModel.setBiHuanMC(dto.getBiHuanMC());
//                ziBiHXXModel.setJieDianID(jieDianXXSZ.getJieDianID());
//                ziBiHXXModel.setJieDianMC(jieDianXXSZ.getJieDianMC());
//                ziBiHXX.add(ziBiHXXModel);
//            }
//            //时效
//            for (var s :jieDianXXSZ.getJieDianSXList())
//            {
//               var jieDianSXModel= BeanUtil.copyProperties(s,SC_BH_JieDianSXModel.class);
//                jieDianSXModel.setBiHuanID(dto.getBiHuanID());
//                jieDianSXModel.setBiHuanMC(dto.getBiHuanMC());
//
//                jieDianSX.add(jieDianSXModel);
//            }
//            //显示字段
//            for (var x:jieDianXXSZ.getZiBiHXSLDtoList())
//            {
//                var ziBiHXSLModel=BeanUtil.copyProperties(x,SC_BH_ZiBiHXSLModel.class);
//                ziBiHXSLModel.setBiHuanID(dto.getBiHuanID());
//                ziBiHXSLModel.setBiHuanMC(dto.getBiHuanMC());
//                ziBiHXSL.add(ziBiHXSLModel);
//            }
//
//        //节点失效
//        jieDianSXRepository.asDeleteDsl().where(n->n.biHuanID.eq(dto.getBiHuanID())).execute();
//        jieDianSXRepository.saveAll(jieDianSX);
//        //子闭环信息
//        ziBiHXXRepository.asDeleteDsl().where(n -> n.biHuanID.eq(dto.getBiHuanID())).execute();
//        ziBiHXXRepository.saveAll(ziBiHXX);
//        //子闭环显示列
//        ziBiHXSLRepository.asDeleteDsl().where(n->n.biHuanID.eq(dto.getBiHuanID())).execute();
//        ziBiHXSLRepository.saveAll(ziBiHXSL);
//        return jieDianXX.getId();
//    }

    /**
     * 根据闭环ID获取闭环设置信息
     *
     * @param biHuanID
     * @return
     */
    @Override
    public List<BiHuanSZXXDto> getBiHuanSZXXBybiHuanID(String biHuanID, String jiGouID) {
        //节点信息
        List<BiHuanSZXXDto> jieDianXXList = jieDianXXRepository.jieDianXXList(biHuanID, jiGouID);
        //节点失效
        List<SC_BH_JieDianSXDto> biHuanSZXXList = jieDianSXRepository.biHuanSZXXList(biHuanID, jiGouID);

        //子闭环信息
        var ziBiHXXList = ziBiHXXRepository.ziBiHXXList(biHuanID, jiGouID);

        //子闭环显示列
        List<SC_BH_ZiBiHXSLDto> ziBiHXSLList = ziBiHXSLRepository.ziBiHXSLList(biHuanID, jiGouID);

        for (var a : jieDianXXList) {
            a.setZiBiHXXDto(BeanUtil.copyProperties(ziBiHXXList.stream().filter(x -> x.getJieDianID().equals(a.getJieDianID())).findFirst().orElse(null), ZiBiHXXDto.class));

            a.setJieDianSXList(BeanUtil.copyListProperties(biHuanSZXXList.stream().filter(x -> x.getJieDianID().equals(a.getJieDianID())).collect(Collectors.toList()), JieDianSXDto::new));

            a.setZiBiHXSLDtoList(BeanUtil.copyListProperties(ziBiHXSLList.stream().filter(x -> x.getJieDianID().equals(a.getJieDianID())).collect(Collectors.toList()), ZiBiHXSLDto::new));
        }
        return jieDianXXList;
    }


    @Override
    public List<JieDianSXXXDto> getJieDianSXXX(String biHuanID, String jieDianID, String jiGouID) {
        return jieDianXXRepository.byJieDianIDBiHuanID(biHuanID, jieDianID, jiGouID);
    }

    /**
     * 获取闭环信息
     */
    @Override
    public BiHuanSZXXDto getBiHuanJDNRXX(String jieDianXXID) throws WeiZhaoDSJException {

        SC_BH_JieDianXXModel jieDianSXXXDto = jieDianXXRepository.findById(jieDianXXID).orElse(null);
        //todo  直接抛出异常

        if (jieDianSXXXDto != null) {
            BiHuanSZXXDto biHuanSZXXDto = BeanUtil.copyProperties(jieDianSXXXDto, BiHuanSZXXDto.class);
            biHuanSZXXDto.setJieDianSXList(jieDianSXRepository.
                    JieDianSXList(jieDianSXXXDto.getBiHuanID(),
                            jieDianSXXXDto.getJieDianID(),
                            jieDianSXXXDto.getZuZhiJGID()));

            biHuanSZXXDto.setZiBiHXXDto(ziBiHXXRepository.ziBiHXXDto(jieDianSXXXDto.getBiHuanID(),
                    jieDianSXXXDto.getJieDianID(),
                    jieDianSXXXDto.getZuZhiJGID()));
            biHuanSZXXDto.setZiBiHXSLDtoList(ziBiHXSLRepository.ziBiHXSLDtoList(jieDianSXXXDto.getBiHuanID(),
                    jieDianSXXXDto.getJieDianID(),
                    jieDianSXXXDto.getZuZhiJGID()));
            return biHuanSZXXDto;
        } else {
            throw new WeiZhaoDSJException("未找到数据");
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public boolean zuoFeiBiHuanJDXX(String jieDianXXID) throws WeiZhaoDSJException {

        SC_BH_JieDianXXModel jieDianXXModel = jieDianXXRepository.findById(jieDianXXID).orElse(null);
        if (jieDianXXModel == null) {
            throw new WeiZhaoDSJException("未找到数据");
        }
        jieDianXXRepository.delete(jieDianXXModel);
        jieDianSXRepository.deleteByBiHuanID(jieDianXXModel.getBiHuanID(),
                jieDianXXModel.getJieDianID(),
                jieDianXXModel.getZuZhiJGID());
        ziBiHXXRepository.deleteByBiHuanID(jieDianXXModel.getBiHuanID(),
                jieDianXXModel.getJieDianID(),
                jieDianXXModel.getZuZhiJGID());
        ziBiHXSLRepository.deleteByBiHuanID(jieDianXXModel.getBiHuanID(),
                jieDianXXModel.getJieDianID(),
                jieDianXXModel.getZuZhiJGID());
        return true;
    }

    @Override
    public boolean jieDianYC(String jieDianXXID, String yinCangBZ) {
        jieDianXXRepository.jieDianYC(jieDianXXID, yinCangBZ);
        return true;
    }


}
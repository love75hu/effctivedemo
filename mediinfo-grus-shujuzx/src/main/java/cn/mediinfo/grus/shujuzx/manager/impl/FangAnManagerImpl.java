package cn.mediinfo.grus.shujuzx.manager.impl;

import cn.hutool.core.collection.ListUtil;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.*;
import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import cn.mediinfo.grus.shujuzx.common.fangan.condition.FangAnTreeNode;
import cn.mediinfo.grus.shujuzx.dto.fangan.FangAnQueryDTO;
import cn.mediinfo.grus.shujuzx.dto.fangan.FangAnSCDTO;
import cn.mediinfo.grus.shujuzx.dto.shitumx.SchemaTable;
import cn.mediinfo.grus.shujuzx.dto.shitumx.ShuJuSTDto;
import cn.mediinfo.grus.shujuzx.manager.FangAnManager;
import cn.mediinfo.grus.shujuzx.model.BR_ZD_HeBingQZPZModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnNRModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnSCModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnXXModel;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_FangAnNRRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_FangAnSCRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_FangAnXXRepository;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnXXSaveRequest;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnXXUpdateRequest;
import cn.mediinfo.grus.shujuzx.service.ShiTuMXService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import cn.mediinfo.lyra.extension.service.SequenceService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class FangAnManagerImpl implements FangAnManager {
    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private LyraIdentityService lyraIdentityService;

    @Autowired
    private SC_CX_FangAnXXRepository fangAnXXRepository;

    @Autowired
    private SC_CX_FangAnNRRepository fangAnNRRepository;

    @Autowired
    private SC_CX_FangAnSCRepository fangAnSCRepository;

    @Autowired
    private ShiTuMXService shiTuMXService;

    /**
     * 根据方案主键ID获取方案信息
     *
     * @param id
     * @return
     * @throws TongYongYWException
     */
    @Override
    public FangAnQueryDTO getFangAnXXByID(String id) throws TongYongYWException, WeiZhaoDSJException {
        SC_CX_FangAnXXModel fangAnXX = fangAnXXRepository.findById(id).orElse(null);
        if (fangAnXX==null) {
            throw new TongYongYWException("方案不存在！");
        }
        FangAnQueryDTO result = BeanUtil.copyProperties(fangAnXX, FangAnQueryDTO::new);
        zuZhuangFAXX(fangAnXX.getFangAnID(),result);

        return result;
    }

    /**
     * 根据方案ID获取方案信息
     *
     * @param fangAnId
     * @return
     * @throws TongYongYWException
     */
    @Override
    public FangAnQueryDTO getFangAnXXByFAID(String fangAnId) throws TongYongYWException, WeiZhaoDSJException {
        SC_CX_FangAnXXModel fangAnXX = fangAnXXRepository.findByFangAnID(fangAnId);
        if (ObjectUtils.isEmpty(fangAnXX)) {
            throw new TongYongYWException("方案不存在！");
        }
        FangAnQueryDTO result = BeanUtil.copyProperties(fangAnXX, FangAnQueryDTO::new);
        zuZhuangFAXX(fangAnId,result);

        return result;
    }

    /**
     * 保存方案
     *
     * @param request FangAnXXSaveRequest
     * @param sql     sql语句
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveFangAn(FangAnXXSaveRequest request, String sql) {

        String fangAnId = sequenceService.getXuHao("SC_CX_FangAnXX_FangAnID", 10);//stringGenerator.Create();

        //判断方案ID是否存在
        if (ObjectUtils.isNotEmpty(fangAnXXRepository.findByFangAnID(fangAnId))) {
            return saveFangAn(request, sql);
        }

        String zuZhiJGID = lyraIdentityService.getJiGouID();
        String zuZhiJGMC = lyraIdentityService.getJiGouMC();

        //检索方案信息
        SC_CX_FangAnXXModel fangAnXXModel = new SC_CX_FangAnXXModel();
        fangAnXXModel.setFangAnID(fangAnId);
        fangAnXXModel.setFangAnLXDM(request.getFangAnLXDM());
        fangAnXXModel.setFangAnLXMC(request.getFangAnLXMC());
        fangAnXXModel.setFangAnMC(request.getFangAnMC());
        fangAnXXModel.setGuanJianZi(request.getGuanJianZi());
        fangAnXXModel.setChaXunTJMS(request.getChaXunTJMS());
        fangAnXXModel.setZuZhiJGID(zuZhiJGID);
        fangAnXXModel.setZuZhiJGMC(zuZhiJGMC);

        //检索方案内容
        SC_CX_FangAnNRModel fangAnNRModel = new SC_CX_FangAnNRModel();
        fangAnNRModel.setFangAnID(fangAnId);
        fangAnNRModel.setFangAnMC(request.getFangAnMC());
        fangAnNRModel.setChaXunSQL(sql);
        fangAnNRModel.setChaXunTJ(JacksonUtil.getBeanToJson(request.getRoot()));
        fangAnNRModel.setZuZhiJGID(zuZhiJGID);
        fangAnNRModel.setZuZhiJGMC(zuZhiJGMC);

        //输出
        List<SC_CX_FangAnSCModel> fangAnSCModelList = ListUtil.toList();
        request.getFangAnSCList().forEach(e -> {
            SC_CX_FangAnSCModel fangAnSCModel = BeanUtil.copyProperties(e, SC_CX_FangAnSCModel.class);
            fangAnSCModel.setFangAnID(fangAnId);
            fangAnSCModel.setFangAnMC(request.getFangAnMC());
            fangAnSCModel.setZuZhiJGID(zuZhiJGID);
            fangAnSCModel.setZuZhiJGMC(zuZhiJGMC);
            fangAnSCModel.setShunXuHao(request.getFangAnSCList().indexOf(e) + 1);
            fangAnSCModelList.add(fangAnSCModel);
        });

        fangAnXXRepository.save(fangAnXXModel);
        fangAnNRRepository.save(fangAnNRModel);
        fangAnSCRepository.saveAll(fangAnSCModelList);

        return fangAnId;
    }

    /**
     * 更新方案信息
     *
     * @param request FangAnXXSaveRequest
     * @param sql     sql语句
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateFangAnXX(FangAnXXUpdateRequest request, String sql) throws TongYongYWException {
        //检索方案信息
        SC_CX_FangAnXXModel fangAnXXModel = fangAnXXRepository.findById(request.getId()).orElse(null);
        if (fangAnXXModel==null) {
            throw new TongYongYWException("方案不存在");
        }
        BeanUtil.mergeProperties(request, fangAnXXModel);

        //检索方案内容
        SC_CX_FangAnNRModel fangAnNRModel = fangAnNRRepository.findByFangAnID(fangAnXXModel.getFangAnID());
        if (ObjectUtils.isEmpty(fangAnXXModel)) {
            throw new TongYongYWException("方案内容不存在");
        }
        fangAnNRModel.setChaXunSQL(sql);
        fangAnNRModel.setChaXunTJ(JacksonUtil.getBeanToJson(request.getRoot()));

        //输出
        List<SC_CX_FangAnSCModel> oldFangAnSCModelList = fangAnSCRepository.findByFangAnID(fangAnXXModel.getFangAnID());
        if (CollectionUtils.isNotEmpty(oldFangAnSCModelList)) {
            fangAnSCRepository.deleteAllByIdInBatch(oldFangAnSCModelList.stream().map(StringMTEntity::getId).collect(Collectors.toSet()));
        }
        List<SC_CX_FangAnSCModel> fangAnSCModelList = ListUtil.toList();
        request.getFangAnSCList().forEach(e -> {
            SC_CX_FangAnSCModel fangAnSCModel = BeanUtil.copyProperties(e, SC_CX_FangAnSCModel.class);
            fangAnSCModel.setFangAnID(fangAnXXModel.getFangAnID());
            fangAnSCModel.setFangAnMC(request.getFangAnMC());
            fangAnSCModel.setZuZhiJGID(lyraIdentityService.getJiGouID());
            fangAnSCModel.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            fangAnSCModel.setShunXuHao(request.getFangAnSCList().indexOf(e) + 1);
            fangAnSCModelList.add(fangAnSCModel);
        });

        fangAnXXRepository.save(fangAnXXModel);
        fangAnNRRepository.save(fangAnNRModel);
        fangAnSCRepository.saveAll(fangAnSCModelList);

        return true;
    }

    /**
     * 组装方案信息
     * @param fangAnId
     * @param result
     */
    private void zuZhuangFAXX(String fangAnId,FangAnQueryDTO result) throws WeiZhaoDSJException {
        SC_CX_FangAnNRModel fangAnNR = fangAnNRRepository.findByFangAnID(fangAnId);
        if (ObjectUtils.isNotEmpty(fangAnNR) && StringUtil.hasText(fangAnNR.getChaXunTJ())) {
            result.setRoot(JsonUtil.getJsonToBean(fangAnNR.getChaXunTJ(), FangAnTreeNode.class));
        }
        //装载输出字段信息
        List<SC_CX_FangAnSCModel> fangAnSCModelList = fangAnSCRepository.findByFangAnID(fangAnId);
        fangAnSCModelList.sort(Comparator.comparingInt(SC_CX_FangAnSCModel::getShunXuHao));
        List<ShuJuSTDto> shuJuSTList= fangAnSCModelList.stream().filter(p->"1".equals(p.getZhiBiaoLXDM())).collect(Collectors.groupingBy(SC_CX_FangAnSCModel::getZhiBiaoFLID)).entrySet().stream().map(p->{
            ShuJuSTDto shuJuST=new ShuJuSTDto();
            shuJuST.setShiTuID(p.getKey());
            shuJuST.setZiDuanBM(p.getValue().stream().map(SC_CX_FangAnSCModel::getZhiBiaoID).distinct().collect(Collectors.toList()));
            return  shuJuST;
        }).toList();
        List<SchemaTable> schemaTableList= new ArrayList<>();
        if(!shuJuSTList.isEmpty()){
            schemaTableList = shiTuMXService.getFangAnSCZD(shuJuSTList);
        }
        List<FangAnSCDTO> fangAnSCList =new ArrayList<>();
        for(var fanAnSC : fangAnSCModelList) {
            SchemaTable schemaTable =schemaTableList.stream().filter(p->p.getShuJuJMXZDDtos().stream().anyMatch(q-> Objects.equals(Optional.ofNullable(fanAnSC.getZhiBiaoID()).orElse("").toLowerCase(),Optional.ofNullable(q.getZiDuanBM()).orElse("").toLowerCase()))).findFirst().orElse(null);
            FangAnSCDTO currentFangAnSC= BeanUtil.copyProperties(fanAnSC,FangAnSCDTO::new) ;
            if(schemaTable!=null&&"1".equals(fanAnSC.getZhiBiaoLXDM())) {
                currentFangAnSC.setMoShi(schemaTable.getMoShi());
                currentFangAnSC.setBiaoMing(schemaTable.getBiaoMing());
            }
            fangAnSCList.add(currentFangAnSC);
        }
        result.setFangAnSCList(fangAnSCList);
    }
}

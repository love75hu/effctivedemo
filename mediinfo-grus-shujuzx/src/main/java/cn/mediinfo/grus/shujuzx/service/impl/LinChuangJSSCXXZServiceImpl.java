package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.core.util.CollectionUtil;
import cn.mediinfo.grus.shujuzx.dto.shitumx.*;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.GuanLianTJZD;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ShiTuMXGXModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ShiTuMXModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ShiTuXXModel;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.LingChuangJSPZZDXXRso;
import cn.mediinfo.grus.shujuzx.remoteservice.GongYongRemoteService;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_ShiTuMXGXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_ShiTuMXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_ShiTuXXRepository;
import cn.mediinfo.grus.shujuzx.service.LinChuangJSSCXXZService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class LinChuangJSSCXXZServiceImpl implements LinChuangJSSCXXZService {
    private final SC_CX_ShiTuXXRepository sc_cx_shiTuXXRepository;
    private final SC_CX_ShiTuMXRepository sc_cx_shiTuMXRepository;
    private final SC_CX_ShiTuMXGXRepository sc_cx_shiTuMXGXRepository;
    private final GongYongRemoteService gongYongRemoteService;
    public LinChuangJSSCXXZServiceImpl(SC_CX_ShiTuXXRepository sc_cx_shiTuXXRepository, SC_CX_ShiTuMXRepository sc_cx_shiTuMXRepository, SC_CX_ShiTuMXGXRepository sc_cx_shiTuMXGXRepository, GongYongRemoteService gongYongRemoteService) {
        this.sc_cx_shiTuXXRepository = sc_cx_shiTuXXRepository;
        this.sc_cx_shiTuMXRepository = sc_cx_shiTuMXRepository;
        this.sc_cx_shiTuMXGXRepository = sc_cx_shiTuMXGXRepository;
        this.gongYongRemoteService = gongYongRemoteService;
    }
    /**
     * 综合查询输出项选择列表
     * @param yeWuLX
     * @param likeQuery
     * @param jieKouLX
     * @return
     */
    @Override
    public ShiTuSCXDto getShuTuMXForZHCX (Integer yeWuLX, Integer jieKouLX,String likeQuery) throws YuanChengException {
        ShiTuSCXDto shiTuSCXDto = new ShiTuSCXDto();
        if (jieKouLX == 1){
            shiTuSCXDto.setShuChuDto(getShuChuMXByYeWuLX(yeWuLX,1,likeQuery));
        } else if (jieKouLX == 0) {
            shiTuSCXDto.setTiaoJianDto(getShuChuMXByYeWuLX(yeWuLX,0,likeQuery));
        }else{
            shiTuSCXDto.setShuChuDto(getShuChuMXByYeWuLX(yeWuLX,1,likeQuery));
            shiTuSCXDto.setTiaoJianDto(getShuChuMXByYeWuLX(yeWuLX,0,likeQuery));
        }
        shiTuSCXDto.setShuChuBXDto(sc_cx_shiTuMXRepository.getShiTuMXByBTBZ());
        return shiTuSCXDto;
    }


    public List<ShiTuMXZHCXDto> getShuChuMXByYeWuLX(Integer yeWuLX, Integer jieKouLX,String likeQuery) throws YuanChengException{
        //获取视图信息数据
        List<SC_CX_ShiTuXXModel> shiTuXXList = sc_cx_shiTuXXRepository.getShiTuXXSJ(yeWuLX);
        List<String> shiTuIDs = shiTuXXList.stream().map(SC_CX_ShiTuXXModel::getShiTuID).toList();
        //获取视图明细数据
        List<SC_CX_ShiTuMXModel> shiTuMXList = sc_cx_shiTuMXRepository.getShiTuMXSJ(shiTuIDs,jieKouLX,likeQuery);
        //获取视图明细关联字段
        List<GuanLianTJZD> shiTuMXGXList = sc_cx_shiTuMXGXRepository.findByShiTuMXGXIDIn(shiTuIDs);
        if (shiTuMXList.isEmpty()){
            return new ArrayList<>();
        }
        //公共接口入参组装
        List<LingChuangJSPZDto> lingChuangJSPZDtos = new ArrayList<>();
        List<LingChuangJSPZDto> guanLianDtos = new ArrayList<>();
        for (SC_CX_ShiTuXXModel e :shiTuXXList) {
            LingChuangJSPZDto lingChuangJSPZDto = new LingChuangJSPZDto();
            lingChuangJSPZDto.setShuJuLYID(e.getShuJuLYID());
            lingChuangJSPZDto.setShuJuLYLXDM(e.getShuJuLYLXDM());
            lingChuangJSPZDto.setShiTuID(e.getShiTuID());
            lingChuangJSPZDto.setShiTuMC(e.getShiTuMC());
            //视图ID关联查找到明细
            List<ShuJuJMXZDDto>  list = shiTuMXList.stream().filter(t-> Objects.equals(t.getShiTuID(),e.getShiTuID())).map(t -> {
                ShuJuJMXZDDto shuJuJMXZDDto = new ShuJuJMXZDDto();
                shuJuJMXZDDto.setZiDuanBM(t.getZiDuanBM());
                shuJuJMXZDDto.setZiDuanMC(t.getZiDuanMC());
                return shuJuJMXZDDto;
            }).toList();
            var copyList = BeanUtil.copyListProperties(list.stream().toList(), ShuJuJMXZDDto::new);
            var guanLianZDS = copyList.stream().map(ShuJuJMXZDDto::getZiDuanBM).toList();
            //追加关联表字段
            var guanLianMXList = shiTuMXGXList.stream().filter(t->t.getShiTuID().equals(e.getShiTuID()) && guanLianZDS.contains(t.getZiDuanBM())).toList();
            if(!guanLianMXList.isEmpty()){
                LingChuangJSPZDto guanLianDto = new LingChuangJSPZDto();
                guanLianDto.setShuJuLYID(e.getShuJuLYID());
                guanLianDto.setShuJuLYLXDM(e.getShuJuLYLXDM());
                guanLianDto.setShiTuID(e.getShiTuID());
                guanLianDto.setShiTuMC(e.getShiTuMC());
                guanLianDtos.add(guanLianDto);
                List<ShuJuJMXZDDto> guanLianZDList = new ArrayList<>();
                for (var glmx :guanLianMXList){
                    ShuJuJMXZDDto shuJuJMXZDDto = new ShuJuJMXZDDto();
                    shuJuJMXZDDto.setZiDuanBM(glmx.getGuanLianZDBM());
                    shuJuJMXZDDto.setZiDuanMC(glmx.getGuanLianZDMC());
                    guanLianZDList.add(shuJuJMXZDDto);
                }
                guanLianDto.setShuJuJMXZDDtos(guanLianZDList);
            }
            //存在则添加公共入参
            if (!copyList.isEmpty()){
                //存在相同数据来源ID则合并去重
                var cunZaiDto = lingChuangJSPZDtos.stream().filter(t->t.getShuJuLYID().equals(e.getShuJuLYID()) && t.getShuJuLYLXDM().equals(e.getShuJuLYLXDM())).findFirst().orElse(null);
                if (cunZaiDto != null){
                    var cunZaiZDMX = cunZaiDto.getShuJuJMXZDDtos();
                    var quanBuZDMX = Stream.concat(cunZaiZDMX.stream(),copyList.stream()).toList();
                    cunZaiDto.setShuJuJMXZDDtos(CollectionUtil.removeDuplicateObjects(quanBuZDMX, ShuJuJMXZDDto::getZiDuanBM));
                }
                else
                {
                    lingChuangJSPZDto.setShuJuJMXZDDtos(copyList);
                    lingChuangJSPZDtos.add(lingChuangJSPZDto);
                }
            }
        }
        if (lingChuangJSPZDtos.isEmpty()){
            return new ArrayList<>();
        }
        //获取公共接口数据
        List<LingChuangJSPZZDXXRso> lingChuangJSPZZDList = gongYongRemoteService.getlingChuangJSPZZDXX(lingChuangJSPZDtos).getData("远程调用接口getlingChuangJSPZZDXX错误！");
        List<LingChuangJSPZZDXXRso> guanLianZZDList = gongYongRemoteService.getlingChuangJSPZZDXX(guanLianDtos).getData("远程调用接口getlingChuangJSPZZDXX错误！");
        if (ObjectUtils.isEmpty(lingChuangJSPZZDList)){
            return new ArrayList<>();
        }
        List<ShiTuMXZHCXDto> resultlist = new ArrayList<>();
        for (SC_CX_ShiTuXXModel e :shiTuXXList) {
            var cunZaiJSPZ = lingChuangJSPZZDList.stream().filter(t->t.getShuJuLYID().equals(e.getShuJuLYID()) && t.getShuJuLYLXDM().equals(e.getShuJuLYLXDM())).findFirst().orElse(null);
            if(cunZaiJSPZ != null){
                var cunZaiZDBM = shiTuMXList.stream().filter(t-> Objects.equals(t.getShiTuID(),e.getShiTuID())).map(SC_CX_ShiTuMXModel::getZiDuanBM).toList();
                List<ShuJuJMXZDDto> gongGongZDMX = cunZaiJSPZ.getShiTuMXZDDtos().stream().filter(t->cunZaiZDBM.contains(t.getZiDuanBM())).toList();
                var copyGongGongZDMX = BeanUtil.copyListProperties(gongGongZDMX.stream().toList(), ShuJuJMXZDDto::new);
                if(!copyGongGongZDMX.isEmpty()){
                    for ( ShuJuJMXZDDto zdmxDto : copyGongGongZDMX) {
                        zdmxDto.setShiTuID(e.getShiTuID());
                        zdmxDto.setShiTuMC(e.getShiTuMC());
                        var guanLianZDMXXX = guanLianZZDList.stream().filter(t-> Objects.equals(t.getShiTuID(), e.getShiTuID()) ).findFirst().orElse(null);
                        if (guanLianZDMXXX != null)
                        {
                            var shiTuGLMX = shiTuMXGXList.stream().filter(t-> Objects.equals(t.getShiTuID(), e.getShiTuID()) && Objects.equals(t.getZiDuanBM(), zdmxDto.getZiDuanBM())).toList();
                            if (!shiTuGLMX.isEmpty()){
                                var ziDuanMBS = shiTuGLMX.stream().map(GuanLianTJZD::getGuanLianZDBM).toList();
                                var shiTuMXZDList = guanLianZDMXXX.getShiTuMXZDDtos().stream().filter(t->ziDuanMBS.contains(t.getZiDuanBM())).toList();
                                zdmxDto.setGuanLianTJZDList(shiTuMXZDList);
                            }
                        }
                    }
                    var cunZaiDto = resultlist.stream().filter(t->t.getFuLeiID().equals(e.getFuLeiID())).findFirst().orElse(null);
                    //存在则合并
                    if(cunZaiDto == null){
                        ShiTuMXZHCXDto shiTuMXZHCXDto = new ShiTuMXZHCXDto();
                        shiTuMXZHCXDto.setFuLeiID(e.getFuLeiID());
                        shiTuMXZHCXDto.setFuLeiMC(e.getFuLeiMC());
                        shiTuMXZHCXDto.setZiDuanList(copyGongGongZDMX);
                        resultlist.add(shiTuMXZHCXDto);
                    }else {
                        var cunZaiZDMX = cunZaiDto.getZiDuanList();
                        var quanBuZDMX = Stream.concat(cunZaiZDMX.stream(),copyGongGongZDMX.stream()).toList();//合并字段
                        cunZaiDto.setZiDuanList(CollectionUtil.removeDuplicateObjects(quanBuZDMX, ShuJuJMXZDDto::getZiDuanBM));//根据字段编码去重
                    }
                }
            }
        }
        return resultlist ;
    }
}

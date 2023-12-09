package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
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
import io.swagger.v3.oas.annotations.media.Schema;
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


    @Override
    public  ShiTuSCXDto getShuTuMXForZHCX (Integer yeWuLX, Integer jieKouLX,String likeQuery) throws YuanChengException {
        ShiTuSCXDto shiTuSCXDto = new ShiTuSCXDto();
        //获取视图信息数据
        List<SC_CX_ShiTuXXModel> shiTuXXList = sc_cx_shiTuXXRepository.getShiTuXXSJ(yeWuLX);
        List<String> shiTuIDs = shiTuXXList.stream().map(SC_CX_ShiTuXXModel::getShiTuID).toList();
        //获取视图明细数据
        List<SC_CX_ShiTuMXModel> shiTuMXList = sc_cx_shiTuMXRepository.getShiTuMXSJ(shiTuIDs,jieKouLX,likeQuery);
        //获取视图明细关联字段
        List<GuanLianTJZD> shiTuMXGXList = sc_cx_shiTuMXGXRepository.findByShiTuMXGXIDIn(shiTuIDs);
        if (shiTuMXList.isEmpty()){
            throw new YuanChengException("视图明细为空请检查配置!");
        }
        //公共接口入参组装
        List<LingChuangJSPZDto> lingChuangJSPZDtos = new ArrayList<>();
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
            var copyList = BeanUtil.copyListProperties(list.stream().toList(), ShuJuJMXZDDto::new);//拷贝一个LIST
            //追加关联表字段
            var guanLianZDS = copyList.stream().map(ShuJuJMXZDDto::getZiDuanBM).toList();
            var guanLianMXList = shiTuMXGXList.stream().filter(t->t.getShiTuID().equals(e.getShiTuID()) && guanLianZDS.contains(t.getZiDuanBM())).toList();
            if(!guanLianMXList.isEmpty()){
                List<ShuJuJMXZDDto> guanLianZDList = new ArrayList<>();
                for (var glmx :guanLianMXList){
                    ShuJuJMXZDDto shuJuJMXZDDto = new ShuJuJMXZDDto();
                    shuJuJMXZDDto.setZiDuanBM(glmx.getGuanLianZDBM());
                    shuJuJMXZDDto.setZiDuanMC(glmx.getGuanLianZDMC());
                    guanLianZDList.add(shuJuJMXZDDto);
                }
                copyList =Stream.concat(copyList.stream(),guanLianZDList.stream()).toList();//合并关联字段
            }
            //存在则添加公共入参
            if (!copyList.isEmpty()){
                var cunZaiDto = lingChuangJSPZDtos.stream().filter(t->t.getShuJuLYID().equals(e.getShuJuLYID()) && t.getShuJuLYLXDM().equals(e.getShuJuLYLXDM())).findFirst().orElse(null);
                //不存在则添加
                if (cunZaiDto == null){
                    lingChuangJSPZDto.setShuJuJMXZDDtos(copyList);
                    lingChuangJSPZDtos.add(lingChuangJSPZDto);
                }
                else
                {
                    //存在相同数据来源ID则合并去重
                    var cunZaiZDMX = cunZaiDto.getShuJuJMXZDDtos();
                    var quanBuZDMX = Stream.concat(cunZaiZDMX.stream(),copyList.stream()).toList();//存在合并处理
                    cunZaiDto.setShuJuJMXZDDtos(CollectionUtil.removeDuplicateObjects(quanBuZDMX, ShuJuJMXZDDto::getZiDuanBM));//合并要去重
                }
            }
        }
        if (lingChuangJSPZDtos.isEmpty()){
            throw new YuanChengException("视图明细远程接口入参为空请检查配置!");
        }
        //获取公共接口数据
        List<LingChuangJSPZZDXXRso> lingChuangJSPZZDList = gongYongRemoteService.getlingChuangJSPZZDXX(lingChuangJSPZDtos).getData("远程调用接口getlingChuangJSPZZDXX错误！");
        //组装前端输出

        List<ShiTuMXZHCXDto> shuChuDto = new ArrayList<>();
        List<ShiTuMXZHCXDto> tiaoJianDto= new ArrayList<>();
        List<ShuJuJMXZDDto> shuChuBXDto= new ArrayList<>();

        if (jieKouLX == 1){
            shuChuDto = getShiTuMXZHCX(shiTuXXList,shiTuMXList.stream().filter(t->Objects.equals(t.getShuChuBZ(),1)).toList(),lingChuangJSPZZDList,shiTuMXGXList);
        } else if (jieKouLX == 0) {
            tiaoJianDto = getShiTuMXZHCX(shiTuXXList,shiTuMXList.stream().filter(t->Objects.equals(t.getTiaoJianBZ(),1)).toList(),lingChuangJSPZZDList,shiTuMXGXList);
        }else{
            shuChuDto = getShiTuMXZHCX(shiTuXXList,shiTuMXList.stream().filter(t->Objects.equals(t.getShuChuBZ(),1)).toList(),lingChuangJSPZZDList,shiTuMXGXList);
            tiaoJianDto = getShiTuMXZHCX(shiTuXXList,shiTuMXList.stream().filter(t->Objects.equals(t.getTiaoJianBZ(),1)).toList(),lingChuangJSPZZDList,shiTuMXGXList);
        }

        //赋值输出必选字段
        var shuChuBXList = shiTuMXList.stream().filter(t->Objects.equals(t.getShuChuBXBZ(),1)).toList();
        for (var item :lingChuangJSPZZDList){
            for (var scmx :item.getShiTuMXZDDtos()){
                if (!shuChuBXList.stream().filter(t-> Objects.equals(t.getZiDuanBM(), scmx.getZiDuanBM()) && Objects.equals(t.getShiTuID(), scmx.getShiTuID())).toList().isEmpty()){
                    shuChuBXDto.add(scmx);
                }
            }
        }
        shiTuSCXDto.setShuChuDto(shuChuDto);
        shiTuSCXDto.setTiaoJianDto(tiaoJianDto);
        shiTuSCXDto.setShuChuBXDto(shuChuBXDto);
        return shiTuSCXDto;
    }

    public List<ShiTuMXZHCXDto> getShiTuMXZHCX (List<SC_CX_ShiTuXXModel> shiTuXXList,List<SC_CX_ShiTuMXModel> shiTuMXList,List<LingChuangJSPZZDXXRso> lingChuangJSPZZDList,List<GuanLianTJZD> shiTuMXGXList){
        List<ShiTuMXZHCXDto> resultList = new ArrayList<>();
        for (SC_CX_ShiTuXXModel e :shiTuXXList) {
            var shiTuMXByShiTuIdList = shiTuMXList.stream().filter(t-> Objects.equals(t.getShiTuID(), e.getShiTuID())).toList();
            var dto = lingChuangJSPZZDList.stream().filter(t-> Objects.equals(t.getShiTuID(), e.getShiTuID()) && t.getShuJuLYID().equals(e.getShuJuLYID()) && t.getShuJuLYLXDM().equals(e.getShuJuLYLXDM())).findFirst().orElse(null);
            if (dto != null && !shiTuMXByShiTuIdList.isEmpty()){
                ShiTuMXZHCXDto shiTuMXZHCXDto = new ShiTuMXZHCXDto();
                var mingXiZDBMList = shiTuMXByShiTuIdList.stream().map(SC_CX_ShiTuMXModel::getZiDuanBM).toList();//视图信息的字段编码
                shiTuMXZHCXDto.setFuLeiID(e.getFuLeiID());
                shiTuMXZHCXDto.setFuLeiMC(e.getFuLeiMC());
                var list =dto.getShiTuMXZDDtos();//远程返回的数据
                var copyList = BeanUtil.copyListProperties(list, ShuJuJMXZDDto::new).stream().filter(t->mingXiZDBMList.contains(t.getZiDuanBM())).toList();//远程返回的数据拷贝一个list 并过滤明细
                var guanLianZDList = shiTuMXGXList.stream().filter(t-> Objects.equals(t.getShiTuID(), e.getShiTuID())).toList();//查找关联字段
                if (!guanLianZDList.isEmpty()){
                    for (var copy : copyList){//赋值关联字段
                        var guanLianZDBMs = guanLianZDList.stream().filter(t-> Objects.equals(t.getZiDuanBM(), copy.getZiDuanBM())).map(GuanLianTJZD::getGuanLianZDBM).toList();
                        var guanLianList = list.stream().filter(t->guanLianZDBMs.contains(t.getZiDuanBM())).toList();
                        copy.setGuanLianTJZDList(guanLianList);
                    }
                }
                shiTuMXZHCXDto.setZiDuanList(copyList);
                resultList.add(shiTuMXZHCXDto);
            }
        }
        return  resultList;
    }
}

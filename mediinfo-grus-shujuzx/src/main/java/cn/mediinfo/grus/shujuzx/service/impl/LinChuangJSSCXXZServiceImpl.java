package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.util.CollectionUtil;
import cn.mediinfo.grus.shujuzx.dto.shitumx.*;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ShiTuMXModel;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ShiTuXXModel;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.LingChuangJSPZZDXXRso;
import cn.mediinfo.grus.shujuzx.remoteservice.GongYongRemoteService;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_ShiTuMXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_ShiTuXXRepository;
import cn.mediinfo.grus.shujuzx.service.LinChuangJSSCXXZService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class LinChuangJSSCXXZServiceImpl implements LinChuangJSSCXXZService {
    private final SC_CX_ShiTuXXRepository sc_cx_shiTuXXRepository;
    private final SC_CX_ShiTuMXRepository sc_cx_shiTuMXRepository;
    private final GongYongRemoteService gongYongRemoteService;
    public LinChuangJSSCXXZServiceImpl(SC_CX_ShiTuXXRepository sc_cx_shiTuXXRepository, SC_CX_ShiTuMXRepository sc_cx_shiTuMXRepository, GongYongRemoteService gongYongRemoteService) {
        this.sc_cx_shiTuXXRepository = sc_cx_shiTuXXRepository;
        this.sc_cx_shiTuMXRepository = sc_cx_shiTuMXRepository;
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
    public List<ShiTuMXZHCXDto> getShuTuMXForZHCX (Integer yeWuLX, Integer jieKouLX,String likeQuery) throws YuanChengException {
        //获取视图信息数据
        List<SC_CX_ShiTuXXModel> shiTuXXList = sc_cx_shiTuXXRepository.getShiTuXXSJ(yeWuLX);
        List<String> shiTuIDs = shiTuXXList.stream().map(SC_CX_ShiTuXXModel::getShiTuID).toList();
        //获取视图明细数据
        List<SC_CX_ShiTuMXModel> shiTuMXList = sc_cx_shiTuMXRepository.getShiTuMXSJ(shiTuIDs,jieKouLX,likeQuery);
        if (shiTuMXList.isEmpty()){
            return new ArrayList<>();
        }
        //公共接口入参组装
        List<LingChuangJSPZDto> lingChuangJSPZDtos = new ArrayList<>();
        for (SC_CX_ShiTuXXModel e :shiTuXXList) {
            LingChuangJSPZDto lingChuangJSPZDto = new LingChuangJSPZDto();
            lingChuangJSPZDto.setShuJuLYID(e.getShuJuLYID());
            lingChuangJSPZDto.setShuJuLYLXDM(e.getShuJuLYLXDM());
            //视图ID关联查找到明细
            List<ShuJuJMXZDDto>  list = shiTuMXList.stream().filter(t-> Objects.equals(t.getShiTuID(),e.getShiTuID())).map(t -> {
                ShuJuJMXZDDto shuJuJMXZDDto = new ShuJuJMXZDDto();
                shuJuJMXZDDto.setZiDuanBM(t.getZiDuanBM());
                shuJuJMXZDDto.setZiDuanMC(t.getZiDuanMC());
                return shuJuJMXZDDto;
            }).toList();
            //存在则添加公共入参
            if (!list.isEmpty()){
                //存在相同数据来源ID则合并去重
                var cunZaiDto = lingChuangJSPZDtos.stream().filter(t->t.getShuJuLYID().equals(e.getShuJuLYID())).findFirst().orElse(null);
                if (cunZaiDto != null){
                    var cunZaiZDMX = cunZaiDto.getShuJuJMXZDDtos();
                    var quanBuZDMX = Stream.concat(cunZaiZDMX.stream(),list.stream()).toList();
                    cunZaiDto.setShuJuJMXZDDtos(CollectionUtil.removeDuplicateObjects(quanBuZDMX, ShuJuJMXZDDto::getZiDuanBM));
                }
                else
                {
                    lingChuangJSPZDto.setShuJuJMXZDDtos(list);
                    lingChuangJSPZDtos.add(lingChuangJSPZDto);
                }
            }
        }
        if (lingChuangJSPZDtos.isEmpty()){
            return new ArrayList<>();
        }
        //获取公共接口数据
        List<LingChuangJSPZZDXXRso> lingChuangJSPZZDList = gongYongRemoteService.getlingChuangJSPZZDXX(lingChuangJSPZDtos).getData("远程调用接口getlingChuangJSPZZDXX错误！");
        if (ObjectUtils.isEmpty(lingChuangJSPZZDList)){
            return new ArrayList<>();
        }
        List<ShiTuMXZHCXDto> resultlist = new ArrayList<>();
        for (SC_CX_ShiTuXXModel e :shiTuXXList) {
            var cunZaiJSPZ = lingChuangJSPZZDList.stream().filter(t->t.getShuJuLYID().equals(e.getShuJuLYID())).findFirst().orElse(null);
            if(cunZaiJSPZ != null){
                var cunZaiZDBM = shiTuMXList.stream().filter(t-> Objects.equals(t.getShiTuID(),e.getShiTuID())).map(SC_CX_ShiTuMXModel::getZiDuanBM).toList();
                List<ShiTuZDMXDto> gongGongZDMX = cunZaiJSPZ.getShiTuMXZDDtos().stream().filter(t->cunZaiZDBM.contains(t.getZiDuanBM())).toList();
                if(!gongGongZDMX.isEmpty()){
                    for ( ShiTuZDMXDto zdmxDto : gongGongZDMX) {
                        zdmxDto.setShiTuID(e.getShiTuID());
                        zdmxDto.setShiTuMC(e.getShiTuMC());
                    }
                    var cunZaiDto = resultlist.stream().filter(t->t.getFuLeiID().equals(e.getFuLeiID())).findFirst().orElse(null);
                    //存在则合并
                    if(cunZaiDto == null){
                        ShiTuMXZHCXDto shiTuMXZHCXDto = new ShiTuMXZHCXDto();
                        shiTuMXZHCXDto.setFuLeiID(e.getFuLeiID());
                        shiTuMXZHCXDto.setFuLeiMC(e.getFuLeiMC());
                        shiTuMXZHCXDto.setZiDuanList(gongGongZDMX);
                        resultlist.add(shiTuMXZHCXDto);
                    }else {
                        var cunZaiZDMX = cunZaiDto.getZiDuanList();
                        var quanBuZDMX = Stream.concat(cunZaiZDMX.stream(),gongGongZDMX.stream()).toList();
                        cunZaiDto.setZiDuanList(CollectionUtil.removeDuplicateObjects(quanBuZDMX, ShiTuZDMXDto::getZiDuanBM));
                    }
                }
            }
        }
        return resultlist ;
    }
}

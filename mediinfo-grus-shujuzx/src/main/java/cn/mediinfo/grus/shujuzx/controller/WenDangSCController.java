package cn.mediinfo.grus.shujuzx.controller;
import cn.hutool.core.collection.ListUtil;
import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.grus.shujuzx.constant.WenDangSJLYConstant;
import cn.mediinfo.grus.shujuzx.constant.YaSuoFSConstant;
import cn.mediinfo.grus.shujuzx.dto.cda.gongwei.DA_GA_JiBenXXDto;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangDto;
import cn.mediinfo.cyan.msf.security.annotation.AnonymousAccess;
import cn.mediinfo.grus.shujuzx.dto.wendangjls.SC_GW_JiLuXXCreateDto;
import cn.mediinfo.grus.shujuzx.dto.wendangjls.SC_GW_JiLuXXSCDto;
import cn.mediinfo.grus.shujuzx.dto.wendangnrs.SC_GW_JiLuNRDto;
import cn.mediinfo.grus.shujuzx.dto.wendangscs.WenDangSCQueryDto;
import cn.mediinfo.grus.shujuzx.dto.wendangscs.WenDangSC_JZXXDto;
import cn.mediinfo.grus.shujuzx.dto.wendangscs.WenDangSJJDto;
import cn.mediinfo.grus.shujuzx.dto.wendangscs.WenDangSLDto;
import cn.mediinfo.grus.shujuzx.remoteservice.JiuZhenRemoteService;
import cn.mediinfo.grus.shujuzx.service.ICDADocService;
import cn.mediinfo.grus.shujuzx.service.WenDangJLXXService;
import cn.mediinfo.grus.shujuzx.service.WenDangPZService;
import cn.mediinfo.grus.shujuzx.service.impl.cda.B0001ServiceImpl;
import cn.mediinfo.grus.shujuzx.service.WenDangSCService;
import cn.mediinfo.grus.shujuzx.utils.GZIPUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@Tag(name = "WenDangSCController", description = "文档生成")
@RequestMapping({"api/v1.0/WenDangSC", "api/v1/WenDangSC"})
@Slf4j
@Validated
public class WenDangSCController implements  BeanFactoryAware {
    private BeanFactory beanFactory;
    private final WenDangJLXXService wenDangJLXXService;
    private  final JiuZhenRemoteService jiuZhenRemoteService;
    private  final WenDangSCService wenDangSCService;
    private final WenDangPZService wenDangPZService;
    public WenDangSCController(WenDangJLXXService wenDangJLXXService,WenDangPZService wenDangPZService,JiuZhenRemoteService jiuZhenRemoteService,WenDangSCService wenDangSCService) {
        this.wenDangJLXXService=wenDangJLXXService;
        this.wenDangPZService = wenDangPZService;
        this.jiuZhenRemoteService=jiuZhenRemoteService;
        this.wenDangSCService=wenDangSCService;
    }
    
    @Operation(summary = "生成共享文档")
    @PostMapping("shengChengWDXML")
    public MsfResponse<Long> shengChengWDXML(@RequestBody SC_GW_JiLuXXSCDto dto) throws JAXBException, TongYongYWException {
        if(dto.getMpiList().isEmpty())
        {
            throw  new TongYongYWException("参数mpilist不能为空");
        }
        //获取文档配置
        var wenDangXX=wenDangPZService.getWenDangXXByWDID(dto.getWenDangID());
        ICDADocService cdaProc = beanFactory.getBean(dto.getWenDangID(), ICDADocService.class);
        cdaProc.setMPIList(dto.getMpiList());
        cdaProc.setWenDangMC(wenDangXX.getWenDangMC());
        cdaProc.setWenDangID(dto.getWenDangID());
        cdaProc.GetData();
        List<SC_GW_JiLuXXCreateDto> jiLuXXCreateDtoList= ListUtil.toList();
        switch (dto.getWenDangID()) {
            case "B0001":
                B0001ServiceImpl B0001 = (B0001ServiceImpl) cdaProc;
                SC_GW_JiLuXXCreateDto jiLuXXCreateDto=genJianKangDACdaXml(cdaProc,B0001.jiBenXXDto,B0001.jiBenXXDto.getJianDangSJ(),B0001.jiBenXXDto.getId());
                jiLuXXCreateDtoList.add(jiLuXXCreateDto);
                break;
            case "B0002":
                break;
            default:
                break;
        }
        dto.setJiLuXXList(jiLuXXCreateDtoList);
        wenDangJLXXService.addWenDangJLXX(dto);
        return MsfResponse.success(jiLuXXCreateDtoList.stream().count());
    }

    private SC_GW_JiLuXXCreateDto genJianKangDACdaXml(ICDADocService cdaProc, DA_GA_JiBenXXDto jiBenXX, Date yeWuSJ, String yeWuZJID) throws JAXBException {
        SC_GW_JiLuXXCreateDto jiLuXXCreateDto=new SC_GW_JiLuXXCreateDto();
        jiLuXXCreateDto.setChuShengRQ(jiBenXX.getChuShengRQ());//出生日期
        jiLuXXCreateDto.setXingBieDM(jiBenXX.getXingBieDM());//性别代码
        jiLuXXCreateDto.setXingBieMC(jiBenXX.getXingBieMC());//性别名称
        jiLuXXCreateDto.setBingRenID(jiBenXX.getMPI());//病人id
        jiLuXXCreateDto.setXingMing(jiBenXX.getXingMing());//姓名
        jiLuXXCreateDto.setZhengJianLXDM(jiBenXX.getZhengJianLBDM());//证件类别
        jiLuXXCreateDto.setZhengJianLXMC(jiBenXX.getZhengJianLBMC());//证件类别名称
        jiLuXXCreateDto.setZhengJianHM(jiBenXX.getZhengJianHM());//证件号码
        jiLuXXCreateDto.setShuJuLYDM(WenDangSJLYConstant.SHOUDONG);//数据来源代码
        jiLuXXCreateDto.setShuJuLYMC(WenDangSJLYConstant.SHOUDONGMC);//数据来源名称
        jiLuXXCreateDto.setWenDangID(cdaProc.getWenDangID());
        jiLuXXCreateDto.setWenDangMC(cdaProc.getWenDangMC());
        jiLuXXCreateDto.setYeWuSJ(yeWuSJ);
        jiLuXXCreateDto.setYeWuZJID(yeWuZJID);
        jiLuXXCreateDto.setShengChengSJ(new Date());
        SC_GW_JiLuNRDto jiLuNRDto=new SC_GW_JiLuNRDto();
        jiLuNRDto.setWenDangID(cdaProc.getWenDangID());
        jiLuNRDto.setWenDangMC(cdaProc.getWenDangMC());
        jiLuNRDto.setNeiRong(GZIPUtils.compressData(assembleDictionaryDoc(cdaProc)));
        jiLuNRDto.setYaSuoFSDM(YaSuoFSConstant.zip);//压缩方式
        jiLuNRDto.setYaSuoFSMC(YaSuoFSConstant.zip_mc);
        jiLuXXCreateDto.setJiLuNRDto(jiLuNRDto);
        return  jiLuXXCreateDto;
    }

    private String assembleDictionaryDoc(ICDADocService cdaProc) throws JAXBException {
        if (cdaProc == null) return  "";
        cdaProc.GeneDOC();
        var xml=cdaProc.getXml();
        return  cdaProc.getXml();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
       this.beanFactory=beanFactory;
    }

    /**
     * 获取文档生成健康档案列表
     *
     * @param mpi
     * @return
     */
    @Operation(summary = "获取文档生成健康档案列表")
    @PostMapping("GetWenDangSCJKDAList")
    public MsfResponse<List<WenDangSLDto>> GetWenDangSCJKDAList(String mpi) throws TongYongYWException, YuanChengException, MsfResponseException {
        if (StringUtil.notHasText(mpi)) {
            return MsfResponse.success();
        }
        return MsfResponse.success(jiuZhenRemoteService.GetWenDangSCList(mpi).getData());
    }

    /**
     * 获取电子病历类型列表
     *
     * @return List<HuanZhe360_JZXX_Item>
     * @throws TongYongYWException 通用异常
     */
    @Operation(summary = "获取电子病历类型列表")
    @GetMapping("getJiuZhenXXListByBRXX")
    public MsfResponse<List<WenDangSC_JZXXDto>> getJiuZhenXXListByBRXX(@RequestParam(required = false) String mpi) throws TongYongYWException, WeiZhaoDSJException, YuanChengException {
        if (StringUtil.notHasText(mpi)) {
            return MsfResponse.success();
        }
        return MsfResponse.success(jiuZhenRemoteService.getJiuZhenXXListByBRXX(mpi).getData());
    }

    /**
     * 获取电子病历类型数量
     *
     * @return
     * @throws TongYongYWException 通用异常
     */
    @Operation(summary = "获取电子病历类型数量")
    @GetMapping("getJiuZhenXXCountByBRXX")
    public MsfResponse<Integer> getJiuZhenXXCountByBRXX(@RequestParam(required = false) String mpi) throws TongYongYWException, WeiZhaoDSJException, YuanChengException {
        if (StringUtil.notHasText(mpi)) {
            return MsfResponse.success();
        }
        return MsfResponse.success(jiuZhenRemoteService.getJiuZhenXXCountByBRXX(mpi).getData());
    }

    /**
     * 生成共享文档数据集
     *
     * @return
     * @throws TongYongYWException 通用异常
     */
    @Operation(summary = "生成共享文档数据集")
    @GetMapping("getGongXiangWDSJJ")
    public MsfResponse<List<WenDangSJJDto>> getGongXiangWDSJJ(WenDangSCQueryDto dto) throws IOException, TongYongYWException, WeiZhaoDSJException, YuanChengException, NoSuchFieldException, IllegalAccessException {
        if(Objects.isNull(dto)||StringUtil.notHasText(dto.getMpi())||StringUtil.notHasText(dto.getWenDangLXDM()))
        {
            return MsfResponse.success();
        }
        return MsfResponse.success(wenDangSCService.getGongXiangWDSJJ(dto));
    }
    /**
     * 导出文档数据集
     *
     * @param
     * @return
     */
    @Operation(summary = "导出文档数据集")
    @GetMapping("ExportWenDangSJJ")
    @AnonymousAccess
    public void ExportWenDangSJJ(
            HttpServletResponse response,
            @RequestParam(required = false) String wenDangLXDM,String mpi) throws IOException, NoSuchFieldException, IllegalAccessException, YuanChengException {
        var dto=new WenDangSCQueryDto();
        dto.setWenDangLXDM(wenDangLXDM);
        dto.setMpi(mpi);
        wenDangSCService.exportWenDangSJJ(response,dto);
    }
}

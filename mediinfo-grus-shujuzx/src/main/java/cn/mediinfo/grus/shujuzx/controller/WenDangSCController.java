package cn.mediinfo.grus.shujuzx.controller;
import cn.hutool.core.collection.ListUtil;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.cda.dangan.DA_GA_JiBenXXDto;
import cn.mediinfo.grus.shujuzx.dto.wendangjls.SC_GW_JiLuXXCreateDto;
import cn.mediinfo.grus.shujuzx.dto.wendangjls.SC_GW_JiLuXXSCDto;
import cn.mediinfo.grus.shujuzx.dto.wendangnrs.SC_GW_JiLuNRDto;
import cn.mediinfo.grus.shujuzx.service.ICDADocService;
import cn.mediinfo.grus.shujuzx.service.WenDangJLXXService;
import cn.mediinfo.grus.shujuzx.service.impl.B0001ServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@Tag(name = "WenDangSCController", description = "文档生成")
@RequestMapping({"api/v1.0/WenDangSC", "api/v1/WenDangSC"})
@Slf4j
@Validated
public class WenDangSCController implements  BeanFactoryAware {
    private BeanFactory beanFactory;
    private final WenDangJLXXService wenDangJLXXService;
    public WenDangSCController(WenDangJLXXService wenDangJLXXService) {
        this.wenDangJLXXService=wenDangJLXXService;
    }
    /*

     */
    @Operation(summary = "生成共享文档")
    @PostMapping("shengChengWDXML")
    public MsfResponse<Long> shengChengWDXML(SC_GW_JiLuXXSCDto dto) throws JAXBException, TongYongYWException {
        String DocType="B0001";

        ICDADocService cdaProc = beanFactory.getBean(DocType, ICDADocService.class);
        cdaProc.GetData();
        List<SC_GW_JiLuXXCreateDto> jiLuXXCreateDtoList= ListUtil.toList();
        switch (dto.getWenDangLX()) {
            case "B0001":
                B0001ServiceImpl B0001 = (B0001ServiceImpl) cdaProc;
                var a=B0001.jiBenXXDto;
                SC_GW_JiLuXXCreateDto jiLuXXCreateDto=genJianKangDACdaXml(cdaProc,B0001.jiBenXXDto);
                jiLuXXCreateDto.setYeWuSJ(B0001.jiBenXXDto.getJianDangSJ());
                jiLuXXCreateDto.setYeWuZJID(B0001.jiBenXXDto.getId());
                jiLuXXCreateDtoList.add(jiLuXXCreateDto);
                break;
            case "B0002":
                break;
            default:
                break;
        }
        wenDangJLXXService.addWenDangJLXX(dto);
        return MsfResponse.success(jiLuXXCreateDtoList.stream().count());
    }

    private SC_GW_JiLuXXCreateDto genJianKangDACdaXml(ICDADocService cdaProc,DA_GA_JiBenXXDto jiBenXX) throws JAXBException {
        SC_GW_JiLuXXCreateDto jiLuXXCreateDto=new SC_GW_JiLuXXCreateDto();
        jiLuXXCreateDto.setChuShengRQ(jiBenXX.getChuShengRQ());//出生日期
        jiLuXXCreateDto.setXingBieDM(jiBenXX.getXingBieDM());//性别代码
        jiLuXXCreateDto.setXingBieMC(jiBenXX.getXingBieMC());//性别名称
        jiLuXXCreateDto.setBingRenID(jiBenXX.getMPI());//病人id
        jiLuXXCreateDto.setXingMing(jiBenXX.getXingMing());//姓名
        jiLuXXCreateDto.setXingMing(jiBenXX.getXingMing());//姓名
        jiLuXXCreateDto.setZhengJianLXDM(jiBenXX.getZhengJianLBDM());//证件类别
        jiLuXXCreateDto.setZhengJianLXMC(jiBenXX.getZhengJianLBMC());//证件类别名称
        jiLuXXCreateDto.setZhengJianHM(jiBenXX.getZhengJianHM());//证件号码
        jiLuXXCreateDto.setZhengJianLXDM(jiBenXX.getZhengJianLBDM());//证件类别
        jiLuXXCreateDto.setShuJuLYDM("");//数据来源代码
        jiLuXXCreateDto.setShuJuLYMC("");//数据来源名称

        SC_GW_JiLuNRDto jiLuNRDto=new SC_GW_JiLuNRDto();
        jiLuNRDto.setWenDangID("");
        jiLuNRDto.setWenDangMC("");
        jiLuNRDto.setNeiRong(assembleDictionaryDoc(cdaProc));
        jiLuNRDto.setYaSuoFSDM("");//压缩方式
        jiLuNRDto.setYaSuoFSMC("");//压缩方式

        return  jiLuXXCreateDto;


    }

    private String assembleDictionaryDoc(ICDADocService cdaProc) throws JAXBException {
        if (cdaProc == null) return  "";
        cdaProc.AssembleData();
        cdaProc.GeneDOC();
        return  cdaProc.getXml();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
       this.beanFactory=beanFactory;
    }
}

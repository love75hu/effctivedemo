package cn.mediinfo.grus.shujuzx.controller;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.service.ICDADocService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "WenDangXMLController", description = "")
@RequestMapping({"api/v1.0/WenDangXML", "api/v1/WenDangXML"})
@Slf4j
@Validated
public class WenDangXMLController implements  BeanFactoryAware {
    private BeanFactory beanFactory;
    public WenDangXMLController(HttpServletRequest request) {

    }

    @Operation(summary = "")
    @PostMapping("shengChengWDXML")
    public MsfResponse<String> shengChengWDXML() throws JAXBException {
        String DocType="B0001";
        ICDADocService cdaProc = beanFactory.getBean(DocType, ICDADocService.class);
        cdaProc.GetData();
        var xml= assembleDictionaryDoc(cdaProc);
        return MsfResponse.success(xml);
    }

    private String assembleDictionaryDoc(ICDADocService cdaProc) throws JAXBException {
        if (cdaProc == null) return  "";
        cdaProc.AssembleData();
        cdaProc.GeneDOC();
        return  cdaProc.getXml();


    }

    @Override
    public void setBeanFactory(org.springframework.beans.factory.BeanFactory beanFactory) throws BeansException {
       this.beanFactory=beanFactory;
    }
}

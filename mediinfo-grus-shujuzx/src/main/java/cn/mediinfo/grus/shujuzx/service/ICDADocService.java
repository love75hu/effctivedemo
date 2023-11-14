package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.cda.gongwei.DA_GA_BaoLuShiDto;
import jakarta.xml.bind.JAXBException;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.util.List;

public interface ICDADocService {
    /// <summary>
    /// 获取数据
    /// </summary>
    void GetData();
    void GeneDOC();
    String getXml() throws JAXBException;
    List<String> mpiList=null;
    String title=null;
    void setMPIList(List<String> mpiList);
    List<String> getMPIList();
    void setTitle(String title);
    String getTitle() ;

}

package cn.mediinfo.grus.shujuzx.service;

import jakarta.xml.bind.JAXBException;

public interface ICDADocService {

    public  String Title = null;

    public  String WenDangID = null;
    /// <summary>
    /// 获取数据
    /// </summary>
    void GetData();
    /// <summary>
    /// 组装数据
    /// </summary>
    void AssembleData();
    void GeneDOC();
    String getXml() throws JAXBException;



}

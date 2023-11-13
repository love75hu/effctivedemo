package cn.mediinfo.grus.shujuzx.service;

import jakarta.xml.bind.JAXBException;

public interface ICDADocService {

    public  String Title = null;

    public  String WenDangID = null;
    /// <summary>
    /// 获取数据
    /// </summary>
    void GetData();
    void GeneDOC();
    String getXml() throws JAXBException;



}

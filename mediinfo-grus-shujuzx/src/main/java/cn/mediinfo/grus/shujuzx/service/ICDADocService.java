package cn.mediinfo.grus.shujuzx.service;

import jakarta.xml.bind.JAXBException;

public interface ICDADocService {

    public  String Title = null;

    public  String WenDangID = null;
    /// <summary>
    /// ��ȡ����
    /// </summary>
    void GetData();
    /// <summary>
    /// ��װ����
    /// </summary>
    void AssembleData();
    void GeneDOC();
    String getXml() throws JAXBException;



}

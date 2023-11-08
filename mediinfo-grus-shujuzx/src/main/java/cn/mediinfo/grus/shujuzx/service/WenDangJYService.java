package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.wendangjys.VerifyXMLDto;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface WenDangJYService {
    String VerifyCDADoc(VerifyXMLDto dto) throws ParserConfigurationException, IOException, SAXException;
}

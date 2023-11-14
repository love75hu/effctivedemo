package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.hl7.*;
import cn.mediinfo.grus.shujuzx.service.ICDADocService;
import io.micrometer.common.util.StringUtils;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;
import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class CDADocBase implements ICDADocService {
    /// <summary>
    /// 获取数据
    /// </summary>
    public abstract void DoGetData();
    /// <summary>
    /// 根据获取数据生成文档
    /// </summary>
    public abstract void DoGenDoc();
    public String title;
    public String wenDangID;
    List<String> mpiList=null;
    public void setMPIList(List<String> mpi) {
      this.mpiList=mpi;
    }
    public List<String> getMPIList() {
        return  this.mpiList;
    }
    public void setTitle(String title) {
        this.title=title;
    }
    public String getTitle() {
        return  this.title;
    }
    public POCDMT000040ClinicalDocument clinicalDOC = new POCDMT000040ClinicalDocument();
    public void GetData()
    {
        this.DoGetData();
    }


    public void GeneDOC()
    {
        GeneGy();
        DoGenHead();
        DoGenDoc();
    }

    public void GeneGy()
    {
        CS cs=new CS();
        cs.setCode("CN");
        clinicalDOC.getRealmCode().add(cs);
        POCDMT000040InfrastructureRootTypeId typeId=new POCDMT000040InfrastructureRootTypeId();
        typeId.setRoot("2.16.840.1.113883.1.3");
        typeId.setExtension("POCD_MT000040");
        clinicalDOC.setTypeId(typeId);
        II templateId_ii=new II();
        templateId_ii.setRoot("2.16.156.10011.2.1.1.1");
        clinicalDOC.getTemplateId().add(templateId_ii);
        II ii=new II();
        ii.setRoot(UUID.randomUUID().toString());
        ii.setExtension("D2011000001");
        clinicalDOC.setId(ii);

        CE ce=new CE();
        ce.setCodeSystem("2.16.156.10011.2.4");
        ce.setCodeSystemName("卫生信息共享文档规范编码体系");
        ce.setCode(this.wenDangID);
        clinicalDOC.setCode(ce);

        //生成时间
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = now.format(formatter);
        ST st=new ST();
        st.setLanguage(this.title);
        clinicalDOC.setTitle(st);
        TS ts=new TS();
        ts.setValue(formattedDateTime);
        clinicalDOC.setEffectiveTime(ts);
        CE ce_bm=new CE();
        ce_bm.setCode("N");
        ce_bm.setDisplayName("正常访问保密级别");
        ce_bm.setCodeSystem("2.16.840.1.113883.5.25");
        ce_bm.setCodeSystemName("Confidentiality");
        clinicalDOC.setConfidentialityCode(ce);
        CS cs_zh=new CS();
        cs_zh.setCode("zh-CN");
        clinicalDOC.setLanguageCode(cs_zh);
        clinicalDOC.setId(new II());
        clinicalDOC.setVersionNumber(new INT());
    }

    public  void DoGenHead()
    {

    }


    public POCDMT000040Component3 AppendComponent(String lonicNum, String displayName)
    {
        POCDMT000040Component3 component3=new POCDMT000040Component3();
        POCDMT000040Section section=new POCDMT000040Section();
        CE ce=new CE();
        ce.setCode(lonicNum);
        ce.setDisplayName(displayName);
        ce.setCodeSystem("2.16.840.1.113883.6.1");
        ce.setCodeSystemName("LOINC");
        section.setCode(ce);
        section.setText(new StrucDocText());
        component3.setSection(section);
        return component3;
    }

    public POCDMT000040Component4 AppendComponent(POCDMT000040Organizer entry, String code , String displayName)
    {
        POCDMT000040Component4 component4=new POCDMT000040Component4();
        POCDMT000040Observation observation=new POCDMT000040Observation();
        observation.getClassCode().set(0,"OBS");
        observation.setMoodCode(XActMoodDocumentObservation.EVN);
        CD cd=new CD();
        cd.setCode("DE04.50.001.00");
        cd.setCodeSystem("2.16.156.10011.2.2.1");
        cd.setCodeSystem("2.16.156.10011.2.3.1.85");
        cd.setCodeSystemName("卫生信息数据元目录");
        observation.setCode(cd);
        CD cd1=new CD();
        cd.setCode("1");
        cd.setDisplayName("A型");
        cd.setCodeSystem("2.16.156.10011.2.3.1.85");
        cd.setCodeSystemName("ABO血型代码表");
        observation.getValue().set(0,cd);
        component4.setObservation(observation);
        entry.getComponent().add(component4);
        return component4;
    }

    public POCDMT000040Observation appendOrganizerObservation_B0001(POCDMT000040Organizer organizer, String code, String displayName,
                                                         String codeSystem, String codeSystemName)
    {
        if (StringUtils.isNotEmpty(codeSystem)) {
            codeSystem="2.16.156.10011.2.2.1";
        }
        if (StringUtils.isNotEmpty(codeSystemName)) {
            codeSystemName="卫生信息数据元目录";
        }
        POCDMT000040Component4 component4=new POCDMT000040Component4();
        POCDMT000040Observation observation=new POCDMT000040Observation();
        observation.getClassCode().add("OBS");
        observation.setMoodCode(XActMoodDocumentObservation.EVN);
        CD cd=new CD();
        cd.setCode("");
        cd.setCodeSystem(codeSystem);
        cd.setCodeSystemName(codeSystemName);
        observation.setCode(cd);
        component4.setObservation(observation);
        organizer.getComponent().add(component4);

       /* if (!string.IsNullOrEmpty(code))
        {
            var shuJuJi = _sysDataCache.GetShuJuJiByCode(code + DocType);
            component4.AsObservation.Code.Code = shuJuJi != null ? shuJuJi.SHUJUYUANBSF : "-";
            component4.AsObservation.Code.CodeSystem = codeSystem;
            component4.AsObservation.Code.CodeSystemName = codeSystemName;
        }*/
        return component4.getObservation();
    }

    public CD newCD(String neiBuBSF, String codeSystem, String code)
    {
        CD cd=new CD();
        cd.setCode("");
        cd.setDisplayName("");
        cd.setCodeSystem(codeSystem);
        cd.setCodeSystemName("");
        // var cda_ShuJuYZY = _sysDataCache.GetShuJuYZYForNewCD(codeSystem, code);
       // cd.Code = cda_ShuJuYZY?.Code?.Replace("?","+");//存在ICD10中的+读取后变成特殊符号“?”
       // cd.DisplayName = cda_ShuJuYZY?.DisplayName;
       // cd.CodeSystem = codeSystem;
       // cd.CodeSystemName = cda_ShuJuYZY?.CodeSystemName;
        return cd;
    }

    public  String getXml () throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(clinicalDOC.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(clinicalDOC, stringWriter);
        return stringWriter.toString();
    }

}

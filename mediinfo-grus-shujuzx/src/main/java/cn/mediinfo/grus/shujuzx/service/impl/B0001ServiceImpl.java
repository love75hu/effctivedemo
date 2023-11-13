package cn.mediinfo.grus.shujuzx.service.impl;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.grus.shujuzx.constant.ComponentTypeConstant;
import cn.mediinfo.grus.shujuzx.dto.cda.gongwei.DA_GA_BaoLuShiDto;
import cn.mediinfo.grus.shujuzx.dto.cda.gongwei.DA_GA_GuoMinShiDto;
import cn.mediinfo.grus.shujuzx.dto.cda.gongwei.DA_GA_JiBenXXDto;
import cn.mediinfo.grus.shujuzx.hl7.*;
import cn.mediinfo.grus.shujuzx.remoteservice.GongWeiRemoteService;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.List;

@Service("B0001")
public  class B0001ServiceImpl extends CDADocBase  {
    public DA_GA_JiBenXXDto jiBenXXDto;
    private final GongWeiRemoteService gongWeiRemoteService;
    public   ObjectFactory objectFactory;
    //过敏史
    public List<DA_GA_GuoMinShiDto> guoMinShiList;
    //暴露史
    public List<DA_GA_BaoLuShiDto> baoLuShiList;

    public B0001ServiceImpl(GongWeiRemoteService gongWeiRemoteService){
        this.gongWeiRemoteService=gongWeiRemoteService;
    }

    public  void DoGetData()
    {
        jiBenXXDto= gongWeiRemoteService.getB0001Data(this.mpiList).getData();
    }

    public  void DoGenHead()  {
        POCDMT000040RecordTarget target=new POCDMT000040RecordTarget();
        target.setContextControlCode("OP");
        target.getTypeCode().add("RCT");
        clinicalDOC.getRecordTarget().add(target);
        POCDMT000040PatientRole PatientRole=new POCDMT000040PatientRole();

        PatientRole.getClassCode().add("PAT");
        var ii=new II();
        PatientRole.getId().add(ii);
        ii.setExtension(jiBenXXDto.getJianKangDABH());//健康档案编号
        ii.setRoot("2.16.156.10011.1.2");

        //家庭地址
        var ad=new AD();
        ad.getUse().add("H");
        objectFactory.createAD();
        AdxpHouseNumber houseNumber=new AdxpHouseNumber();
        houseNumber.setLanguage("wwwww");
        ad.getContent().add(houseNumber.toString());
       // ad.getUse()..add(0,"13131414");
        PatientRole.getAddr().add(ad);

        //电话号码
        var tel=new TEL();
        tel.setValue(jiBenXXDto.getDianHuaHM());
        PatientRole.getTelecom().add(tel);
        //
        POCDMT000040Patient patient=new POCDMT000040Patient();
        patient.getClassCode().add("PSN");
        patient.setDeterminerCode("INSTANCE");
        II ii2=new II();
        ii2.setExtension(jiBenXXDto.getZhengJianHM());//证件号码
        ii2.setRoot("2.16.156.10011.1.3");
        patient.setId(ii2);

        PN pn=new PN();
        pn.getContent().add(jiBenXXDto.getXingMing());//姓名
        patient.getName().add(pn);
        //性别
        CE ce=new CE();
        ce.setCode("0");
        ce.setDisplayName("未知的性别");
        if (!StringUtil.notHasText(jiBenXXDto.getXingBieDM())  && !StringUtil.notHasText(jiBenXXDto.getXingBieMC()))
        {
            ce.setCode(jiBenXXDto.getXingBieDM());
            ce.setDisplayName(jiBenXXDto.getXingBieMC());
        }
        ce.setCodeSystem("2.16.156.10011.2.3.3.4");
        ce.setCodeSystemName("生理性别代码表(GB/T 2261.1)");
        patient.setAdministrativeGenderCode(ce);

        //出生日期
        TS ts=new TS();
        ts.setValue("20231110");
        patient.setBirthTime(ts);

        //婚姻状况
        CE hy=new CE();
        hy.setCode("90");
        hy.setDisplayName("未说明的婚姻状况");
        hy.setCodeSystem("2.16.156.10011.2.3.3.5");
        hy.setCodeSystemName("婚姻状况代码表(GB/T 2261.2)");
        patient.setMaritalStatusCode(hy);

        //民族类别
        CE mz=new CE();
        mz.setCode("01");
        mz.setDisplayName("汉族");
        mz.setCodeSystem("2.16.156.10011.2.3.3.3");
        mz.setCodeSystemName("民族类别代码表(GB 3304)");
        patient.setEthnicGroupCode(mz);

        //工作单位
        ON on=new ON();
        on.getContent().add("hhh");
        PCHISEmployerorganization pchisEmployerorganization=new PCHISEmployerorganization();
        pchisEmployerorganization.setName(on);
        patient.setEmployerOrganization(pchisEmployerorganization);

        //常住类型
        BL bl=new BL();
        bl.setValue(true);
        PCHISHouseholdregistrationplace pchisHouseholdregistrationplace=new PCHISHouseholdregistrationplace();
        pchisHouseholdregistrationplace.getHouseType().add(bl);
        patient.setHousehold(pchisHouseholdregistrationplace);

        //文化程度
        PCHISEducationlevel pchisEducationlevel=new PCHISEducationlevel();
        CE xl=new CE();
        xl.setCode("");
        xl.setDisplayName("");
        xl.setCodeSystem("2.16.156.10011.2.3.3.6");
        xl.setCodeSystemName("学历代码表(GB/T 4658)");
        pchisEducationlevel.setEducationLevelCode(xl);
        patient.setEducationLevel(pchisEducationlevel);

        //职业状况
        PCHISOccupation pchisOccupation=new PCHISOccupation();
        CE zy=new CE();
        zy.setCode("");
        zy.setDisplayName("");
        zy.setCodeSystem("2.16.156.10011.2.3.3.7");
        zy.setCodeSystemName("职业类别代码表(GB/T 6565)");
        pchisOccupation.setOccupationCode(zy);
        patient.setOccupation(pchisOccupation);
        PatientRole.setPatient(patient);
        target.setPatientRole(PatientRole);

        //-文档创作时间
        POCDMT000040Author author=new POCDMT000040Author();
        author.getTypeCode().add("AUT");
        author.setContextControlCode("OP");
        TS scts=new TS();
        scts.setValue("");
        author.setTime(scts);
        POCDMT000040AssignedAuthor assignedAuthor=new POCDMT000040AssignedAuthor();
        assignedAuthor.setClassCode("ASSIGNED");
        II assignedAuthor_ii=new II();
        assignedAuthor_ii.setExtension("234234234");
        assignedAuthor_ii.setRoot("2.16.156.10011.1.7");
        assignedAuthor.getId().add(assignedAuthor_ii);
        pn.getContent().set(0,"wy");
        POCDMT000040Person pocdmt000040Person =new POCDMT000040Person();
        pocdmt000040Person.getName().add(pn);
        assignedAuthor.setAssignedPerson(pocdmt000040Person);
        POCDMT000040Organization pocdmt000040Organization=new POCDMT000040Organization();
        II author_ii=new II();
        author_ii.setExtension("2.16.156.10011.1.5");
        author_ii.setRoot("");
        pocdmt000040Organization.getId().add(author_ii);
        ON author_on=new ON();
        author_on.getContent().add("xx医院");
        pocdmt000040Organization.getName().add(author_on);
        AD author_ad=new AD();
        author_ad.getContent().add("街道");
        pocdmt000040Organization.getAddr().add(author_ad);
        assignedAuthor.setRepresentedOrganization(pocdmt000040Organization);
        author.setAssignedAuthor(assignedAuthor);
        clinicalDOC.getAuthor().add(author);

        //保管机构信息
        POCDMT000040Custodian custodian=new POCDMT000040Custodian();
        custodian.getTypeCode().add("CST");
        POCDMT000040AssignedCustodian assignedCustodian=new POCDMT000040AssignedCustodian();
        assignedCustodian.setClassCode("ASSIGNED");
        POCDMT000040CustodianOrganization custodianOrganization=new POCDMT000040CustodianOrganization();
        custodianOrganization.setClassCode("ORG");
        custodianOrganization.setDeterminerCode("INSTANCE");
        II custonian_ii=new II();
        custonian_ii.setExtension("");
        custonian_ii.setRoot("2.16.156.10011.1.6");
        custodianOrganization.getId().add(custonian_ii);
        ON custonian_on=new ON();
        custonian_on.getContent().add("卫生局健康档案管理中心");
        custodianOrganization.setName(custonian_on);
        //机构联系电话
        TEL custodian_tel=new TEL();
        custodian_tel.setValue("");
        custodianOrganization.setTelecom(custodian_tel);
        AD custodian_ad=new AD();
        custodian_ad.getContent().add("jiiii");
        custodianOrganization.setAddr(custodian_ad);
        assignedCustodian.setRepresentedCustodianOrganization(custodianOrganization);
        custodian.setAssignedCustodian(assignedCustodian);
        clinicalDOC.setCustodian(custodian);

        //联系人
        POCDMT000040Participant1 participant1=new POCDMT000040Participant1();
        participant1.getTypeCode().add("NOT");
        POCDMT000040AssociatedEntity associatedEntity=new POCDMT000040AssociatedEntity();
        associatedEntity.getClassCode().add("ECON");
        TEL participant_tel=new TEL();
        participant_tel.setValue("15067120953");
        associatedEntity.getTelecom().add(participant_tel);
        POCDMT000040Person participant_pocdmt000040Person=new POCDMT000040Person();
        PN participan_pn=new PN();
        participan_pn.getContent().add("张三");
        participant_pocdmt000040Person.getName().add(participan_pn);
        associatedEntity.setAssociatedPerson(participant_pocdmt000040Person);
        participant1.setAssociatedEntity(associatedEntity);
        clinicalDOC.getParticipant().add(participant1);

        //版本
        POCDMT000040RelatedDocument relatedDocument=new POCDMT000040RelatedDocument();
        relatedDocument.setTypeCode(XActRelationshipDocument.RPLC);
        POCDMT000040ParentDocument parentDocument=new POCDMT000040ParentDocument();
        parentDocument.setClassCode(ActClinicalDocument.DOCCLIN);
        parentDocument.getMoodCode().add("EVN");
        II relatedDocument_ii=new II();
        relatedDocument_ii.setRoot("2.16.156.10011.1.1.2");
        relatedDocument_ii.setExtension("D2011000000");
        parentDocument.getId().add(relatedDocument_ii);
        parentDocument.setSetId(new II());
        INT parentDocument_int=new INT();
        parentDocument_int.setValue(BigInteger.ONE);
        parentDocument.setVersionNumber(parentDocument_int);
        relatedDocument.setParentDocument(parentDocument);
        clinicalDOC.getRelatedDocument().add(relatedDocument);
    }

    public  void DoGenDoc() {
        POCDMT000040Component2 component2=new POCDMT000040Component2();
        POCDMT000040StructuredBody structuredBody=new POCDMT000040StructuredBody();

        //实验室检查章节
        var componentSYSJC = this.AppendComponent(ComponentTypeConstant.ShiYanSJC, "STUDIES SUMMARY");
        this.CreateShiYanSJC(componentSYSJC);

        structuredBody.getComponent().add(componentSYSJC);
        component2.setStructuredBody(structuredBody);
        clinicalDOC.setComponent(component2);
    }

    public void CreateShiYanSJC(POCDMT000040Component3 component)
    {
        //血型
        POCDMT000040Section section=component.getSection();
        POCDMT000040Entry entry=new POCDMT000040Entry();
        entry.setTypeCode(XActRelationshipEntry.COMP);
        entry.setContextConductionInd(true);

        POCDMT000040Organizer organizer=new POCDMT000040Organizer();
        organizer.setClassCode(XActClassDocumentEntryOrganizer.BATTERY);
        organizer.getMoodCode().add("EVN");
        organizer.setStatusCode(new CS());
        entry.setOrganizer(organizer);
        section.getEntry().add(entry);

        var organizer1 = entry.getOrganizer();
        //ABO血型
        var entry0 = this.appendOrganizerObservation_B0001(organizer1, "HDSD00.01.013","","","");
        entry0.getCode().setDisplayName("ABO血型代码表");
        var value0 = this.newCD("HDSD00.01.013", "2.16.156.10011.2.3.1.85", "GeRenJKDA.ABODM");
        entry0.getValue().add(value0);
        //Rh血型
        var entry1 = this.appendOrganizerObservation_B0001(organizer1, "HDSD00.01.014","","","");
        entry1.getCode().setDisplayName("Rh(D)血型代码表");
        var value1 = this.newCD("HDSD00.01.014", "2.16.156.10011.2.3.1.250", "GeRenJKDA.RHDM");
        entry1.getValue().add(value1);
    }


}

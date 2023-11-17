package cn.mediinfo.grus.shujuzx.service.impl.cda;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.grus.shujuzx.constant.ComponentTypeConstant;
import cn.mediinfo.grus.shujuzx.dto.cda.gongwei.*;
import cn.mediinfo.grus.shujuzx.hl7.cda.*;
import cn.mediinfo.grus.shujuzx.remoteservice.GongWeiRemoteService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRefs;
import org.springframework.stereotype.Service;

import javax.xml.namespace.QName;
import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("B0001")
public  class B0001ServiceImpl extends CDAJCServiceImpl {
    public DA_GA_JiBenXXDto jiBenXXDto;
    private final GongWeiRemoteService _gongWeiRemoteService;
    //过敏史
    public List<DA_GA_GuoMinShiDto> guoMinShiList;
    //暴露史
    public List<DA_GA_BaoLuShiDto> baoLuShiList;
    public List<DA_GA_CanJiQKDto> canJiQKList;
    public List<DA_GA_JiaZuShiDto> jiaZuShiList;
    public List<DA_GA_JiBingShiDto> jiBingShiList;
    public List<DA_GA_ShouShuShiDto> shouShuShiList;
    public List<DA_GA_WaiShangShiDto> waiShangShiList;
    public List<DA_GA_ShuXueShiDto> shuXueShiList;

    public B0001ServiceImpl(GongWeiRemoteService gongWeiRemoteService){
        this._gongWeiRemoteService=gongWeiRemoteService;
    }

    public  void DoGetData()
    {
        jiBenXXDto= _gongWeiRemoteService.getB0001Data(getMPIList()).getData();
        guoMinShiList=jiBenXXDto.getGuoMinShi()==null? new ArrayList<>():jiBenXXDto.getGuoMinShi();
        baoLuShiList=jiBenXXDto.getBaoLuShi()==null? new ArrayList<>():jiBenXXDto.getBaoLuShi();
        canJiQKList=jiBenXXDto.getCanJiQK()==null? new ArrayList<>():jiBenXXDto.getCanJiQK();
        jiaZuShiList=jiBenXXDto.getJiaZuShi()==null? new ArrayList<>():jiBenXXDto.getJiaZuShi();
        jiBingShiList=jiBenXXDto.getJiBingShi()==null? new ArrayList<>():jiBenXXDto.getJiBingShi();
        shouShuShiList=jiBenXXDto.getShouShuShi()==null? new ArrayList<>():jiBenXXDto.getShouShuShi();
        shuXueShiList=jiBenXXDto.getShuXueShi()==null? new ArrayList<>():jiBenXXDto.getShuXueShi();
        waiShangShiList=jiBenXXDto.getWaiShangShi()==null? new ArrayList<>():jiBenXXDto.getWaiShangShi();
    }

    public  String getHouseNumberXML (AdxpHouseNumber houseNumber) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(houseNumber.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(houseNumber, stringWriter);
        return stringWriter.toString();
    }

    public  void DoGenHead() {
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

        AdxpHouseNumber houseNumber=new AdxpHouseNumber();
        houseNumber.getPartType().add("1111");

      //  var a=getHouseNumberXML(houseNumber);
       // ad.getContent().add(a);
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
        ts.setValue(dateTimeToString(jiBenXXDto.getChuShengRQ(),1));
        patient.setBirthTime(ts);

        //婚姻状况
        CE hy=new CE();
        hy.setCode("90");
        hy.setDisplayName("未说明的婚姻状况");
        if (!StringUtil.notHasText(jiBenXXDto.getHunYinDM())  && !StringUtil.notHasText(jiBenXXDto.getHunYinMC()))
        {
            hy.setCode(jiBenXXDto.getHunYinDM());
            hy.setDisplayName(jiBenXXDto.getHunYinMC());
        }
        hy.setCodeSystem("2.16.156.10011.2.3.3.5");
        hy.setCodeSystemName("婚姻状况代码表(GB/T 2261.2)");
        patient.setMaritalStatusCode(hy);

        //民族类别
        CE mz=new CE();
        mz.setCode("01");
        mz.setDisplayName("汉族");
        if (!StringUtil.notHasText(jiBenXXDto.getMinZuDM())  && !StringUtil.notHasText(jiBenXXDto.getMinZuMC()))
        {
            mz.setCode(jiBenXXDto.getMinZuDM());
            mz.setDisplayName(jiBenXXDto.getMinZuMC());
        }
        mz.setCodeSystem("2.16.156.10011.2.3.3.3");
        mz.setCodeSystemName("民族类别代码表(GB 3304)");
        patient.setEthnicGroupCode(mz);

        //工作单位
        ON on=new ON();
        on.getContent().add(jiBenXXDto.getGongZuoDW());
        PCHISEmployerorganization pchisEmployerorganization=new PCHISEmployerorganization();
        pchisEmployerorganization.setName(on);
        patient.setEmployerOrganization(pchisEmployerorganization);

        //常住类型
        BL bl=new BL();
        bl.setValue(jiBenXXDto.getChangZhuLXDM().equals("1")?true:false);
        PCHISHouseholdregistrationplace pchisHouseholdregistrationplace=new PCHISHouseholdregistrationplace();
        pchisHouseholdregistrationplace.getHouseType().add(bl);
        patient.setHousehold(pchisHouseholdregistrationplace);

        //文化程度
        PCHISEducationlevel pchisEducationlevel=new PCHISEducationlevel();
        CE xl=new CE();
        xl.setCode("");
        xl.setDisplayName("");
        if (!StringUtil.notHasText(jiBenXXDto.getXueLiDM())  && !StringUtil.notHasText(jiBenXXDto.getXueLiMC()))
        {
            xl.setCode(jiBenXXDto.getXueLiDM());
            xl.setDisplayName(jiBenXXDto.getXueLiMC());
        }
        xl.setCodeSystem("2.16.156.10011.2.3.3.6");
        xl.setCodeSystemName("学历代码表(GB/T 4658)");
        pchisEducationlevel.setEducationLevelCode(xl);
        patient.setEducationLevel(pchisEducationlevel);

        //职业状况
        PCHISOccupation pchisOccupation=new PCHISOccupation();
        CE zy=new CE();
        zy.setCode("");
        zy.setDisplayName("");
        if (!StringUtil.notHasText(jiBenXXDto.getZhiYeDM())  && !StringUtil.notHasText(jiBenXXDto.getZhiYeMC()))
        {
            zy.setCode(jiBenXXDto.getZhiYeDM());
            zy.setDisplayName(jiBenXXDto.getZhiYeMC());
        }
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
        //建档时间
        scts.setValue(dateTimeToString(jiBenXXDto.getJianDangSJ(),1) );
        author.setTime(scts);
        POCDMT000040AssignedAuthor assignedAuthor=new POCDMT000040AssignedAuthor();
        assignedAuthor.setClassCode("ASSIGNED");
        II assignedAuthor_ii=new II();
        //建档医生
        assignedAuthor_ii.setExtension(jiBenXXDto.getJianDangYSID());
        assignedAuthor_ii.setRoot("2.16.156.10011.1.7");
        assignedAuthor.getId().add(assignedAuthor_ii);
        PN pn_author=new PN();
        //建档医生姓名
        pn_author.getContent().add(jiBenXXDto.getJianDangYSXM());
        POCDMT000040Person pocdmt000040Person =new POCDMT000040Person();
        pocdmt000040Person.getName().add(new PN());
        assignedAuthor.setAssignedPerson(pocdmt000040Person);
        POCDMT000040Organization pocdmt000040Organization=new POCDMT000040Organization();
        II author_ii=new II();
        author_ii.setExtension("2.16.156.10011.1.5");
        author_ii.setRoot(jiBenXXDto.getJianDangJGID());//建档机构代码
        pocdmt000040Organization.getId().add(author_ii);
        ON author_on=new ON();
        author_on.getContent().add(jiBenXXDto.getJianDangJGMC());
        pocdmt000040Organization.getName().add(author_on);
        AD author_ad=new AD();
        //建档机构地址
        author_ad.getContent().add("");
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
        //联系人电话
        participant_tel.setValue(jiBenXXDto.getLianXiRDH());
        associatedEntity.getTelecom().add(participant_tel);
        POCDMT000040Person participant_pocdmt000040Person=new POCDMT000040Person();
        PN participan_pn=new PN();
        participan_pn.getContent().add(jiBenXXDto.getLianXiRXM());
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

        //费用章节
        var componentFY = this.AppendComponent(ComponentTypeConstant.FeiYong, "PAYMENT SOURCES");
        this.createFeiYong(componentFY);
        structuredBody.getComponent().add(componentFY);

        //药物过敏史章节
        var componentYWGMS = this.AppendComponent(ComponentTypeConstant.GuoMingShi, "Allergies, adverse reactions, alerts");
        this.createYaoWuGMS(componentYWGMS);
        structuredBody.getComponent().add(componentYWGMS);

        //职业暴露史章节
        var componentZYBLS = this.AppendComponent(ComponentTypeConstant.ZhiYeBLS, "HISTORY OF OCCUPATIONAL EXPOSURE");
        this.createZhiYeBLS(componentZYBLS);
        structuredBody.getComponent().add(componentZYBLS);

        //既往史章节
        var componentJWS = this.AppendComponent(ComponentTypeConstant.JiWangShi, "HISTORY OF PAST ILLNESS");
        this.createJiWangShi(componentJWS);
        structuredBody.getComponent().add(componentJWS);

        //家族史章节
        var componentJZS = this.AppendComponent(ComponentTypeConstant.JiaZuShi, "HISTORY OF FAMILY MEMBER DISEASES");
        this.createJiaZuShi(componentJZS);
        structuredBody.getComponent().add(componentJZS);

        //遗传病史章节
        POCDMT000040Component3 componentYCBS=new POCDMT000040Component3();
        this.createYiChuanBS(componentYCBS);
        structuredBody.getComponent().add(componentYCBS);

        //残疾史章节
        var componentCJS = this.AppendComponent(ComponentTypeConstant.CanJiShi, "History of functional status");
        this.createCanJiShi(componentCJS);
        structuredBody.getComponent().add(componentCJS);

        //生活环境章节
        var componentSHHJ = new POCDMT000040Component3();
        this.createShengHuoHJ(componentSHHJ);
        structuredBody.getComponent().add(componentSHHJ);

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
        var value0 = this.newCD("HDSD00.01.013", "2.16.156.10011.2.3.1.85", "GeRenJKDA.ABODM");
        entry0.getValue().add(value0);
        //Rh血型
        var entry1 = this.appendOrganizerObservation_B0001(organizer1, "HDSD00.01.014","","","");
        var value1 = this.newCD("HDSD00.01.014", "2.16.156.10011.2.3.1.250", "GeRenJKDA.RHDM");
        entry1.getValue().add(value1);
    }

    //费用章节
    public void createFeiYong(POCDMT000040Component3 component)
    {
        //医疗付款方式代码
        var entry0 = this.appendObservation(component, "HDSD00.01.018","");
        var value0 = this.newCD("HDSD00.01.018", "2.16.156.10011.2.3.1.197", jiBenXXDto.getYiLiaoFYZFFSDM());
        entry0.getValue().add(value0);
    }

    public void createYaoWuGMS(POCDMT000040Component3 component)
    {
        guoMinShiList.forEach(x -> {
            //药物过敏史标志
            var entry0 = this.appendObservation(component, "HDSD00.01.019","");
            var value0 = this.newBL(true);
            entry0.getValue().add(value0);
            //过敏源代码
            var entryRelationship0 = this.appendObservationRelationship(entry0, "HDSD00.01.020","");
            IVLTS ivlts=new IVLTS();
            ivlts.setValue(""); //过敏时间 目前表中没这个字段
            entryRelationship0.getObservation().setEffectiveTime(ivlts);
            var value1 = this.newCD("HDSD00.01.020", "2.16.156.10011.2.3.1.137", x.getGuoMinYDM());
            entryRelationship0.getObservation().getValue().add(value1);
;        });
    }

    public void createZhiYeBLS(POCDMT000040Component3 component)
    {
        baoLuShiList.forEach(item -> {
            //职业暴露史
            var entry0 = this.appendObservation(component, "HDSD00.01.021","");
            var value0 = this.newCD("HDSD00.01.021", "2.16.156.10011.2.3.1.33", item.getBaoLuSLBDM());
            entry0.getValue().add(value0);
        });
    }

    public void createJiWangShi(POCDMT000040Component3 component)
    {
        jiBingShiList.forEach(item -> {
            //疾病史
            //既往患病种类代码
            var entry0 = this.appendObservation(component, "HDSD00.01.022","");
            var value0 = this.newCD("HDSD00.01.022", "2.16.156.10011.2.3.1.12", item.getJiBingZLDM());
            entry0.getEffectiveTime().setValue(dateTimeToString(item.getQueZhenRQ(),1));
            entry0.getValue().add(value0);
        });
        //手术史标志
        shouShuShiList.forEach(item -> {
            //手术史标志
            var entry0 = this.appendObservation(component, "HDSD00.01.024","");
            entry0.getEffectiveTime().setValue(dateTimeToString(item.getShouShuRQ(),1));
            var value0 = this.newBL(true);
            entry0.getValue().add(value0);
            //手术史
            var entryRelationship0 = this.appendObservationRelationship(entry0, "HDSD00.01.025","");
            var value2 = this.newST(item.getShouShuMC());
            entryRelationship0.getObservation().getValue().add(value2);
        });
        //外伤史标志
        waiShangShiList.forEach(item -> {
            var entry2 = this.appendObservation(component, "HDSD00.01.027","");
            entry2.getCode().setDisplayName("外伤史标志");
            entry2.getEffectiveTime().setValue(dateTimeToString(item.getFaShengRQ(),1));
            var value0 = this.newBL(true);
            entry2.getValue().add(value0);
            //外伤史
            var entryRelationship1 = this.appendObservationRelationship(entry2, "HDSD00.01.028","");
            var value4 = this.newST(item.getWaiShangMC());
            entryRelationship1.getObservation().getValue().add(value4);
        });
        //输血史标志
        shuXueShiList.forEach(item -> {
            var entry3 = this.appendObservation(component, "HDSD00.01.030","");
            entry3.getEffectiveTime().setValue(dateTimeToString(item.getShuXueRQ(),1));;
            var value5 = this.newBL(true);
            entry3.getValue().add(value5);
            //输血史
            var entryRelationship2 = this.appendObservationRelationship(entry3, "HDSD00.01.031","");
            var value6 = this.newST(item.getShuXueYY());
            entryRelationship2.getObservation().getValue().add(value6);
        });
    }


    public void createJiaZuShi(POCDMT000040Component3 component)
    {
        //component.Section.Text.Lazy = true;
        //家族史
        jiaZuShiList.forEach(item -> {
            var entry1 = this.appendOrganizer(component, "",XActClassDocumentEntryOrganizer.CLUSTER);
            POCDMT000040Subject subject=new POCDMT000040Subject();
            subject.setTypeCode(ParticipationTargetSubject.SBJ);
            POCDMT000040RelatedSubject relatedSubject=new POCDMT000040RelatedSubject();
            CE ce=new CE();
            ce.setCode(item.getYuBenRGXDM());
            ce.setDisplayName(item.getYuBenRGXMC());
            ce.setCodeSystem("2.16.156.10011.2.3.3.8");
            ce.setCode("家庭关系代码表(GB/T 4761)");
            relatedSubject.setCode(ce);

            //todo...缺少家庭成员性别
            POCDMT000040SubjectPerson subjectPerson=new POCDMT000040SubjectPerson();
            CE person_ce=new CE();
            person_ce.setCode("0");
            person_ce.setDisplayName("未知的性别");
            person_ce.setCodeSystem("2.16.156.10011.2.3.3.4");
            person_ce.setCodeSystemName("生理性别代码表(GB/T 2261.1)");
            subjectPerson.setAdministrativeGenderCode(person_ce);
            relatedSubject.setSubject(subjectPerson);
            subject.setRelatedSubject(relatedSubject);
            entry1.setSubject(subject);

            //家族性疾病名称代码
            var entry2 = this.appendOrganizerObservation(entry1, "HDSD00.01.033","");
            var value0 = this.newCD("HDSD00.01.033", "2.16.156.10011.2.3.1.12", item.getJiBingZLDM());
            entry2.getValue().add(value0);

        });


    }

    public void createYiChuanBS(POCDMT000040Component3 component)
    {
        //遗传病名称
        POCDMT000040Section section=new POCDMT000040Section();
        CE ce=new CE();
        ce.setDisplayName("遗传病史");
        section.setCode(ce);
        component.setSection(section);
        section.setText(new StrucDocText());
        var entry0 = this.appendObservation(component, "HDSD00.01.035","");
        var value0 = this.newST(jiBenXXDto.getYiChuanBSMC());
        entry0.getValue().add(value0);
    }

    public void createCanJiShi(POCDMT000040Component3 component)
    {
        POCDMT000040Section section=new POCDMT000040Section();
        CE ce=new CE();
        ce.setCode("8671-0");
        ce.setDisplayName("History of functional status");
        ce.setCodeSystem("2.16.840.1.113883.6.1");
        ce.setCodeSystemName("LOINC");
        section.setCode(ce);
        component.setSection(section);
        section.setText(new StrucDocText());
        //残疾情况
        canJiQKList.forEach(item -> {
            var entry0 = this.appendObservation(component, "HDSD00.01.036","");
            entry0.getEffectiveTime().setValue(dateTimeToString(jiBenXXDto.getJianDangSJ(),2));
            var value0 = this.newCD("HDSD00.01.036", "2.16.156.10011.2.3.1.139", item.getCanJiQKDM());
            entry0.getValue().add(value0);
        });
    }

    public void createShengHuoHJ(POCDMT000040Component3 component)
    {
        POCDMT000040Section section=new POCDMT000040Section();
        CE ce=new CE();
        ce.setDisplayName("生活环境");
        section.setCode(ce);
        section.setText(new StrucDocText());
        component.setSection(section);


        //家庭厨房排风设施类别代码
        var entry0 = this.appendObservation(component, "HDSD00.01.037","");
        var value0 = this.newBL(jiBenXXDto.getChuFangPFSSLBDM().equals("1")?true:false);
        entry0.getValue().add(value0);
        //家庭厨房排风设施类别代码
        var entryRelationship0 = this.appendObservationRelationship(entry0, "HDSD00.01.038","");
        var value1 = this.newCD("HDSD00.01.038", "2.16.156.10011.2.3.1.34", jiBenXXDto.getChuFangPFSSLBDM());
        entryRelationship0.getObservation().getValue().add(value1);

        //家庭燃料类型类别代码
        var entry1 = this.appendObservation(component, "HDSD00.01.039","");
        var value2 = this.newCD("HDSD00.01.039", "2.16.156.10011.2.3.1.35", jiBenXXDto.getRanLiaoLXLBDM());
        entry1.getValue().add(value2);

        //家庭饮水类别代码
        var entry2 = this.appendObservation(component, "HDSD00.01.040","");
        var value3 = this.newCD("HDSD00.01.040", "2.16.156.10011.2.3.1.27", jiBenXXDto.getYinShuiLBDM());
        entry2.getValue().add(value3);

        //家庭厕所类别代码
        var entry3 = this.appendObservation(component, "HDSD00.01.041","");
        var value4 = this.newCD("HDSD00.01.041", "2.16.156.10011.2.3.1.36", jiBenXXDto.getJiaTingCLLBDM());
        entry3.getValue().add(value4);

        //家庭禽畜栏类别
        var entry4 = this.appendObservation(component, "HDSD00.01.042","");
        var value5 = this.newCD("HDSD00.01.042", "2.16.156.10011.2.3.2.2", jiBenXXDto.getQinChuLLBDM());
        entry4.getValue().add(value5);
    }

}

package cn.mediinfo.grus.shujuzx.service;

import cn.hutool.core.collection.ListUtil;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.cyan.msf.tenant.security.BasicTenantInfo;
import cn.mediinfo.cyan.msf.tenant.security.TenantIdentityService;
import cn.mediinfo.grus.shujuzx.common.fangan.condition.FangAnCondition;
import cn.mediinfo.grus.shujuzx.common.fangan.condition.FangAnTreeNode;
import cn.mediinfo.grus.shujuzx.common.fangan.condition.FanganConditionValue;
import cn.mediinfo.grus.shujuzx.dto.fangansc.FangAnSCDTO;
import cn.mediinfo.grus.shujuzx.dto.fangansc.QueryField;
import cn.mediinfo.grus.shujuzx.dto.shitumx.*;
import cn.mediinfo.grus.shujuzx.enums.NodeTypeEnum;
import cn.mediinfo.grus.shujuzx.enums.ShuJuZLXDMEnum;
import cn.mediinfo.grus.shujuzx.manager.FangAnManager;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.ShuJuXXMSRso;
import cn.mediinfo.grus.shujuzx.remoteservice.GongYongRemoteService;
import cn.mediinfo.grus.shujuzx.request.fangan.FangAnXXSaveRequest;
import cn.mediinfo.grus.shujuzx.service.impl.FangAnServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
class FangAnServiceTest {

   /* @Mock
    private ShiTuMXService shiTuMXService;

    @Mock
    private FangAnSCService fangAnSCService;*/

    @Mock
    private FangAnManager fangAnManager;

    @Mock
    private GongYongRemoteService gongYongRemoteService;

    @Mock
    private TenantIdentityService tenantIdentityService;

    @InjectMocks
    private FangAnService fangAnService = new FangAnServiceImpl();

    @Test
    void saveFangAn() throws YuanChengException, TongYongYWException {
       /* when(shiTuMXService.listTable(anySet())).thenReturn(mockTable());
        when(shiTuMXService.listFields(anySet())).thenReturn(mockFields());
        when(gongYongRemoteService.getShuJXXMS(anyList())).thenReturn(mockJiChuTable());*/
        //when(fangAnSCService.getAllFangAnSC(anyList())).thenReturn(mockFangAnSC());
        /*when(fangAnManager.saveFangAn(any(), anyString())).thenReturn("1");
        when(tenantIdentityService.getCurrentTenant()).thenReturn(new BasicTenantInfo("1040209927943733248", "黄岩卫健局"));*/

        FangAnTreeNode root = new FangAnTreeNode();
        root.setNodeType(NodeTypeEnum.RELATION_NODE.getType());
        root.setRelation("AND");

        FangAnTreeNode nameNode = new FangAnTreeNode();
        nameNode.setNodeType(NodeTypeEnum.CONDITION_NODE.getType());
        FangAnCondition name = new FangAnCondition();
        name.setShiTuID("10000");
        name.setZiDuanMC("姓名");
        name.setOperator("=");
        FanganConditionValue val = new FanganConditionValue();
        val.setVal("张三");
        name.setValues("张三");
        nameNode.setCondition(name);

        FangAnTreeNode and = new FangAnTreeNode();
        and.setNodeType(NodeTypeEnum.RELATION_NODE.getType());
        and.setRelation("AND");

        FangAnTreeNode nianlingNode = new FangAnTreeNode();
        nianlingNode.setNodeType(NodeTypeEnum.CONDITION_NODE.getType());
        FangAnCondition nianling = new FangAnCondition();
        nianling.setShiTuID("20000");
        nianling.setZiDuanMC("年龄");
        nianling.setOperator(">");
        FanganConditionValue age = new FanganConditionValue();
        age.setVal("30");
        nianling.setValues("30");
        nianlingNode.setCondition(nianling);


        FangAnTreeNode birthdateNode = new FangAnTreeNode();
        birthdateNode.setNodeType(NodeTypeEnum.CONDITION_NODE.getType());
        FangAnCondition birthdate = new FangAnCondition();
        birthdate.setShiTuID("10001");
        birthdate.setZiDuanMC("出生日期");
        birthdate.setOperator(">");
        FanganConditionValue date = new FanganConditionValue();
        date.setVal("1940-01-01");
        birthdate.setValues("1940-01-01");
        birthdateNode.setCondition(birthdate);

//        and.setLeft(nianlingNode);
//        and.setRight(birthdateNode);
//        root.setLeft(and);
//        root.setRight(nameNode);

        FangAnXXSaveRequest request = new FangAnXXSaveRequest();
        request.setFangAnMC("患者信息查询");
        request.setFangAnLXDM("0");
        request.setFangAnLXMC("全部");
        request.setFangAnSCList(ListUtil.toList());
        request.setRoot(root);

        /*String fangAnId = fangAnService.saveFangAn(request);
        assertEquals("Equals", "1", fangAnId);*/
    }

    private List<TableDTO> mockTable() {
        TableDTO table = new TableDTO();

        SchemaTable br = new SchemaTable();
        br.setBiaoMing("br_da_jibenxx");
        br.setMoShi("vela_br");

        SchemaTable jz = new SchemaTable();
        jz.setBiaoMing("jz_mz_jiuzhenxx");
        jz.setMoShi("vela_jz");

        table.setSchemaTableList(ListUtil.toList(br, jz));

        /*TableRelationCondition join = new TableRelationCondition();
        join.setBiaoMing("br_da_jibenxx");
        join.setMoShi("vela_br");
        join.setZiduan("id");
        join.setGlMoShi("vela_jz");
        join.setGlBiaoMing("jz_mz_jiuzhenxx");
        join.setGlZiDuan("bingrenid");

        table.setTableRelationConditionList(ListUtil.toList(join));

        SqlFilterCondition filter = new SqlFilterCondition();
        filter.setBiaoMing("br_da_jibenxx");
        filter.setMoShi("vela_br");
        filter.setZiDuanBM("xingbiedm");
        filter.setOperator("<>");
        filter.setVal("'2'");

        table.setFilterConditionList(ListUtil.toList(filter));*/

        return ListUtil.toList(table);
    }


    private List<FieldDTO> mockFields() {
        FieldDTO field1 = new FieldDTO();
        field1.setId("10000");
        field1.setBiaoMing("br_da_jibenxx");
        field1.setMoShi("vela_br");
        field1.setZiDuanBM("xingming");
        field1.setZiDuanMC("姓名");


        FieldDTO field2 = new FieldDTO();
        field2.setId("10001");
        field2.setBiaoMing("br_da_jibenxx");
        field2.setMoShi("vela_br");
        field2.setZiDuanBM("chushengrq");
        field2.setZiDuanMC("出生日期");


        FieldDTO field3 = new FieldDTO();
        field3.setId("20000");
        field3.setBiaoMing("jz_mz_jiuzhenxx");
        field3.setMoShi("vela_jz");
        field3.setZiDuanBM("nianling");
        field3.setZiDuanMC("年龄");
        field3.setShuJuZLXDM(ShuJuZLXDMEnum.NUMBER.getType());

        return ListUtil.toList(field1, field2, field3);
    }

    private FangAnSCDTO mockFangAnSC() {
        QueryField field1 = new QueryField();
        field1.setBiaoMing("br_da_jibenxx");
        field1.setMoShi("vela_br");
        field1.setZiDuanBM("id");
        field1.setZiDuanMC("id");

        QueryField field2 = new QueryField();
        field2.setBiaoMing("br_da_jibenxx");
        field2.setMoShi("vela_br");
        field2.setZiDuanBM("xingming");
        field2.setZiDuanMC("姓名");


        QueryField field3 = new QueryField();
        field3.setBiaoMing("br_da_jibenxx");
        field3.setMoShi("vela_br");
        field3.setZiDuanBM("chushengrq");
        field3.setZiDuanMC("出生日期");

        QueryField field4 = new QueryField();
        field4.setBiaoMing("jz_mz_jiuzhenxx");
        field4.setMoShi("vela_jz");
        field4.setZiDuanBM("id");
        field4.setZiDuanMC("id");

        QueryField field5 = new QueryField();
        field5.setBiaoMing("jz_mz_jiuzhenxx");
        field5.setMoShi("vela_jz");
        field5.setZiDuanBM("jiuzhenrq");
        field5.setZiDuanMC("就诊时间");

        FangAnSCDTO fangAnSC = new FangAnSCDTO();
        fangAnSC.setQueryFields(ListUtil.toList(field1, field2, field3, field4, field5));
        return fangAnSC;
    }

    private MsfResponse<List<ShuJuXXMSRso>> mockJiChuTable() {
        MsfResponse<List<ShuJuXXMSRso>> result=new MsfResponse<>();
        ShuJuXXMSRso menZhen=new ShuJuXXMSRso();
        menZhen.setShuJuYMC("VELA_JZ");
        menZhen.setBiaoMing("JZ_MZ_JIUZHENXX");

        ShuJuXXMSRso zhuYuan=new ShuJuXXMSRso();
        zhuYuan.setShuJuYMC("VELA_JZ");
        zhuYuan.setBiaoMing("JZ_ZY_JIUZHENXX");
        result.setData(ListUtil.toList(menZhen,zhuYuan));
        return result;
    }
}
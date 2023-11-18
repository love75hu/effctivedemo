package cn.mediinfo.grus.shujuzx.service.impl;

import cn.hutool.core.convert.Convert;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.grus.shujuzx.dto.cda.dangan.DA_GA_JiBenXXDto;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangMBDto;
import cn.mediinfo.grus.shujuzx.dto.wenDang.XML_JieDian;
import cn.mediinfo.grus.shujuzx.dto.wenDang.XML_JieDianSX;
import cn.mediinfo.grus.shujuzx.dto.wendangjls.SC_GW_JiLuXXExportDto;
import cn.mediinfo.grus.shujuzx.dto.wendangscs.*;
import cn.mediinfo.grus.shujuzx.po.wendangcx.SC_GW_JiLuXXListDtoPO;
import cn.mediinfo.grus.shujuzx.remoteservice.GongWeiRemoteService;
import cn.mediinfo.grus.shujuzx.remoteservice.JiuZhenRemoteService;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_WenDangMBRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_WenDangRepository;
import cn.mediinfo.grus.shujuzx.service.WenDangSCService;
import cn.mediinfo.grus.shujuzx.utils.GZIPUtils;
import cn.mediinfo.grus.shujuzx.utils.QueryableUtils;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.GsonBuilder;
import com.nimbusds.jose.shaded.gson.ToNumberPolicy;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.querydsl.core.types.Expression;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.pulsar.shade.org.apache.avro.data.Json;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.ws.rs.core.HttpHeaders;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import cn.mediinfo.grus.shujuzx.utils.WenDangSCSJJUtils;

@Service
public class WenDangSCServiceImpl implements WenDangSCService {

    private final JiuZhenRemoteService jiuZhenRemoteService;
    private  final GongWeiRemoteService gongWeiRemoteService;
    public WenDangSCServiceImpl(JiuZhenRemoteService jiuZhenRemoteService,GongWeiRemoteService gongWeiRemoteService) {
        this.jiuZhenRemoteService=jiuZhenRemoteService;
        this.gongWeiRemoteService=gongWeiRemoteService;
    }
    /**
     * 生成共享文档数据集
     */
    @Override
    public  List<WenDangSJJDto>  getGongXiangWDSJJ(WenDangSCQueryDto dto) throws IOException, NoSuchFieldException, IllegalAccessException, YuanChengException {
        var mpiList=jiuZhenRemoteService.GetGuanLianBRIDs(dto.getMpi()).getData();
        var luJing="src/main/java/cn/mediinfo/grus/shujuzx/xmls/";
        var error_XML ="";
        List<WenDangSJJDto> resList=new ArrayList<>();
        switch (dto.getWenDangLXDM()){
            case "B0001":
                luJing=luJing+"HDSD00.01.01.xml";
                var b0001XML = Files.readAllLines(Path.of(luJing));
                //获取数据
                var b0001Data= MsfResponse.getData(gongWeiRemoteService.GetB0001(mpiList), "远程获取健康档案失败");
                var b0001Utils= new WenDangSCSJJUtils<B0001ShuJuJDto>();
                var b0001Res=b0001Utils.getXMLStr(b0001Data,b0001XML);
                var b0001Item=new WenDangSJJDto();
                b0001Item.setWenDangLXDM("B0001");
                b0001Item.setWenDangLXMC("个人基本健康信息登记");
                b0001Item.setShuJuJMC("");
                b0001Item.setShuJuJNR(b0001Res);
                resList.add(b0001Item);
                break;
            case"B0002":
                break;
            case"B0003":
                luJing=luJing+"HDSD00.01.03.xml";
                var b0003XML = Files.readAllLines(Path.of(luJing));
                //获取数据
                var b0003Data=new B0003ShuJuJDto();
                var b0003Utils= new WenDangSCSJJUtils<B0003ShuJuJDto>();
                var b0003Res=b0003Utils.getXMLStr(b0003Data,b0003XML);
                var b0003Item=new WenDangSJJDto();
                b0003Item.setWenDangLXDM("B0003");
                b0003Item.setWenDangLXMC("新生儿家庭访视");
                b0003Item.setShuJuJMC("");
                b0003Item.setShuJuJNR(b0003Res);
                resList.add(b0003Item);
        }
        return resList;
    }
    /**
     * 导出文档内容
     *
     * @param
     * @return
     */
    @Override
    public void exportWenDangSJJ(HttpServletResponse response,WenDangSCQueryDto dto) throws IOException, NoSuchFieldException, IllegalAccessException, YuanChengException {
        var wenDangJLDtoList=getGongXiangWDSJJ(dto);
        DaoChuSJJ(response,wenDangJLDtoList);
    }
    public void DaoChuSJJ(HttpServletResponse response, List<WenDangSJJDto> wenDangJLDtoList) throws IOException, NoSuchFieldException, IllegalAccessException {
        var nowDate = new Date();
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+new SimpleDateFormat("yyyyMMddHHmmss").format(nowDate)+".zip");

        ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
        wenDangJLDtoList.forEach(item->{
            if (Objects.nonNull(item) && StringUtil.hasText(item.getShuJuJNR())){
                try {
                    var xmlName = item.getShuJuJMC()+".xml";
                    zos.putNextEntry(new ZipEntry(xmlName));
                    var docContent = GZIPUtils.uncompressToString(item.getShuJuJNR().getBytes(StandardCharsets.UTF_8));//解压
                    zos.write(docContent.getBytes(StandardCharsets.UTF_8));
                    zos.closeEntry();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        zos.close();
    }
}

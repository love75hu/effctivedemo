package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.util.MapUtils;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.orm.repository.querydsl.QuerydslExpression;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangDto;
import cn.mediinfo.grus.shujuzx.dto.wendangjls.SC_GW_JiLuXXExportDto;
import cn.mediinfo.grus.shujuzx.dto.wendangjls.SC_GW_JiLuXXListDto;
import cn.mediinfo.grus.shujuzx.dto.wendangjls.SC_ZD_WenDangGroupListDto;
import cn.mediinfo.grus.shujuzx.model.QSC_GW_JiLuXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_GW_JiLuXXModel;
import cn.mediinfo.grus.shujuzx.po.wendangcx.SC_GW_JiLuXXListDtoPO;
import cn.mediinfo.grus.shujuzx.repository.SC_GW_JiLuNRRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_GW_JiLuXXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_WenDangRepository;
import cn.mediinfo.grus.shujuzx.service.WenDangCXService;
import cn.mediinfo.grus.shujuzx.utils.QueryableUtils;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import com.querydsl.core.types.Expression;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import cn.mediinfo.grus.shujuzx.utils.GZIPUtils;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class WenDangCXServiceImpl implements WenDangCXService {
    private final LyraIdentityService lyraIdentityService;
    private final SC_GW_JiLuNRRepository sc_gw_jiLuNRRepository;
    private final SC_GW_JiLuXXRepository sc_gw_jiLuXXRepository;
    private final SC_ZD_WenDangRepository sc_zd_wenDangRepository;
    public WenDangCXServiceImpl (LyraIdentityService lyraIdentityService,SC_GW_JiLuNRRepository sc_gw_jiLuNRRepository,SC_GW_JiLuXXRepository sc_gw_jiLuXXRepository,SC_ZD_WenDangRepository sc_zd_wenDangRepository){
        this.lyraIdentityService = lyraIdentityService;
        this.sc_gw_jiLuNRRepository = sc_gw_jiLuNRRepository;
        this.sc_gw_jiLuXXRepository = sc_gw_jiLuXXRepository;
        this.sc_zd_wenDangRepository = sc_zd_wenDangRepository;
    }

    /**
     * 获取文档记录列表
     *
     * @param wenDangID
     * @param likeQueryType
     * @param likeQuery
     * @param startTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<SC_GW_JiLuXXListDto> getWenDangJLList(String wenDangID,String likeQueryType, String likeQuery,Date startTime, Date endTime,  Integer pageIndex, Integer pageSize) {
        pageIndex = pageIndex == null ? 1 : pageIndex;
        pageSize = pageSize == null ? 10 : pageSize;
        return getWenDangJLQueryable(wenDangID,likeQueryType,likeQuery,startTime,endTime)
                .orderBy(x->x.shengChengSJ.desc())
                .select(SC_GW_JiLuXXListDto.class)
                .fetchPage(pageIndex,pageSize);
    }

    /**
     * 获取文档记录数量
     *
     * @param wenDangID
     * @param likeQueryType
     * @param likeQuery
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public Long getWenDangJLCount(String wenDangID,String likeQueryType, String likeQuery, Date startTime, Date endTime) {
        return getWenDangJLQueryable(wenDangID,likeQueryType,likeQuery,startTime,endTime).count();
    }

    /**
     * 获取文档分组列表
     */
    @Override
    public List<SC_ZD_WenDangGroupListDto> getWenDangGroupList(){
        List<SC_ZD_WenDangGroupListDto> result = new ArrayList<>();
        var list = sc_zd_wenDangRepository.asQuerydsl().where(x->x.qiYongBZ.eq(1)).orderBy(x->x.shunXuHao.asc()).fetch();
        record group(String leiBieDM,String leiBieMC){}
        var groupList = list.stream().collect(Collectors.groupingBy(q->new group(q.getLeiBieDM(),q.getLeiBieMC())));
        groupList.forEach((key,children)->{
            children = list.stream().filter(x->x.getLeiBieDM().equals(key.leiBieDM)).toList();
            var childrenList = MapUtils.copyListProperties(children, SC_ZD_WenDangDto::new);
            var item = new SC_ZD_WenDangGroupListDto();
            item.setLeiBieDM(key.leiBieDM);
            item.setLeiBieMC(key.leiBieMC);
            item.setItemList(childrenList);
            result.add(item);
        });
        return result;
    }

    private QuerydslExpression<QSC_GW_JiLuXXModel,SC_GW_JiLuXXModel> getWenDangJLQueryable(String wenDangID,String likeQueryType, String likeQuery, Date startTime, Date endTime){
        var queryble = sc_gw_jiLuXXRepository.asQuerydsl()
                .whereIf(StringUtil.hasText(wenDangID),  r -> r.wenDangID.eq(wenDangID))
                .whereIf(Objects.nonNull(startTime), r -> r.shengChengSJ.goe(startTime))
                .whereIf(Objects.nonNull(endTime), r -> r.shengChengSJ.lt(endTime));
        if (StringUtil.hasText(likeQueryType) && StringUtil.hasText(likeQuery))
        {
            switch (likeQueryType){
                case "MPI":
                    queryble = queryble.where(x->x.bingRenID.eq(likeQuery));
                    break;
                case "XingMing":
                    queryble = queryble.where(x->x.xingMing.contains(likeQuery));
                    break;
                case "ShenFenZH":
                    queryble = queryble.where(x->x.zhengJianHM.eq(likeQuery));
                    break;
                case "MenZhenH":
                    //queryble = queryble.where(x->x..eq(likeQuery));
                    break;
                case "BingAnH":
                    //queryble = queryble.where(x->x.bingRenID.eq(likeQuery));
                    break;
                case "WenDangID":
                    queryble = queryble.where(x->x.wenDangID.eq(likeQuery));
                    break;
            }
        }
        return queryble;
    }

    /**
     * 导出文档内容
     *
     * @param yeWuIDList
     * @return
     */
    @Override
    public void exportWenDangNR(HttpServletResponse response,String yeWuIDList) throws IOException {
        var wenDangJLList = Arrays.stream(yeWuIDList.split("\\|")).toList();
        Function<SC_GW_JiLuXXListDtoPO, Expression<?>[]> selectExpression = t -> new Expression<?>[]{
                t.jiLuXXModel().id,
                t.jiLuXXModel().wenDangID,
                t.jiLuXXModel().wenDangMC,
                t.jiLuXXModel().xingMing,
                t.jiLuXXModel().yeWuSJ,
                t.neiRongModel().yaSuoFSDM,
                t.neiRongModel().neiRong
        };
        var wenDangNR = sc_gw_jiLuXXRepository.asQuerydsl().join(sc_gw_jiLuNRRepository.asQuerydsl(), (jiLuXX, jiLuNR) -> jiLuXX.id.eq(jiLuNR.wenDangJLID), SC_GW_JiLuXXListDtoPO::new).where(x->x.jiLuXXModel().id.in(wenDangJLList));
        var wenDangJLDtoList = QueryableUtils.orderBy(wenDangNR, selectExpression, "yeWuSJ", 1)
                .select(selectExpression, SC_GW_JiLuXXExportDto.class).fetch();
        generateXmlFiles(response,wenDangJLDtoList);
    }

    private void CreateXmlFile(List<SC_GW_JiLuXXExportDto> wenDangJLDtoList){
        wenDangJLDtoList.forEach(item->{
            try {
                var xmlName = item.getWenDangMC()+"_"+item.getXingMing()+"_"+ String.format("{0:yyyyMMdd}", java.time.LocalDateTime.now());

                var data = Files.write(Paths.get(xmlName), item.getNeiRong().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void generateXmlFiles(HttpServletResponse response,List<SC_GW_JiLuXXExportDto> wenDangJLDtoList) throws IOException {
        var nowDate = new Date();
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+new SimpleDateFormat("yyyyMMddHHmmss").format(nowDate)+".zip");
        ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
        wenDangJLDtoList.forEach(item->{
            if (Objects.nonNull(item) && StringUtil.hasText(item.getNeiRong())){
                try {
                    var xmlName = item.getWenDangMC()+"_"+item.getXingMing()+"_"+ new SimpleDateFormat("yyyyMMddHHmmss").format(nowDate)+".xml";
                    zos.putNextEntry(new ZipEntry(xmlName));
                    var docContent = GZIPUtils.uncompressToString(item.getNeiRong().getBytes(StandardCharsets.UTF_8));//解压
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

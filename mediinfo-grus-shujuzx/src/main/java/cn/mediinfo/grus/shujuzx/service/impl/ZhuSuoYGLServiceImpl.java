package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.constant.ZhuSuoYHBZTConstant;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs.BR_DA_JiBenXXByZSYGLDto;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs.BR_DA_JiBenXXByZSYXQDto;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs.JieZhiXXDto;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs.ZhuSuoYXQDto;
import cn.mediinfo.grus.shujuzx.dto.YinSiGZSZs.SC_ZD_YinSiPZOutDto;
import cn.mediinfo.grus.shujuzx.model.*;
import cn.mediinfo.grus.shujuzx.repository.*;
import cn.mediinfo.grus.shujuzx.service.YinSiGZSZService;
import cn.mediinfo.grus.shujuzx.service.ZhuSuoYGLService;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.lyra.service.LyraIdentityService;
import cn.mediinfo.starter.base.util.*;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 主索引管理
 */
@Service
public class ZhuSuoYGLServiceImpl implements ZhuSuoYGLService {
    public final BR_DA_XiangSiSYRepository brDaXiangSiSYRepository;
    public final BR_DA_JiaoChaSYRepository brDaJiaoChaSYRepository;
    public final BR_DA_JiBenXXRepository brDaJiBenXXRepository;
    public final BR_DA_HeBingJLRepository brDaHeBingJLRepository;
    public final BR_DA_JieZhiXXRepository brDaJieZhiXXRepository;
    public final YinSiGZSZService yinSiGZSZService;
    private final LyraIdentityService lyraIdentityService;
    @PersistenceContext
    private final EntityManager entityManager;
    public ZhuSuoYGLServiceImpl(BR_DA_XiangSiSYRepository brDaXiangSiSYRepository, BR_DA_JiaoChaSYRepository brDaJiaoChaSYRepository, BR_DA_JiBenXXRepository brDaJiBenXXRepository, BR_DA_HeBingJLRepository brDaHeBingJLRepository, BR_DA_JieZhiXXRepository brDaJieZhiXXRepository, YinSiGZSZService yinSiGZSZService, LyraIdentityService lyraIdentityService, EntityManager entityManager) {
        this.brDaXiangSiSYRepository = brDaXiangSiSYRepository;
        this.brDaJiaoChaSYRepository = brDaJiaoChaSYRepository;
        this.brDaJiBenXXRepository = brDaJiBenXXRepository;
        this.brDaHeBingJLRepository = brDaHeBingJLRepository;
        this.brDaJieZhiXXRepository = brDaJieZhiXXRepository;
        this.yinSiGZSZService = yinSiGZSZService;
        this.lyraIdentityService = lyraIdentityService;
        this.entityManager = entityManager;
    }

    /**
     * 获取主索引的相似索引信息
     * @return BR_DA_JiBenXXByZSYGLDto
     * @throws TongYongYWException 通用异常
     */
    @Override
    public List<BR_DA_JiBenXXByZSYGLDto> getXiangSiSYList(String zhuSuoYBRID) throws TongYongYWException {
        var qModel = QBR_DA_XiangSiSYModel.bR_DA_XiangSiSYModel;
        var xiangSiBRIDList = new JPAQueryFactory(entityManager).select(
                        Projections.bean(BR_DA_XiangSiSYModel.class, qModel.bingRenID2,qModel.xiangSiDu,qModel.bingRenID1))
                .from(qModel)
                .where(qModel.bingRenID1.eq(zhuSuoYBRID).and(qModel.huLueBZ.eq(0)))
                .fetch();
        //获取交叉病人Id列表
        var jiaoChaSYList =brDaJiaoChaSYRepository.findByZhuBingRID(zhuSuoYBRID);
        //相似集合
        var bingRenIDs= xiangSiBRIDList.stream().map(BR_DA_XiangSiSYModel::getBingRenID2).toList();
        var jiaoChaBRIDs = jiaoChaSYList.stream().map(BR_DA_JiaoChaSYModel::getGuanLianBRID).collect(Collectors.toList());
        jiaoChaBRIDs.addAll(bingRenIDs);
        var xiangSiBRXXList = MapUtils.copyListProperties(brDaJiBenXXRepository.findAllById(bingRenIDs),BR_DA_JiBenXXByZSYGLDto::new);
        var xiangSIBRIDs = xiangSiBRXXList.stream().map(BR_DA_JiBenXXByZSYGLDto::getId).toList();
        var jiaoChaSYCounts =brDaJiaoChaSYRepository.getJiaoChaZhuBRIDList(xiangSIBRIDs);
        //合并记录
        var heBingJLList = brDaHeBingJLRepository.findByBingRenIDIn(xiangSIBRIDs);
        for(var item :xiangSiBRXXList){
            var xiangSiSY = xiangSiBRIDList.stream().filter(x-> Objects.equals(x.getBingRenID2(), item.getId())).findFirst().orElse(null);
            var jiaoChaSY = jiaoChaSYList.stream().filter(x-> Objects.equals(x.getGuanLianBRID(), item.getId())).findFirst().orElse(null);
            var heBingJL = heBingJLList.stream().filter(x-> Objects.equals(x.getBingRenID(), item.getId())).findFirst().orElse(null);
            if(heBingJL!=null){
                item.setHeBingZTDM(heBingJL.getHeBingZTDM());
                item.setHeBingZTMC(heBingJL.getHeBingZTMC());
            }
            item.setHeBingBZ(jiaoChaSY == null?0:1);
            if(xiangSiSY!=null){
                item.setXiangSiDu(xiangSiSY.getXiangSiDu());
            }
            if(jiaoChaSY!=null){
                item.setZiDongHBBZ(jiaoChaSY.getZiDongHBBZ());
                item.setHeBingRXM(jiaoChaSY.getHeBingRXM());
                item.setHeBingSJ(jiaoChaSY.getChuangJianSJ());
            }
            item.setNianLing(getNianLing(item.getChuShengRQ()));
            item.setZhuSuoYBRID(zhuSuoYBRID);
            item.setJiaoChaSYCount((int) jiaoChaSYCounts.stream().filter(x -> Objects.equals(x, item.getId())).count());
            item.setXianZhuZXX(StringUtil.concat(item.getXianZhuZXX(),item.getXianZhuZXZMC(),item.getXianZhuZCJMC(),item.getXianZhuZQTXX()));
        }
        return xiangSiBRXXList.stream()
                .sorted(Comparator.comparing(BR_DA_JiBenXXByZSYGLDto::getHeBingBZ).reversed()
                        .thenComparing(Comparator.comparing(BR_DA_JiBenXXByZSYGLDto::getHeBingSJ).reversed())
                        .thenComparing(Comparator.comparing(BR_DA_JiBenXXByZSYGLDto::getXiangSiDu).reversed())
                        .thenComparing(Comparator.comparing(BR_DA_JiBenXXByZSYGLDto::getJiaoChaSYCount).reversed()))
                .collect(Collectors.toList());
    }

    /**
     * 获取主索引详情
     * @return ZhuSuoYXQDto
     * @throws TongYongYWException 通用异常
     */
    @Override
    public ZhuSuoYXQDto getZhuSuoYXQ(String bingRenID, String chaXunMSDM) throws TongYongYWException, ParseException, NoSuchFieldException, IllegalAccessException {
        if(!StringUtil.hasText(chaXunMSDM)){
            chaXunMSDM ="1";
        }
        var bingRenXX = brDaJiBenXXRepository.findById(bingRenID).orElse(null);
        if(bingRenXX == null){
            throw new TongYongYWException("未找到相关病人信息");
        }
        List<SC_ZD_YinSiPZOutDto> yinSiGZPZ = yinSiGZSZService.getYinSiGZPZList(chaXunMSDM,lyraIdentityService.getJiGouID());
        //获取交叉索引信息列表
        var jiaoChaModels = brDaJiaoChaSYRepository.findByZhuBingRID(bingRenID);
        var xiangSiBRIDList = jiaoChaModels.stream().map(BR_DA_JiaoChaSYModel::getGuanLianBRID).toList();
        var xiangSiBRList = MapUtils.copyListProperties(brDaJiBenXXRepository.findAllById(xiangSiBRIDList), BR_DA_JiBenXXByZSYXQDto::new);
        //介质信息列表
        var jieZhiXXModels = brDaJieZhiXXRepository.findByBingRenIDOrBingRenIDIn(bingRenID,xiangSiBRIDList);
        List<JieZhiXXDto> jieZhiXXList =  MapUtils.copyListProperties(jieZhiXXModels,JieZhiXXDto::new);
        var result = new ZhuSuoYXQDto();
        result.setBingRenXXList(new ArrayList<>());
        result.getBingRenXXList().add(MapUtils.copyProperties(bingRenXX,BR_DA_JiBenXXByZSYXQDto::new));
        result.getBingRenXXList().addAll(xiangSiBRList);
        for(var item :result.getBingRenXXList()){
            if(item.getJianDangSJ() == null){
                item.setJianDangSJ(item.getChuangJianSJ());
            }
            item.setNianLing(getNianLing(item.getChuShengRQ()));
            if(!CollectionUtils.isEmpty(yinSiGZPZ)){
                MapUtils.mergeProperties(GetYinShiSJ(item, yinSiGZPZ),item,true);
            }
        }
        for(var jieZhi :jieZhiXXList){
            if(!StringUtil.hasText(jieZhi.getDiSanFBRID())){
                jieZhi.setDiSanFBRID(jieZhi.getBingRenID());
            }
        }
        result.setGuanLianYXXList(jieZhiXXList);
        return result;
    }

    /**
     * 获取主索引管理数量
     * @return Integer
     * @throws TongYongYWException 通用异常
     */
    @Override
    public Integer getZhuSuoYinCount(Date kaiShiSJ, Date jieShuSJ, Integer xiangSiDu, String MPI, String xingMing, String lianXiDH, String shenFenZH) throws TongYongYWException, ParseException {
        var qJiBenXX = QBR_DA_JiBenXXModel.bR_DA_JiBenXXModel;
        var qHeBingJL  = QBR_DA_HeBingJLModel.bR_DA_HeBingJLModel;
        var count = new JPAQueryFactory(entityManager).select(qJiBenXX.count())
                .leftJoin(qHeBingJL)
                .on(qJiBenXX.id.eq(qHeBingJL.bingRenID))
                .where(QueryDSLUtils.whereIf(!ObjectUtils.isEmpty(kaiShiSJ),qJiBenXX.jianDangSJ.goe(kaiShiSJ)))
                .where(QueryDSLUtils.whereIf(!ObjectUtils.isEmpty(jieShuSJ),qJiBenXX.jianDangSJ.loe(jieShuSJ)))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(MPI),qJiBenXX.id.contains(MPI)))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(xingMing),qJiBenXX.xingMing.contains(xingMing)))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(lianXiDH),qJiBenXX.lianXiDH.contains(lianXiDH)))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(shenFenZH),qJiBenXX.zhengJianHM.contains(shenFenZH)))
                .where(qHeBingJL.heBingZTDM.ne(ZhuSuoYHBZTConstant.HEBINGZTMC_WHB).or(qHeBingJL.heBingZTDM.isNull()))
                .fetchOne();
        if (count != null) {
            return count.intValue();
        }
        return  0;
    }

    /**
     * 获取主索引管理列表
     * @return List<BR_DA_JiBenXXByZSYGLDto>
     * @throws TongYongYWException 通用异常
     */
    @Override
    public List<BR_DA_JiBenXXByZSYGLDto> getZhuSuoYGLList(Integer pageIndex, Integer pageSize, Date kaiShiSJ, Date jieShuSJ, Integer xiangSiDu, String MPI, String xingMing, String lianXiDH, String shenFenZH, String jiuZhenKH) throws TongYongYWException, ParseException {
        var qJiBenXX = QBR_DA_JiBenXXModel.bR_DA_JiBenXXModel;
        var qHeBingJL = QBR_DA_HeBingJLModel.bR_DA_HeBingJLModel;
        var result = new JPAQueryFactory(entityManager).select(
                Projections.bean(BR_DA_JiBenXXByZSYGLDto.class,qJiBenXX,
                        qHeBingJL.heBingZTDM,qHeBingJL.heBingZTMC,qHeBingJL.zuiDaXSD,qHeBingJL.heBingShu,qHeBingJL.xiangSiShu))
                .leftJoin(qHeBingJL)
                .on(qJiBenXX.id.eq(qHeBingJL.bingRenID))
                .where(QueryDSLUtils.whereIf(!ObjectUtils.isEmpty(kaiShiSJ),qJiBenXX.jianDangSJ.goe(kaiShiSJ)))
                .where(QueryDSLUtils.whereIf(!ObjectUtils.isEmpty(jieShuSJ),qJiBenXX.jianDangSJ.loe(jieShuSJ)))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(MPI),qJiBenXX.id.contains(MPI)))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(xingMing),qJiBenXX.xingMing.contains(xingMing)))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(lianXiDH),qJiBenXX.lianXiDH.contains(lianXiDH)))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(shenFenZH),qJiBenXX.zhengJianHM.contains(shenFenZH)))
                .where(qHeBingJL.heBingZTDM.ne(ZhuSuoYHBZTConstant.HEBINGZTMC_WHB).or(qHeBingJL.heBingZTDM.isNull()))
                .orderBy(qJiBenXX.jianDangSJ.asc().nullsLast())
                .offset(PageRequestUtil.of(pageIndex, pageSize).getOffset())
                .limit(pageSize)
                .fetch();
        for(var item : result){
            if(item.getHeBingZTDM() == null){
                item.setHeBingZTDM(ZhuSuoYHBZTConstant.HEBINGZTDM_WHB);
            }
            if(item.getHeBingZTMC() == null){
                item.setHeBingZTMC(ZhuSuoYHBZTConstant.HEBINGZTMC_WHB);
            }
            if(item.getZuiDaXSD() == null){
                item.setZuiDaXSD(0);
            }
            if(item.getHeBingShu() == null){
                item.setHeBingShu(0);
            }
            if(item.getXiangSiShu() == null){
                item.setXiangSiShu(0);
            }
            item.setNianLing(getNianLing(item.getChuShengRQ()));
            item.setXianZhuZXX(StringUtil.concat(item.getXianZhuZXX(),item.getXianZhuZXZMC(),item.getXianZhuZCJMC(),item.getXianZhuZQTXX()));
        }
        return result;
    }

    private String getNianLing(Date chuShengRQ){
        if(ObjectUtils.isEmpty(chuShengRQ)){
            return "";
        }
        var nianLing = new NianLing(chuShengRQ, new Date()).getAge();
        return nianLing.replaceAll("[\u4e00-\u9fa5]", " $0 ").trim();
    }

    private <T> T GetYinShiSJ(T tModel,List<SC_ZD_YinSiPZOutDto> tuoMinPZList) throws NoSuchFieldException, IllegalAccessException {
        if(tModel == null || CollectionUtils.isEmpty(tuoMinPZList)){
            return tModel;
        }
        for(var tuoMinPZ : tuoMinPZList){
            Class<?> type = tModel.getClass(); // 获取类型
            Field propertyInfo = type.getDeclaredField(tuoMinPZ.getShuJuYLM()); // 获取属性
            propertyInfo.setAccessible(true);
            String t = propertyInfo.getType().getName();
            if (t.equals("java.lang.String")) {
                Object value = propertyInfo.get(tModel);
                if(ObjectUtils.isEmpty(value)||!StringUtil.hasText(value.toString())){
                    continue;
                }
                String str = value.toString();
                if(Objects.equals(tuoMinPZ.getTuoMinFSDM(), "1")){
                    //1-全部替换为所需字符，如 *
                    propertyInfo.set(tModel, (tuoMinPZ.getTuoMinGZ() != null ? tuoMinPZ.getTuoMinGZ() : "*").repeat(value.toString().length())); //给对应属性赋值
                }
                else if(Objects.equals(tuoMinPZ.getTuoMinFSDM(), "2")){
                    //2-部分替换为设置字符：如：000***000，0为占位符
                    char[] charArray = value.toString().toCharArray();
                    int index = 1;
                    int j = 1;
                    String fuHao2 = "";
                    if (StringUtil.hasText(tuoMinPZ.getTuoMinGZ()) && tuoMinPZ.getTuoMinGZ().split(",").length>2) {
                        index = tuoMinPZ.getTuoMinGZ().split(",")[0].isEmpty() ? 0 : Integer.parseInt(tuoMinPZ.getTuoMinGZ().split(",")[0]);
                        j = tuoMinPZ.getTuoMinGZ().split(",")[1].isEmpty() ? 0 : Integer.parseInt(tuoMinPZ.getTuoMinGZ().split(",")[1]);
                        fuHao2 = tuoMinPZ.getTuoMinGZ().split(",")[2].isEmpty() ? "*" : tuoMinPZ.getTuoMinGZ().split(",")[2];
                    }
                    for (int i = index; i < index + j; i++) {
                        if (charArray.length - 1 < i) {
                            break;
                        }
                        charArray[i] = fuHao2.charAt(0);
                    }
                    propertyInfo.set(tModel, new String(charArray));//给对应属性赋值
                }
                else if(Objects.equals(tuoMinPZ.getTuoMinFSDM(), "3")){
                     //3-正则替换
                    if (StringUtil.hasText(tuoMinPZ.getTuoMinGZ()) && tuoMinPZ.getTuoMinGZ().split(",").length>1) {
                        Pattern pattern = Pattern.compile(tuoMinPZ.getTuoMinGZ().split(",")[0]);
                        String replaceWord = tuoMinPZ.getTuoMinGZ().split(",")[1];
                        value = pattern.matcher(value.toString()).replaceAll(replaceWord);
                    }
                    propertyInfo.set(tModel, value);//给对应属性赋值
                }
            }
            else {
                propertyInfo.set(tModel, null);
            }
        }
        return tModel;
    }
}

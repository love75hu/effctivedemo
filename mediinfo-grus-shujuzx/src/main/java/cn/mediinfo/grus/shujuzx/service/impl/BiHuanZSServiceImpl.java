package cn.mediinfo.grus.shujuzx.service.impl;

import aj.org.objectweb.asm.TypeReference;
import cn.hutool.core.collection.CollUtil;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.cyan.msf.core.util.*;
import cn.mediinfo.cyan.msf.security.IdentityService;
import cn.mediinfo.grus.shujuzx.dto.bihuandy.SC_BH_DiaoYongPZDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_JieDianXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_ZiBiHXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuanzs.*;
import cn.mediinfo.grus.shujuzx.dto.fangan.BingLiSCDTO;
import cn.mediinfo.grus.shujuzx.dto.shitumx.*;
import cn.mediinfo.grus.shujuzx.model.*;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.GY_ZD_ShuJuSTXXRso;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.GuanLianGXDto;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.LingChuangJSPZZDXXRso;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.ShuJuXXMSRso;
import cn.mediinfo.grus.shujuzx.remotedto.linchuang.ChaXunDto;
import cn.mediinfo.grus.shujuzx.remoteservice.GongYongRemoteService;
import cn.mediinfo.grus.shujuzx.remoteservice.LinChuangRemoteService;
import cn.mediinfo.grus.shujuzx.repository.*;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.mediinfo.grus.shujuzx.service.BiHuanZSService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.val;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Console;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Period;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * 闭环展示
 */
@Service
@AllArgsConstructor
public class BiHuanZSServiceImpl implements BiHuanZSService {
    /**
     * 闭环调用
     */
    private SC_BH_DiaoYongPZRepository diaoYongPZRepository;

    private final LyraIdentityService lyraIdentityService;

    /**
     * 闭环入参
     */
    private SC_BH_RuCanXXRepository ruCanXXRepository;

    /**
     * 公用服务
     */
    private GongYongRemoteService gongYongRemoteService;

    /**
     * 闭环基本信息
     */
    private SC_BH_JiBenXXRepository jiBenXXRepository;

    /**
     * 闭环节点信息
     */
    private SC_BH_JieDianXXRepository jieDianXXRepository;

    /**
     * 闭环节点时效
     */
    private SC_BH_JieDianSXRepository jieDianSXRepository;

    /**
     * 闭环子闭环显示列
     */
    private SC_BH_ZiBiHXSLRepository ziBiHXSLRepository;

    /**
     * 闭环子闭环信息
     */
    private SC_BH_ZiBiHXXRepository ziBiHXXRepository;

    /**
     * 闭环视图基本信息
     */
    private SC_BH_ShiTuXXRepository shiTuXXRepository;

    /**
     * 闭环节点信息
     */
    private SC_BH_ShiTuJDXXRepository shiTuJDXXRepository;

    /**
     * 闭环视图节点明细
     */
    private SC_BH_ShiTuJDMXRepository shiTuJDMXRepository;

    private final SC_BH_ShiTuMXRepository shiTuMXRepository;
    /**
     * 闭环节点关系
     */
    private SC_BH_ShiTuJDGXRepository shiTuJDGXRepository;

    private LinChuangRemoteService linChuangRemoteService;

    private IdentityService identityService;


    /**
     * 根据闭环功能点和相关入参获取闭环详情
     */
    public BiHuanXQDto getBiHuanXQ(BiHuanGNDPZ biHuanGNDPZ) throws YuanChengException, TongYongYWException {

        //闭环调用信息
        //当前功能点的调用配置
        List<SC_BH_DiaoYongPZDto> biHuanPZList = diaoYongPZRepository.getBiHuanPZList(lyraIdentityService.getJiGouID(), biHuanGNDPZ.getBiHuanGNDDM(), 1);
        //获取配的闭环
        var biHuanIDs = new CopyOnWriteArrayList<>(biHuanPZList.stream().map(SC_BH_DiaoYongPZDto::getBiHuanID).toList());
        //获取入参字段
        var ruCanZD = biHuanGNDPZ.getRuCanList().stream().map(ZiDuanRCDto::getZiDuanBM).toList();
        //闭环入参字段集合
        var ruCanXXList = ruCanXXRepository.findByBiHuanIDInAndZuZhiJGID(biHuanIDs, lyraIdentityService.getJiGouID());
        List<String> shituIDList = new ArrayList<>();
        List<String> biaoMingList = new ArrayList<>();
        boolean kongTiaoJFH = false;
        //循环判断入参字段是否一致
        for (SC_BH_DiaoYongPZDto sc_bh_diaoYongPZDto : biHuanPZList) {
            long count = ruCanXXList.stream().filter(n -> ruCanZD.contains(n.getZiDuanBM()) && n.getBiHuanID().equals(sc_bh_diaoYongPZDto.getBiHuanID())).count();
            if (count != ruCanZD.size()) {
                biHuanIDs.remove(sc_bh_diaoYongPZDto.getBiHuanID());
                continue;
            }
            //解析条件
            List<GuiZeDto> jsonToList = JsonUtil.getJsonToList(sc_bh_diaoYongPZDto.getTiaoJian(), GuiZeDto.class);
            List<String> shituIDs = jsonToList.stream().flatMap(guiZeDto -> guiZeDto.getGuiZeList().stream()).map(ShiTu::getShiTuID).distinct().toList();
            if (shituIDs.isEmpty() || (shituIDs.size() == 1 && shituIDs.get(0) == null)) {
                kongTiaoJFH = true;
                break;
            }
            //获取数据视图明细信息
            var biaoMing = extractTableNames(jsonToList);
            biaoMingList.addAll(biaoMing);
            shituIDList.addAll(shituIDs);
        }
        var shujuSHiTu = shiTuXXRepository.findByShiTuIDIn(shituIDList);

        var byJieDianIDIn = shiTuMXRepository.findByShiTuIDIn(shituIDList);

        List<LingChuangJSPZDto> lingChuangJSPZDtos = new ArrayList<>();
        shujuSHiTu.forEach(s -> {
            LingChuangJSPZDto lingChuangJSPZDto = new LingChuangJSPZDto();
            lingChuangJSPZDto.setShiTuID(s.getShiTuID());
            lingChuangJSPZDto.setShiTuMC(s.getShiTuMC());
            lingChuangJSPZDto.setShuJuLYLXDM(s.getShuJuLYLXDM());
            lingChuangJSPZDto.setShuJuLYID(s.getShuJuLYID());
            lingChuangJSPZDto.setShuJuJMXZDDtos(BeanUtil.copyListProperties(byJieDianIDIn.stream().filter(n -> n.getShiTuID().equals(s.getShiTuID())).toList(), ShuJuJMXZDDto::new));
            lingChuangJSPZDtos.add(lingChuangJSPZDto);
        });

        List<LingChuangJSPZZDXXRso> shuJuSTXXList = gongYongRemoteService.getlingChuangJSPZZDXX(lingChuangJSPZDtos).getData("获取数据视图信息失败");

        List<ShuJuXXMSRso> biaoMoshi = gongYongRemoteService.getShuJXXMS(new ArrayList<>(biaoMingList)).getData("获取表模式失败");

        if (!kongTiaoJFH) {
            for (String biHuanID : biHuanIDs) {
                SC_BH_DiaoYongPZDto scBhDiaoYongPZDto = biHuanPZList.stream().filter(n -> n.getBiHuanID().equals(biHuanID)).findFirst().orElse(null);
                if (scBhDiaoYongPZDto != null) {
                    List<GuiZeDto> jsonToList = JsonUtil.getJsonToList(scBhDiaoYongPZDto.getTiaoJian(), GuiZeDto.class);
                    //处理入参条件
                    //获取数据视图明细信息
                   // var biaoMing = extractTableNames(jsonToList);
                    //拼接入参条件
                    long count1 = shuJuSTXXList.get(0).getShiTuMXZDDtos().stream().filter(n -> ruCanZD.contains(n.getZiDuanBM())).count();


                    if (count1 == ruCanZD.size()) {
                        GuiZeDto guiZeDto = new GuiZeDto();
                        guiZeDto.setGuiZeList(shuJuSTXXList.get(0).getShiTuMXZDDtos().stream().filter(n -> ruCanZD.contains(n.getZiDuanBM())).map(n -> {
                            ShiTu shiTu = new ShiTu();
                            shiTu.setShiTuID(shuJuSTXXList.get(0).getShiTuID());
                            shiTu.setZiDuanBM(n.getZiDuanBM());
                            shiTu.setZiDuanMC(n.getZiDuanMC());
                            shiTu.setBiaoMing(n.getBiaoMing());
                            var ziduanzhi = new ZiDuanZhi();
                            ziduanzhi.setKey(n.getZiDuanBM());
                            ziduanzhi.setValue(biHuanGNDPZ.getRuCanList().stream().filter(l -> l.getZiDuanBM().equals(n.getZiDuanBM())).findFirst().orElse(new ZiDuanRCDto()).getZiDuanZhi());
                            shiTu.setZiDuanZhi(List.of(ziduanzhi));
                            return shiTu;
                        }).toList());

                        jsonToList.add(guiZeDto);

                    } else {
                        throw new TongYongYWException("数据视图配置没有入参id信息");
                    }
                    //处理基础条件
                    String tiaoJian = parseGuiZeListToSql(jsonToList);
                    StringBuilder guoLuTJ=new StringBuilder();
                    String fullTableName = biaoMoshi.stream().findFirst().map(s ->
                            {
                                guoLuTJ.append(s.getShuJuYMC()).append(".").append(s.getBiaoMing()).append(".").append("zuoFeiBZ=0").append(" AND ");
                                guoLuTJ.append(s.getShuJuYMC()).append(".").append(s.getBiaoMing()).append(".").append("zuzhijgid=").append("'").append(lyraIdentityService.getJiGouID()).append("'").append(" AND ");
                                guoLuTJ.append(s.getShuJuYMC()).append(".").append(s.getBiaoMing()).append(".").append("zuhuid=").append("'").append(identityService.getTenantId()).append("'").append(" AND ");
                              return  s.getShuJuYMC() + "." + s.getBiaoMing();
                            }
                    ).orElse("");
                    String sql = "SELECT COUNT(*) FROM " + fullTableName + " WHERE " + guoLuTJ +tiaoJian;
                    Long count = linChuangRemoteService.getShuLiang(new ChaXunDto(sql)).getData("执行sql报错");
                    if (count > 0) {
                        break;
                    } else {
                        biHuanIDs.remove(biHuanID);
                    }
                }

            }

        }

        if (biHuanIDs.isEmpty()) {
            return new BiHuanXQDto();
        }
        return getBiHuanZXJG(biHuanIDs.get(0), "0", "0", "0", lyraIdentityService.getJiGouID(), biHuanGNDPZ.getRuCanList());
    }

    public BiHuanXQDto getBiHuanXQ2(BiHuanGNDPZ biHuanGNDPZ) throws TongYongYWException, YuanChengException {
        //如果入参是空的 就返回空的数据
        if ( CollectionUtil.isEmpty(biHuanGNDPZ.getRuCanList()))return new BiHuanXQDto();
        //闭环调用信息
        //当前功能点的调用配置
        List<SC_BH_DiaoYongPZDto> biHuanPZList = diaoYongPZRepository.getBiHuanPZList(lyraIdentityService.getJiGouID(), biHuanGNDPZ.getBiHuanGNDDM(), 1);
        //如果没有配置也返回空
        if (CollectionUtil.isEmpty(biHuanPZList))return new BiHuanXQDto();
        String biHuanID = null;
        if (CollectionUtil.isEmpty(biHuanGNDPZ.getTiaoJianList()))
        {
            biHuanID= biHuanPZList.stream().filter(n->n.getTiaoJian().equals("[]")).findFirst().orElse(new SC_BH_DiaoYongPZDto()).getBiHuanID();
        }else {
            // 获取配的闭环
            for (SC_BH_DiaoYongPZDto sc_bh_diaoYongPZDto : biHuanPZList) {
                // 解析条件
                List<GuiZeDto> jsonToList = JsonUtil.getJsonToList(sc_bh_diaoYongPZDto.getTiaoJian(), GuiZeDto.class);
                boolean isMatched = false;
                if (jsonToList.isEmpty()) {
                    biHuanID = sc_bh_diaoYongPZDto.getBiHuanID();
                    break;
                }
                for (var guiZeDto : jsonToList) {

                    for (var shiTu : guiZeDto.getGuiZeList()) {
                        String ziDuanZhi = biHuanGNDPZ.getTiaoJianList().stream()
                                .filter(n -> n.getZiDuanBM().equals(shiTu.getZiDuanBM()))
                                .findFirst().orElse(new ZiDuanRCDto()).getZiDuanZhi();
                        if (!Objects.equals(ziDuanZhi, shiTu.getZiDuanZhi().get(0).getValue())) {
                            isMatched = false;
                            break;
                        } else {
                            isMatched = true;
                        }
                    }
                    if (isMatched) {
                        break;
                    }
                }
                if (isMatched) {
                    biHuanID = sc_bh_diaoYongPZDto.getBiHuanID();
                    break;
                }
            }
        }
        if (biHuanID==null)return new BiHuanXQDto();
        return getBiHuanZXJG("0" ,biHuanID, "0", "0", lyraIdentityService.getJiGouID(), biHuanGNDPZ.getRuCanList());
    }

    /**
         * 根据闭环id获取闭配置信息去执行sql
     */
    public BiHuanXQDto getBiHuanZXJG(String ziBiHID,String biHuanID, String ziBiHDCZXBZ, String jieDianID, String zuZhiJGID, List<ZiDuanRCDto> ruCanList) throws YuanChengException, TongYongYWException {
        zuZhiJGID = StringUtil.hasText(zuZhiJGID) ? zuZhiJGID : lyraIdentityService.getJiGouID();
        //获取闭环基本信息
        //1.获取闭环基本信息 SELECT * FROM SC_BH_JIBENXX;-- 闭环基本信息
        //2.获取闭环入参信息  SELECT * FROM SC_BH_RUCANXX;-- 闭环入参信息
        //3.获取闭环下的节点信息 SELECT * FROM SC_BH_JIEDIANXX;-- 闭环节点信息
        //4.在获取节点下的 节点时效
        //5.在获取节点下的 子闭环信信息
        //6.在获取子闭环的显示列

        //1.获取闭环基本信息
        SC_BH_JiBenXXModel biHuanJBXX = jiBenXXRepository.findFirstByBiHuanIDAndZuZhiJGID(biHuanID, zuZhiJGID);

        if (biHuanJBXX == null) {
            return new BiHuanXQDto();
        }
        //2.获取闭环入参信息
        List<SC_BH_RuCanXXModel> biHuanRCXXList = ruCanXXRepository.findByBiHuanIDAndZuZhiJGID(biHuanID, zuZhiJGID);
        //3.获取闭环下的节点信息
        List<SC_BH_JieDianXXModel> biHuanJDXXList = jieDianXXRepository.findByBiHuanIDAndZuZhiJGIDOrderByShunXuHao(biHuanID, zuZhiJGID);
        //4.在获取节点下的 节点时效
        List<SC_BH_JieDianSXModel> biHuanJDSXList = jieDianSXRepository.findByBiHuanIDAndZuZhiJGID(biHuanID, zuZhiJGID);
        //5.在获取节点下的 子闭环信信息
        List<SC_BH_ZiBiHXXModel> ziBiHXX = ziBiHXXRepository.findByBiHuanIDAndZuZhiJGID(biHuanID, zuZhiJGID);

        //6.在获取子闭环的显示列
        List<SC_BH_ZiBiHXSLModel> biHuanZBHXSLList = ziBiHXSLRepository.findByBiHuanIDAndZuZhiJGID(Objects.equals(ziBiHXX,"0")?ziBiHID:biHuanID , zuZhiJGID);

        //闭环入参信息
        //List<String> ruCanZDBMs = biHuanRCXXList.stream().map(n -> n.getZiDuanBM()).distinct().toList();
        //视图id 整理
        List<String> shituIDs = biHuanJDXXList.stream().map(SC_BH_JieDianXXModel::getShiTuID).distinct().toList();
        //节点id 整理
        List<String> jieDianIDs = biHuanJDXXList.stream().map(SC_BH_JieDianXXModel::getJieDianID).distinct().toList();
        //获取闭环视图数据
        List<SC_BH_ShiTuXXModel> biHuanSTXXList = shiTuXXRepository.findByShiTuIDIn(shituIDs);
        List<SC_BH_ShiTuMXModel> biHuanSTMXList = shiTuMXRepository.findByShiTuIDIn(shituIDs);
        //获取闭环节点数据
        List<SC_BH_ShiTuJDXXModel> biHuanSTJDXXList = shiTuJDXXRepository.findByJieDianIDIn(jieDianIDs);
        //获取节点明细数据
        List<SC_BH_ShiTuJDMXModel> biHuanSTJDMXList = shiTuJDMXRepository.findByJieDianIDIn(jieDianIDs);
        //节点关系
        List<SC_BH_ShiTuJDGXModel> jieDianGXList = shiTuJDGXRepository.findByJieDianIDIn(jieDianIDs);
        //节点管理下的所有节点
        //List<SC_BH_ShiTuJDXXModel> biHuanPZSYJD = shiTuJDXXRepository.findByShiTuIDIn(shituIDs);

        List<SC_BH_ShiTuJDMXModel> biHuanSTJDMXSYMX = shiTuJDMXRepository.findByShiTuIDIn(shituIDs);

        BiHuanXQDto biHuanXQDto = new BiHuanXQDto();
        biHuanXQDto.setBiHuanID(biHuanID);
        biHuanXQDto.setBiHuanMC(biHuanJBXX.getBiHuanMC());
        //组装节点管理中的配置的字段和数据视图
        List<LingChuangJSPZDto> shuJuLYDtos = new ArrayList<>();
        biHuanSTXXList.forEach(s -> {
            LingChuangJSPZDto shuJuLYDto = new LingChuangJSPZDto();
            shuJuLYDto.setShuJuLYID(s.getShuJuLYID());
            shuJuLYDto.setShiTuID(s.getShiTuID());
            shuJuLYDto.setShuJuLYLXDM(s.getShuJuLYLXDM());
            val list = biHuanSTMXList.stream().filter(n -> n.getShiTuID().equals(s.getShiTuID())).toList();
            shuJuLYDto.setShuJuJMXZDDtos(BeanUtil.copyListProperties(list, ShuJuJMXZDDto::new));
            shuJuLYDtos.add(shuJuLYDto);
        });
        if (shuJuLYDtos.isEmpty()) {
            return new BiHuanXQDto();
        }
        List<TableDTO> tableList = gongYongRemoteService.getShiTuGLFSGLTJ(shuJuLYDtos).getData("获取功能服务字段信息失败");

        //拼接入参条件

        List<ShiTuZXJGDto> shiTuZXJGDtoList=new ArrayList<>();
        //处理多视图执行代码
        for (var table : tableList) {
            ShiTuZXJGDto shiTuZXJGDto=new ShiTuZXJGDto();
            StringBuilder builder = new StringBuilder();
            for (SC_BH_RuCanXXModel r : biHuanRCXXList) {
                table.getSchemaTableList().forEach(n -> {
                    n.getShuJuJMXZDDtos().forEach(m -> {
                        if (m.getZiDuanBM().equals(r.getZiDuanBM()) && ruCanList.stream().allMatch(l->l.getZiDuanBM().equals(r.getZiDuanBM()))) {
                            String itemString = m.getShuJuYMC() + "." + m.getBiaoMing() + "." + r.getZiDuanBM() + "='" +
                                    ruCanList.stream().filter(l->l.getZiDuanBM().equals(r.getZiDuanBM()))
                                            .findFirst().orElse(new ZiDuanRCDto()).getZiDuanZhi() + "'";
                            if (!builder.isEmpty()) {
                                builder.append(" AND ");
                            }
                            builder.append(itemString);
                        }
                    });
                });
            }

            if (!StringUtil.hasText(builder.toString())) {
                throw new TongYongYWException("数据视图配置没有入参id信息");
            }
            // 为当前表设置过滤条件
            table.setFilterConditionList(builder.toString());

            shiTuZXJGDto.setShuJuLYLXDM(table.getShuJuLYLXDM());
            shiTuZXJGDto.setShuJuLYID(table.getShuJuLYID());
            String sql = getShiTuBGX(table,identityService.getTenantId(),zuZhiJGID);
            //执行sql 得出结果
            List<Map<String, Object>> maps = linChuangRemoteService.getZiDianList(new ChaXunDto(sql.toLowerCase())).getData("执行sql报错");
            shiTuZXJGDto.setShiTuZXJG(maps);
            shiTuZXJGDtoList.add(shiTuZXJGDto);
        }
        //单次执行视图 整理数据
        //String sql = getShiTuBGX(tableList.get(0),identityService.getTenantId(),zuZhiJGID);
        //执行sql 得出结果
        //List<Map<String, Object>> maps = linChuangRemoteService.getZiDianList(new ChaXunDto(sql.toLowerCase())).getData("执行sql报错");
        //执行完视图 获取节点的数据集合
        List<JieDianList> jieDianLists = new ArrayList<>();
        List<ZiDuanBMMC> ziDuanBMMCList = new ArrayList<>();
//        if (maps.isEmpty())
//        {
//            return new BiHuanXQDto();
//        }
        if (Objects.equals(ziBiHDCZXBZ, "1")) //子闭环多次执行信息
        {
            for (Map<String, Object> dataMap : shiTuZXJGDtoList.get(0).getShiTuZXJG()) {
               // List<SC_BH_ZiBiHXSLModel> ziBiHXSLList = biHuanZBHXSLList.stream().filter(n -> n.getJieDianID().equals(jieDianID)).toList();

                ZiDuanBMMC ziDuanBMMC = new ZiDuanBMMC();
                List<ZhanShiLList> zhanShiLLists = new ArrayList<>();
                biHuanZBHXSLList.forEach(z -> {
                    ZhanShiLList zhanShiL = new ZhanShiLList();
                    zhanShiL.setZiDuanBM(z.getZiDuanBM());
                    var ziDuanZ = dataMap.getOrDefault(z.getZiDuanBM().toLowerCase(), "");
                    if (ziDuanZ != null) {
                        zhanShiL.setZiDuanZhi(ziDuanZ.toString());
                    }

                    zhanShiL.setZiDuanMC(z.getZiDuanMC().toLowerCase());
                    zhanShiLLists.add(zhanShiL);
                });
                ziDuanBMMC.setZhanShiLLists(zhanShiLLists);
                List<JieDianList> jieDianList1 = new ArrayList<>();

                for (SC_BH_JieDianXXModel j : biHuanJDXXList.stream().filter(n->Objects.equals(n.getYinCangBZ(),0)).toList()) {//获取节点下的 子闭环信信息
                    List<SC_BH_ZiBiHXXModel> ziBiHXXList = ziBiHXX.stream().filter(n -> n.getJieDianID().equals(j.getJieDianID())).toList();
                    //获取子闭环的显示列
                    List<SC_BH_ZiBiHXSLModel> ziBiHXSLList1 = biHuanZBHXSLList.stream().filter(n -> n.getJieDianID().equals(j.getJieDianID())).toList();
                    //查询 视图信息
                    SC_BH_ShiTuXXModel biHuanSTXX = biHuanSTXXList.stream().filter(n -> n.getShiTuID().equals(j.getShiTuID())).findFirst().orElse(new SC_BH_ShiTuXXModel());
                    //查询 视图节点信息
                    SC_BH_ShiTuJDXXModel biHuanSTJDXXByJieID = biHuanSTJDXXList.stream().filter(n -> n.getJieDianID().equals(j.getJieDianID())).findFirst().orElse(new SC_BH_ShiTuJDXXModel());
                    //查询 视图下下的字段信息
                    List<SC_BH_ShiTuJDMXModel> biHuanJDMXByJieID = biHuanSTJDMXList.stream().filter(n -> n.getJieDianID().equals(j.getJieDianID())).toList();
                    JieDianList jieDianList = new JieDianList();
                    List<jieDianNRList> jieDianNRList = new ArrayList<>();
                    // 在 forEach 循环之前判断是行存储还是列存储
                    boolean isRowStorage = Objects.equals(biHuanSTXX.getShiTuLXDM(), 2);
                    if (isRowStorage && !StringUtil.hasText(biHuanSTJDXXByJieID.getShiJianZDBM())) continue;
                    // 如果是行存储，先过滤出符合条件的映射
                    List<Map<String, Object>> filteredMaps = isRowStorage ? shiTuZXJGDtoList.get(0).getShiTuZXJG().stream().filter(map -> Objects.equals(map.get(biHuanSTJDXXByJieID.getShiJianZDBM()), biHuanSTJDXXByJieID.getShiJianDM())).toList() : shiTuZXJGDtoList.get(0).getShiTuZXJG();

                    biHuanJDMXByJieID.forEach(f -> {
                        String value;
                        //value = getValueBasedOnStorage(isRowStorage, filteredMaps, maps, f.getZiDuanBM());
                        if (isRowStorage) {
                            var quZhi= filteredMaps.stream().map(map -> map.getOrDefault(f.getZiDuanBM().toLowerCase(), "")).findFirst().orElse("");
                            value= quZhi.toString() ;
                        } else {
                            var quZhi=dataMap.getOrDefault(f.getZiDuanBM().toLowerCase(), "");
                            if (Objects.isNull(quZhi))
                            {
                                value= "";
                            }else {
                                value= quZhi.toString() ;
                            }
                        }
                        // 创建并配置 jieDianNRList 对象
                        jieDianNRList biHuanJDNr = new jieDianNRList();
                        biHuanJDNr.setZiDuanBM(f.getZiDuanBM());
                        biHuanJDNr.setZiDuanMC(f.getZiDuanMC());
                        biHuanJDNr.setYunXuWKBZ(f.getYunXuWKBZ());
                        biHuanJDNr.setKongZhiSJBZ(f.getKongZhiSJBZ());
                        biHuanJDNr.setZiDuanZhi(value);
                        if (Objects.equals(f.getKongZhiSJBZ(), 1)) {
                            jieDianList.setKongZhiSJ(Converter.toDate(value));
                        }
                        // 将对象添加到列表中
                        jieDianNRList.add(biHuanJDNr);
                    });
                    //获取是否有子闭环
                    val ziBiHXXModel = ziBiHXXList.stream().filter(n -> n.getJieDianID().equals(j.getJieDianID())).findFirst().orElse(null);
                    if (ziBiHXXModel != null) {
                        //子闭环标志
                        jieDianList.setZiBiHBZ("1");
                        jieDianList.setZiBiHGLZD(ziBiHXXModel.getGuanLianZDBM());
                        jieDianList.setZiBiHGLZDZ(shiTuZXJGDtoList.get(0).getShiTuZXJG().stream().map(k -> k.getOrDefault(ziBiHXXModel.getGuanLianZDBM(), "").toString()).findFirst().orElse(""));
                    }
                    if (!CollectionUtils.isEmpty(ziBiHXSLList1)) {
                        jieDianList.setZiBiHDCZXBZ("1");
                    }
                    jieDianList.setBiXuBZ(j.getBiXuBZ());
                    jieDianList.setBingXingBZ(j.getBingXingBZ());
                    jieDianList.setId(j.getJieDianID());

                    if (Objects.equals(j.getBiXuBZ(), 1)) {
                        if (jieDianNRList.stream().noneMatch(e -> !Objects.equals(1, e.getYunXuWKBZ()) && ObjectUtils.isEmpty(e.getZiDuanZhi()))) {
                            jieDianList.setQueShiBZ(0);
                        } else {
                            jieDianList.setQueShiBZ(1);
                            if (jieDianNRList.stream().allMatch(e -> ObjectUtils.isEmpty(e.getZiDuanZhi())))
                            {
                                jieDianList.setWeiZhiXBZ(1);
                            }
                        }
                        jieDianList.setJieDianNRList(jieDianNRList);
                        jieDianLists.add(jieDianList);

                    } else {
                        if (jieDianNRList.isEmpty())
                        {
                            jieDianList.setJieDianNRList(new ArrayList<>());
                            jieDianLists.add(jieDianList);

                        }else {
                            if (jieDianNRList.stream().allMatch(e -> !Objects.equals(1, e.getYunXuWKBZ()) && ObjectUtils.isEmpty(e.getZiDuanZhi()))) {
                                continue;
                            }else
                            {
                                jieDianList.setXianShiBZ(1);
                            }
                        }
                    }
                    jieDianList.setJieDianMC(StringUtil.hasText(j.getXianShiMC()) ? j.getXianShiMC() : j.getJieDianMC());
                    jieDianList.setJieDianNRList(jieDianNRList);
                    jieDianList1.add(jieDianList);
                }
                ziDuanBMMC.setJieDianXX(bingXingJDPX(jieDianList1));
                ziDuanBMMCList.add(ziDuanBMMC);
            }
            biHuanXQDto.setZiBiHXSLList(ziDuanBMMCList);

             return biHuanXQDto;
        }

        for (SC_BH_JieDianXXModel scBhJieDianXXModel : biHuanJDXXList.stream().filter(n->Objects.equals(n.getYinCangBZ(),0)).toList()) {//获取节点下的 子闭环信信息
            List<SC_BH_ZiBiHXXModel> ziBiHXXList = ziBiHXX.stream().filter(n -> n.getJieDianID().equals(scBhJieDianXXModel.getJieDianID())).toList();
            //获取子闭环的显示列
            List<SC_BH_ZiBiHXSLModel> ziBiHXSLList = biHuanZBHXSLList.stream().filter(n -> n.getJieDianID().equals(scBhJieDianXXModel.getJieDianID())).toList();
            //查询 视图信息
            SC_BH_ShiTuXXModel biHuanSTXX = biHuanSTXXList.stream().filter(n -> n.getShiTuID().equals(scBhJieDianXXModel.getShiTuID())).findFirst().orElse(new SC_BH_ShiTuXXModel());
            //查询 视图节点信息
            SC_BH_ShiTuJDXXModel biHuanSTJDXXByJieID = biHuanSTJDXXList.stream().filter(n -> n.getJieDianID().equals(scBhJieDianXXModel.getJieDianID())).findFirst().orElse(new SC_BH_ShiTuJDXXModel());
            //查询 视图下下的字段信息
            List<SC_BH_ShiTuJDMXModel> biHuanJDMXByJieID = biHuanSTJDMXList.stream().filter(n -> n.getJieDianID().equals(scBhJieDianXXModel.getJieDianID())).toList();
            //节点管理中的关联节点
            List<SC_BH_ShiTuJDGXModel> scBhShiTuJDGXModel = jieDianGXList.stream().filter(n -> n.getJieDianID().equals(scBhJieDianXXModel.getJieDianID())).toList();
            JieDianList jieDianList = new JieDianList();
            List<jieDianNRList> jieDianNRList = new ArrayList<>();


            ShiTuZXJGDto shiTuZXJGDto = shiTuZXJGDtoList.stream().filter(n -> n.getShuJuLYID().equals(biHuanSTXX.getShuJuLYID())
                    && n.getShuJuLYLXDM().equals(biHuanSTXX.getShuJuLYLXDM())).findFirst().orElse(null);
            if (shiTuZXJGDto==null) return new BiHuanXQDto();
            // 在 forEach 循环之前判断是行存储还是列存储
            boolean isRowStorage = Objects.equals(biHuanSTXX.getShiTuLXDM(), "2");
            // 如果是行存储，先过滤出符合条件的映射
            if (isRowStorage && !StringUtil.hasText(biHuanSTJDXXByJieID.getShiJianZDBM())) continue;
            List<Map<String, Object>> filteredMaps = isRowStorage ? shiTuZXJGDto.getShiTuZXJG().stream().filter(map -> Objects.equals(map.get(biHuanSTJDXXByJieID.getShiJianZDBM().toLowerCase()), biHuanSTJDXXByJieID.getShiJianDM())).toList() : shiTuZXJGDto.getShiTuZXJG();

            //处理关联节点
            biHuanJDMXByJieID.forEach(f -> {
                String value;
                value = getValueBasedOnStorage(isRowStorage, filteredMaps, shiTuZXJGDto.getShiTuZXJG(), f.getZiDuanBM());
                // 创建并配置 jieDianNRList 对象
                jieDianNRList biHuanJDNr = new jieDianNRList();
                biHuanJDNr.setZiDuanBM(f.getZiDuanBM());
                biHuanJDNr.setZiDuanMC(f.getZiDuanMC());
                biHuanJDNr.setYunXuWKBZ(f.getYunXuWKBZ());
                biHuanJDNr.setKongZhiSJBZ(f.getKongZhiSJBZ());
                biHuanJDNr.setZiDuanZhi(value);
                if (Objects.equals(f.getKongZhiSJBZ(), 1) && StringUtil.hasText(value)) {
                    jieDianList.setKongZhiSJ(Converter.toDate(value));
                }
                // 将对象添加到列表中
                jieDianNRList.add(biHuanJDNr);
            });
            //获取是否有子闭环
            val ziBiHXXModel = ziBiHXXList.stream().filter(n -> n.getJieDianID().equals(scBhJieDianXXModel.getJieDianID())).findFirst().orElse(null);
            if (ziBiHXXModel != null) {
                //子闭环标志
                jieDianList.setZiBiHBZ("1");
                jieDianList.setZiBiHGLZD(ziBiHXXModel.getZiBiHZDBM());
                jieDianList.setZiBiHGLZDZ(shiTuZXJGDto.getShiTuZXJG().stream().map(k -> {
                    var quZhi = k.getOrDefault(ziBiHXXModel.getGuanLianZDBM().toLowerCase(), "");
                    if (Objects.isNull(quZhi)) {
                        return "";
                    }
                    return quZhi.toString();
                }).findFirst().orElse(""));
                jieDianList.setZiBiHID(ziBiHXXModel.getZiBiHID());
                if (ObjectUtils.equals(jieDianList.getZiBiHGLZDZ(), "")) {
                    throw new TongYongYWException("子闭环关联字段值为空");
                }
            }
            if (!CollectionUtil.isEmpty(ziBiHXSLList.stream().filter(n -> n.getJieDianID().equals(scBhJieDianXXModel.getJieDianID())).toList())) {
                jieDianList.setZiBiHDCZXBZ("1");
            }
            var shiFouSGLJD = scBhShiTuJDGXModel.stream().filter(n -> n.getJieDianID().equals(scBhJieDianXXModel.getJieDianID())).toList();
            //逆节点标志
            jieDianList.setBiXuBZ(scBhJieDianXXModel.getBiXuBZ());
            jieDianList.setBingXingBZ(scBhJieDianXXModel.getBingXingBZ());
            jieDianList.setId(scBhJieDianXXModel.getJieDianID());

            //未执行标志
            if (jieDianNRList.isEmpty()) {
                jieDianList.setWeiZhiXBZ(1);
            }
            jieDianList.setJieDianMC(StringUtil.hasText(scBhJieDianXXModel.getXianShiMC()) ? scBhJieDianXXModel.getXianShiMC() : scBhJieDianXXModel.getJieDianMC());
            //缺失标志
            //闭环
            if (Objects.equals(scBhJieDianXXModel.getBiXuBZ(), 1)) {
                if (jieDianNRList.stream().noneMatch(e -> !Objects.equals(1, e.getYunXuWKBZ()) && ObjectUtils.isEmpty(e.getZiDuanZhi()))) {
                    jieDianList.setQueShiBZ(0);
                } else {
                    jieDianList.setQueShiBZ(1);
                    if (jieDianNRList.stream().allMatch(e -> ObjectUtils.isEmpty(e.getZiDuanZhi())))
                    {
                        jieDianList.setWeiZhiXBZ(1);
                    }
                }
                jieDianList.setJieDianNRList(jieDianNRList);
                jieDianLists.add(jieDianList);

            } else {
                if (jieDianNRList.isEmpty())
                {
                    jieDianList.setJieDianNRList(new ArrayList<>());
                    jieDianLists.add(jieDianList);

                }else {
                    if (jieDianNRList.stream().allMatch(e -> !Objects.equals(1, e.getYunXuWKBZ()) && ObjectUtils.isEmpty(e.getZiDuanZhi()))) {
                        continue;
                    }else
                    {
                        jieDianList.setXianShiBZ(1);
                        jieDianList.setJieDianNRList(jieDianNRList);
                        jieDianLists.add(jieDianList);
                    }
                }
            }
//            关联节点处理
//            处理逆节点内容
            if (!shiFouSGLJD.isEmpty()) {
                for (SC_BH_ShiTuJDGXModel g : shiFouSGLJD) {
                    JieDianList niJieDianXX = new JieDianList();
                    niJieDianXX.setNiJieDBZ(1);
                    niJieDianXX.setJieDianMC(g.getGuanLianJDMC());
                    List<SC_BH_ShiTuJDMXModel> niJieDianMX = biHuanSTJDMXSYMX.stream().filter(n -> n.getJieDianID().equals(g.getGuanLianJDID())).toList();
                    List<jieDianNRList> niJieDianNRList = new ArrayList<>();

                    SC_BH_ShiTuXXModel niJieDianST = biHuanSTXXList.stream().filter(n -> n.getShiTuID().equals(g.getGuanLianJDID())).findFirst().orElse(new SC_BH_ShiTuXXModel());
                    SC_BH_ShiTuJDXXModel niJieDianXXX = biHuanSTJDXXList.stream().filter(n -> n.getJieDianID().equals(g.getGuanLianJDID())).findFirst().orElse(new SC_BH_ShiTuJDXXModel());

                    boolean niJieDianSTLXDM = Objects.equals(niJieDianST.getShiTuLXDM(), "2");
                    if (isRowStorage && !StringUtil.hasText(niJieDianXXX.getShiJianZDBM())) continue;

                    List<Map<String, Object>> mapList = isRowStorage
                            ? shiTuZXJGDto.getShiTuZXJG().stream().filter(map -> Objects.equals(map.get(niJieDianXXX.getShiJianZDBM().toLowerCase()), niJieDianXXX.getShiJianDM())).toList() :  shiTuZXJGDto.getShiTuZXJG();

                    if (mapList.isEmpty()||shiTuZXJGDto.getShiTuZXJG().isEmpty())
                    {
                        continue;
                    }
                    niJieDianMX.forEach(bjdmx -> {
                        String value;
                        if (niJieDianSTLXDM) {
                            value = mapList.stream().map(k -> {
                                var quZhi = k.getOrDefault(bjdmx.getZiDuanBM().toLowerCase(), "");
                                if (Objects.isNull(quZhi)) {
                                    return "";
                                }
                                return quZhi.toString();
                            }).findFirst().orElse("");
                        } else {
                            value = shiTuZXJGDto.getShiTuZXJG().stream().map(k -> {
                                var quZhi = k.getOrDefault(bjdmx.getZiDuanBM().toLowerCase(), "");
                                if (Objects.isNull(quZhi)) {
                                    return "";
                                }
                                return quZhi.toString();
                            }).findFirst().orElse("");
                        }

                        value = getValueBasedOnStorage(isRowStorage, mapList, shiTuZXJGDto.getShiTuZXJG(), bjdmx.getZiDuanBM());
                        jieDianNRList niJieDianNR = new jieDianNRList();
                        niJieDianNR.setZiDuanBM(bjdmx.getZiDuanBM());
                        niJieDianNR.setZiDuanBM(bjdmx.getZiDuanBM());
                        niJieDianNR.setZiDuanMC(bjdmx.getZiDuanMC());
                        niJieDianNR.setYunXuWKBZ(bjdmx.getYunXuWKBZ());
                        niJieDianNR.setKongZhiSJBZ(bjdmx.getKongZhiSJBZ());
                        niJieDianNR.setZiDuanZhi(value);
                        if (Objects.equals(bjdmx.getKongZhiSJBZ(), 1) && StringUtil.hasText(value)) {
                            niJieDianXX.setKongZhiSJ(Converter.toDate(value));
                        }
                        niJieDianNRList.add(niJieDianNR);
                    });
                    if (niJieDianNRList.stream().noneMatch(e -> !Objects.equals(1, e.getYunXuWKBZ()) && ObjectUtils.isEmpty(e.getZiDuanZhi())) && !niJieDianNRList.isEmpty()) {
                        niJieDianXX.setJieDianNRList(niJieDianNRList);
                        jieDianLists.add(niJieDianXX);
                    }

                }
            }
        }
        //处理时效问题，用控制时间字段 做比较
        //循环 得出的节点信息，在去找时效，中的数据 对应比较 如果有异常就给异常标志
        processTimeValidity(jieDianLists,biHuanJDSXList);
//        jieDianLists.forEach(j -> {
//            //当前这个节点下 的时效
//            List<SC_BH_JieDianSXModel> jieDianSXList = biHuanJDSXList.stream().filter(n -> n.getJieDianID().equals(j.getId())).toList();
//            //遍历 节点时效
//            List<ShiXiaoList> shiXiaoLists = new ArrayList<>();
//            jieDianSXList.forEach(p -> {
//                var jieDianList = jieDianLists.stream().filter(d->Objects.nonNull(d.getId())).filter(d -> d.getId().equals(p.getGuanLianJDID())).findFirst().orElse(null);
//                if (jieDianList != null) {
//                    if (j.getKongZhiSJ() == null || jieDianList.getKongZhiSJ() == null) {
//                        ShiXiaoList shiXiaoList = new ShiXiaoList();
//                        shiXiaoList.setShiXiaoYCBZ("1");
//                        shiXiaoList.setShiXiaoYCMS("时效异常配置控制时间异常");
//                        shiXiaoLists.add(shiXiaoList);
//                    } else {
//                        //时效秒
//                        int shiXiaoM = DateUtil.getSecondsBetween(j.getKongZhiSJ(), jieDianList.getKongZhiSJ());
//                        if (isTimeInvalid(p.getYunSuanFDM(), p.getDanWeiDM(), p.getShiXiao(), shiXiaoM)) {
//                            ShiXiaoList shiXiaoList = new ShiXiaoList();
//                            shiXiaoList.setShiXiaoYCBZ("1");
//                            shiXiaoList.setShiXiaoYCMS("时效异常" + shiXiaoM + "秒");
//                            shiXiaoLists.add(shiXiaoList);
//                        }
//                    }
//
//                }
//            });
//            j.setShiXiaoLists(shiXiaoLists);
//        });
//        });
        biHuanXQDto.setJieDianList(bingXingJDPX(jieDianLists));
        return biHuanXQDto;
    }

   private List<JieDianList> bingXingJDPX(List<JieDianList> jieDianLists)
   {
       // 提取满足条件的元素并排序
       List<JieDianList> sortedList = jieDianLists.stream()
               .filter(j -> Objects.equals(j.getBingXingBZ(),1))
               .sorted(Comparator.comparing(JieDianList::getKongZhiSJ))
               .toList();
       // 如果节点中没有 返回当前的顺序 不去排序
       if (CollectionUtil.isEmpty(sortedList)) {
           return jieDianLists;
       }
       // 遍历原始列表，并用排序后的元素替换满足条件的元素
       int sortedIndex = 0;
       for (int i = 0; i < jieDianLists.size(); i++) {
           if (jieDianLists.get(i).getBingXingBZ() == 1) {
               jieDianLists.set(i, sortedList.get(sortedIndex++));
           }
       }
       return jieDianLists;

   }

    private void processTimeValidity(List<JieDianList> jieDianLists, List<SC_BH_JieDianSXModel> biHuanJDSXList) {
        jieDianLists.stream().filter(n->!Objects.equals(n.getQueShiBZ(),1)&&!Objects.equals(n.getWeiZhiXBZ(),1) ).forEach(j -> {
            List<SC_BH_JieDianSXModel> jieDianSXList = biHuanJDSXList.stream()
                    .filter(n -> n.getJieDianID().equals(j.getId())).toList();
            List<ShiXiaoList> shiXiaoLists = new ArrayList<>();

            jieDianSXList.forEach(p -> {
                JieDianList jieDianList = jieDianLists.stream()
                        .filter(d -> Objects.nonNull(d.getId()) && d.getId().equals(p.getGuanLianJDID()))
                        .findFirst().orElse(null);

                if (jieDianList != null) {
                    shiXiaoLists.addAll(checkTimeValidity(j, jieDianList, p));
                }
            });
            j.setShiXiaoLists(shiXiaoLists);
        });
    }

    private List<ShiXiaoList> checkTimeValidity(JieDianList sourceJieDian, JieDianList targetJieDian, SC_BH_JieDianSXModel p) {
        List<ShiXiaoList> shiXiaoLists = new ArrayList<>();
        if (sourceJieDian.getKongZhiSJ() == null || targetJieDian.getKongZhiSJ() == null) {
            ShiXiaoList shiXiaoList = new ShiXiaoList();
            shiXiaoList.setShiXiaoYCBZ("1");
            shiXiaoList.setShiXiaoYCMS("时效异常配置控制时间异常");
            shiXiaoLists.add(shiXiaoList);
        } else {
            int shiXiaoM = DateUtil.getSecondsBetween(sourceJieDian.getKongZhiSJ(), targetJieDian.getKongZhiSJ());
            if (isTimeInvalid(p.getYunSuanFDM(), p.getDanWeiDM(), p.getShiXiao(), shiXiaoM)) {
                ShiXiaoList shiXiaoList = new ShiXiaoList();
                shiXiaoList.setShiXiaoYCBZ("1");
                shiXiaoList.setShiXiaoYCMS("时效异常" + shiXiaoM + "秒");
                shiXiaoLists.add(shiXiaoList);
            }
        }
        return shiXiaoLists;
    }


    private boolean isTimeInvalid(String operator, String yunSuanFDM, BigDecimal time, int value) {
        // 转换time为秒
        if ("HH".equals(yunSuanFDM)) {
            time = time.multiply(BigDecimal.valueOf(3600)); // 将小时转换为秒
        } else if ("MM".equals(yunSuanFDM)) {
            time = time.multiply(BigDecimal.valueOf(60)); // 将分钟转换为秒
        }
        switch (operator) {
            case ">":
                return time.compareTo(BigDecimal.valueOf(value)) <= 0;
            case "<":
                return time.compareTo(BigDecimal.valueOf(value)) >= 0;
            case ">=":
                return time.compareTo(BigDecimal.valueOf(value)) < 0;
            case "<=":
                return time.compareTo(BigDecimal.valueOf(value)) > 0;
            default:
                return false;
        }
    }

    /**
     * 获取视图表关系
     *
     * @param table 表信息
     * @return String
     */
    private String getShiTuBGX(TableDTO table,String zuHuID,String zuZhiJGID) {
        StringBuilder builder = new StringBuilder();
        builder.append("select ");
        //字段名列表
        List<String> ziDuanMingList = new ArrayList<>();
        //表名列表
        Set<String> biaoMingList = new HashSet<>();
        boolean isNeiLian = !"1".equals(table.getGuanLianFSDM());
        //获取视图中表关系
        String relationCondition = Optional.ofNullable(table.getTableRelationConditionList()).orElse("");
        //获取视图中过滤条件
        String filterCondition = Optional.ofNullable(table.getFilterConditionList()).orElse("");
        int index = 0;
        StringBuilder zuoFeiBZ= new StringBuilder();
        StringBuilder zuZhiJGIDTJ= new StringBuilder();
        StringBuilder zuHuIDTJ= new StringBuilder();
        for (SchemaTable f : table.getSchemaTableList()) {
            String key = formatBiaoMing(f.getMoShi(), f.getBiaoMing());
            for (ShuJuJMXZDDto p : Optional.ofNullable(f.getShuJuJMXZDDtos()).orElse(new ArrayList<>())) {
                ziDuanMingList.add(isNeiLian ? "l" + index + "." + p.getZiDuanBM() : f.getBiaoMing() + "." + p.getZiDuanBM());
            }
            if (isNeiLian) {
                zuoFeiBZ.append(" And l").append(index).append(".zuoFeiBZ=0");
                zuZhiJGIDTJ.append(" And l").append(index).append(".zuzhijgid=").append("'").append(zuZhiJGID).append("'");
                zuHuIDTJ.append(" And l").append(index).append(".zuhuid=").append("'").append(zuHuID).append("'");
            }else {
//                zuoFeiBZ.append(" And ").append(f.getBiaoMing()).append(".zuoFeiBZ=0");
//                zuZhiJGIDTJ.append(" And ").append(f.getBiaoMing()).append(".zuzhijgid=").append("'").append(zuZhiJGID).append("'");
//                zuHuIDTJ.append(" And ").append(f.getBiaoMing()).append(".zuhuid=").append("'").append(zuHuID).append("'");
            }
            if (!isNeiLian) {
                continue;
            }
            biaoMingList.add(key + " l" + index);
            relationCondition = relationCondition.replaceAll("(?i)" + key, "l" + index);
            filterCondition = filterCondition.replaceAll("(?i)" + key, "l" + index);

            index++;
            }
        builder.append(CollUtil.join(ziDuanMingList, ","));
        builder.append(" from ");
        builder.append(isNeiLian ? CollUtil.join(biaoMingList, ",") : relationCondition);
        builder.append(" where 1=1 ");
        if (StringUtils.isNotBlank(filterCondition)  ) {
            builder.append(" AND ");
            builder.append(filterCondition);
            builder.append(zuoFeiBZ);
            builder.append(zuZhiJGIDTJ);
            builder.append(zuHuIDTJ);
        }
        if (StringUtils.isNotBlank(relationCondition) && isNeiLian) {
            builder.append(" and ");
            builder.append(relationCondition);
        }
        return builder.toString();
    }

    /**
     * 格式化表名
     *
     * @param moShi
     * @param biaoMing
     * @return String
     */
    private String formatBiaoMing(String moShi, String biaoMing) {
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isNotBlank(moShi)) {
            builder.append(moShi + ".");
        }
        builder.append(biaoMing);
        return builder.toString().toLowerCase();
    }

    /**
     * 取所有表明 用于查询
     *
     * @param guiZeList 规则
     * @return 表明
     */
    public Set<String> extractTableNames(List<GuiZeDto> guiZeList) {
        // 使用flatMap将所有guiZeList中的ShiTu对象扁平化为一个流，然后映射每个ShiTu对象的biaoMing属性
        // 使用collect收集结果到Set中，这样可以自动去重
        return guiZeList.stream().flatMap(guiZeDto -> guiZeDto.getGuiZeList().stream()).map(ShiTu::getBiaoMing).collect(Collectors.toSet());
    }

    /**
     * 解析规则
     *
     * @param guiZeList 规则
     * @return sql
     */
    public static String parseGuiZeListToSql(List<GuiZeDto> guiZeList) {
        List<String> orConditions = new ArrayList<>();

        for (GuiZeDto guiZeDto : guiZeList) {
            List<String> andConditions = new ArrayList<>();

            for (ShiTu shiTu : guiZeDto.getGuiZeList()) {
                String tableName = shiTu.getBiaoMing();
                String fieldName = shiTu.getZiDuanBM();
                String fullFieldName = tableName + "." + fieldName;
                String operator = shiTu.getGuanXiDM();
                List<ZiDuanZhi> ziduanZhiList = shiTu.getZiDuanZhi();

                if (!ziduanZhiList.isEmpty()) {
                    List<String> values = ziduanZhiList.stream().map(ziDuanZhi -> "'" + ziDuanZhi.getValue() + "'").collect(Collectors.toList());

                    String condition;
                    String s = values.stream().findFirst().orElse("''");
                    if ("=".equals(operator) && s.equals("''")) {
                        // 单个值使用 "="
                        condition = String.format("%s is null ", fullFieldName);
                    } else if ("=".equals(operator) && values.size() == 1) {
                        condition = String.format("%s = %s", fullFieldName, values.get(0));
                    } else {
                        // 多个值或者操作符为 "in" 使用 "IN"
                        condition = String.format("%s IN (%s)", fullFieldName, String.join(", ", values));
                    }
                    andConditions.add(condition);
                }
            }

            if (!andConditions.isEmpty()) {
                orConditions.add("(" + String.join(" AND ", andConditions) + ")");
            }
        }

        return String.join(" AND ", orConditions);
    }

    //取值单独抽取逻辑
    public String getValueBasedOnStorage(boolean isRowStorage, List<Map<String, Object>> filteredMaps, List<Map<String, Object>> maps, String ziDuanBM) {
        if (isRowStorage) {
            return filteredMaps.stream().map(k -> {
                var quZhi = k.getOrDefault(ziDuanBM.toLowerCase(), "");
                if (Objects.isNull(quZhi)) {
                    return "";
                }
                return quZhi.toString();
            }).findFirst().orElse("");
        } else {
            return maps.stream().map(k -> {
                var quZhi = k.getOrDefault(ziDuanBM.toLowerCase(), "");
                if (Objects.isNull(quZhi)) {
                    return "";
                }
                return quZhi.toString();
            }).findFirst().orElse("");
        }
    }
}

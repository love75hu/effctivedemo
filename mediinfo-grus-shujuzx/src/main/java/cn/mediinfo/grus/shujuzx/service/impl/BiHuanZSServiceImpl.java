package cn.mediinfo.grus.shujuzx.service.impl;

import aj.org.objectweb.asm.TypeReference;
import cn.hutool.core.collection.CollUtil;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.util.JsonUtil;
import cn.mediinfo.cyan.msf.core.util.Tuple;
import cn.mediinfo.grus.shujuzx.dto.bihuandy.SC_BH_DiaoYongPZDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_JieDianXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_ZiBiHXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuanzs.*;
import cn.mediinfo.grus.shujuzx.dto.fangan.BingLiSCDTO;
import cn.mediinfo.grus.shujuzx.dto.shitumx.SchemaTable;
import cn.mediinfo.grus.shujuzx.dto.shitumx.ShuJuJMXZDDto;
import cn.mediinfo.grus.shujuzx.dto.shitumx.ShuJuLYDto;
import cn.mediinfo.grus.shujuzx.dto.shitumx.TableDTO;
import cn.mediinfo.grus.shujuzx.model.*;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.GY_ZD_ShuJuSTXXRso;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.GuanLianGXDto;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.ShuJuXXMSRso;
import cn.mediinfo.grus.shujuzx.remoteservice.GongYongRemoteService;
import cn.mediinfo.grus.shujuzx.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.mediinfo.grus.shujuzx.service.BiHuanZSService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.io.IOException;
import java.util.*;
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
    private SC_BH_DiaoYongPZRepository  diaoYongPZRepository;

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
    /**
     * 闭环节点关系
     */
    private SC_BH_ShiTuJDGXRepository shiTuJDGXRepository;


    @Resource
    @Qualifier("datasourcesjzx_jdbcTemplateFactory")
    JdbcTemplate jdbcTemplate;

    /**
     * 根据闭环功能点和相关入参获取闭环详情
     *
     */
    public BiHuanXQDto getBiHuanXQ(BiHuanGNDPZ biHuanGNDPZ) throws YuanChengException {

        //当前功能点的调用配置
        List<SC_BH_DiaoYongPZDto> biHuanPZList = diaoYongPZRepository.getBiHuanPZList(biHuanGNDPZ.getBiHuanGNDDM());
        //获取配的闭环
        var biHuanIDs=biHuanPZList.stream().map(SC_BH_DiaoYongPZDto::getBiHuanID).toList();

        //闭环调用信息
        List<String>  guoLuhuanIDs=new ArrayList<>();
        for (var b:biHuanPZList)
        {
            List<GuiZeDto> jsonToList = JsonUtil.getJsonToList(b.getTiaoJian(), GuiZeDto.class);

            //视图id
            List<String> shituIDs=jsonToList.stream().flatMap(guiZeDto -> guiZeDto.getGuiZeList().stream()).map(ShiTu::getShiTuID).distinct().toList();

            //获取数据视图信息
            List<GY_ZD_ShuJuSTXXRso> shuJuSTXXList  = gongYongRemoteService.getShuJuSTXXList(shituIDs).getData("获取数据视图信息");
            //获取数据视图明细信息
            var biaoMing=extractTableNames(jsonToList);

            List<ShuJuXXMSRso> biaoMoshi = gongYongRemoteService.getShuJXXMS(new ArrayList<>(biaoMing)).getData("获取表模式失败");

            String tiaoJian = parseGuiZeListToSql(jsonToList);
            String fullTableName = biaoMoshi.stream()
                    .findFirst()
                    .map(s -> s.getShuJuYMC() + "." + s.getBiaoMing())
                    .orElse("");

            StringBuilder joinBuilder = new StringBuilder();

            if (biaoMing.size() > 1) {
                // If multiple tables are involved, construct JOIN clauses
                for (GY_ZD_ShuJuSTXXRso stxx : shuJuSTXXList) {
                    List<GuanLianGXDto> guanLianList = JsonUtil.getJsonToList(stxx.getGuanLianGX(), GuanLianGXDto.class);
                    for (GuanLianGXDto guanLian : guanLianList) {
                        joinBuilder.append(" INNER JOIN ")
                                .append(guanLian.getGuanLianSJYMC())
                                .append(".")
                                .append(guanLian.getGuanLianBM())
                                .append(" ON ")
                                .append(guanLian.getBiaoMing())
                                .append(".")
                                .append(guanLian.getZiDuanBM())
                                .append(" = ")
                                .append(guanLian.getGuanLianBM())
                                .append(".")
                                .append(guanLian.getGuanLianZDBM())
                                .append(" ");
                    }
                }
            }

            String sql = "SELECT COUNT(*) FROM " + fullTableName + " " + joinBuilder + " WHERE " + tiaoJian;

            Long count = jdbcTemplate.queryForObject(sql, Long.class);
            if (count!=null && count>0)
            {
                guoLuhuanIDs.add(b.getBiHuanID());
            }
        }
        //获取闭环入参信息
        var biHuanRCXXList=ruCanXXRepository.findByBiHuanIDIn(biHuanIDs);
        //入参字段
        var ziDuanBM=biHuanGNDPZ.getRuCanList().stream().map(ZiDuanBMMC::getZiDuanBM).toList();
        //匹配 闭环调用的  的所有闭环，匹配入参是否一致
        var zuiZhongBiHuanID=new ArrayList<String>();
        for (var b:guoLuhuanIDs)
        {
            long count = biHuanRCXXList.stream().filter(n -> n.getBiHuanID().equals(b) && ziDuanBM.contains(n.getZiDuanBM())).count();
            if(count==ziDuanBM.size())
            {
                zuiZhongBiHuanID.add(b);
            }
        }
        //闭环 执行的逻辑

        getBiHuanZXJG(biHuanIDs.get(0),"0",biHuanGNDPZ.getRuCanList());



        return null;
    }

    /**
     * 根据闭环id获取闭配置信息去执行sql
     *
     */
    public BiHuanXQDto getBiHuanZXJG(String biHuanID,String zuZhiJGID, List<ZiDuanBMMC> ruCanList) throws YuanChengException
    {
        //获取闭环基本信息
        //1.获取闭环基本信息 SELECT * FROM SC_BH_JIBENXX;-- 闭环基本信息
        //2.获取闭环入参信息  SELECT * FROM SC_BH_RUCANXX;-- 闭环入参信息
        //3.获取闭环下的节点信息 SELECT * FROM SC_BH_JIEDIANXX;-- 闭环节点信息
        //4.在获取节点下的 节点时效
        //5.在获取节点下的 子闭环信信息
        //6.在获取子闭环的显示列

        //1.获取闭环基本信息
        SC_BH_JiBenXXModel biHuanJBXX = jiBenXXRepository.findFirstByBiHuanIDAndZuZhiJGID(biHuanID,zuZhiJGID);
        //2.获取闭环入参信息
        List<SC_BH_RuCanXXModel> biHuanRCXXList = ruCanXXRepository.findByBiHuanIDAndZuZhiJGID(biHuanID,zuZhiJGID);
        //3.获取闭环下的节点信息
        List<SC_BH_JieDianXXModel> biHuanJDXXList = jieDianXXRepository.findByBiHuanIDAndZuZhiJGIDOrderByShunXuHao(biHuanID,zuZhiJGID);
        //4.在获取节点下的 节点时效
        List<SC_BH_JieDianSXModel> biHuanJDSXList = jieDianSXRepository.findByBiHuanIDAndZuZhiJGID(biHuanID, zuZhiJGID);
        //5.在获取节点下的 子闭环信信息
        SC_BH_ZiBiHXXModel ziBiHXX = ziBiHXXRepository.findFirstByBiHuanIDAndZuZhiJGID(biHuanID, zuZhiJGID);
        //6.在获取子闭环的显示列
        List<SC_BH_ZiBiHXSLModel> biHuanZBHXSLList = ziBiHXSLRepository.findByBiHuanIDAndZuZhiJGID(biHuanID, zuZhiJGID);

        List<String> ruCanZDBMs = biHuanRCXXList.stream().map(n -> n.getZiDuanBM()).distinct().toList();
        //视图id 整理
        List<String> shituIDs=biHuanJDXXList.stream().map(SC_BH_JieDianXXModel::getShiTuID).distinct().toList();
        //节点id 整理
        List<String> jieDianIDs=biHuanJDXXList.stream().map(SC_BH_JieDianXXModel::getJieDianID).distinct().toList();
        //获取闭环视图数据
        List<SC_BH_ShiTuXXModel> biHuanSTXXList = shiTuXXRepository.findByShiTuIDIn(shituIDs);
        //获取闭环节点数据
        List<SC_BH_ShiTuJDXXModel> biHuanSTJDXXList = shiTuJDXXRepository.findByJieDianIDIn(jieDianIDs);
        //获取节点明细数据
        List<SC_BH_ShiTuJDMXModel> biHuanSTJDMXList = shiTuJDMXRepository.findByJieDianIDIn(jieDianIDs);

        BiHuanXQDto biHuanXQDto=new BiHuanXQDto();
        biHuanXQDto.setBiHuanID(biHuanID);
        biHuanXQDto.setBiHuanMC(biHuanJBXX.getBiHuanMC());


        List<ShuJuLYDto> shuJuLYDtos=new ArrayList<>();
        biHuanSTXXList.forEach(s->{
            ShuJuLYDto shuJuLYDto=new ShuJuLYDto();
            shuJuLYDto.setShuJuLYID(s.getShuJuLYID());
            shuJuLYDto.setShuJuLYLXDM(s.getShuJuLYLXDM());
            shuJuLYDtos.add(shuJuLYDto);
        });


        List<TableDTO> tableList = gongYongRemoteService.getShiTuGLFSGLTJ(shuJuLYDtos).getData("获取功能服务字段信息失败");

        StringBuilder builder = new StringBuilder();
        tableList.get(0).getSchemaTableList().forEach(p->{
            List<ShuJuJMXZDDto> shujMX = p.getShuJuJMXZDDtos().stream().filter(n -> ruCanZDBMs.contains(n.getZiDuanBM())).toList();

            for (int i=0;i<shujMX.size();i++)
            {
                int finalI = i;
                ZiDuanBMMC ziDuanBMMC = ruCanList.stream().filter(n ->
                        n.getZiDuanBM().equals(shujMX.get(finalI).getZiDuanBM())).findFirst().orElse(null);
                builder.append(p.getMoShi())
                        .append(".")
                        .append(p.getBiaoMing())
                        .append(".")
                        .append(shujMX.get(finalI).getZiDuanBM())
                        .append("=")
                        .append("'")
                        .append(ziDuanBMMC.getZiDuanZhi())
                        .append("'");
                if (i < shujMX.size() - 1) { // 如果不是最后一个元素，则添加"and"
                    builder.append(" and ");
                }
            }
        });
        tableList.get(0).setFilterConditionList(builder.toString());

        String sql = getShiTuBGX(tableList.get(0));

        //执行sql 得出结果
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        //执行完视图 获取节点的数据集合
       List<JieDianList> jieDianLists=new ArrayList<>();
        biHuanJDXXList.forEach(j->{
            //获取节点下的 节点时效
            List<SC_BH_JieDianSXModel> jieDianSXList = biHuanJDSXList.stream().filter(n -> n.getJieDianID().equals(j.getJieDianID())).toList();
            //获取节点下的 子闭环信信息
            List<SC_BH_ZiBiHXXModel> ziBiHXXList = ziBiHXXRepository.findByBiHuanIDAndZuZhiJGIDAndZuZhiJGMC(biHuanID, zuZhiJGID, j.getJieDianMC());
            //获取子闭环的显示列
            List<SC_BH_ZiBiHXSLModel> ziBiHXSLList = biHuanZBHXSLList.stream().filter(n -> n.getJieDianID().equals(j.getJieDianID())).toList();

            //查询 视图信息
            SC_BH_ShiTuXXModel biHuanSTXX = biHuanSTXXList.stream().filter(n -> n.getShiTuID().equals(j.getShiTuID())).findFirst().orElse(new SC_BH_ShiTuXXModel());

            //查询 视图节点信息
            SC_BH_ShiTuJDXXModel biHuanSTJDXXByJieID = biHuanSTJDXXList.stream().filter(n -> n.getJieDianID().equals(j.getJieDianID())).findFirst().orElse(null);
            //查询 视图下下的字段信息
            List<SC_BH_ShiTuJDMXModel> biHuanJDMXByJieID = biHuanSTJDMXList.stream().filter(n -> n.getJieDianID().equals(j.getJieDianID())).toList();

            JieDianList jieDianList=new JieDianList();
            List<jieDianNRList> jieDianNRList=new ArrayList<>();

            //为行存储的情况
            if (Objects.equals(biHuanSTXX.getShiTuLXDM(),2))
            {
                //事件字段编码
                var shiJianZDBM=biHuanSTJDXXByJieID.getShiJianZDBM();
                //事件字段值
                var shiJianDM=biHuanSTJDXXByJieID.getShiJianDM();

                List<Map<String, Object>> collect = maps.stream().filter(map -> shiJianDM.equals(map.get(shiJianZDBM))).collect(Collectors.toList());

                biHuanJDMXByJieID.forEach(f->{
                    String o = collect.stream().map(k -> k.get(f.getZiDuanBM()).toString()).findFirst().orElse("");
                    jieDianNRList biHuanJDNr=new jieDianNRList();
                    biHuanJDNr.setZiDuanBM(f.getZiDuanBM());
                    biHuanJDNr.setZiDuanZhi(o);
                    biHuanJDNr.setKongZhiSJBZ(f.getKongZhiSJBZ());
                    biHuanJDNr.setYunXuWKBZ(f.getYunXuWKBZ());
                    jieDianNRList.add(biHuanJDNr);
                });

            }else {
                //为列存储的情况
                biHuanJDMXByJieID.forEach(f->{
                    String s = maps.stream().map(k -> k.get(f.getZiDuanBM()).toString()).findFirst().orElse("");
                    jieDianNRList biHuanJDNr=new jieDianNRList();
                    biHuanJDNr.setZiDuanBM(f.getZiDuanBM());
                    biHuanJDNr.setZiDuanZhi(s);
                    biHuanJDNr.setKongZhiSJBZ(f.getKongZhiSJBZ());
                    biHuanJDNr.setYunXuWKBZ(f.getYunXuWKBZ());
                    jieDianNRList.add(biHuanJDNr);
                });
            }
            jieDianList.setJieDianList(jieDianNRList);
            jieDianLists.add(jieDianList);

        });

        return null;
    }

    /**
     * 获取视图表关系
     *
     * @param table     表信息
     * @return String
     */
    private String getShiTuBGX(TableDTO table) {
            StringBuilder builder = new StringBuilder();

        builder.append("select ");
        //字段名列表
        List<String> ziDuanMingList = new ArrayList<>();
        //表名列表
        Set<String> biaoMingList = new HashSet<>();
        //获取视图中表关系
        String relationCondition = Optional.ofNullable(table.getTableRelationConditionList()).orElse("");
        //获取视图中过滤条件
        String filterCondition = Optional.ofNullable(table.getFilterConditionList()).orElse("");
        int index = 0;
        for (SchemaTable f : table.getSchemaTableList()) {
            String key = formatBiaoMing(f.getMoShi(), f.getBiaoMing());
            for (ShuJuJMXZDDto p : Optional.ofNullable(f.getShuJuJMXZDDtos()).orElse(new ArrayList<>())) {
                ziDuanMingList.add("l" + index + "." + p.getZiDuanBM());
            }
            biaoMingList.add(key + " l" + index);
            relationCondition = relationCondition.replaceAll("(?i)" + key, "l" + index);
            filterCondition = filterCondition.replaceAll("(?i)" + key, "l" + index);
            index++;
        }
        builder.append(CollUtil.join(ziDuanMingList, ","));
        builder.append(" from ");
        builder.append(CollUtil.join(biaoMingList, " INNER JOIN "));

        if (StringUtils.isNotBlank(relationCondition)) {
            builder.append(" ON ");
            builder.append(relationCondition);
        }
        builder.append(" where 1=1 ");
        if (StringUtils.isNotBlank(filterCondition)) {
            builder.append(" AND ");
            builder.append(filterCondition);
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
     *  取所有表明 用于查询
     * @param guiZeList 规则
     * @return 表明
     */
    public Set<String> extractTableNames(List<GuiZeDto> guiZeList) {
        // 使用flatMap将所有guiZeList中的ShiTu对象扁平化为一个流，然后映射每个ShiTu对象的biaoMing属性
        // 使用collect收集结果到Set中，这样可以自动去重
        return guiZeList.stream()
                .flatMap(guiZeDto -> guiZeDto.getGuiZeList().stream())
                .map(ShiTu::getBiaoMing)
                .collect(Collectors.toSet());
    }
    /**
     * 解析规则
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
                    List<String> values = ziduanZhiList.stream()
                            .map(ziDuanZhi -> "'" + ziDuanZhi.getValue() + "'")
                            .collect(Collectors.toList());

                    String condition;
                    if ("=".equals(operator) && values.size() == 1) {
                        // 单个值使用 "="
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

        return String.join(" OR ", orConditions);
    }
}

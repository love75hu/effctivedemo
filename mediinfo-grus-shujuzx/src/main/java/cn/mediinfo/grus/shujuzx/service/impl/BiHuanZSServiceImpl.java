package cn.mediinfo.grus.shujuzx.service.impl;

import aj.org.objectweb.asm.TypeReference;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.util.JsonUtil;
import cn.mediinfo.grus.shujuzx.dto.bihuandy.SC_BH_DiaoYongPZDto;
import cn.mediinfo.grus.shujuzx.dto.bihuanzs.*;
import cn.mediinfo.grus.shujuzx.dto.fangan.BingLiSCDTO;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.GY_ZD_ShuJuSTXXRso;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.GuanLianGXDto;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.ShuJuXXMSRso;
import cn.mediinfo.grus.shujuzx.remoteservice.GongYongRemoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_DiaoYongPZRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_RuCanXXRepository;
import cn.mediinfo.grus.shujuzx.service.BiHuanZSService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

        return null;
    }

    /**
     * 根据闭环id获取闭配置信息去执行sql
     *
     */
    public BiHuanXQDto getBiHuanZXJG(String biHuanID)
    {

        return null;
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

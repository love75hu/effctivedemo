package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.shitumx.DatabaseDTO;
import cn.mediinfo.grus.shujuzx.dto.shitumx.FieldDTO;
import cn.mediinfo.grus.shujuzx.dto.shitumx.TableDTO;

import java.util.List;
import java.util.Set;

public interface ShiTuMXService {

    /**
     * 获取视图配置的字段信息
     * @param shiTuMXIds 视图明细id
     * @return 字段信息
     */
     List<FieldDTO> listFields(Set<String> shiTuMXIds);

    /**
     * 获取视图配置的表信息
     * @param shiTuMXIds 视图明细id
     * @return 表信息
     */
     List<TableDTO> listTable(Set<String> shiTuMXIds);

    /**
     * 获取视图配置的数据库配置信息
     * @param shiTuMXIds 视图明细id
     * @return 表信息
     */
     List<DatabaseDTO> listDatabase(Set<String> shiTuMXIds);
}

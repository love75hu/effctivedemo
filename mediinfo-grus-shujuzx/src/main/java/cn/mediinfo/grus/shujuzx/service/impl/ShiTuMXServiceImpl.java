package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.dto.shitumx.DatabaseDTO;
import cn.mediinfo.grus.shujuzx.dto.shitumx.FieldDTO;
import cn.mediinfo.grus.shujuzx.dto.shitumx.TableDTO;
import cn.mediinfo.grus.shujuzx.service.ShiTuMXService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class ShiTuMXServiceImpl implements ShiTuMXService {


    @Override
    public List<FieldDTO> listFields(Set<String> shiTuMXIds) {
        //todo 未实现
        return Collections.emptyList();
    }

    @Override
    public List<TableDTO> listTable(Set<String> shiTuMXIds) {
        //todo 未实现
        return Collections.emptyList();
    }

    @Override
    public List<DatabaseDTO> listDatabase(Set<String> shiTuMXIds) {
        //todo  未实现
        return Collections.emptyList();
    }
}

package cn.mediinfo.grus.shujuzx.remoteservice;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.shitumx.FieldDTO;
import cn.mediinfo.grus.shujuzx.dto.shitumx.SC_CX_ShiTuXXZDDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "mediinfo-lyra-gongyong", path = "mediinfo-lyra-gongyong")
public interface GongYongRemoteService {

    /**
     * 获取配置字段信息
     *
     * @return
     */
    @PostMapping("api/v1.0/shujust/getShiTuZDXXList")
    MsfResponse<List<FieldDTO>> getShiTuZDXXList(@RequestBody List<SC_CX_ShiTuXXZDDto> dtos);
}

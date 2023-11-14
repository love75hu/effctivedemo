package cn.mediinfo.grus.shujuzx.remoteservice;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.cda.gongwei.DA_GA_JiBenXXDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient( value = "mediinfo-grus-gongwei-query",path = "mediinfo-grus-gongwei-query")
public interface GongWeiRemoteService {

    @GetMapping("api/v1.0/GeRenDA/GetGeAnJBXX")
    MsfResponse<DA_GA_JiBenXXDto> getB0001Data(@RequestParam List<String> mpiList);
}


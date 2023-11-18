package cn.mediinfo.grus.shujuzx.remoteservice;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.cda.gongwei.DA_GA_JiBenXXDto;
import cn.mediinfo.grus.shujuzx.dto.wendangscs.B0001ShuJuJDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient( value = "mediinfo-grus-gongwei-query",path = "mediinfo-grus-gongwei-query")
public interface GongWeiRemoteService {

    @GetMapping("api/v1.0/GeRenDA/GetGeAnJBXX")
    MsfResponse<DA_GA_JiBenXXDto> getB0001Data(@RequestParam List<String> mpiList);
    @PostMapping ("api/v1.0/wendangsc/GetB0001")
    MsfResponse<B0001ShuJuJDto> GetB0001(@RequestBody List<String> mpiList);

}


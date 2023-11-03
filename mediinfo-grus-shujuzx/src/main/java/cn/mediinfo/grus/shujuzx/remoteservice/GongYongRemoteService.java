package cn.mediinfo.grus.shujuzx.remoteservice;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.shitumx.*;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.LingChuangJSPZZDXXRso;
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

    @PostMapping("api/v1.0/shujust/getShiTuGLFSGLTJ")
    MsfResponse<List<TableDTO>> getShiTuGLFSGLTJ(@RequestBody List<ShuJuLYDto> shuJuLYDtos);


    /**
     * 根据视图类型获取数据视图中字段信息列表
     *
     * @return
     */
    @PostMapping ("/api/v1.0/shujust/getlingChuangJSPZZDXX")
    MsfResponse<List<LingChuangJSPZZDXXRso>> getlingChuangJSPZZDXX(@RequestBody List<LingChuangJSPZDto> dtos);

}

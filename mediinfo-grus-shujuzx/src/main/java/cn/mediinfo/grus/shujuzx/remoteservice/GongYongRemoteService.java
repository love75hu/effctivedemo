package cn.mediinfo.grus.shujuzx.remoteservice;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.shitumx.*;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
    MsfResponse<List<TableDTO>> getShiTuGLFSGLTJ(@RequestBody List<LingChuangJSPZDto> lingChuangJSPZDtos);

    /**
     * 根据视图类型获取数据视图中字段信息列表
     *
     * @return
     */
    @PostMapping ("/api/v1.0/shujust/getlingChuangJSPZZDXX")
    MsfResponse<List<LingChuangJSPZZDXXRso>> getlingChuangJSPZZDXX(@RequestBody List<LingChuangJSPZDto> dtos);

    /**
     * 根据表名获取模式信息
     * @param biaoMing 表名（大写）
     * @return
     */
    @GetMapping("api/v1.0/shujujmx/getShuJXXMS")
    MsfResponse<List<ShuJuXXMSRso>> getShuJXXMS(@RequestParam(required = false)List<String> biaoMing);

    @PostMapping("api/v1.0/shujust/getShuJuSTXXList")
    MsfResponse<List<GY_ZD_ShuJuSTXXRso>> getShuJuSTXXList(@RequestBody List<String> shiTuId);
    /**
     * 获取数据源树
     * @return
     */
    @GetMapping("api/v1.0/shujuyuan/GetShuJuYFLTree")
    MsfResponse<List<GY_ZD_ShuJuYuanTreeRso>> getShuJuYFLTree();

    @GetMapping("api/v1.0/shujust/getZiDuanXXList")
    MsfResponse<List<YuanSuJXXRso>> getZiDuanXXList(@RequestParam("id") String id,@RequestParam("leiXing") String leiXing,@RequestParam("likeQuery") String likeQuery);


}

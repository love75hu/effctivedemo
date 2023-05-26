package cn.mediinfo.grus.shujuzx.controller;
import cn.mediinfo.grus.shujuzx.service.ZhuSuoYGLService;
import cn.mediinfo.grus.shujuzx.service.ZhuSuoYCZRZService;
import cn.mediinfo.grus.shujuzx.service.ZhuSuoYHBGZService;
import cn.mediinfo.grus.shujuzx.service.ZhuSuoYQZPZService;
import cn.mediinfo.grus.shujuzx.service.BingRenJBXXService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "ShuJuZXZSYController", description = "数据中心-主索引")
@RequestMapping({"api/v1.0/ShuJuZXZSY", "api/v1/ShuJuZXZSY"})
@Slf4j
@Validated
public class ShuJuZXZSYController {

    private final ZhuSuoYGLService zhuSuoYGLService;
    private final ZhuSuoYCZRZService zhuSuoYCZRZService;
    private final ZhuSuoYHBGZService zhuSuoYHBGZService;
    private final ZhuSuoYQZPZService zhuSuoYQZPZService;
    private final BingRenJBXXService bingRenJBXXService;



    public ShuJuZXZSYController(ZhuSuoYGLService zhuSuoYGLService,
                                ZhuSuoYCZRZService zhuSuoYCZRZService,
                                ZhuSuoYHBGZService zhuSuoYHBGZService,
                                ZhuSuoYQZPZService zhuSuoYQZPZService,
                                BingRenJBXXService bingRenJBXXService)
    {
        this.zhuSuoYGLService=zhuSuoYGLService;
        this.zhuSuoYCZRZService=zhuSuoYCZRZService;
        this.zhuSuoYHBGZService=zhuSuoYHBGZService;
        this.zhuSuoYQZPZService=zhuSuoYQZPZService;
        this.bingRenJBXXService=bingRenJBXXService;
    }

}

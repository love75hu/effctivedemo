package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.aqua.scheduler.annotation.JobDefinition;
import cn.mediinfo.cyan.aqua.scheduler.impl.DefaultJobDetailImpl;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.repository.SC_RW_JiBenXXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_RW_TongYongPZRepository;
import cn.mediinfo.grus.shujuzx.service.RenWuDDService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/RenWuDD")
public class RenWuDDController {
    private final RenWuDDService renWuDDService;
    public RenWuDDController(RenWuDDService renWuDDService){
        this.renWuDDService=renWuDDService;

    }

    @JobDefinition(name = "BI_WD_RIQI", description = "日期")
    @GetMapping()
    public MsfResponse<Boolean> jobs(DefaultJobDetailImpl a) {

        return MsfResponse.success(renWuDDService.saveRenWuZX("BI_WD_RIQI"));
        
    }
}

package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.aqua.scheduler.JobExecutionContext;
import cn.mediinfo.cyan.aqua.scheduler.annotation.JobDefinition;
import cn.mediinfo.cyan.aqua.scheduler.api.SchedulerService;
import cn.mediinfo.cyan.aqua.scheduler.api.contanst.ConCurrent;
import cn.mediinfo.cyan.aqua.scheduler.api.contanst.MisFirePolicy;
import cn.mediinfo.cyan.aqua.scheduler.api.contanst.TriggerType;
import cn.mediinfo.cyan.aqua.scheduler.api.dto.job.ScheduleCurrentServiceJobCreateDto;
import cn.mediinfo.cyan.aqua.scheduler.api.dto.job.ScheduleCurrentServiceJobUpdateDto;
import cn.mediinfo.cyan.aqua.scheduler.impl.DefaultJobDetailImpl;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.http.HttpMethod;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.cyan.msf.security.IdentityProperties;
import cn.mediinfo.cyan.msf.security.IdentityService;
import cn.mediinfo.grus.shujuzx.constant.ETLZhiXingFSConstant;
import cn.mediinfo.grus.shujuzx.service.RenWuDDService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRotY;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.mediinfo.cyan.aqua.scheduler.api.dto.trigger.TriggerCreateDto;

@RestController
@Tag(name = "RenWuGLController", description = "ETL管理-任务管理")
@RequestMapping({"api/v1.0/RenWuDD", "api/v1/RenWuDD"})
@Slf4j
@Validated

public class RenWuDDController {
    private final RenWuDDService renWuDDService;

    public RenWuDDController(RenWuDDService renWuDDService){
        this.renWuDDService=renWuDDService;
    }

    @JobDefinition(name = "BI_WD_RIQI", description = "日期")
    @GetMapping()
    public MsfResponse<String> testJobs(DefaultJobDetailImpl a,JobExecutionContext context) {
        log.info("任务调度开始了");
        var ziDongBZ= context.getTrigger().getName().startsWith("MT_");//手动执行的触发器
        String zhiXingFSDM= ziDongBZ ? ETLZhiXingFSConstant.shouDongZXDM:ETLZhiXingFSConstant.ziDongZXDM;
        String zhiXingFSMC=ziDongBZ? ETLZhiXingFSConstant.shouDongZXMC:ETLZhiXingFSConstant.ziDongZXMC;
        return MsfResponse.success(renWuDDService.saveRenWuZX("BI_WD_RIQI",zhiXingFSDM,zhiXingFSMC));
    }

    @GetMapping(path = "zhiXingRW")
    public MsfResponse<String> zhiXingRW(JobExecutionContext context,String renWuMC) {
        var ziDongBZ= context.getTrigger().getName().startsWith("MT_");//手动执行的触发器
        String zhiXingFSDM=ziDongBZ? ETLZhiXingFSConstant.shouDongZXDM:ETLZhiXingFSConstant.ziDongZXDM;
        String zhiXingFSMC=ziDongBZ? ETLZhiXingFSConstant.shouDongZXMC:ETLZhiXingFSConstant.ziDongZXMC;
      //  String zhiXingFSDM=ETLZhiXingFSConstant.shouDongZXDM;
      //  String zhiXingFSMC=ETLZhiXingFSConstant.shouDongZXMC;
        return MsfResponse.success(renWuDDService.saveRenWuZX(renWuMC,zhiXingFSDM,zhiXingFSMC));
    }

}

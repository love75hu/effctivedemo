package cn.mediinfo.grus.shujuzx.dto.renwugls;

import cn.mediinfo.cyan.msf.core.http.HttpMethod;
import lombok.Data;

@Data
public class ScheduleCreateDto {
    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务分组
     */
    private String jobGroup;

    /**
     * 请求url
     */
    private String requestUrl;

    /**
     * 请求类型
     */
    private HttpMethod httpMethod;

    /**
     * job描述
     */
    private String jobDescription;


}

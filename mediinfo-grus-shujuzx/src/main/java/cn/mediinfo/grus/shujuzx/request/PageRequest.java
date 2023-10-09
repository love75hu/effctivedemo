package cn.mediinfo.grus.shujuzx.request;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 分页
 */
@Getter
@Setter
@ToString
public abstract class PageRequest {

    @Parameter(description = "当前页")
    @Min(value = 1,message = "pageIndex不能小于1")
    protected int pageIndex;


    @Parameter(description = "每页大小")
    @Min(value = 1,message = "pageSize不能小于1")
    protected int pageSize;
}

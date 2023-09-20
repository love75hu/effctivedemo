package cn.mediinfo.grus.shujuzx.events;

import lombok.Data;
/**
 * 业务信息
 */
@Data
public class YeWuXXEventDto<T> {
    YeWuXXEventDto()
    {
        bingRenXX = new BingRenXXEventDto();
        setBingRenXX(bingRenXX);
    }
    private BingRenXXEventDto bingRenXX ;

    private T qiTaYWXX = null;
}

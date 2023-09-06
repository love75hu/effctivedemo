package cn.mediinfo.grus.shujuzx.utils;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class SpringCache {

    // 从缓存中获取数据
    @Cacheable(value = "ZhuSuoYGL", key = "#a")
    public String get(String a) {
        return "";
    }
    // 更新数据并存入缓存
    @CachePut(value = "ZhuSuoYGL", key = "#a")
    public String put(String a,String b) {
        return b ;
    }

}

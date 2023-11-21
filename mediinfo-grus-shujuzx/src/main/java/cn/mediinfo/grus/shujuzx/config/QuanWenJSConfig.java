package cn.mediinfo.grus.shujuzx.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mediinfo.zhparser")
@Data
public class QuanWenJSConfig {
    private Integer state;
}

package cn.mediinfo.grus.shujuzx;

import cn.mediinfo.cyan.msf.eventbus.annotation.MsfEnableEventBus;
import cn.mediinfo.cyan.msf.service.MsfSpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.TimeZone;

@EnableCaching
@MsfSpringBootApplication
@MsfEnableEventBus
public class MediinfoGrusShujuzxApplication {

        public static void main(String[] args) {
           // TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
            SpringApplication.run(MediinfoGrusShujuzxApplication.class, args);
        }

}

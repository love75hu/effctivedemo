package cn.mediinfo.grus.shujuzx;

import cn.mediinfo.cyan.msf.service.MsfSpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@MsfSpringBootApplication
public class MediinfoGrusShujuzxApplication {

        public static void main(String[] args) {
            SpringApplication.run(MediinfoGrusShujuzxApplication.class, args);
        }

}

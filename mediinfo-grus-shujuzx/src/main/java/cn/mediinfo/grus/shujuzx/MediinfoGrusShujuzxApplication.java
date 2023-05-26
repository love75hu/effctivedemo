package cn.mediinfo.grus.shujuzx;

import cn.mediinfo.starter.base.MsfSpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MsfSpringBootApplication
public class MediinfoGrusShujuzxApplication {

        public static void main(String[] args) {
            SpringApplication.run(MediinfoGrusShujuzxApplication.class, args);
        }

}

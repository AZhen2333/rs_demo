package qzlife.net.zhrs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@MapperScan(basePackages = "qzlife.net.zhrs.*.mapper")
@SpringBootApplication
public class ZhrsApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ZhrsApplication.class, args);
    }


}

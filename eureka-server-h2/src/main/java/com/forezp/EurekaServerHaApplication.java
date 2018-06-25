package com.forezp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * EurekaServer集群高可用注册中心以及简单的安全认证。<br/>
 *
 * Eureka默认端口是8761
 * http://localhost:8761/eureka/apps 可以查看注册到该服务器上的一堆微服务实例的信息。
 *
 * @author hmilyylimh
 *
 * @version 0.0.1
 *
 * @date 2017/10/25
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerHaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerHaApplication.class, args);
        System.out.println("【【【【【【 EurekaHa微服务 】】】】】】已启动.");
    }
}




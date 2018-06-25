package com.forezp.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.forezp.entity.User;

import config.TestFeignCustomConfiguration;
import feign.Param;
import feign.RequestLine;

/**
 * 用户Http请求的客户端，FeignClient 注解地方采用了自定义的配置。
 *
 * 注解FeignClient的传参：表示的是注册到 Eureka 服务上的模块名称。
 *
 * @author hmilyylimh
 *
 * @version 0.0.1
 *
 * @date 2017/9/24
 *
 */
@FeignClient(name = "service-user", configuration = TestFeignCustomConfiguration.class, fallback = UserFeignCustomClientFallback.class)
public interface UserFeignCustomClient {

    /**
     * 
     * @param id
     * @return
     */
    @RequestLine("GET /simple/{id}")
    public User findById(@Param("id") Long id);
}

package com.springms.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * 链接Oracle数据库简单的集成Mybatis框架访问数据库。
 *
 * @author dfd
 *
 * @version 0.0.1
 *
 * @date 2018-6-19
 *
 */
@Controller
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@ServletComponentScan
@MapperScan("com.springms.cloud.mapper.**")
public class MsProviderUserMysqlMybatisApplication extends SpringBootServletInitializer  implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(MsProviderUserMysqlMybatisApplication.class, args);
		System.out.println("【【【【【【 用户微服务 】】】】】】已启动.");
	}
    // Java EE应用服务器配置，
    // 如果要使用tomcat来加载jsp的话就必须继承SpringBootServletInitializer类并且重写其中configure方法
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@RequestMapping("/login")
	String home() {
		return "login";
	}
	
	@RequestMapping("/404")
	String notFound() {
		return "common/404";
	}
	
	@RequestMapping("/500")
	String error() {
		return "common/500";
	}
}




/****************************************************************************************
 注意：Mybatis 需要加上 entity 等注解才可以使用，不然启动都会报错；
 @MapperScan("com.springms.cloud.mapper.**") 或者在每个 Mapper 接口文件上添加 @Mapper 也可以；

 一、简单用户链接oracle数据库微服务（通过 mybatis 链接 oracle 编写数据访问）：
  **首先启动服务注册中心eureka
 1、启动 springms-provider-user-oracle-mybatis 模块服务，启动1个端口；
 2、在浏览器输入地址 http://localhost:8325/simple/1 可以看到用户ID=11的信息成功的被打印出来；
 3、使用 IDEA 自带工具或者用其他工具（postman） Test Restful WebService 发送 HTTP POST 请求,并添加 username、age、balance三个参数，然后执行请求，并去 mysql 数据库查看数据是否存在，正常情况下 mysql 数据库刚刚插入的数据成功了:
 4、使用 REST Client 执行 "/simple/list" 接口，也正常将 mysql 数据库中所有的用户信息打印出来了；
 ****************************************************************************************/


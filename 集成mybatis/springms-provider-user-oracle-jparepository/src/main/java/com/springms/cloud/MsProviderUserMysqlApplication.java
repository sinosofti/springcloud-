package com.springms.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 链接oracle数据库,通过JpaRepository编写数据库访问。
 *
 * @author dfd
 *
 * @version 0.0.1
 *
 * @date 2018-6-19
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class MsProviderUserMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProviderUserMysqlApplication.class, args);
		System.out.println("【【【【【【用户微服务 】】】】】】已启动.");
	}
}




/****************************************************************************************
 一、链接Mysql数据库,通过JpaRepository编写数据库访问：

 1、启动 springms-provider-user-oracle-jparepository 模块服务，启动1个端口；
 2、在浏览器输入地址 http://localhost:8310/simple/10 可以看到用户ID=10的信息成功的被打印出来；

 3、使用 IDEA 自带工具 Test Restful WebService 发送 HTTP POST 请求,并添加 username、age、balance三个参数，然后执行请求，并去 mysql 数据库查看数据是否存在，正常情况下 mysql 数据库刚刚插入的数据成功了:
 4、使用 REST Client 执行 "/simple/list" 接口，也正常将 mysql 数据库中所有的用户信息打印出来了；
 ****************************************************************************************/


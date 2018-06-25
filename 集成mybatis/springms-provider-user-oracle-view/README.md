## 一、大致介绍

``` 
springcloud集成mybatis（注解版）
```


## 二、实现步骤

### 2.1 添加 maven 引用包
``` 
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<artifactId>springms-provider-user-oracle-mybatis</artifactId>
	<version>1.0-SNAPSHOT</version>
	<packaging>jar</packaging>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.5.2.RELEASE</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<dependencies>
		<!-- 访问数据库模块 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<!-- 客户端发现模块 -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-eureka</artifactId>
		</dependency>
		<!-- web模块 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>com.oracle</groupId>
			<artifactId>ojdbc6</artifactId>
			<version>11.1.0.7.0</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope>
		</dependency>
		<!-- MYSQL -->
		<!-- <dependency>
			<groupId>oracle</groupId>
			<artifactId>oracle-connector-java</artifactId>
		</dependency> -->
		<!-- 使用数据源 -->
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>druid</artifactId>
			<version>1.0.14</version>
		</dependency>
		<!-- Mybatis依赖 -->
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.3.1</version>
		</dependency>
	</dependencies>
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>Dalston.RC1</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
	<repositories>
		<repository>
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/milestone</url>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</repository>
	</repositories>

</project>

```


### 2.2 添加应用配置文件（springms-provider-user-oracle-mybatis\src\main\resources\application.yml）
``` 
server:
  port: 8325
spring:
  application:
    name: springms-provider-user-oracle-mybatis  #全部小写


#####################################################################################################
spring:
    application:
        name: springms-provider-user-mysql-mybatis  #全部小写 
    datasource:
        url: jdbc:oracle:thin:@192.168.100.95:1521:orcl
        username: base
        password: embed
        # 使用druid数据源
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: oracle.jdbc.driver.OracleDriver
        # 下面为连接池的补充设置，应用到上面所有数据源中
        # 初始化大小，最小，最大
        initialSize: 5
        minIdle: 5
        maxActive: 20
        # 配置获取连接等待超时的时间
        maxWait: 3000
        # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        timeBetweenEvictionRunsMillis: 60000
        # 配置一个连接在池中最小生存的时间，单位是毫秒
        minEvictableIdleTimeMillis: 300000
        validationQuery: SELECT 1 FROM DUAL
        testWhileIdle: true
        testOnBorrow: false
        testOnReturn: false
        # 打开PSCache，并且指定每个连接上PSCache的大小
        poolPreparedStatements: true
        maxPoolPreparedStatementPerConnectionSize: 20
        # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
        spring.datasource.filters: stat,wall,log4j
        # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
        connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
        # 合并多个DruidDataSource的监控数据
        #useGlobalDataSourceStat: true
        debug: true
#####################################################################################################


#####################################################################################################
# 打印日志
logging:
  level:
    root: INFO
    org.hibernate: INFO
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
    org.hibernate.type.descriptor.sql.BasicExtractor: TRACE
    com.springms: DEBUG
#####################################################################################################

添加其他配置例如： 数据源、mybatis等
```



### 2.3 添加实体用户类User（springms-provider-user-oracle-mybatis/src/main/java/com/springms/cloud/entity/User.java）
``` 
package com.springms.cloud.entity;


import javax.persistence.*;

/**
 *
 * Mybatis 需要加上这些注解才可以使用，不然启动都会报错；
 *
 */
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column
  private String username;

  @Column
  private String name;

  @Column
  private Integer age;

  @Column
  private String balance;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return this.age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getBalance() {
    return this.balance;
  }

  public void setBalance(String balance) {
    this.balance = balance;
  }
}

```



### 2.4 添加用户mapper接口（springms-provider-user-oracle-mybatis/src/main/java/com/springms/cloud/mapper/IUserMapper.java）
``` 
package com.springms.cloud.mapper;

import com.springms.cloud.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户 mybatis 映射文件。
 *
 * @author dfd
 *
 * @version 0.0.1
 *
 * @date 2018-6-19
 *
 */
public interface IUserMapper {

    @Select("select * from user where id = #{id}")
    User findUserById(Long id);

    @Select("select * from user")
    List<User> findAllUsers();

    @Insert("INSERT INTO user(username, name, age, balance) VALUES(#{username}, #{name}, #{age}, #{balance})")
    int insertUser(User user);
}
```



### 2.5 添加用户mapper接口实现类（springms-provider-user-oracle-mybatis/src/main/java/com/springms/cloud/mapper/user/UserMapper.java）
``` 
package com.springms.cloud.mapper.user;

/**
 *
 *
 * @author dfd
 *
 * @version 0.0.1
 *
 * @date 2018-6-19
 */
public class UserMapper {
}

```



### 2.6 添加用户Service接口类（springms-provider-user-oracle-mybatis/src/main/java/com/springms/cloud/service/IUserService.java）
``` 
package com.springms.cloud.service;

import com.springms.cloud.entity.User;

import java.util.List;

/**
 * 简单用户链接Oracle数据库微服务（通过@Service注解标注该类为持久化操作对象）。
 *
 * @author dfd
 *
 * @version 0.0.1
 *
 * @date 2018-6-19
 *
 */
public interface IUserService {

    User findUserById(Long id);

    List<User> findAllUsers();

    int insertUser(User user);
}

```


### 2.7 添加用户Service接口实现类（springms-provider-user-oracle-mybatis/src/main/java/com/springms/cloud/service/impl/UserServiceImpl.java）
``` 
package com.springms.cloud.service.impl;

import com.springms.cloud.entity.User;
import com.springms.cloud.mapper.IUserMapper;
import com.springms.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 简单用户链接Oracle数据库微服务（通过@Service注解标注该类为持久化操作对象）。
 *
 * @author dfd
 *
 * @version 0.0.1
 *
 * @date 2018-6-19
 *
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserMapper iUserMapper;

    @Override
    public User findUserById(Long id) {
        return iUserMapper.findUserById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return iUserMapper.findAllUsers();
    }

    @Override
    public int insertUser(User user) {
        return iUserMapper.insertUser(user);
    }
}
```


### 2.8 添加用户Web访问层Controller（springms-provider-user-oracle-mybatis/src/main/java/com/springms/cloud/controller/ProviderUserOracleMybatisController.java）
``` 
package com.springms.cloud.controller;

import com.springms.cloud.entity.User;
import com.springms.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户微服务Controller。
 *
 * @author dfd
 *
 * @version 0.0.1
 *
 * @date 2018-6-19
 *
 */
@RestController
public class ProviderUserOracleMybatisController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/simple/{id}")
    public User findUserById(@PathVariable Long id) {
        return this.iUserService.findUserById(id);
    }

    @GetMapping("/simple/list")
    public List<User> findUserList() {
        return this.iUserService.findAllUsers();
    }

    /**
     * 添加一个student,使用postMapping接收post请求
     *
     * http://localhost:8310/simple/addUser?username=user11&age=11&balance=11
     *
     * @return
     */
    @PostMapping("/simple/addUser")
    public User addUser(@RequestParam(value = "username", required=false) String username, @RequestParam(value = "age", required=false) Integer age, @RequestParam(value = "balance", required=false) String balance){
        User user=new User();

        user.setUsername(username);
        user.setName(username);
        user.setAge(age);
        user.setBalance(balance);

        int result = iUserService.insertUser(user);
        if(result > 0){
            return user;
        }

        user.setId(0L);
        user.setName(null);
        user.setUsername(null);
        user.setBalance(null);
        return user;
    }
}

```


### 2.9 添加微服务启动类（springms-provider-user-oracle-mybatis/src/main/java/com/springms/cloud/MsProviderUserOracleMybatisApplication.java）
``` 
package com.springms.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
@SpringBootApplication
@MapperScan("com.springms.cloud.mapper.**")
public class MsProviderUserOracleMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProviderUserOracleMybatisApplication.class, args);
		System.out.println("【【【【【【 链接OracleMybatis数据库微服务 】】】】】】已启动.");
	}
}

```



## 三、测试

``` 
/****************************************************************************************
 注意：Mybatis 需要加上 entity 等注解才可以使用，不然启动都会报错；
 @MapperScan("com.springms.cloud.mapper.**") 或者在每个 Mapper 接口文件上添加 @Mapper 也可以；

 一、简单用户链接Oracle数据库微服务（通过 mybatis 链接 oracle 编写数据访问）：

 1、启动 springms-provider-user-oracle-mybatis 模块服务，启动1个端口；
 2、在浏览器输入地址 http://localhost:8325/simple/10 可以看到用户ID=10的信息成功的被打印出来；

 3、使用 IDEA 自带工具 Test Restful WebService 发送 HTTP POST 请求,并添加 username、age、balance三个参数，然后执行请求，并去 oracle 数据库查看数据是否存在，正常情况下 oracle 数据库刚刚插入的数据成功了:
 4、使用 REST Client 执行 "/simple/list" 接口，也正常将 oracle 数据库中所有的用户信息打印出来了；
 ****************************************************************************************/

```




## 四、下载地址

https://github.com/sinosofti/springcloud






























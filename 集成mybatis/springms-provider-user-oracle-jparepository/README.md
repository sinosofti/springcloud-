## 一、大致介绍

``` 
spring data jpa 链接oracle数据库
spring data jpa详解请看博客  https://www.cnblogs.com/dreamroute/p/5173896.html；
```

## 二、实现步骤

### 2.1 添加 maven 引用包
``` 
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

	<artifactId>springms-provider-user-oracle-jparepository</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>
	
    <parent>
        <groupId>com.springms.cloud</groupId>
        <artifactId>springms-spring-cloud</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
	
	<dependencies>
        <!-- 访问数据库模块 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- web模块 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- MYSQL -->
        <dependency>
            <groupId>oracle</groupId>
            <artifactId>oracle-connector-java</artifactId>
        </dependency>
    </dependencies>

</project>
```


### 2.2 添加应用配置文件（springms-provider-user-oracle-jparepository\src\main\resources\application.yml）
``` 
server:
  port: 8310
spring:
  application:
    name: springms-provider-user-oracle-jparepository  #全部小写


#####################################################################################################
# oracle 属性配置
  datasource:
    driver-class-name: com.oracle.jdbc.Driver
    url: jdbc:oracle://ip:3306/hmilyylimh
    username: username
    password: password
  jpa:
    hibernate:
      #ddl-auto: create #ddl-auto:设为create表示每次都重新建表
      ddl-auto: update #ddl-auto:设为update表示每次都不会重新建表
    show-sql: true
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


```



### 2.3 添加访问底层数据模型的DAO接口（springms-provider-user-oracle-jparepository/src/main/java/com/springms/cloud/repository/UserRepository.java）
``` 
package com.springms.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springms.cloud.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
```

### 2.4 添加实体用户类User（springms-provider-user-oracle-jparepository/src/main/java/com/springms/cloud/entity/User.java）
``` 
package com.springms.cloud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

### 2.5 添加用户Web访问层Controller（springms-provider-user-oracle-jparepository/src/main/java/com/springms/cloud/controller/ProviderUserOracleController.java）
``` 
package com.springms.cloud.controller;

import com.springms.cloud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springms.cloud.entity.User;

import java.util.List;

/**
 * 用户微服务Controller。
 *
 * @author hmilyylimh
 *
 * @version 0.0.1
 *
 * @date 17/10/18
 *
 */
@RestController
public class ProviderUserOracleController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/simple/{id}")
    public User findUserById(@PathVariable Long id) {
        return this.userRepository.findOne(id);
    }

    @GetMapping("/simple/list")
    public List<User> findUserList() {
        return this.userRepository.findAll();
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

        return userRepository.save(user);
    }
}


```


### 2.6 添加用户微服务启动类（springms-provider-user-oracle-jparepository/src/main/java/com/springms/cloud/MsProviderUserOracleApplication.java）
``` 
package com.springms.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 链接Oracle数据库,通过JpaRepository编写数据库访问。
 *
 * @author hmilyylimh
 *
 * @version 0.0.1
 *
 * @date 17/10/18
 *
 */
@SpringBootApplication
public class MsProviderUserOracleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProviderUserOracleApplication.class, args);
		System.out.println("【【【【【【 链接Oracle数据库微服务 】】】】】】已启动.");
	}
}

```



## 三、测试

``` 
/****************************************************************************************
 一、链接Oracle数据库,通过JpaRepository编写数据库访问：

 1、启动 springms-provider-user-oracle-jparepository 模块服务，启动1个端口；
 2、在浏览器输入地址 http://localhost:8310/simple/10 可以看到用户ID=10的信息成功的被打印出来；

 3、使用 IDEA 自带工具 Test Restful WebService 发送 HTTP POST 请求,并添加 username、age、balance三个参数，然后执行请求，并去 oracle 数据库查看数据是否存在，正常情况下 oracle 数据库刚刚插入的数据成功了:
 4、使用 REST Client 执行 "/simple/list" 接口，也正常将 oracle 数据库中所有的用户信息打印出来了；
 ****************************************************************************************/

```



























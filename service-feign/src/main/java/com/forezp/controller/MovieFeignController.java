package com.forezp.controller;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forezp.entity.User;
import com.forezp.feign.UserFeignClient;

/**
 * 电影 Feign 控制器，通过 Feign 访问远端服务。
 *
 * @author hmilyylimh
 *
 * @version 0.0.1
 *
 * @date 17/9/19
 *
 */
@RestController
public class MovieFeignController {

    @Autowired
    private UserFeignClient userFeignClient;

    @RequestMapping("/index")
    public String helloJsp(Map<String,Object> map){
    
        return "index";
    }
    @GetMapping("/movie/{id}")
    public User findById(@PathVariable Long id) {
        return userFeignClient.findById(id);
    }

    @GetMapping("/movie/user")
    public User postUser(User user){
        Random random = new Random();
        User tmpUser = new User();
        long id = (long) random.nextInt(100);
        tmpUser.setId(id);
        tmpUser.setName("TempUser" + id);
        tmpUser.setAge((short) id);

        return userFeignClient.postUser(tmpUser);
    }
}

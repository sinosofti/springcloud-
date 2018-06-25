package com.forezp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.forezp.entity.User;
import com.forezp.repository.UserRepository;
import com.google.common.collect.Lists;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private EurekaClient discoveryClient;

    @GetMapping("/simple/{id}")
    @CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,  
            RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,  
            RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
    public User findById(@PathVariable Long id){
        return userRepository.findOne(id);
    }

//    public String serviceUrl(){
//        InstanceInfo instance = this.discoveryClient.getNextServerFromEureka("SPRINGMS-PROVIDER-USER", false);
//        return instance.getHomePageUrl();
//    }

    @PostMapping("/user")
    public User postUser(@RequestBody User user){
        System.out.println("@GetMapping(\"user\") 接收参数对象 user: " + user);
        return user;
    }

    @GetMapping("listAll")
    public List<User> listAll(){
        ArrayList<User> list = Lists.newArrayList();
        User user1 = new User(1L, "user1");
        User user2 = new User(1L, "user2");
        User user3 = new User(1L, "user3");
        User user4 = new User(1L, "user4");
        User user5 = new User(1L, "user5");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        return list;
    }
}

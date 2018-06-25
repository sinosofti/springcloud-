package com.springms.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springms.cloud.common.annotation.ControllerLog;
import com.springms.cloud.common.pojo.AjaxResult;
import com.springms.cloud.service.LoginService;

/**
 * 登录Controller
 */
@Controller
@RequestMapping("/admin/")
public class LoginController{
	
	@Autowired
	private LoginService loginService;

	/**
	 * 登录
	 * @param response
	 * @param userName
	 * @param password
	 * @return
	 */
	@ControllerLog("登录")
	@RequestMapping("login")
	@ResponseBody
    public AjaxResult login(@RequestParam("username") String username,@RequestParam("password") String password) {
        return loginService.login(username,password);
    }
}

package com.springms.cloud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springms.cloud.common.annotation.ControllerLog;
import com.springms.cloud.common.pojo.AjaxResult;
import com.springms.cloud.entity.User;
import com.springms.cloud.service.IUserService;
import com.springms.cloud.service.LoginService;

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
@Controller
public class ProviderUserMysqlMybatisController {

	@Autowired
	private IUserService iUserService;

	@GetMapping("/simple/{id}")
	@ResponseBody
	public User findUserById(@PathVariable Long id) {
		return this.iUserService.findUserById(id);
	}

	@GetMapping("/simple/list")
	@ResponseBody
	public List<User> findUserList() {
		return this.iUserService.findAllUsers();
	}

	/**
	 * 添加一个用戶,使用postMapping接收post请求
	 *
	 * http://localhost:8325/simple/addUser?username=user11&age=11&balance=11
	 *
	 * @return
	 */
	@PostMapping("/simple/addUser")
	@ResponseBody
	public User addUser(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "age", required = false) Integer age,
			@RequestParam(value = "balance", required = false) String balance) {
		User user = new User();
		user.setUsername(username);
		user.setName(username);
		user.setAge(age);
		user.setBalance(balance);

		int result = iUserService.insertUser(user);
		if (result > 0) {
			return user;
		}

		user.setId("0");
		user.setName(null);
		user.setUsername(null);
		user.setBalance(null);
		return user;
	}
}

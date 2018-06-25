package com.springms.cloud.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springms.cloud.common.pojo.AjaxResult;
import com.springms.cloud.common.pojo.PageAjax;
import com.springms.cloud.entity.User;
import com.springms.cloud.service.IUserService;

@Controller
@RequestMapping("/admin/user/")
public class UserController {
	@Autowired
	private IUserService iUserService;
	/**
	 * 查询用户列表
	 * @return
	 */
	@RequestMapping("queryPage")
	@ResponseBody
	public PageAjax<User> queryPage() {
		return iUserService.findAllUsers();
	}
	/**
	 * 添加用户
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(@RequestParam("username") String username,@RequestParam("name") String name
			,@RequestParam("age") Integer age,@RequestParam("balance") String balance) {
		User user = new User();
		user.setAge(age);
		user.setBalance(balance);
		user.setName(name);
		user.setUsername(username);
		return iUserService.insertUser(user);
	}

	
	@RequestMapping("updatePage/{id}") 
	@ResponseBody
	public User updatePage(@PathVariable("id") String id) {
		return iUserService.findUserById(id);
	}
	
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(@RequestParam("id") String id,@RequestParam("username") String username,@RequestParam("name") String name
			,@RequestParam("age") Integer age,@RequestParam("balance") String balance) {
		User user = new User();
		user.setId(id);
		user.setAge(age);
		user.setBalance(balance);
		user.setName(name);
		user.setUsername(username);
		return iUserService.updateUser(user);
	}

	@RequestMapping("deleteByID/{id}")
	@ResponseBody
	public AjaxResult deleteByID(@PathVariable("id") String id) {
		return iUserService.deleteByID(id);
	}
}

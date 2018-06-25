package com.springms.cloud.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.springms.cloud.client.AccountFeignClient;
import com.springms.cloud.common.annotation.ControllerLog;
import com.springms.cloud.common.pojo.AjaxResult;
import com.springms.cloud.common.utils.PageAjax;
import com.springms.cloud.entity.User;

/**
 * 登录Controller
 * 
 * @author dfd
 */
@Controller
@RequestMapping("/admin/")
public class LoginController {

	@Autowired
	private AccountFeignClient acountFeignClient;

	/**
	 * 查询用户名密码
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public AjaxResult findById(@RequestParam("username") String username, @RequestParam("password") String password) {
		return acountFeignClient.login(username, password);
	}

	/**
	 * 登录成功进入主界面
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("main")
	public String main() {
		return "common/main";
	}

	/**
	 * 跳转用户qry页面
	 * 
	 * @param map
	 * @return
	 */

	@RequestMapping("user/mainPage")
	public String mainPage(Map<String, Object> map) {
		return "auth/user/main";
	}

	@ControllerLog("查询用户列表")
	@RequestMapping("user/queryPage")
	@ResponseBody
	public PageAjax<User> queryPage() {
		return acountFeignClient.findAllUsers();
	}

	@RequestMapping("user/addPage")
	public String addPage(Map<String, Object> map) {
		return "auth/user/add";
	}

	/**
	 * 添加用户
	 * 
	 * @return
	 */
	@RequestMapping("user/add")
	@ResponseBody
	public AjaxResult add(@RequestParam("username") String username, @RequestParam("name") String name,
			@RequestParam("age") Integer age, @RequestParam("balance") String balance) {
		return acountFeignClient.add(username, name, age, balance);
	}
	/**
	 * 跳转修改保存
	 * 
	 * @return
	 */
	@RequestMapping("user/deleteByID/{id}")
	@ResponseBody
	public AjaxResult deleteByID(@PathVariable("id") String id) {
		return acountFeignClient.deleteByID(id);
	}
	@RequestMapping("user/updatePage/{id}") 
	public String updatePage(@PathVariable("id") String id, Map<String, Object> map) {
		User user = acountFeignClient.updatePage(id);
		map.put("user", user);
		return "auth/user/update";
	}
	@RequestMapping("user/update")
	@ResponseBody
	public AjaxResult update(@RequestParam("id") String id,@RequestParam("username") String username,@RequestParam("name") String name
			,@RequestParam("age") Integer age,@RequestParam("balance") String balance) {
		return acountFeignClient.update(id, username, name, age, balance);
	}
}

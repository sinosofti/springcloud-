package com.springms.cloud.client;

import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springms.cloud.client.hystrix.AccountFeignClientHystrix;
import com.springms.cloud.common.pojo.AjaxResult;
import com.springms.cloud.common.utils.PageAjax;
import com.springms.cloud.entity.User;

import feign.Logger;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

/**
 * feign与@RequestParam配合使用时，一定要写value值。
 * feign方法的@RequestMapping，务必与服务端方法保持一致，请求类型，请求参数，返回值等等
 * 
 * TODO 可以从服务端定义一个接口层，服务实现层实现接口，调用方扩展此接口，即可完成接口定义的复用，而无须在此重新复制一次。
 * 但此举会导致服务端接口变动后，调用方就会直接受影响，建议事先约定好规则
 * 
 * @author dfd
 *
 */
@FeignClient(name = "SPRINGMS-PROVIDER-USER-ORACLE-MYBATIS-MAPPER", fallback = AccountFeignClientHystrix.class)
public interface AccountFeignClient {

	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/admin/login")
	public AjaxResult login(@RequestParam("username") String username, @RequestParam("password") String password);

	/**
	 * 获取用户列表
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/admin/user/queryPage")
	public PageAjax<User> findAllUsers();

	/**
	 * 添加用户
	 * 
	 * @return
	 */
	@RequestMapping("/admin/user/add")
	public AjaxResult add(@RequestParam("username") String username, @RequestParam("name") String name,
			@RequestParam("age") Integer age, @RequestParam("balance") String balance);

	/**
	 * 删除用户
	 * 
	 * @return
	 */
	@RequestMapping("/admin/user/deleteByID/{id}")
	public AjaxResult deleteByID(@PathVariable("id") String id);

	/**
	 * 跳转修改用户
	 * 
	 * @return
	 */
	@RequestMapping("/admin/user/updatePage/{id}")
	public User updatePage(@PathVariable("id") String id);

	/**
	 * 保存修改用户
	 * 
	 * @return
	 */
	@RequestMapping("/admin/user/update")
	public AjaxResult update(@RequestParam("id") String id, @RequestParam("username") String username,
			@RequestParam("name") String name, @RequestParam("age") Integer age,
			@RequestParam("balance") String balance);
}

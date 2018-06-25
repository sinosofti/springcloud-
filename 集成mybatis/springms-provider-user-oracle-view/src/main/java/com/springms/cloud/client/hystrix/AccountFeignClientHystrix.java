package com.springms.cloud.client.hystrix;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.springms.cloud.client.AccountFeignClient;
import com.springms.cloud.common.pojo.AjaxResult;
import com.springms.cloud.common.utils.AppUtil;
import com.springms.cloud.common.utils.PageAjax;
import com.springms.cloud.entity.User;

@Component
public class AccountFeignClientHystrix implements AccountFeignClient {

	@Override
	public AjaxResult login(String username, String password) {
		return new AjaxResult("create failed!");
	}

	@Override
	public PageAjax<User> findAllUsers() {
		User user = new User();
		user.setId("0");
		user.setName(null);
		user.setUsername(null);
		user.setBalance(null);
		ArrayList<User> list = new ArrayList<User>();
		list.add(user);
		return AppUtil.returnPage(list);
	}


	@Override
	public AjaxResult add(String username, String name, Integer age, String balance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AjaxResult deleteByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updatePage(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AjaxResult update(String id, String username, String name, Integer age, String balance) {
		// TODO Auto-generated method stub
		return null;
	}

}

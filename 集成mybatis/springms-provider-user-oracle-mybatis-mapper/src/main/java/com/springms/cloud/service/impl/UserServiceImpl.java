package com.springms.cloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springms.cloud.common.pojo.AjaxResult;
import com.springms.cloud.common.pojo.PageAjax;
import com.springms.cloud.common.utils.AppUtil;
import com.springms.cloud.entity.User;
import com.springms.cloud.mapper.IUserMapper;
import com.springms.cloud.service.IUserService;

/**
 * 简单用户链接oracle数据库微服务（通过@Service注解标注该类为持久化操作对象）。
 *
 * @author dfd
 *
 * @version 0.0.1
 *
 * @date 2018-6-22
 *
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserMapper iUserDao;

	@Override
	public User findUserById(String id) {
		return iUserDao.findUserById(id);
	}

	@Override
	public PageAjax<User> findAllUsers() {
		List<User> list = iUserDao.findAllUsers();
		return AppUtil.returnPage(list);
	}

	@Override
	public AjaxResult insertUser(User user) {
		iUserDao.insertUser(user);
		String result=null;
		return AppUtil.returnObj(result);
	}

	@Override
	public AjaxResult updateUser(User user) {
		iUserDao.updateUser(user);
		String result=null;
		return AppUtil.returnObj(result);
	}

	@Override
	public AjaxResult deleteByID(String id) {
		iUserDao.delete(id);
		String result=null;
		return AppUtil.returnObj(result);
	}
}
package com.springms.cloud.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springms.cloud.common.Constant;
import com.springms.cloud.common.annotation.ServiceLog;
import com.springms.cloud.common.pojo.AjaxResult;
import com.springms.cloud.common.pojo.ParamData;
import com.springms.cloud.common.utils.AppUtil;
import com.springms.cloud.common.utils.MD5Util;
import com.springms.cloud.entity.AuthUser;
import com.springms.cloud.mapper.AuthUserMapper;

/**
 * 登录管理业务层
 * 
 * @author CZH
 */
@Service
public class LoginService {
	@Autowired
	private AuthUserMapper userMapper;

	@ServiceLog("登录")
	public AjaxResult login(String username, String password) {
		String result = null;
		AuthUser user = userMapper.queryByUsername(username);
		if (user == null || !user.getPassword().equals(password)) {
			result = "用户名或密码错误";
		}
		return AppUtil.returnObj(result);
	}
}

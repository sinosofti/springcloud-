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
 * @author CZH
 */
@Service
public class LoginService  {
	@Autowired
	private AuthUserMapper userMapper;

	@ServiceLog("登录")
	public AjaxResult login(HttpServletRequest request, HttpServletResponse response) {
		String verifyCode = (String) request.getSession().getAttribute(Constant.VERIFY_CODE);
		String result = null;
		ParamData params = new ParamData();
		String vcode = params.getString("vcode");
		if (params.containsKey("vcode") && (StringUtils.isEmpty(verifyCode) || !verifyCode.equalsIgnoreCase(vcode))) {
			result = "验证码错误";
		}else{
			String username = params.getString("username");
			String loginIp = params.getString("loginIp");
			String key = username + loginIp + Constant.LOGIN_ERROR_TIMES;
			AuthUser user = userMapper.queryByUsername(username);
			
			if (user == null || !user.getPassword().equals(params.getString("password"))) {
				//int errTimes = dataCache.getInt(key);
				//记录密码错误次数,达到3次则需要输出验证码
				//dataCache.setValue(key, errTimes += 1);
				result = "用户名或密码错误|" ;
			}/*else if(Constant.ROLE_ANONYMOUS.equals(user.getRole().getRolename())){
				result = "用户未分组,无法登录";
			}*//*else{
				CookieUtil.set(Constant.SESSION_IDENTITY_KEY, sessionId, response);
			}*/
		}
		return AppUtil.returnObj(result);
	}
/*
	@ServiceLog("退出")
	public AjaxResult logout(HttpServletResponse response, HttpServletRequest request) {
		String sessionId = CookieUtil.get(Constant.SESSION_IDENTITY_KEY, request);

		if (StringUtils.isNotEmpty(sessionId)) {
			dataCache.remove(sessionId);
			String userName = (String) dataCache.getValue(sessionId);
			if (StringUtils.isNotEmpty(userName)) {
				dataCache.remove(userName + IPUtil.getIpAdd(request));
			}
			CookieUtil.delete(Constant.SESSION_IDENTITY_KEY, request, response);
		}
		return AppUtil.returnObj(null);
	}*/

	private String getSessionId(String userName, String ip) {
		String str = userName + "_" + System.currentTimeMillis() + "_" + ip;
		try {
			return MD5Util.encrypt(str);
		} catch (Exception e) {
			return "生成token错误";
		}
	}
}

package com.springms.cloud.service;

import com.springms.cloud.common.pojo.AjaxResult;
import com.springms.cloud.common.pojo.PageAjax;
import com.springms.cloud.entity.AuthUser;
import com.springms.cloud.entity.User;

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
public interface IUserService {

    User findUserById(String id);

    PageAjax<User> findAllUsers();

    AjaxResult insertUser(User user);

	AjaxResult updateUser(User user);

	AjaxResult deleteByID(String id);
}

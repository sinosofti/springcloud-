package com.springms.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springms.cloud.entity.User;

/**
 * 用户 mybatis 接口文件。
 *
 * @author DFD
 *
 * @version 0.0.1
 *
 * @date 2018-6-22
 *
 */
public interface IUserMapper {

	User findUserById(String id);

	List<User> findAllUsers();

	int insertUser(User user);

	void delete (String id);

	void updateUser(User user);
}
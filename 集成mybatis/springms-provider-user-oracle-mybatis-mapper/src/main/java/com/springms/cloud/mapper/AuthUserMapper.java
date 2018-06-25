package com.springms.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springms.cloud.common.pojo.AjaxResult;
import com.springms.cloud.entity.AuthUser;

public interface AuthUserMapper {

	AuthUser queryByUsername(@Param("username") String username);

	List<Integer> queryRoleUids(@Param("roleid") int roleid);

/*	int insertUser(AuthUser user);

	void updateByID(AuthUser user);*/

	AuthUser findUserById(@Param("id") Long id);

	AjaxResult delete(@Param("id") Long id);
}
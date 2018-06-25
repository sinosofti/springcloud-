package com.springms.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springms.cloud.entity.AuthUser;

public interface AuthUserMapper {

	AuthUser queryByUsername(@Param("username") String username);

	List<AuthUser> queryList(@Param("user")AuthUser user);

	List<Integer> queryRoleUids(@Param("roleid")int roleid);
}
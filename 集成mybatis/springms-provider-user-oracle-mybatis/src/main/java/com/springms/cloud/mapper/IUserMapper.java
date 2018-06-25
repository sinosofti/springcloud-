package com.springms.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.springms.cloud.entity.User;

/**
 * 用户 mybatis 映射文件。
 *
 * @author dfd
 *
 * @version 0.0.1
 *
 * @date 2018-6-19
 *
 */
public interface IUserMapper {

    @Select("select * from t_user where id = #{id}")
    User findUserById(Long id);

    @Select("select * from t_user")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "username", column = "username"),
        @Result(property = "name", column = "name"),
        @Result(property = "age", column = "age"),
        @Result(property = "balance", column = "balance") })
    List<User> findAllUsers();
    @SelectKey(statement="select sys_guid() as id from dual",   
    	    keyProperty="id", resultType=String.class, before=true)   
    @Insert("INSERT INTO t_user(id,username, name, age, balance) VALUES(#{id},#{username}, #{name}, #{age}, #{balance})")
	/**
	 * 使用mybatis插入oracle数据库需要创建sequence p: 因为oracle数据库不是自增长
	 * 例：
		create sequence T_USER_SEQ
		start with 1
		increment by 1
		minvalue 1 
		nomaxvalue 
	 * @param user
	 * @return
	 */
    int insertUser(User user);

    @Update("UPDATE t_user SET username=#{username} WHERE id=#{id}")
    int updateEntity(User user);

    @Delete("DELETE FROM t_user WHERE user_id =#{id}")
    int delete(Long userId);

    @Delete("DELETE FROM user WHERE user_id =#{id}")
    int deleteEntity(User entity);
}
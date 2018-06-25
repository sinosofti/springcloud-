package com.springms.cloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springms.cloud.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	/**
	 * 根id查询所有字段
	 * @param id
	 * @return
	 */
	@Query(value="select * from t_user where id=?1",nativeQuery=true)
	public List<User> listBySaleListId(String id);
	
	/**
	 * 删除
	 * @param id
	 */
	@Query(value="delete from t_user where id=?1",nativeQuery=true)
	@Modifying
	public void deleteBySaleListId(String id);

	/**
	 * 根据名称模糊查信息
	 * @param name
	 * @return
	 */
	@Query(value="select * from t_user where name like ?1",nativeQuery=true)
	public List<User> findByName(String name);
}

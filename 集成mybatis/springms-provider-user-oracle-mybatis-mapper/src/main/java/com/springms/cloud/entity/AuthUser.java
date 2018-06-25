package com.springms.cloud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "AUTH_USER")
public class AuthUser {
	@Id
	@GeneratedValue
	private String id;

	private String username;

	private String password;
	/**
	 * 所属角色
	 */
	private Integer roleid;
	private String email;
	/**
	 * 是否可用(0禁用,1可用)
	 */
	private String useable;

	/**
	 * 创建时间
	 */
	private String addtime;

	/**
	 * 登陆时间
	 */
	private String logintime;

	/**
	 * 登陆IP
	 */
	private String loginip;

	/**
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取创建时间
	 *
	 * @return addtime - 创建时间
	 */
	public String getAddtime() {
		return addtime;
	}

	/**
	 * 设置创建时间
	 *
	 * @param addtime
	 *            创建时间
	 */
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	/**
	 * 获取登陆时间
	 *
	 * @return logintime - 登陆时间
	 */
	public String getLogintime() {
		return logintime;
	}

	/**
	 * 设置登陆时间
	 *
	 * @param logintime
	 *            登陆时间
	 */
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}

	/**
	 * 获取登陆IP
	 *
	 * @return loginip - 登陆IP
	 */
	public String getLoginip() {
		return loginip;
	}

	/**
	 * 设置登陆IP
	 *
	 * @param loginip
	 *            登陆IP
	 */
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

}
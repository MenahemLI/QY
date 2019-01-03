package com.three.dms.bean;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="db_user")
public class Users implements Serializable{

	/**
	 * 创建users类
	 */
	private static final long serialVersionUID = 1L;
	private Long id;//id
	private String u_name;//用户名
	private String u_number;//编号
	private String u_post;//职务
	private Boolean u_limit;//通过判断职务授予权限
	private String u_tel;//联系方式
	private String u_idnum;//证件号
	private String u_email;//邮件
	private String u_password;//密码
	private String u_gernder;//性别
	/**
	 * 提供无参的构造方法
	 */
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 提供有参的构造方法
	 * @param id
	 * @param u_name
	 * @param u_number
	 * @param u_post
	 * @param u_limit
	 * @param u_tel
	 * @param u_idnum
	 * @param u_email
	 * @param u_password
	 */


	/**
	 * 提供set get 方法
	 * @return
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Long getId() {
		return id;
	}
	public Users(Long id, String u_name, String u_number, String u_post, Boolean u_limit, String u_tel, String u_idnum,
			String u_email, String u_password, String u_gernder, String picturePath) {
		super();
		this.id = id;
		this.u_name = u_name;
		this.u_number = u_number;
		this.u_post = u_post;
		this.u_limit = u_limit;
		this.u_tel = u_tel;
		this.u_idnum = u_idnum;
		this.u_email = u_email;
		this.u_password = u_password;
		this.u_gernder = u_gernder;
	}


	public void setId(Long id) {
		this.id = id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_number() {
		return u_number;
	}
	public void setU_number(String u_number) {
		this.u_number = u_number;
	}
	public String getU_post() {
		return u_post;
	}
	public void setU_post(String u_post) {
		this.u_post = u_post;
	}
	public Boolean getU_limit() {
		return u_limit;
	}
	public void setU_limit(Boolean u_limit) {
		this.u_limit = u_limit;
	}
	public String getU_tel() {
		return u_tel;
	}
	public void setU_tel(String u_tel) {
		this.u_tel = u_tel;
	}
	public String getU_idnum() {
		return u_idnum;
	}
	public void setU_idnum(String u_idnum) {
		this.u_idnum = u_idnum;
	}
	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		this.u_email = u_email;
	}
	public String getU_password() {
		return u_password;
	}
	public void setU_password(String u_password) {
		this.u_password = u_password;
	}
	
	public String getU_gernder() {
		return u_gernder;
	}
	public void setU_gernder(String u_gernder) {
		this.u_gernder = u_gernder;
	}
	
	@Override
	public String toString() {
		return "Users [id=" + id + ", u_name=" + u_name + ", u_number=" + u_number + ", u_post=" + u_post + ", u_limit="
				+ u_limit + ", u_tel=" + u_tel + ", u_idnum=" + u_idnum + ", u_email=" + u_email + ", u_password="
				+ u_password + ", u_gernder=" + u_gernder + "]";
	}
	
	
   
}

package com.three.dms.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

import com.opensymphony.xwork2.ActionSupport;
import com.three.dms.bean.Users;
import com.three.dms.service.Info.IUsersService;

public class IndexAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String u_gernder;
	private String msg;
	private String u_number;
	private String u_password;
	private Long id;
	//获取文件
	private File picturePath;
	//获取文件名，名字必需这样写
	private String picturePathFileName;
	//文件的保存路径
	private String newFilePath;

	private String u_name;
	private String u_post;
	private String u_limit;
	private String u_tel;
	private String u_idnum;
	private String u_email;
	// 下 user_one 用于设置用户信息用。
	private Users user_one;
	@Autowired
	IUsersService usersService;

	@Autowired
	IUsersService usersServoice;
	Users user = new Users();

	@Action(value = "toIndex", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsp/frame.jsp") })
	public String toIndex() {
		user = usersServoice.findbynumber(u_number);
		return SUCCESS;
	}

	/**
	 * 登陆页面
	 */

	@Action(value = "login", results = { @Result(name = SUCCESS, location = "/index.jsp"),
			@Result(name = ERROR, location = "/index.jsp") })
	public String login() {
		String flag = SUCCESS;
		try {
			System.out.println("编号" + u_number + "密码" + u_password);
			Users user = usersService.login(u_number, u_password);
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute("user", user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = ERROR;
			msg = e.getMessage();
		}

		return flag;
	}

	/**
	 * 登陆验证密码页面
	 * 
	 * @throws IOException
	 */

	@Action(value = "loginJudge")
	public void loginJudge() throws IOException {
		HttpServletRequest reqest = ServletActionContext.getRequest();
		reqest.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 获取前台的u_number
		String u_number = reqest.getParameter("number");
		// 通过u_number找到user的数据库密码
		Users user = usersService.findbynumber(u_number);
		// 数据库的密码
		String db_password = user.getU_password();
		// 获取前台的密码
		String u_password = reqest.getParameter("password");
		if (db_password.equals(u_password)) {
			System.out.println("相等");
			String res = "SUCCESS";
			out.println(res);
			out.flush();
			out.close();
		} else {
			System.out.println("不相等");
			String res = "ERROR";
			out.println(res);
			out.flush();
			out.close();
		}
	}

	/**
	 * 注册页面
	 * 
	 * @return
	 */
	// 完成注册
	@Action(value = "register", results = { @Result(name = "success", location = "/WEB-INF/jsp/login.jsp"),
			@Result(name = "error", location = "/WEB-INF/jsp/Register.jsp") })
	public String register() {
		String flag = SUCCESS;

		try {
			
			user = new Users(null, u_name, u_number, u_post, null, u_tel, u_idnum, u_email, u_password, u_gernder,
					null);
			System.out.println(user.toString());
			usersService.register(user);
		} catch (Exception e) {
			flag = ERROR;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 退出按钮,退出登陆
	 */
	@Action(value = "logout", results = { @Result(name = SUCCESS, location = "/index.jsp"),
			@Result(name = ERROR, location = "/WEB-INF/jsp/login.jsp") })
	public String logout() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Users session_user = (Users) session.getAttribute("user");
		if (session_user == null)
			return ERROR;
		else {
			session.removeAttribute("user");
			return SUCCESS;
		}
	}

	/**
	 * 跳转到个人简介页面
	 */
	@Action(value = "toIntroduce", results = {
			@Result(name = SUCCESS, location = "/WEB-INF/jsp/manager/introduce.jsp") })
	public String toIntroduce() {

		return SUCCESS;
	}

	/**
	 * 跳转到登录页面
	 */
	@Action(value = "toLogin", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsp/login.jsp") })
	public String toLogin() {
		return SUCCESS;
	}

	/**
	 * 跳转到修改页面
	 */
	@Action(value = "toSetPassword", results = {
			@Result(name = SUCCESS, location = "/WEB-INF/jsp/manager/setPassword.jsp") })
	public String toSetPassword() throws IOException {
		return SUCCESS;
	}

	/**
	 * 修改密码页面,判断与原密码是否一致
	 * 
	 * @throws IOException
	 */
	@Action(value = "setPassword")
	public void setPassword() throws IOException {
		System.out.println("判断密码是够正确");
		// 首先获取user对象，从user对象中获取原来密码
		HttpSession session = ServletActionContext.getRequest().getSession();
		Users session_user = (Users) session.getAttribute("user");
		String old_password = session_user.getU_password();
		// 获取前台传入的password值
		HttpServletRequest reqest = ServletActionContext.getRequest();
		reqest.setCharacterEncoding("UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 新密码指的是需要确认的密码
		String new_password = reqest.getParameter("old_u_password");
		System.out.println(new_password);
		if (old_password.equals(new_password)) {
			String res = "SUCCESS";
			out.println(res);
			out.flush();
			out.close();
		} else {
			String res = "ERROR";
			out.println(res);
			out.flush();
			out.close();
		}
	}

	/**
	 * 修改密码页面
	 * 
	 * @throws IOException
	 */
	@Action(value = "updatePassword", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsp/login.jsp") })
	public String updatePassword() throws IOException {
		System.out.println(u_password);
		String new_password = u_password;

		usersServoice.updatePassword(new_password);

		// HttpSession session = ServletActionContext.getRequest().getSession();
		// session.removeAttribute("user");
		return SUCCESS;
	}

	/**
	 * 跳转到设置用户信息页面
	 */
	@Action(value = "setUserjsp", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsp/manager/setUser.jsp"),
			@Result(name = ERROR, location = "/WEB-INF/jsp/login.jsp") })
	public String setUser() {
		/**
		 * 从session中获取user的信息
		 */
		HttpSession session = ServletActionContext.getRequest().getSession();
		Users session_user = (Users) session.getAttribute("user");
		if (session_user == null)
			return ERROR;
		else {
			String user_phone = session_user.getU_tel();
			user_one = usersServoice.findbytel(user_phone);
			return SUCCESS;
		}
	}

	/**
	 * 跳转到注册页面
	 */
	@Action(value = "toRegister", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsp/Register.jsp") })
	public String toRegister() {
		return SUCCESS;
	}

	/**
	 * 修改用户信息
	 */
	@Action(value = "updateUserInfomation", results = { @Result(name = SUCCESS, location = "/index.jsp") })
	public String updateUserInfomation() {
		try {
			System.out.println("fangwenda");
			HttpSession session = ServletActionContext.getRequest().getSession();
			Users session_user = (Users) session.getAttribute("user");
			Long id = session_user.getId();
			String password = session_user.getU_password();
			user = new Users(id, u_name, u_number, u_post, null, u_tel, u_idnum, u_email, password, u_gernder,
					newFilePath);
			System.out.println(user.toString());
			usersService.updateUserInfomation(user);
			// 移除原来的user
			session.removeAttribute("user");
			// 将新user加入
			session.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getU_number() {
		return u_number;
	}

	public void setU_number(String u_number) {
		this.u_number = u_number;
	}

	public String getU_password() {
		return u_password;
	}

	public void setU_password(String u_password) {
		this.u_password = u_password;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Long getId() {
		return id;
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

	public String getU_post() {
		return u_post;
	}

	public void setU_post(String u_post) {
		this.u_post = u_post;
	}

	public String getU_limit() {
		return u_limit;
	}

	public void setU_limit(String u_limit) {
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

	public String getU_gernder() {
		return u_gernder;
	}

	public void setU_gernder(String u_gernder) {
		this.u_gernder = u_gernder;
	}

	public Users getUser_one() {
		return user_one;
	}

	public void setUser_one(Users user_one) {
		this.user_one = user_one;
	}

	public File getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(File picturePath) {
		this.picturePath = picturePath;
	}

	public String getNewFilePath() {
		return newFilePath;
	}

	public void setNewFilePath(String newFilePath) {
		this.newFilePath = newFilePath;
	}

	public String getPicturePathFileName() {
		return picturePathFileName;
	}

	public void setPicturePathFileName(String picturePathFileName) {
		this.picturePathFileName = picturePathFileName;
	}



}

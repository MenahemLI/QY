package com.three.dms.webapp.action;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.three.dms.bean.Users;
import com.three.dms.service.Info.IUsersService;

public class UsersAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	Users users = new Users();
	@Autowired
	IUsersService usersService;
	/**
	 * 注册时进行验证
	 * 从后台获取到员工注册时的信息
	 * 判断注册信息的类型
	 * 验证并给前台返回结果
	 * @throws IOException
	 */
	@Action(value="findinf")
	public void findinf() throws IOException{
		System.out.println("到达Users");
		HttpServletRequest reqest= ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		reqest.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String map = reqest.getParameter("u_name");
		String value= reqest.getParameter("namec");
		switch(value){
		case "u_tel": users = usersService.findbytel(map);break;
		case "u_email": users = usersService.findbyemail(map);break;
		case "u_number": users = usersService.findbynumber(map);break;
		case "u_idnum": users = usersService.findbyidnum(map);break;
		}
		if(users==null){
			 String res="1";
		  out.println(res);
		  out.flush();  
		  out.close();
		}else {
			 String res="0";
			  out.println(res);
			  out.flush();  
			  out.close();
		}
		
	}   
}

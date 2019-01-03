package com.three.dms.webapp.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.three.dms.bean.Users;

public class AuthInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Users user = (Users) session.getAttribute("user");
		if(user!=null){
			System.out.println("user不为空没有拦截");
			return invocation.invoke();
		}
		System.out.println("user为空，拦截饿了");
		return "toLogin";
	}

}

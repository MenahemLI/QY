package com.three.dms.webapp.action.manager;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

public class OfficeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 跳转到登录页面
	 * */
	@Action(value="toOffice",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/manager/details.jsp")})
	public String toOffice(){
		return SUCCESS;
	}
	

}

package com.three.dms.service.Info;

import com.three.dms.bean.Users;

public interface IUsersService {
	void save(Users users);
	Users findbyid(long id);
	void  updatePassword(String password);
	void register(Users users)throws Exception;
	Users login(String u_number, String u_password) throws Exception;
	Users findbynumber(String u_number);
	Users findbytel(String u_tel);
	Users findbyidnum(String u_idnum);
	Users findbyemail(String u_email);
	//更新用户信息
	void updateUserInfomation(Users users) throws Exception;
}

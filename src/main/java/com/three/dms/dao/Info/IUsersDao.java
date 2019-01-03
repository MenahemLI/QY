package com.three.dms.dao.Info;
import com.three.dms.bean.Users;

public interface IUsersDao {
	void save(Users users);
	void update(Users users);
	void merge(Users users);
	Users findbyid(long id);
	void delete(long id);
	boolean exists(long id);
	Users findbynumber(String u_number);
	Users findbytel(String u_tel);
	Users findbyidnum(String u_idnum);
	Users findbyemail(String u_email);
	void updatePassword(String password);
}

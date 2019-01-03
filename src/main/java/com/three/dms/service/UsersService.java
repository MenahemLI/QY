package com.three.dms.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.dms.bean.Users;
import com.three.dms.dao.Info.IUsersDao;
import com.three.dms.service.Info.IUsersService;


@Service
public class UsersService implements IUsersService{
	@Autowired
	IUsersDao usersDao;
	//实例化users对象
	Users users = new Users();
	@Override
	public void save(Users users) {
		System.out.println("2");
		usersDao.save(users);
		
	}
	@Override
	public Users findbynumber(String u_number) {
		users = usersDao.findbynumber(u_number);
		return users;
	}

	@Override
	public Users findbyid(long id) {
		users = usersDao.findbyid(id);
		return users;
	}
	//注册方法
	@Override
	public void register(Users users) throws Exception {
		Users user = usersDao.findbynumber(users.getU_number());
		if (user==null) {
			usersDao.save(users);
		} else {
			
			throw new Exception("该编号已被注册");
		}
	}
	/**
	 * 修改账户信息
	 * */
	@Override
	public void updateUserInfomation(Users users) throws Exception {
		System.out.println("到达Service层");
			usersDao.update(users);
	}
	//此方法的主要作用是判断正在登陆的信息是否存在
	@Override
	public Users login(String u_number, String u_password) throws Exception {
		// TODO Auto-generated method stub
		users =usersDao.findbynumber(u_number);
		if(users!=null){
			if(u_password.equals(users.getU_password())){
				return users;
			}
			throw new Exception("编号与密码不匹配");
		}else {
			throw new Exception("没有此编号的员工");
		}
	}
	@Override
	public Users findbytel(String u_tel) {
		Users users =new Users();
		users = usersDao.findbytel(u_tel);
		return users;
	}
	@Override
	public Users findbyidnum(String u_idnum) {
		users = usersDao.findbyidnum(u_idnum);
		return users;
	}
	@Override
	public Users findbyemail(String u_email) {
		// TODO Auto-generated method stub
				users = usersDao.findbyemail(u_email);
				return users;
	}
	@Override
	public void updatePassword(String password) {
		System.out.println();
		usersDao.updatePassword(password);
	}

	
}

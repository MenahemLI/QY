package com.three.dms.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.three.dms.bean.Users;
import com.three.dms.dao.Info.IUsersDao;

@Repository
public class UsersDao implements IUsersDao{
	//建立hibernate的session工厂，获取到session进行与数据库的操作
	
	//实例化users对象
	Users users = new Users();
	
	/**
	 * 创建hibernate会话工厂
	 * 提供getsession方法与数据库交互
	 */
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
/**
 * 从前台获取职务信息进行判断
 * 经理   权限为TRUE
 * 会计   权限为FALSE
 */
	@Override
	public void save(Users users) {
		if(users.getU_post().equals("经理")){
			users.setU_limit(true);
		}else{
			users.setU_limit(false);
		}
		getSession().save(users);
	}
	
	@Override
	public void update(Users users) {
		getSession().update(users);
	}

	@Override
	public void merge(Users users) {
		getSession().merge(users);
	}

	@Override
	public Users findbyid(long id) {
		String hql = "from Users us where us.id=?";
		users =(Users) getSession().createQuery(hql).setLong(0, id).list();
		return users;
	}

	@Override
	public void delete(long id) {
		users =  ((IUsersDao) users).findbyid(id);
		getSession().delete(users);
	}

	@Override
	public boolean exists(long id) {
		return false;
	}

	@Override
	public Users findbynumber(String u_number) {
		String hql = "from Users us where us.u_number=?";
		users =(Users) getSession().createQuery(hql).setString(0, u_number).uniqueResult();
		return users;
	}

	@Override
	public Users findbytel(String u_tel) {
		String hql = "from Users us where us.u_tel=?";
		users =(Users) getSession().createQuery(hql).setString(0, u_tel).uniqueResult();
		return users;
	}

	@Override
	public Users findbyidnum(String u_idnum) {
		String hql = "from Users us where us.u_idnum=?";
		users =(Users) getSession().createQuery(hql).setString(0, u_idnum).uniqueResult();
		return users;
	}

	@Override
	public Users findbyemail(String u_email) {
		String hql = "from Users us where us.u_email=?";
		users =(Users) getSession().createQuery(hql).setString(0, u_email).uniqueResult();
		return users;
	}
	@Override
	public void updatePassword(String password) {
		    Session session = getSession();
			session.beginTransaction();  
	        Query query = session.createQuery("update Users t set t.u_password = ?").setString(0, password);  
	        query.executeUpdate();
	}
}

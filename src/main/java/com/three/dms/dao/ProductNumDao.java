package com.three.dms.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.three.dms.bean.ProdutNum;
import com.three.dms.dao.Info.IProductNumDao;

@Repository
public class ProductNumDao implements IProductNumDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void sava(ProdutNum produtNum) {
		getSession().save(produtNum);
	}

	@Override
	public void update(ProdutNum produtNum) {
		getSession().update(produtNum);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProdutNum> findbydate(String datey) {
		List<ProdutNum> produtNums = new ArrayList<>();
		String hql = "from ProdutNum ideta where ideta.datey=?";
		produtNums = getSession().createQuery(hql).setString(0, datey).list();
		return produtNums;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProdutNum> findbydateandname(String datey, String productn) {
		List<ProdutNum> produtNums = new ArrayList<>();
		String hql = "from ProdutNum ideta where ideta.datey=? and ideta.productn=?";
		produtNums = getSession().createQuery(hql).setString(0, datey).setString(1, productn).list();
		return produtNums;
	}

}

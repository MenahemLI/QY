package com.three.dms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.three.dms.bean.Goods;
import com.three.dms.dao.Info.IGoodsDao;
@Repository
public class GoodsDao implements IGoodsDao{
	Goods goods = new Goods();
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	
	/** 
	 * 通过session对象调用save内置方法，进行货物的存储
	 * */
	@Override
	public void save(Goods goods) {
		getSession().save(goods);
	}

	/**
	 * 进行货物的查找功能
	 * */

	@Override
	public List<Goods> findByG_name(String g_name) {
		//获取Session对象
		/**
		 * 查找对象第一种方式
		 * */
//		List<Goods> goods_two;
//		String hql = "from Goods goods_two where goods_two.g_format = ? ";
//		goods_two =getSession().createQuery(hql).setString(0, g_format).list();
//		Goods goods_three =goods_two.get(0);
//		System.out.println(goods_three);
		/**
		 * 查找对象第二种方式
		 * */
		List<Goods> goods = new ArrayList<>();
		String hql = "from Goods goods where goods.g_name = ?";
		 goods  = getSession().createQuery(hql).setString(0, g_name).list();		
		return goods;
	}


	@Override
	public void update(Goods goods) {
		// TODO Auto-generated method stub
		System.out.println("更新goods持久层");
		System.out.println("goodsDao里面的goods是"+goods.toString());
		System.out.println(goods.toString());
		getSession().update(goods);
	}


	@Override
	public Goods findByG_nameAndformat(String g_name, String g_format) {
		String hql = "from Goods goods where goods.g_name = ? and goods.g_format = ?";
		Goods goods  =(Goods) getSession().createQuery(hql).setString(0, g_name).setString(1,g_format).uniqueResult();		
		return goods;
	}

 
	@SuppressWarnings("unchecked")
	@Override
	public TreeSet<String> findbytag(String tag) {
		List<String> goods_name = new ArrayList<>();
		TreeSet<String> tree = new TreeSet<>();
		String hql = "select goods.g_name from Goods goods where goods.tag = ?";
		goods_name  = getSession().createQuery(hql).setString(0, tag).list();	
		for (String name : goods_name) {
			tree.add(name);
		}
		return  tree;
	}


	
	
}
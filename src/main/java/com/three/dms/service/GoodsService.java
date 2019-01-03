package com.three.dms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.apache.struts2.components.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.dms.bean.Goods;
import com.three.dms.dao.Info.IGoodsDao;
import com.three.dms.service.Info.IGoodsService;

@Service
public class GoodsService implements IGoodsService {
	 
	Goods goods =new Goods();
	@Autowired
	IGoodsDao goodsDao;
	/**
	 * 货物保存
	 * */
	@Override
	public void save(Goods goods) {
		System.out.println(" 访问到GoodsService， 下层函数为Dao");
		goodsDao.save(goods);
	}
	/**
	 * 货物查找，通过货物的任何属性进行查找
	 * */
	
	@Override
	public void update(Goods goods) {
		// TODO Auto-generated method stub
		System.out.println("更新GOODS业务层");
		goodsDao.update(goods);
		
	}
	@Override
	public List<Goods> findByG_name(String g_name) {
		// TODO Auto-generated method stub
		
		return goodsDao.findByG_name(g_name);
	}
	@Override
	public Goods findByG_nameAndformat(String g_name, String g_format) {
		
		return goodsDao.findByG_nameAndformat(g_name, g_format);
	}
	@Override
	public TreeSet<String> findbytag(String tag) {
		TreeSet<String> goods_name = new TreeSet<>();
		goods_name = goodsDao.findbytag(tag);
		return goods_name;
	}

}

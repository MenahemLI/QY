package com.three.dms.dao.Info;

import java.util.List;
import java.util.TreeSet;

import com.three.dms.bean.Goods;

public interface IGoodsDao {
	 //货物增加
	void save(Goods goods);
	//货物寻找功能
	List<Goods> findByG_name(String g_name);
	TreeSet<String> findbytag(String tag);
	Goods findByG_nameAndformat(String g_name,String g_format);
	void update(Goods goods);
	
}

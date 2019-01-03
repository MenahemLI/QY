package com.three.dms.service.Info;

import java.util.List;
import java.util.TreeSet;

import com.three.dms.bean.Goods;

/**
 * Goods 接口，GoodsService 具体实现
 * */
public interface IGoodsService {
   //Goods 添加方法
	void save(Goods goods);
	//货物寻找,通过规格型号
	List<Goods> findByG_name(String g_name);
	TreeSet<String> findbytag(String tag);
	Goods findByG_nameAndformat(String g_name,String g_format);
	void update(Goods goods);
}

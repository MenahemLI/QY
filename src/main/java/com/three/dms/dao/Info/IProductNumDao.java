package com.three.dms.dao.Info;

import java.util.List;

import com.three.dms.bean.ProdutNum;

public interface IProductNumDao {
	void sava (ProdutNum produtNum);
	void update (ProdutNum produtNum);
	List<ProdutNum> findbydate(String datey);
	List<ProdutNum> findbydateandname(String datey,String productn);
	
}
